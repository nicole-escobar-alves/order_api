package br.com.postech.techchallenge.order_api.exception;

public class EntityNotFoundException extends BaseException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
