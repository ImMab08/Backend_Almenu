package org.example.backend_almenu.service;

import org.example.backend_almenu.model.Empleado;
import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.EmpleadoRepository;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    // Traer todos los empleados del usuario.
    public List<Empleado> getEmpleadoUsuario(Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Restaurante restaurante = usuario.getRestaurante();
        if (restaurante == null) {
            throw new RuntimeException("Restaurante no encontrado");
        }

        List<Empleado> empleados = restaurante.getEmpleado();
        return empleados;
    }

    // Crear un nuevo empelado.
    public Empleado createEmpleado(String email, Empleado empleado) {
        try {
            Usuario usuario = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Restaurante restaurante = usuario.getRestaurante();
            if (restaurante == null) {
                throw new Exception("No existe el restaurante con el email");
            }

            empleado.setRestaurante(restaurante);
            return empleadoRepository.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear al empleado");
        }
    }

    // Actualizar la información del empleado.
    public Empleado updateEmpleado(Empleado empleado) {
        Empleado emp = empleadoRepository.findById(empleado.getId()).get();

        emp.setNombres(empleado.getNombres());
        emp.setApellidos(empleado.getApellidos());
        emp.setCelular(empleado.getCelular());
        emp.setEmail(empleado.getEmail());
        emp.setCargo(empleado.getCargo());
        emp.setSalario(empleado.getSalario());

        Empleado saveEmpleado = empleadoRepository.save(emp);

        Empleado updateEmpleado = new Empleado();
        updateEmpleado.setNombres(saveEmpleado.getNombres());
        updateEmpleado.setApellidos(saveEmpleado.getApellidos());
        updateEmpleado.setCelular(saveEmpleado.getCelular());
        updateEmpleado.setEmail(saveEmpleado.getEmail());
        updateEmpleado.setCargo(saveEmpleado.getCargo());
        updateEmpleado.setSalario(saveEmpleado.getSalario());

        return updateEmpleado;

    }

    // Eliminar a un empelado.
    public String deleteEmpeladoUsuario(int id_empleado) {
        Empleado empleado = empleadoRepository.findEmpleadoById(id_empleado);
        if (empleado != null) {
            empleadoRepository.delete(empleado);
            return "Empleado eliminado";
        } else {
            return "No existe el empleado con el id " + id_empleado;
        }
    }

}
