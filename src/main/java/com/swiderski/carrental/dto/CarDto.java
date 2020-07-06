package com.swiderski.carrental.dto;

import com.swiderski.carrental.entity.EngineType;

import java.util.Objects;


public class CarDto {

    private long id;
    private String brand;
    private String colour;
    private String model;
    private String modelVersion;
    private EngineType engineType;
    private int productionYear;
    private double cost;

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
                Objects.equals(model, carDto.model) &&
                Objects.equals(modelVersion, carDto.modelVersion) &&
                engineType == carDto.engineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, colour, model, modelVersion, engineType, productionYear, cost);
    }


    public static final class CarDtoBuilder {
        private long id;
        private String brand;
        private String colour;
        private String model;
        private String modelVersion;
        private EngineType engineType;
        private int productionYear;
        private double cost;

        private CarDtoBuilder() {
        }

        public static CarDtoBuilder aCarDto() {
            return new CarDtoBuilder();
        }

        public CarDtoBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public CarDtoBuilder withBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public CarDtoBuilder withColour(String colour) {
            this.colour = colour;
            return this;
        }

        public CarDtoBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public CarDtoBuilder withModelVersion(String modelVersion) {
            this.modelVersion = modelVersion;
            return this;
        }

        public CarDtoBuilder withEngineType(EngineType engineType) {
            this.engineType = engineType;
            return this;
        }

        public CarDtoBuilder withProductionYear(int productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public CarDtoBuilder withCost(double cost) {
            this.cost = cost;
            return this;
        }

        public CarDto build() {
            CarDto carDto = new CarDto();
            carDto.setId(id);
            carDto.setBrand(brand);
            carDto.setColour(colour);
            carDto.setModel(model);
            carDto.setModelVersion(modelVersion);
            carDto.setEngineType(engineType);
            carDto.setProductionYear(productionYear);
            carDto.setCost(cost);
            return carDto;
        }
    }
}
