package com.test.covidTracker.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationResponseTemplate {
	private int cin;
	private String name;

	private String adress;
	private int age;
	List<Vaccination> vaccs;
}
