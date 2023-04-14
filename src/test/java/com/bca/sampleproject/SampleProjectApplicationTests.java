package com.bca.sampleproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class SampleProjectApplicationTests extends BaseControllerTest {

	@Test
	void test() throws Exception {
		MockHttpServletResponse response = mvc.perform(
						MockMvcRequestBuilders.post("/")
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse();
		// test
		assertEquals(
				HttpStatus.OK.value(),
				response.getStatus()
		);



	}

}
