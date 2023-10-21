package com.freshshare.controller.Dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatRequest {

    private String model;
    private List<Message> messages;
    private int max_tokens=1000;
    //private double temperature;

    public ChatRequest(String model, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();

        this.messages.add(new Message("user", prompt));
    }

    // getters and setters
}