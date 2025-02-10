package br.com.postech.techchallenge.order_api.service.internalService;

import br.com.postech.techchallenge.order_api.dto.combo.CreateComboDto;
import br.com.postech.techchallenge.order_api.dto.order.CreateOrderDto;
import br.com.postech.techchallenge.order_api.dto.order.DetailsOrderDto;
import br.com.postech.techchallenge.order_api.dto.order.OrderDto;
import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.infrastructure.entities.CustomerEntity;
import br.com.postech.techchallenge.order_api.infrastructure.entities.OrderEntity;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.IOrderJpaRepository;
import br.com.postech.techchallenge.order_api.mapper.IOrderMapper;
import br.com.postech.techchallenge.order_api.models.Combo;
import br.com.postech.techchallenge.order_api.models.Order;
import br.com.postech.techchallenge.order_api.service.externalApiService.IPaymentApiService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    IOrderJpaRepository orderRepository;

    @Autowired
    IOrderMapper orderMapper;

    @Autowired
    ComboService comboService;
    @Autowired
    CustomerService customerService;
    @Autowired
    IPaymentApiService paymentService;

    public void Create(CreateOrderDto createOrderDto) throws EntityNotFoundException {

        Order order = new Order();

        for (CreateComboDto comboDto : createOrderDto.getCombos()) {

            Combo combo = comboService.Create(comboDto);

            order.AddCombo(combo);
        }

        AddCustomerInOrder(order, createOrderDto);

        OrderEntity orderSaved = orderRepository.save(orderMapper.toEntity(order));

        //paymentService.Create(orderMapper.toDomain(orderSaved));
    }

    private void AddCustomerInOrder(Order order, CreateOrderDto createOrderDto) throws EntityNotFoundException {

        CustomerEntity customer;
        try {
            customer = customerService.customerRepository.findById(createOrderDto.getCustomerId()).get();
        } catch (EntityNotFoundException e) {
            customer = customerService.customerRepository.save(new CustomerEntity());
        }

        order.setCustomer(customerService.customerMapper.toDomain(customer));
    }

    public List<OrderDto> FindByCPF(String cpf) {

        List<OrderEntity> ordersListEntity = orderRepository.findAllByCustomerCpf(cpf);

        return orderMapper.toOrdersDto(orderMapper.toDomains(ordersListEntity));
    }

    public DetailsOrderDto FindById(Long id) {

        OrderEntity ordersEntity = orderRepository.findById(id).get();

        return orderMapper.toDetailsOrderDto(orderMapper.toDomain(ordersEntity));
    }

    public List<DetailsOrderDto> FindByOrderStatus(OrderStatus orderStatus) throws EntityNotFoundException {

        List<OrderEntity> OrderListEntity = orderRepository.findAllByOrderStatus(orderStatus);

        return orderMapper.toDetailsOrdersDto(orderMapper.toDomains(OrderListEntity));
    }

    public List<DetailsOrderDto> FindAll() throws EntityNotFoundException {

        var response = orderRepository.findAll();

        return orderMapper.toDetailsOrdersDto(orderMapper.toDomains(response));
    }

    public void Update(Long id, OrderStatus orderStatus) throws EntityNotFoundException {

        OrderEntity orderEntity = orderRepository.findById(id).get();

        orderMapper.toDomain(orderEntity).UpdateOrderStatus(orderStatus);

        orderRepository.save(orderEntity);
    }
}
