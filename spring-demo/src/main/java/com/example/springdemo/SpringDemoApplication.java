package com.example.springdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.example.springdemo.property.HelloBeerProperty;
import com.example.springdemo.service.HelloBeerService;

@SpringBootApplication
@EnableConfigurationProperties(HelloBeerProperty.class)
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}
	@Bean
	CommandLineRunner init(HelloBeerService helloBeerService) {
		return (args) -> {
			helloBeerService.init();
		};
	}
	@Bean
	CommandLineRunner init1(HelloBeerService helloBeerService) {
		return (args) -> {
			helloBeerService.init1();
		};
	}
}
