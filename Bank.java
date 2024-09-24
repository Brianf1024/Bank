import java.util.*;


 // Enumeration that defines the currency
enum Currency {
	USD,MXP,INR
}

// Data type that defines the transaction and currency
class Transaction {
	private double amount;
	private Currency currency;
	
	public Transaction(double amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public Currency getCurrency() {
		return currency;
	}
}


class Account {

	private double balance = 0.0;
	private AccountHolder holder;
	private Currency currency;

	/************************
	 * administrative methods
	 ************************/

	public Account(double balance, AccountHolder holder, Currency currency) {
		this.balance = balance;
		this.holder = holder;
		this.currency = currency;

	}

	public double getBalance() { return balance; }
	public AccountHolder getHolder() { return holder; }
	public Currency getcurrency() { return currency; } // An endpoint that accesses the account's currency and retrieves it


	// probably want to make setters private
	public void setBalance(double amt) {
		balance = amt;
	}

	public void setHolder(AccountHolder holder) {
		this.holder = holder;
	}

	/**********************
	 * semantic methods
	 **********************/

	public void deposit(double amt) {
		balance += amt;					// Adds amount to balance

	}

	public void withdraw(double amt)  {
		balance -= amt;					//  Subtracts amount from balance
	}


}

// Inheritance: AccountHolder is the parent class of IndividualHolder and CorporateHolder
abstract class AccountHolder {

	protected int ID;
	protected String address;
	protected Set<Account> accounts;
	
	// ID and Address are attributes of the AccountHolder
	public AccountHolder(int ID, String address) {
		this.ID = ID;
		this.address = address;
		accounts = new HashSet<Account>();
	}

	public int getID() { return ID; }
	public String getAddress() { return address; }

	public void setID(int id) {
		this.ID = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// delegators for accounts
	public void add(Account account) {
		accounts.add(account);
	}

	public Iterator<Account> iterator() {
		return accounts.iterator();
	}
	
	// Derived attribute that calculates the sum of balances across all accounts from the AccountHolder
	public double getTotalBalance() {
		double total = 0;
		for (Account account: accounts) {
			total += account.getBalance();
		}
		return total;
	}
	
}

class IndividualHolder extends AccountHolder {

	private String name;
	private String SSN;

	public IndividualHolder(int ID, String address, String name, String SSN) {
		super(ID, address);
		this.name = name;
		this.SSN = SSN;
	}

	public String getName() { return name; }
	public String getSSN() { return SSN; }

	public void setName(String name) {
		this.name = name;
	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}


}


class CorporateHolder extends AccountHolder {

	private String contact;

	public CorporateHolder(int ID, String address, String contact) {
		super(ID, address);
		this.contact = contact;
	}

	public String getContact() { return contact; }
	public void setContact(String contact) {
		this.contact = contact;
	}



}


public class Bank {
   public static void main(String[] args) {

	     // Creating 5 IndividualHolders
       IndividualHolder holder1 = new IndividualHolder(1, "123 Main St", "John Johnson", "111-11-1111");
       IndividualHolder holder2 = new IndividualHolder(2, "456 Elm St", "Jane Dillon", "222-22-2222");
       IndividualHolder holder3 = new IndividualHolder(3, "789 Oak St", "Sam Smith", "333-33-3333");
       IndividualHolder holder4 = new IndividualHolder(4, "101 Maple St", "Alice Wonderland", "444-44-4444");
       IndividualHolder holder5 = new IndividualHolder(5, "202 Birch St", "Bob Brown", "555-55-5555");
       
       // Creating 5 Accounts for IndividualHolders
       Account account1 = new Account(1000.0, holder1, Currency.USD);
       Account account2 = new Account(2000.0, holder2, Currency.MXP);
       Account account3 = new Account(1500.0, holder3, Currency.INR);
       Account account4 = new Account(3000.0, holder4, Currency.USD);
       Account account5 = new Account(2500.0, holder5, Currency.MXP);
       
       // Printing attributes of 5 IndividualHolders and properties
       System.out.println("\nIndividual Holders:");
       System.out.println(holder1.getName() + " | Address: " + holder1.getAddress() + " | SSN: " + holder1.getSSN());
       System.out.println(holder2.getName() + " | Address: " + holder2.getAddress() + " | SSN: " + holder2.getSSN());
       System.out.println(holder3.getName() + " | Address: " + holder3.getAddress() + " | SSN: " + holder3.getSSN());
       System.out.println(holder4.getName() + " | Address: " + holder4.getAddress() + " | SSN: " + holder4.getSSN());
       System.out.println(holder5.getName() + " | Address: " + holder5.getAddress() + " | SSN: " + holder5.getSSN()+ "\n");
       
       System.out.println("Account Holder: " + holder1.getName() + " | Balance: " + account1.getBalance() + " " + account1.getcurrency());
       System.out.println("Account Holder: " + holder2.getName() + " | Balance: " + account2.getBalance() + " " + account2.getcurrency());
       System.out.println("Account Holder: " + holder3.getName() + " | Balance: " + account3.getBalance() + " " + account3.getcurrency());
       System.out.println("Account Holder: " + holder4.getName() + " | Balance: " + account4.getBalance() + " " + account4.getcurrency());
       System.out.println("Account Holder: " + holder5.getName() + " | Balance: " + account5.getBalance() + " " + account5.getcurrency() + "\n");
       
       // Creating 5 CorporateHolders
       CorporateHolder corp1 = new CorporateHolder(6, "111 Corporation Blvd", "Intel");
       CorporateHolder corp2 = new CorporateHolder(7, "222 Enterprise St", "TSMC");
       CorporateHolder corp3 = new CorporateHolder(8, "333 Business Rd", "Caltrol");
       CorporateHolder corp4 = new CorporateHolder(9, "444 Commerce Dr", "PSNS");
       CorporateHolder corp5 = new CorporateHolder(10, "555 Industry Ln", "CAT");
	

       // Creating 5 Accounts for CorporateHolders
       Account corpAccount1 = new Account(50000.0, corp1, Currency.USD);
       Account corpAccount2 = new Account(60000.0, corp2, Currency.MXP);
       Account corpAccount3 = new Account(55000.0, corp3, Currency.INR);
       Account corpAccount4 = new Account(75000.0, corp4, Currency.USD);
       Account corpAccount5 = new Account(80000.0, corp5, Currency.MXP);
       
       // Printing Corporate Accounts
       System.out.println("Corporate Account Holder: " + corp1.getContact() + " | Balance: " + corpAccount1.getBalance() + " " + corpAccount1.getcurrency());
       System.out.println("Corporate Account Holder: " + corp2.getContact() + " | Balance: " + corpAccount2.getBalance() + " " + corpAccount2.getcurrency());
       System.out.println("Corporate Account Holder: " + corp3.getContact() + " | Balance: " + corpAccount3.getBalance() + " " + corpAccount3.getcurrency());
       System.out.println("Corporate Account Holder: " + corp4.getContact() + " | Balance: " + corpAccount4.getBalance() + " " + corpAccount4.getcurrency());
       System.out.println("Corporate Account Holder: " + corp5.getContact() + " | Balance: " + corpAccount5.getBalance() + " " + corpAccount5.getcurrency());


       // Adding Accounts to Holders
       holder1.add(account1);
       holder2.add(account2);
       holder3.add(account3);
       holder4.add(account4);
       holder5.add(account5);

       corp1.add(corpAccount1);
       corp2.add(corpAccount2);
       corp3.add(corpAccount3);
       corp4.add(corpAccount4);
       corp5.add(corpAccount5);

       // Printing attributes of 5 CorporateHolders
       System.out.println("\nCorporate Holders:");
       System.out.println(corp1.getContact() + " | Address: " + corp2.getAddress());
       System.out.println(corp3.getContact() + " | Address: " + corp3.getAddress());
       System.out.println(corp4.getContact() + " | Address: " + corp4.getAddress());
       System.out.println(corp5.getContact() + " | Address: " + corp5.getAddress());
       
   }
}
   

