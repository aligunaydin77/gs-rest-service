/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hello;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class })
public class GreetingControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private GreetingRepo repo;

	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc
		.perform(get("/greeting"))
		.andDo(log())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.content").value("Hello, World!"));
	}

	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
	}


	@Test
	public void invalidUrlShouldReturnError404() throws Exception {

		this.mockMvc
		.perform(get("/greeting1"))
		.andExpect(status().is4xxClientError());
	}

	@Test
	public void saveGreeting() throws Exception {
		//doNothing().when(repo.save(any(Greeting.class)));

		this.mockMvc
		.perform(post("/greeting/form"))
		.andExpect(status().isOk());
	}
}
