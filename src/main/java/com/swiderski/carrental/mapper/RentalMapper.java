package com.swiderski.carrental.mapper;

import com.swiderski.carrental.dto.RentalDto;
import com.swiderski.carrental.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CarMapper.class, ClientMapper.class})
public interface RentalMapper {

    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    @Mapping(source = "car", target = "carDto")
    @Mapping(source = "client", target = "clientDto")
    RentalDto rentalToRentalDto(Rental rental);

    @Mapping(source = "clientDto", target = "client")
    @Mapping(source = "carDto", target = "car")
    Rental rentalDtoToRental(RentalDto rentalDto);
}
