package org.example.backend_almenu.service;

import org.example.backend_almenu.model.Empleado;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.EmpleadoRepository;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        List<Empleado> empleados = usuario.getEmpleado();
        return empleados;
    }

    // Crear un nuevo empleado para el usuario.
    public Empleado createEmpleado(Empleado createEmpelado, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            createEmpelado.setUsuario(usuario);
            return empleadoRepository.save(createEmpelado);
        } else  {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    // Actualizar la información del empleado.
    public String updateEmpleado(int id_empleado, Empleado updateEmpleado, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Buscar el empleado en la lista de empleados del usuario.
            Empleado empleado = usuario.getEmpleado()
                    .stream() // Iterar entre los empleados.
                    .filter(idEmpleado -> idEmpleado.getId() == id_empleado) // Buscar el empleado con el ID enviado.
                    .findFirst() // Recuperar la primera coincidencia.
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

            // Actualizar los campos del empleado.
            empleado.setNombre(updateEmpleado.getNombre());
            empleado.setApellido(updateEmpleado.getApellido());
            empleado.setCelular(updateEmpleado.getCelular());
            empleado.setEmail(updateEmpleado.getEmail());
            empleado.setSalario(updateEmpleado.getSalario());
            empleado.setCargo(updateEmpleado.getCargo());

            // Guardar el empleado actualizado en la bd.
            empleadoRepository.save(empleado);
            return "Empleado actualizado exitosamente.";
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    // Eliminar a un empelado.
    public String deleteEmpeladoUsuario(int id_empleado, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Buscar el empleado en la lista de empleados del usuario.
            Empleado empleado = usuario.getEmpleado()
                    .stream() // Iterar entre los empleados.
                    .filter(idEmpleado -> idEmpleado.getId() == id_empleado) // Buscar el empleado con el ID enviado.
                    .findFirst() // Recuperar la primera coincidencia.
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

            // Eliminar el empleado de la lista del usuario.
            usuario.getEmpleado().remove(empleado);

            // Eliminar el empleado de la db.
            empleadoRepository.delete(empleado);
            return "Empleado eliminado exitosamente.";
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

}
