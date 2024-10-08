package com.crud.project.domain;

import com.crud.project.notification.Observer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "clients")
public class Client {
    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @Column(name = "client_name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "mail")
    private String mail;

    @Column(name = "vat_number")
    private String vatNumber;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "client",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    public Client(Long id, String name, String address, String mail, String vatNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.vatNumber = vatNumber;
    }
}
