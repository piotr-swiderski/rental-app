package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.CommonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService extends CommonService<CarDto, CarParam> {

//    Page<CarDto> getAll(CarParam carParam, Pageable pageable);
//
//    byte[] getPdfReport(CarParam carParam);
}
