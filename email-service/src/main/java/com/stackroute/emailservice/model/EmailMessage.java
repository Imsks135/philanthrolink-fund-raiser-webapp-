package com.stackroute.emailservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {

    private String to;
    private String subject;
    private String body;





    // You can add more fields and methods as needed.
}
