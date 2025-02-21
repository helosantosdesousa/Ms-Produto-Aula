package br.com.helosantosdesousa.ms_produtos.controller;

import br.com.helosantosdesousa.ms_produtos.dto.ProdutoResponseDTO;
import br.com.helosantosdesousa.ms_produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll(){
        List<ProdutoResponseDTO> dto = service.findAll();

        return ResponseEntity.ok(dto);
    }

}
