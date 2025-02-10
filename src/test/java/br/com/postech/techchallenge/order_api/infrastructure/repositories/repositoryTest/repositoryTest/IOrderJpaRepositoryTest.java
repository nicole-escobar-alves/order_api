package br.com.postech.techchallenge.order_api.infrastructure.repositories.repositoryTest.repositoryTest;

import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.infrastructure.entities.*;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.IOrderJpaRepository;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.repositoryTest.repositoryTest.entityMock.OrderRepositoryMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class IOrderJpaRepositoryTest {

    @Autowired
    IOrderJpaRepository orderJpaRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Should find all orders by status")
    void findAllByOrderStatus() {

        //Arrange
        TestScenario1();

        //Act
        List<OrderEntity> orderList = orderJpaRepository.findAllByOrderStatus(OrderStatus.CREATED);

        //Assert
        assertThat(orderList).isNotEmpty();

        orderList.forEach(order -> assertSame(order.getOrderStatus(), OrderStatus.CREATED));

    }

    @Test
    void findAllByCustomerCpf() {

        //Arrange
        TestScenario1();
        String cpfTest = "123.123.123";

        //Act
        List<OrderEntity> orderList = orderJpaRepository.findAllByCustomerCpf(cpfTest);

        //Assert
        assertThat(orderList.size()).isEqualTo(3);

        orderList.forEach(order -> assertSame(order.getCustomer().getCpf(), cpfTest));
    }

    private void TestScenario1() {

        ProductEntity productEntity = OrderRepositoryMock.CreateProduct1("LancheX", ProductCategory.LANCHE);
        em.persist(productEntity);

        List<AddonEntity> addonListEntity = OrderRepositoryMock.CreateAddonList();
        addonListEntity.forEach(addon -> em.persist(addon));

        List<ComboEntity> comboListEntity = OrderRepositoryMock.CreateCombo1(productEntity, addonListEntity);
        comboListEntity.forEach(combo -> em.persist(combo));

        CustomerEntity customerEntity = OrderRepositoryMock.CreateCustomer("customer1", "123.123.123");
        em.persist(customerEntity);

        OrderEntity orderEntity = OrderRepositoryMock.CreateOrderMock(comboListEntity, OrderStatus.CREATED, customerEntity);
        em.persist(orderEntity);
        OrderEntity order2Entity = OrderRepositoryMock.CreateOrderMock(comboListEntity, OrderStatus.RECEIVED, customerEntity);
        em.persist(order2Entity);
        OrderEntity order3Entity = OrderRepositoryMock.CreateOrderMock(comboListEntity, OrderStatus.DONE, customerEntity);
        em.persist(order3Entity);

        CustomerEntity customer2Entity = OrderRepositoryMock.CreateCustomer("customer2", "123.123.325");
        em.persist(customer2Entity);

        OrderEntity order4Entity = OrderRepositoryMock.CreateOrderMock(comboListEntity, OrderStatus.DONE, customer2Entity);
        em.persist(order4Entity);

        CustomerEntity customer3Entity = OrderRepositoryMock.CreateCustomer("customer3", "345.123.345");
        em.persist(customer3Entity);

        OrderEntity order5Entity = OrderRepositoryMock.CreateOrderMock(comboListEntity, OrderStatus.FINISHED, customer3Entity);
        em.persist(order5Entity);

    }
}