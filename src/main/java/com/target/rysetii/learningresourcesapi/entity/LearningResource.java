package com.target.rysetii.learningresourcesapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "learningresources")
public class LearningResource {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "PRODUCTNAME")
    private String productName;
    @Column(name="cost_price")
    private double costPrice;
    @Column(name="selling_price")
    private double sellingPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "resource_status")
    private LearningResourceStatus learningResourceStatus;
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Column(name = "published_date")
    private LocalDate publishedDate;
    @Column(name = "retired_date")
    private LocalDate retiredDate;

    public LearningResource(int id, String productName, double costPrice, double sellingPrice, LearningResourceStatus learningResourceStatus, LocalDate createdDate, LocalDate publishedDate, LocalDate retiredDate) {
        super();
        this.id = id;
        this.productName = productName;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.learningResourceStatus = learningResourceStatus;
        this.createdDate = createdDate;
        this.publishedDate = publishedDate;
        this.retiredDate = retiredDate;
    }

    public LearningResource(){
        super();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public LearningResourceStatus getLearningResourceStatus() {
        return learningResourceStatus;
    }

    public void setLearningResourceStatus(LearningResourceStatus learningResourceStatus) {
        this.learningResourceStatus = learningResourceStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public LocalDate getRetiredDate() {
        return retiredDate;
    }

    public void setRetiredDate(LocalDate retiredDate) {
        this.retiredDate = retiredDate;
    }

    @Override
    public String toString() {
        return "LearningResource{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", learningResourceStatus=" + learningResourceStatus +
                ", createdDate=" + createdDate +
                ", publishedDate=" + publishedDate +
                ", retiredDate=" + retiredDate +
                '}';
    }
}
