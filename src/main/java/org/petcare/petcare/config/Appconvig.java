package org.petcare.petcare.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconvig {
    @Bean
    public ModelMapper ModelMapper() {
     return new ModelMapper();
    }
}
