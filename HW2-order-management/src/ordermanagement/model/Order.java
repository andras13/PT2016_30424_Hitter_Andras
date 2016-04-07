package ordermanagement.model;

public class Order {

	private int orderID;
	private int customerId;
	private int productId;
	//private String customerName;
	//private String productName;
	private int quantity;
	private String date;
	private float Full_price;
	
	public Order() {
		super();
		this.orderID = 0;
		this.customerId = 0;
		this.productId = 0;
		//this.customerName = customerName;
		//this.productName = productName;
		this.quantity = 0;
		this.date = null;
		this.Full_price = 0;
	}
	
	public Order(int orderID, int customerId, int productId,/* String customerName, String productName,*/ int quantity, String date,
			float full_price) {
		super();
		this.orderID = orderID;
		this.customerId = customerId;
		this.productId = productId;
		//this.customerName = customerName;
		//this.productName = productName;
		this.quantity = quantity;
		this.date = date;
		this.Full_price = full_price;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/*public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}*/
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getFull_price() {
		return Full_price;
	}
	public void setFull_price(float full_price) {
		Full_price = full_price;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	
	
}
