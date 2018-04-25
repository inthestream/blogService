package com.yun.blogService.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(WebBoardController.class)
public class TestWebBoardController {

	@Autowired
	MockMvc mock;
	
	@Test
	public void testRespondStatus200() throws Exception {
		mock.perform(get("/list")).andExpect(status().isOk());
	}
}
