package br.com.helosantosdesousa.ms_produtos.controller;

import br.com.helosantosdesousa.ms_produtos.dto.ProdutoRequestDTO;
import br.com.helosantosdesousa.ms_produtos.dto.ProdutoResponseDTO;
import br.com.helosantosdesousa.ms_produtos.entities.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {

    @GetMapping
    public ResponseEntity<ProdutoResponseDTO> createProduto(
            @RequestBody ProdutoRequestDTO dto){
        ProdutoResponseDTO produtoDTO = new ProdutoResponseDTO(10L, dto.nome(), dto.descricao(), dto.preco());

        return ResponseEntity.created(null).body(produtoDTO);

        //Produto produto = new Produto(1L, "Smart TV", "Smart TV LG LED 29 polegadas", 2990.0);
        /*List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1L, "Smart TV", "Smart TV LG LED 29 polegadas", 2990.0));
        produtos.add(new Produto(2L, "Mouse Microsoft", "Mouse sem fio", 250.00));
        produtos.add(new Produto(3L, "Teclado Microsoft", "Teclado sem fio", 278.59));
*/

//        List<ProdutoResponseDTO> dto = ProdutoResponseDTO.createMock();
//        return ResponseEntity.ok(dto);



    }

}
