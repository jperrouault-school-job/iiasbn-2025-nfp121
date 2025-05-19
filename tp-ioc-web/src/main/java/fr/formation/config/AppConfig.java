package fr.formation.config;

import java.net.http.HttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.annotation.Bean;
import fr.formation.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }
}
