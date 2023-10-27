import com.freshshare.starter.Starter;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SpringBootTest(classes = Starter.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class POrderControllerTest {

    @Resource
    private MockMvc mockMvc;

    /**
     *
     * @param orderId
     * @throws Exception
     * Note that the input parameter order id is written manually in the comment
     * Note that the input parameter order id is written manually in the comment
     * Note that the input parameter order id is written manually in the comment
     */
    @ParameterizedTest
    @ValueSource(strings = {"1","2","3"}) // Different order ids can be listed here to test
    void acceptOrder(String orderId) throws Exception {
        String jsonRequestBody = "{\"order_id\":\"" + orderId + "\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/business/order/accept")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").value("update order successfully 1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
