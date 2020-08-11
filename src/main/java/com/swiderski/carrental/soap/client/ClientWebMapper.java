package com.swiderski.carrental.soap.client;

import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientParam;
import com.swiderski.carrental.soap.abstraction.CommonWebMapper;
import com.swiderski.rental_service.schema.client.ClientData;
import com.swiderski.rental_service.schema.client.ClientFilter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientWebMapper extends CommonWebMapper<ClientDto, ClientData> {

    ClientParam toClientParam(ClientFilter clientFilter);

    ClientFilter toClientFiler(ClientParam clientParam);
}

