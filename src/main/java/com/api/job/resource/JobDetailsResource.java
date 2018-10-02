package com.api.job.resource;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.job.pojo.JobDetails;
import com.api.job.repository.JobDetailsRepository;
import com.api.reader.CSVReader;
import com.api.util.Publisher;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JobDetailsResource {

	@Autowired
	private JobDetailsRepository jobDetailsRepository;
	
	@Autowired
	Publisher publisher;
	
	@Autowired
	private CSVReader csvReader;
	
	
	
	public JobDetails createJob(JobDetails jobDetails) {
		return jobDetailsRepository.save(jobDetails);
	}
	
	public void bulkInsertJob(List<JobDetails> jobDetailsList) {
		
		jobDetailsList.forEach(new Consumer<JobDetails>() {
		    public void accept(JobDetails jobDetails) {
		        jobDetailsRepository.save(jobDetails);
		    }
		});
	}
	
	public void bulkLoadJob() throws IOException {
		List<JobDetails> jobDetailsList=csvReader.readJobSV();
		ObjectMapper mapper = new ObjectMapper();
		//Object to JSON in String
		String jsonInString = mapper.writeValueAsString(jobDetailsList);
		publisher.send(jsonInString);
	}
	
}
