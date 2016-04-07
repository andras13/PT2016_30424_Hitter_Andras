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

public class Prod extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7578737377924998666L;
	private Object[][] data;
	private DefaultTableModel model;
	private String columnNamesProduct[] = {"ID","NAME","TYPE","PRICE","QUANTITY"};
	public static JPanel ProductPanel;
	private JPanel opPanel;
	
	private JTable Product;
	public static JScrollPane scrollPane;
	
	private JLabel prodid = new JLabel("ID: ");
    private JLabel prodname = new JLabel("Name: ");
    private JLabel prodtype = new JLabel("Type: ");
    private JLabel prodprice = new JLabel("Price: ");
    private JLabel prodquantity = new JLabel("Quantity: ");
    
    public static JButton insert;
    public static JButton search;
    public static JButton update;
    public static JButton delete;
    //public static JButton back;
    
    public static JTextField ID = new JTextField();
    public static JTextField Name = new JTextField();
    public static JTextField Type = new JTextField();
    public static JTextField Price = new JTextField();
    public static JTextField Quantity = new JTextField();
    public static JTextField queryField = new JTextField("ANSWERS",50);
    
    //private QueryTableModel qtm;
    
	public Prod() throws HeadlessException {
		super("Product operations");
		Vector<String> columnNames = new Vector<String>();
		//cache = CustomerBLL.readAll();
		columnNames.addElement("product_id");
		columnNames.addElement("product_name");
		columnNames.addElement("product_type");
		columnNames.addElement("product_price");
		columnNames.addElement("product_quantity");
		
		//qtm = new QueryTableModel();
		data=ProductBLL.readAll();
		model = new DefaultTableModel(data,columnNamesProduct);
		Product = new JTable(model);
		Product.setSize(200, 100);
		//Customer.setModel(CustomerBLL.readAll(),columnNames);
		//Customer.setModel(DbUtils.resultSetToTableModel(CustomerBLL.readAll()));
		//JScrollPane scrollpane = new JScrollPane(Customer);
		
		ProductPanel = new JPanel(/*new GridLayout(0,1)*/);
		ProductPanel.setBackground(Color.LIGHT_GRAY);
    	
    	opPanel = new JPanel(new GridLayout(3,1));
    	opPanel.setBackground(Color.GREEN);
    	//CustomerPanel.setLayout(new BorderLayout().PAGE_START);
    	
    	add(ProductPanel, BorderLayout.PAGE_START);
    	add(opPanel, BorderLayout.CENTER);
    	
    	
    	JScrollPane scrollpane = new JScrollPane(Product);
    	insert = new JButton("Insert");
    	search = new JButton("Search");
    	update = new JButton("Update");
    	delete = new JButton("Delete");
    	//back = new JButton("Back");
    	
    	ProductPanel.add(scrollpane);
    	
    	opPanel.add(prodid);
    	opPanel.add(ID);
    	opPanel.add(prodname);
    	opPanel.add(Name);
    	opPanel.add(prodtype);
    	opPanel.add(Type);
    	opPanel.add(prodprice);
    	opPanel.add(Price);
    	opPanel.add(prodquantity);
    	opPanel.add(Quantity);
    	
    	opPanel.add(insert);
    	opPanel.add(search);
    	opPanel.add(update);
    	opPanel.add(delete);
    	ProductPanel.add(queryField);
    	//opPanel.add(back);
    	HandlerClass handler = new HandlerClass();
    	insert.addActionListener(handler);
    	search.addActionListener(handler);
    	update.addActionListener(handler);
    	delete.addActionListener(handler);
    	
	}
	
}
