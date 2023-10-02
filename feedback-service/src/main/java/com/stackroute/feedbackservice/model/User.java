package com.stackroute.feedbackservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Document
public class User {
    @Id
    private String name;
    private String email;
    private int rating;
    private String feedback;

}
