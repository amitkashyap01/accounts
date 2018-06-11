package com.user.accounts.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


public class AccountController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	
	public void getItems() throws RestClientException, IOException{
				
			List<ServiceInstance> instances = discoveryClient.getInstances("inventoryapp");
		
			ServiceInstance serviceInstance = instances.get(0);
			
			String baseUrl = serviceInstance.getUri().toString();
			
			
			String urlForItem = baseUrl + "/items";
			
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<String> entity = null;
			try{
				
				
				entity = restTemplate.getForEntity(urlForItem, String.class);
			}catch(Exception ex){
				System.out.println(ex.toString());
			}
			
			System.out.println("Output: "+entity.getBody());
	}
	
	public static HttpEntity<?> getHeaders() throws Exception{
			HttpHeaders headers = new HttpHeaders();
			
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			
			return new HttpEntity<>(headers);
			
	}
}
