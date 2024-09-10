package org.example.backend_almenu.controller;

import org.example.backend_almenu.model.Empleado;
import org.example.backend_almenu.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v01/empleado/")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    // Traer los empelados del usuario
    @GetMapping("empleados/{email}")
    public List<Empleado> getEmpleadosUsuarioByEmail(@PathVariable String email) {
        return empleadoService.getEmpleadoUsuario(email);
    }

    // Guardar un empleado del usuario
    @PostMapping("create/{email}")
    public ResponseEntity<?> createEmpleado(@PathVariable String email,@RequestBody Empleado empleado) {
        try {
            empleadoService.createEmpleado(email, empleado);
            String menssage = "Empleado registrado exitosamente";

            return new ResponseEntity<>(menssage, HttpStatus.CREATED);
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
