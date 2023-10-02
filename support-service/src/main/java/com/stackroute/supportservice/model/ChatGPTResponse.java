package com.stackroute.supportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTResponse {

    public String id;
    public String object;
    public int created;
    public List<Choice> choices;
    public Usage usage;
}
