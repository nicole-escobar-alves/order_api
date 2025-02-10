package br.com.postech.techchallenge.order_api.mapper;

import br.com.postech.techchallenge.order_api.dto.customer.CreateCustomerDto;
import br.com.postech.techchallenge.order_api.dto.customer.CustomerDto;
import br.com.postech.techchallenge.order_api.models.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    Customer toCustomer(CreateCustomerDto dto);

    CustomerDto toCustomerDto(Customer customerSaved);
}
