package polynomial.model;


import javax.management.openmbean.OpenDataException;
import javax.swing.JFrame;
import java.util.Scanner;
import java.awt.*;
import plynomial.logic.Operations;
import polynomial.gui.Interface;

public class Start {

	public static void main(String[] args){
		
		Interface go = new Interface();
		go.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        go.setSize(400,250);
        go.setBackground(Color.yellow);
        go.setVisible(true);
		/*Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		
		Term t1 = new Term (-4, 2);
		Term t2 = new Term (2, 1);
		
		Term t3 = new Term (3, 3);
		Term t4 = new Term (3, 2);
		
		p1.addTerm(t1);
		p1.addTerm(t2);
		
		p2.addTerm(t3);
		p2.addTerm(t4);
		
		System.out.println(p1);
		System.out.println(p2);
		
		System.out.println("\nAdd: ");
		Polynomial result = Operations.add(p1, p2);
		System.out.println(result);
		result.sort();
		System.out.println(result);
		
		System.out.println("\nSubtract: ");
		result = Operations.sub(p1, p2);
		System.out.println(result);
		result.sort();
		System.out.println(result);
		
		System.out.println("\nMultiply: ");
		result = Operations.mul(p1, p2);
		System.out.println(result);
		result.sort();
		System.out.println(result);
		
		System.out.println("\nMinus: ");
		Operations.minus(result);
		System.out.println(result);
		
		System.out.println("\nDivision: ");
		result = Operations.div(p2, p1);
		System.out.println(result);
		result.sort();
		//System.out.println(result);
		
		System.out.println("\nDerive: ");
		result = Operations.derive(p1);
		System.out.println(result);
		
		System.out.println("\nIntegrate: ");
		result = Operations.integrate(p1);
		System.out.println(result);*/
	}
}
