package com.swiderski.carrental.crud.car;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public final class CarSpecification {

    private CarSpecification() {
    }

    public static Specification<Car> hasBrand(String brand) {
        return likePrediction(brand, "brand");
    }

    public static Specification<Car> hasColour(String colour) {
        return likePrediction(colour, "colour");
    }

    public static Specification<Car> hasModel(String model) {
        return likePrediction(model, "model");
    }

    @NotNull
    public static Specification<Car> hasEngineType(EngineType engineType) {
        if (engineType == null) {
            return (root, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (Root<Car> root, CriteriaQuery<?> cq, CriteriaBuilder cb) ->
                cb.greaterThanOrEqualTo(root.get("engineType").as(EngineType.class), engineType);
    }

    @NotNull
    public static Specification<Car> yearFrom(Integer yearFrom) {
        if (yearFrom == null) {
            return (root, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (Root<Car> root, CriteriaQuery<?> cq, CriteriaBuilder cb) ->
                cb.greaterThanOrEqualTo(root.get("productionYear").as(Integer.class), yearFrom);
    }

    @NotNull
    public static Specification<Car> yearTo(Integer yearTo) {
        if (yearTo == null) {
            return (root, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (Root<Car> root, CriteriaQuery<?> cq, CriteriaBuilder cb) ->
                cb.lessThanOrEqualTo(root.get("productionYear").as(Integer.class), yearTo);
    }

    @NotNull
    private static Specification<Car> likePrediction(String value, String field) {
        if (value == null) {
            return (root, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (Root<Car> root, CriteriaQuery<?> cq, CriteriaBuilder cb)
                -> cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }

}
