package ordermanagement.gui.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ordermanagement.BLL.ProductBLL;

public class Ord extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7646057196568215731L;
	private Object[][] data;
	private DefaultTableModel ordModel;
	private String ordColumnNamesProduct[] = {"ID","NAME","TYPE","PRICE","QUANTITY"};
	public static JPanel ordProductPanel;
	private JPanel ordOpPanel;
	private JTable ordProduct;
	public static JScrollPane ordScrollPane;
	
	private JLabel prodid = new JLabel("Product ID: ");
	private JLabel prodquantity = new JLabel("Quantity: ");
    private JLabel YourName = new JLabel("YourName: ");
    private JLabel EnterName = new JLabel("Enter Name: ");
    private JLabel YourAddress = new JLabel("YourAddress: ");
    private JLabel YourNumber = new JLabel("YourNumber: ");
 
    public static JButton Register;
    public static JButton Order;
    
    public static JTextField ID = new JTextField();
    public static JTextField Quantity = new JTextField();
    public static JTextField Name = new JTextField();
    public static JTextField regName = new JTextField();
    public static JTextField regAddress = new JTextField();
    public static JTextField regPhone = new JTextField();
    public static JTextField queryField = new JTextField("ANSWERS",50);

	public Ord() throws HeadlessException {
		super("Ordering");
		Vector<String> columnNames = new Vector<String>();
		//cache = CustomerBLL.readAll();
		columnNames.addElement("product_id");
		columnNames.addElement("product_name");
		columnNames.addElement("product_type");
		columnNames.addElement("product_price");
		columnNames.addElement("product_quantity");
		
		//qtm = new QueryTableModel();
		data=ProductBLL.readAll();
		ordModel = new DefaultTableModel(data,ordColumnNamesProduct);
		ordProduct = new JTable(ordModel);
		ordProduct.setSize(200, 100);
		ordProductPanel = new JPanel(/*new GridLayout(0,1)*/);
		ordProductPanel.setBackground(Color.LIGHT_GRAY);
    	
    	ordOpPanel = new JPanel(new GridLayout(0,1));
    	ordOpPanel.setBackground(Color.GREEN);
    	//CustomerPanel.setLayout(new BorderLayout().PAGE_START);
    	
    	add(ordProductPanel, BorderLayout.PAGE_START);
    	add(ordOpPanel, BorderLayout.CENTER);
    	//add(customerPanel, BorderLayout.PAGE_END);

    	JScrollPane scrollpane = new JScrollPane(ordProduct);
    	Order = new JButton("Order");
    	Register = new JButton("Register");
    	//back = new JButton("Back");
    	
    	ordProductPanel.add(scrollpane);
    	
    	ordOpPanel.add(prodid);
    	ordOpPanel.add(ID);
    	ordOpPanel.add(prodquantity);
    	ordOpPanel.add(Quantity);
    	ordOpPanel.add(YourName);
    	ordOpPanel.add(Name);
    	ordOpPanel.add(Order);
    	
    	ordOpPanel.add(EnterName);
    	ordOpPanel.add(regName);
    	ordOpPanel.add(YourAddress);
    	ordOpPanel.add(regAddress);
    	ordOpPanel.add(YourNumber);
    	ordOpPanel.add(regPhone);
    	ordOpPanel.add(Register);
    	ordProductPanel.add(queryField);

    	HandlerClass handler1 = new HandlerClass();
    	Order.addActionListener(handler1);
    	Register.addActionListener(handler1);    	
	}
}
