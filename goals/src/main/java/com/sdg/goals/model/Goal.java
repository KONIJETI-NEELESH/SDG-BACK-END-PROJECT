package com.sdg.goals.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="goals")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Goal {

	@Id
	@Column(name="GoalId")
	private int goalid;
	
	@Column(name="GoalName")
	private String goalname;
	
	@Column(name="GoalDescription")
	private String goaldescription;
	
	@OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
	
    private List<Target> targets = new ArrayList<>();

	public Goal() {
	
	}
	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}
	
	public int getGoalid() {
		return goalid;
	}

	public void setGoalid(int goalid) {
		this.goalid = goalid;
	}

	public String getGoalname() {
		return goalname;
	}

	public void setGoalname(String goalname) {
		this.goalname = goalname;
	}

	public String getGoaldescription() {
		return goaldescription;
	}

	public void setGoaldescription(String goaldescription) {
		this.goaldescription = goaldescription;
	}

	public Goal(String goalname, String goaldescription) {
		super();
		this.goalname = goalname;
		this.goaldescription = goaldescription;
	}

	@Override
	public String toString() {
		return "Goal [goalid=" + goalid + ", goalname=" + goalname + ", goaldescription=" + goaldescription + "]";
	}
	public Goal orElse(Goal goal) {
	
		return null;
	}

	
	
}
