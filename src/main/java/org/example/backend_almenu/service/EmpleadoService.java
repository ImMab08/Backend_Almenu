package org.example.backend_almenu.service;

import org.example.backend_almenu.model.Empleado;
import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.EmpleadoRepository;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Empleado> getEmpleadoUsuario(String email) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        Usuario usuario = usuarioOpt.get();

        Restaurante restaurante = usuario.getRestaurante();

        List<Empleado> empelados = restaurante.getEmpleado().stream()
                .map(empleado -> {
                    Empleado empleados = new Empleado();
                    empleados.setId(empleado.getId());
                    empleados.setNombres(empleado.getNombres());
                    empleados.setApellidos(empleado.getApellidos());
                    empleados.setCelular(empleado.getCelular());
                    empleados.setEmail(empleado.getEmail());
                    empleados.setCargo(empleado.getCargo());
                    empleados.setSalario(empleado.getSalario());
                    return empleados;
                }).collect(Collectors.toList());

        return empelados;
    }

    // Crear un nuevo empelado.
    public Empleado createEmpleado(String email, Empleado empleado) {
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
            if (usuarioOpt.isEmpty()) {
                throw new Exception("No existe el usuario con el email");
            }

            Usuario usuario = usuarioOpt.get();
            Restaurante restaurante = usuario.getRestaurante();
            if (restaurante == null) {
                throw new Exception("No existe el restaurante con el email");
            }

            Empleado empleados = new Empleado();
            empleados.setNombres(empleado.getNombres());
            empleados.setApellidos(empleado.getApellidos());
            empleados.setCelular(empleado.getCelular());
            empleados.setEmail(empleado.getEmail());
            empleados.setCargo(empleado.getCargo());
            empleados.setSalario(empleado.getSalario());

            empleados.setRestaurante(restaurante);

            return empleadoRepository.save(empleados);
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
