package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.abstraction.CommonMapper;
import com.swiderski.carrental.crud.car.CarMapper;
import com.swiderski.carrental.crud.client.ClientMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(uses = {CarMapper.class, ClientMapper.class}, componentModel = "spring")
public interface RentalMapper extends CommonMapper<Rental, RentalDto> {

    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    Collection<RentalDto> rentalCollectionToRentalDtoCollection(Collection<Rental> rentals);

}
