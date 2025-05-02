package org.machinestalk.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.machinestalk.api.dto.UserDto;
import org.machinestalk.api.dto.UserRegistrationDto;
import org.machinestalk.domain.Address;
import org.machinestalk.domain.Department;
import org.machinestalk.domain.User;
import org.machinestalk.repository.UserRepository;
import org.machinestalk.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	@Override
	public UserDto registerUser(final UserRegistrationDto userRegistrationDto) {
		User userToSave = modelMapper.map(userRegistrationDto, User.class);
		userToSave.setDepartment(new Department(userRegistrationDto.getDepartment()));
		Set<Address> addresses = new HashSet<>();
		addresses.add(modelMapper.map(userRegistrationDto.getPrincipalAddress(), Address.class));
		if(userRegistrationDto.getSecondaryAddress() != null) {
			addresses.add(modelMapper.map(userRegistrationDto.getSecondaryAddress(), Address.class));
		}
		userToSave.setAddresses(addresses);
		User savedUser = null;
		try {
			savedUser = userRepository.save(userToSave);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDto userDto = new UserDto();
		userDto.setId(savedUser.getId());
		List<String> addressesDto = savedUser.getAddresses().stream().map(Address::toString).toList();
		userDto.setUserInfos(new UserDto.UserInfos(savedUser.getFirstName(), savedUser.getLastName(), savedUser.getDepartment().getName(), addressesDto));
		return userDto;
	}

	@Override
	public Mono<UserDto>  getById(final long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return Mono.just(modelMapper.map(user.get(), UserDto.class));
		}
		// If we want to throw exception if user not found
//		else {
//			throw new RuntimeException("User not found");
//		}
		return null;
	}
}