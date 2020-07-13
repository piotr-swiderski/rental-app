package com.swiderski.carrental.car;

import com.swiderski.carrental.abstraction.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Entity(name = "car_table")
public class Car extends AbstractEntity {

    @Column(name = "brand")
    private String brand;

    @Column(name = "model_name")
    private String model;

    @Column(name = "model_version")
    private String modelVersion;

    @Column(name = "colour")
    private String colour;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "cost")
    private double cost;

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
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
                Objects.equals(model, car.model) &&
                Objects.equals(modelVersion, car.modelVersion) &&
                Objects.equals(colour, car.colour) &&
                engineType == car.engineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, modelVersion, colour, engineType, productionYear, cost);
    }


    public static final class CarBuilder {
        private long id;
        private String brand;
        private String model;
        private String modelVersion;
        private String colour;
        private EngineType engineType;
        private int productionYear;
        private double cost;

        private CarBuilder() {
        }

        public static CarBuilder aCar() {
            return new CarBuilder();
        }

        public CarBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public CarBuilder withBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public CarBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder withModelVersion(String modelVersion) {
            this.modelVersion = modelVersion;
            return this;
        }

        public CarBuilder withColour(String colour) {
            this.colour = colour;
            return this;
        }

        public CarBuilder withEngineType(EngineType engineType) {
            this.engineType = engineType;
            return this;
        }

        public CarBuilder withProductionYear(int productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public CarBuilder withCost(double cost) {
            this.cost = cost;
            return this;
        }

        public Car build() {
            Car car = new Car();
            car.setId(id);
            car.setBrand(brand);
            car.setModel(model);
            car.setModelVersion(modelVersion);
            car.setColour(colour);
            car.setEngineType(engineType);
            car.setProductionYear(productionYear);
            car.setCost(cost);
            return car;
        }
    }
}
