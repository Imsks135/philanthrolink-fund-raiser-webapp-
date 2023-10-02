package com.stackroute.donationservice.controller;

import com.stackroute.donationservice.model.ProjectCategory;
import com.stackroute.donationservice.model.ProjectsCampaign;
import com.stackroute.donationservice.service.DonationnServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campaigns")
public class ProjectControll {

    @Autowired
    private DonationnServices donationnServices;


    @GetMapping("/")
    public String Home(){
        return "The Application is Running";
    }

//    @CrossOrigin("http://localhost:4200")
    @GetMapping("/getall")
    public ResponseEntity<?> getAllProjects(){
        ResponseEntity<?> entity = null;
        try{
            Iterable<ProjectsCampaign> campaignList = donationnServices.getAllProjects();
            entity = new ResponseEntity<Iterable<ProjectsCampaign>>(campaignList,HttpStatus.OK);
        }

        catch (Exception e){
            entity = new ResponseEntity<String>("Error...."+ e.getMessage(),HttpStatus.CONFLICT);
        }
        return entity;
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable int id){
        ResponseEntity<?> entity = null;

        try{
            ProjectsCampaign getbyid = donationnServices.getProjectById(id);
            if (getbyid != null){
                entity = new ResponseEntity<ProjectsCampaign>(getbyid,HttpStatus.OK);
            }
            else {
                entity = new ResponseEntity<String>("Error not found", HttpStatus.CONFLICT);
            }
        }
        catch (Exception e){
            entity = new ResponseEntity<String>("Error" + e.getMessage(),HttpStatus.CONFLICT);
        }
        return entity;
    }
//    @CrossOrigin("http://localhost:4200")
    @PostMapping("/addNew")
    public ResponseEntity<?> addProjects(@RequestBody ProjectsCampaign projectsCampaign){
        ResponseEntity<?> entity = null;
        try{
            ProjectsCampaign newproject = donationnServices.addProjects(projectsCampaign);
            if (newproject != null){
                entity = new ResponseEntity<ProjectsCampaign>(newproject, HttpStatus.CREATED);
            }
            else {
                entity = new ResponseEntity<String>("Error... no new project added", HttpStatus.CONFLICT);
            }

        }
        catch(Exception e){
            entity = new ResponseEntity<String>("Error..." + e.getMessage(),HttpStatus.CONFLICT);

        }
        return entity;
    }
//    @CrossOrigin("http://localhost:4200")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteProjectbyId(@PathVariable int id){
        ResponseEntity<?> entity = null;
        try{
            boolean removeCampaign = donationnServices.deleteProjectbyId(id);
            if(removeCampaign != false) {
                entity = new ResponseEntity<Boolean>(true, HttpStatus.OK);
            }
            else {
                entity = new ResponseEntity<String>("Id not found", HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e){
            entity = new ResponseEntity<String>("Error" + e.getMessage(),HttpStatus.CONFLICT);
        }
        return entity;

    }
//    @CrossOrigin("http://localhost:4200")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCampaign(@RequestBody ProjectsCampaign getCampaign, @PathVariable("id") int Id){
        ResponseEntity<?> entity = null;
        try{
            ProjectsCampaign editProject = donationnServices.updateCampaign(getCampaign, Id);
            if (editProject != null){
                entity = new ResponseEntity<ProjectsCampaign>(editProject, HttpStatus.OK);
            }
            else{
                entity = new ResponseEntity<String>("Error Id not forund", HttpStatus.CONFLICT);
            }
        }
        catch (Exception e){
            entity = new ResponseEntity<String>("Error.." + e.getMessage(),HttpStatus.CONFLICT);
        }

        return  entity;
    }

//    @CrossOrigin("http://localhost:4200")
    @GetMapping("getbyname/{name}")
    public ResponseEntity<?> getProjectByName(@PathVariable String name){
        ResponseEntity<?> entity = null;

        try{
            ProjectsCampaign getbyname = donationnServices.getProjectByName(name);
            if (getbyname != null){
                entity = new ResponseEntity<ProjectsCampaign>(getbyname,HttpStatus.OK);
            }
            else {
                entity = new ResponseEntity<String>("Error not found", HttpStatus.CONFLICT);
            }
        }
        catch (Exception e){
            entity = new ResponseEntity<String>("Error" + e.getMessage(),HttpStatus.CONFLICT);
        }
        return entity;
    }

//    @CrossOrigin("http://localhost:4200")
    @GetMapping("category/{category}")
    public ResponseEntity<?> getProjectbyCategory(@PathVariable ProjectCategory category){
        ResponseEntity<?> entity = null;

        try{
            List<ProjectsCampaign> getbycategory = donationnServices.getProjectbyCategory(category);
            if (getbycategory != null){
                entity = new ResponseEntity<List<ProjectsCampaign>>(getbycategory,HttpStatus.OK);
            }
            else {
                entity = new ResponseEntity<String>("Error not found", HttpStatus.CONFLICT);
            }
        }
        catch (Exception e){
            entity = new ResponseEntity<String>("Error" + e.getMessage(),HttpStatus.CONFLICT);
        }
        return entity;
    }

//    @CrossOrigin("http://localhost:4200")
    @GetMapping("email/{email}")
    public ResponseEntity<?> getProjectByEmail(@PathVariable String email){
        ResponseEntity<?> entity = null;

        try{
            List<ProjectsCampaign> getbyemail = donationnServices.getProjectByEmail(email);
            if (getbyemail != null){
                entity = new ResponseEntity<List<ProjectsCampaign>>(getbyemail,HttpStatus.OK);
            }
            else {
                entity = new ResponseEntity<String>("Error not found", HttpStatus.CONFLICT);
            }
        }
        catch (Exception e){
            entity = new ResponseEntity<String>("Error" + e.getMessage(),HttpStatus.CONFLICT);
        }
        return entity;
    }

}

