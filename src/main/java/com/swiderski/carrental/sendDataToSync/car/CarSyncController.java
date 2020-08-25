package com.swiderski.carrental.sendDataToSync.car;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.sendDataToSync.SyncRevisionData;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}"})
@RestController
public class CarSyncController {

    private final CarShareRevisionDataService carShareRevisionDataService;

    public CarSyncController(CarShareRevisionDataService carShareRevisionDataService) {
        this.carShareRevisionDataService = carShareRevisionDataService;
    }

    @GetMapping("cars/getRevisionData")
    public SyncRevisionData<CarDto> getSyncRevisionData(@RequestParam Integer fromRevision) {
        return carShareRevisionDataService.getLastedByRevision(fromRevision);
    }

}
