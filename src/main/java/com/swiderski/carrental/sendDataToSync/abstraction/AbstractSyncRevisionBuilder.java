package com.swiderski.carrental.sendDataToSync.abstraction;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.carrental.crud.abstraction.AbstractEntity;
import com.swiderski.carrental.crud.abstraction.CommonMapper;
import com.swiderski.carrental.sendDataToSync.SyncRevisionData;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractSyncRevisionBuilder<E extends AbstractEntity, D extends AbstractDto> {

    private final EntityManager entityManager;
    private final CommonMapper<E, D> commonMapper;

    public AbstractSyncRevisionBuilder(EntityManager entityManager, CommonMapper<E, D> commonMapper) {
        this.entityManager = entityManager;
        this.commonMapper = commonMapper;
    }

    public SyncRevisionData<D> getSyncRevisionData(Integer fromRevision, Class<?> clazz) {
        SyncRevisionData<D> syncRevisionData = new SyncRevisionData<>();

        List<Long> deletedData = getLastedDeletedByRevision(fromRevision, clazz);
        List<E> updatedData = getLastedUpdatedByRevision(fromRevision, clazz);

        updatedData.removeIf(u -> deletedData.contains(u.id));

        syncRevisionData.setDeletedData(deletedData);
        syncRevisionData.setUpdatedData(commonMapper.toListDto(updatedData));
        syncRevisionData.setLastRevision(getLastRevision(clazz));

        return syncRevisionData;
    }

    private List<E> getLastedUpdatedByRevision(Integer fromRevision, Class<?> clazz) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        List updatedList = auditReader.createQuery().forRevisionsOfEntity(clazz, true, false)
                .add(AuditEntity.revisionNumber().gt(fromRevision))
                .add(AuditEntity.revisionType().eq(RevisionType.ADD))
                .add(AuditEntity.revisionType().eq(RevisionType.MOD))
                .addOrder(AuditEntity.revisionNumber().asc())
                .getResultList();

        return updatedList;
    }

    private List<Long> getLastedDeletedByRevision(Integer fromRevision, Class<?> clazz) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        List<Long> deletedList = auditReader
                .createQuery().forRevisionsOfEntity(clazz, false, true)
                .add(AuditEntity.revisionType().eq(RevisionType.DEL))
                .add(AuditEntity.revisionNumber().gt(fromRevision))
                .addProjection(AuditEntity.id())
                .addOrder(AuditEntity.revisionNumber().asc())
                .getResultList();

        return deletedList;
    }

    private Integer getLastRevision(Class<?> clazz) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return (int) auditReader
                .createQuery().forRevisionsOfEntity(clazz, false, true)
                .add(AuditEntity.revisionNumber().maximize())
                .addProjection(AuditEntity.revisionNumber())
                .getSingleResult();
    }
}
