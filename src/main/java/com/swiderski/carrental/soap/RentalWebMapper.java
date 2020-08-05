package com.swiderski.carrental.soap;

import com.swiderski.carrental.crud.rental.RentalDto;
import com.swiderski.rental_service.schema.pageable.Pageable;
import com.swiderski.rental_service.schema.rental.RentalData;
import com.swiderski.rental_service.schema.rental.RentalPageable;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalWebMapper {

    RentalData toRentalData(RentalDto rentalDto);

    RentalDto toRentalDto(RentalData rentalData);

    List<RentalData> toRentalDataList(List<RentalDto> rentalDtoList);

    default RentalPageable toWebPageable(Page<RentalDto> page) {
        RentalPageable wsPage = new RentalPageable();

        wsPage.setEmpty(page.isEmpty());
        wsPage.setFirst(page.isFirst());
        wsPage.setTotalPages(page.getTotalPages());
        wsPage.setTotalElements(page.getTotalElements());
        wsPage.setNumberOfElements(page.getNumberOfElements());
        wsPage.setSize(page.getSize());
        wsPage.setNumber(page.getNumber());
        wsPage.getContent().addAll(toRentalDataList(page.getContent()));

        return wsPage;
    }

}
