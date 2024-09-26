package com.example.crud.Repositories;

import com.example.crud.Entities.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteDao {
    private final EntityManager em;
    private final ClienteRepository repository;

    public ClienteDao(EntityManager em, ClienteRepository repository){
        this.em = em;
        this.repository = repository;
    }

    /*public Page<Cliente> findClienteByNombre(String nombre){
        return repository.findAll(findClienteByName(nombre), PageRequest.of(page,pageSize));
    }*/

    public Specification<Cliente> findClienteByName(String nombre){
        return ((cliente, cq, cb)->{
          Predicate nombrePredicate = cb.equal(cliente.get("nombre"), nombre);
          return cb.and(nombrePredicate);
        }
        );
    }
}
