package org.escuelaing.eci.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.escuelaing.eci.Controller.PlaceController;
import org.escuelaing.eci.repository.place.Place;
import org.escuelaing.eci.service.place.PlaceService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

class PlaceControllerTest {

    @InjectMocks
    private PlaceController placeController;

    @Mock
    private PlaceService placeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPlaces() {
        // Arrange
        Place place1 = new Place("1", "Place 1", "Description 1", "password", "Food Type 1", "Value 1", null, null);
        Place place2 = new Place("2", "Place 2", "Description 2", "password", "Food Type 2", "Value 2", null, null);
        ArrayList<Place> places = new ArrayList<>();
        places.add(place1);
        places.add(place2);
        when(placeService.all()).thenReturn(places);

        // Act
        ArrayList<Place> result = placeController.getPlaces();

        // Assert
        assertEquals(2, result.size());
        verify(placeService, times(1)).all();
    }

    @Test
    void testSavePlace() {
        // Arrange
        Place place = new Place("1", "Place 1", "Description 1", "password", "Food Type 1", "Value 1", null, null);
        when(placeService.save(any(Place.class))).thenReturn(place);

        // Act
        Place result = placeController.savePlace(place);

        // Assert
        assertEquals(place, result);
        verify(placeService, times(1)).save(place);
    }

    @Test
    void testGetPlaceByIdFound() {
        // Arrange
        Place place = new Place("1", "Place 1", "Description 1", "password", "Food Type 1", "Value 1", null, null);
        when(placeService.findById("1")).thenReturn(Optional.of(place));

        // Act
        Optional<Place> result = placeController.getPlace("1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(place, result.get());
        verify(placeService, times(1)).findById("1");
    }

    @Test
    void testGetPlaceByIdNotFound() {
        // Arrange
        when(placeService.findById("1")).thenReturn(Optional.empty());

        // Act
        Optional<Place> result = placeController.getPlace("1");

        // Assert
        assertFalse(result.isPresent());
        verify(placeService, times(1)).findById("1");
    }

    @Test
    void testUpdatePlace() {
        // Arrange
        Place updatedPlace = new Place("1", "Updated Place", "Updated Description", "password", "Updated Food Type", "Updated Value", null, null);
        when(placeService.update(any(Place.class), anyString())).thenReturn(updatedPlace);

        // Act
        Place result = placeController.updatePlace(updatedPlace, "1");

        // Assert
        assertEquals(updatedPlace, result);
        verify(placeService, times(1)).update(updatedPlace, "1");
    }

    @Test
    void testDeletePlace() {
        // Arrange: Aquí solo se configura el comportamiento del mock sin necesidad de `doNothing()`
        // Act: Llamamos al método que queremos probar
        placeController.deletePlace("1");

        // Assert: Verificamos que el método `deleteById` del servicio se llame una vez con el parámetro correcto
        verify(placeService, times(1)).deleteById("1");
    }

}
