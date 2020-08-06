package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.abstraction.CommonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService extends CommonService<ClientDto> {

    Page<ClientDto> getAll(ClientParam clientParam, Pageable pageable);

    Client getEntity(long id);

}
