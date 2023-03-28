package com.sdg.goals.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sdg.goals.model.SubGoal;



public interface SubGoalRepository extends JpaRepository<SubGoal, Integer> {
}