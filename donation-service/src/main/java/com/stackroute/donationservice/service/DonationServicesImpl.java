package com.stackroute.donationservice.service;

import com.stackroute.donationservice.model.ProjectCategory;
import com.stackroute.donationservice.model.ProjectsCampaign;
import com.stackroute.donationservice.repository.CampaignRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationServicesImpl implements DonationnServices{

    @Autowired
    private CampaignRepo campaignRepo;

    @Override
    public ProjectsCampaign addProjects(ProjectsCampaign projectsCampaign) {
        ProjectsCampaign newproject = campaignRepo.save(projectsCampaign);
        return  newproject;
    }

    @Override
    public Iterable<ProjectsCampaign> getAllProjects() {
        return campaignRepo.findAll();
    }


    @Override
    public boolean deleteProjectbyId(int projectId) {
        Optional<ProjectsCampaign> optional = campaignRepo.findById(projectId);
        boolean isDeleted = false;
        if(optional.isPresent()) {
            campaignRepo.deleteById(projectId);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public ProjectsCampaign getProjectById(int projectId) {
        Optional<ProjectsCampaign> optional = campaignRepo.findById(projectId);
        ProjectsCampaign getprojbyId = optional.isPresent()?optional.get():null;
        return  getprojbyId;
    }

    @Override
    public ProjectsCampaign getProjectByName(String nameOfTheProject) {
        Optional<ProjectsCampaign> optional = campaignRepo.findBynameOfTheProject(nameOfTheProject);
        ProjectsCampaign getbyName = optional.isPresent()?optional.get():null;
        return getbyName;
    }

    @Override
    public List<ProjectsCampaign> getProjectbyCategory(ProjectCategory category) {
        List<ProjectsCampaign> getcamp = campaignRepo.findBycategory(category);
        return  getcamp;
    }

    @Override
    public List<ProjectsCampaign> getProjectByEmail(String email) {
        List<ProjectsCampaign> getcampbye = campaignRepo.findByemail(email);
        return getcampbye;
    }

    @Override
    public ProjectsCampaign updateCampaign( ProjectsCampaign getCampaign, int projectId) {
        ProjectsCampaign updateeCampaign = campaignRepo.findById(projectId).get();
        if (updateeCampaign != null) {
            updateeCampaign.setName(getCampaign.getName());
            updateeCampaign.setEmail(getCampaign.getEmail());
            updateeCampaign.setContactNumber(getCampaign.getContactNumber());
            updateeCampaign.setImageUrl(getCampaign.getImageUrl());
            updateeCampaign.setNameOfTheProject(getCampaign.getNameOfTheProject());
            updateeCampaign.setProjectDescription(getCampaign.getProjectDescription());
            updateeCampaign.setCategory(getCampaign.getCategory());
            updateeCampaign.setTargetedAmount(getCampaign.getTargetedAmount());
            updateeCampaign.setProjectStatus(getCampaign.getProjectStatus());
        }

        return  campaignRepo.save(updateeCampaign);


    }
}
