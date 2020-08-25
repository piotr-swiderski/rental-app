package com.swiderski.carrental.sendDataToSync.abstraction;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.carrental.sendDataToSync.SyncRevisionData;

public interface CommonShareRevisionDataService<E extends AbstractDto> {

    SyncRevisionData<E> getLastedByRevision(Integer fromRevision);
}
