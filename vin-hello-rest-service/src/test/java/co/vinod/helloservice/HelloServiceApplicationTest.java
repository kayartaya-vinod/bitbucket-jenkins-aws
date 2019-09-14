package co.vinod.helloservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloServiceApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void test1() throws Exception {
		this.mvc.perform(get("/api/hello"))
			.andExpect(status().isOk())
			.andExpect(content().string("{\"from\":\"Vinod KK\",\"message\":\"Hello, world!\",\"email\":\"vinod@vinod.co\"}"));
	}
}
