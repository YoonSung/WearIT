package kr.wearit.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class IndexControllerTest {

	MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = standaloneSetup(new IndexController()).build();
	}
	
	//SimpleConrollerTest 참조
	@Test
	public void reqeust() throws Exception {
		mockMvc
		.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("index"));
	}
	
	//FormControllerTests 참조
	@Test
	public void create() {
		
	}
}
