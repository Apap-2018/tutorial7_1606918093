package com.apap.tutorial7.service;
import com.apap.tutorial7.repository.FlightDb;
import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;


@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public FlightModel addFlight(FlightModel flight) {
		return flightDb.save(flight);
	}
	
	@Override
	public List<FlightModel> getFlightDetailByPilot(String licenseNumber) {
		return flightDb.findByPilotLicenseNumber(licenseNumber);
	}

	@Override
	public void deleteById(long id) {
		flightDb.deleteById(id);
	}
	
	@Override
	public void updateFlight(long id, FlightModel newFlight) {
		FlightModel updateFlight = flightDb.findById(id);
		updateFlight.setTime(newFlight.getTime());	
		updateFlight.setFlightNumber(newFlight.getFlightNumber());
		updateFlight.setDestination(newFlight.getDestination());
		updateFlight.setOrigin(newFlight.getOrigin());
		flightDb.save(updateFlight);
	}

	@Override
	public FlightModel getFlightDetailById(long id) {
		return flightDb.findById(id);
	}

	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		// TODO Auto-generated method stub
		return flightDb.findByFlightNumber(flightNumber);
	}

	@Override
	public List<FlightModel> getListOfFlight() {
		// TODO Auto-generated method stub
		return flightDb.findAll();
	}

}
