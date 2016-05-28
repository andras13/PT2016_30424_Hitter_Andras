package banking;

import java.util.Set;

public interface BankProc {

	/**
     * @Precondition person != null, account != null
     * @Postcondition person.account size ++
     * @param person - the holder of the account to be
     * @param account - the account to be added
     */
    void addAccountForPerson(Person person, Account account);

    /**
     * @Precondition accountID checkDigit must be correct , person != null
     * @Postcondition account.money += sum
     * @Invariant isWellFormed
     * @param sum - the sum to be added
     * @param accountID - the id of the account to which the sum is added
     * @param person - the person who owns the account
     */
    void depositMoney(double sum, String accountID, Person person);

    /**
     * @Precondition accountID checkDigit must be correct , person != null
     * @Postcondition account.money -= sum
     * @Invariant isWellFormed
     * @param sum - the sum to be withdrawn
     * @param accountID - the id of the account from which the money is withdrawn
     * @param person - the person who owns the account
     * @return 
     */
    boolean withdrawMoney(double sum, int accountID, Person person);

    /**
     * @Precondition person != null
     * @Postcondition the hash map remains the same
     * @param person the person for which the accounts are returned
     * @return an observable list of the accounts of the person
     */
    Set<Account> getAccountsForPerson(Person person);

    /**
     * @Postcondition the hash map remains the same
     * @return an observable list of all account holders
     */
    Set<Person> getPersons();

    /**
     * @Precondition person!= null
     * @Postcondition hashMap.size++
     * @param person the person to be added
     * @throws Exception
     */
    void addPerson(Person person) throws Exception;

    /**
     * @Precondition person!= null
     * @Postcondition hashMap.size--
     * @param person the person to be added
     */
    void removePerson(Person person);

    /**
     * @Precondition parameters != null
     * @Postcondition the number of accounts belonging to the person should decrease by one
     * @param person the person to whom the account belongs
     * @param selectedItem the account to be deleted
     */
    void deleteAccountForPerson(Person person, Account selectedItem);
}
