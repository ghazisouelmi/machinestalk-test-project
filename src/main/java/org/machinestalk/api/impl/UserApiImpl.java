package org.machinestalk.api.impl;

import org.machinestalk.api.UserApi;
import org.machinestalk.api.dto.UserDto;
import org.machinestalk.api.dto.UserRegistrationDto;
import org.machinestalk.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class UserApiImpl implements UserApi {
	
	public UserApiImpl(UserService userService) {
		super();
		this.userService = userService;
	}

	private UserService userService;

    @Override
    public UserDto register(final UserRegistrationDto userRegistrationDto) {
        // implement me !!
        return userService.registerUser(userRegistrationDto);
    }

    @Override
    public Mono<UserDto> findUserById(long id) {
        System.out.println("findUserById: " + id);
        return null;
    }
}
