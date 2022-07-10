package edu.poly;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import edu.poly.config.StorageProperties;
import edu.poly.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Chandqpd05208AsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chandqpd05208AsmApplication.class, args);
	}
	
	CommandLineRunner init (StorageService storageService) {
		return (args->{
			storageService.init();
		});
	}

}
