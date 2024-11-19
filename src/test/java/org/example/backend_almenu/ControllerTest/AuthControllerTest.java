package org.example.backend_almenu.ControllerTest;

import org.example.backend_almenu.controller.usuario.AuthController;
import org.example.backend_almenu.dto.usuario.LoginInfoUsuario;
import org.example.backend_almenu.dto.usuario.LoginRequest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @Test
    public void testlogin_true() {

        LoginRequest informacionLogueo = new LoginRequest();
        informacionLogueo.setEmail("prueba@gmail.com");
        informacionLogueo.setPassword("123");

        ResponseEntity<?> response = authController.login(informacionLogueo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody(), "Body is null");
//        assertTrue(response.getBody() instanceof LoginRequest, "Body request is null");

        LoginInfoUsuario infoautenticidad = (LoginInfoUsuario) response.getBody();

        assertNotNull(infoautenticidad.getToken(), "El token no puede ser nulo");
        assertTrue(infoautenticidad.getToken() instanceof String, "El token debe de ser de tipo String");
        assertFalse(infoautenticidad.getToken().isEmpty(), "El token no deberia de estar vacio");
    }

}
