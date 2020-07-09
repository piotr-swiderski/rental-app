package com.swiderski.carrental.services;

import com.swiderski.carrental.dto.CarDto;
import com.swiderski.carrental.dto.RentalDto;

import java.time.LocalDate;
import java.util.Set;

public interface RentService extends CrudService<RentalDto> {

    RentalDto rentCar(long carId, long clientId);

    RentalDto returnCar(long id, LocalDate returnDate);

    Set<CarDto> getAvailableCars();

    Set<CarDto> getRentedCars();
}
