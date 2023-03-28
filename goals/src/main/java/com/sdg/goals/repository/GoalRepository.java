package com.sdg.goals.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sdg.goals.model.Goal;



public interface GoalRepository extends JpaRepository<Goal, Integer> {
}