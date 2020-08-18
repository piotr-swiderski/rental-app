package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.CommonParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

import static com.swiderski.carrental.crud.car.CarMessageUtils.MIN_YEAR_VALID_MESSAGE;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarParam implements CommonParam {

    private String brand;
    private String model;
    private String colour;
    @Min(value = 1880, message = MIN_YEAR_VALID_MESSAGE)
    private Integer yearFrom;
    private Integer yearTo;
    private EngineType engineType;

}
