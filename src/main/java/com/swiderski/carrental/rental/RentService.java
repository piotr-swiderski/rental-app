package com.swiderski.carrental.rental;

import com.swiderski.carrental.abstraction.CommonService;
import com.swiderski.carrental.car.CarDto;

import java.time.LocalDate;
import java.util.Set;

public interface RentService extends CommonService<RentalDto> {

    RentalDto rentCar(long carId, long clientId);

    RentalDto returnCar(long id, LocalDate returnDate);

    Set<CarDto> getAvailableCars();

    Set<CarDto> getRentedCars();
}
