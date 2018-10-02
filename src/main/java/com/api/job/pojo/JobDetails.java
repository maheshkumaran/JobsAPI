package com.api.job.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Job_Details")
public class JobDetails {
	
	
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long job_id;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "jobDetails", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<JobSkillsMaster> jobsSkills;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "jobDetails",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<JobLanguageMaster> joblanguageSet;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryMaster countryMaster;
    

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_type_id", nullable = false)
    private JobTypeMaster jobTypeMaster;
    
	
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_category_id", nullable = false)
    private JobCategoryMaster jobCategoryMaster;
    
    @Column(name="min_Pay_Scale")
    private Integer minPayScale;
    
    @Column(name="max_Pay_Scale")
    private Integer maxPayScale;
    
    @Column(name="min_Experience") 
    private Integer minExperience;
    
    @Column(name="max_Experience")
    private Integer maxExperience;
    
    
	public Long getJob_id() {
		return job_id;
	}
	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}
	public Set<JobSkillsMaster> getJobsSkills() {
		return jobsSkills;
	}
	public void setJobsSkills(Set<JobSkillsMaster> jobsSkills) {
		this.jobsSkills = jobsSkills;
	}
	public Set<JobLanguageMaster> getJoblanguageSet() {
		return joblanguageSet;
	}
	public void setJoblanguageSet(Set<JobLanguageMaster> joblanguageSet) {
		this.joblanguageSet = joblanguageSet;
	}
	public CountryMaster getCountryMaster() {
		return countryMaster;
	}
	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}
	public JobTypeMaster getJobTypeMaster() {
		return jobTypeMaster;
	}
	public void setJobTypeMaster(JobTypeMaster jobTypeMaster) {
		this.jobTypeMaster = jobTypeMaster;
	}
	public JobCategoryMaster getJobCategoryMaster() {
		return jobCategoryMaster;
	}
	public void setJobCategoryMaster(JobCategoryMaster jobCategoryMaster) {
		this.jobCategoryMaster = jobCategoryMaster;
	}
	public Integer getMinPayScale() {
		return minPayScale;
	}
	public void setMinPayScale(Integer minPayScale) {
		this.minPayScale = minPayScale;
	}
	public Integer getMaxPayScale() {
		return maxPayScale;
	}
	public void setMaxPayScale(Integer maxPayScale) {
		this.maxPayScale = maxPayScale;
	}
	public Integer getMinExperience() {
		return minExperience;
	}
	public void setMinExperience(Integer minExperience) {
		this.minExperience = minExperience;
	}
	public Integer getMaxExperience() {
		return maxExperience;
	}
	public void setMaxExperience(Integer maxExperience) {
		this.maxExperience = maxExperience;
	}
	
	

}
