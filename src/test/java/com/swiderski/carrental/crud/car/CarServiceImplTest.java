package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

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
        when(carRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(new PageImpl<>(cars));
        //when
        Page<CarDto> allCars = carService.getAll(new CarParam(), pageRequest);
        //then
        assertEquals(2, allCars.getTotalElements());
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