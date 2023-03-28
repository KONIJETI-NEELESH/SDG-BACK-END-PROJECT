package com.sdg.goals.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sdg.goals.dto.SubGoalDTO;
import com.sdg.goals.model.SubGoal;

public interface SubGoalService {
    void save(MultipartFile file);
    List<SubGoal> getAllSubgoals();
	List<SubGoal> findAll();
	public List<SubGoalDTO> getAllSubGoals();
}

