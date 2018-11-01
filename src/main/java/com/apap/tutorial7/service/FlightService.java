package com.apap.tutorial7.service;
import com.apap.tutorial7.model.FlightModel;
import java.util.List;

public interface FlightService {
	List<FlightModel> getFlightDetailByPilot(String licenseNumber);
	FlightModel getFlightDetailById(long id);
	FlightModel addFlight(FlightModel flight);
	void updateFlight(long id, FlightModel newFlight);
	void deleteById(long id);
	FlightModel getFlightDetailByFlightNumber(String flightNumber);
	List<FlightModel> getListOfFlight();
	
	
}
