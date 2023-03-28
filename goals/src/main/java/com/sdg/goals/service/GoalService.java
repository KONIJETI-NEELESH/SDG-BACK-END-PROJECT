package com.sdg.goals.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sdg.goals.model.Goal;

public interface GoalService {
	
    void save(MultipartFile file);
    List<Goal> getAllGoals();
    Goal findById(int goalId);
    Map<String, Object> getGoalDetails(int goalId);
    Map<String, Object> getGoalDetail(int goalId);
}
