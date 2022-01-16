package com.test.covidTracker;
/*
 *    Create a REST API app to expose service which can

·         Bulk add/modify daily/monthly cases of covid (with patient details like test date, test result, name, DOB and address)

·         Bulk add/modify daily/monthly covid vaccination info(with patient details like vaccination date, second vaccination date, name, DOB and address)

·         Get report of the total number of cases per day,per month, per year for every country (atleast have three countries) with pagination support

·         Use spring boot or micronaut

·         Use in-memory database

·         Demonstrate usages of the patterns like IOC, DI, factory, ORM and so on

·         All code should be unit tested

·         Integration test should be part of the repo

·         Proper documentation using readme should be followed
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CovidTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidTrackerApplication.class, args);
	}

}
