package com.stackroute.donationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableEurekaClient
@CrossOrigin("http://localhost:4200")
public class DonationService {

	public static void main(String[] args) {
		SpringApplication.run(DonationService.class, args);
	}

}
