package org.example.backend_almenu.service;

import org.example.backend_almenu.model.Cliente;
import org.example.backend_almenu.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> cliente() {
        return clienteRepository.findAll();
    }

    // Traer los clientes por su id.
    public Cliente getIdCliente(Integer id_cliente) {
        return clienteRepository.getId(id_cliente);
    }

    // Guardar  un cliente.
    public String SaveCliente(Cliente cliente) {
        try {
            Cliente ClienteId = clienteRepository.getId(cliente.getId());
            if (ClienteId == null) {
                clienteRepository.save(cliente);
                return "Cliente guardado exitosamente.";
            } else  {
                return "El cliente ya existe.";
            }
        } catch (Exception e) {
            return "Error al guardar al cliente." + e.getMessage();
        }
    }

    //Actualizar un cliente.
    public String UpdateCliente(Cliente cliente) {
        Cliente ClienteId = clienteRepository.getId(cliente.getId());
        if (ClienteId != null) {

            ClienteId.setNombreCompleto(cliente.getNombreCompleto());
            ClienteId.setCelular(cliente.getCelular());
            ClienteId.setEmail(cliente.getEmail());
            ClienteId.setDireccion(cliente.getDireccion());

            clienteRepository.save(cliente);
            return "Cliente actualizado exitosamente.";
        } else {
            return "El cliente no existe.";
        }
    }

}
