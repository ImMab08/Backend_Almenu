package org.example.backend_almenu.controller;

import org.example.backend_almenu.model.Mesa;
import org.example.backend_almenu.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/v01/mesa/")
public class MesaController {
    @Autowired
    MesaService mesaService;

    @GetMapping("usuario")
    public List<Mesa> getMesas(Authentication authentication) {
        try {
            System.out.println("Usuario autenticado" + authentication.getName());
            List<Mesa> mesas = mesaService.getMesasUsuario(authentication);

            System.out.println("Mesas obtenidas: " + mesas);

            return mesas;
        } catch (Exception e) {
            System.out.println("Error al obtener las mesas: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al obtener las mesas", e);
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> createMesa(@RequestBody List<Mesa> createMesas, Authentication authentication) {
        try {
            List<Mesa> savedMesas = mesaService.createMesasUsuario(createMesas, authentication);
            return new ResponseEntity<>(savedMesas, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{id_mesa}")
    public ResponseEntity<?> updateMesa(@PathVariable int id_mesa, @RequestBody Mesa updateMesa, Authentication authentication) {
        try {
            String mesaActualizada = mesaService.updateMesaUsuario(id_mesa, updateMesa, authentication);
            return new ResponseEntity<>(mesaActualizada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id_mesa}")
    public void deleteMesa(@PathVariable int id_mesa, Authentication authentication) {
        mesaService.deleteMesaUsuario(id_mesa, authentication);
    }
}
