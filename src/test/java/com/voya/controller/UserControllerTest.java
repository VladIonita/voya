package com.voya.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Arrays;
import java.util.List;

import com.voya.config.WebMvcConfig;
import com.voya.config.WebMvcIntializer;
import com.voya.domain.User;
import com.voya.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebMvcIntializer.class)
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testGetUser() throws Exception {
		
		User user = new User(1, "v@d.com", "unu", "unu");
		User user2 = new User(2, "v@e.com", "doi", "doi");				
		
		when(userService.getAllUsers()).thenReturn(Arrays.asList(user, user2));

		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"))
				.andExpect(model().attribute("listUser", hasSize(2)))
				.andExpect(model().attribute("listUser", hasItem(allOf(hasProperty("id", is(1)), hasProperty("email", is("v@d.com")), hasProperty("first_name", is("unu")) 
						,hasProperty("last_name", is("unu"))))));
	}

}
