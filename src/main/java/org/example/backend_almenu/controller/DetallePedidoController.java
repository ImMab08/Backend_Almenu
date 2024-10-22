package org.example.backend_almenu.controller;

import org.example.backend_almenu.dto.DetallePedidoDTO;
import org.example.backend_almenu.model.DetallePedido;
import org.example.backend_almenu.model.Pedido;
import org.example.backend_almenu.service.DetallePedidoService;
import org.example.backend_almenu.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/v01/detallespedido/")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    // Obtener todos los detalles de un pedido por ID del pedido
    @GetMapping("pedido/{id_pedido}")
    public ResponseEntity<List<DetallePedido>> getDetallesByPedidoId(@PathVariable Integer id_pedido, Authentication authentication) {
        List<DetallePedido> detalles = detallePedidoService.getDetallesByPedidoId(id_pedido, authentication);
        return ResponseEntity.ok(detalles);
    }

    // Crear un nuevo detalle para un pedido
    @PostMapping("create")
    public ResponseEntity<?> createDetallesPedido(@RequestBody List<DetallePedidoDTO> detallesPedidoDTO, Authentication authentication) {
        try {
            List<DetallePedido> newDetalles = detallePedidoService.createDetallesPedido(detallesPedidoDTO, authentication);
            return new ResponseEntity<>(newDetalles, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // Eliminar un detalle de pedido
    @DeleteMapping("delete/{id_detalle}")
    public ResponseEntity<Void> deleteDetallePedido(@PathVariable Integer id_detalle, Authentication authentication) {
        detallePedidoService.deleteDetallePedido(id_detalle, authentication);
        return ResponseEntity.noContent().build();
    }
}