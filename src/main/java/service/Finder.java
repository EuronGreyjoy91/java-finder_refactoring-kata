package service;
import enumerator.FinderType;
import model.F;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class Finder {
	private final List<Person> persons;

	public Finder(List<Person> persons) {
		this.persons = persons;
	}

	public F Find(FinderType finderType) { //ft = ONE, TWO
		List<F> tr = new ArrayList<F>();

		//Este primer bloque lo que hace es iterar la lista de personas que se le pasa,
		//y por cada una se fija si es mayor o menor (edad) con las otras, aparte de eso le setea la diferencia en milisegundos
		//entre las edades.

		for (int i = 0; i < persons.size() - 1; i++) {
			for (int j = i + 1; j < persons.size(); j++) {
				F f = new F();
				if (persons.get(i).getBirthDate().getTime() < persons.get(j).getBirthDate().getTime()) {
					f.setPersonOne(persons.get(i));
					f.setPersonTwo(persons.get(j));
				} else {
					f.setPersonOne(persons.get(j));
					f.setPersonTwo(persons.get(i));
				}
				f.setD(f.getPersonTwo().getBirthDate().getTime() - f.getPersonOne().getBirthDate().getTime());
				tr.add(f);
			}
		}

		//Si la lista donde se guardan las evaluaciones entre personas esta vacia, devuelvo un objeto F vacio
		if (tr.size() < 1) {
			return new F();
		}

		//Agarro la primer evaluacion entre personas because... reasons.
		F answer = tr.get(0);

		//Itero la lista de evaluaciones,
		// si el tipo de busqueda es ONE, el resultado va a ser la persona con MENOR diferencia entre edades
		// si el tipo de busqueda es TWO, el resultado va a ser la persona con MAYOR diferencia entre edades

		for (F result : tr) {
			switch (finderType) {
				case CLOSEST:
					if (result.getD() < answer.getD()) {
						answer = result;
					}
					break;

				case FURTHEST:
					if (result.getD() > answer.getD()) {
						answer = result;
					}
					break;
			}
		}

		return answer;
	}
}
