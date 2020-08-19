package com.swiderski.carrental.soap.abstraction;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.rental_service.schema.pageable.AnyTypeXml;
import com.swiderski.rental_service.schema.pageable.PageRequestXml;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public interface CommonWebMapper<E extends AbstractDto, T extends AnyTypeXml> {

    E toDto(T webData);

    T toWebData(E dto);

    List<T> toWebListData(List<E> listDto);

    List<E> toDtoList(List<T> listDto);

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

    default List<T> toDataList(List<AnyTypeXml> webList) {
        List<T> list = new ArrayList<>();
        for (AnyTypeXml element : webList) {
            list.add((T) element);
        }
        return list;
    }

    default PageRequest toPageRequest(PageRequestXml pageRequestXml) {
        return PageRequest.of(pageRequestXml.getPageNo(), pageRequestXml.getPageSize(), Sort.by(pageRequestXml.getSortBy()));
    }

    default PageRequestXml toPageRequestXml(Pageable pageable) {
        PageRequestXml pageRequestXml = new PageRequestXml();
        pageRequestXml.setPageNo(pageable.getPageNumber());
        pageRequestXml.setPageSize(pageable.getPageSize());
        if (pageable.getSort().isSorted()) {
            pageRequestXml.setSortBy(pageable.getSort().iterator().next().getProperty());
        } else {
            pageRequestXml.setSortBy("");
        }
        return pageRequestXml;
    }
}
