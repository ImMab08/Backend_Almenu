package org.example.backend_almenu.controller;

import org.apache.coyote.Response;
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
    public ResponseEntity<?> xcreateEmpleado(@RequestBody Empleado createEmpleado, Authentication authentication) {
        try {
            Empleado newColaborador = empleadoService.createEmpleado(createEmpleado, authentication);;
            return new ResponseEntity<>(newColaborador, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Editar un empleado del usuario
    @PutMapping("update/{id_empleado}")
    public ResponseEntity<?> updateEmpleado(@PathVariable("id_empleado") int id_empleado, @RequestBody Empleado updateEmpleado, Authentication authentication) {
        try {
            String empleadoActualizado = empleadoService.updateEmpleado(id_empleado, updateEmpleado, authentication);
            return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar un empelado del usuario
    @DeleteMapping("delete/{id_empleado}")
    public String deleteEmpleado(@PathVariable("id_empleado") int id_empleado, Authentication authentication) {
        return empleadoService.deleteEmpeladoUsuario(id_empleado, authentication);
    }

}
