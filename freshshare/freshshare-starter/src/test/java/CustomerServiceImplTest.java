import com.freshshare.starter.Starter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Starter.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
class CustomerServiceImplTest {

    @Resource
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        log.info("setUp");
    }

    @AfterEach
    void tearDown() {
        log.info("tearDown");
    }

    @Test
    void signUp_MultipleUsers() throws Exception {
        for (int i = 1; i <= 100; i++) {
            String jsonRequestBody = "{"
                    + "\"customerUsername\":\"test" + i + "\","
                    + "\"customerPassword\":\"123456\","
                    + "\"customerEmail\":\"test" + i + "@gmail.com\","
                    + "\"customerAddress\":\"testAddress\","
                    + "\"customerPhone\":\"1234567890\","
                    + "\"customerFirstname\":\"test\","
                    + "\"customerLastname\":\"" + i + "\","
                    + "\"customerPostcode\":\"2000\""
                    + "}";

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/auth/customer/signUp")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonRequestBody))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100000"))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }
    }

    @Test
    void signUp() throws Exception{
        String jsonRequestBody = "{"
                + "\"customerUsername\":\"testUserRegister\"," //please each test add 1 to the username
                + "\"customerPassword\":\"testPass\","
                + "\"customerEmail\":\"testRegister@example.com\","//please each test add 1 to the email
                + "\"customerAddress\":\"testAddress\","
                + "\"customerPhone\":\"1234567890\","
                + "\"customerFirstname\":\"testFirst\","
                + "\"customerLastname\":\"testLast\","
                + "\"customerPostcode\":\"12345\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/customer/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100000"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
        ;
    }

    @Test
    public void signUp_UsernameAlreadyExists_ReturnsCustomError() throws Exception {
        String jsonRequestBody = "{"
                + "\"customerUsername\":\"test\","
                + "\"customerPassword\":\"123456\","
                + "\"customerEmail\":\"test@gmailasd.com\","
                + "\"customerAddress\":\"testAddress\","
                + "\"customerPhone\":\"1234567890\","
                + "\"customerFirstname\":\"testFirst\","
                + "\"customerLastname\":\"testLast\","
                + "\"customerPostcode\":\"12345\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/customer/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk()) // expecting 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100001"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void signUp_EmailAlreadyExists_ReturnsCustomError() throws Exception {
        String jsonRequestBody = "{"
                + "\"customerUsername\":\"testUser1234567890\","
                + "\"customerPassword\":\"testPass\","
                + "\"customerEmail\":\"test@gmail.com\","
                + "\"customerAddress\":\"testAddress\","
                + "\"customerPhone\":\"1234567890\","
                + "\"customerFirstname\":\"testFirst\","
                + "\"customerLastname\":\"testLast\","
                + "\"customerPostcode\":\"12345\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/customer/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk()) // expecting 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100002"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    void logIn() throws Exception{
        String jsonRequestBody = "{"
                + "\"customerUsername\":\"testUser\","
                + "\"customerPassword\":\"testPass\","
                + "\"rememberMe\":false"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/customer/logIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void logIn_UsernamePasswordNotMatch_ReturnsCustomError() throws Exception{
        String jsonRequestBody = "{"
                + "\"customerUsername\":\"zhehao\","
                + "\"customerPassword\":\"testPass1\","
                + "\"rememberMe\":false"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/customer/logIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk()) // expecting 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100011"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void logIn_UsernameNotExisted_ReturnsCustomError() throws Exception{
        String jsonRequestBody = "{"
                + "\"customerUsername\":\"testUser321313121\","
                + "\"customerPassword\":\"testPass\","
                + "\"rememberMe\":false"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/customer/logIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk()) // expecting 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100013"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void logIn_UsernameIsLocked_ReturnsCustomError() throws Exception{
        String jsonRequestBody = "{"
                + "\"customerUsername\":\"testlock\","
                + "\"customerPassword\":\"testPass\","
                + "\"rememberMe\":false"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/customer/logIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk()) // expecting 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100014"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}