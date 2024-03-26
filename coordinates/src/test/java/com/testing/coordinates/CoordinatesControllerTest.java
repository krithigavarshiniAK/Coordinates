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
import static org.mockito.Mockito.doReturn;
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
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("Coordinates");
    }

    @Test
    void testCalculateDistance1_Positive_testCase() throws Exception {
        when(mockCoordinatesService.getDistanceWithFixedSourceAndDestination(any(Coordinates.class))).thenReturn(127171.1223524084);

        String requestBody = "{\"latitude1\": 13.011760, \"longitude1\": 80.221481, \"latitude2\": 11.941591, \"longitude2\": 79.808311}";

        final MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance")
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)

                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("127171.1223524084");
    }

    @Test
    void testCalculateDistance1_Missing_Parameters() throws Exception{
        when(mockCoordinatesService.getDistanceWithFixedSourceAndDestination(any(Coordinates.class)))
                .thenThrow(new RuntimeException("Error calculating distance"));

        String requestBody = "{\"latitude1\": , \"longitude1\": 80.221481, \"latitude2\": 13.008430, \"longitude2\": 80.207169}";

        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance")
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        System.err.println("Error occurred due to missing parameter in the input");
    }

    @Test
    void testCalculateDistance1_Negative_testCase() throws Exception {
        when(mockCoordinatesService.getDistanceWithFixedSourceAndDestination(any(Coordinates.class))).thenReturn(1594.1693658154443);

        String requestBody = "{\"latitude1\": -13.011760, \"longitude1\": -80.221481, \"latitude2\": -13.008430, \"longitude2\": -80.207169}";

        final MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance")
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("1594.1693658154443");
    }

    @Test
    void testCalculateDistance2_Positive_testCase() throws Exception {
        when(mockCoordinatesService.getDistanceWithFixedSource(any(Coordinates.class), anyDouble(), anyDouble()))
                .thenReturn(1594.1693658154443); // Mock distance value

        String requestBody = "{\"latitude1\": 13.011760, \"longitude1\": 80.221481}";

        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("latitude2", "13.008430").param("longitude2", "80.207169"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("1594.1693658154443");
    }

    @Test
    void testCalculateDistance2_Missing_Parameter() throws Exception{
        when(mockCoordinatesService.getDistanceWithFixedSource(any(Coordinates.class), anyDouble(), anyDouble()))
                .thenThrow(new RuntimeException("Error calculating distance"));

        String requestBody = "{\"latitude1\": 13.011760, \"longitude1\": 80.221481}";

        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("latitude2", " ").param("longitude2", "80.207169"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        System.err.println("Error occurred due to missing parameter in the input");
    }

    @Test
    void testCalculateDistance2_Negative_testCase() throws Exception{
        when(mockCoordinatesService.getDistanceWithFixedSource(any(Coordinates.class), anyDouble(), anyDouble()))
                .thenReturn(1594.1693658154443); // Mock distance value

        String requestBody = "{\"latitude1\": -13.011760, \"longitude1\": -80.221481}";

        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("latitude2", "-13.008430").param("longitude2", "-80.207169"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("1594.1693658154443");
    }

    @Test
    void testCalculateDistance3_Positive_testCase() throws Exception {
        when(mockCoordinatesService.getDistanceWithDynamicSourceAndDestination(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
                .thenReturn(422105.23754448025); // Mock distance value

        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance3")
                        .param("latitude1", "9.9252").param("longitude1", "78.1198")
                        .param("latitude2", "13.0826").param("longitude2", "80.2707"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("422105.23754448025");
    }

    @Test
    void testCalculateDistance3_Missing_Parameters() throws Exception {
        when(mockCoordinatesService.getDistanceWithDynamicSourceAndDestination(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
                .thenThrow(new RuntimeException("Error calculating distance"));

        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
                        .param("latitude1", " ").param("longitude1", "78.1198")
                        .param("latitude2", "13.0826").param("longitude2", "80.2707"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        System.err.println("Error occurred due to missing parameter in the input");
    }

    @Test
    void testCalculateDistance3_Negative_Parameter() throws Exception {
        when(mockCoordinatesService.getDistanceWithDynamicSourceAndDestination(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
                .thenThrow(new RuntimeException("Error calculating distance"));

        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
                        .param("latitude1", "-0").param("longitude1", "78.1198")
                        .param("latitude2", "13.0826").param("longitude2", "80.2707"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        System.err.println("Error occurred due to negative parameter in the input");
    }
}