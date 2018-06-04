package com.asraf.dtos.response.requestdto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.asraf.dtos.request.BaseRequestDto;
import com.asraf.dtos.response.BaseResponseDto;
import com.asraf.utils.EnumUtils;
import com.asraf.validators.ContactNumberConstraint;
import com.asraf.validators.EnumValueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RequestBodyResponseDto<T extends BaseRequestDto> extends BaseResponseDto {

	private List<RequestField> fields = new ArrayList<>();

	public RequestBodyResponseDto(Class<T> clazzRequestDto) {
		for (Field field : clazzRequestDto.getDeclaredFields()) {
			FieldValidations validations = new FieldValidations();
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof NotNull) {
					validations.setIsRequired(true);
				} else if (annotation instanceof Email) {
					validations.setIsEmail(true);
				} else if (annotation instanceof ContactNumberConstraint) {
					validations.setIsMobileNumber(true);
				} else if (annotation instanceof Min) {
					validations.setMinValue(((Min) annotation).value());
				} else if (annotation instanceof Max) {
					validations.setMaxValue(((Max) annotation).value());
				} else if (annotation instanceof Size) {
					validations.setMinSize(((Size) annotation).min());
					validations.setMaxSize(((Size) annotation).max());
				} else if (annotation instanceof Pattern) {
					validations.setPattern(((Pattern) annotation).regexp());
				} else if (annotation instanceof EnumValueConstraint) {
					Class<? extends Enum<?>> enumClazz = ((EnumValueConstraint) annotation).enumClass();
					validations.setOptions(EnumUtils.getNameValues(enumClazz));
				}
			}
			RequestField requestField = RequestField.builder().name(field.getName())
					.type(field.getType().getSimpleName()).validations(validations).build();
			fields.add(requestField);
		}
	}

}
