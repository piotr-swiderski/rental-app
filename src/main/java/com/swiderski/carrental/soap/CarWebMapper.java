package com.swiderski.carrental.soap;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.rental_service.Car;
import com.swiderski.rental_service.CarObject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarWebMapper {

    CarDto toDto(CarObject carObject);

    CarObject toCarWebObj(CarDto carDto);

    List<Car> toCarWebList(List<CarDto> carDtos);
}
