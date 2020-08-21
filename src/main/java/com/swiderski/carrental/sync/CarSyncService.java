package com.swiderski.carrental.sync;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarMapper;
import com.swiderski.carrental.crud.car.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarSyncService implements SyncService<CarDto> {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarSyncService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public List<CarDto> getLastedByDateModified(LocalDateTime modifiedDate) {
        return carMapper.toListDto(carRepository.findLastedUpdateCars(modifiedDate));
    }
}
