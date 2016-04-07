package ordermanagement.gui.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;

import ordermanagement.BLL.CustomerBLL;
import ordermanagement.BLL.OrderBLL;
import ordermanagement.BLL.ProductBLL;
import ordermanagement.model.Customer;
import ordermanagement.model.Order;
import ordermanagement.model.Product;

public class HandlerClass implements ActionListener {

	public Customer c = new Customer();
	public Product p = new Product();
	public Order o = new Order();
	public static Cust customerTable;
	public static Prod productTable;
	public static Ord orderTable;

	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == Interface.Customers) {
			customerTable = new Cust();
			customerTable.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			// customerTable.setSize(1200, 900);
			customerTable.setExtendedState(JFrame.MAXIMIZED_BOTH);
			customerTable.setBackground(Color.yellow);
			customerTable.setVisible(true);

			System.out.println("Customers table");
		}

		if (event.getSource() == Interface.Products) {
			productTable = new Prod();
			productTable.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			// productTable.setSize(1200, 900);
			productTable.setExtendedState(JFrame.MAXIMIZED_BOTH);
			productTable.setBackground(Color.yellow);
			productTable.setVisible(true);

			System.out.println("Products table");
		}

		if (event.getSource() == Interface.Order) {
			orderTable = new Ord();
			orderTable.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			// customerTable.setSize(1200, 900);
			orderTable.setExtendedState(JFrame.MAXIMIZED_BOTH);
			orderTable.setBackground(Color.yellow);
			orderTable.setVisible(true);
			System.out.println("Order table");
		}
		// String input2=Interface.poly2.getText();

		if (event.getSource() == Cust.insert) {
			Customer c = new Customer();
			int id = Integer.parseInt(Cust.ID.getText());
			String Name = Cust.Name.getText();
			String Address = Cust.Address.getText();
			String Phone = Cust.phone.getText();
			c.setId(id);
			c.setName(Name);
			c.setAddress(Address);
			c.setNumber(Phone);
			try {
				CustomerBLL.insert(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Cust.queryField.setText("Inserted...");
			System.out.println("Inserted");
			customerTable.setVisible(false);
			customerTable.dispose();
			customerTable = new Cust();
			customerTable.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			// productTable.setSize(1200, 900);
			customerTable.setExtendedState(JFrame.MAXIMIZED_BOTH);
			customerTable.setBackground(Color.yellow);
			customerTable.setVisible(true);
		}
		if (event.getSource() == Cust.update) {
			Customer c = new Customer();
			int id = Integer.parseInt(Cust.ID.getText());
			String Name = Cust.Name.getText();
			String Address = Cust.Address.getText();
			String Number = Cust.phone.getText();

			c.setId(id);
			c.setName(Name);
			c.setAddress(Address);
			c.setNumber(Number);
			try {
				CustomerBLL.update(c);
				/*
				 * Cust.CustomerPanel.remove(Cust.scrollPane);
				 * Cust.CustomerPanel.add(Cust.scrollPane);
				 * Cust.CustomerPanel.revalidate();
				 * Cust.CustomerPanel.repaint();
				 */
				System.out.println("Updated");
				customerTable.setVisible(false);
				customerTable.dispose();
				customerTable = new Cust();
				customerTable.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				// productTable.setSize(1200, 900);
				customerTable.setExtendedState(JFrame.MAXIMIZED_BOTH);
				customerTable.setBackground(Color.yellow);
				customerTable.setVisible(true);
			} catch (Exception e) {
				Cust.queryField.setText("All fields must be completed correctly for this operation");
				e.printStackTrace();
			}
		}
		if (event.getSource() == Cust.search) {
			Customer c = new Customer();
			int id = Integer.parseInt(Cust.ID.getText());
			try {
				c = CustomerBLL.read(id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				String idread = Integer.toString(c.getId());
				String Name = c.getName();
				String Address = c.getAddress();
				String Number = c.getNumber();
				String output = "ID:" + idread + "; " + "Name: " + Name + "; Address: " + Address + "; Number: "
						+ Number;
				Cust.queryField.setText(output);
				System.out.println(output);
			} catch (Exception e) {
				Cust.queryField.setText("Wrong number!!!");
				e.printStackTrace();
			}
		}

		if (event.getSource() == Cust.delete) {
			Customer c = new Customer();
			int id = Integer.parseInt(Cust.ID.getText());
			try {
				c = CustomerBLL.read(id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				CustomerBLL.delete(c.getId());
				String idread = Integer.toString(c.getId());
				String Name = c.getName();
				String Address = c.getAddress();
				String Number = c.getNumber();
				String output = "ID:" + idread + "; " + "Name: " + Name + "; Address: " + Address + "; Number: "
						+ Number + " DELETETD!";
				Cust.queryField.setText(output);
				System.out.println(output);
				customerTable.setVisible(false);
				customerTable.dispose();
				customerTable = new Cust();
				customerTable.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				// productTable.setSize(1200, 900);
				customerTable.setExtendedState(JFrame.MAXIMIZED_BOTH);
				customerTable.setBackground(Color.yellow);
				customerTable.setVisible(true);
			} catch (Exception e) {
				Cust.queryField.setText("Wrong number!!!");
				e.printStackTrace();
			}
		}
		/*
		 * if (event.getSource()== Cust.back) { //return la panoul main-iesire
		 * din adminPanel //mainPanel.setVisible(true); endApp(); }
		 */
		// =======================================PRODUCT
		// HANDLERS======================================
		if (event.getSource() == Prod.insert) {
			Product p = new Product();

			int id = Integer.parseInt(Prod.ID.getText());
			String Name = Prod.Name.getText();
			String Type = Prod.Type.getText();
			float Price = Float.parseFloat(Prod.Price.getText());
			int quantity = Integer.parseInt(Prod.Quantity.getText());
			p.setId(id);
			p.setName(Name);
			p.setType(Type);
			p.setPrice(Price);
			p.setQuantity(quantity);
			try {
				ProductBLL.insert(p);
				Prod.queryField.setText("Inserted...");
				System.out.println("Inserted");
				productTable.setVisible(false);
				productTable.dispose();
				productTable = new Prod();
				productTable.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				// productTable.setSize(1200, 900);
				productTable.setExtendedState(JFrame.MAXIMIZED_BOTH);
				productTable.setBackground(Color.yellow);
				productTable.setVisible(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (event.getSource() == Prod.update) {
			Product p = new Product();
			int id = 0;
			try {
				id = Integer.parseInt(Prod.ID.getText());
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String Name = Prod.Name.getText();
			String Type = Prod.Type.getText();
			float Price = Float.parseFloat(Prod.Price.getText());
			int quantity = Integer.parseInt(Prod.Quantity.getText());

			p.setId(id);
			p.setName(Name);
			p.setType(Type);
			p.setPrice(Price);
			p.setQuantity(quantity);
			try {
				ProductBLL.update(p);
				/*
				 * Cust.CustomerPanel.remove(Cust.scrollPane);
				 * Cust.CustomerPanel.add(Cust.scrollPane);
				 * Cust.CustomerPanel.revalidate();
				 * Cust.CustomerPanel.repaint();
				 */
				System.out.println("Updated");
				productTable.setVisible(false);
				productTable.dispose();
				productTable = new Prod();
				productTable.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				// productTable.setSize(1200, 900);
				productTable.setExtendedState(JFrame.MAXIMIZED_BOTH);
				productTable.setBackground(Color.yellow);
				productTable.setVisible(true);
			} catch (Exception e) {
				Prod.queryField.setText("All fields must be completed correctly for this operation");
				e.printStackTrace();
			}
		}
		if (event.getSource() == Prod.search) {
			Product p = new Product();
			int id = Integer.parseInt(Prod.ID.getText());
			try {
				p = ProductBLL.read(id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				String idread = Integer.toString(p.getId());
				String Name = p.getName();
				String Type = p.getType();
				String Price = Float.toString(p.getPrice());
				String Quantity = Integer.toString(p.getQuantity());
				String output = "ID:" + idread + "; " + "Name: " + Name + "; Type: " + Type + "; Price: " + Price
						+ "; Quantity: " + Quantity;
				Prod.queryField.setText(output);
				System.out.println(output);
			} catch (Exception e) {
				Prod.queryField.setText("Wrong number!!!");
				e.printStackTrace();
			}
		}

		if (event.getSource() == Prod.delete) {
			Product p = new Product();
			int id = Integer.parseInt(Prod.ID.getText());
			try {
				p = ProductBLL.read(id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				ProductBLL.delete(p.getId());
				String idread = Integer.toString(p.getId());
				String Name = p.getName();
				String Type = p.getType();
				String Price = Float.toString(p.getPrice());
				String Quantity = Integer.toString(p.getQuantity());
				String output = "ID:" + idread + "; " + "Name: " + Name + "; Type: " + Type + "; Price: " + Price
						+ "; Quantity: " + Quantity + " ->DELETED<-!";
				Prod.queryField.setText(output);
				System.out.println(output);
				productTable.setVisible(false);
				productTable.dispose();
				productTable = new Prod();
				productTable.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				// productTable.setSize(1200, 900);
				productTable.setExtendedState(JFrame.MAXIMIZED_BOTH);
				productTable.setBackground(Color.yellow);
				productTable.setVisible(true);
			} catch (Exception e) {
				Cust.queryField.setText("Wrong number!!!");
				e.printStackTrace();
			}
		}
		// ================================ORDER===============================
		if (event.getSource() == ordermanagement.gui.UI.Ord.Order) {
			System.out.println("Order pressed");
			Product p1 = new Product();
			Customer c1 = new Customer();
			
			Order o = new Order();
			int nextOrder = OrderBLL.readLastId();
			System.out.println("Next order "+nextOrder);
			// Reads the product ID
			int id1 = Integer.parseInt(Ord.ID.getText());
			// Reads Customer name
			String name = Ord.Name.getText();
			// Reads quantity
			int quantity = Integer.parseInt(Ord.Quantity.getText());
			try {
				p1 = ProductBLL.read(id1);
				try {
					c1 = CustomerBLL.readName(name);
					try {
						// IF Name & ID & quantity is OK we create a new
						// order
						if (quantity >= 0 && quantity <= p1.getQuantity()) {
							o.setOrderID(nextOrder);
							o.setCustomerId(c1.getId());
							o.setProductId(p1.getId());
							o.setQuantity(quantity);
							
							p1.setQuantity(p1.getQuantity()-o.getQuantity());
							try {
								//!!!! ASDASDASDASDADADADASD MAYBE TROUBLE
								ProductBLL.update(p1);
								productTable.setVisible(false);
								productTable.dispose();
								productTable = new Prod();
								productTable.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
								// productTable.setSize(1200, 900);
								productTable.setExtendedState(JFrame.MAXIMIZED_BOTH);
								productTable.setBackground(Color.yellow);
								productTable.setVisible(true);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
							// Get the date today using Calendar object.
							Date today = Calendar.getInstance().getTime();
							// Using DateFormat format method we can create a
							// string
							// representation of a date with the defined format.
							String reportDate = df.format(today);
							o.setDate(reportDate);
							o.setFull_price(quantity * p1.getPrice());
							String output = "ID:" + nextOrder;
							String output2 = "Name: " + c1.getName();
							String output3 = "Address: " + c1.getAddress();
							String output4 = "Number: " + c1.getNumber();
							String output5 = "Product: " + p1.getName();
							String output6 = "Quantity: " + p1.getQuantity();
							String output7 = "FINAL PRICE:-------->" + o.getFull_price() + "<--------------";
							String output8 = "Ordered on date: " + o.getDate();

							try {
								// Whatever the file path is.
								File statText = new File("D:/Programok/eclipseWorkspace/order-management/Order.txt");
								FileOutputStream is = new FileOutputStream(statText);
								OutputStreamWriter osw = new OutputStreamWriter(is);
								Writer w = new BufferedWriter(osw);
								w.write(output);
								((BufferedWriter) w).newLine();
								w.write(output2);
								((BufferedWriter) w).newLine();
								w.write(output3);
								((BufferedWriter) w).newLine();
								w.write(output4);
								((BufferedWriter) w).newLine();
								w.write(output5);
								((BufferedWriter) w).newLine();
								w.write(output6);
								((BufferedWriter) w).newLine();
								w.write(output7);
								((BufferedWriter) w).newLine();
								w.write(output8);
								((BufferedWriter) w).newLine();
								w.close();
							} catch (IOException e) {
								System.err.println("Problem writing to the file statsTest.txt");
							}

							Ord.queryField.setText("Order done! ");
							// System.out.println(output);
							try {
								
								OrderBLL.insert(o);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else{
							Ord.queryField.setText("UNDERSTOK!!!");
						}
					} catch (Exception e) {
						Prod.queryField.setText("Wrong number!!!");
						e.printStackTrace();
					}
				} catch (Exception e1) {
					Ord.queryField.setText("Wrong Name! Please re-enter OR register!");
					e1.printStackTrace();
				}
			} catch (Exception e1) {
				Ord.queryField.setText("Wrong Product ID! ");
				e1.printStackTrace();
			}

		}

		if (event.getSource() == Ord.Register) {
			int nextCustomer = CustomerBLL.readLastId();
			Customer c = new Customer();
			int id = nextCustomer;
			String Name = Ord.regName.getText();
			String Address = Ord.regAddress.getText();
			String Phone = Ord.regPhone.getText();
			c.setId(id);
			c.setName(Name);
			c.setAddress(Address);
			c.setNumber(Phone);
			try {
				CustomerBLL.insert(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Ord.queryField.setText("Register pushed");
			System.out.println("Register pushed");
		}
	}
}
