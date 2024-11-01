package org.escuelaing.eci.controller;

import java.util.ArrayList;

import org.escuelaing.eci.Controller.AuthController;
import org.escuelaing.eci.Security.JwtUtil;
import org.escuelaing.eci.Security.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(value = AuthController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenticationManager authenticationManager;

    
    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;  

    @InjectMocks
    private AuthController authController;

    @Test
    public void testLoginSuccess() throws Exception {
        Authentication authentication = Mockito.mock(Authentication.class);
        User user = new User("username", "password", new ArrayList<>());

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(jwtUtil.generateToken(user.getUsername())).thenReturn("mocked_token");

        mockMvc.perform(post("/api/auth/login")
                .param("username", "username")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(content().string("mocked_token"));
    }
}
