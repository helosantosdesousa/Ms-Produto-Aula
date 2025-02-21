package br.com.helosantosdesousa.ms_produtos.dto;

public record ProdutoRequestDTO(
        String nome,
        String descricao,
        Double preco
) {
}
