package com.swiderski.carrental.sync;

import com.swiderski.carrental.crud.car.CarDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TestController {

    private final SyncService<CarDto> syncService;

    public TestController(SyncService<CarDto> syncService) {
        this.syncService = syncService;
    }

    @GetMapping("/test")
    public List<CarDto> test(@RequestParam LocalDateTime localDateTime) {
        return syncService.getLastedByDateModified(localDateTime);
    }

}
