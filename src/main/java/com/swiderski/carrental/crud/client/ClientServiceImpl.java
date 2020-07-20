package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.abstraction.AbstractService;
import com.swiderski.carrental.crud.abstraction.CommonMapper;
import com.swiderski.carrental.crud.abstraction.CommonRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends AbstractService<Client, ClientDto> implements ClientService {

    public ClientServiceImpl(CommonMapper<Client, ClientDto> commonMapper, CommonRepository<Client> commonRepository) {
        super(commonMapper, commonRepository);
    }
}
