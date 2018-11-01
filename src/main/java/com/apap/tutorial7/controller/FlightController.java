package com.apap.tutorial7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/flight")
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	//@Autowired
	//RestTemplate restTemplate;
	
//	@Bean
	//public RestTemplate rest(){
	//	return new RestTemplate();
	//}
	
	@PostMapping(value = "/add")
	private FlightModel addFlightSubmit(@RequestBody FlightModel flight, HttpServletRequest req) {
		return flightService.addFlight(flight);
	}
	
	@DeleteMapping(value = "{flightId}")
	private String deleteFlight(@PathVariable("flightId") long id) {
		FlightModel flight = flightService.getFlightDetailById(id);
		flightService.deleteById(id);
		return "flight has been deleted" ;
	}
	
	@PutMapping(value = "/update/{flightId}")
	private String updateFlightSubmit(
			@PathVariable (value="flightId", required = false) long flightId,
			@RequestParam("destination" ) String destination,
			@RequestParam("origin") String origin,
			//@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date time) {
			@RequestParam("time") Date time) {
		FlightModel flight = flightService.getFlightDetailById(flightId);
		if(flight.equals(null)) {
			return "Couldn't find any flight details";
		}
		flight.setDestination(destination);
		flight.setOrigin(origin);
		flight.setTime(time);
		flightService.updateFlight(flightId, flight);
		return "flight update success";
	}
	
	@GetMapping(value = "/view/{flightNumber}")
	private FlightModel viewFlight(@PathVariable ("flightNumber") String flightNumber){
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		return flight;
	}	
	
	@GetMapping(value = "/all")
	private List<FlightModel> viewAllFlight(){
		List<FlightModel> listFlight = flightService.getListOfFlight();
		for(FlightModel flight : listFlight) {
			flight.setPilot(null);
		}
		return listFlight;
	}
	
	//@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
		//private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		//	flightService.addFlight(flight);
		//	return "add";
		//}
		
		
	//@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
		//private String deleteFlight(@ModelAttribute PilotModel pilot,  Model model) {
		//	for(FlightModel dataflight : pilot.getPilotFlight()) {
		//		flightService.deleteById(dataflight.getId());
		//	}
		//	model.addAttribute("title", "Delete Flight");
		//	return "delete";
		//}

	//@RequestMapping(value = "/flight/view", method = RequestMethod.GET)
		//private String viewFlight(@RequestParam(value = "id") long id, Model model) {
		//	FlightModel flight = flightService.getFlightDetailById(id);
		//	PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(flight.getPilot().getLicenseNumber());
		//	model.addAttribute("pilot", pilot);
		//	model.addAttribute("flight", flight);
		//	model.addAttribute("title", "Flight View");
		//	return "view-flight";
		//}
	
	//@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
		//private String updateFlight(@PathVariable(value = "id") long id, Model model) {
		//	model.addAttribute("flight", new FlightModel());
		//	FlightModel oldFlight = flightService.getFlightDetailById(id);
		//	model.addAttribute("oldFlight", oldFlight);
		//	model.addAttribute("title", "Flight Updated");
		//	return "update-flight";
		//}

	//@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
		//private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		//FlightModel flight = new FlightModel();

		//PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		//flight.setPilot(pilot);
		
		//model.addAttribute("flight", flight);
		//model.addAttribute("title", "Flight Add");
		//return "addFlight";
		//}
		
}

	
	


