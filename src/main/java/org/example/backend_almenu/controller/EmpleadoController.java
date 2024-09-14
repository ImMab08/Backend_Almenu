package org.example.backend_almenu.controller;

import org.example.backend_almenu.model.Empleado;
import org.example.backend_almenu.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/v01/colaborador/")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    // Traer los empelados del usuario
    @GetMapping("colaboradores")
    public List<Empleado> getEmpleadosUsuarioByEmail(Authentication authentication) {
        return empleadoService.getEmpleadoUsuario(authentication);
    }

    // Guardar un empleado del usuario
    @PostMapping("create")
    public ResponseEntity<?> createEmpleado(@RequestBody Empleado empleado, Authentication authentication) {
        try {
            String email = authentication.getName();
            Empleado nuevoEmpleado = empleadoService.createEmpleado(email, empleado);;
            return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Editar un empleado del usuario
    @PutMapping("update")
    public Empleado updateEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.updateEmpleado(empleado);
    }

    // Eliminar un empelado del usuario
    @DeleteMapping("delete/{id}")
    public String deleteEmpleado(@PathVariable("id") int id_empleado) {
        return empleadoService.deleteEmpeladoUsuario(id_empleado);
    }

}
