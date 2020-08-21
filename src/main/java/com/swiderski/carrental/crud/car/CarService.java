package com.swiderski.carrental.crud.car;

import com.swiderski.carrental.crud.abstraction.CommonService;
import com.swiderski.carrental.crud.abstraction.EmailSenderService;
import com.swiderski.carrental.mail.MailSenderConfigurer;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface CarService extends CommonService<CarDto, CarParam>, EmailSenderService<CarParam> {
    @Async
    CompletableFuture<String> sendPdfEmail(MailSenderConfigurer mailSenderConfigurer, CarParam carParam);
}
