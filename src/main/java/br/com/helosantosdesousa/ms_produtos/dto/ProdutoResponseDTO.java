package br.com.helosantosdesousa.ms_produtos.dto;

import javax.swing.plaf.PanelUI;
import java.util.List;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double valor
) {
   /* public static ProdutoResponseDTO createMock(){
        return new ProdutoResponseDTO(1L, "Smart TV", "Smart TV LG LED 29 polegadas", 2990.0);

    }*/
    public static List<ProdutoResponseDTO> createMock(){
        return List.of(
                new ProdutoResponseDTO(1L, "Smart TV", "Smart TV LED 29 polegadas", 2990.0),
                new ProdutoResponseDTO(2L, "Mouse Microsoft", "Mouse sem fio", 250.0),
                new ProdutoResponseDTO(3L, "Teclado Microsoft", "Teclado sem fio", 278.59)
        );
    }
}
