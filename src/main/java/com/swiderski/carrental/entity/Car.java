package com.swiderski.carrental.entity;

import javax.persistence.*;
import java.util.Objects;

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

    @Column(name = "")
    private boolean status;

    public Car(long id, String brand, String colour, EngineType engineType, int productionYear, double cost) {
        this.id = id;
        this.brand = brand;
        this.colour = colour;
        this.engineType = engineType;
        this.productionYear = productionYear;
        this.cost = cost;
    }

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                productionYear == car.productionYear &&
                Double.compare(car.cost, cost) == 0 &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(colour, car.colour) &&
                engineType == car.engineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, colour, engineType, productionYear, cost);
    }
}
