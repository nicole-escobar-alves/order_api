package br.com.postech.techchallenge.order_api.mapper;

import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IOrderStatusMapper {

    IOrderStatusMapper INSTANCE = Mappers.getMapper(IOrderStatusMapper.class);

    default String map(OrderStatus orderStatus) {
        return orderStatus == null ? null : orderStatus.name();
    }

    default OrderStatus map(String orderStatusName) {
        return orderStatusName == null ? null : OrderStatus.valueOf(orderStatusName);
    }

}
