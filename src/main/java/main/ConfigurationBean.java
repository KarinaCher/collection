package main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import resources.PostcardOtherResource;
import resources.PostcardResource;

@Configuration
public class ConfigurationBean {
    @Bean
    public PostcardResource postcardResource() {
        return new PostcardResource();
    }

    @Bean
    public PostcardOtherResource postcardOtherResource() {
        return new PostcardOtherResource();
    }
}
