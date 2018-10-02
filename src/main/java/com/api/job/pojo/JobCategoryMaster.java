package com.api.job.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Job_Category_Master")
public class JobCategoryMaster {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long job_category_id;
	 @Column(name="job_Category_Description")
	private String job_Category_Description;
	
	
	public String getJob_Category_Description() {
		return job_Category_Description;
	}
	public void setJob_Category_Description(String job_Category_Description) {
		this.job_Category_Description = job_Category_Description;
	}
	public Long getJob_category_id() {
		return job_category_id;
	}
	public void setJob_category_id(Long job_category_id) {
		this.job_category_id = job_category_id;
	}
	
	

}
