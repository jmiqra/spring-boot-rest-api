package com.asraf.persistence.repositories;

import com.asraf.util.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.ArrayList;
import java.util.List;

public final class UserPredicatesBuilder {
    private final List<SearchCriteria> params;

    public UserPredicatesBuilder() {
        params = new ArrayList<>();
    }

    public UserPredicatesBuilder with(final String key, final String operation, final Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.size() == 0) {
            return null;
        }

        final List<BooleanExpression> predicates = new ArrayList<>();
        UserPredicate predicate;
        for (final SearchCriteria param : params) {
            predicate = new UserPredicate(param);
            final BooleanExpression exp = predicate.getPredicate();
            if (exp != null) {
                predicates.add(exp);
            }
        }

        BooleanExpression result = predicates.get(0);
        for (int i = 1; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }
        return result;
    }
}