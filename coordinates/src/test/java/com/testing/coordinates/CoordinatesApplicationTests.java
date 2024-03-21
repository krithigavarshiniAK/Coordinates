package com.testing.coordinates;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.coordinates.Service.CoordinatesService;
import com.testing.coordinates.model.Coordinates;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = CoordinatesApplication.class)
class CoordinatesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	CoordinatesService coordinatesService;

	@Test
	public void demotest(){
		assertTrue(true);
	}

	@Test
	public void testMyEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v3/Coordinates/coord-test"))
				.andExpect(status().isOk())
				.andExpect(content().string("Coordinates"));
	}

	@Test
	public void testCalculateDistance() throws Exception {
		Coordinates coordinates = new Coordinates(51.5007, 0.1246, 40.6892, 74.0445);
		when(coordinatesService.calculateDistance(coordinates)).thenReturn(5574.840456848554);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v3/Coordinates/distance")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(coordinates)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(String.valueOf(0.0)));
	}
}