package com.test.covidTracker.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.covidTracker.models.CaseCovid;
import com.test.covidTracker.models.Person;

@Repository
public interface CaseRepository  extends  JpaRepository<CaseCovid, Long>{
	List<CaseCovid> findByTestDate(Date date);
	List<CaseCovid> findByPerson(Person person);
	
	@Query("select c from CaseCovid c where year(c.testDate) = ?1 and month(c.testDate) = ?2")
	List<CaseCovid> getByYearAndMonth(int year, int month);
}
