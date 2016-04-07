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

import ordermanagement.BLL.CustomerBLL;

public class Cust extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4105621565579935623L;
	//private final ResultSetTableModel model = new ResultSetTableModel();
	
	private Object[][] data;
	private DefaultTableModel model;
	private String columnNamesProduct[] = {"ID","NAME","ADDR","PHONE"};
	public static JPanel CustomerPanel;
	private JPanel opPanel;
	
	private JTable Customer;
	public static JScrollPane scrollPane;
	
	private JLabel custid = new JLabel("ID: ");
    private JLabel custname = new JLabel("Name: ");
    private JLabel custaddr = new JLabel("Address: ");
    private JLabel custphone = new JLabel("Phone number: ");
    
    
    public static JButton insert;
    public static JButton search;
    public static JButton update;
    public static JButton delete;
    //public static JButton back;
    
    public static JTextField ID = new JTextField();
    public static JTextField Name = new JTextField();
    public static JTextField Address = new JTextField();
    public static JTextField phone = new JTextField();
    public static JTextField queryField = new JTextField("ANSWERS",50);
    
    //private QueryTableModel qtm;
    
	public Cust() throws HeadlessException {
		super("Customer operations");
		Vector<String> columnNames = new Vector<String>();
		//cache = CustomerBLL.readAll();
		columnNames.addElement("customer_id");
		columnNames.addElement("customer_name");
		columnNames.addElement("customer_address");
		columnNames.addElement("customer_number");
		
		//qtm = new QueryTableModel();
		data=CustomerBLL.readAll();
		model = new DefaultTableModel(data,columnNamesProduct);
		Customer = new JTable(model);
		Customer.setSize(200, 100);
		//Customer.setModel(CustomerBLL.readAll(),columnNames);
		//Customer.setModel(DbUtils.resultSetToTableModel(CustomerBLL.readAll()));
		//JScrollPane scrollpane = new JScrollPane(Customer);
		
		CustomerPanel = new JPanel(/*new GridLayout(0,1)*/);
    	CustomerPanel.setBackground(Color.LIGHT_GRAY);
    	
    	opPanel = new JPanel(new GridLayout(3,1));
    	opPanel.setBackground(Color.ORANGE);
    	//CustomerPanel.setLayout(new BorderLayout().PAGE_START);
    	
    	add(CustomerPanel, BorderLayout.PAGE_START);
    	add(opPanel, BorderLayout.CENTER);
    	
    	
    	JScrollPane scrollpane = new JScrollPane(Customer);
    	insert = new JButton("Insert");
    	search = new JButton("Search");
    	update = new JButton("Update");
    	delete = new JButton("Delete");
    	//back = new JButton("Back");
    	
    	CustomerPanel.add(scrollpane);
    	
    	opPanel.add(custid);
    	opPanel.add(ID);
    	opPanel.add(custname);
    	opPanel.add(Name);
    	opPanel.add(custaddr);
    	opPanel.add(Address);
    	opPanel.add(custphone);
    	opPanel.add(phone);
    	
    	opPanel.add(insert);
    	opPanel.add(search);
    	opPanel.add(update);
    	opPanel.add(delete);
    	CustomerPanel.add(queryField);
    	//opPanel.add(back);
    	HandlerClass handler = new HandlerClass();
    	insert.addActionListener(handler);
    	search.addActionListener(handler);
    	update.addActionListener(handler);
    	delete.addActionListener(handler);
    	
	}
	
}
