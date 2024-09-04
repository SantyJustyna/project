package com.crud.project.controller;

import com.crud.project.domain.DriverDto;
import com.crud.project.domain.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    @GetMapping
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{orderId}")
    public OrderDto getOrder(Long orderId) {
        return new OrderDto(1L, "Warsaw", "Bristol", LocalDate.of(2022, 12, 2), LocalDate.of(2022, 12, 12), true);
    }

    @DeleteMapping(value = "{orderId}")
    public void deleteOrder(Long orderId) {

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(OrderDto orderDto) {
        return new OrderDto(1L, "Berlin", "Bristol", LocalDate.of(2022, 12, 2), LocalDate.of(2022, 12, 12), true);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(OrderDto orderDto) {

    }
}
