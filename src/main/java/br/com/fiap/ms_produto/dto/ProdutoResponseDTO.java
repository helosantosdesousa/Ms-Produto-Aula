package br.com.fiap.ms_produto.dto;

import br.com.fiap.ms_produto.entities.Produto;
import io.swagger.v3.oas.annotations.media.Schema;

public record ProdutoResponseDTO(
        @Schema(description = "ID gerado pelo banco de dados")
        Long id,
        @Schema(description = "Nome do produto")
        String nome,
        @Schema(description = "Descrição do produto")
        String descricao,
        @Schema(description = "Valor do produto")
        Double valor,
        CategoriaDTO categoria
) {

    public ProdutoResponseDTO(Produto entity) {
        this(entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getValor(),
                new CategoriaDTO((entity.getCategoria())));
    }
}
