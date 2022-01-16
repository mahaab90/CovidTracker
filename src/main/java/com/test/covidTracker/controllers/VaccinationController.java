package com.test.covidTracker.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.covidTracker.models.Vaccination;
import com.test.covidTracker.models.VaccinationResponseTemplate;
import com.test.covidTracker.services.VaccinationService;

@RestController
@RequestMapping("vaccins")
public class VaccinationController {
  VaccinationService vaccinationService;
  
  public VaccinationController(VaccinationService vaccinationService) {
	  this.vaccinationService=vaccinationService;
  }
  
  @GetMapping
	public ResponseEntity<List<Vaccination>> getAllVaccins() {
		List<Vaccination> vaccins = vaccinationService.getVaccinations();
		return new ResponseEntity<>(vaccins, HttpStatus.OK);
	}
  
  @GetMapping({ "/id/{vaccinId}" })
 	public ResponseEntity<Vaccination> getVaccinsById(@PathVariable Long vaccinId) {
 		Vaccination vaccin = vaccinationService.getVaccinationById(vaccinId);
 		return new ResponseEntity<>(vaccin, HttpStatus.OK);
 	}
  @GetMapping({ "/type/{type}" })
	public ResponseEntity<List<Vaccination>> getVaccinsByType(@PathVariable String type) {
	  List<Vaccination> vaccins = vaccinationService.getVaccinationByType(type);
		return new ResponseEntity<>(vaccins, HttpStatus.OK);
	}
  @GetMapping({ "/name/{name}" })
	public ResponseEntity<List<Vaccination>> getVaccinsByName(@PathVariable String name) {
	  List<Vaccination> vaccins = vaccinationService.getVaccinationByName(name);
		return new ResponseEntity<>(vaccins, HttpStatus.OK);
	}
  @GetMapping({ "/date/{date}" })
	public ResponseEntity<List<Vaccination>> getVaccinsByDate(@PathVariable Date date) {
	  List<Vaccination> vaccins = vaccinationService.getVaccinationByDate(date);
		return new ResponseEntity<>(vaccins, HttpStatus.OK);
	}
  @GetMapping({ "/person/{personId}" })
 	public ResponseEntity<VaccinationResponseTemplate> getVaccinsByDate(@PathVariable int personId) {
	  VaccinationResponseTemplate vaccins = vaccinationService.getVaccinationByPerson(personId);	
 			  return new ResponseEntity<>(vaccins, HttpStatus.OK);
 	}
  @GetMapping({ "/date" })
	public ResponseEntity<List<Vaccination>> getVaccinsByMonth(@RequestParam int year,@RequestParam int month) {
	  List<Vaccination> vaccins = vaccinationService.getVaccinationByYearMonth(year, month);
		return new ResponseEntity<>(vaccins, HttpStatus.OK);
	}
  
  @DeleteMapping({ "/{vaccinId}" })
	public ResponseEntity<Vaccination> deleteVaccin(@PathVariable Long vaccinId) {
	  vaccinationService.Delete(vaccinId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
  
	@PutMapping({ "/{vaccinId}" })
	public ResponseEntity<Vaccination> UpdateVaccin(@PathVariable Long vaccinId, @RequestBody Vaccination vaccin) {
		vaccinationService.updateVaccination(vaccinId, vaccin);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping({ "/{personId}/vaccin" })
	public ResponseEntity<Vaccination> AddVaccin(@PathVariable int personId, @RequestBody Vaccination vaccin) {
		  vaccinationService.Insert(vaccin, personId);
		  HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("vaccin", "/{personId}/vaccin" + vaccin.getId().toString());
			return new ResponseEntity<>(vaccin, httpHeaders, HttpStatus.CREATED);
		}
	
}
