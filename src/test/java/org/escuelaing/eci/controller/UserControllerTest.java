package org.escuelaing.eci.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.escuelaing.eci.Controller.UserController;
import org.escuelaing.eci.Security.JwtUtil;
import org.escuelaing.eci.repository.user.User;
import org.escuelaing.eci.service.user.UsersService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UsersService userService;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        // Arrange
        User user1 = new User("1", "John", "Doe", "john.doe@example.com", "password123");
        User user2 = new User("2", "Jane", "Doe", "jane.doe@example.com", "password123");
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        when(userService.all()).thenReturn(users);

        // Act
        ArrayList<User> result = userController.getUsers();

        // Assert
        assertEquals(2, result.size());
        verify(userService, times(1)).all();
    }

    @Test
    void testSaveUser() {
        // Arrange
        User user = new User("1", "John", "Doe", "john.doe@example.com", "password123");
        when(userService.save(any(User.class))).thenReturn(user);

        // Act
        User result = userController.saveUser(user);

        // Assert
        assertEquals(user, result);
        verify(userService, times(1)).save(user);
    }

    @Test
    void testGetUserByIdFound() {
        // Arrange
        User user = new User("1", "John", "Doe", "john.doe@example.com", "password123");
        when(userService.findById("1")).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userController.getUser("1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userService, times(1)).findById("1");
    }

    @Test
    void testGetUserByIdNotFound() {
        // Arrange
        when(userService.findById("1")).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userController.getUser("1");

        // Assert
        assertFalse(result.isPresent());
        verify(userService, times(1)).findById("1");
    }

    @Test
    void testCreateUser() {
        // Arrange
        User user = new User("1", "John", "Doe", "john.doe@example.com", "password123");
        when(userService.save(any(User.class))).thenReturn(user);

        // Act
        User result = userController.createUser(user);

        // Assert
        assertEquals(user, result);
        verify(userService, times(1)).save(user);
    }

    @Test
    void testUpdateUser() {
        // Arrange
        User user = new User("1", "John", "Doe", "john.doe@example.com", "password123");
        when(userService.update(any(User.class), anyString())).thenReturn(user);

        // Act
        User result = userController.updateUser(user, "1");

        // Assert
        assertEquals(user, result);
        verify(userService, times(1)).update(user, "1");
    }

    @Test
    void testDeleteUserFound() {
        // Arrange
        User user = new User("1", "John", "Doe", "john.doe@example.com", "password123");
        when(userService.deleteById("1")).thenReturn(user);

        // Act
        User result = userController.deleteUser("1");

        // Assert
        assertEquals(user, result);
        verify(userService, times(1)).deleteById("1");
    }

    @Test
    void testDeleteUserNotFound() {
        // Arrange
        when(userService.deleteById("1")).thenThrow(new RuntimeException("User with ID 1 not found"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userController.deleteUser("1");
        });
        assertEquals("User with ID 1 not found", exception.getMessage());
        verify(userService, times(1)).deleteById("1");
    }

    @Test
    void testTestEndpoint() {
        // Act
        String result = userController.testEndpoint();

        // Assert
        assertEquals("Controller is working!", result);
    }

    @Test
    public void testValidateTokenForDifferentUser() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username);
        String otherUsername = "differentUser";
        assertFalse(jwtUtil.validateToken(token, otherUsername));
 
        String usernameA = "testUser1";
        String tokenA = jwtUtil.generateToken(usernameA);
        String otherUsernameA = "differentUser1";
        assertFalse(jwtUtil.validateToken(tokenA, otherUsernameA));
 
        String usernameB = "testUserB";
        String tokenB = jwtUtil.generateToken(usernameB);
        String otherUsernameB = "differentUserB";
        assertFalse(jwtUtil.validateToken(tokenB, otherUsernameB));
    }
 
 
}
