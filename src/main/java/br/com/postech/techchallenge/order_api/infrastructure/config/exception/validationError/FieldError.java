package br.com.postech.techchallenge.order_api.infrastructure.config.exception.validationError;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FieldError {

    private String fieldName;
    private String message;
}