package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

import static com.swiderski.carrental.crud.car.CarMessageUtils.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDto extends AbstractDto {

    @NotBlank(message = BRAND_VALID_MESSAGE)
    private String brand;

    @NotBlank(message = COLOUR_VALID_MESSAGE)
    private String colour;

    @NotBlank(message = MODEL_VALID_MESSAGE)
    private String model;

    private String modelVersion;

    @NotNull(message = ENGINE_TYPE_VALID_MESSAGE)
    private EngineType engineType;

    @Min(value= 1850, message = PRODUCTION_YEAR_VALID_MESSAGE)
    private int productionYear;

    @Positive(message = COST_VALID_MESSAGE)
    private double cost;

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
