package polynomial.model;

/**
 *It simulates a TERM from the polynomial 
 *It has a coefficient and a degree.
 */

public class Term {
	private double coeff;
	private int degree;

	public Term(double coeff, int degree) {
		super();
		this.coeff = coeff;
		this.degree = degree;
	}
	
	public Term(Term t) {
		super();
		this.coeff = t.coeff;
		this.degree = t.degree;
	}

	public double getCoeff() {
		return coeff;
	}

	public void setCoeff(double d) {
		this.coeff = d;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	/**
	 * True if Obj(a) equals Obj(b)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(!(obj instanceof Term)){
			return false;
		}
		
		Term that = (Term)obj;
		
		return that.coeff == this.coeff && that.degree == this.degree;
	}

}
