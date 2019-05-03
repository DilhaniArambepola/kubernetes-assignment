package com.ragul.assignment.dataserver;

import com.ragul.assignment.dataserver.service.DataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
//@EnableDiscoveryClient

public class DataServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataServerApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(DataService dataService) {
        return args -> dataService.getDataFromFile();
    }
}
