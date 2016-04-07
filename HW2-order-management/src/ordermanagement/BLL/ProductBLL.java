package ordermanagement.BLL;

import ordermanagement.DAO.ProductDAO;
import ordermanagement.model.Product;

public class ProductBLL {

	public static void insert(Product p){
		Product aux = new Product();
		aux = ProductDAO.read(p.getId());
		if (aux.getId() != -2) {
			if (aux.getId() == -1) {
				if (p.getId() >= 0 && p.getPrice() > 0 && p.getQuantity() >= 0) {
					if (p.getName().length() >= 0 && p.getType().length() > 0) {
						ProductDAO.insert(p);
					} else {
						System.out.println("Name or Type missing");
						//throw new Exception("Name or Type missing!");
					}
				} else {
					System.out.println("Wrong id, quantity or price!");
					//throw new Exception("Wrong id, quantity or price!");
				}
			} else {
				System.out.println("There alread exist a Product with this id!");
				//throw new Exception("There alread exist a Product with this id!");
			}
		} else {
			System.out.println("Connection error!");
		}
	}

	public static Product read(int id) throws Exception{
		Product aux = new Product();
		aux = ProductDAO.read(id);
		if (aux.getId() != -2) {
			if (aux.getId() != -1) {
				return aux;
			} else {
				System.out.println("There is no element with this id!");
				throw new Exception("There is no element with this id!");
			}
		} else {
			System.out.println("Connection error!");
		}
		return null;
	}
	public static Object[][] readAll() {
		//Vector<Object[]> cache = new Vector<Object[]>();
		//cache = CustomerDAO.readAll();
		return ProductDAO.readAll();
	}

	public static void update(Product p) throws Exception{
		Product aux = new Product();
		aux = ProductDAO.read(p.getId());
		if (aux.getId() != -2) {
			if (aux.getId() != -1) {
				if (p.getId() >= 0 && p.getPrice() > 0 && p.getQuantity() >= 0) {
					if (p.getName().length() >= 0 && p.getType().length() > 0) {	
							ProductDAO.update(p);
					} else {
						System.out.println("Missing Name or Type");
						throw new Exception("Missing Name or Type");
					}
				} else {
					System.out.println("Wrong id, price or quantity!");
					throw new Exception("Wrong id, price or quantity!");
				}

			} else {
				System.out.println("There wasn't anything to update! You should use INSERT!");
				throw new Exception("There wasn't anything to update! You should use INSERT!");
			}
		} else {
			System.out.println("Connection error");
		}
	}

	public static void delete(int id) throws Exception{
		Product aux = new Product();
		aux = ProductDAO.read(id);
		if (aux.getId() != -2) {
			if (aux.getId() != -1) {
				ProductDAO.delete(id);
			} else {
				System.out.println("There is no element with this ID! ");
				throw new Exception("There is no element with this ID! ");
			}
		} else
			System.out.println("Connection error");
	}
}
