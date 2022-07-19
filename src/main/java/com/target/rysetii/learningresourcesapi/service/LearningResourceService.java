package com.target.rysetii.learningresourcesapi.service;

import com.target.rysetii.learningresourcesapi.entity.LearningResource;
import com.target.rysetii.learningresourcesapi.repository.LearningResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class LearningResourceService {

    @Autowired
    private final LearningResourceRepository learningResourceRepo;

    public LearningResourceService(LearningResourceRepository learningResourceRepo) {
        this.learningResourceRepo = learningResourceRepo;
    }

    public List<LearningResource> getLearningResource(){
        List<LearningResource> learningResource = new ArrayList();
        learningResourceRepo .findAll().forEach(i -> learningResource.add(i));
        return learningResource;
    }

    public void saveLearningResources(List<LearningResource> learningResources){
        for(LearningResource i : learningResources){
            learningResourceRepo.save(i);
        }
    }
    public List<Double> getProfitMargin(){
        List<LearningResource> list = getLearningResource();
        List<Double> profitMargins = new ArrayList<>();
        for(LearningResource i : list){
            Double a = (i.getSellingPrice() - i.getCostPrice())/i.getSellingPrice();
            profitMargins.add(a);
        }
        return profitMargins;
    }

    public List<LearningResource> sortlearningResource(){
        List<LearningResource> list = getLearningResource();
        list.sort((l1,l2)->{
            Double a = (l1.getSellingPrice() - l1.getCostPrice())/l1.getSellingPrice();
            Double b = (l2.getSellingPrice() - l2.getCostPrice())/l2.getSellingPrice();
            return b.compareTo(a);
        });
        return list;
    }
    public String deleteLearningResource(int id){
        if(learningResourceRepo.existsById(id)){
            learningResourceRepo.deleteById(id);
            return "Successfully deleted entry";
        }
        else return "Learning Resource doesn't exist";
    }
}
