package com.target.rysetii.learningresourcesapi.service;

import com.target.rysetii.learningresourcesapi.entity.LearningResource;
import com.target.rysetii.learningresourcesapi.entity.LearningResourceStatus;
import com.target.rysetii.learningresourcesapi.repository.LearningResourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LearningResourceServiceTests {
    @Mock
    LearningResourceRepository lr;

    @InjectMocks
    LearningResourceService ls;

    @Test
    public void getProfMarginsOfAll(){
        List<LearningResource> learningResources = new ArrayList<>();
        LearningResource learningResource1 = new LearningResource(1311, "Test Name 1", 100.0, 120.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResource learningResource2 = new LearningResource(1312, "Test Name 2", 120.0, 180.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        learningResources.add(learningResource1);
        learningResources.add(learningResource2);

        List<Double> expectedProfitMargins = new ArrayList<>();
        for (LearningResource learningResource:learningResources) {
            expectedProfitMargins.add((learningResource.getSellingPrice()-learningResource.getCostPrice())/learningResource.getSellingPrice());
        }

        when(lr.findAll()).thenReturn(learningResources);

        List<Double> actualProfitMargins = ls.getProfitMargin();
        assertEquals(expectedProfitMargins, actualProfitMargins, "Wrong profit margins");
    }
    @Test
    public void sortTheLearningResourceBasedOnProfitMarginInNonIncreasingOrder(){
        List<LearningResource> learningResources = new ArrayList<>();
        LearningResource learningResource1 = new LearningResource(1311, "Test Name 1", 100.0, 120.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResource learningResource2 = new LearningResource(1312, "Test Name 2", 120.0, 180.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        learningResources.add(learningResource1);
        learningResources.add(learningResource2);

        learningResources.sort((lr1, lr2) -> {
            Double profitMargin1 = (lr1.getSellingPrice() - lr1.getCostPrice())/lr1.getSellingPrice();
            Double profitMargin2 = (lr2.getSellingPrice() - lr2.getCostPrice())/lr2.getSellingPrice();

            return profitMargin2.compareTo(profitMargin1) ;
        });

        when(lr.findAll()).thenReturn(learningResources);

        List<LearningResource> learningResourcesSorted = ls.sortlearningResource();

        assertEquals(learningResources, learningResourcesSorted, "The learning resources are not sorted by profit margin");
    }
    @Test
    public void saveTheLearningResources(){
        List<LearningResource> learningResources = new ArrayList<>();
        LearningResource learningResource1 = new LearningResource(1311, "Test Name 1", 100.0, 120.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResource learningResource2 = new LearningResource(1312, "Test Name 2", 120.0, 180.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        LearningResource learningResource3 = new LearningResource(1313, "Test Name 3", 120.0, 180.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        learningResources.add(learningResource1);
        learningResources.add(learningResource2);
        learningResources.add(learningResource3);

        ls.saveLearningResources(learningResources);

        verify(lr, times(learningResources.size())).save(any(LearningResource.class));
    }
    @Test
    public void deleteLearningResourceById(){
        int id = 1234;
        ls.deleteLearningResource(id);
        verify(lr, times(1)).deleteById(id);
    }

}
