package com.swiderski.carrental.crud.rental;

import com.swiderski.carrental.crud.client.Client;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public final class RentalSpecification {

    private RentalSpecification() {
    }

    public static Specification<Client> hasName(String name) {
        return likePrediction(name, "name");
    }

    public static Specification<Client> hasSurname(String surname) {
        return likePrediction(surname, "surname");
    }

    public static Specification<Client> hasEmail(String email) {
        return likePrediction(email, "email");
    }

    public static Specification<Client> hasCity(String city) {
        return likePrediction(city, "city");
    }

    public static Specification<Client> hasStreet(String street) {
        return likePrediction(street, "street");
    }

    public static Specification<Client> hasZipCode(String zipCode) {
        return likePrediction(zipCode, "zipCode");
    }

    public static Specification<Client> hasPhone(String phone) {
        return likePrediction(phone, "phone");
    }

    @NotNull
    private static Specification<Client> likePrediction(String value, String field) {
        if (value == null) {
            return (root, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (Root<Client> root, CriteriaQuery<?> cq, CriteriaBuilder cb)
                -> cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }
}
