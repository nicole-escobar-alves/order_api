package br.com.postech.techchallenge.order_api.mapper;

import br.com.postech.techchallenge.order_api.infrastructure.entities.OrderEntity;
import br.com.postech.techchallenge.order_api.models.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IProductMapper.class, IAddonMapper.class, IOrderStatusMapper.class})
public interface IOrderMapper extends IBaseDomainMapper<Order, OrderEntity> {


}
