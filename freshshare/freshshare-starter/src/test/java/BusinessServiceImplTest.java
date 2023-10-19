import com.freshshare.starter.Starter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = Starter.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BusinessServiceImplTest {


    @Resource
    private MockMvc mockMvc;

    @Test
    void signUp() throws Exception{

        String jsonRequestBody = "{"
                + "\"businessUsername\":\"testUser\","
                + "\"businessEmail\":\"test1@example.com\","
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
