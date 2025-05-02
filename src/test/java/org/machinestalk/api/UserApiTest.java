package org.machinestalk.api;

import org.junit.jupiter.api.Test;
import org.machinestalk.api.dto.AddressDto;
import org.machinestalk.api.dto.UserRegistrationDto;
import org.machinestalk.service.impl.UserServiceImpl;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
public class UserApiTest {
	
	@Mock private UserServiceImpl userService;
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Test
	void shouldCreateUser() throws Exception {
		
		
		UserRegistrationDto userRequest = getUserDto();
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/users/register").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userRequest)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
		result.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Jack"));
	}

	private UserRegistrationDto getUserDto() {
		final AddressDto addressDto = new AddressDto();
	    addressDto.setStreetName("20");
	    addressDto.setStreetName("Rue de Voltaire");
	    addressDto.setPostalCode("75015");
	    addressDto.setCity("Paris");
	    addressDto.setCountry("France");

	    final UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
	    userRegistrationDto.setFirstName("Jack");
	    userRegistrationDto.setLastName("Sparrow");
	    userRegistrationDto.setDepartment("RH");
	    userRegistrationDto.setPrincipalAddress(addressDto);
		return userRegistrationDto;
	}
}
