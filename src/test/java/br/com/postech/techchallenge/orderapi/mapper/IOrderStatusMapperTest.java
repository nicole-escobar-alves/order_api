<<<<<<<< HEAD:src/test/java/br/com/postech/techchallenge/order_api/unitTests/mapper/IOrderStatusMapperTest.java
package br.com.postech.techchallenge.order_api.unitTests.mapper;

import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.mapper.IOrderStatusMapper;
========
package br.com.postech.techchallenge.orderapi.mapper;

import br.com.postech.techchallenge.orderapi.enums.OrderStatus;
>>>>>>>> 2b22bf7f4b2f320fe86d03630d6207be83bfa663:src/test/java/br/com/postech/techchallenge/orderapi/mapper/IOrderStatusMapperTest.java
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class IOrderStatusMapperTest {

    private IOrderStatusMapper orderStatusMapper = Mappers.getMapper(IOrderStatusMapper.class);

    @Test
    void mapToString() {
        String response = orderStatusMapper.map(OrderStatus.CREATED);
        assertEquals("CREATED", response);
    }

    @Test
    void mapToOrderStatus() {
        OrderStatus response = orderStatusMapper.map("CREATED");
        assertEquals(OrderStatus.CREATED, response);
    }
}