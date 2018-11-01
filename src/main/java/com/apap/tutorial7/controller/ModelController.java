package com.apap.tutorial7.controller;

import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.PilotService;
import com.apap.tutorial7.service.FlightService;

@RestController
@RequestMapping("/model")
public class ModelController {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private PilotService pilotService;
	
	@Autowired
	private FlightService flightService;
	
	
	@Bean
	public RestTemplate res() {
    	return new RestTemplate();
    }
	    
    @GetMapping(value ="/airports/{city}")
    public String getAirportByCity(@PathVariable("city") String city) throws Exception{
    	String path = Setting.flightUrl + "&term=" + city;
    	return restTemplate.getForEntity(path, String.class).getBody();
	}

	
}