package com.target.rysetii.learningresourcesapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.target.rysetii.learningresourcesapi.entity.LearningResource;
import com.target.rysetii.learningresourcesapi.service.LearningResourceService;

import java.util.List;

@RestController
@RequestMapping("/Data")
public class LearningResourceController {
    @Autowired
    LearningResourceService learningResourceService;

    @GetMapping("/Data")
    public List<LearningResource> getResources(){
        return learningResourceService.getLearningResource();
    }

    @PostMapping("/Data")
    public String putResources(@RequestBody List<LearningResource> learningResources){
        learningResourceService.saveLearningResources(learningResources);
        return "Resources added successfully";
    }

    @DeleteMapping("Data/{id}")
    public String deleteResource(@PathVariable int id){
    return learningResourceService.deleteLearningResource(id);
    }

}
