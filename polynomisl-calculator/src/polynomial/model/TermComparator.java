package polynomial.model;

import java.util.Comparator;

public class TermComparator implements Comparator<Term> {

	@Override
	public int compare(Term arg0, Term arg1) {
		if (arg0.getDegree() < arg1.getDegree()) {
			return 1;
		}

		if (arg0.getDegree() > arg1.getDegree()) {
			return -1;
		}
		return 0;
	}

}
