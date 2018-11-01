package com.apap.tutorial7.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;


@Entity
@Table(name= "pilot")

@JsonIgnoreProperties(ignoreUnknown = true)
public class PilotModel implements Serializable {
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFlyHour() {
		return flyHour;
	}

	public void setFlyHour(int flyHour) {
		this.flyHour = flyHour;
	}

	@JsonGetter
	public List<FlightModel> getPilotFlight() {
		return pilotFlight;
	}

	@JsonSetter
	public void setPilotFlight(List<FlightModel> pilotFlight) {
		this.pilotFlight = pilotFlight;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "license_number", nullable = false, unique = true)
	private String licenseNumber;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull
	@Column(name = "fly_hour", nullable = false)
	private int flyHour;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pilot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<FlightModel> pilotFlight;
	
	
	

}
