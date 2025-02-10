package br.com.postech.techchallenge.order_api.service.internalService;

import br.com.postech.techchallenge.order_api.dto.customer.CreateCustomerDto;
import br.com.postech.techchallenge.order_api.dto.customer.CustomerDto;
import br.com.postech.techchallenge.order_api.exception.EntityNotFoundException;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.ICustomerJpaRepository;
import br.com.postech.techchallenge.order_api.mapper.ICustomerMapper;
import br.com.postech.techchallenge.order_api.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final ICustomerJpaRepository customerRepository;

    private final ICustomerMapper customerMapper;

    public void create(CreateCustomerDto dto) {

        if (customerRepository.existsByCpf(dto.getCpf()))
            throw new IllegalArgumentException("Customer already registered.");

        Customer customer = customerMapper.toCustomer(dto);

        customerRepository.save(customer);
    }

    public CustomerDto find(String cpf) throws EntityNotFoundException {

        Customer customer = customerRepository.findByCpf(cpf)
                .orElseThrow(()-> new EntityNotFoundException("The customer was not found."));

        return customerMapper.toCustomerDto(customer);
    }


}
