package com.swiderski.carrental.soap.rental;

import com.swiderski.carrental.soap.SoapEndpoint;
import com.swiderski.carrental.soap.SoapService;
import com.swiderski.rental_service.schema.rental.CarRentRequest;
import com.swiderski.rental_service.schema.rental.CarReturnRequest;
import com.swiderski.rental_service.schema.rental.Rental;
import com.swiderski.rental_service.schema.rental.RentalClient;
import com.swiderski.rental_service.schema.rental.RentalDeleteRequest;
import com.swiderski.rental_service.schema.rental.RentalList;
import com.swiderski.rental_service.schema.rental.RentalListRequest;
import com.swiderski.rental_service.schema.rental.RentalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.jws.WebService;

@Controller
@SoapEndpoint(publish = "/rental")
@WebService(endpointInterface = "com.swiderski.rental_service.schema.rental.RentalClient")
public class RentalSoapController implements RentalClient, SoapService {

    @Autowired
    private RentalSoapService rentalSoapService;

    @Override
    public RentalList rentals(RentalListRequest pageableRequest) {
        return rentalSoapService.rentals(pageableRequest);
    }

    @Override
    public Rental deleteRental(RentalDeleteRequest deleteRequest) {
        return rentalSoapService.deleteRental(deleteRequest);
    }

    @Override
    public Rental returnCar(CarReturnRequest carReturnRequest) {
        return rentalSoapService.returnCar(carReturnRequest);
    }

    @Override
    public Rental addRental(Rental rentalRequest) {
        return rentalSoapService.addRental(rentalRequest);
    }

    @Override
    public Rental rental(RentalRequest request) {
        return rentalSoapService.rental(request);
    }

    @Override
    public Rental rentCar(CarRentRequest carRentRequest) {
        return rentalSoapService.rentCar(carRentRequest);
    }
}
