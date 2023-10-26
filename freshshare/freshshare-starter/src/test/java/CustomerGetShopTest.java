import com.freshshare.starter.Starter;
import jakarta.annotation.Resource;
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
public class CustomerGetShopTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    void viewShops() throws Exception {
        String jsonRequestBody = "{"
                + "\"latitude\":\"-33.878159\","
                + "\"longitude\":\"151.206955\""
                + "}";


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customer/shop/viewShops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100000"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void viewShopItems() throws Exception {
        String jsonRequestBody = "{"
                + "\"businessId\":\"1714643251402977282\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customer/shop/viewShopItems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100010"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void getShopAddress() throws Exception {
        String jsonRequestBody = "{"
                + "\"businessId\":\"1714643251402977282\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customer/shop/getShopAddress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("100050"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
