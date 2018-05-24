package com.asraf.repositories.persistence;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.asraf.entities.BaseEntity;
import com.asraf.repositories.ExtendedQueryDslJpaRepository;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

public class ExtendedQueryDslJpaRepositoryImpl<TEntity extends BaseEntity, ID extends Serializable>
		extends QuerydslJpaRepository<TEntity, ID> implements ExtendedQueryDslJpaRepository<TEntity, ID> {

	private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

	private final EntityPath<TEntity> path;
	private final PathBuilder<TEntity> builder;
	private final Querydsl querydsl;

	@SuppressWarnings("unused")
	private EntityManager entityManager;

	public ExtendedQueryDslJpaRepositoryImpl(JpaEntityInformation<TEntity, ID> entityInformation,
			EntityManager entityManager) {
		this(entityInformation, entityManager, DEFAULT_ENTITY_PATH_RESOLVER);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ExtendedQueryDslJpaRepositoryImpl(JpaEntityInformation<TEntity, ID> entityInformation,
			EntityManager entityManager, EntityPathResolver resolver) {

		super(entityInformation, entityManager);
		this.path = resolver.createPath(entityInformation.getJavaType());
		this.builder = new PathBuilder(this.path.getType(), this.path.getMetadata());
		this.querydsl = new Querydsl(entityManager, this.builder);
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T1> Page<T1> findAll(JPQLQuery<?> jpqlQuery, Pageable pageable) {

		// Count query
		final JPQLQuery<?> countQuery = jpqlQuery;

		// Apply pagination
		JPQLQuery<T1> query = (JPQLQuery<T1>) querydsl.applyPagination(pageable, jpqlQuery);

		// Run query
		return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchCount);
	}

}