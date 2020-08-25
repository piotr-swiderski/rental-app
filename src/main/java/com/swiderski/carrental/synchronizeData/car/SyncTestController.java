package com.swiderski.carrental.synchronizeData.car;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.sendDataToSync.SyncRevisionData;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/sync")
@EnableFeignClients(basePackageClasses = CarFeignClient.class)
public class SyncTestController {

    private final CarFeignClient carFeignClient;

    public SyncTestController(CarFeignClient carFeignClient) {
        this.carFeignClient = carFeignClient;
    }

    @GetMapping
    public SyncRevisionData<CarDto> getRevision(@RequestParam Integer rev) {
        return carFeignClient.getRevisionData(rev);
    }
}
