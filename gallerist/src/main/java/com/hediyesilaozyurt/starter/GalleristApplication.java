package com.hediyesilaozyurt.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.hediyesilaozyurt")
@EntityScan(basePackages = {"com.hediyesilaozyurt"})
@ComponentScan(basePackages = {"com.hediyesilaozyurt"})
@EnableJpaRepositories("com.hediyesilaozyurt.repository")
@EnableJpaAuditing
public class GalleristApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalleristApplication.class, args);
	}

}
