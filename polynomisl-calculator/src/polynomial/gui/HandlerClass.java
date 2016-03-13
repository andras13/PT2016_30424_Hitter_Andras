package polynomial.gui;

import java.awt.event.*;
import java.security.GeneralSecurityException;
import java.util.regex.*;

import plynomial.logic.Operations;
import polynomial.model.Polynomial;
import polynomial.model.Term;

public class HandlerClass implements ActionListener {

	public static Polynomial regex(String inputPolynomial) {
		// String input = "2x^3-3x^-2";
		Polynomial p1 = new Polynomial();
		double coeff = 0;
		int degree = 0;
		Term term = new Term(coeff, degree);
		Pattern p = Pattern.compile("(-?\\b\\d+)[xX]\\^(-?\\d+\\b)");
		Matcher m = p.matcher(inputPolynomial);
		while (m.find()) {
			coeff = Double.parseDouble(m.group(1));
			degree = Integer.parseInt(m.group(2));
			term.setCoeff(coeff);
			term.setDegree(degree);
			p1.addTerm(term);
			System.out.println("Coef: " + m.group(1));
			System.out.println("Degree: " + m.group(2));
		}
		p1.sort();
		return p1;
	}
	public Polynomial p1 = new Polynomial();
	public Polynomial p2 = new Polynomial();

	public void actionPerformed(ActionEvent event) {
		
		//Interface poly1 = null;
		if(event.getSource()==Interface.poly1){
			String input=String.format("%s", event.getActionCommand());
			p1 = regex(input);
		}
		if(event.getSource()==Interface.poly2){
			String input=String.format("%s", event.getActionCommand());
			p2 = regex(input);
		}
		if(event.getSource()==Interface.add){
			System.out.println(p1);
			System.out.println(p2);
			
			System.out.println("\nAdd: ");
			Polynomial result = Operations.add(p1, p2);
			System.out.println(result);
			result.sort();
			System.out.println(result);
			String out =result.toString();
			Interface.result1.setText(out);
		}
		if(event.getSource()==Interface.sub){			
			System.out.println("\nSub: ");
			Polynomial result = Operations.sub(p1, p2);
			System.out.println(result);
			result.sort();
			System.out.println(result);
			String out =result.toString();
			Interface.result1.setText(out);
		}
		if(event.getSource()==Interface.mul){
			System.out.println("\nmul: ");
			Polynomial result = Operations.mul(p1, p2);
			System.out.println(result);
			result.sort();
			System.out.println(result);
			String out =result.toString();
			Interface.result1.setText(out);
		}
		if(event.getSource()==Interface.div){			
			System.out.println("\ndiv: ");
			Polynomial result = Operations.div(p1, p2);
			System.out.println(result);
			result.sort();
			//System.out.println(result);
			String out =result.toString();
			Interface.result1.setText(out);
		}
		if(event.getSource()==Interface.derive){
			
			System.out.println("\nDerive: ");
			Polynomial result = Operations.derive(p1);
			//System.out.println(result);
			result.sort();
			String out =result.toString();
			Interface.result1.setText(out);
			
			result = Operations.derive(p2);
			System.out.println(result);
			String out2 =result.toString();
			Interface.result2.setText(out2);
		}
		if(event.getSource()==Interface.integrate){	
			System.out.println("\nIntegrate: ");
			Polynomial result = Operations.integrate(p1);
			System.out.println(result);
			result.sort();
			String out =result.toString();
			Interface.result1.setText(out);
			
			result = Operations.integrate(p2);
			System.out.println(result);
			String out2 =result.toString();
			Interface.result2.setText(out2);
		}
	}
}
