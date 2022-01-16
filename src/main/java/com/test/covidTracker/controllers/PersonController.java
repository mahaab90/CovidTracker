
/**
 * Controller to handel Rest request :https://localhost:8080/persons/*
 *
 */
package com.test.covidTracker.controllers;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.covidTracker.models.Person;
import com.test.covidTracker.services.CaseService;
import com.test.covidTracker.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	PersonService personService;
	CaseService caseService;

	public PersonController(PersonService personService, CaseService caseService) {
		this.personService = personService;
		this.caseService = caseService;
	}

	@GetMapping
	public ResponseEntity<List<Person>> getPersons() {
		return new ResponseEntity<>(personService.getPersons(), HttpStatus.OK);
	}

	@GetMapping({ "/{personId}" })
	public ResponseEntity<Person> getPerson(@PathVariable int personId) {
		return new ResponseEntity<>(personService.getPerson(personId).get(), HttpStatus.OK);
	}


	@PostMapping({ "/{personId}" })
	public ResponseEntity<Person> savePerson(@PathVariable int personId, @RequestBody Person person) {
		personService.insertPerson(personId, person);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("person", "/persons" + String.valueOf(personId));
		return new ResponseEntity<>(person, httpHeaders, HttpStatus.CREATED);
	}
}
