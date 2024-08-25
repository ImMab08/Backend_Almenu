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

    // Traer los clientes por su # de telefono.
    public Cliente getClientePhone(String telefono) {
        return clienteRepository.getTelefono(telefono);
    }

    // Guardar  un cliente.
    public String SaveCliente(Cliente cliente) {
        try {
            Cliente ClienteTelefono = clienteRepository.getTelefono(cliente.getTelefono());
            if (ClienteTelefono == null) {
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
        Cliente ClienteTelefono = clienteRepository.getTelefono(cliente.getTelefono());
        if (ClienteTelefono != null) {

            ClienteTelefono.setNombre(cliente.getNombre());
            ClienteTelefono.setApellido(cliente.getApellido());
            ClienteTelefono.setTelefono(cliente.getTelefono());

            clienteRepository.save(cliente);
            return "Cliente actualizado exitosamente.";
        } else {
            return "El cliente no existe.";
        }
    }

}
