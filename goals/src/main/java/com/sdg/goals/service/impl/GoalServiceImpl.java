package com.sdg.goals.service.impl;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sdg.goals.exception.ResourceNotFoundException;
import com.sdg.goals.helper.GoalHelper;
import com.sdg.goals.model.Goal;
import com.sdg.goals.model.SubGoal;
import com.sdg.goals.model.Target;
import com.sdg.goals.repository.GoalRepository;
import com.sdg.goals.service.GoalService;


@Service
public class GoalServiceImpl implements GoalService {
	private static final Logger logger = LoggerFactory.getLogger(GoalServiceImpl.class);
  @Autowired
  GoalRepository repository;

  public void save(MultipartFile goalFile) {
    try {
      List<Goal> goals = GoalHelper.excelToGoals(goalFile.getInputStream());
      logger.info("Goals table has imported from GoalHelper");
      repository.saveAll(goals);
      logger.info("Goal table saved to repository");
    } catch (IOException e) {
      throw new RuntimeException("fail to store goals data: "+e);
    }
  }

  public List<Goal> getAllGoals() {
    return repository.findAll();
  }
  
  public Goal findById(int goalId) {
      Optional<Goal> goal = repository.findById(goalId);
      if (goal.isPresent()) {
          return goal.get();
      }
      return null;
  }
  @Override
  public Map<String, Object> getGoalDetails(int goalId) {
      Optional<Goal> optionalGoal = repository.findById(goalId);
      if (optionalGoal.isPresent()) {
          Goal goal = optionalGoal.get();
          List<Target> targets = goal.getTargets();

          List<List<Map<String, Object>>> subgoalsList = new ArrayList<>();

          for (Target target : targets) {
              List<Map<String, Object>> subgoals = new ArrayList<>();
              for (SubGoal subgoal : target.getSubgoals()) {
                  Map<String, Object> subgoalMap = new HashMap<>();
                  subgoalMap.put("subgoalId", subgoal.getSubgoalid());
                  subgoalMap.put("subgoalName", subgoal.getSubgoalname());
                  subgoals.add(subgoalMap);
              }
              subgoalsList.add(subgoals);
          }

          Map<String, Object> response = new HashMap<>();
          response.put("goalId", goal.getGoalid());
          response.put("goalName", goal.getGoalname());
          response.put("goaldescription", goal.getGoaldescription());
          List<Map<String, Object>> targetsList = new ArrayList<>();
          int i = 0;
          for (Target target : targets) {
              Map<String, Object> targetMap = new HashMap<>();
              targetMap.put("targetid", target.getTargetid());
              targetMap.put("targetname", target.getTargetname());
              targetMap.put("subgoals", subgoalsList.get(i));
              targetsList.add(targetMap);
              i++;
          }
          response.put("targets", targetsList);

          return response;
      } else {
          throw new ResourceNotFoundException("Goal not found with id: " + goalId);
      }
  }

  @Override
  public Map<String, Object> getGoalDetail(int goalId) {
      Optional<Goal> optionalGoal = repository.findById(goalId);
      if (optionalGoal.isPresent()) {
          Goal goal = optionalGoal.get();
          List<Target> targets = goal.getTargets();

          List<List<Map<String, Object>>> subgoalsList = new ArrayList<>();

          for (Target target : targets) {
	            List<Map<String, Object>> subgoals = new ArrayList<>();
	            for (SubGoal subgoal : target.getSubgoals()) {
	            	LinkedHashMap<String, Object> subgoalMap = new LinkedHashMap<>();
	                subgoalMap.put("name", subgoal.getSubgoalname());
	                subgoalMap.put("parent", target.getTargetname());
	                subgoals.add(subgoalMap);
	            }
	            subgoalsList.add(subgoals);
	        }

	        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
	        response.put("name", goal.getGoalname());
	        response.put("parent", "null");
	        List<Map<String, Object>> targetsList = new ArrayList<>();
	        int i = 0;
	        for (Target target : targets) {
	        	LinkedHashMap<String, Object> targetMap = new LinkedHashMap<>();
	            targetMap.put("name",target.getTargetname());
	            targetMap.put("parent",goal.getGoalname());
	            targetMap.put("children", subgoalsList.get(i));
	            targetsList.add(targetMap);
	            i++;
	        }
	        response.put("children", targetsList);
          return response;
      } else {
          throw new ResourceNotFoundException("Goal not found with id: " + goalId);
      }
  }

}

