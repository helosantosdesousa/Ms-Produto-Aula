package br.com.helosantosdesousa.ms_produtos.controller.handlers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FieldMessageDTO {
    private String fieldName;
    private String message;
}
