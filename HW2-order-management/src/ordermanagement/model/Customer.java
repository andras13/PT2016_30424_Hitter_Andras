package ordermanagement.model;

public class Customer {

	private int id;
	private String name;
	private String number;
	private String address;
	
	public Customer() {
		super();
		this.id = 0;
		this.name = null;
		this.address = null;
		this.number = null;
	}
	
	public Customer(int id, String name, String number, String address) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.address = address;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
