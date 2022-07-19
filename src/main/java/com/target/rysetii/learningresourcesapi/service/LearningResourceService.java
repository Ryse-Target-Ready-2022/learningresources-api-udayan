package com.target.rysetii.learningresourcesapi.service;

import com.target.rysetii.learningresourcesapi.entity.LearningResource;
import com.target.rysetii.learningresourcesapi.entity.LearningResourceStatus;
import com.target.rysetii.learningresourcesapi.repository.LearningResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class LearningResourceService {

    @Autowired
    private LearningResourceRepository learningResourceRepo;

    private List<LearningResource> getLearningResource(){
        List<LearningResource> learningResource = new ArrayList();
        learningResourceRepo .findAll().forEach(i -> learningResource.add(i));
        return learningResource;
    }

    private void saveLearningResourcesIntoFile(List<LearningResource> learningResources){
        for(LearningResource i : learningResources){
            learningResourceRepo.save(i);
        }
    }
    private List<Double> getProfitMargin(){
        List<LearningResource> list = getLearningResource();
        List<Double> profitMargins = new ArrayList<>();
        for(LearningResource i : list){
            Double a = (i.getSellingPrice() - i.getCostPrice())/i.getSellingPrice();
            profitMargins.add(a);
        }
        return profitMargins;
    }

    private List<LearningResource> sortlearningResource(){
        List<LearningResource> list = getLearningResource();
        list.sort((l1,l2)->{
            Double a = (l1.getSellingPrice() - l1.getCostPrice())/l1.getSellingPrice();
            Double b = (l2.getSellingPrice() - l2.getCostPrice())/l2.getSellingPrice();
            return b.compareTo(a);
        });
        return list;
    }
}
