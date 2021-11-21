package algorithm;
import java.util.ArrayList;
import java.util.List;

public class Finder {
	private final List<Person> _p;

	public Finder(List<Person> p) {
		_p = p;
	}

	public F Find(FT ft) {
		List<F> tr = new ArrayList<F>();

		for (int i = 0; i < _p.size() - 1; i++) {
			for (int j = i + 1; j < _p.size(); j++) {
				F r = new F();
				if (_p.get(i).getBirthDate().getTime() < _p.get(j).getBirthDate().getTime()) {
					r.setPersonOne(_p.get(i));
					r.setPersonTwo(_p.get(j));
				} else {
					r.setPersonOne(_p.get(j));
					r.setPersonTwo(_p.get(i));
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
				case One :
					if (result.getD() < answer.getD()) {
						answer = result;
					}
					break;

				case Two :
					if (result.getD() > answer.getD()) {
						answer = result;
					}
					break;
			}
		}

		return answer;
	}
}
