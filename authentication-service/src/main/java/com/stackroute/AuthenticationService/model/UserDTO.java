package com.stackroute.AuthenticationService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private JSONObject jsonObject;
}
