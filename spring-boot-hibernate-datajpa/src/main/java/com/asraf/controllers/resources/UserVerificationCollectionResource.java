package com.asraf.controllers.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.Collection;
import java.util.stream.Collectors;

import com.asraf.controllers.UserVerificationController;
import com.asraf.dtos.mapper.UserVerificationMappper;
import com.asraf.entities.UserVerification;

import lombok.Getter;

@Getter
public class UserVerificationCollectionResource extends BaseResource {

	private Collection<UserVerificationResource> userVerifications;

	public UserVerificationCollectionResource(final Collection<UserVerification> userVerifications,
			final UserVerificationMappper userVerificationMappper) {

		this.userVerifications = userVerifications.stream()
				.map(uv -> new UserVerificationResource(uv, userVerificationMappper)).collect(Collectors.toList());

		add(linkTo(UserVerificationController.class).withSelfRel());

	}

}