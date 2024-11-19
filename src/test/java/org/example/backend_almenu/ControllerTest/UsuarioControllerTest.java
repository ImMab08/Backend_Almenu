package org.example.backend_almenu.ControllerTest;

import org.example.backend_almenu.controller.usuario.UsuarioController;
import org.example.backend_almenu.dto.usuario.SettingsInfoUsuario;
import org.example.backend_almenu.service.usuario.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsuarioControllerTest {

    @Autowired
    private UsuarioController usuarioController;
    @MockBean
    private UsuarioService usuarioService;
    @MockBean
    private Authentication authentication;


    @Test
    public void testGetSettingsInfoUsuarioDto() {
        // Simulación del correo electrónico en la autenticación
        Mockito.when(authentication.getName()).thenReturn("usuario@gmail.com");

        // Crear un usuario simulado para la prueba
        SettingsInfoUsuario usuarioSimulado = new SettingsInfoUsuario();
        usuarioSimulado.setId_usuario("2");
        usuarioSimulado.setNombre("usuario");
        usuarioSimulado.setApellido("usuarios");
        usuarioSimulado.setCelular("123");
        usuarioSimulado.setEmail("usuario@gmail.com");
        usuarioSimulado.setPlan("gratuito");

        // Simular la respuesta del servicio
        Mockito.when(usuarioService.getSettingsInfoUsuarioDto(authentication)).thenReturn(usuarioSimulado);

        // Llamar al controlador
        SettingsInfoUsuario resultado = usuarioController.getSettingsInfoUsuarioDto(authentication);

        // Verificar resultados
        assertNotNull(resultado, "El resultado no debe ser nulo");
        assertEquals("usuario", resultado.getNombre(), "El nombre no coincide");
        assertEquals("usuario@gmail.com", resultado.getEmail(), "El correo electrónico no coincide");
        assertEquals("gratuito", resultado.getPlan(), "El plan no coincide");
    }
}

