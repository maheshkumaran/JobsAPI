package com.api.job.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.api.job.pojo.JobDetails;
import com.api.job.pojo.JobLanguageMaster;
import com.api.job.pojo.JobSkillsMaster;
import com.api.job.pojo.LanguageMaster;
import com.api.job.pojo.SearchCriteria;
import com.api.job.pojo.SkillMaster;
import com.api.job.repository.JobDetailsRepository;

@Component
public class JobService {
	
	 @Autowired
	 private JobDetailsRepository jobDetailsRepository;
     
	 
	 public List<JobDetails> searchJobs(final SearchCriteria searchCriteria){
		 return jobDetailsRepository.findAll(JobSearchSpecificaton.findByCriteria(searchCriteria));
	 }
    
	 private static class JobSearchSpecificaton {
	        private static Specification<JobDetails> findByCriteria(final SearchCriteria searchCriteria) {
	            return new Specification<JobDetails>() {
	                @Override
	                public Predicate toPredicate(Root<JobDetails> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	                    List<Predicate> predicates = new ArrayList<>();
	                    if (searchCriteria.getLanguageDescription() != null) {
	                        Join<JobDetails,JobLanguageMaster> jobLanguges = root.join("joblanguageSet");
	                        Join<JobLanguageMaster,LanguageMaster> languageDescription = jobLanguges.join("languageMaster");	
	                        predicates.add(cb.equal(languageDescription.get("language_Description"),searchCriteria.getLanguageDescription()));
	                        

	                    }
	                    if (null != searchCriteria.getSkillDescription()) {
	                    	
	                        Join<JobDetails,JobSkillsMaster> jobSKills = root.join("jobsSkills");
	                        Join<JobSkillsMaster,SkillMaster> skillsDescription = jobSKills.join("skillsMaster");
	                        predicates.add(cb.equal(skillsDescription.get("skillDescription"),searchCriteria.getSkillDescription()));
	                        
	                    }
	                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	                }
	            };
	        }
	    }
	
}
