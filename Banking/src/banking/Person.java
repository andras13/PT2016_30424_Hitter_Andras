package banking;

import java.util.Observable;
import java.util.Observer;

public class Person implements Observer {

	private String name;
	private String familyName;
	private String cnp;

	public Person(String name, String familyName, String cnp) {
		super();
		this.name = name;
		this.familyName = familyName;
		this.cnp = cnp;
	}

	public Person(Person newPerson) {
		this.cnp = newPerson.getCNP();
		this.name = newPerson.getName();
		this.familyName = newPerson.getfamilyName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setfamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getfamilyName() {
		return this.familyName;
	}

	public void setCNP(String c) {
		this.cnp = c;
	}

	public String getCNP() {
		return this.cnp;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Person person = (Person) o;

		if (!cnp.equals(person.cnp))
			return false;
		return name.equals(person.name);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + cnp.hashCode();
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Person ID : " + cnp + ", Person's name: " + name + "]";
	}

}
