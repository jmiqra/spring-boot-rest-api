package com.asraf.util;

import com.asraf.exceptions.EntityNotFoundException;

public final class ExceptionPreconditions {

	private ExceptionPreconditions() {
		throw new AssertionError();
	}

	/**
	 * @param resource
	 *            has value null if not found
	 * @param clazz
	 *            class type of resource
	 * @param searchParamsMap
	 *            sequence of parameter-value string
	 * @throws EntityNotFoundException
	 *             if resource is null, means value not found
	 * @return resource
	 */
	public static <T> T checkFound(final T resource, final Class<T> clazz, String... searchParamsMap) {
		if (resource == null) {
			entityNotFound(clazz, searchParamsMap);
		}
		return resource;
	}

	/**
	 * @param clazz
	 *            class type of resource
	 * @param searchParamsMap
	 *            sequence of parameter-value string
	 * @throws EntityNotFoundException
	 *             if resource is null, means value not found
	 */
	public static <T> void entityNotFound(final Class<T> clazz, String... searchParamsMap) throws EntityNotFoundException {
		throw new EntityNotFoundException(clazz, searchParamsMap);
	}
}