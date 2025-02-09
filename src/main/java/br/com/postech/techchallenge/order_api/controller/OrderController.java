package br.com.postech.techchallenge.order_api.controller;

import br.com.postech.techchallenge.order_api.dto.order.CreateOrderDto;
import br.com.postech.techchallenge.order_api.dto.order.DetailsOrderDto;
import br.com.postech.techchallenge.order_api.dto.order.OrderDto;
import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.service.internalService.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> Create(@RequestBody @Valid CreateOrderDto orderDto) throws EntityNotFoundException {

        orderService.Create(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();

    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> GetAllOrdersByCostumer(@RequestParam String cpf) {
        return ResponseEntity.ok(orderService.FindByCPF(cpf));
    }

    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<List<DetailsOrderDto>> GetAllOrdersByStatus(@PathVariable OrderStatus orderStatus) throws EntityNotFoundException {
        var response = orderService.FindByOrderStatus(orderStatus);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allStatus")
    public ResponseEntity<List<DetailsOrderDto>> GetAllOrders() throws EntityNotFoundException {
        var response = orderService.FindAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/{update}")
    public ResponseEntity<OrderDto> Update(@RequestParam("id") Long id, @RequestParam("orderStatus") OrderStatus orderStatus) throws EntityNotFoundException {
        orderService.Update(id, orderStatus);
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsOrderDto> GetOrderById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(orderService.FindById(id));
    }
}
