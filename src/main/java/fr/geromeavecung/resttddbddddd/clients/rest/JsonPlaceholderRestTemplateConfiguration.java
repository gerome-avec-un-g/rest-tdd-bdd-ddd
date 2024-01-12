package fr.geromeavecung.resttddbddddd.clients.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class JsonPlaceholderRestTemplateConfiguration {

    @Bean
    public RestTemplate jsonPlaceholderRestTemplate(RestTemplateBuilder restTemplateBuilder,
                                                    @Value("${jsonPlaceholder.rootUri}") String rootUri) {
        return restTemplateBuilder.rootUri(rootUri).build();
    }

    @Bean
    public WebClient jsonPlaceholderWebClient(@Value("${jsonPlaceholder.rootUri}") String rootUri) {
        return WebClient.builder().baseUrl(rootUri).build();
    }
}
