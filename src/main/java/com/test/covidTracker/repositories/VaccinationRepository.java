package com.test.covidTracker.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.covidTracker.models.Person;
import com.test.covidTracker.models.Vaccination;

@Repository
public interface VaccinationRepository  extends  JpaRepository<Vaccination, Long>{
    
	List<Vaccination> findByVaccDate(Date date);
	List<Vaccination> findByName(String name);
	List<Vaccination> findByType(String type);
	List<Vaccination> findByPerson(Person person);
	
	@Query("select v from Vaccination v where year(v.vaccDate) = ?1 and month(v.vaccDate) = ?2")
	List<Vaccination> getByYearAndMonth(int year, int month);
}
