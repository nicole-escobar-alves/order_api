package br.com.postech.techchallenge.order_api.service.internalService;

import br.com.postech.techchallenge.order_api.dto.customer.CreateCustomerDto;
import br.com.postech.techchallenge.order_api.dto.customer.CustomerDto;
import br.com.postech.techchallenge.order_api.infrastructure.entities.CustomerEntity;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.ICustomerJpaRepository;
import br.com.postech.techchallenge.order_api.mapper.ICustomerMapper;
import br.com.postech.techchallenge.order_api.models.Customer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    ICustomerJpaRepository customerRepository;

    @Autowired
    ICustomerMapper customerMapper;

    public void Create(CreateCustomerDto dto) {

        if (customerRepository.existsByCpf(dto.getCpf()))
            throw new IllegalArgumentException("Customer already registered.");

        Customer customer = customerMapper.toCustomer(dto);

        customerRepository.save(customerMapper.toEntity(customer));
    }

    public CustomerDto Find(String cpf) throws EntityNotFoundException {

        CustomerEntity customerEntity = customerRepository.findByCpf(cpf).get();

        Customer customer = customerMapper.toDomain(customerEntity);

        return customerMapper.toCustomerDto(customer);
    }


}
