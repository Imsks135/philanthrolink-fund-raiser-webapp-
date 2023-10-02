package com.stackroute.supportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTRequest {

    private String model;
    private List<Message> messages;
    private Integer max_tokens;
}
