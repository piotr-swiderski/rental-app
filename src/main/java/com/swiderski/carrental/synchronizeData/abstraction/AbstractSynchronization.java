package com.swiderski.carrental.synchronizeData.abstraction;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.carrental.crud.abstraction.AbstractEntity;
import com.swiderski.carrental.crud.abstraction.CommonMapper;
import com.swiderski.carrental.crud.abstraction.CommonRepository;
import com.swiderski.carrental.sendDataToSync.SyncRevisionData;
import com.swiderski.carrental.synchronizeData.revisionInfo.RevisionInfo;
import com.swiderski.carrental.synchronizeData.revisionInfo.RevisionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
public abstract class AbstractSynchronization<E extends AbstractEntity, D extends AbstractDto, T extends CommonSyncClient<D>> {

    private final T commonSyncClient;
    protected final CommonMapper<E, D> commonMapper;
    private final RevisionRepository revisionRepository;
    private final CommonRepository<E> repository;


    public AbstractSynchronization(T commonSyncClient,
                                   CommonMapper<E, D> commonMapper,
                                   RevisionRepository revisionRepository,
                                   CommonRepository<E> commonRepository) {
        this.commonSyncClient = commonSyncClient;
        this.commonMapper = commonMapper;
        this.revisionRepository = revisionRepository;
        this.repository = commonRepository;
    }


    @Scheduled(fixedDelay = 10000)
    @Async
    @Transactional
    public void synchronization() {

        log.info("Start sync");
        SyncRevisionData<D> revisionData = getRevisionDataToUpdate();

        setLastRevision(revisionData.getLastRevision());

        List<E> savedList = commonMapper.fromListDto(revisionData.getUpdatedData());

        repository.saveAll(savedList);
        revisionData.getDeletedData().forEach(repository::deleteById);
        log.info("End sync");

    }

    private SyncRevisionData<D> getRevisionDataToUpdate() {
        int lastRevision = getLastRevision();
        return commonSyncClient.getRevisionData(lastRevision);
    }

    private int getLastRevision() {
        return revisionRepository.getLastRevisionNumber().getLastRevision();
    }

    private void setLastRevision(Integer lastRevision) {
        RevisionInfo revisionInfo = new RevisionInfo();
        revisionInfo.setLastRevision(lastRevision);
        revisionRepository.save(revisionInfo);
    }

}
