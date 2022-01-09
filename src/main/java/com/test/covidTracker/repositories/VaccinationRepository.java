package com.test.covidTracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.covidTracker.models.Vaccination;

public interface VaccinationRepository  extends  JpaRepository<Vaccination, Long>{

}
