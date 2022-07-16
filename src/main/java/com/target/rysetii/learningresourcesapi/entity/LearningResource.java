package com.target.rysetii.learningresourcesapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningResource {
    private int id;
    private String productName;
    private double costPrice;
    private double sellingPrice;
    private LearningResourceStatus learningResourceStatus;
    private LocalDate createdDate;
    private LocalDate publishedDate;
    private LocalDate retiredDate;
}
