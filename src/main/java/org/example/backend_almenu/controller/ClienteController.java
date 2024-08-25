package org.example.backend_almenu.controller;


import org.example.backend_almenu.model.Cliente;
import org.example.backend_almenu.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/clients/")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    // Treer todos los clientes.
    @GetMapping("client")
    public List<Cliente> getCliente() {
        return clienteService.cliente();
    }

    // Crear un nuevo cliente.
    @PostMapping("new-client")
    public String nuevoCliente(@RequestBody Cliente cliente) {
        String mensaje = clienteService.SaveCliente(cliente);
        return mensaje;
    }

    // Actualizar un nuevo cliente.
    @PutMapping("update-client")
    public String updateCliente(@RequestBody Cliente cliente) {
        String mensaje = clienteService.SaveCliente(cliente);
        return mensaje;
    }

}
