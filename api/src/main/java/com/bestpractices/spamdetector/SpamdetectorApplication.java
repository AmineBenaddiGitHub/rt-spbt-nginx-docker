package com.bestpractices.spamdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpamdetectorApplication {

	public static void main(String[] args) {
		// faille de sécu : non-contrôle des args ...
		// dependency check => check des failles de sécu (audit)
		SpringApplication.run(SpamdetectorApplication.class, args);
	}

}
