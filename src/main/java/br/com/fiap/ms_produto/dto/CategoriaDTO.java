package br.com.fiap.ms_produto.dto;

import br.com.fiap.ms_produto.entities.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "O nome não pode ser vazio, nulo ou em branco")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    public CategoriaDTO(Categoria entity) {
        id = entity.getId();
       nome = entity.getNome();
    }
}
