package com.swiderski.carrental.storageSoap;

import org.springframework.data.domain.Pageable;
import pl.slowikowski.jakub.soap_example.pageable.PageableXml;

public interface PageableXmlMapper {

    default PageableXml toPageableXml(Pageable pageable) {

        PageableXml pageableXml = new PageableXml();
        pageableXml.setPage(pageable.getPageNumber());
        pageableXml.setSize(pageable.getPageSize());
        if (pageable.getSort().isSorted()) {
            pageableXml.setSort(pageable.getSort().iterator().next().getProperty());
        } else {
            pageableXml.setSort("");
            pageableXml.setDir("ASC");
        }

        return pageableXml;
    }
}
