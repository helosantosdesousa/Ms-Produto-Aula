package br.com.fiap.ms_produto.controller.handlers.dto;

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
