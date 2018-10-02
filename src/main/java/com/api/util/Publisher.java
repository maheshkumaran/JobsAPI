package com.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

	  @Value("${kafka.topic}")
	  private String topicName;

	  @Autowired
	  private KafkaTemplate<String,String> kafkaTemplate;

	  public void send(String jobDetails) {
	    LOGGER.info("sending JobDetails='{}'", jobDetails.toString());
	    System.out.println("TopicName "+topicName);
	    kafkaTemplate.send(topicName,jobDetails);
	  }

}
