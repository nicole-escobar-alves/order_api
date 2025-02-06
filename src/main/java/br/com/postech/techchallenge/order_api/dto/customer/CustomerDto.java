package br.com.postech.techchallenge.order_api.dto.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Long id;
    private String name;
    private String cpf;
    private String email;
}
