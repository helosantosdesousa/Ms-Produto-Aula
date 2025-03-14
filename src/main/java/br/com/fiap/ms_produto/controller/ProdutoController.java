package br.com.fiap.ms_produto.controller;

import br.com.fiap.ms_produto.dto.ProdutoRequestDTO;
import br.com.fiap.ms_produto.dto.ProdutoResponseDTO;
import br.com.fiap.ms_produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll() {

        List<ProdutoResponseDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id) {

        ProdutoResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> insert(@Valid @RequestBody ProdutoRequestDTO requestDTO) {

        ProdutoResponseDTO dto = service.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.id())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long id,
                                                     @Valid @RequestBody ProdutoRequestDTO requestDTO) {

        ProdutoResponseDTO dto = service.update(id, requestDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}






