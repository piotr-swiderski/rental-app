package com.swiderski.carrental.crud.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarParam {

    private String brand;
    private String model;
    private String colour;
    private Integer yearFrom;
    private Integer yearTo;
    private EngineType engineType;

}
