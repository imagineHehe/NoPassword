package ru.mironov.projects.noPassword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NoPasswordApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoPasswordApplication.class, args);
	}

}
