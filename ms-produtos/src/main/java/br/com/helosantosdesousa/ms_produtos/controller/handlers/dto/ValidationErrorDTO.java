package br.com.helosantosdesousa.ms_produtos.controller.handlers.dto;

import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorDTO extends CustomErrorDTO {
    private List<FieldMessageDTO> errors = new ArrayList<>();

    public ValidationErrorDTO(Instant timestamp, Integer status, String error, String path){
        super(timestamp, status, error, path);
    }
    public void addError(String fieldName, String message){
        errors.removeIf(x -> x.getFieldName().equals(fieldName));
        errors.add(new FieldMessageDTO(fieldName, message));
    }

}
