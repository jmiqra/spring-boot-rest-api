package com.asraf.repositories;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.asraf.entities.BaseEntity;
import com.querydsl.jpa.JPQLQuery;

@NoRepositoryBean
public interface ExtendedQueryDslJpaRepository<TEntity extends BaseEntity, ID extends Serializable>
		extends JpaRepository<TEntity, ID>, QuerydslPredicateExecutor<TEntity> {

	<T1> Page<T1> findAll(JPQLQuery<?> jpqlQuery, Pageable pageable);
}