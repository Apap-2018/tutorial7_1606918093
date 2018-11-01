package com.apap.tutorial7.service;
import com.apap.tutorial7.model.PilotModel;

import java.util.List;
import java.util.Optional;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	PilotModel addPilot(PilotModel pilot);
	void deleteByLicenseNumber(String licenseNumber);
	Optional<PilotModel> getPilotDetailById(long id);
	void deletePilot(long id);
	void updatePilot(long id, PilotModel pilot);

}
