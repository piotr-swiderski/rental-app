package com.swiderski.carrental.soap.rental;

import com.swiderski.carrental.crud.rental.RentalDto;
import com.swiderski.carrental.soap.abstraction.CommonWebMapper;
import com.swiderski.rental_service.schema.rental.RentalData;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface RentalWebMapper extends CommonWebMapper<RentalDto, RentalData> {

    //RentalFilter toRentalParam(RentalParam rentalParam);

    @SneakyThrows
    default XMLGregorianCalendar toXmlCalander(LocalDate localDate) {
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate.toString());
    }
}
