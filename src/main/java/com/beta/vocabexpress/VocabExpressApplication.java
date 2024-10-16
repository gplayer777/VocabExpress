package com.beta.vocabexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class VocabExpressApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(VocabExpressApplication.class, args);

		VocabExpressController controller = ctx.getBean(VocabExpressController.class);
		controller.mainLoop();

	}
	@Bean
	Scanner scanner(){
		return new Scanner(System.in);
	}

}
