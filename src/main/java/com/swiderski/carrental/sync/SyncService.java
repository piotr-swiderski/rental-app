package com.swiderski.carrental.sync;

import com.swiderski.carrental.crud.abstraction.AbstractDto;

import java.time.LocalDateTime;
import java.util.List;

public interface SyncService<E extends AbstractDto> {

    List<E> getLastedByDateModified(LocalDateTime modifiedDate);


}
