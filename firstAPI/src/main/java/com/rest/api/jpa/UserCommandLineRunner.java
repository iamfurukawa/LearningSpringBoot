package com.rest.api.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

	@Override
	public void run(String... args) throws Exception {
		// save a couple of customers
		userRepository.save(new User("Ranga", "Admin"));
		userRepository.save(new User("Ravi", "User"));
		userRepository.save(new User("Satish", "Admin"));
		userRepository.save(new User("Raghu", "User"));
		userRepository.save(new User("Vinicius", "Admin"));

		log.info("-------------------------------");
		log.info("Finding all users");
		log.info("-------------------------------");
		for (User user : userRepository.findAll()) {
			log.info(user.toString());
		}
		
		log.info("-------------------------------");
		log.info("Finding all Admin users");
		log.info("-------------------------------");
		for (User user : userRepository.findByRole("Admin")) {
			log.info(user.toString());
		}
	}

}
