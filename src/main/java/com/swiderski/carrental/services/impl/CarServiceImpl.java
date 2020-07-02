package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.repository.CarRepository;
import com.swiderski.carrental.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car deleteCarById(long id) {
        Car carById = getCarById(id);
        carRepository.delete(carById);
        return carById;
    }

    @Override
    public Car updateCar(long id, Car car) {

        getOptionalCarById(id).ifPresent(c -> {
            car.setId(id);
            carRepository.save(car);
        });
        return car;
    }


    @Override
    public Car getCarById(long id) {
        return getOptionalCarById(id).orElseThrow(EntityNotFoundException::new);
    }

    private Optional<Car> getOptionalCarById(long id) {
        return carRepository.findById(id);
    }
}
