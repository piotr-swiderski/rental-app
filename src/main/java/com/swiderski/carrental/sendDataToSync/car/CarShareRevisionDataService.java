package com.swiderski.carrental.sendDataToSync.car;

import com.swiderski.carrental.crud.car.Car;
import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.sendDataToSync.SyncRevisionData;
import com.swiderski.carrental.sendDataToSync.abstraction.CommonShareRevisionDataService;
import com.swiderski.carrental.sendDataToSync.abstraction.AbstractSyncRevisionBuilder;
import org.springframework.stereotype.Service;

@Service
public class CarShareRevisionDataService implements CommonShareRevisionDataService<CarDto> {

    private final AbstractSyncRevisionBuilder<Car, CarDto> abstractSyncRevisionBuilder;

    public CarShareRevisionDataService(AbstractSyncRevisionBuilder<Car, CarDto> abstractSyncRevisionBuilder) {
        this.abstractSyncRevisionBuilder = abstractSyncRevisionBuilder;
    }

    @Override
    public SyncRevisionData<CarDto> getLastedByRevision(Integer fromRevision) {
        return abstractSyncRevisionBuilder.getSyncRevisionData(fromRevision, Car.class);
    }
}
