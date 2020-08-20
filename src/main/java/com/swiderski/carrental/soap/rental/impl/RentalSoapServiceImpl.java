package com.swiderski.carrental.soap.rental.impl;

import com.swiderski.carrental.crud.rental.RentService;
import com.swiderski.carrental.crud.rental.RentalDto;
import com.swiderski.carrental.crud.rental.RentalParam;
import com.swiderski.carrental.soap.rental.RentalSoapService;
import com.swiderski.carrental.soap.rental.RentalWebMapper;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import com.swiderski.rental_service.schema.rental.CarRentRequest;
import com.swiderski.rental_service.schema.rental.CarReturnRequest;
import com.swiderski.rental_service.schema.rental.ObjectFactory;
import com.swiderski.rental_service.schema.rental.Rental;
import com.swiderski.rental_service.schema.rental.RentalData;
import com.swiderski.rental_service.schema.rental.RentalDeleteRequest;
import com.swiderski.rental_service.schema.rental.RentalList;
import com.swiderski.rental_service.schema.rental.RentalListRequest;
import com.swiderski.rental_service.schema.rental.RentalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentalSoapServiceImpl implements RentalSoapService {

    @Autowired
    private RentService rentService;
    @Autowired
    private RentalWebMapper rentalWebMapper;
    private final ObjectFactory objectFactory = new ObjectFactory();

    @Override
    public RentalList rentals(RentalListRequest pageableRequest) {
        PageRequest pageable = rentalWebMapper.toPageRequest(pageableRequest.getPageRequest());
        RentalParam rentalParam = rentalWebMapper.toCarParam(pageableRequest.getRentalFilter());

        Page<RentalDto> page = rentService.getAll(rentalParam, pageable);
        PageableXml rentalPageable = rentalWebMapper.toWebPageable(page);

        RentalList rentalList = objectFactory.createRentalList();
        rentalList.setPage(rentalPageable);

        return rentalList;
    }

    @Override
    public Rental deleteRental(RentalDeleteRequest deleteRequest) {
        long rentalId = deleteRequest.getId();

        RentalDto deletedRental = rentService.delete(rentalId);
        RentalData rentalData = rentalWebMapper.toWebData(deletedRental);

        Rental rental = objectFactory.createRental();
        rental.setRental(rentalData);

        return rental;
    }

    @Override
    public Rental addRental(Rental rentalRequest) {
        RentalData rentalData = rentalRequest.getRental();

        RentalDto rentalDto = rentalWebMapper.toDto(rentalData);
        RentalDto savedRental = rentService.save(rentalDto);

        Rental rental = objectFactory.createRental();
        RentalData rentalDataResponse = rentalWebMapper.toWebData(savedRental);
        rental.setRental(rentalDataResponse);

        return rental;
    }

    @Override
    public Rental rental(RentalRequest request) {
        long carId = request.getId();

        RentalDto rentalDto = rentService.getById(carId);

        Rental rentalResponse = objectFactory.createRental();
        RentalData rentalData = rentalWebMapper.toWebData(rentalDto);
        rentalResponse.setRental(rentalData);

        return rentalResponse;
    }

    @Override
    public Rental rentCar(CarRentRequest carRentRequest) {
        RentalDto rentalDto = rentService.rentCar(carRentRequest.getCarId(), carRentRequest.getClientId());
        RentalData rentalData = rentalWebMapper.toWebData(rentalDto);

        Rental rental = objectFactory.createRental();
        rental.setRental(rentalData);

        return rental;
    }

    @Override
    public Rental returnCar(CarReturnRequest carReturnRequest) {

        long rentalId = carReturnRequest.getRentalId();
        LocalDate returnDate = LocalDate.parse(carReturnRequest.getRentalEnd().toString());

        RentalDto rentalDto = rentService.returnCar(rentalId, returnDate);
        RentalData rentalData = rentalWebMapper.toWebData(rentalDto);

        Rental rental = objectFactory.createRental();
        rental.setRental(rentalData);

        return rental;
    }

}
