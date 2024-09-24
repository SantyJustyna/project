package com.crud.project.domain;

import com.crud.project.notification.Observable;
import com.crud.project.notification.Observer;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "orders")
public class Order {
    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @Column(name = "reference")
    private String orderReference;

    @Column(name = "loading_place")
    private String loadingPlace;

    @Column(name = "delivery_place")
    private String deliveryPlace;

    @Column(name = "loading_date")
    private LocalDate loadingDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "order_completed", nullable = false)
    private Boolean completed = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public Order(Long id, String orderReference, String loadingPlace, String deliveryPlace, LocalDate loadingDate, LocalDate deliveryDate, Boolean completed) {
        this.id = id;
        this.orderReference = orderReference;
        this.loadingPlace = loadingPlace;
        this.deliveryPlace = deliveryPlace;
        this.loadingDate = loadingDate;
        this.deliveryDate = deliveryDate;
        this.completed = completed;
    }
}
