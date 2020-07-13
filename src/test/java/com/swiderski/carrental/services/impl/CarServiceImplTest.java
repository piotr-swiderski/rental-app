package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.car.CarDto;
import com.swiderski.carrental.car.Car;
import com.swiderski.carrental.car.CarServiceImpl;
import com.swiderski.carrental.exception.NotFoundException;
import com.swiderski.carrental.car.CarMapper;
import com.swiderski.carrental.car.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    @Spy
    CarMapper carMapper = CarMapper.INSTANCE;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveCar_shouldSavedCar() {
        //given
        CarDto carDto = getCarDto();
        Car car = getCar();
        when(carRepository.save(any(Car.class))).thenReturn(car);
        //when
        CarDto savedCar = carService.save(carDto);
        //then
        assertEquals(carDto, savedCar);
    }

    @Test
    void getAllCars_shouldReturnedAllCars() {
        //given
        List<Car> cars = getCars();
        when(carRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(cars));
        //when
        Collection<CarDto> allCars = carService.getAll(0, 10, "id");
        //then
        assertEquals(2, allCars.size());
    }

    @Test
    void deleteCarById_shouldDeletedCar() {
        //given
        CarDto carDto = getCarDto();
        Car car = getCar();
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        //when
        CarDto deletedCar = carService.delete(carId);
        //then
        assertEquals(carDto, deletedCar);
    }

    @Test
    void updateCar_shouldChangeModelName() {
        //given
        Car car = getCar();
        Car modifiedCar = getCar();
        modifiedCar.setModel("XXX");
        CarDto modifiedCarDto = getCarDto();
        modifiedCarDto.setModel("XXX");
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        when(carRepository.save(any(Car.class))).thenReturn(modifiedCar);
        //when
        CarDto updateCar = carService.update(carId, modifiedCarDto);
        //then
        assertEquals(modifiedCarDto, updateCar);
    }

    @Test
    void getCarById_shouldReturnedCarById() {
        //given
        CarDto carDto = getCarDto();
        Car car = getCar();
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        //when
        CarDto carById = carService.getById(carId);
        //then
        assertEquals(carDto, carById);
    }

    @Test
    void getCarById_shouldThrowNotFoundException() {
        when(carRepository.findById(carId)).thenReturn(Optional.empty());
        String expectedMessages = "Could not find " + "entity" + " with id = " + carId;

        Throwable exception = assertThrows(NotFoundException.class, () -> carService.getById(carId));
        assertEquals(expectedMessages, exception.getMessage());
    }

}