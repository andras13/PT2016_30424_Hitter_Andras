package banking;

public class SpendingAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9014366474628139324L;

	public SpendingAccount(int ID, Person client, double money, int type, String pin) {
		super(ID, client, money, type, pin);
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
		// super.setMoney(super.getMoney() + sum);
		super.money += sum;
		this.setChanged();
		this.notifyObservers(sum + "$ were added to your spending account.");
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
