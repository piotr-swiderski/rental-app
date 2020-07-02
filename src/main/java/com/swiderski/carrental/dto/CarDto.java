package com.swiderski.carrental.dto;

import com.swiderski.carrental.entity.EngineType;
import java.util.Objects;


public class CarDto {

    private long id;
    private String brand;
    private String colour;
    private EngineType engineType;
    private int productionYear;
    private double cost;

    public CarDto(long id, String brand, String colour, EngineType engineType, int productionYear, double cost) {
        this.id = id;
        this.brand = brand;
        this.colour = colour;
        this.engineType = engineType;
        this.productionYear = productionYear;
        this.cost = cost;
    }

    public CarDto() {
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
        CarDto carDto = (CarDto) o;
        return id == carDto.id &&
                productionYear == carDto.productionYear &&
                Double.compare(carDto.cost, cost) == 0 &&
                Objects.equals(brand, carDto.brand) &&
                Objects.equals(colour, carDto.colour) &&
                engineType == carDto.engineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, colour, engineType, productionYear, cost);
    }
}
