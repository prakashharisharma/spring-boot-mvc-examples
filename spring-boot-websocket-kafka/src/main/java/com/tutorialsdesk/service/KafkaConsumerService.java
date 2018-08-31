package com.tutorialsdesk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
@Service
public class KafkaConsumerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);
	
	@Autowired
	SimpMessagingTemplate template;

	@KafkaListener(topics = "${kafka.topic.dashboard}")
	public void consume(@Payload String message) {
		if (isNumeric(message)) {
			
			LOGGER.info("Received message='{}'", message);
			
			template.convertAndSend("/topic/requests", message);
		}

	}

	public boolean isNumeric(String str) {
		try {
			@SuppressWarnings("unused")
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
