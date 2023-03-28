package com.sdg.goals.dto;

public class SubGoalDTO {
	
	 private int subgoalid;
     private String subgoalname;
     private String subgoaldescription;
     private int targetid;

     public SubGoalDTO(int subgoalid, String subgoalname, String subgoaldescription, int targetid) {
         this.subgoalid = subgoalid;
         this.subgoalname = subgoalname;
         this.subgoaldescription = subgoaldescription;
         this.targetid = targetid;
     }

     public int getSubgoalid() {
         return subgoalid;
     }

     public void setSubgoalid(int subgoalid) {
         this.subgoalid = subgoalid;
     }

     public String getSubgoalname() {
         return subgoalname;
     }

     public void setSubgoalname(String subgoalname) {
         this.subgoalname = subgoalname;
     }

     public String getSubgoaldescription() {
         return subgoaldescription;
     }

     public void setSubgoaldescription(String subgoaldescription) {
         this.subgoaldescription = subgoaldescription;
     }

     public int getTargetid() {
         return targetid;
     }

     public void setTargetid(int targetid) {
         this.targetid = targetid;
     }

 }


