package com.example.crud.Controllers;

import com.example.crud.Entities.Cliente;
import com.example.crud.Repositories.ClienteDao;
import com.example.crud.Repositories.ClienteRepository;
import com.example.crud.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    private ClienteRepository repository;

    private final ClienteDao dao;

    @Autowired
    ClienteService clienteService;

    public ClienteController(ClienteRepository repository, ClienteDao dao){
        this.repository = repository;
        this.dao = dao;
    }

    @PostMapping("cliente")
    public Cliente crearCliente(@RequestBody Cliente cliente){
        Cliente clienteCreado = clienteService.crearCliente(cliente);
        return clienteCreado;
    }

    @GetMapping("clienteNombre/{nombre}")
    public List<Cliente> obtenerClientePorNombre(@PathVariable("nombre") String nombre){
        return clienteService.obtenerClientePorNombre(nombre);
    }

    /*@GetMapping("clientesNombre/{nombre}/{page}/{pageSize}")
    public Page<Cliente> obtenerClientePorNombre(@PathVariable("nombre") String nombre,
                                                 @PathVariable("page")int page,
                                                 @PathVariable("pageSize")int pageSize){
        return dao.findClienteByName(nombre,page,pageSize);
    }*/

    @GetMapping
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getClientesById(@PathVariable Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el cliente con el ID: " + id));
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) { return clienteRepository.save(cliente);}

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el cliente con el ID: " + id));
        cliente.setNombre(clienteDetails.getNombre());
        cliente.setFechaIngreso(clienteDetails.getFechaIngreso());
        cliente.setServicioPlus(clienteDetails.isServicioPlus());
        return  clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public String deleteCliente(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el cliente con el ID: " + id));

        clienteRepository.delete(cliente);
        return "EL cliente con el ID: " + id + " fue eliminado correctamente.";
    }
}
