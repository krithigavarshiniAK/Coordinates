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

@ExtendWith(SpringExtension.class)
@WebMvcTest(CoordinatesController.class)
class CoordinatesApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CoordinatesService mockCoordinatesService;


    @MockBean
    TerminalDetails terminal;

    @Test
    public void demotest() {
        assertTrue(true);
    }

    @Autowired
    CoordinatesServiceImple coordinatesServiceImpleUnderTest;

    @Autowired
    ExternalPropertyConfig externalPropertyConfig;

    @BeforeEach
    void setUp() {
        coordinatesServiceImpleUnderTest = new CoordinatesServiceImple();
        externalPropertyConfig = new ExternalPropertyConfig();
    }

//    @Test
//    void testGetDistanceWithFixedSourceAndDestination(){
//        final Coordinates coordinates = new Coordinates(13.011760, 80.221481, 11.941591, 79.808311, 127171.1223524084);
//        final double result = coordinatesServiceImpleUnderTest.getDistanceWithFixedSourceAndDestination(coordinates);
//        assertThat(result).isEqualTo(127171.1223524084, within(0.0001));
//    }

//    @Test
//    void testGetDistanceWithFixedSource() {
//        final Coordinates coordinates = new Coordinates(13.011760, 80.221481, 13.008430, 80.207169, 1594.1693658154443);
//        final double result = coordinatesServiceImpleUnderTest.getDistanceWithFixedSource(coordinates, 13.008430, 80.207169);
//        assertThat(result).isEqualTo(1594.1693658154443, within(0.0001));
//    }

//    @Test
//    void testGetDistanceWithDynamicSourceAndDestination() {
//        assertThat(coordinatesServiceImpleUnderTest.getDistanceWithDynamicSourceAndDestination(9.9252, 78.1198, 13.0826,
//                80.2707)).isEqualTo(422105.23754448025, within(0.0001));
//    }

    @Test
    void testPassGeoCoordinate() {
        externalPropertyConfig.loadProperties();
        assertThat(coordinatesServiceImpleUnderTest.fetchAndCompareDistances("000000000000020","APPSYN00003", 9.9252,78.1198))
                .isEqualTo("The distance Comparison Output is : true.");
    }
    @Test
    void testCoord() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v3/Coordinates/coord-test")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("Coordinates");
    }

//    @Test
//    void testCalculateDistance1_Positive_testCase() throws Exception {
//        when(mockCoordinatesService.getDistanceWithFixedSourceAndDestination(any(Coordinates.class))).thenReturn(127171.1223524084);
//
//        String requestBody = "{\"latitude1\": 13.011760, \"longitude1\": 80.221481, \"latitude2\": 11.941591, \"longitude2\": 79.808311}";
//
//        final MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance")
//                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andReturn().getResponse();
//
//        verify(mockCoordinatesService).getDistanceWithFixedSourceAndDestination(any(Coordinates.class));
//
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("127171.1223524084");
//    }
//
//    @Test
//    void testCalculateDistance1_Missing_Parameters() throws Exception{
//        when(mockCoordinatesService.getDistanceWithFixedSourceAndDestination(any(Coordinates.class)))
//                .thenThrow(new RuntimeException("Error calculating distance"));
//
//        String requestBody = "{\"latitude1\": , \"longitude1\": 80.221481, \"latitude2\": 13.008430, \"longitude2\": 80.207169}";
//
//        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance")
//                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andReturn().getResponse();
//
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//        System.err.println("Error occurred due to missing parameter in the input");
//    }
//
//    @Test
//    void testCalculateDistance1_Negative_testCase() throws Exception {
//        when(mockCoordinatesService.getDistanceWithFixedSourceAndDestination(any(Coordinates.class))).thenReturn(1594.1693658154443);
//
//        String requestBody = "{\"latitude1\": -13.011760, \"longitude1\": -80.221481, \"latitude2\": -13.008430, \"longitude2\": -80.207169}";
//
//        final MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance")
//                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andReturn().getResponse();
//
//        verify(mockCoordinatesService).getDistanceWithFixedSourceAndDestination(any(Coordinates.class));
//
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("1594.1693658154443");
//    }

//    @Test
//    void testCalculateDistance2_Positive_testCase() throws Exception {
//        when(mockCoordinatesService.getDistanceWithFixedSource(any(Coordinates.class), anyDouble(), anyDouble()))
//                .thenReturn(1594.1693658154443); // Mock distance value
//
//        String requestBody = "{\"latitude1\": 13.011760, \"longitude1\": 80.221481}";
//
//        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
//                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .param("latitude2", "13.008430").param("longitude2", "80.207169"))
//                .andReturn().getResponse();
//
//        verify(mockCoordinatesService).getDistanceWithFixedSource(any(Coordinates.class), anyDouble(), anyDouble());
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("1594.1693658154443");
//    }
//
//    @Test
//    void testCalculateDistance2_Missing_Parameter() throws Exception{
//        when(mockCoordinatesService.getDistanceWithFixedSource(any(Coordinates.class), anyDouble(), anyDouble()))
//                .thenThrow(new RuntimeException("Error calculating distance"));
//
//        String requestBody = "{\"latitude1\": 13.011760, \"longitude1\": 80.221481}";
//
//        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
//                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .param("latitude2", " ").param("longitude2", "80.207169"))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//        System.err.println("Error occurred due to missing parameter in the input");
//    }
//
//    @Test
//    void testCalculateDistance2_Negative_testCase() throws Exception{
//        when(mockCoordinatesService.getDistanceWithFixedSource(any(Coordinates.class), anyDouble(), anyDouble()))
//                .thenReturn(1594.1693658154443); // Mock distance value
//
//        String requestBody = "{\"latitude1\": -13.011760, \"longitude1\": -80.221481}";
//
//        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
//                        .content(requestBody).contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .param("latitude2", "-13.008430").param("longitude2", "-80.207169"))
//                .andReturn().getResponse();
//
//        verify(mockCoordinatesService).getDistanceWithFixedSource(any(Coordinates.class), anyDouble(), anyDouble());
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("1594.1693658154443");
//    }

//    @Test
//    void testCalculateDistance3_Positive_testCase() throws Exception {
//        when(mockCoordinatesService.getDistanceWithDynamicSourceAndDestination(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
//                .thenReturn(422105.23754448025); // Mock distance value
//
//        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance3")
//                        .param("latitude1", "9.9252").param("longitude1", "78.1198")
//                        .param("latitude2", "13.0826").param("longitude2", "80.2707"))
//                .andReturn().getResponse();
//
//        verify(mockCoordinatesService).getDistanceWithDynamicSourceAndDestination(anyDouble(), anyDouble(), anyDouble(), anyDouble());
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("422105.23754448025");
//    }
//    @Test
//    void testCalculateDistance3_Missing_Parameters() throws Exception {when(mockCoordinatesService.getDistanceWithDynamicSourceAndDestination(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
//                .thenThrow(new RuntimeException("Error calculating distance"));
//
//        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance2")
//                        .param("latitude1", " ").param("longitude1", "78.1198")
//                        .param("latitude2", "13.0826").param("longitude2", "80.2707"))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//        System.err.println("Error occurred due to missing parameter in the input");
//    }
//
//    @Test
//    void testCalculateDistance3_Negative_testCase() throws Exception
//    {
//        when(mockCoordinatesService.getDistanceWithDynamicSourceAndDestination(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
//                .thenReturn(422105.23754448025);
//
//        MockHttpServletResponse response = mockMvc.perform(post("/api/v3/Coordinates/distance3")
//                        .param("latitude1", "-9.9252").param("longitude1", "-78.1198")
//                        .param("latitude2", "-13.0826").param("longitude2", "-80.2707"))
//                .andReturn().getResponse();
//
//        verify(mockCoordinatesService).getDistanceWithDynamicSourceAndDestination(anyDouble(), anyDouble(), anyDouble(), anyDouble());
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("422105.23754448025");
//    }
}
