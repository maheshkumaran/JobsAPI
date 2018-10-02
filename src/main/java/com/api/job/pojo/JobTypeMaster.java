package com.api.job.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Job_Type_Master")
public class JobTypeMaster {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long job_Type_id;
	@Column(name="job_Type_Description")
    private String jobTypeDescription;
    
	public Long getJob_Type_id() {
		return job_Type_id;
	}
	public void setJob_Type_id(Long job_Type_id) {
		this.job_Type_id = job_Type_id;
	}
	public String getJobTypeDescription() {
		return jobTypeDescription;
	}
	public void setJobTypeDescription(String jobTypeDescription) {
		this.jobTypeDescription = jobTypeDescription;
	}

}
