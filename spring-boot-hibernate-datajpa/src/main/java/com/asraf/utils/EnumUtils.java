package com.asraf.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public final class EnumUtils {
	public static String[] getNames(Class<? extends Enum<?>> enumClazz) {
		return Arrays.stream(enumClazz.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
	public static Map<String, Object> getNameValues(Class<? extends Enum<?>> enumClazz) {
		String[] names = Arrays.stream(enumClazz.getEnumConstants()).map(Enum::name).toArray(String[]::new);
		Object[] ordinals = Arrays.stream(enumClazz.getEnumConstants()).map(Enum::ordinal).toArray(Object[]::new);
		Map<String, Object> nameValues = new LinkedHashMap<>();
		for(int I = 0; I < names.length; I++)
			nameValues.put(names[I], ordinals[I]);
		return nameValues;
	}
}
