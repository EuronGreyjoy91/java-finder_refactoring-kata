package service;
import enumerator.FT;
import model.F;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class Finder {
	private final List<Person> persons;

	public Finder(List<Person> p) {
		persons = p;
	}

	public F Find(FT ft) {
		List<F> tr = new ArrayList<F>();

		for (int i = 0; i < persons.size() - 1; i++) {
			for (int j = i + 1; j < persons.size(); j++) {
				F r = new F();
				if (persons.get(i).getBirthDate().getTime() < persons.get(j).getBirthDate().getTime()) {
					r.setPersonOne(persons.get(i));
					r.setPersonTwo(persons.get(j));
				} else {
					r.setPersonOne(persons.get(j));
					r.setPersonTwo(persons.get(i));
				}
				r.setD(r.getPersonTwo().getBirthDate().getTime() - r.getPersonOne().getBirthDate().getTime());
				tr.add(r);
			}
		}

		if (tr.size() < 1) {
			return new F();
		}

		F answer = tr.get(0);
		for (F result : tr) {
			switch (ft) {
				case ONE:
					if (result.getD() < answer.getD()) {
						answer = result;
					}
					break;

				case TWO:
					if (result.getD() > answer.getD()) {
						answer = result;
					}
					break;
			}
		}

		return answer;
	}
}
