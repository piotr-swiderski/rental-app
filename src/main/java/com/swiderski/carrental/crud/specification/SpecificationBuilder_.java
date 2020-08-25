package com.swiderski.carrental.crud.specification;

import com.swiderski.carrental.crud.abstraction.AbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder_<E extends AbstractEntity> implements Specification<E> {
    private final transient List<SearchCriteria> criteriaList;

    public SpecificationBuilder_() {
        this.criteriaList = new ArrayList<>();
    }

    public SpecificationBuilder_<E> add(SearchCriteria criteria) {
        criteriaList.add(criteria);
        return this;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<E> root, @NotNull CriteriaQuery<?> cq, @NotNull CriteriaBuilder cb) {
        List<Predicate> predicates = SpecificationFactory.getFromCriteriaList(root, cb, criteriaList);
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
