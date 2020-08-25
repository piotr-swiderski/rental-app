package com.swiderski.carrental.synchronizeData.revisionInfo;

import com.swiderski.carrental.crud.abstraction.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "revision_info")
public class RevisionInfo extends AbstractEntity {

    @Column(name = "last_revision")
    private Integer lastRevision;

}
