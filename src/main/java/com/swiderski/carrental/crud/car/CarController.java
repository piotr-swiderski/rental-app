package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.AbstractController;
import com.swiderski.carrental.mail.MailSenderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}/cars"})
public class CarController extends AbstractController<CarService, CarDto, CarParam> {

    private final CarService carService;


    @Autowired
    public CarController(CarService service) {
        super(service);
        this.carService = service;
    }

    @GetMapping("/mail")
    public String sendMail(@ModelAttribute MailSenderConfigurer mailSenderConfigurer, CarParam carParam) {
        carService.sendPdfEmail(mailSenderConfigurer, carParam);
        return "Send mail";
    }

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @PreAuthorize("hasAuthority('read_profile')")
    public ResponseEntity<byte[]> getPdf(@ModelAttribute CarParam param) {
        byte[] pdfReport = carService.getPdfReport(param);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report" + LocalDateTime.now() + ".pdf\"")
                .body(pdfReport);
    }

}
