package org.example.backend_almenu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private int id_pedido;
    private int id_usuario;
    private LocalDateTime fecha_pedido;
    private String estado;

    private int id_mesa;
    private int numeroMesa;

    private List<DetallePedidoDTO> detalles;

}
