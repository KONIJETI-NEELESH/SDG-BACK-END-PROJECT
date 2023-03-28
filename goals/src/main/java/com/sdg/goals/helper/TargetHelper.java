package com.sdg.goals.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.sdg.goals.model.Goal;
import com.sdg.goals.model.Target;
import com.sdg.goals.service.GoalService;



public class TargetHelper {
	private static final Logger logger = LoggerFactory.getLogger(TargetHelper.class);
	@Autowired
	 public  GoalService goalservice;
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "TargetId","GoalId","TargetName","TargetDescription"};
  

  public static  boolean hasExcelFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static  List<Target> excelToTargets(InputStream is) {
	    try {
	    	
	     
	      Workbook workbook = new XSSFWorkbook(is);

	      Sheet sheet = workbook.getSheetAt(1);
	     
	      Iterator<Row> rows = sheet.iterator();
	      List<Target> targets = new ArrayList<Target>();

	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();

	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }

	        Iterator<Cell> cellsInRow = currentRow.iterator();

	        Target target = new Target();

	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();

	          switch (cellIdx) {
	        
	          case 0:
		            target.setTargetid((int)currentCell.getNumericCellValue());
		            break;
		            
	         
	          case 1:
	              int goalId = (int) currentCell.getNumericCellValue();
	              Goal goal = new Goal();
	              target.setGoal(goal);
	              target.setGoalid(goalId);
	              break; 
	          case 2:
		            target.setTargetname(currentCell.getStringCellValue());
		            break;
		            
	          case 3:
		            target.setTargetdescription(currentCell.getStringCellValue());
		            break;
	          
	          default:
	            break;
	          }

	          cellIdx++;
	        }

	        targets.add(target);
	      }

	      workbook.close();
	      logger.info("Targets sheet has exported to TargetService successfully");
	   return targets;
	    } catch (IOException e) {
	    	logger.error("fail to parse targets sheet",e);
	      throw new RuntimeException("fail to parse targets sheet: " + e.getMessage());
	    }
	  }
	}