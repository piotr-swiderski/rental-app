package com.swiderski.carrental.sendDataToSync.car;

import com.swiderski.carrental.crud.abstraction.CommonMapper;
import com.swiderski.carrental.crud.car.Car;
import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.sendDataToSync.abstraction.AbstractSyncRevisionBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class CarSyncRevisionBuilder extends AbstractSyncRevisionBuilder<Car, CarDto> {

    public CarSyncRevisionBuilder(EntityManager entityManager, CommonMapper<Car, CarDto> commonMapper) {
        super(entityManager, commonMapper);
    }
}
