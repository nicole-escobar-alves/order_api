package br.com.postech.techchallenge.order_api.controller;

import br.com.postech.techchallenge.order_api.dto.order.CreateOrderDto;
import br.com.postech.techchallenge.order_api.dto.order.DetailsOrderDto;
import br.com.postech.techchallenge.order_api.dto.order.OrderDto;
import br.com.postech.techchallenge.order_api.enums.OrderStatus;
import br.com.postech.techchallenge.order_api.exception.EntityNotFoundException;
import br.com.postech.techchallenge.order_api.service.internalService.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody @Valid CreateOrderDto orderDto) throws EntityNotFoundException {
        orderService.create(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();

    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrdersByCostumer(@RequestParam String cpf) {
        return ResponseEntity.ok(orderService.findAllByCPF(cpf));
    }

    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<List<DetailsOrderDto>> getAllOrdersByStatus(@PathVariable OrderStatus orderStatus) throws EntityNotFoundException {
        var response = orderService.findByOrderStatus(orderStatus);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allStatus")
    public ResponseEntity<List<DetailsOrderDto>> getAllOrders() throws EntityNotFoundException {
        var response = orderService.findAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<OrderDto> updateStatus(@PathVariable("id") Long id, @RequestBody() OrderStatus orderStatus) throws EntityNotFoundException, br.com.postech.techchallenge.order_api.exception.EntityNotFoundException {
        orderService.updateStatus(id, orderStatus);
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsOrderDto> getOrderById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(orderService.findById(id));
    }
}
