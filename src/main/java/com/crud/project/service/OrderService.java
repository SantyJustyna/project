package com.crud.project.service;

import com.crud.project.controller.OrderNotFoundException;
import com.crud.project.domain.Order;
import com.crud.project.notification.ClientObserver;
import com.crud.project.notification.Observer;
import com.crud.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderNotificationService notificationService;
    private final ClientObserver clientObserver;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(final Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public Order saveOrder(final Order order) {
        if (order.getCompleted()) {
            notificationService.notifyObservers(order);
        }
        return orderRepository.save(order);
    }

    public void deleteOrder(final Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order markAsCompleted(Long orderId) throws OrderNotFoundException {
        Order order = getOrderById(orderId);
        order.setCompleted(true);
        Order savedOrder = orderRepository.save(order);

        notificationService.notifyObservers(savedOrder);

        return savedOrder;
    }

    @PostConstruct
    public void init() {
        notificationService.registerObserver(clientObserver);
    }
}
