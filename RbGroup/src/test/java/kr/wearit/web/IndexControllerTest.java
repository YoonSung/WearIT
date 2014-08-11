package kr.wearit.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;

public class IndexControllerTest {

	@Test
	public void reqeust() throws Exception {
		standaloneSetup(new IndexController()).build()
		.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("index"));
	}
}
