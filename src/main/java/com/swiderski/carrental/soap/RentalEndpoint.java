package com.swiderski.carrental.soap;

import com.swiderski.carrental.crud.rental.RentService;
import com.swiderski.carrental.crud.rental.RentalDto;
import com.swiderski.carrental.crud.rental.RentalParam;
import com.swiderski.rental_service.schema.rental.ListPageRequest;
import com.swiderski.rental_service.schema.rental.ObjectFactory;
import com.swiderski.rental_service.schema.rental.Rental;
import com.swiderski.rental_service.schema.rental.RentalData;
import com.swiderski.rental_service.schema.rental.RentalDeleteRequest;
import com.swiderski.rental_service.schema.rental.RentalList;
import com.swiderski.rental_service.schema.rental.RentalRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class RentalEndpoint {

    private final RentService rentService;
    private final RentalWebMapper rentalWebMapper;

    private static final String NAMESPACE_URI = "http://swiderski.com/rental-service/schema/rental";

    public RentalEndpoint(RentService rentService, RentalWebMapper rentalWebMapper) {
        this.rentService = rentService;
        this.rentalWebMapper = rentalWebMapper;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RentalRequest")
    @ResponsePayload
    public Rental getRental(@RequestPayload RentalRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();
        long carId = request.getId();

        RentalDto rentalDto = rentService.getById(carId);

        Rental rentalResponse = objectFactory.createRental();
        RentalData rentalData = rentalWebMapper.toRentalData(rentalDto);
        rentalResponse.setRental(rentalData);

        return rentalResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RentalDeleteRequest")
    @ResponsePayload
    public Rental deleteRental(@RequestPayload RentalDeleteRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();
        long rentalId = request.getId();

        RentalDto deletedRental = rentService.delete(rentalId);
        RentalData rentalData = rentalWebMapper.toRentalData(deletedRental);

        Rental rental = objectFactory.createRental();
        rental.setRental(rentalData);

        return rental;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Rental")
    @ResponsePayload
    public Rental addRental(@RequestPayload Rental addRequest) {
        ObjectFactory objectFactory = new ObjectFactory();
        RentalData rentalData = addRequest.getRental();

        RentalDto rentalDto = rentalWebMapper.toRentalDto(rentalData);
        RentalDto savedRental = rentService.save(rentalDto);

        Rental rental = objectFactory.createRental();
        RentalData rentalDataResponse = rentalWebMapper.toRentalData(savedRental);
        rental.setRental(rentalDataResponse);

        return rental;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PageRequest")
    @ResponsePayload
    public RentalList getRentalList(@RequestPayload ListPageRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();

        int pageNo = request.getPageNo();
        int pageSize = request.getPageSize();
        String sortBy = request.getSortBy();
        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        List<RentalDto> allWithoutSpec = rentService.getAll(new RentalParam(), pageable).getContent();
        List<RentalData> rentalDataList = rentalWebMapper.toRentalDataList(allWithoutSpec);

        RentalList rentalList = objectFactory.createRentalList();
        rentalList.getRental().addAll(rentalDataList);

        return rentalList;
    }

}
