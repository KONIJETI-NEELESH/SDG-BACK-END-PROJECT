package com.sdg.goals.service.impl;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sdg.goals.dto.SubGoalDTO;
import com.sdg.goals.helper.SubGoalHelper;
import com.sdg.goals.model.SubGoal;
import com.sdg.goals.repository.SubGoalRepository;
import com.sdg.goals.service.SubGoalService;



@Service
public class SubGoalServiceImpl implements SubGoalService {
	private static final Logger logger = LoggerFactory.getLogger(SubGoalServiceImpl.class);

  @Autowired
  SubGoalRepository repository;

  public void save(MultipartFile file) {
    try {
      List<SubGoal> subgoals = SubGoalHelper.excelToSubGoals(file.getInputStream());
      logger.info("Subgoals table has imported from SubGoalHelper");
      repository.saveAll(subgoals);
      logger.info("Subgoals table saved to repository");
    } catch (IOException e) {
        throw new RuntimeException("fail to store subgoals data: " + e.getMessage());
    }
  }

  public List<SubGoal> getAllSubgoals() {
    return repository.findAll();
  }

@Override
public List<SubGoal> findAll() {
	return repository.findAll();
}
public List<SubGoalDTO> getAllSubGoals() {
    List<SubGoal> subGoals = repository.findAll();
    List<SubGoalDTO> subGoalDTOs = new ArrayList<>();

    for (SubGoal subGoal : subGoals) {
        SubGoalDTO subGoalDTO = new SubGoalDTO(
            subGoal.getSubgoalid(),
            subGoal.getSubgoalname(),
            subGoal.getSubgoaldescription(),
            subGoal.getTarget().getTargetid()
        );
        subGoalDTOs.add(subGoalDTO);
    }
    return subGoalDTOs;
}

}

