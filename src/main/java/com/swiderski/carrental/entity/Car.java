package com.swiderski.carrental.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "car_table")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "colour")
    private String colour;

    @Enumerated(EnumType.ORDINAL)
    private EngineType engineType;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "cost")
    private double cost;
}
