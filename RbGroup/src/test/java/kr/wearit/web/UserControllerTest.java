package kr.wearit.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class UserControllerTest {

	MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = standaloneSetup(new UserController()).alwaysExpect(status().isMovedTemporarily()).build();
	}
	
	// FormControllerTests 참조
	@Test
	public void create() throws Exception {
		mockMvc.perform(
				post("/user/register")
					.param("userId", "lvev9925")
					.param("password", "Yoonsung")
					.param("name", "JungYoonSung")
					.param("email",""))
		.andDo(print())
		.andExpect(redirectedUrl("/"));
	}
}
