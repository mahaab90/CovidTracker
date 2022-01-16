package com.test.covidTracker.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.test.covidTracker.models.Person;
import com.test.covidTracker.models.Vaccination;
import com.test.covidTracker.models.VaccinationResponseTemplate;
import com.test.covidTracker.repositories.PersonRepository;
import com.test.covidTracker.repositories.VaccinationRepository;
@Service
public class VaccinationServiceImpl implements  VaccinationService{
   
	VaccinationRepository vaccinationRepository;
	PersonRepository personRepository;
	
	public VaccinationServiceImpl(VaccinationRepository vaccinationRepository, PersonRepository personRepository) {
		this.vaccinationRepository=vaccinationRepository;
		this.personRepository=personRepository;
	}
	
	
	
	
	@Override
	public List<Vaccination> getVaccinations() {
		// TODO Auto-generated method stub
		List<Vaccination> vaccins= new ArrayList<>();
		vaccinationRepository.findAll(Sort.by(Sort.Direction.ASC, "vaccDate")).forEach(vaccins::add);
        return vaccins;
	}

	@Override
	public List<Vaccination> getVaccinationByDate(Date date) {
		// TODO Auto-generated method stub
		return vaccinationRepository.findByVaccDate(date);
	}

	@Override
	public List<Vaccination> getVaccinationByType(String type) {
		// TODO Auto-generated method stub
		return vaccinationRepository.findByType(type);
	}

	@Override
	public List<Vaccination> getVaccinationByName(String name) {
		// TODO Auto-generated method stub
		return vaccinationRepository.findByName(name);
	}

	@Override
	public Vaccination getVaccinationById(Long id) {
		// TODO Auto-generated method stub
		return vaccinationRepository.findById(id).get();
	}

	@Override
	public VaccinationResponseTemplate getVaccinationByPerson(int personId) {
		// TODO Auto-generated method stub
		Person person=personRepository.findByCin(personId).get();
		 List<Vaccination> vaccs=vaccinationRepository.findByPerson(person);
		 VaccinationResponseTemplate vaccResponseTemplate=new VaccinationResponseTemplate();
		 vaccResponseTemplate.setVaccs(vaccs);

		 vaccResponseTemplate.setAdress(person.getAdress());
		 vaccResponseTemplate.setAge(person.getAge());
		 vaccResponseTemplate.setCin(person.getCin());
		 vaccResponseTemplate.setName(person.getName());
		 return vaccResponseTemplate;
	}

	@Override
	public void Insert(Vaccination vacc, int personId) {
		// TODO Auto-generated method stub
		personRepository.findByCin(personId).map(person -> {
			vacc.setPerson(person);
            return vaccinationRepository.save(vacc);
        });
	}

	@Override
	public void Delete(Long vaccin) {
		// TODO Auto-generated method stub
		vaccinationRepository.delete(vaccinationRepository.findById(vaccin).get());
	}

	@Override
	public Optional<Vaccination> updateVaccination(Long id, Vaccination vaccin) {
		// TODO Auto-generated method stub
		return vaccinationRepository.findById(id).map(vaccFromDB -> {
			vaccFromDB.setName(vaccin.getName());
			vaccFromDB.setType(vaccin.getType());
			vaccFromDB.setVaccDate(vaccin.getVaccDate());
			return vaccinationRepository.save(vaccFromDB);
        });
	}




	@Override
	public List<Vaccination> getVaccinationByYearMonth(int year, int month) {
		// TODO Auto-generated method stub
		return vaccinationRepository.getByYearAndMonth(year, month);
	}

}
