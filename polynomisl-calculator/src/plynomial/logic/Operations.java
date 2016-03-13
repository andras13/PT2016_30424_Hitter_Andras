package plynomial.logic;

import polynomial.model.Polynomial;
import polynomial.model.Term;

public class Operations {

	public static Polynomial add(Polynomial p1, Polynomial p2) {
		Polynomial result = new Polynomial(p1);
		for (Term term : p2.getTerms()) {
			result.addTerm(term);
		}
		return result;
	}

	public static Polynomial sub(Polynomial p1, Polynomial p2) {
		Polynomial result = new Polynomial(p1);
		Polynomial p2copy = new Polynomial(p2);
		for (Term term : p2copy.getTerms()) {
			double coeff = term.getCoeff();
			term.setCoeff((-1) * coeff);
			result.addTerm(term);
		}
		return result;
	}

	public static Polynomial mul(Polynomial p1, Polynomial p2) {
		Polynomial result = new Polynomial();
		double coeff = 0;
		int degree = 0;
		Term aux = new Term(coeff, degree);

		for (Term term : p2.getTerms()) {
			for (Term term1 : p1.getTerms()) {
				coeff = term.getCoeff() * term1.getCoeff();
				degree = term.getDegree() + term1.getDegree();
				aux.setCoeff(coeff);
				aux.setDegree(degree);
				result.addTerm(aux);
			}
		}
		return result;
	}

	public static Polynomial minus(Polynomial p1) {
		Polynomial result = new Polynomial();
		for (Term term : p1.getTerms()) {
			double coeff = term.getCoeff();
			term.setCoeff((-1) * coeff);
			result.addTerm(term);
		}
		return result;
	}

	public static Polynomial div(Polynomial p1, Polynomial p2) {
		Polynomial ZERO = new Polynomial();
		Term t1 = new Term(0, 0);
		ZERO.addTerm(t1);
		Polynomial remainder = new Polynomial(p1);
		Term a = p1.getTerms().get(0);// Term b = p2.getTerms().get(0);
		if (p2.equals(ZERO)) {throw new RuntimeException("Divide by zero polynomial");}		
		if (a.getDegree() < p2.getTerms().get(0).getDegree()) {return ZERO;}
		
		double coeff = 0;
		int degree = 0;
		Term quotent = new Term(coeff, degree);
		Polynomial finalResult = new Polynomial();
		Polynomial product = new Polynomial();
		Polynomial result = new Polynomial();

		while ((p2.getTerms().get(0).getDegree() <= a.getDegree()) && !(remainder.equals(ZERO))) {
			quotent.setDegree(a.getDegree() - p2.getTerms().get(0).getDegree());
			quotent.setCoeff(a.getCoeff() / p2.getTerms().get(0).getCoeff());
			result = new Polynomial();
			result.addTerm(quotent);
			finalResult.addTerm(quotent);

			product = minus(mul(result, p2));
			remainder = add(remainder, product);
			remainder.sort();
			a = remainder.getTerms().get(0);
		}
		return finalResult;
	}

	public static Polynomial derive(Polynomial p1){
		Polynomial result = new Polynomial();
		double coeff = 0;
		int degree = 0;
		Term aux = new Term(coeff, degree);
		for(Term term : p1.getTerms()){
			if(term.getDegree()!=0){
				aux.setCoeff(term.getDegree() * term.getCoeff());
				aux.setDegree(term.getDegree()-1);
			}
			else{
				aux.setCoeff(0);
			}
			result.addTerm(aux);
		}
		return result;
	}
	
	public static Polynomial integrate(Polynomial p1){
		Polynomial result = new Polynomial();
		double coeff = 0;
		int degree = 0;
		Term aux = new Term(coeff, degree);
		for(Term term :p1.getTerms()){
			aux.setDegree(term.getDegree()+1);
			aux.setCoeff(term.getCoeff() / aux.getDegree());
			result.addTerm(aux);
		}
		return result;
	}
}
