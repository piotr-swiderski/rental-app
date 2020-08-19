package com.swiderski.carrental.xlsxGenerator;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarParam;
import com.swiderski.carrental.crud.car.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SimpleController {

    private final CarService carService;

    public SimpleController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/excel", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getPdf() {
        Page<CarDto> all = carService.getAll(new CarParam(), Pageable.unpaged());
        byte[] excel = XlsxGenerator.customersToExcel(all.getContent());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report" + LocalDateTime.now() + ".xlsx\"")
                .body(excel);
    }
}
