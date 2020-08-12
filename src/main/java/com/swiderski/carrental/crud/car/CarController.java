package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.crud.abstraction.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}/cars"})
public class CarController extends AbstractController<CarService, CarDto> {

    CarService carService;

    @Autowired
    public CarController(CarService service) {
        super(service);
        this.carService = service;
    }

    @GetMapping()
    @ApiPageable
    public Page<CarDto> getAll(@Valid @ModelAttribute CarParam carParam,
                               @ApiIgnore @NonNull Pageable pageable) {
        return carService.getAll(carParam, pageable);
    }
}
