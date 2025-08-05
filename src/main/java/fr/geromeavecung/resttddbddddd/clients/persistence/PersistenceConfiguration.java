package fr.geromeavecung.resttddbddddd.clients.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("fr.geromeavecung.resttddbddddd.*")
@EnableJpaRepositories(considerNestedRepositories = true)
public class PersistenceConfiguration {

    // need to be in a separate file from spring-boot application, otherwise seems to clash with @RestClientTest

}
