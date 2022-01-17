# CovidTracker
springboot project to manipulate covid data
we have 3 entities to deal with
1: Person : CIN [number as primary key]
            Name [String as name of person]
            age [number as age of person]
            adress[String as ardess of person]
            list_Case [detected person cases==> association OneToMany with case entity]
2:Case : ID [number auto-generated as primary key]
         testDate [date of test]
         testResult [String as result of test , enum {'P','N'}]
         city [String as city where case is detected]
         person[foreign key map case to tested person ]
2:Vaccination : ID [number auto-generated as primary key]
                VaccinDate [date of vaccination]
                Type [Nuber as type of vacciantion , enum {'1','2'}]
                name [String as vaccine name ]
                person[foreign key map case to vaccinated person ]
                
Using Rest request, we apply the CRUD operation for the different entities
-Get all persons: http://localhost:8080/persons
-Get person by cin: http://localhost:8080/persons/{person.cin}
-Post new perons:  http://localhost:8080/persons/{person.cin}

-Get all case: http://localhost:8080/cases
-Get case by id:http://localhost:8080/cases/id/{caseId}
-Get case by test Date:http://localhost:8080/cases/date/{testDate}
-Get person cases:http://localhost:8080/cases/perso/{CIN}
-Get case in specific month: http://localhost:8080/cases/date/?{year=year}?{month=month}
-PUT existing case: http://localhost:8080/cases/{caseID}
-DELETE existing case: http://localhost:8080/cases/{caseID}
-POST new case:http://localhost:8080/cases/{CIN}/case

-Get all vaccinations: http://localhost:8080/vaccins
-Get vaccination by ID=http://localhost:8080/vaccins/id/{vaccinId}
-Get vaccination by name=http://localhost:8080/vaccins/id/{name}
-Get vaccination by type=http://localhost:8080/vaccins/id/{type}
-Get person vaccinations=http://localhost:8080/vaccins/person/{CIN}
-Get vaccination in specific month: http://localhost:8080/vaccins/date/?{year=year}?{month=month}
-PUT existing vaccination:http://localhost:8080/vaccins/{vaccinId}
-DELETE existion vaccination:http://localhost:8080/vaccins/{vaccinId}
-POST new vaccination:http://localhost:8080/vaccins/{CIN}/vaccin

     
         
