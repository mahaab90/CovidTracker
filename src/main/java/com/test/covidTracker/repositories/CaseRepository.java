package com.test.covidTracker.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.test.covidTracker.models.CaseCovid;
import com.test.covidTracker.models.Person;

public interface CaseRepository  extends  CrudRepository<CaseCovid, Long>{
	List<CaseCovid> findByTestDate(Date date);
	
	List<CaseCovid> findByPerson(Person person);
}
