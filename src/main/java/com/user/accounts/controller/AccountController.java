package com.user.accounts.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


public class AccountController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
	// To discover the producer application from Eureka Server
//	@Autowired
//	private DiscoveryClient discoveryClient;
	
	
	//To load balacer discovery
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	public void getItems() throws RestClientException, IOException{
				
			// For Normal Discovery
			//List<ServiceInstance> instances = discoveryClient.getInstances("inventoryapp");
			//ServiceInstance serviceInstance = instances.get(0);
		
			// For LoadBalacer Discovery
			ServiceInstance serviceInstance = loadBalancerClient.choose("inventoryapp");
		
			String baseUrl = serviceInstance.getUri().toString();
			
			LOGGER.info("Discovered producer's URL: "+baseUrl);
			
			String urlForItem = baseUrl + "/items";
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<String> entity = null;
			try{
				LOGGER.info("Calling GET on URL: "+urlForItem);
				entity = restTemplate.getForEntity(urlForItem, String.class);
			}catch(Exception ex){
				System.out.println(ex.toString());
			}
			
			LOGGER.info("Received Value from GET "+urlForItem+": "+entity.getBody());
	}
	
	public static HttpEntity<?> getHeaders() throws Exception{
			HttpHeaders headers = new HttpHeaders();
			
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			
			return new HttpEntity<>(headers);
			
	}
}
