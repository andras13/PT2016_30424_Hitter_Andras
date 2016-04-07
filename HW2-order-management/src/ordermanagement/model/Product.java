package ordermanagement.model;

public class Product {

	private int id;
	private int quantity;
	private String name;
	private String type;
	private float price;
	
	public Product() {
		super();
		this.id = 0;
		this.name = null;
		this.type = null;
		this.price = 0;
		this.quantity = 0;
	}
	
	public Product(int id,int quantity, String name, String type, float price, int quantity1) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.quantity = quantity1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
