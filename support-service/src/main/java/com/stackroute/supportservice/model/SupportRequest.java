package com.stackroute.supportservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "supportRequests")
public class SupportRequest {
      @Id
      private Long id;
      private String userId;
      private String title;
      private String description;
      private String status;
      private String response;
}
