package com.swiderski.carrental.synchronizeData.car;

import com.swiderski.carrental.crud.abstraction.CommonMapper;
import com.swiderski.carrental.crud.abstraction.CommonRepository;
import com.swiderski.carrental.crud.car.Car;
import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.synchronizeData.abstraction.AbstractSynchronization;
import com.swiderski.carrental.synchronizeData.revisionInfo.RevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarSynchronization extends AbstractSynchronization<Car, CarDto, CarFeignClient> {

    @Autowired
    public CarSynchronization(CarFeignClient commonSyncClient,
                              CommonMapper<Car, CarDto> commonMapper,
                              RevisionRepository revisionRepository,
                              CommonRepository<Car> commonRepository) {
        super(commonSyncClient, commonMapper, revisionRepository, commonRepository);
    }
}
