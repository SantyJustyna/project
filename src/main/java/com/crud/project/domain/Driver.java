package com.crud.project.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "drivers")
public class Driver {
    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @Column(name = "driver_name")
    private String name;

    @Column(name = "driver_surname")
    private String surname;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "driver",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "join_vehicles_drivers",
            joinColumns = {@JoinColumn(name = "driver_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "vehicle_id", referencedColumnName = "id")}
    )
    private List<Vehicle> vehicles = new ArrayList<>();

    public Driver(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
