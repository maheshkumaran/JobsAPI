package com.api.util;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.api.job.pojo.JobDetails;
import com.api.job.resource.JobDetailsResource;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Receiver {
     
	@Autowired
	private JobDetailsResource jobDetailsResource;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
     
	  
	  @KafkaListener(topics = "${kafka.topic}")
	  public void receive(String jobDetails) throws JsonParseException, JsonMappingException, IOException {
		  System.out.println("JibDetails "+jobDetails);
		  ObjectMapper mapper = new ObjectMapper();
		  List<JobDetails> jobDetailsList=mapper.readValue(jobDetails,new TypeReference<List<JobDetails>>(){});
		  jobDetailsResource.bulkInsertJob(jobDetailsList);
	    LOGGER.info("received jobDetails='{}'", jobDetails.toString());
	    //storage.put(jobDetails);
	  }
	
}
