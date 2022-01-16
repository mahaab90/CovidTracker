/**
 * Model presents the tested persons
 * a tested person is defined BY: identity number, name, birth city, age, set of the registred cased
 */

package com.test.covidTracker.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

	@Id
	@Column(updatable = false, nullable = false)
	private int cin;
	private String name;

	private String adress;
	private int age;

	
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Vaccination> vacinations;
	
	
	public void setVaccinations(Set<Vaccination> vacinations) {
		this.vacinations = vacinations;
		for (Vaccination vaccin : vacinations) {
			vaccin.setPerson(this);
		}
	}
	
	
	
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<CaseCovid> cases;

	public void setCases(Set<CaseCovid> cases) {
		this.cases = cases;
		for (CaseCovid _case : cases) {
			_case.setPerson(this);
		}
	}
	




}
