package fr.geromeavecung.resttddbddddd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestTddBddDddApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestTddBddDddApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestTddBddDddApplication.class, args);
		LOGGER.info("http://localhost:8080/authors");
	}

}
