package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.dto.CarDto;
import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.exception.NotFoundException;
import com.swiderski.carrental.mapper.CarMapper;
import com.swiderski.carrental.repository.CarRepository;
import com.swiderski.carrental.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public CarDto save(CarDto carDto) {
        Car car = carMapper.carDtoToCar(carDto);
        Car savedCar = carRepository.save(car);
        return carMapper.carToCarDto(savedCar);
    }

    @Override
    public List<CarDto> getAll(int pageNo, int pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Car> pagedResult = carRepository.findAll(paging).getContent();
        return carMapper.carListToCarDtoList(pagedResult);
    }

    @Override
    public CarDto delete(long id) {
        Car carById = getCarById(id);
        carRepository.delete(carById);
        return carMapper.carToCarDto(carById);
    }

    @Override
    public CarDto update(long id, CarDto car) {
        car.setId(id);
        return save(car);
    }

    @Override
    public CarDto getById(long id) {
        Car car = getCarById(id);
        return carMapper.carToCarDto(car);
    }


    private Car getCarById(long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, Car.class.getSimpleName()));
    }
}
