package com.swiderski.carrental.soap.rental;

import com.swiderski.carrental.crud.rental.RentalDto;
import com.swiderski.carrental.soap.abstraction.CommonWebMapper;
import com.swiderski.rental_service.schema.rental.RentalData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RentalWebMapper extends CommonWebMapper<RentalDto, RentalData> {

}
