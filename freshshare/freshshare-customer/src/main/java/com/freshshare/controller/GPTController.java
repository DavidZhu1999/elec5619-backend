package com.freshshare.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.freshshare.request.GPTRequest;
import com.freshshare.response.CustomerResponse;
import com.freshshare.response.CustomerResponseEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/gpt")
@CrossOrigin
public class GPTController {

    @Value("${gpt.api.key}")
    private String gptApiKey;

    @Value("${gpt.api.url}")
    private String gptApiUrl;

    @PostMapping("/getResult")
    public CustomerResponse gpt(@RequestBody GPTRequest gptRequest){
        String backgroundMessage = "{\"role\":\"system\", \"content\":\"" + "Now I have some ingredients, please tell me how to process them and give me some recipes " + "\"}";
        String ingredientsMessage = "{\"role\":\"customer\", \"content\":\"" + gptRequest.getContent() + "\"}";

        try {
            URL url = new URL(gptApiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to POST
            connection.setRequestMethod("POST");

            // Add headers
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", gptApiKey);

            // Enable input/output streams
            connection.setDoOutput(true);

            JSONObject jsonRoot = new JSONObject();
            jsonRoot.put("model", "gpt-4");
            jsonRoot.put("max_tokens", 200);

            JSONArray messageeArray = JSONArray.parseArray("[" + backgroundMessage + "]");
            JSONObject newUserMessage1 = new JSONObject();
            newUserMessage1.put("role", "user");
            newUserMessage1.put("content", ingredientsMessage);
            messageeArray.add(newUserMessage1);
            jsonRoot.put("messages", messageeArray);

            // JSON body
            String jsonBody = jsonRoot.toJSONString();

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JSONObject json = JSONObject.parseObject(response.toString());
                String result = json.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
//                System.out.println(response.toString());
                return new CustomerResponse(CustomerResponseEnum.GPT_SUCCESS, result);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
