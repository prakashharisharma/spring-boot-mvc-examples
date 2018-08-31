package com.tutorialsdesk;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.tutorialsdesk.service.KafkaProducerService;


@Component
@Profile("!test")
public class AppRunner implements CommandLineRunner {

	@Autowired
	KafkaProducerService kafkaProducerService;
	
	private static final String DASHBOARD_TOPIC = "dashboard.t";
	
	@Override
	public void run(String... arg0) throws Exception {
		
		int i=0;
		
		while(i < 50) {
			
			kafkaProducerService.send(DASHBOARD_TOPIC,getRandomNumberInRange(30,46)+"");
			
			++i;
			
			Thread.sleep(3000);
			
		}
		
	}

	private static int getRandomNumberInRange(int min, int max) {
		
		Random r = new Random();
		
		return r.ints(min, (max + 1)).findFirst().getAsInt();

	}
	

}
