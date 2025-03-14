package br.com.fiap.ms_produto.controller;

import br.com.fiap.ms_produto.dto.CategoriaDTO;
import br.com.fiap.ms_produto.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<CategoriaDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id){
        CategoriaDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategoria(
            @RequestBody @Valid CategoriaDTO dto){
        dto = service.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
