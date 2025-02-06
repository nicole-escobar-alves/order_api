package br.com.postech.techchallenge.order_api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer extends BaseDomain {
    private String name;
    private String cpf;
    private String email;
}
