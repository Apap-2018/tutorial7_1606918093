package com.apap.tutorial7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.rest.PilotDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.PilotService;
import com.apap.tutorial7.service.FlightService; 

@RestController
@RequestMapping("/pilot")
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate rest(){
		return new RestTemplate();
	}
	
	@PostMapping(value = "/add")
	private PilotModel addPilotSubmit(@RequestBody PilotModel pilot){
		return pilotService.addPilot(pilot);
	}

	@GetMapping(value = "/view/{licenseNumber}")
	private PilotModel viewPilot(@PathVariable ("licenseNumber") String licenseNumber){
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		return pilot;
	}	
	
	@DeleteMapping(value = "/delete")
	private String deletePilot(@RequestParam("pilotId") long id){
		PilotModel pilot = pilotService.getPilotDetailById(id).get();
		pilotService.deletePilot(id);
		return "success";
	}
	
	@PutMapping(value = "/update/{pilotId}")
	private String updatePilotSubmit(
			@PathVariable (value = "pilotId") long pilotId,
			@RequestParam("name") String name,
			@RequestParam("flyHour") int flyHour){
		PilotModel pilot = pilotService.getPilotDetailById(pilotId).get();
		if(pilot.equals(null)){
			return "Could't find your pilot";
		}
		pilot.setName(name);
		pilot.setFlyHour(flyHour);
		pilotService.updatePilot(pilotId, pilot);
		return "update";
	}
	
	@GetMapping(value = "/status/{licenseNumber}")
	private String getStatus(@PathVariable ("licenseNumber") String licenseNumber) throws Exception {
		String path = Setting.pilotUrl + "/pilot?licenseNumber=" + licenseNumber;
		return restTemplate.getForEntity(path, String.class).getBody();
	}
	
	@GetMapping(value = "/full/{licenseNumber}")
	private PilotDetail postStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception {
		String path = Setting.pilotUrl + "/pilot";
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		PilotDetail detail = restTemplate.postForObject(path, pilot, PilotDetail.class);
		return detail;
	}
	
	//@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
		//private String add(Model model) {
		//	model.addAttribute("pilot", new PilotModel());
		//	model.addAttribute("title", "Add Pilot");
		//	return "addPilot";
		//}
		
	//@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
		//private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		//	pilotService.addPilot(pilot);
		//	return "add";
		//}
	
	//ambil pilot berdasarkan licenseNumber
	
	//@RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
		//private String deletePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		//	pilotService.deleteByLicenseNumber(licenseNumber);
		//	model.addAttribute("title", "Delete Pilot");
		//	return "delete";
		//}
		
	//@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
		//private String updatePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		//	model.addAttribute("pilot", new PilotModel());
		//	PilotModel oldPilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		//	model.addAttribute("oldPilot", oldPilot);
		//	model.addAttribute("title", "Pilot Update");
		//	return "update-pilot";
		//}
		
	//@RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
		//private String updatePilotSubmit(@ModelAttribute PilotModel pilot) {
		//	String licenseNumber = pilot.getLicenseNumber();
		//	pilotService.updatePilot(licenseNumber, pilot);
		//	return "update";
		//}
	
	//@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
		//private String viewAllPilotJourney(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
		//	PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		//	List<FlightModel> addNew = flightService.getFlightDetailByPilot(archive.getLicenseNumber());
		//	if(archive != null) {
		//		model.addAttribute("dataflight", addNew);
		//		model.addAttribute("datapilot", archive);
		//		model.addAttribute("title", "Pilot Information System");
		//		return "view-pilot";
		//	}
		//	return "no-input";
		//}
}
