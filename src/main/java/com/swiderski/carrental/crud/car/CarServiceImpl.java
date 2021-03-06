package com.swiderski.carrental.crud.car;


import com.swiderski.carrental.crud.abstraction.AbstractService;
import com.swiderski.carrental.crud.specification.SearchCriteria;
import com.swiderski.carrental.crud.specification.SpecificationBuilder_;
import com.swiderski.carrental.mail.MailSenderConfigurer;
import com.swiderski.carrental.mail.MailServiceImpl;
import com.swiderski.carrental.pdfGenerator.service.PdfService;
import com.swiderski.carrental.xlsxGenerator.XlsxGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.swiderski.carrental.crud.specification.SearchOperation.*;

@Service
public class CarServiceImpl extends AbstractService<Car, CarDto, CarParam> implements CarService {

    private final MailServiceImpl mailService;
    private final EntityManager entityManager;
    private final CarRepository carRepository;
    private final PdfService pdfService;

    public CarServiceImpl(CarMapper carMapper, CarRepository commonRepository, MailServiceImpl mailService, EntityManager entityManager, PdfService pdfService) {
        super(carMapper, commonRepository);
        this.mailService = mailService;
        this.entityManager = entityManager;
        this.carRepository = commonRepository;
        this.pdfService = pdfService;
    }

    @Override
    public Page<CarDto> getAll(CarParam carParam, Pageable pageable) {
        SpecificationBuilder_<Car> specificationBuilder = new SpecificationBuilder_<>();

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


    @Async("asyncExecutor")
    @Override
    public CompletableFuture<String> sendPdfEmail(MailSenderConfigurer mailSenderConfigurer, CarParam carParam) {
        return CompletableFuture.supplyAsync(() -> {
            byte[] pdfReport = getPdfReport(carParam);
            mailSenderConfigurer.setFile(pdfReport);
            mailService.sendEmailWithAttachment(mailSenderConfigurer);
            return null;
        }).handle((ok, ex) -> (ok == null) ? "Email not send" : "Email is send");
    }

    @Override
    public byte[] getPdfReport(CarParam param) {
        Page<CarDto> all = getAll(param, PageRequest.of(0, 50, Sort.by("id")));
        return pdfService.generatePdf(all.getContent());
    }

    @Override
    public byte[] getXlsxReport(CarParam param) {
        Page<CarDto> all = getAll(param, PageRequest.of(0, 50, Sort.by("id")));
        return XlsxGenerator.build(all.getContent());
    }
}
