package com.sdg.goals.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="subgoals")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubGoal {

	@Id
	@Column(name="SubgoalId")
	private int subgoalid;
	
	
	@Column(name="SubgoalName")
	private String subgoalname;
	
	@Column(name ="SubGoalDescription")
	private String subgoaldescription;
	

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "target_id")
    @JsonIgnore
    private Target target;

	
	
	public Target getTarget() {
		return target;
	}



	public void setTarget(Target target) {
		this.target = target;
	}


	
	public SubGoal() {
		
	}

	

	public SubGoal( String subgoalname, String subgoaldescription) {
		super();
		
		this.subgoalname = subgoalname;
		this.subgoaldescription = subgoaldescription;
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

	public void setTargetid(int targetId) {
	    this.target.setTargetid(targetId);
	}

	@Override
	public String toString() {
		return "SubGoal [subgoalid=" + subgoalid + ", subgoalname=" + subgoalname
				+ ", subgoaldescription=" + subgoaldescription + "]";
	}

	
	
	
}
