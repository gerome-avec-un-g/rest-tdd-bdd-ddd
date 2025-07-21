package fr.geromeavecung.resttddbddddd.drivers.cucumber;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"fr.geromeavecung.resttddbddddd.drivers.cucumber", "fr.geromeavecung.resttddbddddd.domain"})
public class CucumberTestConfiguration {

}