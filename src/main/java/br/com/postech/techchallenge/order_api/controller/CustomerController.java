package br.com.postech.techchallenge.order_api.controller;

import br.com.postech.techchallenge.order_api.dto.customer.CreateCustomerDto;
import br.com.postech.techchallenge.order_api.dto.customer.CustomerDto;
import br.com.postech.techchallenge.order_api.exception.EntityNotFoundException;
import br.com.postech.techchallenge.order_api.service.internalService.CustomerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerDto> getByCpf(@PathVariable String cpf) throws EntityNotFoundException {
        return ResponseEntity.ok(customerService.find(cpf));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CreateCustomerDto dto) throws EntityNotFoundException {
        customerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }


}
