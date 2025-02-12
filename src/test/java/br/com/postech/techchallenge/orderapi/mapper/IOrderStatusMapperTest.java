package br.com.postech.techchallenge.orderapi.mapper;

import br.com.postech.techchallenge.orderapi.enums.OrderStatus;
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