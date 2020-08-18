package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.abstraction.CommonService;
import com.swiderski.carrental.crud.car.CarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface RentService extends CommonService<RentalDto, RentalParam> {

    RentalDto rentCar(long carId, long clientId);

    RentalDto returnCar(long id, LocalDate returnDate);

    Page<CarDto> getAvailableCars(Pageable pageable);

    Page<CarDto> getRentedCars(Pageable pageable);
}
