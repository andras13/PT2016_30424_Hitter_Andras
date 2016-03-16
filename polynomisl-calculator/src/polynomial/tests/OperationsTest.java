package polynomial.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.event.ActionListener;

import plynomial.logic.Operations;
import polynomial.gui.HandlerClass;
import polynomial.model.Polynomial;
import polynomial.model.Term;

public class OperationsTest {

	@Test
	public void testAddition() {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial result = new Polynomial();
		p1 = HandlerClass.regex("3x^3+3x^2");
		p2 = HandlerClass.regex("-3x^2+2x^1");
		result = Operations.add(p1,p2);
		result.sort();
		System.out.println(result);
		assertTrue("check addition", result.toString().equals("3x^3+2x"));
				
		/*Term t1 = new Term(-3, 2);
		Term t2 = new Term(2, 1);
		p1.addTerm(t1);
		p1.addTerm(t2);
		
		Term t3 = new Term(3, 3);
		Term t4 = new Term(4, 2);
		
		p2.addTerm(t3);
		p2.addTerm(t4);
		
		Term tr1 = new Term(3, 3);
		Term tr2 = new Term(1, 2);
		Term tr3 = new Term(2, 1);
		result.addTerm(tr1);
		result.addTerm(tr2);
		result.addTerm(tr3);
		
		Polynomial resultAdd = Operations.add(p1, p2);
		
		result.sort();
		resultAdd.sort();
		assertTrue("check addition", result.equals(resultAdd));*/
		
	}
	
	@Test
	public void testSubtraction() {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial result = new Polynomial();
		p1 = HandlerClass.regex("3x^3+3x^2");
		p2 = HandlerClass.regex("-3x^2+2x^1");
		result = Operations.sub(p1,p2);
		result.sort();
		System.out.println(result);
		assertTrue("check addition", result.toString().equals("3x^3+6x^2-2x"));	
	}
	
	@Test
	public void testMultiplication() {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial result = new Polynomial();
		p1 = HandlerClass.regex("3x^3+3x^2");
		p2 = HandlerClass.regex("-3x^2+2x^1");
		result = Operations.mul(p1,p2);
		result.sort();
		System.out.println(result);
		assertTrue("check addition", result.toString().equals("-9x^5-3x^4+6x^3"));	
	}
	
	@Test
	public void testDivision() {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial result = new Polynomial();
		p1 = HandlerClass.regex("3x^3+3x^2");
		p2 = HandlerClass.regex("-3x^2+2x^1");
		result = Operations.div(p1,p2);
		result.sort();
		System.out.println(result);
		assertTrue("check addition", result.toString().equals("-x-1.66"));	
	}
	
	@Test
	public void testDerive() {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial result = new Polynomial();
		p1 = HandlerClass.regex("3x^3+3x^2");
		p2 = HandlerClass.regex("-3x^2+2x^1");
		result = Operations.derive(p1);
		result.sort();
		System.out.println(result);
		assertTrue("check addition", result.toString().equals("9x^2+6x"));	
	}
	@Test
	public void testIntegrate() {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		Polynomial result = new Polynomial();
		p1 = HandlerClass.regex("3x^3+3x^2");
		p2 = HandlerClass.regex("-3x^2+2x^1");
		result = Operations.integrate(p1);
		result.sort();
		System.out.println(result);
		assertTrue("check addition", result.toString().equals("0.75x^4+x^3"));	
	}
	
}
