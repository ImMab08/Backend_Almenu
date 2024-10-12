package org.example.backend_almenu.controller;

import org.example.backend_almenu.dto.menu.MenuDTO;
import org.example.backend_almenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/v01/menu/")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("usuario/{idCategoria}/productos")
    public ResponseEntity<List<MenuDTO>> getMenuUsuario(@PathVariable("idCategoria") int idCategoria, Authentication authentication) {
        try {
            List<MenuDTO> menu = menuService.getMenuUsuario(idCategoria, authentication);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
