package com.apap.tutorial7.service;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.repository.PilotDb;
import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDb;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDb pilotDb;
	
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

	
	@Override
	public PilotModel addPilot(PilotModel pilot) {
		return pilotDb.save(pilot);
	}
	
	@Override
	public void deleteByLicenseNumber(String licenseNumber) {
		pilotDb.deleteByLicenseNumber(licenseNumber);
	}

	@Override
	public void updatePilot(long id, PilotModel newPilot) {
		PilotModel updatePilot = pilotDb.findById(id).get();
		updatePilot.setFlyHour(newPilot.getFlyHour());
		updatePilot.setLicenseNumber(newPilot.getLicenseNumber());
		updatePilot.setName(newPilot.getName());
		pilotDb.save(updatePilot);
	}


	@Override
	public Optional<PilotModel> getPilotDetailById(long id) {
		// TODO Auto-generated method stub
		return pilotDb.findById(id);
	}


	@Override
	public void deletePilot(long id) {
		// TODO Auto-generated method stub
		pilotDb.deleteById(id);
	}
}
