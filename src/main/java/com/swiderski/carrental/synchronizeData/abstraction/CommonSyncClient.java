package com.swiderski.carrental.synchronizeData.abstraction;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.carrental.sendDataToSync.SyncRevisionData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface CommonSyncClient<E extends AbstractDto> {

    @GetMapping(path = "/getRevisionData")
    SyncRevisionData<E> getRevisionData(@RequestParam Integer fromRevision);

}
