package com.test.covidTracker.services;

import java.util.List;
import java.util.Optional;

import com.test.covidTracker.models.Person;

public interface PersonService {
	
	Optional<Person> getPerson(int cin);
	List<Person> getPersons();
	void insertPerson(int cin,Person person);
	void deletePerson(Person person);
	
	void updatePerson(int cin, Person person);
	
	

}
