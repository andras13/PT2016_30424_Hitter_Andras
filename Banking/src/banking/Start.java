package banking;

public class Start {

	public static void main(String[] args) {
		GUI go = new GUI();
		go.setVisible(true);
		System.out.println("START\n");

		Person test = new Person("name","family","123456987");
		Account testAcc = new SavingAccount(4321, test, 300.0, 1, "9999");
		testAcc.addMoney(150.0);
		System.out.println("Added money"+testAcc.money);
		testAcc.withdrawMoney(35.5);
		System.out.println("Withdraw money"+testAcc.money);
		
		Account testAcc2 = new SpendingAccount(4322, test, 1000.0, 1, "9899");
		testAcc2.addMoney(150.0);
		System.out.println("Added money"+testAcc2.money);
		testAcc2.withdrawMoney(35.5);
		System.out.println("Withdraw money"+testAcc2.money);
	}

}
