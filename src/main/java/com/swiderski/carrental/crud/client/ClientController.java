package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.crud.abstraction.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}/clients"})
public class ClientController extends AbstractController<ClientService, ClientDto, ClientParam> {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService service, ClientService clientService) {
        super(service);
        this.clientService = clientService;
    }

    @GetMapping()
    @ApiPageable
    public Page<ClientDto> getAll(@Valid @ModelAttribute ClientParam clientParam,
                                  @ApiIgnore @NonNull Pageable pageable) {
        return clientService.getAll(clientParam, pageable);
    }

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdf(@ModelAttribute ClientParam clientParam) {
        byte[] pdfReport = clientService.getPdfReport(clientParam);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"clients" + LocalDateTime.now() + ".pdf\"")
                .body(pdfReport);
    }

}
