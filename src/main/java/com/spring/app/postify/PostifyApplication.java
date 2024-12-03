package com.spring.app.postify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.spring.app.postify.config")
public class PostifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostifyApplication.class, args);
	}

}
