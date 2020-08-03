package com.swiderski.carrental.soap;

import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.rental_service.schema.client.ClientData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientWebMapper {

    ClientData toClientData(ClientDto clientDto);

    ClientDto toClientDto(ClientData clientData);

    List<ClientData> toClientDataList(List<ClientDto> clientDtos);
}

