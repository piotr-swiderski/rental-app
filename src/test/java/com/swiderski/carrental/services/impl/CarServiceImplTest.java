package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static com.swiderski.carrental.utils.Utils.getCar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CarServiceImplTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarServiceImpl carService;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveCar_shouldSavedCar() {
        //given
        Car car = getCar();
        when(carRepository.save(any(Car.class))).thenReturn(car);
        //when
        Car savedCar = carService.saveCar(car);
        //then
        assertEquals(car, savedCar);
    }

    @Test
    public void getAllCars() {
        //given
        //when

        //then
    }

    @Test
    public void deleteCarById() {
    }

    @Test
    public void updateCar() {
    }

    @Test
    public void getCarById() {
    }
}