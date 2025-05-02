package org.machinestalk.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.machinestalk.api.dto.UserDto;
import org.machinestalk.api.dto.UserRegistrationDto;

import reactor.core.publisher.Mono;

public interface UserService {

	/**
	 * Register a new user.
	 *
	 * @param userRegistrationDto dto for user registration.
	 * @return user entity.
	 */
	UserDto registerUser(@NotNull @Valid UserRegistrationDto userRegistrationDto);

	/**
	 * Get user by its id.
	 *
	 * @param id user id.
	 * @return user entity.
	 */
	Mono<UserDto> getById(long id);
}