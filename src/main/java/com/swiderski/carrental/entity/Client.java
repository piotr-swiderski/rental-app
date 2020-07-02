package com.swiderski.carrental.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "client_table")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "money")
    private double money;

    @OneToMany
    private Set<Car> rentCars;

}


