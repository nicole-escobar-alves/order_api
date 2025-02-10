package br.com.postech.techchallenge.order_api.infrastructure.repositories.repositoryTest.repositoryTest.entityMock;

import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.infrastructure.entities.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryMock {

    public static OrderEntity CreateOrderMock(List<ComboEntity> combos,
                                              OrderStatus orderStatus,
                                              CustomerEntity customer)
    {
        OrderEntity entity = new OrderEntity();
        entity.setCombos(combos);
        entity.setTotalPrice(BigDecimal.TEN);
        entity.setOrderStatus(orderStatus);
        entity.setFinishedTime(LocalDateTime.now().plusMinutes(10));
        entity.setCustomer(customer);

        return entity;
    }

    public static List<ComboEntity> CreateCombo1(ProductEntity product, List<AddonEntity> addonList)
    {
        List<ComboEntity> entityList = new ArrayList<ComboEntity>();

        ComboEntity combo1 = new ComboEntity();
        combo1.setProduct(product);
        combo1.setAddons(addonList);

        entityList.add(combo1);

        return entityList;
    }
    public static CustomerEntity CreateCustomer(String name, String cpf) {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(name);
        customer.setCpf(cpf);
        customer.setEmail("Customer1@");

        return customer;
    }
    public static ProductEntity CreateProduct1(String name, ProductCategory category)
    {
        ProductEntity product = new ProductEntity();

        product.setName(name);
        product.setDescription(" ");
        product.setPrice(BigDecimal.ONE);
        product.setDiscountPercent(0.0);
        product.setEstimatedTime(Duration.ofMinutes(10));
        product.setProductCategory(category);

        return product;
    }

    public static List<AddonEntity> CreateAddonList() {

        List<AddonEntity> entityList = new ArrayList<AddonEntity>();

        AddonEntity addon1 = new AddonEntity();
        addon1.setName("Batata");
        addon1.setPrice(BigDecimal.ONE);
        addon1.setDiscountPercent(BigDecimal.ZERO);
        addon1.setProductCategory(ProductCategory.ACOMPANHAMENTO);

        entityList.add(addon1);

        return entityList;
    }
}
