package com.crud.project.mapper;

import com.crud.project.domain.Order;
import com.crud.project.domain.OrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class OrderMapperTestSuite {
    @Autowired
    private OrderMapper mapper;

    @Test
    public void shouldMapToOrder() {
        //Given
        OrderDto orderDto = new OrderDto(33L, "2222", "Berlin", "Bonn", LocalDate.of(2020, 12, 1), LocalDate.of(2020, 12, 2), true);

        //When
        Order order = mapper.mapToOrder(orderDto);

        //Then
        assertEquals("Bonn", order. getDeliveryPlace());
    }

    @Test
    public void shouldMapToOrderDto() {
        //Given
        Order order = new Order(33L, "2222", "Berlin", "Bonn", LocalDate.of(2020, 12, 1), LocalDate.of(2020, 12, 2), true);

        //When
        OrderDto orderDto = mapper.mapToOrderDto(order);

        //Then
        assertEquals("Berlin", orderDto.getLoadingPlace());
    }

    @Test
    public void shouldMapToOrderDtoList() {
        //Given
        Order order1 = new Order(33L, "2222", "Berlin", "Bonn", LocalDate.of(2020, 12, 1), LocalDate.of(2020, 12, 2), true);
        Order order2 = new Order(34L, "333", "Zerbst", "Bonn", LocalDate.of(2020, 12, 1), LocalDate.of(2020, 12, 2), true);
        List<Order> orderList = List.of(order1, order2);

        //When
        List<OrderDto> orderDtoList = mapper.mapToOrderDtoList(orderList);

        //Then
        assertEquals(2, orderDtoList.size());
        assertEquals("Zerbst", orderDtoList.get(1).getLoadingPlace());
    }
}
