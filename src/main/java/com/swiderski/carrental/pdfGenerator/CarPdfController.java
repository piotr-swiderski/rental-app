package com.swiderski.carrental.pdfGenerator;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarParam;
import com.swiderski.carrental.crud.car.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pdf")
public class CarPdfController {

    private final CarService carService;

    public CarPdfController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/car", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdf() {
        Page<CarDto> all = carService.getAll(new CarParam(), Pageable.unpaged());
        byte[] pdf = PdfGeneratorImpl.build(all.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "cars" + ".pdf\"")
                .body(pdf);
    }


}
