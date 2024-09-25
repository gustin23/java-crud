package com.example.crud.Controllers;

import com.example.crud.Entities.Cliente;
import com.example.crud.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getClientesById(@PathVariable Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) { return clienteRepository.save(cliente);}

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
        cliente.setNombre(clienteDetails.getNombre());
        cliente.setFechaIngreso(clienteDetails.getFechaIngreso());
        cliente.setServicioPlus(clienteDetails.isServicioPlus());
        return  clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public String deleteCliente(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));

        clienteRepository.delete(cliente);
        return "EL producto con el ID: " + id + " fue eliminado correctamente.";
    }
}
