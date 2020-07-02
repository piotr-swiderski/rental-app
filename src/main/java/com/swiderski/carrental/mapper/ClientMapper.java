package com.swiderski.carrental.mapper;

import com.swiderski.carrental.dto.ClientDto;
import com.swiderski.carrental.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    ClientDto clientToClientDto(Client client);
    Client clientDtoDtoClient(ClientDto clientDto);
}
