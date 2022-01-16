package com.test.covidTracker;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.test.covidTracker.controllers.PersonController;
import com.test.covidTracker.models.Person;
import com.test.covidTracker.repositories.PersonRepository;

@SpringBootTest
@AutoConfigureMockMvc
class CovidTrackerApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private PersonController personController;
	@Autowired
	private PersonRepository personRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testInsertion() throws Exception {
		// Test Person Insertion
		Person person = new Person();
		person.setCin(003);
		person.setName("Mohamed Ali");
		person.setAge(20);
		personController.savePerson(003, person);

		Person new_person = personRepository.findByCin(003).get();
		assertThat(new_person.getName()).isEqualTo("Mohamed Ali");

		// Test Case add
		// Get case number before adding new case
		int total_case = new_person.getCases().size();

		String jsonString = new JSONObject().put("city", "Ariana").put("testDate", "2021-06-18").put("testResult", "P")
				.toString();

		mockMvc.perform(post("/cases/003/case").content(jsonString).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isCreated());

		int total_case_new = new_person.getCases().size();
		assertThat(total_case_new == total_case + 1);

		// Test Vaccination insertion
		// Get vaccin number before adding new vaccin
		int total_vaccin = new_person.getVacinations().size();

		jsonString = new JSONObject().put("name", "CoronaVacc").put("vaccDate", "2021-12-30").put("type", "1")
				.toString();
		mockMvc.perform(post("/vaccins/003/vaccin").content(jsonString).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isCreated());
		int total_vaccin_new = new_person.getVacinations().size();
		assertThat(total_vaccin_new == total_vaccin + 1);
	}

	@Test
	public void testGetPerson() throws Exception {
		mockMvc.perform(get("/persons/100")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andExpect(jsonPath("$.cin").value(100))
				.andExpect(jsonPath("$.name").value("Saleh ben ali")).andExpect(jsonPath("$.age").value(50))
				.andExpect(jsonPath("$.adress").value("Ariana"));

	}

}
