package fr.geromeavecung.resttddbddddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(considerNestedRepositories = true)
public class RestTddBddDddApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTddBddDddApplication.class, args);
		System.out.println("http://localhost:8080/authors");
	}

}
