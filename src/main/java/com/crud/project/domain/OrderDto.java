package com.crud.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String orderReference;
    private String loadingPlace;
    private String deliveryPlace;
    private LocalDate loadingDate;
    private LocalDate deliveryDate;
    private Boolean completed;
    private String driverSurname;
    private String plateNumber;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.orderReference = order.getOrderReference();
        this.loadingPlace = order.getLoadingPlace();
        this.deliveryPlace = order.getDeliveryPlace();
        this.loadingDate = order.getLoadingDate();
        this.deliveryDate = order.getDeliveryDate();
        this.completed = order.getCompleted();
        this.driverSurname = order.getDriver().getSurname();
        this.plateNumber= order.getVehicle().getPlateNumber();
    }
}
