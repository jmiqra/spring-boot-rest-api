package com.asraf.repositories;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.jpa.JPQLQuery;

@NoRepositoryBean
public interface ExtendedQueryDslJpaRepository<T, ID extends Serializable> 
        extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {

    <T1> Page<T1> findAll(JPQLQuery<?> jpqlQuery, Pageable pageable);
}