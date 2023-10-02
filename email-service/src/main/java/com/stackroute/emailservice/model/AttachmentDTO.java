package com.stackroute.emailservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDTO {
    private String filename;
    private byte[] content;
}
