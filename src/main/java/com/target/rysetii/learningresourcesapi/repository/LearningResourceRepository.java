package com.target.rysetii.learningresourcesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.target.rysetii.learningresourcesapi.entity.LearningResource;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningResourceRepository extends JpaRepository<LearningResource, Integer> {
}
