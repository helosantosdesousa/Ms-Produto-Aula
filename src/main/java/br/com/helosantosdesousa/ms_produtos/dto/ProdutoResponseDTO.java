package br.com.helosantosdesousa.ms_produtos.dto;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double valor
) {

}
