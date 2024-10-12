package org.example.backend_almenu.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend_almenu.dto.producto.ProductoDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

    private int idSubcategoria;
    private String nombreSubcategoria;
    private List<ProductoDTO> productosDTO;

}
