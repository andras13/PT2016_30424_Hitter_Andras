package polynomial.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Polynomial {

	private List<Term> terms;

	public Polynomial() {
		terms = new ArrayList<Term>();
	}

	public Polynomial(Polynomial p) {
		terms = new ArrayList<Term>();
		for (Term t : p.getTerms()) {
			this.terms.add(new Term(t.getCoeff(), t.getDegree()));
		}
	}
	/*public String getOutString(Polynomial p){
		terms = new ArrayList<Term>();
		String output="";
		for(Term t : p.getTerms()){
			this.terms.add(new Term(t.getCoeff(),t.getDegree()));
		}
		output = toString();
		return output;
	}*/

	/**
	 * Uses Compar it sorts 'terms' and compares their getDegree()
	 */
	public void sort() {
		Collections.sort(terms, new TermComparator());
	}

	public void addTerm(Term t) {
		boolean termExists = false;
		boolean removable = false;
		for (Term currentT : terms) {
			removable = false;
			if (currentT.getDegree() == t.getDegree()) {
				double coeff = currentT.getCoeff();
				if (coeff + t.getCoeff() != 0) {
					currentT.setCoeff(coeff + t.getCoeff());
				}
				else{
					terms.remove(currentT);					//terms.remove(t);
				}
				termExists = true;
				break;
			}
		}
		if (!termExists) {
			terms.add(new Term(t));
		}
	}

	public List<Term> getTerms() {
		return terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Term first = terms.get(0);
		for (Term t : terms) {
			if(t.getCoeff() == -1 /*&& t == first*/) {sb.append("-");}
			if (t.getCoeff() > 0 && t != first) {
				sb.append("+");
			}// else { sb.append("-"); }	
			if (t.getCoeff() != 1 && t.getCoeff() != -1) {// At double values checks if it has the form: 2.0 If has then
				if ((t.getCoeff() * 10) % 10 == 0 && t.getCoeff() % 10 != 0) {// eliminates the .0
					sb.append((int) t.getCoeff());
				} else {
					double decimal = t.getCoeff();
					decimal *= 100;
					decimal = (int) decimal;
					decimal /= 100;
					sb.append(decimal);
				}
			}
			if (t.getDegree() != 1) { 			// If the term is like x^1 it will write only x
				if (t.getDegree() != 0) {					// if x^0 writes nothing
					sb.append("x^");
					sb.append(t.getDegree());
				}
			} else { sb.append("x");}
		}
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Polynomial)) {
			return false;
		}

		Polynomial that = (Polynomial) obj;

		return that.terms.equals(this.terms);
	}
}
