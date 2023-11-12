package com.lyk;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootEventDemoApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringbootEventDemoApplication.class);

//		springApplication.addInitializers();

		springApplication.run(args);
	}


	@Bean
	public ApplicationRunner applicationRunner(){

		return  args -> {
			System.out.println("===ApplicationRunner 运行了.....");
		};
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return  args -> {
			System.out.println("===CommandLineRunner 运行了.....");
		};
	}

}
