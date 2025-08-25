package fr.geromeavecung.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
		LOGGER.info("http://localhost:8080/");
		LOGGER.info("http://localhost:8080/api/authors");
	}

}
