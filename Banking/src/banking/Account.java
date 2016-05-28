package banking;

import java.io.Serializable;
import java.util.Observable;

public abstract class Account extends Observable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8056961604887397002L;

	protected int accID;
	protected Person client;
	protected double money;
	protected int accType; //1 = saving 2 = spending
	protected String PIN;
	
	public Account(int ID, Person client, double money, int type, String pin)
	{
		this.accID = ID;
		this.client = new Person(client);
		this.money = money;
		this.accType = type;
		this.PIN=pin;
	}

	/*
	 * addMoney
	 */
	public abstract void addMoney(double sum);
	public abstract boolean withdrawMoney(double sum);

	public abstract int getAccID();

	public abstract banking.Person getClient();

	public abstract double getMoney();

	public abstract int getAccType();

	public abstract String getPIN();

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if(accID == account.accID)
        {
        	return true;
        }
        else
        	return false;
        //return accID.equals(account.accID);
	}

	@Override
	public int hashCode() {
	//	return accID.hashCode();
		return 1;  
	}

	@Override
	public abstract String toString();
	
}
