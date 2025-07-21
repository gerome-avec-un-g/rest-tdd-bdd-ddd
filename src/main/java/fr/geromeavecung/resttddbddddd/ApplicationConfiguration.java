package fr.geromeavecung.resttddbddddd;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("fr.geromeavecung.resttddbddddd.*")
@EnableJpaRepositories(considerNestedRepositories = true)
public class ApplicationConfiguration {
}
