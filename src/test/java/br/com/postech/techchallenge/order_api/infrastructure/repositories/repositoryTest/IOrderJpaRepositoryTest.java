package br.com.postech.techchallenge.order_api.infrastructure.repositories.repositoryTest;

import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.infrastructure.entities.*;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.IOrderJpaRepository;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.repositoryTest.entityMock.OrderRepositoryMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        ProductEntity productEntity = OrderRepositoryMock.CreateProduct1("LancheX", ProductCategory.LANCHE);
        em.persist(productEntity);

        List<AddonEntity> addonListEntity = OrderRepositoryMock.CreateAddonList();
        addonListEntity.forEach(addon -> em.persist(addon));

        List<ComboEntity> comboListEntity = OrderRepositoryMock.CreateCombo1(productEntity, addonListEntity);
        comboListEntity.forEach(combo -> em.persist(combo));

        CustomerEntity customerEntity = OrderRepositoryMock.CreateCustomer();
        em.persist(customerEntity);

        OrderEntity orderEntity = OrderRepositoryMock.CreateOrderMock(comboListEntity, OrderStatus.CREATED, customerEntity);
        em.persist(orderEntity);

        //Act
        List<OrderEntity> orderList = orderJpaRepository.findAllByOrderStatus(OrderStatus.CREATED);

        //Assert
        assertThat(orderList).isNotEmpty();
    }

    @Test
    void findAllByCustomerCpf() {
    }
}