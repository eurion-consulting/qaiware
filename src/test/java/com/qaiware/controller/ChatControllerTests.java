package com.qaiware.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChatControllerTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void getMessagesShouldContainString() throws Exception {
		this.mockMvc.perform(get("/messages/test"))
		.andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("test")));
	}
	
	@Test
	public void postValidTextMessages() throws Exception {
		
		this.mockMvc
			.perform(post("/messages/send_text")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content("{\"payload\" : \"Happy\"}")
					)
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(content().string(containsString("")));
	}
	
	@Test
	public void postValidEmotionMessages() throws Exception {
		
		this.mockMvc
			.perform(post("/messages/send_emotion")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content("{\"payload\" : \"Love\"}"))
			.andDo(print())
			.andExpect(status().is(HttpStatus.CREATED.value()))
			.andExpect(content().string(""));
	}
	
	@Test
	public void postEmptyEmotionMessages() throws Exception {
		
		this.mockMvc
			.perform(post("/messages/send_emotion")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content("{\"payload\" : \"\"}"))
			.andDo(print())
			.andExpect(status().is(HttpStatus.PRECONDITION_FAILED.value()))
			.andExpect(content().string(""));
	}
	
	@Test
	public void postOutOfMaxLengthEmotionMessage() throws Exception {
		
		this.mockMvc
			.perform(post("/messages/send_emotion")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content("{\"payload\" : \"Anticipation\"}"))
			.andDo(print())
			.andExpect(status().is(HttpStatus.PRECONDITION_FAILED.value()))
			.andExpect(content().string(""));
	}
	
	@Test
	public void postInvalidEmotionMessage() throws Exception {
		
		this.mockMvc
			.perform(post("/messages/send_emotion")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content("{\"payload\" : \"J0Y\"}"))
			.andDo(print())
			.andExpect(status().is(HttpStatus.PRECONDITION_FAILED.value()))
			.andExpect(content().string(""));
	}
	
}
