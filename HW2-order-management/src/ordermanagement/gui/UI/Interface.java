package ordermanagement.gui.UI;

	import java.awt.*;
	import javax.swing.*;
	import javax.swing.JFrame;

	public class Interface extends JFrame {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7881886153198882315L;
		private JPanel opPanel;
	    private JLabel CustomerLabel;
	    private JLabel ProductLabel;
	    private JLabel OrderLabel;
	    public static JButton Products;
	    public static JButton Customers;
	    public static JButton Order;
	    
	    public Interface(){
	    	super("Order management");
	    	
	    	opPanel = new JPanel();
	    	opPanel.setLayout(new GridLayout(3, 2));
	    	opPanel.setBackground(Color.LIGHT_GRAY);
	    	CustomerLabel = new JLabel("Click to edit the customers: ");
	    	ProductLabel = new JLabel("Click to edit the products: ");
	    	OrderLabel = new JLabel("Click to order something: ");
	    	new JLabel("Result only for 2nd polynomial derive/integrate");
	    	
	    	//add(polyPanel, BorderLayout.PAGE_START);
	    	add(opPanel, BorderLayout.CENTER);
	    	
	    	Customers = new JButton("Customers");
	    	Products = new JButton("Products");
	    	Order = new JButton("Order");

	    	opPanel.add(CustomerLabel);
	    	opPanel.add(Customers);
	    	opPanel.add(ProductLabel);
	    	opPanel.add(Products);
	    	opPanel.add(OrderLabel);
	    	opPanel.add(Order);
	    	
	    	HandlerClass handler = new HandlerClass();
	    	Customers.addActionListener(handler);
	    	Products.addActionListener(handler);
	    	Order.addActionListener(handler);
	    		    	
	    }
	}