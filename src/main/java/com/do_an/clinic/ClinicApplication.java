package com.do_an.clinic;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);
	}
}
