<<<<<<<< HEAD:src/test/java/br/com/postech/techchallenge/order_api/unitTests/controller/CustomerControllerTest.java
package br.com.postech.techchallenge.order_api.unitTests.controller;

import br.com.postech.techchallenge.order_api.controller.CustomerController;
import br.com.postech.techchallenge.order_api.dto.customer.CreateCustomerDto;
import br.com.postech.techchallenge.order_api.dto.customer.CustomerDto;
import br.com.postech.techchallenge.order_api.exception.EntityNotFoundException;
import br.com.postech.techchallenge.order_api.service.internalService.CustomerService;
========
package br.com.postech.techchallenge.orderapi.controller;

import br.com.postech.techchallenge.orderapi.dto.customer.CreateCustomerDto;
import br.com.postech.techchallenge.orderapi.dto.customer.CustomerDto;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.service.internal.CustomerService;
>>>>>>>> 2b22bf7f4b2f320fe86d03630d6207be83bfa663:src/test/java/br/com/postech/techchallenge/orderapi/controller/CustomerControllerTest.java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController controller;
    @Mock
    private CustomerService service;

    @Test
    void getByCpf() throws EntityNotFoundException {
        CustomerDto dto = new CustomerDto();
        when(service.find("cpf")).thenReturn(dto);

        var result = controller.getByCpf("cpf");

        assertAll(
                ()-> assertEquals(HttpStatus.OK, result.getStatusCode()),
                ()-> assertEquals(dto, result.getBody())
        );
    }

    @Test
    void create() {
        CreateCustomerDto dto = new CreateCustomerDto("Customer", "cpf", "email");
        var result = controller.create(dto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }
}