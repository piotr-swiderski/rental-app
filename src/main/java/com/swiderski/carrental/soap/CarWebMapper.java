package com.swiderski.carrental.soap;

import com.swiderski.carrental.crud.car.CarDto;
import com.swiderski.carrental.crud.car.CarParam;
import com.swiderski.rental_service.schema.car.CarData;
import com.swiderski.rental_service.schema.car.CarFilter;
import com.swiderski.rental_service.schema.car.CarPageable;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarWebMapper {

    CarDto toDto(CarData carData);

    CarData toCarData(CarDto carDto);

    List<CarData> toCarDataList(List<CarDto> carDtos);

    CarParam toCarParam(CarFilter carFilter);

    default CarPageable toWebPageable(Page<CarDto> page) {
        CarPageable wsPage = new CarPageable();

        wsPage.setEmpty(page.isEmpty());
        wsPage.setFirst(page.isFirst());
        wsPage.setTotalPages(page.getTotalPages());
        wsPage.setTotalElements(page.getTotalElements());
        wsPage.setNumberOfElements(page.getNumberOfElements());
        wsPage.setSize(page.getSize());
        wsPage.setNumber(page.getNumber());
        wsPage.getContent().addAll(toCarDataList(page.getContent()));

        return wsPage;
    }
}
