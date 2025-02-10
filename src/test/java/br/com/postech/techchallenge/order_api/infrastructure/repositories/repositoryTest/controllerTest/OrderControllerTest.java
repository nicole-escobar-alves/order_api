package br.com.postech.techchallenge.order_api.infrastructure.repositories.repositoryTest.controllerTest;

import br.com.postech.techchallenge.order_api.dto.combo.CreateComboDto;
import br.com.postech.techchallenge.order_api.dto.order.CreateOrderDto;
import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.infrastructure.entities.*;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.IAddonJpaRepository;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.IOrderJpaRepository;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.IProductJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<CreateOrderDto> createOrderJson;
    @MockBean
    private IOrderJpaRepository orderRepository;
    @MockBean
    private IProductJpaRepository productRepository;
    @MockBean
    private IAddonJpaRepository addonRepository;

    @Test
    @DisplayName("Should return 400 http when null field")
    @WithMockUser
    void CreateOrderWithError400() throws Exception {

        var response = mvc.perform(post("/order"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return 200 http when validated field")
    @WithMockUser
    void CreateOrderWithValidFields() throws Exception {

        //Arrange
        CreateOrderDto createOrderDto = CreateOrderDto();
        ProductEntity productEntity = CreateProductEntity();
        CustomerEntity customerEntity = new CustomerEntity();

        List<AddonEntity> addonEntityList = new ArrayList<>();
        AddonEntity addonEntity = CreateAddonEntity();
        addonEntityList.add(addonEntity);

        List<ComboEntity> comboEntityList = new ArrayList<>();

        ComboEntity comboEntity = CreateComboEntity(productEntity, addonEntityList);
        comboEntityList.add(comboEntity);

        OrderEntity orderEntity = CreateOrderEntity(comboEntityList, customerEntity);

        when(addonRepository.findById(1L)).thenReturn(Optional.of(addonEntity));

        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));

        when(orderRepository.save(orderEntity)).thenReturn(null);

        //Act
        var response = mvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createOrderJson.write(createOrderDto).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    private OrderEntity CreateOrderEntity(List<ComboEntity> comboEntityList, CustomerEntity customerEntity) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCombos(comboEntityList);
        orderEntity.setOrderStatus(OrderStatus.CREATED);
        orderEntity.setCustomer(customerEntity);

        return orderEntity;
    }

    private ComboEntity CreateComboEntity(ProductEntity productEntity, List<AddonEntity> addonEntityList) {
        ComboEntity comboEntity = new ComboEntity();
        comboEntity.setProduct(productEntity);
        comboEntity.setAddons(addonEntityList);

        return comboEntity;
    }

    private AddonEntity CreateAddonEntity() {
        AddonEntity addonEntity = new AddonEntity();
        addonEntity.setProductCategory(ProductCategory.LANCHE);
        addonEntity.setPrice(BigDecimal.valueOf(0L));
        addonEntity.setDiscountPercent(BigDecimal.valueOf(0L));

        return addonEntity;
    }

    private ProductEntity CreateProductEntity() {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setDiscountPercent(0.0);
        productEntity.setPrice(BigDecimal.valueOf(0L));
        productEntity.setProductCategory(ProductCategory.LANCHE);

        return productEntity;
    }

    private CreateOrderDto CreateOrderDto() {

        CreateOrderDto orderDto = new CreateOrderDto();

        List<CreateComboDto> comboDtoList = new ArrayList<>();
        CreateComboDto comboDto = new CreateComboDto();
        comboDto.setProductId(1L);
        comboDto.setAddonsId(Collections.singletonList(1L));

        comboDtoList.add(comboDto);

        orderDto.setCombos(comboDtoList);
        orderDto.setCustomerId(1L);

        return orderDto;
    }


}
