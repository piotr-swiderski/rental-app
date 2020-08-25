package com.swiderski.carrental.synchronizeData.car;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.synchronizeData.abstraction.CommonSyncClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CarClient",
        url = "localhost:9093/v1/cars")
public interface CarFeignClient extends CommonSyncClient<CarDto> {

}
