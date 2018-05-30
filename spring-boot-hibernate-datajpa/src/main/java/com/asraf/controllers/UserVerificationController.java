package com.asraf.controllers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.asraf.dtos.mapper.UserVerificationMappper;
import com.asraf.dtos.request.UserVerificationRequestDto;
import com.asraf.dtos.response.UserVerificationResponseDto;
import com.asraf.dtos.response.request.FieldValidations;
import com.asraf.dtos.response.request.RequestBodyResponseDto;
import com.asraf.dtos.response.request.RequestField;
import com.asraf.entities.UserVerification;
import com.asraf.services.UserVerificationService;
import com.asraf.util.EnumUtil;
import com.asraf.validators.EnumValueConstraint;

@RestController
@RequestMapping("/user-verifications")
public class UserVerificationController {

	private UserVerificationService userVerificationService;
	private UserVerificationMappper userVerificationMappper;

	@Autowired
	public UserVerificationController(UserVerificationService userVerificationService,
			UserVerificationMappper userVerificationMappper) {
		this.userVerificationMappper = userVerificationMappper;
		this.userVerificationService = userVerificationService;
	}

	@GetMapping("")
	public List<UserVerificationResponseDto> getAll() {
		List<UserVerificationResponseDto> response = userVerificationMappper
				.getResponseDtos(this.userVerificationService.getAll());
		return response;
	}

	@GetMapping("/{id}")
	public UserVerificationResponseDto getById(@PathVariable long id) {
		UserVerification userVerification = userVerificationService.getById(id);
		return userVerificationMappper.getResponseDto(userVerification);
	}

	@GetMapping("/form")
	public RequestBodyResponseDto getForm() {
		RequestBodyResponseDto response = new RequestBodyResponseDto();
		for (Field field : UserVerificationRequestDto.class.getDeclaredFields()) {
			FieldValidations validations = new FieldValidations();
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof NotNull) {
					validations.setIsRequired(true);
				} else if (annotation instanceof Email) {
					validations.setIsEmail(true);
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
					validations.setOptions(EnumUtil.getNameValues(enumClazz));
				}
			}
			RequestField requestField = RequestField.builder().name(field.getName())
					.type(field.getType().getSimpleName()).validations(validations).build();
			response.addRequestField(requestField);
		}
		return response;
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public UserVerificationResponseDto create(@Valid @RequestBody UserVerificationRequestDto requestDto) {
		UserVerification userVerification = userVerificationMappper.getEntity(requestDto);
		userVerificationService.save(userVerification);
		return userVerificationMappper.getResponseDto(userVerification);
	}

	@DeleteMapping("/{id}")
	public UserVerificationResponseDto delete(@PathVariable long id) {
		UserVerification userVerification = userVerificationService.getById(id);
		UserVerificationResponseDto response = userVerificationMappper.getResponseDto(userVerification);
		userVerificationService.delete(userVerification);
		return response;
	}

	@PutMapping("/{id}")
	public UserVerificationResponseDto update(@PathVariable long id,
			@Valid @RequestBody UserVerificationRequestDto requestDto) {
		UserVerification userVerification = userVerificationService.getById(id);
		userVerificationMappper.loadEntity(requestDto, userVerification);
		userVerificationService.save(userVerification);
		return userVerificationMappper.getResponseDto(userVerification);
	}

}
