import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.freshshare.starter.Starter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@SpringBootTest(classes = Starter.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BusinessServiceImplTest {


    @Resource
    private MockMvc mockMvc;

    @Value("${google.api.key}")
    private String GOOGLE_MAPS_API_KEY;


    private final String GEOCODING_API_BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json";

    @Test
    void signUp() throws Exception{

        String jsonRequestBody = "{"
                + "\"businessUsername\":\"testUser\","
                + "\"businessEmail\":\"testUser@example.com\","
                + "\"businessPassword\":\"testPass\","
                + "\"businessShopname\":\"testName\","
                + "\"businessPhone\":\"0435815188\","
                + "\"businessAddress\":\"718 george street haymarket\","
                + "\"businessPostcode\":\"1234\","
                + "\"businessState\":\"NSW\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/business/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100000"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    void logIn() throws Exception{
        String jsonRequestBody = "{"
                + "\"businessUsername\":\"testUser\","
                + "\"businessPassword\":\"testPass\","
                + "\"rememberMe\":\"true\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/business/logIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100010"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
        ;
    }

}
