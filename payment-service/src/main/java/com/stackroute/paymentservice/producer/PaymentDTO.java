package com.stackroute.paymentservice.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private JSONObject jsonObject;
}
