package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.crud.abstraction.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}/cars"})
public class CarController extends AbstractController<CarService, CarDto, CarParam> {

    CarService carService;

    @Autowired
    public CarController(CarService service) {
        super(service);
        this.carService = service;
    }

//    @GetMapping()
//    @ApiPageable
//    public Page<CarDto> getAll(@Valid @ModelAttribute CarParam carParam,
//                               @ApiIgnore @NonNull Pageable pageable) {
//        return carService.getAll(carParam, pageable);
//    }
//
//    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<byte[]> getPdf(@ModelAttribute CarParam carParam) {
//        byte[] pdfReport = carService.getPdfReport(carParam);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"cars" + LocalDateTime.now() + ".pdf\"")
//                .body(pdfReport);
//    }
}
