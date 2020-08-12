package com.swiderski.carrental.soap.rental;

import com.swiderski.carrental.crud.rental.RentService;
import com.swiderski.carrental.crud.rental.RentalDto;
import com.swiderski.carrental.crud.rental.RentalParam;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDate;

@Endpoint
public class RentalEndpoint {

    private final RentService rentService;
    private final RentalWebMapper rentalWebMapper;
    private final ObjectFactory objectFactory = new ObjectFactory();

    private static final String NAMESPACE_URI = "http://swiderski.com/rental-service/schema/rental";

    public RentalEndpoint(RentService rentService, RentalWebMapper rentalWebMapper) {
        this.rentService = rentService;
        this.rentalWebMapper = rentalWebMapper;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RentalRequest")
    @ResponsePayload
    public Rental getRental(@RequestPayload RentalRequest request) {
        long carId = request.getId();

        RentalDto rentalDto = rentService.getById(carId);

        Rental rentalResponse = objectFactory.createRental();
        RentalData rentalData = rentalWebMapper.toWebData(rentalDto);
        rentalResponse.setRental(rentalData);

        return rentalResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RentalDeleteRequest")
    @ResponsePayload
    public Rental deleteRental(@RequestPayload RentalDeleteRequest request) {
        long rentalId = request.getId();

        RentalDto deletedRental = rentService.delete(rentalId);
        RentalData rentalData = rentalWebMapper.toWebData(deletedRental);

        Rental rental = objectFactory.createRental();
        rental.setRental(rentalData);

        return rental;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Rental")
    @ResponsePayload
    public Rental addRental(@RequestPayload Rental addRequest) {
        RentalData rentalData = addRequest.getRental();

        RentalDto rentalDto = rentalWebMapper.toDto(rentalData);
        RentalDto savedRental = rentService.save(rentalDto);

        Rental rental = objectFactory.createRental();
        RentalData rentalDataResponse = rentalWebMapper.toWebData(savedRental);
        rental.setRental(rentalDataResponse);

        return rental;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RentalListRequest")
    @ResponsePayload
    public RentalList getRentalList(@RequestPayload RentalListRequest request) {
        int pageNo = request.getPageNo();
        int pageSize = request.getPageSize();
        String sortBy = request.getSortBy();
        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<RentalDto> page = rentService.getAll(new RentalParam(), pageable);
        PageableXml rentalPageable = rentalWebMapper.toWebPageable(page);

        RentalList rentalList = objectFactory.createRentalList();
        rentalList.setPage(rentalPageable);

        return rentalList;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CarRentRequest")
    @ResponsePayload
    public Rental rentCar(@RequestPayload CarRentRequest rentRequest) {

        RentalDto rentalDto = rentService.rentCar(rentRequest.getCarId(), rentRequest.getClientId());
        RentalData rentalData = rentalWebMapper.toWebData(rentalDto);

        Rental rental = objectFactory.createRental();
        rental.setRental(rentalData);

        return rental;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CarReturnRequest")
    @ResponsePayload
    public Rental returnCar(@RequestPayload CarReturnRequest returnRequest) {

        RentalDto rentalDto = rentService.returnCar(returnRequest.getRentalId(), LocalDate.parse(returnRequest.getRentalEnd().toString()));
        RentalData rentalData = rentalWebMapper.toWebData(rentalDto);

        Rental rental = objectFactory.createRental();
        rental.setRental(rentalData);

        return rental;
    }

}
