package com.crud.project.service;

import com.crud.project.controller.OrderNotFoundException;
import com.crud.project.domain.Order;
import com.crud.project.notification.ClientObserver;
import com.crud.project.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTestSuite {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderNotificationService notificationService;

    @Mock
    private ClientObserver clientObserver;

    @Mock
    private JavaMailSender mailSender;

    @Value("${admin.email}")
    private String adminEmail = "admin@example.com";

    @Test
    void testGetAllOrders() {
        // Given
        Order order = new Order();
        when(orderRepository.findAll()).thenReturn(Collections.singletonList(order));

        // When
        var orders = orderService.getAllOrders();

        // Then
        assertNotNull(orders);
        assertEquals(1, orders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrderById() throws OrderNotFoundException {
        // Given
        Long orderId = 1L;
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // When
        var result = orderService.getOrderById(orderId);

        // Then
        assertNotNull(result);
        assertEquals(order, result);
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void testGetOrderByIdThrowsException() {
        // Given
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(orderId));
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void testSaveOrder() {
        // Given
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        // When
        var result = orderService.saveOrder(order);

        // Then
        assertNotNull(result);
        verify(orderRepository, times(1)).save(order);

        // Check if notificationService is called when order is completed
        order.setCompleted(true);
        orderService.saveOrder(order);
        verify(notificationService, times(1)).notifyObservers(order);
    }

    @Test
    void testDeleteOrder() {
        // Given
        Long orderId = 1L;
        doNothing().when(orderRepository).deleteById(orderId);

        // When
        orderService.deleteOrder(orderId);

        // Then
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    void testMarkAsCompleted() throws OrderNotFoundException {
        // Given
        Long orderId = 1L;
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        // When
        var result = orderService.markAsCompleted(orderId);

        // Then
        assertTrue(result.getCompleted());
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(order);
        verify(notificationService, times(1)).notifyObservers(result);
    }

    @Test
    void testInit() {
        // When
        orderService.init();

        // Then
        verify(notificationService, times(1)).registerObserver(clientObserver);
    }

    @Test
    void testCheckAndSendDeliveryNotification() {
        // Given
        Order order = new Order();
        order.setOrderReference("12345");
        LocalDate localDate;
        when(orderRepository.findOrdersByDeliveryDate(LocalDate.now())).thenReturn(Collections.singletonList(order));
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        // When
        orderService.checkAndSendDeliveryNotification();

        // Then
        verify(orderRepository, times(1)).findOrdersByDeliveryDate(LocalDate.now());
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void testSendDeliveryNotification() {
        // Given
        Order order = new Order();
        order.setOrderReference("12345");
        List<Order> orders = Collections.singletonList(order);

        when(orderRepository.findOrdersByDeliveryDate(LocalDate.now())).thenReturn(orders);

        // When
        orderService.checkAndSendDeliveryNotification();

        // Then
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}

