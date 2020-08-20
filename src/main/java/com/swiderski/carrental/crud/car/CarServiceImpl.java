package com.swiderski.carrental.crud.car;


import com.swiderski.carrental.crud.abstraction.AbstractService;
import com.swiderski.carrental.crud.specification.SearchCriteria;
import com.swiderski.carrental.crud.specification.SpecificationBuilder;
import com.swiderski.carrental.mail.MailSenderConfigurer;
import com.swiderski.carrental.mail.MailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.swiderski.carrental.crud.specification.SearchOperation.*;

@Service
public class CarServiceImpl extends AbstractService<Car, CarDto, CarParam> implements CarService {

    private final MailService mailService;

    public CarServiceImpl(CarMapper carMapper, CarRepository commonRepository, MailService mailService) {
        super(carMapper, commonRepository);
        this.mailService = mailService;
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


    public void sendPdfEmail(MailSenderConfigurer mailSenderConfigurer, CarParam carParam) {
        byte[] pdfReport = getPdfReport(carParam);
        mailSenderConfigurer.setFile(pdfReport);
        mailService.sendEmailWithAttachment(mailSenderConfigurer);
        System.out.println("das");
    }
}
