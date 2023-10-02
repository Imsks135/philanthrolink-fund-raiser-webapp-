package com.stackroute.donationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="campaigns")
public class ProjectsCampaign {

    @Id
    private Integer projectId;
    private String name;
    private Long contactNumber;
    private String email;
    private  String nameOfTheProject;
    private String imageUrl;
    private String projectDescription;
    private Integer targetedAmount;
    private Float projectStatus;

    @Enumerated(EnumType.STRING)
    private ProjectCategory category;

}
