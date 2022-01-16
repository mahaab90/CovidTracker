package com.test.covidTracker.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.test.covidTracker.models.Vaccination;
import com.test.covidTracker.models.VaccinationResponseTemplate;

public interface VaccinationService {

	List<Vaccination> getVaccinations();
	
	List<Vaccination> getVaccinationByDate(Date date);
	List<Vaccination> getVaccinationByType(String type);
	List<Vaccination> getVaccinationByName(String name);
	List<Vaccination> getVaccinationByYearMonth(int year, int month);
	Vaccination getVaccinationById(Long id);
	VaccinationResponseTemplate getVaccinationByPerson(int personId);
	void Insert(Vaccination vaccin, int personId);
	void Delete(Long vaccin);
	Optional<Vaccination> updateVaccination(Long id, Vaccination vaccin);
}
