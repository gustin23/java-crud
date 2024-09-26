package com.example.crud.service;

import com.example.crud.Entities.Cliente;
import com.example.crud.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    public Cliente crearCliente(Cliente cliente) {return clienteRepository.save(cliente);}

    public List<Cliente> obtenerClientePorNombre(String nombre){return clienteRepository.findByNombre(nombre);}
}
