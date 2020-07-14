package com.swiderski.carrental.client;

import com.swiderski.carrental.abstraction.CommonMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper extends CommonMapper<Client, ClientDto> {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
}
