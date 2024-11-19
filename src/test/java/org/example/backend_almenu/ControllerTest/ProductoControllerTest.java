package org.example.backend_almenu.ControllerTest;

import org.example.backend_almenu.controller.ProductoController;
import org.example.backend_almenu.dto.producto.ProductoDTO;
import org.example.backend_almenu.model.Producto;
import org.example.backend_almenu.service.ProductoService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductoControllerTest {

    @Autowired
    private ProductoController productoController;
    @Autowired
    private ProductoService productoService;
    @MockBean
    private Authentication authentication;

    @Test
    public void testGetProductosUsuario() {
        // Configurar el usuario autenticado
        Mockito.when(authentication.getName()).thenReturn("prueba@gmail.com");

        // Crear datos de prueba
        ProductoDTO producto1 = new ProductoDTO(1, "Producto A", "Descripción A", new BigDecimal(10000), 20, "imagen1.png", 1, "Categoría A", 1, "Subcategoría A");
        ProductoDTO producto2 = new ProductoDTO(2, "Producto B", "Descripción B", new BigDecimal(20000), 15, "imagen2.png", 1, "Categoría A", 2, "Subcategoría B");
        List<ProductoDTO> productos = Arrays.asList(producto1, producto2);

        // Simular comportamiento del servicio
        Mockito.when(productoService.getProductoUsuario(authentication)).thenReturn(productos);

        // Llamar al método del controlador
        List<ProductoDTO> resultado = productoController.getProductosUsuario(authentication);

        // Verificar resultados
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals("Producto A", resultado.get(0).getNombre());
    }

    @Test
    public void testCreateProducto() {
        // Configurar el usuario autenticado
        Mockito.when(authentication.getName()).thenReturn("prueba@gmail.com");

        // Crear datos de prueba
        ProductoDTO productoDTO = new ProductoDTO(1, "Producto A", "Descripción A", new BigDecimal(10000), 20, "imagen1.png", 1, "Categoría A", 1, "Subcategoría A");
        Producto producto = new Producto();
        producto.setId_producto(1);
        producto.setNombre("Producto A");

        // Simular comportamiento del servicio
        Mockito.when(productoService.createProducto(productoDTO, authentication)).thenReturn(producto);

        // Llamar al método del controlador
        ResponseEntity<?> response = productoController.createProducto(productoDTO, authentication);

        // Verificar resultados
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody() instanceof Producto);
        Assertions.assertEquals("Producto A", ((Producto) response.getBody()).getNombre());
    }

    @Test
    public void testDeleteProducto() {
        // Configurar el usuario autenticado
        Mockito.when(authentication.getName()).thenReturn("prueba@gmail.com");

        // Simular comportamiento del servicio
        Mockito.when(productoService.deleteProductoUsuarioById(1, authentication)).thenReturn("Producto eliminado exitosamente");

        // Llamar al método del controlador
        String mensaje = productoController.deleteProducto(1, authentication);

        // Verificar resultados
        Assertions.assertEquals("Producto eliminado exitosamente", mensaje);
    }
}
