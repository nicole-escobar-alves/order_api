package br.com.postech.techchallenge.order_api.infrastructure.repositories.repositoryTest.repositoryTest.entityMock;

import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.models.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryMock {

    public static Order CreateOrderMock(List<Combo> combos,
                                        OrderStatus orderStatus,
                                        Customer customer)
    {
        Order entity = new Order();
        entity.setCombos(combos);
        entity.setTotalPrice(BigDecimal.TEN);
        entity.setOrderStatus(orderStatus);
        entity.setFinishedTime(LocalDateTime.now().plusMinutes(10));
        entity.setCustomer(customer);

        return entity;
    }

    public static List<Combo> CreateCombo1(Product product, List<Addon> addonList)
    {
        List<Combo> entityList = new ArrayList<Combo>();

        Combo combo1 = new Combo();
        combo1.setProduct(product);
        combo1.setAddons(addonList);

        entityList.add(combo1);

        return entityList;
    }
    public static Customer CreateCustomer(String name, String cpf) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setCpf(cpf);
        customer.setEmail("Customer1@");

        return customer;
    }
    public static Product CreateProduct1(String name, ProductCategory category)
    {
        Product product = new Product();

        product.setName(name);
        product.setDescription(" ");
        product.setPrice(BigDecimal.ONE);
        product.setDiscountPercent(0.0);
        product.setEstimatedTime(Duration.ofMinutes(10));
        product.setProductCategory(category);

        return product;
    }

    public static List<Addon> CreateAddonList() {

        List<Addon> entityList = new ArrayList<Addon>();

        Addon addon1 = new Addon();
        addon1.setName("Batata");
        addon1.setPrice(BigDecimal.ONE);
        addon1.setDiscountPercent(0.0);
        addon1.setProductCategory(ProductCategory.ACOMPANHAMENTO);

        entityList.add(addon1);

        return entityList;
    }
}
