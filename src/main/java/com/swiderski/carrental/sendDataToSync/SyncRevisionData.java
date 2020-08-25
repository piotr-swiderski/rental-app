package com.swiderski.carrental.sendDataToSync;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SyncRevisionData<E extends AbstractDto> {

    List<E> updatedData;
    List<Long> deletedData;
    Integer lastRevision;
}
