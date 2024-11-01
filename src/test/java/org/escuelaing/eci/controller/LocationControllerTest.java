package org.escuelaing.eci.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.escuelaing.eci.Controller.LocationController;
import org.escuelaing.eci.Security.JwtUtil;
import org.escuelaing.eci.Security.UserDetailsServiceImpl;
import org.escuelaing.eci.repository.location.LocationA;
import org.escuelaing.eci.service.Location.LocationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = LocationController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;  

    @Test
    public void testGetLocations() throws Exception {
        ArrayList<LocationA> locations = new ArrayList<>();
        locations.add(new LocationA("1", 12.345f, 67.890f, "123 Main St"));

        when(locationService.all()).thenReturn(locations);

        mockMvc.perform(get("/location"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].address").value("123 Main St"));
    }

    @Test
    public void testSaveLocation() throws Exception {
        LocationA location = new LocationA("2", 12.345f, 67.890f, "123 Main St");

        when(locationService.save(Mockito.any(LocationA.class))).thenReturn(location);

        mockMvc.perform(post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": \"2\", \"address\": \"123 Main St\", \"lat\": 12.345, \"lon\": 67.890 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.address").value("123 Main St"));
    }

    @Test
    public void testGetLocationById() throws Exception {
        LocationA location = new LocationA("1", 12.345f, 67.890f, "123 Main St");

        when(locationService.findById("1")).thenReturn(Optional.of(location));

        mockMvc.perform(get("/location/getLocation/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.address").value("123 Main St"));
    }

    @Test
    public void testUpdateLocation() throws Exception {
        LocationA updatedLocation = new LocationA("1", 12.345f, 67.890f, "Updated Address");

        when(locationService.update(Mockito.any(LocationA.class), Mockito.eq("1"))).thenReturn(updatedLocation);

        mockMvc.perform(put("/location/update/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"address\": \"Updated Address\", \"lat\": 12.345, \"lon\": 67.890 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.address").value("Updated Address"));
    }

    @Test
    public void testDeleteLocation() throws Exception {
        // Ejecutar la solicitud de eliminación
        mockMvc.perform(delete("/location/delete/{id}", "1"))
                .andExpect(status().isOk());

        // Verificar que el servicio `deleteById` fue llamado correctamente
        verify(locationService, times(1)).deleteById("1");
    }
}
