package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.exception.NotFoundException;
import com.swiderski.carrental.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.swiderski.carrental.utils.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void getAllCars_shouldReturnedAllCars() {
        //given
        List<Car> cars = getCars();
        when(carRepository.findAll()).thenReturn(cars);
        //when
        Set<Car> allCars = carService.getAllCars();
        //then
        assertEquals(2, allCars.size());
    }

    @Test
    void deleteCarById_shouldDeletedCar() {
        //given
        Car car = getCar();
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        //when
        Car deletedCar = carService.deleteCarById(carId);
        //then
        assertEquals(car, deletedCar);
    }

    @Test
    void updateCar_shouldChangeModelName() {
        //given
        Car car = getCar();
        Car modifiedCar = getCar();
        modifiedCar.setModel("XXX");
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        //when
        Car updateCar = carService.updateCar(carId, modifiedCar);
        //then
        assertEquals(modifiedCar, updateCar);
    }

    @Test
    void getCarById_shouldReturnedCarById() {
        //given
        Car car = getCar();
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        //when
        Car carById = carService.getCarById(carId);
        //then
        assertEquals(car, carById);
    }

    @Test
    void getCarById_shouldThrowNotFoundException() {
        when(carRepository.findById(carId)).thenReturn(Optional.empty());
        String expectedMessages = "Could not find " + Car.class.getSimpleName() + " with id = " + carId;

        Throwable exception = assertThrows(NotFoundException.class, () -> carService.getCarById(carId));
        assertEquals(expectedMessages, exception.getMessage());

    }

}