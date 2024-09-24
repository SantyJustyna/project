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

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@Transactional
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
}

