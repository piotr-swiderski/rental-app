package com.swiderski.carrental.crud.specification;


import com.swiderski.carrental.crud.abstraction.AbstractEntity;
import lombok.extern.log4j.Log4j2;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.swiderski.carrental.crud.specification.SearchOperation.*;

@Log4j2
public class SpecificationFactory {


    public static <E extends AbstractEntity> List<Predicate> getFromCriteriaList(Root<E> root, CriteriaBuilder cb, List<SearchCriteria> criteriaList) {
        return criteriaList.stream().map(c -> get(root, cb, c)).collect(Collectors.toList());
    }

    private static Predicate get(Root<?> root, CriteriaBuilder cb, SearchCriteria searchCriteria) {
        if (searchCriteria.getValue() == null) {
            return noPropertyExist(root, cb);
        }

        HashMap<SearchOperation, Predicate> mapFactory = getPredicateMap(root, cb, searchCriteria);

        Predicate predicate = mapFactory.get(searchCriteria.getOperation());
        if (predicate == null) {
            return noPropertyExist(root, cb);
        }
        return predicate;
    }

    private static HashMap<SearchOperation, Predicate> getPredicateMap(Root<?> root, CriteriaBuilder cb, SearchCriteria searchCriteria) {
        HashMap<SearchOperation, Predicate> predicateFactory = new HashMap<>();
        predicateFactory.put(GREATER_THAN_EQUAL, greaterThanEqual(root, cb, searchCriteria.getKey(), searchCriteria.getValue()));
        predicateFactory.put(GREATER_THAN, greaterThan(root, cb, searchCriteria.getKey(), searchCriteria.getValue()));
        predicateFactory.put(LESS_THAN, lessThan(root, cb, searchCriteria.getKey(), searchCriteria.getValue()));
        predicateFactory.put(LESS_THAN_EQUAL, lessThanEqual(root, cb, searchCriteria.getKey(), searchCriteria.getValue()));
        predicateFactory.put(NOT_EQUAL, notEqual(root, cb, searchCriteria.getKey(), searchCriteria.getValue()));
        predicateFactory.put(NOT_IN, notIn(root, cb, searchCriteria.getKey(), searchCriteria.getValue()));
        predicateFactory.put(IN, isIn(root, cb, searchCriteria.getKey(), searchCriteria.getValue()));
        predicateFactory.put(MATCH, match(root, cb, searchCriteria.getKey(), searchCriteria.getValue()));
        predicateFactory.put(MATCH_END, matchEnd(root, cb, searchCriteria.getKey(), searchCriteria.getValue()));
        predicateFactory.put(MATCH_START, matchStart(root, cb, searchCriteria.getKey(), searchCriteria.getValue()));
        return predicateFactory;

    }

    private static Predicate greaterThanEqual(Root<?> root, CriteriaBuilder cb, String key, Object value) {
        return cb.greaterThanOrEqualTo(root.get(key).as(key.getClass()), value.toString());
    }

    private static Predicate greaterThan(Root<?> root, CriteriaBuilder cb, String key, Object value) {
        return cb.greaterThan(root.get(key).as(key.getClass()), value.toString());
    }

    private static Predicate noPropertyExist(Root<?> root, CriteriaBuilder cb) {
        log.info("Can't resolve operation");
        return cb.isTrue(cb.literal(true));
    }

    private static Predicate lessThan(Root<?> root, CriteriaBuilder cb, String key, Object value) {
        return cb.lessThan(root.get(key).as(key.getClass()), value.toString());
    }

    private static Predicate lessThanEqual(Root<?> root, CriteriaBuilder cb, String key, Object value) {
        return cb.lessThanOrEqualTo(root.get(key).as(key.getClass()), value.toString());
    }

    private static Predicate notEqual(Root<?> root, CriteriaBuilder cb, String key, Object value) {
        return cb.notEqual(root.get(key).as(key.getClass()), value);
    }

    private static Predicate equal(Root<?> root, CriteriaBuilder cb, String key, Object value) {
        return cb.equal(root.get(key).as(key.getClass()), value);
    }

    private static Predicate match(Root<?> root, CriteriaBuilder cb, String key, Object value) {
        return cb.like(cb.lower(root.get(key)), "%" + value.toString().toLowerCase() + "%");
    }

    private static Predicate matchEnd(Root<?> root, CriteriaBuilder cb, String key, Object value) {
        return cb.like(cb.lower(root.get(key)), value.toString().toLowerCase() + "%");
    }

    private static Predicate matchStart(Root<?> root, CriteriaBuilder cb, String key, Object value) {
        return cb.like(cb.lower(root.get(key)), "%" + value.toString().toLowerCase());
    }

    private static Predicate isIn(Root<?> root, CriteriaBuilder cb, String key, Object value) {
        return cb.in(root.get(key)).value(value);
    }

    private static Predicate notIn(Root<?> root, CriteriaBuilder cb, String key, Object value) {
        return cb.not(root.get(key)).in(value);
    }


}
