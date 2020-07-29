package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.swiderski.carrental.crud.car.CarSpecification.*;

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
    public Page<CarDto> getAll(@RequestParam(required = false) String brand,
                               @RequestParam(required = false) String model,
                               @RequestParam(required = false) String colour,
                               @RequestParam(required = false) Integer yearFrom,
                               @RequestParam(required = false) Integer yearTo,
                               @RequestParam(required = false) EngineType engineType,
                               @PageableDefault Pageable pageable) {

        Specification<Car> carSpecification = Specification
                .where(hasBrand(brand)
                        .and(hasColour(colour)
                                .and(hasModel(model)
                                        .and(yearFrom(yearFrom)
                                                .and(yearTo(yearTo)
                                                        .and(hasEngineType(engineType)))))));

        return carService.getAll(carSpecification, pageable);
    }
}
