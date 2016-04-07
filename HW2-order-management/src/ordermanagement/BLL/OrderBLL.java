package ordermanagement.BLL;


import ordermanagement.DAO.OrderDAO;
import ordermanagement.model.Order;

public class OrderBLL {

	public static void insert(Order o) {
		Order aux = new Order();
		aux = OrderDAO.read(o.getOrderID());
		System.out.println("ORDERBLL aux id" + o.getOrderID());
		if (aux.getOrderID() != -2) {
			if (aux.getOrderID() == -1) {
				if (o.getOrderID() > 0 && o.getCustomerId() > 0 && o.getProductId() > 0 && o.getQuantity() > 0
						&& o.getFull_price() > 0) {
					if (o.getDate().length() >= 8) {
						OrderDAO.insert(o);
					} else {
						System.out.println("Date format: xx.xx.xxxx or x.x.xxxx");
					}
				} else {
					System.out.println("Wrong id(s)!");
				}

			} else {
				System.out.println("There alread exist an Order with this id");
			}
		} else {
			System.out.println("Connection error!");
		}
	}

	public Order read(int id) {
		Order aux = new Order();
		aux = OrderDAO.read(id);
		if (aux.getOrderID() != -2) {
			if (aux.getOrderID() != -1) {
				return aux;
			} else {
				System.out.println("There is no element with this id");
			}
		} else {
			System.out.println("Connection error");
		}
		return null;
	}

	public static int readLastId(){
        int lastId = OrderDAO.readLastId();   
        if(lastId > 0 ){
        	return lastId;
        }
        else return 2;
	}
	
	public void update(Order o ) {
		Order aux = new Order();
		aux = OrderDAO.read(o.getOrderID());
		if (aux.getOrderID() != -2) {
			if (aux.getOrderID() != -1) {
				if (o.getOrderID() >= 0 && o.getCustomerId() >= 0 && o.getProductId() >= 0 && o.getQuantity() > 0
						&& o.getFull_price() > 0) {
					if (o.getDate().length() >= 8 && o.getDate().length() <= 10) {
							OrderDAO.update(o);	
					} else {
						System.out.println("Date format xx.xx.xxxx");
					}
				} else {
					System.out.println("Wrong id(s)!");
				}

			} else {
				System.out.println("There wasn't anything to update! You should use INSERT!");
			}
		}
		else
		{
			System.out.println("Connection error");
		}
	}

	public void delete(int id) {
		Order aux = new Order();
		aux = OrderDAO.read(id);
		if (aux.getOrderID() != -2) {
			if (aux.getOrderID() != -1) {
				OrderDAO.delete(id);
			} else {
				System.out.println("There is no element with this ID ");
			}
		}
		else
			System.out.println("Connection error");
	}
}
