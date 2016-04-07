package ordermanagement.DAO;

import ordermanagement.model.*;
import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
	//private List<Product> products;
	/** The query for insert. */
	private static final String CREATE_QUERY = "INSERT INTO product (product_id, product_name,product_type,product_price,product_quantity) "
			+ "VALUES (?,?,?,?,?);";
	/** The query for read. */
	private static final String READ_QUERY = "SELECT * FROM product WHERE product_id = ?;";
	/** The query for update. */
	private static final String UPDATE_QUERY = "UPDATE product SET product_name=?, product_type=?,product_price=?,product_quantity=?"
			+ " WHERE product_id = ?;";
	/** The query for delete. */
	private static final String DELETE_QUERY = "DELETE FROM product WHERE product_id = ?";
	
	//! INSERT
	public static int insert(Product p){
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
			preparedStatement.setInt(1, p.getId());
			preparedStatement.setString(2, p.getName());
			preparedStatement.setString(3, p.getType());
			preparedStatement.setFloat(4, p.getPrice());
			preparedStatement.setInt(5, p.getQuantity());
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
			preparedStatement = connection.prepareStatement("SELECT * FROM product;");
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
            
            /*columnNames.clear();
            for(int col = columnCount; col >0; col --){
            	columnNames.add(md.getColumnName(col));
            }
            // Cache
            
			while(result.next()){
				Object rowData[] = new Object[columnCount];
				for(int col = columnCount; col >0; col--){
					rowData[col-1] = result.getObject(col);
				}
				cache.add(rowData);
				 	//p.setId(result.getInt(1));
	                //p.setName(result.getString(2));
	                //p.setAddress(result.getString(3));
	                //p.setNumber(result.getString(4));
	                //customers.add(p);
			}*/
			//fireTableStructureChanged();
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
	
	//! READ
	public static Product read(int id){
		//products = new ArrayList<Product>();
		Product p = new Product();
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
			connection = DriverManager.getConnection("jdbc:mysql://localhost/pt_homework", "Andras_homework", "homework");
			preparedStatement = connection.prepareStatement(READ_QUERY);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			
			result = preparedStatement.getResultSet();
			
			//! Is this ok??? will result read all the rows?
			if (result.next() && result != null) {
				System.out.println("Read..");
                p = new Product();
                p.setId(result.getInt(1));
                p.setName(result.getString(2));
                p.setType(result.getString(3));
                p.setPrice(result.getFloat(4));
                p.setQuantity(result.getInt(5));
                //products.add(p);
            } else {
                p.setId(-1);
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
	//! UPDATE
	public static boolean update(Product p){
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
			//preparedStatement.setInt(1, p.getId());
			preparedStatement.setString(1, p.getName());
			preparedStatement.setString(2, p.getType());
			preparedStatement.setFloat(3, p.getPrice());
			preparedStatement.setInt(4, p.getQuantity());
			preparedStatement.setInt(5, p.getId());
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
