package com.testing.coordinates;

import com.testing.coordinates.Controller.CoordinatesController;
import com.testing.coordinates.Service.CoordinatesService;
import com.testing.coordinates.model.Coordinates;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@ExtendWith(SpringExtension.class)
@WebMvcTest(CoordinatesController.class)
class CoordinatesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CoordinatesService mockCoordinatesService;

    @Test
    public void demotest() {
        assertTrue(true);
    }

    @Test
    void testCoord() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v3/Coordinates/coord-test")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("Coordinates");
    }

    @Test
    void testCalculateDistance() throws Exception {
        when(mockCoordinatesService.calculateDistance(any(Coordinates.class))).thenReturn(1594.1693658154443);

        String requestBody = "{\"latitude1\": 13.011760, \"longitude1\": 80.221481, \"latitude2\": 13.008430, \"longitude2\": 80.207169}";

        final MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance")
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("1594.1693658154443");
    }

    @Test
    void testCalculateDistance2() throws Exception {
        when(mockCoordinatesService.calculateDistance1(any(Coordinates.class), anyDouble(), anyDouble()))
                .thenReturn(1.5941693658154443); // Mock distance value

        String requestBody = "{\"latitude1\": 13.011760, \"longitude1\": 80.221481}";

        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("lat2", "13.008430").param("lon2", "80.207169"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("1.5941693658154443");
    }

    @Test
    void testCalculateDistance3_positive_testcase() throws Exception {
        when(mockCoordinatesService.calculateDistance2(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(422.10523754448025); // Mock distance value

        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance3")
                        .param("lat1", "9.9252").param("lon1", "78.1198")
                        .param("lat2", "13.0826").param("lon2", "80.2707"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("422.10523754448025");
    }

    @Test
    void testCalculateDistance3_MissingParameters() throws Exception {
        when(mockCoordinatesService.calculateDistance2(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
                .thenThrow(new RuntimeException("Error calculating distance"));

        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
                        .param("lat1", " ").param("lon1", "78.1198")
                        .param("lat2", "13.0826").param("lon2", "80.2707"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        System.err.println("Error occurred due to missing parameter in the input");
    }

    @Test
    void testCalculateDistance3_NegativeParameter() throws Exception {
        when(mockCoordinatesService.calculateDistance2(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
                .thenThrow(new RuntimeException("Error calculating distance"));

        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
                        .param("lat1", "-200.0000").param("lon1", "78.1198")
                        .param("lat2", "13.0826").param("lon2", "80.2707"))
                .andReturn().getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        System.err.println("Error occurred due to negative parameter in the input");
    }
}