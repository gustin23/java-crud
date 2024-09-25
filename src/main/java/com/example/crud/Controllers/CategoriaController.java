package com.example.crud.Controllers;

import com.example.crud.Entities.Categoria;
import com.example.crud.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> getAllCategorias(){ return categoriaRepository.findAll(); }

    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
    }

    @PostMapping
    public Categoria creataCategoria(@RequestBody Categoria categoria) { return categoriaRepository.save(categoria); }

    @PutMapping("/{id}")
    public Categoria updateCategoria(@PathVariable Long id, @RequestBody Categoria categoriaDetails) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro la categoria con el ID " + id));

        categoria.setNombre(categoriaDetails.getNombre());
        categoria.setDescripcion(categoriaDetails.getDescripcion());

        return categoriaRepository.save(categoria);
    }

    @DeleteMapping("/{id}")
    public String deleteCategoria(@PathVariable Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));

        categoriaRepository.delete(categoria);
        return "EL producto con el ID: " + id + " fue eliminado correctamente.";
    }
}