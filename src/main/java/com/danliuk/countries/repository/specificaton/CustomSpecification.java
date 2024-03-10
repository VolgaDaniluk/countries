package com.danliuk.countries.repository.specificaton;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public interface CustomSpecification<T> {

    default Specification<T> like(String key, String value) {
        return (root, query, builder) -> builder.like(root.get(key), "%" + value + "%");
    }

    default Specification<T> lessThen(String key, String value) {
        return (root, query, builder) -> builder.lessThan(root.get(key), value);
    }

    default Specification<T> greaterThen(String key, String value) {
        return (root, query, builder) -> builder.greaterThan(root.get(key), value);
    }

    default Specification<T> get(SearchCriteria criteria) {
        return switch (criteria.getOperation()) {
            case LIKE -> this.like(criteria.getKey(), criteria.getValue());
            case LESS_THEN -> this.lessThen(criteria.getKey(), criteria.getValue());
            case GREATER_THEN -> this.greaterThen(criteria.getKey(), criteria.getValue());
        };
    }

    default Specification<T> build(List<SearchCriteria> criteriaList) {

        if (criteriaList.isEmpty()) {
            return null;
        }

        List<Specification<T>> specifications = criteriaList.stream()
                .map(this::get)
                .toList();

        Specification<T> specification = Specification.where(specifications.get(0));

        for (int i = 1; i < specifications.size(); i++) {
            specification.and(specifications.get(i));
        }

        return specification;
    }
}
