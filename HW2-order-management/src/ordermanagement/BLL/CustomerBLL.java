package ordermanagement.BLL;

import ordermanagement.DAO.CustomerDAO;
import ordermanagement.model.Customer;

public class CustomerBLL {

	/*
	 * private CustomerDAL customerDAL;
	 * 
	 * public CustomerBLL() { this.customerDAL = new CustomerDAL(); }
	 */

	public static void insert(Customer c) {
		Customer aux = new Customer();
		aux = CustomerDAO.read(c.getId());
		if (aux.getId() != -2) {
			if (aux.getId() == -1) {
				if (c.getId() >= 0) {
					if (c.getNumber().length() >= 9) {
						if (c.getAddress().length() != 0 && c.getName().length() != 0) {
							CustomerDAO.insert(c);
						} else {
							System.out.println("Address or Name missing!");
						}
					} else {
						System.out.println("Too short phone number!");
					}
				} else {
					System.out.println("Wrong id!");
				}

			} else {
				System.out.println("There alread exist a Customer with this id");
			}
		} else {
			System.out.println("Connection error!");
		}
	}

	public static Customer read(int id) throws Exception{
		Customer aux = new Customer();
		aux = CustomerDAO.read(id);
		if (aux.getId() != -2) {
			if (aux.getId() != -1) {
				return aux;
			} else {
				System.out.println("There is no element with this id");
				throw new Exception("There is no element with this id!");
			}
		} else {
			System.out.println("Connection error!");
		}
		return null;
	}
	public static Customer readName(String name) throws Exception{
		Customer aux = new Customer();
		aux = CustomerDAO.readName(name);
		if (aux.getId() != -2) {
			if (aux.getId() != -1) {
				return aux;
			} else {
				System.out.println("There is no element with this id");
				throw new Exception("There is no element with this id!");
			}
		} else {
			System.out.println("Connection error!");
		}
		return null;
	}
	
	public static int readLastId(){
        int lastId = CustomerDAO.readLastId();
        return lastId;
	}
//NOT READY
	public static Object[][] readAll() {
		//Vector<Object[]> cache = new Vector<Object[]>();
		//cache = CustomerDAO.readAll();
		return CustomerDAO.readAll();
	}
	
	public static void update(Customer c) throws Exception{
		Customer aux = new Customer();
		aux = CustomerDAO.read(c.getId());
		if (aux.getId() != -2) {
			if (aux.getId() != -1) {
				if (c.getId() >= 0) {
					if (c.getNumber().length() >= 9) {
						if (c.getAddress().length() != 0 && c.getName().length() != 0) {
							CustomerDAO.update(c);
						} else {
							System.out.println("Address or Name missing!");
							throw new Exception("Address or Name missing!");
						}
					} else {
						System.out.println("Too short phone number!");
						throw new Exception("Too short phone number!");
					}
				} else {
					System.out.println("Wrong id!");
					throw new Exception("Wrong id!");
				}

			} else {
				System.out.println("There wasn't anything to update! You should use INSERT!");
				throw new Exception("There wasn't anything to update! You should use INSERT!");
			}
		} else {
			System.out.println("Connection error");
			throw new Exception("Connection error");
		}
	}

	public static void delete(int id) throws Exception{
		Customer aux = new Customer();
		aux = CustomerDAO.read(id);
		if (aux.getId() != -2) {
			if (aux.getId() != -1) {
				CustomerDAO.delete(id);
			} else {
				System.out.println("There is no element with this ID ");
				throw new Exception("There is no element with this id!");
			}
		}
		else
			System.out.println("Connection error");
	}
}
