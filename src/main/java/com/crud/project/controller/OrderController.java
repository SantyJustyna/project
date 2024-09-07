package com.crud.project.controller;

import com.crud.project.domain.DriverDto;
import com.crud.project.domain.Order;
import com.crud.project.domain.OrderDto;
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
    private OrderService service;

    @Autowired
    private OrderMapper mapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<Order> orders = service.getAllOrders();
        return ResponseEntity.ok(mapper.mapToOrderDtoList(orders));
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.ok(mapper.mapToOrderDto(service.getOrderById(orderId)));
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        service.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {
        Order order = mapper.mapToOrder(orderDto);
        Order savedOrder = service.saveOrder(order);
        return ResponseEntity.ok(mapper.mapToOrderDto(savedOrder));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) {
        Order order = mapper.mapToOrder(orderDto);
        service.saveOrder(order);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{orderId}/complete")
    public ResponseEntity<OrderDto> markOrderAsCompleted(@PathVariable Long orderId) throws OrderNotFoundException {
        Order completedOrder = service.markAsCompleted(orderId);
        return ResponseEntity.ok(mapper.mapToOrderDto(completedOrder));
    }
}
