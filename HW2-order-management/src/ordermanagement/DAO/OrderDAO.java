package ordermanagement.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import ordermanagement.model.Order;

public class OrderDAO {

	//private List<Order> orders;
	/** The query for insert. */
	private static final String CREATE_QUERY = "INSERT INTO customer (order_id, customer_id, product_id, quantity,date, Full_price) "
			+ "VALUES (?,?,?,?,?,?);";
	/** The query for read. */
	private static final String READ_QUERY = "SELECT * FROM order WHERE order_id = ?;";
	/** The query for update. */
	private static final String UPDATE_QUERY = "UPDATE order SET order_id=? ,customer_id=?, product_id=?,quantity=?,date=?,Full_price=?"
			+ " WHERE order_id = ?;";
	/** The query for delete. */
	private static final String DELETE_QUERY = "DELETE FROM order WHERE order_id = ?";
	
	// INSERT
	public static int insert(Order o){
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return -2;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        ResultSet result = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pt_homework", "Andras_homework", "homework");
			preparedStatement = connection.prepareStatement(CREATE_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, o.getOrderID());
			preparedStatement.setInt(2, o.getCustomerId());
			preparedStatement.setInt(3, o.getProductId());
			preparedStatement.setInt(4, o.getQuantity());
			preparedStatement.setString(5, o.getDate());
			preparedStatement.setFloat(6, o.getFull_price());
			preparedStatement.execute();
			result = preparedStatement.getGeneratedKeys();
			if (result.next() && result != null) {
                return result.getInt(1);
            } else {
                return -1;
            }
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return -2;
		}finally{
			try {
                result.close();
            } catch (Exception rse) {
            	System.out.println("Close result failed\n");
                return -2;                
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
            	System.out.println("Close prepStatement failed\n");
            	 return -2;                
            }
            try {
                connection.close();
            } catch (Exception cse) {
                System.out.println("Close connection failed\n");
            	 return -2;
            }
		}
				
	}
	//! READ
	public static Order read(int id){
		//orders = new ArrayList<Order>();
		Order o = new Order();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			o.setOrderID(-2);
		}
		System.out.println("MySQL JDBC Driver Registered!");
        
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/pt_homework", "Andras_homework", "homework");
			preparedStatement = connection.prepareStatement(READ_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			
			result = preparedStatement.getResultSet();

			if (result.next() && result != null) {
				System.out.println("got to Order DAO read");
                o.setOrderID(result.getInt(1));
                o.setCustomerId(result.getInt(2));
                o.setProductId(result.getInt(3));
                o.setQuantity(result.getInt(4));
                o.setDate(result.getString(5));
                o.setFull_price(result.getFloat(6));
                //orders.add(o);
            } else {
            	o.setOrderID(-1);
            }
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}finally{
			try {
                result.close();
            } catch (Exception rse) {
            	System.out.println("Close result failed\n");
                o.setOrderID(-2);                
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
            	System.out.println("Close prepStatement failed\n");
            	o.setOrderID(-2);              
            }
            try {
                connection.close();
            } catch (Exception cse) {
                System.out.println("Close connection failed\n");
                o.setOrderID(-2);
            }
		} 
        return o;	
	}
	
	public static int readLastId(){
		//orders = new ArrayList<Order>();
		Order o = new Order();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        int lastId;
        System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return -2;
		}
		System.out.println("MySQL JDBC Driver Registered!");
        
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/pt_homework", "Andras_homework", "homework");
			preparedStatement = connection.prepareStatement("SELECT * FROM order;");
			preparedStatement.execute();
			result = preparedStatement.getResultSet();
			result.last();
			lastId = result.getInt(1)+1;
			result.first();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return -2;
		}finally{
			try {
                result.close();
            } catch (Exception rse) {
            	System.out.println("Close result failed\n");
                o.setOrderID(-2);                
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
            	System.out.println("Close prepStatement failed\n");
            	o.setOrderID(-2);              
            }
            try {
                connection.close();
            } catch (Exception cse) {
                System.out.println("Close connection failed\n");
                o.setOrderID(-2);
            }
		}
        return lastId;
	}
	
	
	//! UPDATE
	public static boolean update(Order o){
		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return false;
		}
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/pt_homework", "Andras_homework", "homework");
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setInt(1, o.getOrderID());
			preparedStatement.setInt(2, o.getCustomerId());
			preparedStatement.setInt(3, o.getProductId());
			preparedStatement.setInt(4, o.getQuantity());
			preparedStatement.setString(5, o.getDate());
			preparedStatement.setFloat(6, o.getFull_price());
			preparedStatement.execute();
			return true;
			
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return false;
		}finally{
            try {
                preparedStatement.close();
            } catch (Exception sse) {
            	System.out.println("Close prepStatement failed\n");
            	 return false;                
            }
            try {
                connection.close();
            } catch (Exception cse) {
                System.out.println("Close connection failed\n");
            	 return false;
            }
		}
	}
	//! DELETE
	public static boolean delete(int id){
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return false;
		}
		System.out.println("MySQL JDBC Driver Registered!");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/pt_homework", "Andras_homework", "homework");
			preparedStatement = connection.prepareStatement(DELETE_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			return true;
			
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return false;
		}finally{
            try {
                preparedStatement.close();
            } catch (Exception sse) {
            	System.out.println("Close prepStatement failed\n");
            	 return false;                
            }
            try {
                connection.close();
            } catch (Exception cse) {
                System.out.println("Close connection failed\n");
            	 return false;
            }
		}
	}
}
