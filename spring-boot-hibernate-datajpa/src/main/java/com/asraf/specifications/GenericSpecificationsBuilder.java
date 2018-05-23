package com.asraf.specifications;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.asraf.entities.BaseEntity;

@SuppressWarnings("deprecation")
public class GenericSpecificationsBuilder<TEntity extends BaseEntity> {

    private final List<SpecSearchCriteria> params;

    public GenericSpecificationsBuilder() {
        this.params = new ArrayList<>();
    }

    public final GenericSpecificationsBuilder<TEntity> with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final GenericSpecificationsBuilder<TEntity> with(final String precedenceIndicator, final String key, final String operation, final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) // the operation may be complex operation
            {
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(precedenceIndicator, key, op, value));
        }
        return this;
    }

    public Specification<TEntity> build(Function<SpecSearchCriteria, Specification<TEntity>> converter) {

        if (params.size() == 0) {
            return null;
        }

        final List<Specification<TEntity>> specs = params.stream()
            .map(converter)
            .collect(Collectors.toCollection(ArrayList::new));

        Specification<TEntity> result = specs.get(0);

        for (int idx = 1; idx < specs.size(); idx++) {
            result = params.get(idx)
                .isOrPredicate()
                    ? Specifications.where(result)
                        .or(specs.get(idx))
                    : Specifications.where(result)
                        .and(specs.get(idx));
        }
        return result;
    }

    public Specification<TEntity> build(Deque<?> postFixedExprStack, Function<SpecSearchCriteria, Specification<TEntity>> converter) {

        Deque<Specification<TEntity>> specStack = new LinkedList<>();

        Collections.reverse((List<?>) postFixedExprStack);

        while (!postFixedExprStack.isEmpty()) {
            Object mayBeOperand = postFixedExprStack.pop();

            if (!(mayBeOperand instanceof String)) {
                specStack.push(converter.apply((SpecSearchCriteria) mayBeOperand));
            } else {
                Specification<TEntity> operand1 = specStack.pop();
                Specification<TEntity> operand2 = specStack.pop();
                if (mayBeOperand.equals(SearchOperation.AND_OPERATOR))
                    specStack.push(Specifications.where(operand1)
                        .and(operand2));
                else if (mayBeOperand.equals(SearchOperation.OR_OPERATOR))
                    specStack.push(Specifications.where(operand1)
                        .or(operand2));
            }

        }
        return specStack.pop();

    }

}
