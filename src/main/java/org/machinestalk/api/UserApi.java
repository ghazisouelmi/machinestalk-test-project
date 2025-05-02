package org.machinestalk.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.machinestalk.api.dto.UserDto;
import org.machinestalk.api.dto.UserRegistrationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import reactor.core.publisher.Mono;


public interface UserApi {

  /**
   * Register a new user.
   *
   * @param userRegistrationDto DTO that input data needed to register a new user.
   * @return registered user infos.
   */
  @PostMapping("/users/register")
  UserDto register(@NotNull @Valid UserRegistrationDto userRegistrationDto);

  /**
   * Get user details by id.
   *
   * @param id user id
   * @return user infos
   */
  @GetMapping("/users/{id}")
  Mono<UserDto> findUserById(@PathVariable long id);
}