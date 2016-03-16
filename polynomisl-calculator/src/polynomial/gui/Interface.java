package polynomial.gui;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.LinkedList;

public class Interface extends JFrame {

    private JPanel polyPanel;
    private JPanel opPanel;

    private JLabel poly1Label;
    private JLabel poly2Label;
    private JLabel resultLabel;
    private JLabel resultLabel2;
    public static JButton add;
    public static JButton sub;
    public static JButton mul;
    public static JButton div;
    public static JButton derive;
    public static JButton integrate;
    public static JTextField poly1;
    public static JTextField poly2;
    public static JTextField result1;
    public static JTextField result2;
    
    public Interface(){
    	super("Polynomial operations");
    	
    	polyPanel = new JPanel(new GridLayout(0,1));
    	polyPanel.setBackground(Color.LIGHT_GRAY);
    	opPanel = new JPanel();
    	opPanel.setBackground(Color.LIGHT_GRAY);
    	poly1Label = new JLabel("Enter the first polynomial: ");
    	poly2Label = new JLabel("Enter the second polynomial: ");
    	resultLabel = new JLabel("Result:");
    	resultLabel2 = new JLabel("Result only for 2nd polynomial derive/integrate");
    	
    	add(polyPanel, BorderLayout.PAGE_START);
    	add(opPanel, BorderLayout.CENTER);
    	
    	poly1 = new JTextField(30);
    	poly1.setToolTipText("Enter in form: 3x^3+2x^2-3x^0");
    	poly1.setBackground(Color.cyan);
    	
    	poly2 = new JTextField(30);
    	poly2.setToolTipText("Enter in form: 3x^3+2x^2");
    	poly2.setBackground(Color.pink);
    	
    	result1 = new JTextField(30);
    	result1.setToolTipText("Result for + - * / der(poly 1) integ(poly 1)");
    	result1.setEditable(false);
    	result1.setBackground(Color.green);
    	
    	result2 = new JTextField(30);
    	result2.setToolTipText("Result for der(poly 2) integ(poly 2)");
    	result2.setEditable(false);
    	result2.setBackground(Color.green);
    	
    	polyPanel.add(poly1Label);
    	polyPanel.add(poly1);
    	//polyPanel.add(spacer = new JLabel(" "),"span, grow");
    	polyPanel.add(poly2Label);
    	polyPanel.add(poly2);
    	polyPanel.add(resultLabel);
    	polyPanel.add(result1);
    	polyPanel.add(resultLabel2);
    	polyPanel.add(result2);
    	
    	add = new JButton("+");
    	sub = new JButton("-");
    	mul = new JButton("*");
    	div = new JButton("/");
    	derive = new JButton("derive");
    	integrate = new JButton("integrate");
    	opPanel.add(add);
    	opPanel.add(sub);
    	opPanel.add(mul);
    	opPanel.add(div);
    	opPanel.add(derive);
    	opPanel.add(integrate);
    	
    	HandlerClass handler = new HandlerClass();
    	add.addActionListener(handler);
    	sub.addActionListener(handler);
    	mul.addActionListener(handler);
    	div.addActionListener(handler);
    	derive.addActionListener(handler);
    	integrate.addActionListener(handler);
    	poly1.addActionListener(handler);
    	poly2.addActionListener(handler);
    	
    }
}
