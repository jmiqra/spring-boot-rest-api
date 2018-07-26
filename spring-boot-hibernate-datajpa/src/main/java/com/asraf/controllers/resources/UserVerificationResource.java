package com.asraf.controllers.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.asraf.controllers.UserController;
import com.asraf.controllers.UserVerificationController;
import com.asraf.dtos.mapper.UserVerificationMappper;
import com.asraf.dtos.response.entities.UserVerificationResponseDto;
import com.asraf.entities.User;
import com.asraf.entities.UserVerification;

import lombok.Getter;

@Getter
public class UserVerificationResource extends BaseResource {

	private final UserVerificationResponseDto userVerification;

	public UserVerificationResource(final UserVerification userVerification,
			final UserVerificationMappper userVerificationMappper) {

		this.userVerification = userVerificationMappper.getResponseDto(userVerification);
		final long id = userVerification.getId();
		final User user = userVerification.getUser();

		add(linkTo(methodOn(UserVerificationController.class).getById(id)).withSelfRel());
		add(linkTo(UserVerificationController.class).withRel("userVerifications"));
		add(linkTo(methodOn(UserController.class).getByEmail(user.getEmail())).withRel("user"));
		add(linkTo(methodOn(UserController.class).getByName(user.getName())).withRel("user"));
		add(linkTo(methodOn(UserController.class).getByQuery("id==" + user.getId(), null)).withRel("user"));

	}
}