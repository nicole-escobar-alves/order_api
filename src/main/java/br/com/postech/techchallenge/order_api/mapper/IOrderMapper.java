package br.com.postech.techchallenge.order_api.mapper;

import br.com.postech.techchallenge.order_api.dto.order.DetailsOrderDto;
import br.com.postech.techchallenge.order_api.dto.order.OrderDto;
import br.com.postech.techchallenge.order_api.infrastructure.entities.OrderEntity;
import br.com.postech.techchallenge.order_api.models.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IProductMapper.class, IAddonMapper.class, IOrderStatusMapper.class})
public interface IOrderMapper extends IBaseDomainMapper<Order, OrderEntity> {


}
