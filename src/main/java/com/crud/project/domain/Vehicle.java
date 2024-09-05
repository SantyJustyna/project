package com.crud.project.domain;

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
@Entity(name = "vehicles")
public class Vehicle {
    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "type")
    private String type;

    @Column(name = "next_inspection_date")
    private LocalDate nextInspectionDate;

    @ManyToMany(mappedBy = "vehicles")
    private List<Driver> drivers = new ArrayList<>();

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "vehicle",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    public Vehicle(Long id, String plateNumber, String type, LocalDate nextInspectionDate) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.type = type;
        this.nextInspectionDate = nextInspectionDate;
    }
}
