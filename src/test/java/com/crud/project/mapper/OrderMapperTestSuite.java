package com.crud.project.mapper;

import com.crud.project.domain.Driver;
import com.crud.project.domain.Order;
import com.crud.project.domain.OrderDto;
import com.crud.project.domain.Vehicle;
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
        OrderDto orderDto = new OrderDto(33L, "2222", "Berlin", "Bonn", LocalDate.of(2020, 12, 1), LocalDate.of(2020, 12, 2), true, "Nowak", "EZD22222");

        //When
        Order order = mapper.mapToOrder(orderDto);

        //Then
        assertEquals("Bonn", order. getDeliveryPlace());
    }

    @Test
    public void shouldMapToOrderDto() {
        //Given
        Driver driver = new Driver();
        driver.setSurname("Nowak");

        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber("EZD2222222");

        Order order = new Order(33L, "2222", "Berlin", "Bonn", LocalDate.of(2020, 12, 1), LocalDate.of(2020, 12, 2), true);
        order.setDriver(driver);
        order.setVehicle(vehicle);

        //When
        OrderDto orderDto = mapper.mapToOrderDto(order);

        //Then
        assertEquals("Berlin", orderDto.getLoadingPlace());
    }

    @Test
    public void shouldMapToOrderDtoList() {
        //Given
        Driver driver1 = new Driver();
        driver1.setSurname("Nowak");

        Driver driver2 = new Driver();
        driver2.setSurname("Kowalski");

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setPlateNumber("EZD2222222");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setPlateNumber("EZD5555555555");

        Order order1 = new Order(33L, "2222", "Berlin", "Bonn", LocalDate.of(2020, 12, 1), LocalDate.of(2020, 12, 2), true);
        order1.setDriver(driver1);
        order1.setVehicle(vehicle1);

        Order order2 = new Order(34L, "333", "Zerbst", "Bonn", LocalDate.of(2020, 12, 1), LocalDate.of(2020, 12, 2), true);
        order2.setDriver(driver2);
        order2.setVehicle(vehicle2);
        List<Order> orderList = List.of(order1, order2);

        //When
        List<OrderDto> orderDtoList = mapper.mapToOrderDtoList(orderList);

        //Then
        assertEquals(2, orderDtoList.size());
        assertEquals("Zerbst", orderDtoList.get(1).getLoadingPlace());
    }
}
