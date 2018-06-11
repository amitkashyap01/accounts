package com.user.accounts;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.user.accounts.controller.AccountController;

@SpringBootApplication
public class AccountsApplication{

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountsApplication.class);
	
	public static void main(String[] args) throws RestClientException, IOException {

		ApplicationContext ctx = SpringApplication.run(AccountsApplication.class, args);
	
		AccountController accountController = ctx.getBean(AccountController.class);
		
		System.out.println(accountController);
		
		accountController.getItems();
		
	}
	
	
	@Bean 
	public AccountController accountController(){
		return new AccountController();
	}
	
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder){
//		return builder.build();
//	}
//	
//	@Bean
//	public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
//		String urlForItem = "http://localhost:8090/items/5";
//		return args -> {
//				ResponseEntity<String> entity = restTemplate.getForEntity(urlForItem, String.class);
//				LOGGER.info(entity.getBody());
//		};
//	}
}
