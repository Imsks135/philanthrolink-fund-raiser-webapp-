package com.stackroute.donationservice.service;

import com.stackroute.donationservice.model.ProjectCategory;
import com.stackroute.donationservice.model.ProjectsCampaign;

import java.util.List;
import java.util.Optional;

public interface DonationnServices {
    public ProjectsCampaign addProjects(ProjectsCampaign projectsCampaign);
    public Iterable<ProjectsCampaign> getAllProjects();
    public boolean deleteProjectbyId(int projectId);
    public ProjectsCampaign getProjectById(int projectId);

    public ProjectsCampaign getProjectByName(String nameOfTheProject);
    public List<ProjectsCampaign> getProjectbyCategory(ProjectCategory category);

    public List<ProjectsCampaign> getProjectByEmail(String email);

    public ProjectsCampaign updateCampaign(ProjectsCampaign getCampaign, int projectId);

}
