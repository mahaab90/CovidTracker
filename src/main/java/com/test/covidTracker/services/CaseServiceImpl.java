package com.test.covidTracker.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.covidTracker.models.CaseCovid;
import com.test.covidTracker.models.CaseResponseTemplate;
import com.test.covidTracker.models.Person;
import com.test.covidTracker.repositories.CaseRepository;
import com.test.covidTracker.repositories.PersonRepository;

@Service
public class CaseServiceImpl implements CaseService{

	CaseRepository caseRepository;
	PersonRepository personRepository;
	
	public CaseServiceImpl(CaseRepository caseRepository, PersonRepository personRepository) {
		this.caseRepository=caseRepository;
		this.personRepository=personRepository;
	}
	
	@Override
	public List<CaseCovid> getCases() {
		List<CaseCovid> cases= new ArrayList<>();
		caseRepository.findAll().forEach(cases::add);
        return cases;
	}

	@Override
	public List<CaseCovid> getCaseByDate(Date date) {
		// TODO Auto-generated method stub
		return caseRepository.findByTestDate(date);
	
	}

	@Override
	public CaseResponseTemplate getCaseByPerson(int personId) {
		// TODO Auto-generated method stub

		 Person person=personRepository.findByCin(personId).get();
		 List<CaseCovid> cases=caseRepository.findByPerson(person);
		 CaseResponseTemplate caseResponseTemplate=new CaseResponseTemplate();
		 caseResponseTemplate.setCases(cases);
		 caseResponseTemplate.setPerson(person);
		 return caseResponseTemplate;
	}

	@Override
	public Optional<CaseCovid> getCaseById(Long id) {
		// TODO Auto-generated method stub
		return caseRepository.findById(id);
	}



	@Override
	public void Delete(Long id) {
		// TODO Auto-generated method stub
		caseRepository.delete(caseRepository.findById(id).get());
	}

	@Override
	public Optional<CaseCovid> updateCase(Long id, CaseCovid _case) {
		// TODO Auto-generated method stub
		return caseRepository.findById(id).map(caseFromDB -> {
			caseFromDB.setCity(_case.getCity());
			caseFromDB.setPerson(_case.getPerson());
			caseFromDB.setTestDate(_case.getTestDate());
			caseFromDB.setTestResult(_case.getTestResult());
            return caseRepository.save(caseFromDB);
        });
	}

	@Override
	public void Insert(CaseCovid _case, int personId) {
		// TODO Auto-generated method stub
		personRepository.findByCin(personId).map(person -> {
			_case.setPerson(person);
            return caseRepository.save(_case);
        });
	}



}
