package com.swiderski.carrental.soap.rental;

import com.swiderski.rental_service.schema.rental.CarRentRequest;
import com.swiderski.rental_service.schema.rental.CarReturnRequest;
import com.swiderski.rental_service.schema.rental.Rental;
import com.swiderski.rental_service.schema.rental.RentalDeleteRequest;
import com.swiderski.rental_service.schema.rental.RentalList;
import com.swiderski.rental_service.schema.rental.RentalListRequest;
import com.swiderski.rental_service.schema.rental.RentalRequest;

public interface RentalSoapService {

    RentalList rentals(RentalListRequest pageableRequest);

    Rental deleteRental(RentalDeleteRequest deleteRequest);

    Rental addRental(Rental rentalRequest);

    Rental rental(RentalRequest request);

    Rental rentCar(CarRentRequest carRentRequest);

    Rental returnCar(CarReturnRequest carReturnRequest);
}
