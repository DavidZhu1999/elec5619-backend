import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.freshshare.starter.Starter;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@SpringBootTest(classes = Starter.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ManageTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    void getAllCustomers() throws Exception{
        String loginJsonRequestBody = "{"
                + "\"staffUsername\":\"admin\","
                + "\"staffPassword\":\"123456\","
                + "\"rememberMe\":true"
                + "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/staff/logIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJsonRequestBody))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        // Assuming token is in the response body. You might need to parse the response to extract the token.
        System.out.println(response);
        JSONObject jsonResponse = JSON.parseObject(response);
        JSONObject dataObject = jsonResponse.getJSONObject("data");
        JSONObject tokenObject = dataObject.getJSONObject("satoken");
        String tokenValue = tokenObject.getString("tokenValue");


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/staff/manager/customer/getAllCustomers?satoken-staff=" + tokenValue)
                        .header("satoken-staff", tokenValue))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("1000002"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testActiveCustomerStatus() throws Exception {
        String loginJsonRequestBody = "{"
                + "\"staffUsername\":\"admin\","
                + "\"staffPassword\":\"123456\","
                + "\"rememberMe\":true"
                + "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/staff/logIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJsonRequestBody))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        // Assuming token is in the response body. You might need to parse the response to extract the token.
        System.out.println(response);
        JSONObject jsonResponse = JSON.parseObject(response);
        JSONObject dataObject = jsonResponse.getJSONObject("data");
        JSONObject tokenObject = dataObject.getJSONObject("satoken");
        String tokenValue = tokenObject.getString("tokenValue");
        String jsonRequestBody = "{"
                + "\"customerId\":\"1717832146707619842 \","
                + "\"customerStatus\":\"active\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/staff/manager/customer/updateCustomerStatus?satoken-staff=" + tokenValue)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody)
                        .header("satoken-staff", tokenValue))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("1000004"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
