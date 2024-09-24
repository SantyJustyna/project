package com.crud.project.controller;

import com.crud.project.domain.DriverDto;
import com.crud.project.domain.Order;
import com.crud.project.domain.OrderDto;
import com.crud.project.facade.OrderFacade;
import com.crud.project.mapper.OrderMapper;
import com.crud.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
public class OrderController {
    @Autowired
    private final OrderFacade orderFacade;

    @Autowired
    private final OrderMapper mapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = orderFacade.getAllOrders();
        return ResponseEntity.ok(mapper.mapToOrderDtoList(orders));
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(mapper.mapToOrderDto(orderFacade.getOrderById(orderId)));
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderFacade.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {
        Order order = mapper.mapToOrder(orderDto);
        Order savedOrder = orderFacade.saveOrder(order);
        return ResponseEntity.ok(mapper.mapToOrderDto(savedOrder));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) {
        Order order = mapper.mapToOrder(orderDto);
        orderFacade.saveOrder(order);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<OrderDto> markOrderAsCompleted(@PathVariable Long orderId) throws OrderNotFoundException {
        Order completedOrder = orderFacade.markOrderAsCompleted(orderId);
        return ResponseEntity.ok(mapper.mapToOrderDto(completedOrder));
    }

    @GetMapping("/{orderId}/distance")
    public ResponseEntity<Long> getOrderDistance(@PathVariable Long orderId) throws Exception {
        Order order = orderFacade.getOrderById(orderId);
        long distance = orderFacade.calculateOrderDistance(order) / 1000;
        return ResponseEntity.ok(distance);
    }

    @GetMapping("/{orderId}/weather")
    public ResponseEntity<String> getWeatherForOrder(@PathVariable Long orderId) throws OrderNotFoundException{
        Order order = orderFacade.getOrderById(orderId);
        String weather = orderFacade.getWeatherForOrder(order);
        return ResponseEntity.ok(weather);
    }
}
