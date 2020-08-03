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

import static com.swiderski.carrental.crud.specification.SearchOperation.*;

public class SpecificationBuilder<E extends AbstractEntity> implements Specification<E> {
    private final transient List<SearchCriteria> criteriaList;

    public SpecificationBuilder() {
        this.criteriaList = new ArrayList<>();
    }

    public SpecificationBuilder<E> add(SearchCriteria criteria) {
        criteriaList.add(criteria);
        return this;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<E> root, @NotNull CriteriaQuery<?> cq, @NotNull CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : criteriaList) {
            if (criteria.getValue() == null) {
                predicates.add(cb.isTrue(cb.literal(true)));
            } else if (criteria.getOperation().equals(GREATER_THAN)) {
                predicates.add(cb.greaterThan(root.get(criteria.getKey()).as(criteria.getKey().getClass()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(LESS_THAN)) {
                predicates.add(cb.lessThan(root.get(criteria.getKey()).as(criteria.getKey().getClass()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(GREATER_THAN_EQUAL)) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(criteria.getKey()).as(criteria.getKey().getClass()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(LESS_THAN_EQUAL)) {
                predicates.add(cb.lessThanOrEqualTo(root.get(criteria.getKey()).as(criteria.getKey().getClass()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(NOT_EQUAL)) {
                predicates.add(cb.notEqual(root.get(criteria.getKey()).as(criteria.getKey().getClass()), criteria.getValue()));
            } else if (criteria.getOperation().equals(EQUAL)) {
                predicates.add(cb.equal(root.get(criteria.getKey()).as(criteria.getKey().getClass()), criteria.getValue()));
            } else if (criteria.getOperation().equals(MATCH)) {
                predicates.add(cb.like(cb.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(MATCH_END)) {
                predicates.add(cb.like(cb.lower(root.get(criteria.getKey())), criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(MATCH_START)) {
                predicates.add(cb.like(cb.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase()));
            } else if (criteria.getOperation().equals(IN)) {
                predicates.add(cb.in(root.get(criteria.getKey())).value(criteria.getValue()));
            } else if (criteria.getOperation().equals(NOT_IN)) {
                predicates.add(cb.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
