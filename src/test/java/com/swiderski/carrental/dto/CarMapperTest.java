package com.swiderski.carrental.dto;

import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.entity.EngineType;
import com.swiderski.carrental.mapper.CarMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CarMapperTest {

    CarMapper carMapper = CarMapper.INSTANCE;

    @Test
    public void shouldMapCarToCarDto() {
        //given
        Car car = new Car(1, "toyota", "red", EngineType.diesel, 2020, 2000);
        CarDto carDto = new CarDto(1, "toyota", "red", EngineType.diesel, 2020, 2000);
        //when
        CarDto carDtoMapper = carMapper.carToCarDto(car);
        //then
        assertEquals(carDto, carDtoMapper);
    }

    @Test
    public void shouldMapCarDtoToCar() {
        //given
        CarDto carDto = new CarDto(1, "toyota", "red", EngineType.diesel, 2020, 2000);
        Car car = new Car(1, "toyota", "red", EngineType.diesel, 2020, 2000);
        //when
        Car carMapper = this.carMapper.carDtoToCar(carDto);
        //then
        assertEquals(car, carMapper);
    }

    @Test
    public void shouldAssertFalseWhenMapCarToCarDto() {
        //given
        Car car = new Car(1, "toyota", "red", EngineType.diesel, 2020, 2000);
        CarDto carDto = new CarDto(1, "toyotaaaa", "red", EngineType.diesel, 2020, 2000);
        //when
        CarDto carDtoMapper = carMapper.carToCarDto(car);
        //then
        assertNotEquals(carDto, carDtoMapper);
    }

    @Test
    public void shouldMapAssertFalseWhenMapCarDtoToCar() {
        //given
        CarDto carDto = new CarDto(1, "toyotaaa", "red", EngineType.diesel, 2020, 2000);
        Car car = new Car(1, "toyota", "red", EngineType.diesel, 2020, 2000);
        //when
        Car carMapper = this.carMapper.carDtoToCar(carDto);
        //then
        assertNotEquals(car, carMapper);
    }


}