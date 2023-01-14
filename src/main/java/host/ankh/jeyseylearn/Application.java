package host.ankh.jeyseylearn;

import host.ankh.jeyseylearn.domain.account.Account;
import host.ankh.jeyseylearn.domain.account.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


//	@Bean
//	public CommandLineRunner demo(AccountRepository repository) {
//		return (args) -> {
//			// save a few customers
//			repository.save(new Account("user", "$2a$10$zVTkhDKZNuEvO/Gtgp7QhOHGos5AFTSEeA308jOj/HUxzH4k0VOc2", "ankh", "17199999999", "ankh04@icloud.com"));
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Account account : repository.findAll()) {
//				log.info(account.toString());
//			}
//			log.info("");
//		};
//	}
}
