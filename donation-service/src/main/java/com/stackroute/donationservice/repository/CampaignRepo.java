package com.stackroute.donationservice.repository;

import com.stackroute.donationservice.model.ProjectCategory;
import com.stackroute.donationservice.model.ProjectsCampaign;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepo extends ElasticsearchRepository<ProjectsCampaign,Integer> {
    public Optional<ProjectsCampaign> findBynameOfTheProject(String nameOfTheProject);
    public List<ProjectsCampaign> findBycategory(ProjectCategory category);

    public List<ProjectsCampaign> findByemail(String email);
}
