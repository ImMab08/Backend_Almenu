package org.example.backend_almenu.service;

import org.example.backend_almenu.model.Mesa;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.MesaRepository;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    MesaRepository mesaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    // Traer todas las mesas del usuario.
    public List<Mesa> getMesasUsuario(Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        List<Mesa> mesas = usuario.getMesa();
        return mesas;
    }

    // Crear las mesas del usuario.
    public List<Mesa> createMesasUsuario(List<Mesa> createMesas, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Validar y asignar el usuario a cada mesa
            for (Mesa mesa : createMesas) {
                String capacidad = String.valueOf(mesa.getCapacidad());
                if (!List.of("2", "4", "6", "8").contains(capacidad)) {
                    throw new RuntimeException("Capacidad inválida. Debe ser '2', '4', '6', '8'");
                }
                mesa.setUsuario(usuario);
            }

            // Guardar todas las mesas
            return mesaRepository.saveAll(createMesas);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    // Editar las mesas del usuario.
    public String updateMesaUsuario(int id_mesa, Mesa updateMesa, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        // Buscar la mesa en la lista de mesas del usuario.
        if(usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Mesa mesa = usuario.getMesa()
                    .stream()
                    .filter(idMesa -> idMesa.getId() == id_mesa)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

            // Actualizar los campos de la mesa.
            mesa.setNumeroMesa(updateMesa.getNumeroMesa());

            // Guardar la mesa actualizada en la bd
            mesaRepository.save(mesa);
            return "Mesa actualizada exitosamente.";
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    //  Eliminar las mesas del usuario
    public String deleteMesaUsuario(int id_mesa, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Buscar la mesa en la lista del usuario.
            Mesa mesa = usuario.getMesa()
                    .stream() // Iteramos entre las mesas.
                    .filter(idMesa -> idMesa.getId() == id_mesa) // Buscamos la mesa con el ID enviado.
                    .findFirst() // Recuperamos la primera coincidencia.
                    .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

            // Eliminar la mesa de la lista del usuario.
            usuario.getMesa().remove(mesa);

            // Eliminar la mesa de la db.
            mesaRepository.delete(mesa);
            return "Mesa eliminada exitosamente";
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

}
