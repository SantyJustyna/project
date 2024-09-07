package com.crud.project.mapper;

import com.crud.project.domain.Order;
import com.crud.project.domain.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMapper {
    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                orderDto.getOrderReference(),
                orderDto.getLoadingPlace(),
                orderDto.getDeliveryPlace(),
                orderDto.getLoadingDate(),
                orderDto.getDeliveryDate(),
                orderDto.getCompleted()
        );
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderReference(),
                order.getLoadingPlace(),
                order.getDeliveryPlace(),
                order.getLoadingDate(),
                order.getDeliveryDate(),
                order.getCompleted()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .toList();
    }
}
