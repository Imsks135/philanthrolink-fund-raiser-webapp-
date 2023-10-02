package com.stackroute.supportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choice {

    public int index;
    public Message message;
    public String finish_reason;
}
