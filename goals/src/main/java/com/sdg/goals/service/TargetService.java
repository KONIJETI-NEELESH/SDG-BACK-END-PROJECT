package com.sdg.goals.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sdg.goals.dto.TargetDTO;
import com.sdg.goals.model.Target;

public interface TargetService {
    void save(MultipartFile file);
    List<Target> getAllTargets();
	Target findById(int targetId);
	List<Target> findAll();
	public List<TargetDTO> getAllTargets1();

}

