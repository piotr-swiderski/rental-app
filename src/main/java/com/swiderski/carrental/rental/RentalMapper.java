package com.swiderski.carrental.rental;

import com.swiderski.carrental.abstraction.CommonMapper;
import com.swiderski.carrental.car.CarMapper;
import com.swiderski.carrental.client.ClientMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CarMapper.class, ClientMapper.class}, componentModel = "spring")
public interface RentalMapper extends CommonMapper<Rental, RentalDto> {
    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);
}
