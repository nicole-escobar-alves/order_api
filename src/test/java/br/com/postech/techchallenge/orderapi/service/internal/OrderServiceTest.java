<<<<<<<< HEAD:src/test/java/br/com/postech/techchallenge/order_api/unitTests/internalService/OrderServiceTest.java
package br.com.postech.techchallenge.order_api.unitTests.internalService;

import br.com.postech.techchallenge.order_api.dto.combo.CreateComboDto;
import br.com.postech.techchallenge.order_api.dto.order.CreateOrderDto;
import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.exception.EntityNotFoundException;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.ICustomerJpaRepository;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.IOrderJpaRepository;
import br.com.postech.techchallenge.order_api.mapper.IOrderMapper;
import br.com.postech.techchallenge.order_api.mapper.IOrderStatusMapper;
import br.com.postech.techchallenge.order_api.models.Addon;
import br.com.postech.techchallenge.order_api.models.Combo;
import br.com.postech.techchallenge.order_api.models.Order;
import br.com.postech.techchallenge.order_api.models.Product;
import br.com.postech.techchallenge.order_api.service.internalService.ComboService;
import br.com.postech.techchallenge.order_api.service.internalService.OrderService;
========
package br.com.postech.techchallenge.orderapi.service.internal;

import br.com.postech.techchallenge.orderapi.dto.combo.CreateComboDto;
import br.com.postech.techchallenge.orderapi.dto.order.CreateOrderDto;
import br.com.postech.techchallenge.orderapi.dto.order.DetailsOrderDto;
import br.com.postech.techchallenge.orderapi.enums.OrderStatus;
import br.com.postech.techchallenge.orderapi.enums.ProductCategory;
import br.com.postech.techchallenge.orderapi.exception.EntityNotFoundException;
import br.com.postech.techchallenge.orderapi.infrastructure.repositories.ICustomerJpaRepository;
import br.com.postech.techchallenge.orderapi.infrastructure.repositories.IOrderJpaRepository;
import br.com.postech.techchallenge.orderapi.mapper.IOrderMapper;
import br.com.postech.techchallenge.orderapi.mapper.IOrderStatusMapper;
import br.com.postech.techchallenge.orderapi.models.Addon;
import br.com.postech.techchallenge.orderapi.models.Combo;
import br.com.postech.techchallenge.orderapi.models.Order;
import br.com.postech.techchallenge.orderapi.models.Product;
import br.com.postech.techchallenge.orderapi.service.external.IPaymentApiService;
import br.com.postech.techchallenge.orderapi.service.external.IProductionApiService;
>>>>>>>> 2b22bf7f4b2f320fe86d03630d6207be83bfa663:src/test/java/br/com/postech/techchallenge/orderapi/service/internal/OrderServiceTest.java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;
    @Mock
    private IOrderJpaRepository orderRepository;
    @Mock
    private ComboService comboService;
    @Mock
    private ICustomerJpaRepository customerRepository;
    @Mock
    private IPaymentApiService paymentApiService;
    @Mock
    private IProductionApiService productionApiService;
    @Spy
    private IOrderMapper orderMapper = Mappers.getMapper(IOrderMapper.class);
    @Spy
    private IOrderStatusMapper orderStatusMapper = Mappers.getMapper(IOrderStatusMapper.class);

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(orderMapper, "iOrderStatusMapper", orderStatusMapper);
    }

    @Test
    void create() throws EntityNotFoundException {
        CreateComboDto comboDto = new CreateComboDto(1L, List.of(1L));
        Combo combo = createCombo();
        Order order = new Order(List.of(combo), BigDecimal.TEN, OrderStatus.CREATED, null, null );
        CreateOrderDto orderDto = new CreateOrderDto( List.of(comboDto), 1L);
        when(comboService.create(any(CreateComboDto.class))).thenReturn(combo);
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        var dto = orderService.create(orderDto);

        verify(orderRepository, times(1)).save(any(Order.class));
        assertEquals(OrderStatus.CREATED.toString(), dto.getOrderStatus());
    }

    @Test
    void findAllByCPF() {
        Order order = new Order();
        order.setId(1L);
        List<Order> orders = List.of(order);
        when(orderRepository.findAllByCustomerCpf(anyString())).thenReturn(orders);
        var result = orderService.findAllByCPF("cpf");
        assertAll(
                ()-> assertEquals(1, result.size()),
                ()->assertEquals(1L, result.get(0).getId())
        );
    }

    @Test
    void findById() throws EntityNotFoundException {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        var result = orderService.findById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void findByIdWhenIdNotExists() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> orderService.findById(1L));
    }

    @Test
    void findByOrderStatus() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findAllByOrderStatus(OrderStatus.RECEIVED)).thenReturn(List.of(order));

        var result = orderService.findByOrderStatus(OrderStatus.RECEIVED);

        assertAll(
                ()-> assertEquals(1, result.size()),
                ()-> assertEquals(1L, result.get(0).getId())
        );
    }

    @Test
    void findAll() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findAll()).thenReturn(List.of(order));

        var result = orderService.findAll();

        assertAll(
                ()-> assertEquals(1, result.size()),
                ()-> assertEquals(1L, result.get(0).getId())
        );
    }

    @Test
    void updateStatus() throws EntityNotFoundException {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        orderService.updateStatus(1L, OrderStatus.RECEIVED);

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void makePayment() throws EntityNotFoundException {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        orderService.makePayment(1L);

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(productionApiService, times(1)).create(any(DetailsOrderDto.class));
    }


    Combo createCombo(){
        Combo combo = new Combo();
        Product product = new Product();
        product.setProductCategory(ProductCategory.LANCHE);
        Addon addon = new Addon();
        addon.setProductCategory(ProductCategory.LANCHE);
        combo.setAddons(List.of(addon));
        combo.setProduct(product);
        return combo;
    }
}