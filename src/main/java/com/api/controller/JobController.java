package com.api.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.job.pojo.CountryMaster;
import com.api.job.pojo.JobCategoryMaster;
import com.api.job.pojo.JobDetails;
import com.api.job.pojo.JobLanguageMaster;
import com.api.job.pojo.JobSkillsMaster;
import com.api.job.pojo.JobTypeMaster;
import com.api.job.pojo.LanguageMaster;
import com.api.job.pojo.SearchCriteria;
import com.api.job.pojo.SkillMaster;
import com.api.job.resource.JobDetailsResource;
import com.api.job.service.JobService;
import com.api.reader.CSVReader;
import com.api.util.Publisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class JobController {
	
	
	
	
	
	@Autowired
	private JobDetailsResource jobDetailsResource;
     
	@Autowired
	private JobService jobService;
	
	@Autowired
	Publisher publisher;
	
	
	@Autowired
	private CSVReader csvReader;
	
	@RequestMapping("/")
    public String index() {
        return "Welcome to Jobs API World!";
    }
	
	
	@PostMapping("/jobs")
	public ResponseEntity<Object> createJob(@RequestBody JobDetails inputJobDetails){
		jobDetailsResource.createJob(inputJobDetails);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
	
	
	@PostMapping("/mockjobs")
	public ResponseEntity<Object> createMockJob() throws JsonProcessingException{
		JobDetails jobDetails=new JobDetails();
		CountryMaster countryMaster=new CountryMaster();
		countryMaster.setCountry_id(new Long(1));
		jobDetails.setCountryMaster(countryMaster);
		JobCategoryMaster jobCategoryMaster=new JobCategoryMaster();
		jobCategoryMaster.setJob_category_id(new Long(1));
		jobDetails.setJobCategoryMaster(jobCategoryMaster);
		JobLanguageMaster jobLanguageMaster=new JobLanguageMaster();
		LanguageMaster languageMaster=new LanguageMaster();
		languageMaster.setLanguage_id(new Long(1));
		jobLanguageMaster.setLanguageMaster(languageMaster);
		jobLanguageMaster.setJobDetails(jobDetails);
		Set<JobLanguageMaster> jobLanguageSet=new HashSet<JobLanguageMaster>();
		jobLanguageSet.add(jobLanguageMaster);
		jobDetails.setJoblanguageSet(jobLanguageSet);
		JobSkillsMaster jobSkillsMaster=new JobSkillsMaster();
	    SkillMaster skillMaster=new SkillMaster();
	    skillMaster.setSkill_id(new Long(1));
		jobSkillsMaster.setSkillsMaster(skillMaster);
		jobSkillsMaster.setJobDetails(jobDetails);
		Set<JobSkillsMaster> jobSkillsMasterSet=new HashSet<JobSkillsMaster>();
		jobSkillsMasterSet.add(jobSkillsMaster);
		jobDetails.setJobsSkills(jobSkillsMasterSet);
		JobTypeMaster jobTypeMaster=new JobTypeMaster();
		jobTypeMaster.setJob_Type_id(new Long(1));
		jobDetails.setJobTypeMaster(jobTypeMaster);
		jobDetails.setMinExperience(5);
		jobDetails.setMaxExperience(15);
		jobDetails.setMinPayScale(500000);
		jobDetails.setMaxPayScale(2000000);
		
		ObjectMapper mapper = new ObjectMapper();
		//Object to JSON in String
		String jsonInString = mapper.writeValueAsString(jobDetails);
		System.out.println("JsonString "+jsonInString);
		//publisher.send(jsonInString);
		jobDetailsResource.createJob(jobDetails);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	
	@GetMapping(value="/readJobCSV")
	public String readJobCSV() {
		String jobsResp=null;
		try {
			List<JobDetails> jobDetailsList=csvReader.readJobSV();
			ObjectMapper mapper = new ObjectMapper();
			//Object to JSON in String
			String jsonInString = mapper.writeValueAsString(jobDetailsList);
			publisher.send(jsonInString);
			System.out.println("JsonString "+jsonInString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobsResp;
	}
	
	@GetMapping(value="/bulkLoadJob")
	public ResponseEntity<Object> bulkLoadJob() {
		try {
			jobDetailsResource.bulkLoadJob();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping(value="/searchJobs")
	public ResponseEntity<List<JobDetails>> searchJobs(@RequestBody SearchCriteria searchCriteria) {
		return new ResponseEntity<List<JobDetails>>(jobService.searchJobs(searchCriteria),HttpStatus.OK);
	}

	
}
