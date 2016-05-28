package banking;

import banking.Person;

public class SavingAccount extends Account {

	/**
	 * Constructor Account
	 * 
	 * @param accID
	 * @param client
	 * @param suma
	 * @param tip
	 * @param pin
	 */
	private static final long serialVersionUID = 3908160117291235525L;

	public SavingAccount(int accID, Person client, double money, int type, String pin) {
		super(accID, client, money, type, pin);
	}

	@Override
	public int getAccID() {
		return this.accID;

	}

	@Override
	public Person getClient() {
		return this.client;

	}

	@Override
	public int getAccType() {
		return this.accType;

	}

	@Override
	public double getMoney() {
		return this.money;

	}

	@Override
	public String getPIN() {
		return this.PIN;

	}

	@Override
	public void addMoney(double sum) {
		this.money = this.money + sum;
		// super.setMoney(super.getMoney() + sum);
		this.setChanged();
		this.notifyObservers(sum + "$ were added to your saving account.");
	}

	@Override
	public boolean withdrawMoney(double sum) {
		if (this.money >= sum) {
			this.money = this.money - sum;
			this.setChanged();
			this.notifyObservers(sum + "$ were added to your saving account.");
			return true;
		} else {
			this.notifyObservers(sum + "$ can't be withdrawed from your saving account.");
			return false;
		}
	}

	@Override
	public String toString() {
		return "Account{" + "accountID=" + accID + ", money=" + money + '}';
	}
}
