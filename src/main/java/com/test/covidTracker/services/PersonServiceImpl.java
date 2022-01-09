package com.test.covidTracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.test.covidTracker.models.Person;
import com.test.covidTracker.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{
	PersonRepository personRepository;
	
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository=personRepository;
	}

	@Override
	public Optional<Person> getPerson(int cin) {
		// TODO Auto-generated method stub
		return personRepository.findByCin(cin);
	}

	@Override
	public void insertPerson(int cin,Person person) {
		// TODO Auto-generated method stub
		person.setCin(cin);
		personRepository.save(person);
	}

	@Override
	public void deletePerson(Person person) {
		// TODO Auto-generated method stub
		personRepository.delete(person);
	}

	@Override
	public void updatePerson(int cin, Person person) {
		// TODO Auto-generated method stub
		personRepository.findByCin(cin).map(personFromDB -> {
			personFromDB.setAdress(person.getAdress());
			personFromDB.setAge(person.getAge());
			personFromDB.setCases(person.getCases());
			personFromDB.setVacinations(person.getVacinations());
			return personRepository.save(personFromDB);
		});
	}

	@Override
	public List<Person> getPersons() {
		// TODO Auto-generated method stub
		List<Person> persons = new ArrayList<>();
		personRepository.findAll().forEach(persons::add);
        return persons;
	}

}
