package com.sdg.goals.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sdg.goals.helper.GoalHelper;
import com.sdg.goals.service.GoalService;
import com.sdg.goals.service.SubGoalService;
import com.sdg.goals.service.TargetService;
import com.sdg.goals.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private GoalService goalService;

    @Autowired
    private TargetService targetService;

    @Autowired
    private SubGoalService subgoalService;
    
    @Autowired
    public  GoalHelper goalHelper;

    @Override
    public void uploadData(MultipartFile file, String fileType) throws Exception {
        if (!hasExcelFormat1(file)) {
            throw new IllegalArgumentException("Please upload an excel file for " + fileType + " data!");
        }

        switch (fileType) {
            case "goals":
                goalService.save(file);
                break;
            case "targets":
                targetService.save(file);
                break;
            case "subgoals":
                subgoalService.save(file);
                break;
            case "all":
                goalService.save(file);
                targetService.save(file);
                subgoalService.save(file);
                break;
            default:
                throw new IllegalArgumentException("Invalid data type specified!");
        }
    }

    @Override
	public boolean hasExcelFormat1(MultipartFile file) {
		
		return goalHelper.hasExcelFormat1(file);
	}

	

	
   
}
