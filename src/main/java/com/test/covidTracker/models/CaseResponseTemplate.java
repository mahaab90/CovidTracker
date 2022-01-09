/**
 * Model presents a tested person with his registred cases 
 */

package com.test.covidTracker.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseResponseTemplate {

	Person person;
	List<CaseCovid> cases;
}
