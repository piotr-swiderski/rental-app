package com.swiderski.carrental.soapClient.rental;

import com.swiderski.carrental.crud.rental.RentalDto;
import com.swiderski.carrental.crud.rental.RentalParam;
import com.swiderski.carrental.soap.rental.RentalWebMapper;
import com.swiderski.carrental.soapClient.abstraction.CommonSoapClient;
import com.swiderski.rental_service.schema.rental.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.List;

@Component
public class RentalSoapClient implements CommonSoapClient<RentalDto> {

    private final RentalSOAP rentalSoapProxy;
    private final ObjectFactory rentalObjectFactory;
    private final RentalWebMapper rentalWebMapper;

    public RentalSoapClient(@Qualifier("rentalProxy") RentalSOAP rentalSoapProxy,
                            ObjectFactory rentalObjectFactory,
                            RentalWebMapper rentalWebMapper) {
        this.rentalSoapProxy = rentalSoapProxy;
        this.rentalObjectFactory = rentalObjectFactory;
        this.rentalWebMapper = rentalWebMapper;
    }

    public Page<RentalDto> getAll(RentalParam rentalParam, Pageable pageable) {
        RentalListRequest rentalListRequest = rentalObjectFactory.createRentalListRequest();
        rentalListRequest.setPageNo(pageable.getPageNumber());
        rentalListRequest.setPageSize(pageable.getPageSize());
        rentalListRequest.setSortBy("id");

        RentalList rentals = rentalSoapProxy.rentals(rentalListRequest);

        List<RentalData> rentalData = rentalWebMapper.toDataList(rentals.getPage().getContent());
        List<RentalDto> pageList = rentalWebMapper.toDtoList(rentalData);
        return new PageImpl<>(pageList, pageable, rentals.getPage().getNumberOfElements());
    }

    public RentalDto rentCar(long carId, long clientId) {
        CarRentRequest rentRequest = rentalObjectFactory.createCarRentRequest();
        rentRequest.setCarId(carId);
        rentRequest.setClientId(clientId);

        Rental rental = rentalSoapProxy.rentCar(rentRequest);
        return rentalWebMapper.toDto(rental.getRental());
    }

    public RentalDto returnCar(long rentalId, LocalDate rentalEnd) {
        CarReturnRequest returnRequest = rentalObjectFactory.createCarReturnRequest();
        returnRequest.setRentalId(rentalId);
        returnRequest.setRentalEnd(rentalWebMapper.toXmlCalander(rentalEnd));

        Rental rental = rentalSoapProxy.returnCar(returnRequest);
        return rentalWebMapper.toDto(rental.getRental());
    }

    @Override
    public RentalDto delete(long id) {
        RentalDeleteRequest deleteRequest = rentalObjectFactory.createRentalDeleteRequest();
        deleteRequest.setId(id);

        Rental rental = rentalSoapProxy.deleteRental(deleteRequest);
        return rentalWebMapper.toDto(rental.getRental());
    }

    @Override
    public RentalDto getById(long id) {
        RentalRequest rentalRequest = rentalObjectFactory.createRentalRequest();
        rentalRequest.setId(id);

        Rental rental = rentalSoapProxy.rental(rentalRequest);
        return rentalWebMapper.toDto(rental.getRental());
    }

    @Override
    public RentalDto add(RentalDto dto) {
        Rental rental = rentalObjectFactory.createRental();

        RentalData rentalData = rentalWebMapper.toWebData(dto);
        rentalData.setCar(rentalData.getCar());
        rentalData.setClient(rentalData.getClient());
        rental.setRental(rentalData);

        Rental savedRental = rentalSoapProxy.addRental(rental);
        return rentalWebMapper.toDto(savedRental.getRental());
    }

    @Override
    public RentalDto update(RentalDto dto, long id) {
        dto.setId(id);
        return add(dto);
    }
}
