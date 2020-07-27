package com.swiderski.carrental.crud.rental;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public final class RentalSpecification {

    private RentalSpecification() {
    }

    public static Specification<Rental> rentedFromDate(LocalDate rentalFrom) {
        if (rentalFrom == null) {
            return (root, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return ((root, cq, cb) ->
                cb.greaterThanOrEqualTo(root.get("rentalBegin").as(LocalDate.class), rentalFrom));
    }

    public static Specification<Rental> rentedToDate(LocalDate rentalTo) {
        if (rentalTo == null) {
            return (root, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return ((root, cq, cb) ->
                cb.greaterThanOrEqualTo(root.get("rentalEnd").as(LocalDate.class), rentalTo));
    }

    public static Specification<Rental> hasCarModel(String brand) {
        if (brand == null) {
            return (root, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (root, cq, cb) -> cb.like(cb.lower(root.join("car").get("brand")), "%" + brand.toLowerCase() + "%");
    }


    @NotNull
    private static Specification<Rental> likePrediction(String value, String field) {
        if (value == null) {
            return (root, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (Root<Rental> root, CriteriaQuery<?> cq, CriteriaBuilder cb)
                -> cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }
}
