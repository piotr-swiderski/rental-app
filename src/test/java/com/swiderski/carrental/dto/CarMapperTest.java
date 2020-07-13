package com.swiderski.carrental.dto;

import com.swiderski.carrental.car.Car;
import com.swiderski.carrental.car.CarDto;
import com.swiderski.carrental.car.CarMapper;
import org.junit.jupiter.api.Test;

import static com.swiderski.carrental.utils.Utils.getCar;
import static com.swiderski.carrental.utils.Utils.getCarDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class CarMapperTest {

    CarMapper carMapper = CarMapper.INSTANCE;

    @Test
    public void shouldMapCarToCarDto() {
        //given
        Car car = getCar();
        CarDto carDto = getCarDto();
        //when
        CarDto carDtoMapper = carMapper.toDto(car);
        //then
        assertEquals(carDto, carDtoMapper);
    }

    @Test
    public void shouldMapCarDtoToCar() {
        //given
        CarDto carDto = getCarDto();
        Car car = getCar();
        //when
        Car carMapper = this.carMapper.fromDto(carDto);
        //then
        assertEquals(car, carMapper);
    }

    @Test
    public void shouldAssertFalseWhenMapCarToCarDto() {
        //given
        Car car = getCar();
        CarDto carDto = getCarDto();
        carDto.setBrand("Honda");
        //when
        CarDto carDtoMapper = carMapper.toDto(car);
        //then
        assertNotEquals(carDto, carDtoMapper);
    }

    @Test
    public void shouldMapAssertFalseWhenMapCarDtoToCar() {
        //given
        CarDto carDto = getCarDto();
        Car car = getCar();
        car.setBrand("Honda");
        //when
        Car carMapped = carMapper.fromDto(carDto);
        //then
        assertNotEquals(car, carMapped);
    }
}