package com.user.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
public class AccountsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AccountsApplication.class, args);
		
		String urlForItem = "http://localhost:8090/items/5";
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> entity = restTemplate.getForEntity(urlForItem, String.class);
		
		System.out.println("Output: "+entity.getBody());
		
		
	}
}
