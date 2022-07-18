package com.target.rysetii.learningresourcesapi.service;

import com.target.rysetii.learningresourcesapi.entity.LearningResource;
import com.target.rysetii.learningresourcesapi.entity.LearningResourceStatus;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LearningResourceService {
    private List<LearningResource> getFromFile(File filename){
        List<LearningResource> learningResource = new ArrayList<>();
        try
        {
            FileReader file;
            file = new FileReader(filename);
            BufferedReader br = new BufferedReader(file);
            String line = br.readLine();
            while (line != null){
                String attr[] = line.split(",");
                LearningResource ls = formatter(attr);
                line = br.readLine();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return learningResource;
    }
    private LearningResource formatter(String attr[]){
        Integer id = Integer.parseInt(attr[0].replaceAll("\\D+",""));
        String name = attr[1];
        Double costPrice = Double.parseDouble(attr[2]);
        Double sellingPrice = Double.parseDouble(attr[3]);
        LearningResourceStatus learningResourcestatus = LearningResourceStatus.valueOf(attr[4]);
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate createdDate = LocalDate.parse(attr[5],dtf1);
        LocalDate publishedDate = LocalDate.parse(attr[6],dtf1);
        LocalDate retiredDate = LocalDate.parse(attr[7],dtf1);
        LearningResource learningResource = new LearningResource(id, name, costPrice, sellingPrice, learningResourcestatus, createdDate, publishedDate, retiredDate);
        return learningResource;
    }
    private List<LearningResource> getLearningResource(){
        File file = new File("LearningResources.csv");
        List<LearningResource> learningResourceFile = getFromFile(file);
        return learningResourceFile;
    }

    private void saveLearningResourcesIntoFile(List<LearningResource> learningResources){
        try {
            File learningResourceFile = new File("learningResource.csv");
            FileWriter w1 = new FileWriter(learningResourceFile, true);
            BufferedWriter bw = new BufferedWriter(w1);
            for(LearningResource learningResource : learningResources){
                bw.newLine();
                StringBuffer line=new StringBuffer();
                line.append(learningResource.getId());
                line.append(",");
                line.append(learningResource.getProductName());
                line.append(",");
                line.append(learningResource.getCostPrice());
                line.append(",");
                line.append(learningResource.getSellingPrice());
                line.append(",");
                line.append(learningResource.getLearningResourceStatus());
                line.append(",");
                line.append(learningResource.getCreatedDate());
                line.append(",");
                line.append(learningResource.getPublishedDate());
                line.append(",");
                line.append(learningResource.getRetiredDate());
                line.append(",");
                bw.write(line.toString());
            }
            bw.flush();
            bw.close();
        } catch (Exception e){
            System.out.println(e);
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
