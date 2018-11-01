package com.apap.tutorial7.repository;
import com.apap.tutorial7.model.PilotModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PilotDb extends JpaRepository<PilotModel, Long> {
	PilotModel findByLicenseNumber(String licenseNumber);
	void deleteByLicenseNumber(String licenseNumber);
	void deleteById(PilotModel pilot);
}
