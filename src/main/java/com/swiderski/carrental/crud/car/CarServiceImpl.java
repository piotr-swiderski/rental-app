package com.swiderski.carrental.crud.car;


import com.swiderski.carrental.crud.abstraction.AbstractService;
import com.swiderski.carrental.crud.specification.SearchCriteria;
import com.swiderski.carrental.crud.specification.SpecificationBuilder;
import com.swiderski.carrental.pdfGenerator.PdfGeneratorImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.swiderski.carrental.crud.specification.SearchOperation.*;

@Service
public class CarServiceImpl extends AbstractService<Car, CarDto, CarParam> implements CarService {

    public CarServiceImpl(CarMapper carMapper, CarRepository commonRepository) {
        super(carMapper, commonRepository);
    }

    @Override
    public Page<CarDto> getAll(CarParam carParam, Pageable pageable) {
        SpecificationBuilder<Car> specificationBuilder = new SpecificationBuilder<>();

        specificationBuilder
                .add(new SearchCriteria(Car_.BRAND, carParam.getBrand(), MATCH))
                .add(new SearchCriteria(Car_.MODEL, carParam.getModel(), MATCH))
                .add(new SearchCriteria(Car_.COLOUR, carParam.getColour(), MATCH))
                .add(new SearchCriteria(Car_.PRODUCTION_YEAR, carParam.getYearFrom(), GREATER_THAN_EQUAL))
                .add(new SearchCriteria(Car_.PRODUCTION_YEAR, carParam.getYearTo(), LESS_THAN_EQUAL))
                .add(new SearchCriteria(Car_.ENGINE_TYPE, carParam.getEngineType(), EQUAL));

        Page<Car> carPage = commonRepository.findAll(specificationBuilder, pageable);
        List<CarDto> pageList = commonMapper.toListDto(carPage.getContent());
        return new PageImpl<>(pageList, pageable, carPage.getTotalElements());
    }

    @Override
    public byte[] getPdfReport(CarParam carParam) {
        Page<CarDto> all = getAll(carParam, Pageable.unpaged());
        return PdfGeneratorImpl.build(all.getContent());
    }
}
