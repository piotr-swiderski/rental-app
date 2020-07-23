package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.abstraction.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.swiderski.carrental.crud.client.ClientSpecification.*;

@RestController
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}/clients"})
public class ClientController extends AbstractController<ClientService, ClientDto> {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService service, ClientService clientService) {
        super(service);
        this.clientService = clientService;
    }

    @GetMapping()
    public Page<ClientDto> getAll(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String surname,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) String city,
                                  @RequestParam(required = false) String street,
                                  @RequestParam(required = false) String zipCode,
                                  @RequestParam(required = false) String phone,
                                  @PageableDefault Pageable pageable) {
        Specification<Client> specification = Specification
                .where(hasName(name)
                        .and(hasSurname(surname))
                        .and(hasEmail(email))
                        .and(hasCity(city))
                        .and(hasStreet(street))
                        .and(hasZipCode(zipCode))
                        .and(hasPhone(phone))
                );

        return clientService.getAll(specification, pageable);
    }
}
