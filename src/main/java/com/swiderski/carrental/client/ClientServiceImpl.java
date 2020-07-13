package com.swiderski.carrental.client;

import com.swiderski.carrental.abstraction.AbstractService;
import com.swiderski.carrental.abstraction.CommonMapper;
import com.swiderski.carrental.abstraction.CommonRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends AbstractService<Client, ClientDto> implements ClientService {

    public ClientServiceImpl(CommonMapper<Client, ClientDto> commonMapper, CommonRepository<Client> commonRepository) {
        super(commonMapper, commonRepository);
    }
}
