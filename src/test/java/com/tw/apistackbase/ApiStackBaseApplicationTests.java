package com.tw.apistackbase;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApiStackBaseApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnAllEmployees()throws Exception{
		String string=this.mockMvc.perform(get("/employees")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		JSONArray json = new JSONArray(string);
		Assertions.assertEquals(22,json.getJSONObject(0).getInt("age"));
	}

	@Test
	public void shouldReturnAllEmployeesWithPages()throws Exception{
		String string=this.mockMvc.perform(get("/employees?page=1&pageSize=3")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		JSONArray json = new JSONArray(string);
		Assertions.assertEquals(22,json.getJSONObject(0).getInt("age"));
	}

	@Test
	public void shouldReturnAllEmployeesWithGender()throws Exception{
		String string=this.mockMvc.perform(get("/employees?gender=male")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		JSONArray json = new JSONArray(string);
		Assertions.assertEquals(22,json.getJSONObject(0).getInt("age"));
	}

	@Test
	public void shouldReturnEmployeeById()throws Exception{
		String string=this.mockMvc.perform(get("/employees/1")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		JSONObject json = new JSONObject(string);
		Assertions.assertEquals("panda",json.getString("name"));
	}

	@Test
	public void shouldReturnOKWhenCreateEmployee()throws Exception{
		this.mockMvc.perform(get("/employees/1")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void contextLoads() {
		
	}

}
