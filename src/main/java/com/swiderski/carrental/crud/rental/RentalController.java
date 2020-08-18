package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.crud.abstraction.AbstractController;
import com.swiderski.carrental.crud.car.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

import static com.swiderski.carrental.crud.abstraction.MessageUtils.ID_POSITIVE_MESSAGE;

@RestController
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}/rentals"})
public class RentalController extends AbstractController<RentService, RentalDto, RentalParam> {

    private final RentService rentService;

    @Autowired
    public RentalController(RentService service) {
        super(service);
        this.rentService = service;
    }

    @GetMapping("/availableCars")
    @ApiPageable
    @PreAuthorize("hasRole('USER')")
    public Page<CarDto> getAllAvailableCars(@ApiIgnore @NonNull Pageable pageable) {
        return rentService.getAvailableCars(pageable);
    }

    @GetMapping("/rentedCars")
    @ApiPageable
    @PreAuthorize("hasRole('USER')")
    public Page<CarDto> getRentedCars(@ApiIgnore @NonNull Pageable pageable) {
        return rentService.getRentedCars(pageable);
    }

    @PostMapping("/rent")
    @PreAuthorize("hasRole('USER')")
    public RentalDto rentCar(@RequestParam @Positive(message = ID_POSITIVE_MESSAGE) long carId,
                             @RequestParam @Positive(message = ID_POSITIVE_MESSAGE) long clientId) {
        return rentService.rentCar(carId, clientId);
    }

    @PostMapping("/return")
    @PreAuthorize("hasRole('USER')")
    public RentalDto returnCar(@RequestParam @PositiveOrZero(message = ID_POSITIVE_MESSAGE) long rentalId,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate returnedDate) {
        return rentService.returnCar(rentalId, returnedDate);
    }
}
