package com.swiderski.carrental.client;

import com.swiderski.carrental.abstraction.CommonMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ClientMapper extends CommonMapper<Client, ClientDto> {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Collection<ClientDto> clientCollectionToClientDtoCollection(Collection<Client> clients);
}
