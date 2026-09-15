package com.clara.taskly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TasklyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasklyApplication.class, args);
    }
}
