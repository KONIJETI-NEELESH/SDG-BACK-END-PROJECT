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
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sdg.goals.model.Goal;


@Component
public class GoalHelper {
	private static final Logger logger = LoggerFactory.getLogger(GoalHelper.class);
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "GoalId", "GoalName", "GoalDescription"};
  

  public static boolean hasExcelFormat(MultipartFile goalFile) {
	  
	    if (!TYPE.equals(goalFile.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static List<Goal> excelToGoals(InputStream is) {
	    try {
	    	
	     
	      Workbook workbook = new XSSFWorkbook(is);

	      Sheet sheet = workbook.getSheetAt(0);
	     
	      Iterator<Row> rows = sheet.iterator();
	      List<Goal> goals = new ArrayList<Goal>();

	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();

	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }

	        Iterator<Cell> cellsInRow = currentRow.iterator();

	        Goal goal = new Goal();

	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();

	          switch (cellIdx) {
	          case 0:
	            goal.setGoalid((int) currentCell.getNumericCellValue());
	            break;

	          case 1:
	            goal.setGoalname(currentCell.getStringCellValue());
	            break;
	          case 2:
	            goal.setGoaldescription(currentCell.getStringCellValue());
	            break;

	           default:
	            break;
	          }

	          cellIdx++;
	        }

	        goals.add(goal);
	      }

	      workbook.close();
	     logger.info("Goals sheet has exported to GoalService successfully");
	   return goals;
	    } catch (IOException e) {
	    	logger.error("fail to parse Goals sheet",e);
	      throw new RuntimeException("fail to parse Goals sheet: " + e.getMessage());
	    }
	  }
	 

	public boolean hasExcelFormat1(MultipartFile file) {
		 String contentType = file.getContentType();
	        return contentType != null && (
	                contentType.equals("application/vnd.ms-excel") || contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	        );
	}

	
	}