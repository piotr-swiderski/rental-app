package com.swiderski.carrental.soap;

import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientParam;
import com.swiderski.rental_service.schema.client.ClientData;
import com.swiderski.rental_service.schema.client.ClientFilter;
import com.swiderski.rental_service.schema.client.ClientPageable;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientWebMapper {

    ClientData toClientData(ClientDto clientDto);

    ClientDto toClientDto(ClientData clientData);

    List<ClientData> toClientDataList(List<ClientDto> clientDtos);

    ClientParam toClientParam(ClientFilter clientFilter);

    default ClientPageable toWebPageable(Page<ClientDto> page) {
        ClientPageable wsPage = new ClientPageable();

        wsPage.setEmpty(page.isEmpty());
        wsPage.setFirst(page.isFirst());
        wsPage.setTotalPages(page.getTotalPages());
        wsPage.setTotalElements(page.getTotalElements());
        wsPage.setNumberOfElements(page.getNumberOfElements());
        wsPage.setSize(page.getSize());
        wsPage.setNumber(page.getNumber());
        wsPage.getContent().addAll(toClientDataList(page.getContent()));

        return wsPage;
    }
}

