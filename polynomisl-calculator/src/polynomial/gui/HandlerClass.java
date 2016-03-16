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
	public Polynomial remainderPol = new Polynomial();
	public boolean firstField;
	public  boolean secondField;
	
	public void actionPerformed(ActionEvent event) {
		
		//Interface poly1 = null;
		//String input1=Interface.poly1.getText();
		//p1 = regex(input1);
		//firstField = true;
		if(event.getSource()==Interface.poly1){
			String input=String.format("%s", event.getActionCommand());
			if(p1.equals(regex(input))){
				Interface.result1.setText("Wrongly entered!");
			}
			else{
				Interface.result1.setText("First entered!");
			}
			p1 = regex(input);
			firstField = true;
			
		}
		//String input2=Interface.poly2.getText();
		if(event.getSource()==Interface.poly2){
			String input=String.format("%s", event.getActionCommand());
			
			if(p2.equals(regex(input))){
				Interface.result2.setText("Wrongly entered!");
			}
			else{
				Interface.result2.setText("Second entered!");
			}
			p2 = regex(input);
			secondField = true;
		}
		if(event.getSource()==Interface.add && firstField == true && secondField == true){
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
		else if(event.getSource()==Interface.add){
			Interface.result1.setText("Enter a polynomial in both fields to add");
		}
		
		if(event.getSource()==Interface.sub && firstField == true && secondField == true){			
			System.out.println("\nSub: ");
			Polynomial result = Operations.sub(p1, p2);
			System.out.println(result);
			result.sort();
			System.out.println(result);
			String out =result.toString();
			Interface.result1.setText(out);
		}
		else if(event.getSource()==Interface.sub){
			Interface.result1.setText("Enter a polynomial in both fields to subtract");
		}
		if(event.getSource()==Interface.mul && firstField == true && secondField == true){
			System.out.println("\nmul: ");
			Polynomial result = Operations.mul(p1, p2);
			System.out.println(result);
			result.sort();
			System.out.println(result);
			String out =result.toString();
			Interface.result1.setText(out);
		}
		else if(event.getSource()==Interface.mul){
			Interface.result1.setText("Enter a polynomial in both fields to multiply!");
		}
		if(event.getSource()==Interface.div && firstField == true && secondField == true){			
			System.out.println("\ndiv: ");
			Polynomial result = Operations.div(p1, p2);
			System.out.println(result);
			
			remainderPol = Operations.sub(p1,Operations.mul(result, p2)); 
			System.out.println(remainderPol);
			
			result.sort();
			
			remainderPol.sort();
			System.out.println(result);
			
			String out =result.toString();
			String remainderOut = remainderPol.toString();
			Interface.result1.setText(out);
			
			Interface.result2.setText(remainderOut);
		}
		else if(event.getSource()==Interface.div){
			Interface.result1.setText("Enter a polynomial in both fields to divide!");
		}
		
		if(event.getSource()==Interface.derive && firstField == true && secondField== true){
			
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
		else if(event.getSource()==Interface.derive && firstField == true){
			System.out.println("\nDerive: ");
			Polynomial result = Operations.derive(p1);
			//System.out.println(result);
			result.sort();
			String out =result.toString();
			Interface.result1.setText(out);
		}else if(event.getSource()==Interface.derive && secondField == true){
			Polynomial result = Operations.derive(p2);
			result = Operations.derive(p2);
			System.out.println(result);
			String out2 =result.toString();
			Interface.result2.setText(out2);
		}else if(event.getSource()==Interface.derive){
			Interface.result1.setText("Enter a polynomial in at least 1 field to derive!");
		}
		
		
		if(event.getSource()==Interface.integrate && firstField == true && secondField== true){
			
			System.out.println("\nIntegrate: ");
			Polynomial result = Operations.integrate(p1);
			//System.out.println(result);
			result.sort();
			String out =result.toString();
			Interface.result1.setText(out);
			
			result = Operations.integrate(p2);
			System.out.println(result);
			String out2 =result.toString();
			Interface.result2.setText(out2);
		}
		else if(event.getSource()==Interface.integrate && firstField == true){
			System.out.println("\nIntegrate: ");
			Polynomial result = Operations.integrate(p1);
			//System.out.println(result);
			result.sort();
			String out =result.toString();
			Interface.result1.setText(out);
		}else if(event.getSource()==Interface.integrate && secondField == true){
			Polynomial result = Operations.integrate(p2);
			result = Operations.integrate(p2);
			System.out.println(result);
			String out2 =result.toString();
			Interface.result2.setText(out2);
		}else if(event.getSource()==Interface.integrate){
			Interface.result1.setText("Enter a polynomial in at least 1 field to integrate!");
		}
		
		/*if(event.getSource()==Interface.integrate){	
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
		}*/
	}
}
