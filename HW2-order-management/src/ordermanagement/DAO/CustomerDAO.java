package ordermanagement.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ordermanagement.model.Customer;

public class CustomerDAO {
	
	//private static List<Customer> customers;
	/** The query for insert. */
	private static final String CREATE_QUERY = "INSERT INTO customer (customer_id, customer_name,customer_address,customer_number) "
			+ "VALUES (?,?,?,?);";
	/** The query for read. */
	private static final String READ_QUERY = "SELECT * FROM customer where customer_id = ?;";
	/** The query for update. */
	private static final String UPDATE_QUERY = "UPDATE customer SET customer_name=?, customer_address=?,customer_number=?"
			+ " WHERE customer_id = ?;";
	/** The query for delete. */
	private static final String DELETE_QUERY = "DELETE FROM customer WHERE customer_id = ?";
	
	// INSERT
	public static int insert(Customer c){
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
			preparedStatement.setInt(1, c.getId());
			preparedStatement.setString(2, c.getName());
			preparedStatement.setString(3, c.getAddress());
			preparedStatement.setString(4, c.getNumber());
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
	//! READ ALL
	public static Object[][] readAll(){
		Object[][] finalResult = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        ResultSetMetaData md;
        int columnCount;
        //final Vector<String> columnNames = new Vector<String>();
        //final Vector<Object[]> cache = new Vector<Object[]>();
        
        System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}
		System.out.println("MySQL JDBC Driver Registered!");
        
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pt_homework", "Andras_homework", "homework");
			preparedStatement = connection.prepareStatement("SELECT * FROM customer;");
			//preparedStatement.setInt(1, id);
			preparedStatement.execute();
			
			result = preparedStatement.getResultSet();
			md = result.getMetaData();
            columnCount = md.getColumnCount();
            
            ArrayList<Object[]> result1 = new ArrayList<Object[]>();
            Object[] header = new Object[columnCount];
            for (int i=1; i <= columnCount; ++i){
                Object label = md.getColumnLabel(i);
                header[i-1] = label;
            }
            while (result.next()){
                Object[] str = new Object[columnCount];
                for (int i=1; i <= columnCount; ++i){
                    Object obj = result.getObject(i);
                    str[i-1] = obj;
                }
                result1.add(str);
            }
            int resultLength = result1.size()+1;
            finalResult = new Object[resultLength][columnCount];
            finalResult[0] = header;
            for(int i=1;i<resultLength;++i){
                Object[] row = result1.get(i-1);
                finalResult[i] = row;
            }
            
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			//p.setId(-2);
		}finally{
			try {
                result.close();
            } catch (Exception rse) {
            	System.out.println("Close result failed\n");
            	//p.setId(-2);                
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
            	System.out.println("Close prepStatement failed\n");
            	//p.setId(-2);                
            }
            try {
                connection.close();
            } catch (Exception cse) {
                System.out.println("Close connection failed\n");
                //p.setId(-2);
            }
		}
		
		return finalResult;
	}
	
	//read
	public static Customer read(int id){
		//customers = new ArrayList<Customer>();
		Customer p = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}
		System.out.println("MySQL JDBC Driver Registered!");
        
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pt_homework", "Andras_homework", "homework");
			preparedStatement = connection.prepareStatement(READ_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			
			result = preparedStatement.getResultSet();
			//! Is this ok??? will result read all the rows?
			p = new Customer();
			if (result.next() && result != null) {         
                p.setId(result.getInt(1));
                p.setName(result.getString(2));
                p.setAddress(result.getString(3));
                p.setNumber(result.getString(4));
                //customers.add(p);
            } else {
                p.setId(-1);
            }
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			p.setId(-2);
		}finally{
			try {
                result.close();
            } catch (Exception rse) {
            	System.out.println("Close result failed\n");
            	p.setId(-2);                
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
            	System.out.println("Close prepStatement failed\n");
            	p.setId(-2);                
            }
            try {
                connection.close();
            } catch (Exception cse) {
                System.out.println("Close connection failed\n");
                p.setId(-2);
            }
		}
 
        return p;	
	}
	public static Customer readName(String name){
		//customers = new ArrayList<Customer>();
		Customer c = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}
		System.out.println("MySQL JDBC Driver Registered!");
        
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pt_homework", "Andras_homework", "homework");
			preparedStatement = connection.prepareStatement("SELECT * FROM customer where customer_name = ?;");
			preparedStatement.setString(1, name);
			preparedStatement.execute();
			
			result = preparedStatement.getResultSet();
			//! Is this ok??? will result read all the rows?
			c = new Customer();
			if (result.next() && result != null) {         
                c.setId(result.getInt(1));
                c.setName(result.getString(2));
                c.setAddress(result.getString(3));
                c.setNumber(result.getString(4));
                //customers.add(p);
            } else {
                c.setId(-1);
            }
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			c.setId(-2);
		}finally{
			try {
                result.close();
            } catch (Exception rse) {
            	System.out.println("Close result failed\n");
            	c.setId(-2);                
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
            	System.out.println("Close prepStatement failed\n");
            	c.setId(-2);                
            }
            try {
                connection.close();
            } catch (Exception cse) {
                System.out.println("Close connection failed\n");
                c.setId(-2);
            }
		}
 
        return c;	
	}
	
	public static int readLastId(){
		//orders = new ArrayList<Order>();
		Customer c = new Customer();
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
			preparedStatement = connection.prepareStatement("SELECT * FROM customer;");
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
                c.setId(-2);              
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
            	System.out.println("Close prepStatement failed\n");
            	c.setId(-2);             
            }
            try {
                connection.close();
            } catch (Exception cse) {
                System.out.println("Close connection failed\n");
                c.setId(-2);
            }
		}
        return lastId;
	}
	
	//! UPDATE
	public static boolean update(Customer c){
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
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pt_homework", "Andras_homework", "homework");
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getAddress());
			preparedStatement.setString(3, c.getNumber());
			preparedStatement.setInt(4, c.getId());
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
