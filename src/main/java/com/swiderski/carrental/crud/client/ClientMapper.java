package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.abstraction.CommonMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper extends CommonMapper<Client, ClientDto> {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
}
