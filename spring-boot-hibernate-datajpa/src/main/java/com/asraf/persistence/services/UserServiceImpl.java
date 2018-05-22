package com.asraf.persistence.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.User;
import com.asraf.core.models.search.UserSearch;
import com.asraf.core.repositories.UserRepository;
import com.asraf.core.services.UserService;
import com.asraf.exceptions.EntityNotFoundException;
import com.asraf.persistence.repositories.GenericSpecificationsBuilder;
import com.asraf.persistence.repositories.UserPredicatesBuilder;
import com.asraf.persistence.repositories.UserSpecification;
import com.asraf.util.CriteriaParser;
import com.asraf.util.CustomRsqlVisitor;
import com.asraf.util.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public User getById(Long id) throws EntityNotFoundException {
		try {
			return userRepository.findById(id).get();
		} catch (NoSuchElementException nseex) {
			throw new EntityNotFoundException(User.class, "id", id.toString());
		}
	}

	public Iterable<User> getAll() {
		return userRepository.findAll();
	}

	public User getByEmail(String email) throws EntityNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new EntityNotFoundException(User.class, "email", email.toString());
		}
		return user;
	}

	public List<User> getByNameContains(String name) {
		return userRepository.findByNameContains(name);
	}

	public List<User> getBySearchCrud(UserSearch searchItem) {
		return userRepository.findByNameOrEmail(searchItem.getName(), searchItem.getEmail());
	}

	public Page<User> getBySearchCrudPageable(UserSearch searchItem, Pageable pageable) {
		return userRepository.findByNameContainsOrEmailContainsAllIgnoreCase(searchItem.getName(),
				searchItem.getEmail(), pageable);
	}

	public Iterable<User> getByQuery(String search) {
		// UserPredicatesBuilder builder = new UserPredicatesBuilder();
		//
		// if (search != null) {
		// Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
		// Matcher matcher = pattern.matcher(search + ",");
		// while (matcher.find()) {
		// builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		// }
		// }
		// BooleanExpression exp = builder.build();
		// Iterable<User> users = userRepository.findAll(exp);

//		Node rootNode = new RSQLParser().parse(search);
//		Specification<User> spec = rootNode.accept(new CustomRsqlVisitor<User>());
//		Iterable<User> users = userRepository.findAll(spec);
		
		Specification<User> spec = resolveSpecificationFromInfixExpr(search);
		Iterable<User> users = userRepository.findAll(spec);
		return users;
	}

	protected Specification<User> resolveSpecificationFromInfixExpr(String searchParameters) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<User> specBuilder = new GenericSpecificationsBuilder<>();
        return specBuilder.build(parser.parse(searchParameters), UserSpecification::new);
    }
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> searchUser(final List<SearchCriteria> params) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<User> query = builder.createQuery(User.class);
		final Root r = query.from(User.class);

		Predicate predicate = builder.conjunction();

		for (final SearchCriteria param : params) {
			if (param.getOperation().equalsIgnoreCase(">")) {
				predicate = builder.and(predicate,
						builder.greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
			} else if (param.getOperation().equalsIgnoreCase("<")) {
				predicate = builder.and(predicate,
						builder.lessThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
			} else if (param.getOperation().equalsIgnoreCase(":")) {
				if (r.get(param.getKey()).getJavaType() == String.class) {
					predicate = builder.and(predicate,
							builder.like(r.get(param.getKey()), "%" + param.getValue() + "%"));
				} else {
					predicate = builder.and(predicate, builder.equal(r.get(param.getKey()), param.getValue()));
				}
			}
		}
		query.where(predicate);

		return entityManager.createQuery(query).getResultList();
	}
}
