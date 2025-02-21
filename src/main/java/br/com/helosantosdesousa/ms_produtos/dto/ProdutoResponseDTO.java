package br.com.helosantosdesousa.ms_produtos.dto;

import br.com.helosantosdesousa.ms_produtos.entities.Produto;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double valor
) {
    //converter a Entity para um DTO
    public ProdutoResponseDTO(Produto entity) {
        this(entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getValor());
    }
}
