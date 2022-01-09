package com.test.covidTracker.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.test.covidTracker.models.CaseCovid;
import com.test.covidTracker.models.CaseResponseTemplate;

public interface CaseService {

	List<CaseCovid> getCases();
	List<CaseCovid> getCaseByDate(Date date);
	CaseResponseTemplate getCaseByPerson(int personId);
	Optional<CaseCovid> getCaseById(Long id);
	void Insert(CaseCovid _case, int personId);
	void Delete(Long id);
	Optional<CaseCovid> updateCase(Long id, CaseCovid _case);
}
