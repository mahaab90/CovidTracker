/**
 * Controller to handel Rest request :https://localhost:8080/cases/*
 *
 */

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
import org.springframework.web.bind.annotation.RestController;

import com.test.covidTracker.models.CaseCovid;
import com.test.covidTracker.models.CaseResponseTemplate;
import com.test.covidTracker.services.CaseService;

@RestController
@RequestMapping("cases")
public class CaseController {

	CaseService caseService;

	public CaseController(CaseService caseService) {
		this.caseService = caseService;
	}

	@GetMapping
	public ResponseEntity<List<CaseCovid>> getAllCases() {
		List<CaseCovid> cases = caseService.getCases();
		return new ResponseEntity<>(cases, HttpStatus.OK);
	}

	@GetMapping({ "/id/{caseId}" })
	public ResponseEntity<CaseCovid> getCase(@PathVariable Long caseId) {
		return new ResponseEntity<>(caseService.getCaseById(caseId).get(), HttpStatus.OK);
	}

	@GetMapping({ "/date/{date}" })
	public ResponseEntity<List<CaseCovid>> getCaseByDate(@PathVariable Date date) {
		return new ResponseEntity<>(caseService.getCaseByDate(date), HttpStatus.OK);
	}
	

	@GetMapping({ "/person/{personId}" })
	public ResponseEntity<CaseResponseTemplate> getCasesByPerson(@PathVariable int personId) {
		return new ResponseEntity<>(caseService.getCaseByPerson(personId), HttpStatus.OK);
	}

	@PutMapping({ "/{caseId}" })
	public ResponseEntity<CaseCovid> updateCase(@PathVariable("caseId") Long caseId, @RequestBody CaseCovid _case) {
		caseService.updateCase(caseId, _case);
		return new ResponseEntity<>(caseService.getCaseById(caseId).get(), HttpStatus.OK);
	}

	
	@DeleteMapping({ "/{caseId}" })
	public ResponseEntity<CaseCovid> deleteCase(@PathVariable("caseId") Long caseId) {
		caseService.Delete(caseId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping({ "/{personId}/case" })
	public ResponseEntity<CaseCovid> saveCase(@PathVariable int personId, @RequestBody CaseCovid _case) {
		caseService.Insert(_case, personId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("case", "/{personId}/case" + _case.getId().toString());
		return new ResponseEntity<>(_case, httpHeaders, HttpStatus.CREATED);
	}

}
