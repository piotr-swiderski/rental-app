package com.swiderski.carrental.synchronizeData.revisionInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RevisionRepository extends JpaRepository<RevisionInfo, Long> {

    @Query(value = "select * from revision_info as r order by r.id  desc limit(1) ", nativeQuery = true)
    RevisionInfo getLastRevisionNumber();
}
