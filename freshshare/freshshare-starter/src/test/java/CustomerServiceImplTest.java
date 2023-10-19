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
    void signUp() throws Exception{
        String jsonRequestBody = "{"
                + "\"customerUsername\":\"testUser1\"," //please each test add 1 to the username
                + "\"customerPassword\":\"testPass\","
                + "\"customerEmail\":\"test1@example.com\","//please each test add 1 to the email
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
                + "\"customerUsername\":\"testUser\","
                + "\"customerPassword\":\"testPass\","
                + "\"customerEmail\":\"test@example.com\","
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
                + "\"customerUsername\":\"testUser1\","
                + "\"customerPassword\":\"testPass\","
                + "\"customerEmail\":\"test@example.com\","
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
                + "\"customerUsername\":\"testUser\","
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
                + "\"customerUsername\":\"testUser1\","
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
                + "\"customerUsername\":\"admin\","
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