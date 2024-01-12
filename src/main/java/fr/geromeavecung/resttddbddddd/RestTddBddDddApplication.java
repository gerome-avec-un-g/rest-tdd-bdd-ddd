package fr.geromeavecung.resttddbddddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestTddBddDddApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTddBddDddApplication.class, args);
		System.out.println("http://localhost:8080/todos/2");
	}

}
