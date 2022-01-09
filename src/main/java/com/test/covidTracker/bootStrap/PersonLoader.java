/**
 * Initial Data loading
 */


package com.test.covidTracker.bootStrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.test.covidTracker.models.Person;
import com.test.covidTracker.repositories.PersonRepository;

@Component
public class PersonLoader implements CommandLineRunner {

	PersonRepository personRepository;

	public PersonLoader(PersonRepository personRepository) {
		this.personRepository = personRepository;

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		loadPersons();
	}

	void loadPersons() {
		if (personRepository.count() == 0) {
			personRepository.save(Person.builder().cin(100).age(50).name("Saleh ben ali").adress("Ariana").build());
			personRepository.save(Person.builder().cin(101).age(65).name("Fatma el Fehri").adress("Ariana").build());
		}
	}

}
