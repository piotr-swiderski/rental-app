package com.swiderski.carrental.mapper;

import com.swiderski.carrental.dto.ClientDto;
import com.swiderski.carrental.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto clientToClientDto(Client client);

    Client clientDtoDtoClient(ClientDto clientDto);

    Set<ClientDto> clientSetToClientDtoSet(Set<Client> clientSet);

    Collection<ClientDto> clientCollectionToClientDtoCollection(Collection<Client> clients);

    List<ClientDto> clientListToClientDtoList(List<Client> clients);
}
