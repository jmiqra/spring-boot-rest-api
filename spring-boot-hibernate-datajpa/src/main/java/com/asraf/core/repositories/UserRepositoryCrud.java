package com.asraf.core.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.QUser;
import com.asraf.core.entities.User;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

@Transactional
public interface UserRepositoryCrud extends PagingAndSortingRepository<User, Long>, QuerydslPredicateExecutor<User>,
		QuerydslBinderCustomizer<QUser>, JpaSpecificationExecutor<User> {

	@Override
	default public void customize(QuerydslBindings bindings, QUser root) {
		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
		bindings.excluding(root.email);
	}

	/**
	 * Retrieves an user by its email.
	 * 
	 * @param email
	 * @return The user having the passed email or null if no user is found
	 */
	public User findByEmail(String email);

	@Query("select u from User u where u.name like %?1% order by name")
	List<User> findByNameContains(String name);

	List<User> findByNameOrEmail(String name, String email);

	// Slice<User> findAll(Pageable pageRequest);
	Page<User> findByNameContainsOrEmailContainsAllIgnoreCase(String name, String email, Pageable pageRequest);

	// @Query("SELECT t FROM Todo t WHERE " + "LOWER(t.title) LIKE
	// LOWER(CONCAT('%',:searchTerm, '%')) OR "
	// + "LOWER(t.description) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
	// List<Todo> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable
	// pageRequest);

}
