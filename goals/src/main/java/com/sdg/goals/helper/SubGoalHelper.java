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

import com.sdg.goals.model.SubGoal;
import com.sdg.goals.model.Target;
import com.sdg.goals.service.TargetService;



public class SubGoalHelper {
	private static final Logger logger = LoggerFactory.getLogger(SubGoalHelper.class);
	@Autowired
	public
	TargetService targetservice;
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "SubGoalId","TargetId","SubGoalName","SubGoalDescription"};
  

  public static boolean hasExcelFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static List<SubGoal> excelToSubGoals(InputStream is) {
	    try {
	    	
	     
	      Workbook workbook = new XSSFWorkbook(is);

	      Sheet sheet = workbook.getSheetAt(2);
	     
	      Iterator<Row> rows = sheet.iterator();
	      List<SubGoal> subgoals = new ArrayList<SubGoal>();

	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();

	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }

	        Iterator<Cell> cellsInRow = currentRow.iterator();

	        SubGoal subgoal = new SubGoal();

	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();

	          switch (cellIdx) {
	          case 0:
	            subgoal.setSubgoalid((int) currentCell.getNumericCellValue());
	            break;

	          case 1:
	        	  int targetId = (int) currentCell.getNumericCellValue();
	              Target target = new Target();
	              subgoal.setTarget(target);
	              subgoal.setTargetid(targetId);
	              break; 
	          case 2:
		            subgoal.setSubgoalname(currentCell.getStringCellValue());
		            break;
	          case 3:
		            subgoal.setSubgoaldescription(currentCell.getStringCellValue());
		            break;
	          
	          default:
	            break;
	          }

	          cellIdx++;
	        }

	        subgoals.add(subgoal);
	      }

	      workbook.close();
	      logger.info("Suubgoals sheet has exported to SubGoalService successfully");
	   return subgoals;
	    } catch (IOException e) {
	    	logger.error("fail to parse Subgoals sheet",e);
	      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
	    }
	  }
	}