package com.sdg.goals.dto;

public class TargetDTO {
    private int targetid;
    private String targetname;
    private String targetdescription;
    private int goalid;

    public TargetDTO(int targetid, String targetname, String targetdescription, int goalid) {
        this.targetid = targetid;
        this.targetname = targetname;
        this.targetdescription = targetdescription;
        this.goalid = goalid;
    }

    public int getTargetid() {
        return targetid;
    }

    public void setTargetid(int targetid) {
        this.targetid = targetid;
    }

    public String getTargetname() {
        return targetname;
    }

    public void setTargetname(String targetname) {
        this.targetname = targetname;
    }

    public String getTargetdescription() {
        return targetdescription;
    }

    public void setTargetdescription(String targetdescription) {
        this.targetdescription = targetdescription;
    }

    public int getGoalid() {
        return goalid;
    }

    public void setGoalid(int goalid) {
        this.goalid = goalid;
    }
}

 

