package com.crud.project.facade;

import com.crud.project.controller.OrderNotFoundException;
import com.crud.project.domain.Order;
import com.crud.project.service.GoogleMapsService;
import com.crud.project.service.OpenWeatherService;
import com.crud.project.service.OrderNotificationService;
import com.crud.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;
    private final OrderNotificationService notificationService;
    private final GoogleMapsService googleMapsService;
    private final OpenWeatherService openWeatherService;

    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public Order getOrderById(final Long orderId) throws OrderNotFoundException {
        return orderService.getOrderById(orderId);
    }

    public Order saveOrder(final Order order) {
        if (order.getCompleted()) {
            notificationService.notifyObservers(order);
        }
        return orderService.saveOrder(order);
    }

    public void deleteOrder(final Long orderId) {
        orderService.deleteOrder(orderId);
    }

    public Order markOrderAsCompleted(Long orderId) throws OrderNotFoundException {
        Order order = orderService.getOrderById(orderId);
        order.setCompleted(true);
        Order savedOrder = orderService.saveOrder(order);

        notificationService.notifyObservers(savedOrder);

        return savedOrder;
    }

    public long calculateOrderDistance(Order order) throws Exception {
        String origin = order.getLoadingPlace();
        String destination = order.getDeliveryPlace();
        return googleMapsService.calculateDistance(origin, destination);
    }

    public String getWeatherForOrder(Order order) {
        String location = order.getLoadingPlace();
        return openWeatherService.getWeatherForLocation(location);
    }
}
