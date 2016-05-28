package banking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

public class Bank implements BankProc {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	HashMap<Person, Set<Account>> map = new HashMap<Person, Set<Account>>();

	@SuppressWarnings("unchecked")
	public Bank() {
		// this.map = new HashMap<Person, Set<Account>>();
		try {
			Person readClient;
			FileReader fReader = new FileReader("acount.txt");
			BufferedReader reader = new BufferedReader(fReader);
			String line = reader.readLine();
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, " ");
				Vector v = new Vector();
				while (st.hasMoreTokens()) {
					v.addElement(st.nextToken());
				}
				String pin = (String) v.elementAt(0);
				int codCont = new Integer((String) v.elementAt(1)).intValue();
				String cnp = (String) v.elementAt(2);
				String nume = (String) v.elementAt(3);
				String prenume = (String) v.elementAt(4);
				readClient = new Person(prenume, nume, cnp);
				double suma = new Double((String) v.elementAt(5)).intValue();
				int tip = new Integer((String) v.elementAt(6)).intValue();
				Set<Account> accSet = new HashSet<Account>();

				if (tip == 1) { // saving
					SavingAccount newAccount = new SavingAccount(codCont, readClient, suma, tip, pin);
					// System.out.println(newAccount.getAccID()+";
					// "+newAccount.getAccType()+"; "+
					// newAccount.getMoney()+"; "+ newAccount.getPIN());
					accSet.add(newAccount);
					map.put(readClient, accSet);
				}
				if (tip == 2) { // spending
					SpendingAccount newAccount = new SpendingAccount(codCont, readClient, suma, tip, pin);
					// System.out.println(newAccount.getAccID()+";
					// "+newAccount.getAccType()+"; "+
					// newAccount.getMoney()+"; "+ newAccount.getPIN());
					accSet.add(newAccount);
					map.put(readClient, accSet);
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException ex) {
			System.out.println("Error reading file acount.txt");
		}
		Person test = new Person("name","family","123456987");
	}

	public HashMap<Person, Set<Account>> getHashMap() {
		return this.map;
	}

	public int getSize() {
		return map.size();
	}

	// @Pre
	public boolean clientNotNull(Person p) {
		boolean ok = true;
		if (p.getCNP().equals(""))
			ok = false;
		if (p.getName().equals(""))
			ok = false;
		if (p.getfamilyName().equals(""))
			ok = false;
		return ok;
	}

	// @Pre
	public boolean accountNotNull(Account acc) {
		boolean ok = true;
		if (acc.getAccID() == 0)
			ok = false;
		if (acc.getMoney() == 0)
			ok = false;
		return ok;
	}

	// @Pre
	public boolean codNotNull(int cod) {
		boolean ok = true;
		if (cod == 0)
			ok = false;
		return ok;
	}

	// @Pre
	public boolean isIn(int cod) {
		return map.containsKey(cod);
	}

	public void depositMoney(double sum, String accountID, Person person) {
		assert clientNotNull(person) : "The Person object should not be null";
		Set<Account> accountSet = map.get(person);
		double initialSum = 1, finalSum = 2;
		boolean found = false;

		/*
		 * if (map.containsKey(p)) { accountSet = map.get(p);
		 */
		for (Account iterate : accountSet) {
			// assert accountNotNull(iterate);
			if (iterate.equals(accountID)/* accId == iterate.getAccId() */) {
				initialSum = iterate.getMoney();
				iterate.addMoney(sum);
				finalSum = iterate.getMoney();
				found = true;
			}
		}
		// }
		assert found : "The ID should belong to at least one of the person's accounts";
		assert (initialSum
				+ sum == finalSum) : "The final sum should be equal to the initial sum plus the deposited sum";
	}

	public boolean withdrawMoney(double sum, int accountID, Person person) {
		assert clientNotNull(person) : "The Person object should not be null";
		boolean found = false;
		if (map.containsKey(person)) {
			Set<Account> accountSet = map.get(person);
			double initialSum = 1, finalSum = 2;

			for (Account iterate : accountSet) {
				// assert accountNotNull(iterate);
				if (iterate.equals(accountID)) {
					initialSum = iterate.getMoney();
					iterate.withdrawMoney(sum);
					finalSum = iterate.getMoney();
					found = true;
				}
			}
			assert found : "The ID should belong to at least one of the person's accounts";
			assert (initialSum
					- sum == finalSum) : "The final sum should be equal to the initial sum plus the deposited sum";
		}
		if (found)
			return true;
		else
			return false;
	}

	public void addAccountForPerson(Person p, Account assocAcc) {
		assert isWellFormed();
		assert clientNotNull(p) : "The Person object should not be null";
		assert accountNotNull(assocAcc) : "The Account object should not be null";

		int size = 0;
		if (map.containsKey(p)) {
			size = map.get(p).size();
			/*
			 * Set<Account> accountSet; accountSet = map.get(assocAcc);
			 * accountSet.add(assocAcc);
			 */
			map.get(p).add(assocAcc);
		} else {
			Set<Account> acc = new HashSet<Account>();
			acc.add(assocAcc);
			map.put(p, acc);
		}
		assocAcc.addObserver(p);
		assert (size + 1 == map.get(p).size()) : "The Account set size should change";
		assert isWellFormed();
	}

	public void removeAccountForPerson(Person p, Account assocAcc) {
		assert isWellFormed();
		assert clientNotNull(p);
		assert accountNotNull(assocAcc);
		assert !map.isEmpty();

		int size = 0;
		if (map.containsKey(p)) {
			size = map.get(p).size();
			assocAcc.notifyObservers("Account " + assocAcc.accID + "removed");
			map.get(p).remove(assocAcc);
			assert (size - 1 == map.get(p).size()) : "size changed";
		}
		assert isWellFormed();
	}

	public Account getAccount(String pin) {
		assert isWellFormed();
		assert pin != null;
		assert !map.isEmpty();
		Set<?> set = this.getHashMap().entrySet();
		Iterator<?> i = set.iterator();
		Account a = null;
		Account cont = null;
		while (i.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry m = (Map.Entry) i.next();
			a = (Account) m.getValue();
			if (a.getPIN() == null ? pin == null : a.getPIN().equals(pin))
				cont = a;
		}
		return cont;
	}

	public Set<Account> getAccountsForPerson(Person p) {
		assert isWellFormed();
		assert clientNotNull(p) : "The person should not be null";
		Set<Account> accounts = new HashSet<Account>();
		HashMap<Person, Set<Account>> oldMap = map;
		if (map.containsKey(p)) {
			Set<Account> accountSet = map.get(p);

			for (Account account : accountSet) {
				accounts.add(account);
			}
		}
		assert oldMap.equals(map) : "The hash map should not change";
		return accounts;
	}

	public Set<Person> getPersons() {
		assert isWellFormed();
		HashMap<Person, Set<Account>> oldMap = map;
		Set<Person> persons = new HashSet<Person>();

		for (Object o : map.entrySet()) {
			@SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry) o;
			persons.add((Person) pair.getKey());
		}
		assert oldMap.equals(map) : "The hash map should not change";
		return persons;
	}

	public void addPerson(Person p) throws Exception {
		assert clientNotNull(p) : "The person should not be null";
		int oldSize = map.size();
		if (map.containsKey(p)) {
			throw new Exception("The customer is already in the hash map");
		} else {
			map.put(p, null);
		}
		/*
		 * for (Object o : map.entrySet()) {
		 * 
		 * @SuppressWarnings("rawtypes") Map.Entry pair = (Map.Entry) o; if
		 * (pair.getKey().equals(p)) throw new Exception(
		 * "The customer is already in the hash map"); } try { //
		 * Email.send(p.getEmailAddress(), "Holder contract successfully //
		 * created"); } catch (Exception e) { // MessageBox.display("Error",
		 * "The email address is not valid!"); p.setEmail(""); }
		 * addAccountForPerson(p, new SpendingAccount());
		 */
		assert (oldSize + 1 == map.size()) : "The size of the hash map should increase by one";
	}

	public void removePerson(Person p) {
		assert clientNotNull(p) : "The person should not be null";
		int oldSize = map.size();
		map.remove(p);
		assert (oldSize - 1 == map.size()) : "The size of the hash map should decrease by one";

	}

	public void deleteAccountForPerson(Person p, Account selectedItem) {
		assert (p != null && selectedItem != null) : "None of the parameters should be null";
		int oldSize = map.get(p).size();
		int newSize = 0;
		if (oldSize > 1) {
			map.get(p).remove(selectedItem);
			newSize = map.get(p).size();
		} else {
			map.remove(p);
		}
		assert (oldSize - 1 == map.get(p)
				.size()) : "The number of accounts belonging to the person should decrease by one";
	}

	/*
	 * public void removeAccount(int codCont) { assert isWellFormed(); assert
	 * codNotNull(codCont); assert !map.isEmpty(); assert isIn(codCont); int s1
	 * = map.size(); map.remove(codCont); assert
	 * map.containsKey(codCont)==false; int s2 = map.size(); assert s2==s1-1;
	 * assert isWellFormed(); }
	 */

	public Object[][] listAccounts() {
		int index = 0;
		Object data[][] = new Object[getSize()][6];
		Account a = null;
		Iterator i = map.entrySet().iterator();
		while (i.hasNext()) {
			Person p = null;
			Set<Account> accounts = new HashSet<Account>();
			@SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry) i.next();
			// a = (Account) pair.getValue()
			p = (Person) pair.getKey();
			if (map.containsKey(p)) {
				Set<Account> accountSet = map.get(p);
				for (Account account : accountSet) {
					accounts.add(account);
				}
				// }
				a = accounts.iterator().next();
				System.out.println(a.getAccID() + "; " + a.getAccType() + "; " + a.getMoney() + "; " + a.getPIN());
				data[index][0] = a.getAccID();
				data[index][1] = p.getName();
				data[index][2] = p.getfamilyName();
				if (a.getAccType() == 1)
					data[index][3] = "Saving Account";
				else if (a.getAccType() == 2)
					data[index][3] = "Spending Account";
				else
					System.out.println("err");
				data[index][4] = a.getMoney();
				data[index][5] = a.getPIN();
				index++;
			}
			// i.remove();
		}
		return data;
	}

	public Object[][] listClients() {
		int index = 0;
		Object data[][] = new Object[getSize()][3];
		Account a = null;
		Iterator i = map.entrySet().iterator();
		while (i.hasNext()) {
			Person p = null;
			@SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry) i.next();
			p = (Person) pair.getKey();
			if (map.containsKey(p)) {
				data[index][0] = p.getName();
				data[index][1] = p.getfamilyName();
				data[index][2] = p.getCNP();
				index++;
			}
		}
		return data;
	}

	public Object[][] listClientAccount(String pin) {
		Account a = this.getAccount(pin);
		Object date[][] = new Object[1][3];
		date[0][0] = a.getPIN();
		date[0][1] = a.getMoney();
		return date;
	}

	public void saveAllAccounts() {
		assert isWellFormed();
		System.out.println("Save all Started");
		try {
			FileWriter fWriter = new FileWriter("acount.txt", false);
			BufferedWriter writer = new BufferedWriter(fWriter);
			String line = "";

			Account a = null;
			Iterator i = map.entrySet().iterator();
			while (i.hasNext()) {
				Person p = null;
				Set<Account> accounts = new HashSet<Account>();
				@SuppressWarnings("rawtypes")
				Map.Entry pair = (Map.Entry) i.next();
				// a = (Account) pair.getValue()
				p = (Person) pair.getKey();
				if (map.containsKey(p)) {
					Set<Account> accountSet = map.get(p);
					for (Account account : accountSet) {
						accounts.add(account);
					}
					// }
					a = accounts.iterator().next();
					int accId = a.getAccID();
					String cnp = p.getCNP();
					String name = p.getName();
					String familyName = p.getfamilyName();
					double money = a.getMoney();
					int type = a.getAccType();
					String pin = a.getPIN();
					System.out.println(a.getAccID() + "; " + a.getAccType() + "; " + a.getMoney() + "; " + a.getPIN());

					System.out.println(pin + " " + accId + " " + cnp + " " + name + " " + familyName + " " + money + " "
							+ " " + type);
					line = pin + " " + accId + " " + cnp + " " + name + " " + familyName + " " + money + " " + " "
							+ type;
					writer.write(line);
					writer.newLine();
				}
			}
			writer.close();
		} catch (IOException ex) {
			System.out.println("Error writing file acount.txt");
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean isWellFormed() {
		Account acc = null;
		for (Object o : map.entrySet()) {
			Map.Entry pair = (Map.Entry) o;
			Set set = (Set) pair.getValue();
			acc = (Account) pair.getValue();
			if (set.size() == 0 || acc.getClient() == null)
				return false;
			else if (acc.getAccType() != 1 && acc.getAccType() != 2)
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Bank{" + "hashMap=" + map + '}';
	}

}
