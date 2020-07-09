package com.swiderski.carrental.mapper;

import com.swiderski.carrental.dto.RentalDto;
import com.swiderski.carrental.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper(uses = {CarMapper.class, ClientMapper.class}, componentModel = "spring")
public interface RentalMapper {

    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    RentalDto rentalToRentalDto(Rental rental);

    Rental rentalDtoToRental(RentalDto rentalDto);

    Set<RentalDto> rentalSetToRentalDtoSet(Set<Rental> rentalSet);

    Collection<RentalDto> rentalCollectionToRentalDtoCollection(Collection<Rental> rentals);

    List<RentalDto> rentalListToRentalDtoList(List<Rental> rentals);
}
