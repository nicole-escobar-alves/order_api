package br.com.postech.techchallenge.orderapi.service.external;

import br.com.postech.techchallenge.orderapi.dto.order.DetailsOrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${payment-service.name}", url = "${payment-service.url}")
public interface IPaymentApiService {

    @PostMapping()
    void create(@RequestBody() DetailsOrderDto order);

}

