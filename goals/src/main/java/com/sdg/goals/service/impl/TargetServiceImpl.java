package com.sdg.goals.service.impl;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sdg.goals.dto.TargetDTO;
import com.sdg.goals.helper.TargetHelper;
import com.sdg.goals.model.Target;
import com.sdg.goals.repository.TargetRepository;
import com.sdg.goals.service.TargetService;


@Service
public class TargetServiceImpl implements TargetService {
	private static final Logger logger = LoggerFactory.getLogger(GoalServiceImpl.class);

  @Autowired
  TargetRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Target> targets = TargetHelper.excelToTargets(file.getInputStream());
      logger.info("Targets table has imported from TargetHelper");

      repository.saveAll(targets);
      logger.info("Target table saved to repository");

    } catch (IOException e) {
        throw new RuntimeException("fail to store targets data: " + e.getMessage() + getClass().getName() + Thread.currentThread().getStackTrace()[1].getLineNumber());
    }
  }

  public List<Target> getAllTargets() {
    return repository.findAll();
  }
  @Override
  public Target findById(int targetId) {
      Optional<Target> target = repository.findById(targetId);
      if (target.isPresent()) {
          return target.get();
      }
      return null;
  }

  public List<TargetDTO> getAllTargets1() {
	    List<Target> targets = repository.findAll();
	    List<TargetDTO> targetDTOs = new ArrayList<>();

	    for (Target target : targets) {
	    	TargetDTO targetDTO = new TargetDTO(
	    			target.getTargetid(),
	    			target.getTargetname(),
	    			target.getTargetdescription(),
	    			target.getGoal().getGoalid()
	        );
	    	targetDTOs.add(targetDTO);
	    }
	    return targetDTOs;
	}

@Override
public List<Target> findAll() {
	return repository.findAll();
}
}

