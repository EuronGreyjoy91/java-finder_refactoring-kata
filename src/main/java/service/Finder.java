package service;
import enumerator.FinderType;
import model.PersonsComparison;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class Finder {
	private final List<Person> persons;

	public Finder(List<Person> persons) {
		this.persons = persons;
	}

	public PersonsComparison Find(FinderType finderType) { //ft = ONE, TWO
		List<PersonsComparison> tr = new ArrayList<PersonsComparison>();

		//Este primer bloque lo que hace es iterar la lista de personas que se le pasa,
		//y por cada una se fija si es mayor o menor (edad) con las otras, aparte de eso le setea la diferencia en milisegundos
		//entre las edades.

		for (int i = 0; i < persons.size() - 1; i++) {
			for (int j = i + 1; j < persons.size(); j++) {
				PersonsComparison personsComparison = new PersonsComparison();

				if (persons.get(i).getBirthDate().getTime() < persons.get(j).getBirthDate().getTime()) {
					personsComparison.setYoungestPerson(persons.get(i));
					personsComparison.setOldestPerson(persons.get(j));
				} else {
					personsComparison.setYoungestPerson(persons.get(j));
					personsComparison.setOldestPerson(persons.get(i));
				}

				personsComparison.setMillisBetweenOldestAndYoungest(personsComparison.getOldestPerson().getBirthDate().getTime() - personsComparison.getYoungestPerson().getBirthDate().getTime());
				tr.add(personsComparison);
			}
		}

		//Si la lista donde se guardan las evaluaciones entre personas esta vacia, devuelvo un objeto F vacio
		if (tr.size() < 1) {
			return new PersonsComparison();
		}

		//Agarro la primer evaluacion entre personas because... reasons.
		PersonsComparison answer = tr.get(0);

		//Itero la lista de evaluaciones,
		// si el tipo de busqueda es ONE, el resultado va a ser la persona con MENOR diferencia entre edades
		// si el tipo de busqueda es TWO, el resultado va a ser la persona con MAYOR diferencia entre edades

		for (PersonsComparison result : tr) {
			switch (finderType) {
				case CLOSEST:
					if (result.getMillisBetweenOldestAndYoungest() < answer.getMillisBetweenOldestAndYoungest()) {
						answer = result;
					}
					break;

				case FURTHEST:
					if (result.getMillisBetweenOldestAndYoungest() > answer.getMillisBetweenOldestAndYoungest()) {
						answer = result;
					}
					break;
			}
		}

		return answer;
	}
}
