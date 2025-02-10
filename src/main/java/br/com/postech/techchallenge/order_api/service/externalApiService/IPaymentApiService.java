package br.com.postech.techchallenge.order_api.service.externalApiService;

import br.com.postech.techchallenge.order_api.models.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Payment-Api", url = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1")
public interface IPaymentApiService {

    @PostMapping()
    void create(@RequestBody() Order order);

}

