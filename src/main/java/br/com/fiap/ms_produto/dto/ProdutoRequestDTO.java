package br.com.fiap.ms_produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoRequestDTO(
        @NotBlank(message = "Campo requerido!")
        @Size(min = 3, max = 100, message = "O nome deve ter de 3 a 100 caracteres")
        @Schema(description = "Nome do produto")
        String nome,

        @NotBlank(message = "Campo requerido!")
        @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres")
        @Schema(description = "Descrição do produto")
        String descricao,

        @NotNull(message = "Campo requerido")
        @Positive(message = "O preço deve ser um valor positivo maior que zero")
        @Schema(description = "Preço do produto")
        Double valor,

        @NotNull(message = "Campo requerido")
        CategoriaDTO categoria) {

}
