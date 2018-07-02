package com.projectxy;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectxxApplication { 
	// Creates an instance of the logger.
	private static final Logger LOGGER = Logger.getLogger(ProjectxxApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(ProjectxxApplication.class, args);

		LOGGER.info("The name of the logger is: " + LOGGER.getName());
	}
}
