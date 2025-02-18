package br.com.postech.techchallenge.orderapi.service.external;

import br.com.postech.techchallenge.orderapi.dto.order.CreateOrderProductionDto;
import br.com.postech.techchallenge.orderapi.dto.order.DetailsOrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name= "${production-service.name}", url = "${production-service.url}")
public interface IProductionApiService {
    @PostMapping("/production/add_order")
    void create(@RequestBody() CreateOrderProductionDto order);

}
