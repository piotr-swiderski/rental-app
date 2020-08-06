package com.swiderski.carrental.soap.abstraction;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.rental_service.schema.pageable.AnyTypeXml;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommonWebMapper<E extends AbstractDto, T extends AnyTypeXml> {

    E toDto(T webData);

    T toWebData(E dto);

    List<T> toWebListData(List<E> listDto);

    default PageableXml toWebPageable(Page<E> page) {
        PageableXml wsPage = new PageableXml();

        wsPage.setEmpty(page.isEmpty());
        wsPage.setFirst(page.isFirst());
        wsPage.setTotalPages(page.getTotalPages());
        wsPage.setTotalElements(page.getTotalElements());
        wsPage.setNumberOfElements(page.getNumberOfElements());
        wsPage.setSize(page.getSize());
        wsPage.setNumber(page.getNumber());
        wsPage.getContent().addAll(toWebListData(page.getContent()));

        return wsPage;
    }
}
