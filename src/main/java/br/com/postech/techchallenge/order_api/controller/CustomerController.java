package br.com.postech.techchallenge.order_api.controller;

import br.com.postech.techchallenge.order_api.dto.customer.CreateCustomerDto;
import br.com.postech.techchallenge.order_api.dto.customer.CustomerDto;
import br.com.postech.techchallenge.order_api.service.internalService.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerDto> GetByCpf(@PathVariable String cpf) throws EntityNotFoundException {
        return ResponseEntity.ok(customerService.Find(cpf));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> Create(@RequestBody CreateCustomerDto dto) throws EntityNotFoundException {
        customerService.Create(dto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }


}
