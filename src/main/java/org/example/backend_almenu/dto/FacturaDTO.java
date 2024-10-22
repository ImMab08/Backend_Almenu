package org.example.backend_almenu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO {

    private int id_factura;
    private int id_usuario;
    private LocalDateTime fecha_factura;
    private BigDecimal total;

    private int id_mesa;
    private int numero_mesa;

    private int id_pedido;
    private List<DetallePedidoDTO> detalles;

    private int id_cliente;
    private String nombre_cliente;

    private int id_empleado;
    private String nombre_empleado;


}
