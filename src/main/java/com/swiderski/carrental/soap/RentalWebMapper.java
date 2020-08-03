package com.swiderski.carrental.soap;

import com.swiderski.carrental.crud.rental.RentalDto;
import com.swiderski.rental_service.schema.rental.RentalData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalWebMapper {

    RentalData toRentalData(RentalDto rentalDto);

    RentalDto toRentalDto(RentalData rentalData);

    List<RentalData> toRentalDataList(List<RentalDto> rentalDtoList);
}
