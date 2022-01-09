package com.test.covidTracker.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.covidTracker.models.Person;

public interface PersonRepository extends  JpaRepository<Person, Integer> {

	Optional<Person> findByCin(int cin);
}
