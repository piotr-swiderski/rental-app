package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends AbstractService<Car, CarDto> implements CarService {

    public CarServiceImpl(CarMapper carMapper, CarRepository commonRepository) {
        super(carMapper, commonRepository);
    }

}
