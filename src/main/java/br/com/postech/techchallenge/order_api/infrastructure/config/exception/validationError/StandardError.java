package br.com.postech.techchallenge.order_api.infrastructure.config.exception.validationError;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class StandardError {

    private LocalDateTime timestamp;
    private String path;
    private Integer status;
    private String error;
    private String message;
}