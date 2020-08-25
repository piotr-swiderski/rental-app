package com.swiderski.carrental.sendDataToSync.abstraction;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.carrental.sendDataToSync.SyncRevisionData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class AbstractShareRevisionController<E extends AbstractDto> {

    private final CommonShareRevisionDataService<E> shareRevisionService;

    public AbstractShareRevisionController(CommonShareRevisionDataService<E> shareRevisionService) {
        this.shareRevisionService = shareRevisionService;
    }

    @GetMapping("/getRevisionData")
    public SyncRevisionData<E> getSyncRevisionData(@RequestParam Integer fromRevision) {
        return shareRevisionService.getLastedByRevision(fromRevision);
    }
}
