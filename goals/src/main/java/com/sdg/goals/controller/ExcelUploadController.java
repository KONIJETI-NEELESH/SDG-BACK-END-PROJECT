package com.sdg.goals.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sdg.goals.dto.SubGoalDTO;
import com.sdg.goals.dto.TargetDTO;
import com.sdg.goals.dto.UserDTO;
import com.sdg.goals.message.ResponseMessage;
import com.sdg.goals.model.Goal;
import com.sdg.goals.service.GoalService;
import com.sdg.goals.service.SubGoalService;
import com.sdg.goals.service.TargetService;
import com.sdg.goals.service.UploadService;
import com.sdg.goals.service.UserService;

@CrossOrigin("http://localhost:3000")
@Controller
@RequestMapping("/api/excel")
public class ExcelUploadController {
	private static final Logger logger = LoggerFactory.getLogger(ExcelUploadController.class);
	  @Autowired
	  GoalService goalService;
	  @Autowired
	  TargetService targetService;
	  @Autowired
	  SubGoalService subgoalService;
	  @Autowired
	  UploadService uploadService;

	  @Autowired
	    private UserService userService;

    
	    @PostMapping("/create/user")
	    public ResponseEntity<String> createUser(@RequestBody Map<String, String> request) {
	        String username = request.get("username");
	        String password = request.get("password");
	        
	        // Check if the user already exists
	        if (userService.getUserByUsername(username) != null) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
	        }

	       
	        // Create the new user
	        UserDTO userDTO = new UserDTO(username, password);
	        userService.createUser(userDTO);

	        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> request) {
	        String username = request.get("username");
	        String password = request.get("password");
	        
	        // Check if the user already exists
	        if (userService.getUserByUsername(username) != null) {
	         //   return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
	        	if (userService.isUserAuthorized(username, password)) {
	        		return ResponseEntity.status(HttpStatus.OK).body("login successfull");
	        	}
	        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	        }

        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	        
	    }

	  @PostMapping("/uploaddata")
	    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
	                                                        @RequestParam("filetype") String fileType) {
	        String message = "";

	        try {
	            uploadService.uploadData(file, fileType);
	            message = "Uploaded " + fileType + " file successfully: " + file.getOriginalFilename();
	            logger.info(message);
	            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	        } catch (IllegalArgumentException e) {
	            message = e.getMessage();
	            logger.info(message);
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	        } catch (Exception e) {
	            logger.error("Error uploading file", e);
	            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	            logger.info(message);
	            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	        }
	    }
	  @GetMapping("/goals/{goalid}")
	    public ResponseEntity<Map<String, Object>> getGoalDetails(@PathVariable("goalid") int goalId) {
	        Map<String, Object> response = goalService.getGoalDetails(goalId);
	        logger.info("successfully retrieved the goals based on id");
	        return ResponseEntity.ok(response);
	    }
	  
	  @GetMapping("/treegoals/{goalid}")
	    public ResponseEntity<Map<String, Object>> getGoalDetail(@PathVariable("goalid") int goalId) {
	        Map<String, Object> response = goalService.getGoalDetail(goalId);
	        logger.info("successfully retrieved the goals based on id");
	        return ResponseEntity.ok(response);
	    }

	  @GetMapping("/subgoals")
	  public ResponseEntity<List<SubGoalDTO>> getAllSubGoals() {
	      List<SubGoalDTO> subGoalDTOs = subgoalService.getAllSubGoals();
	      logger.info("successfully retrieved subgoals file");
	      return ResponseEntity.ok().body(subGoalDTOs);
	  }

	    @GetMapping("/targets")
	    public ResponseEntity<List<TargetDTO>> getAllTargets1() {
	   List<TargetDTO> targetDTOs = targetService.getAllTargets1();       
	        logger.info("successfully retrieved targets file")  ;    
	        return ResponseEntity.ok().body(targetDTOs);
	    }
	    
	   @GetMapping("/goals")
		  public ResponseEntity<List<Goal>> getAllGoals() {
		    try { List<Goal> goals = goalService.getAllGoals();
		    	if (goals.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }  logger.info("Getting all goals ");
		      return new ResponseEntity<>(goals, HttpStatus.OK);
		    } catch (Exception e) {
		    	 logger.error("Error getting  file", e);
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		  }

	  
	
}
