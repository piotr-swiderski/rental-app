package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.abstraction.AbstractService;
import com.swiderski.carrental.crud.abstraction.CommonMapper;
import com.swiderski.carrental.crud.abstraction.CommonRepository;
import com.swiderski.carrental.crud.specification.SearchCriteria;
import com.swiderski.carrental.crud.specification.SpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.swiderski.carrental.crud.specification.SearchOperation.EQUAL;
import static com.swiderski.carrental.crud.specification.SearchOperation.MATCH;

@Service
public class ClientServiceImpl extends AbstractService<Client, ClientDto> implements ClientService {

    public ClientServiceImpl(CommonMapper<Client, ClientDto> commonMapper, CommonRepository<Client> commonRepository) {
        super(commonMapper, commonRepository);
    }

    @Override
    public Page<ClientDto> getAll(ClientParam clientParam, Pageable pageable) {

        SpecificationBuilder<Client> specificationBuilder = new SpecificationBuilder<>();
        specificationBuilder
                .add(new SearchCriteria(Client_.CITY, clientParam.getCity(), MATCH))
                .add(new SearchCriteria(Client_.EMAIL, clientParam.getEmail(), MATCH))
                .add(new SearchCriteria(Client_.NAME, clientParam.getName(), MATCH))
                .add(new SearchCriteria(Client_.PHONE, clientParam.getPhone(), MATCH))
                .add(new SearchCriteria(Client_.STREET, clientParam.getStreet(), MATCH))
                .add(new SearchCriteria(Client_.SURNAME, clientParam.getSurname(), MATCH))
                .add(new SearchCriteria(Client_.ZIP_CODE, clientParam.getZipCode(), EQUAL));

        Page<Client> clientPage = commonRepository.findAll(specificationBuilder, pageable);
        List<ClientDto> clientDtos = commonMapper.toListDto(clientPage.getContent());
        return new PageImpl<>(clientDtos, pageable, clientPage.getTotalElements());
    }

}
