package com.api.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.api.job.pojo.JobDetails;

@Repository
public interface JobDetailsRepository extends JpaRepository<JobDetails, Long>
,JpaSpecificationExecutor<JobDetails>{
	
	

}
