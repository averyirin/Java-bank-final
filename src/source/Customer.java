package source;

import java.util.ArrayList;

public class Customer
{
	private String firstName;
	private String lastName;
	private String sin;
	private String address;
	private CustomerType custType;
	private final static int ACCT_LIMIT = 2;
	private static Account acct_temp; // Account class wrapper
	private ArrayList<Account> acctList; // Collection for holding account records
	private int acctCount = 0; // Variable to hold the number of accounts our customer has (could be acctList.length as well, but I think its better this way)

	/**
	 * Constructor
	 * @param firstName
	 * @param lastName
	 * @param sin
	 * @param address
	 */
	public Customer(String firstName, String lastName, String sin, String address, int custType)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.sin = sin;
		this.address = address;
		setCustType(custType);
		this.acctList = new ArrayList<Account>();
	}

	/**
	 * Creates an account if account limit is not exceeded and adds it to account list
	 */
        public CustomerType getCustType(){
            return this.custType;
        }
	public boolean createAcct(Account acct)
	{
		
		if (acctCount < ACCT_LIMIT)
		{
		
			this.acctList.add(acct);
			acctCount += 1;
			return true;	
                	
		}else{
                    return false;
                }
    
	}
	
	// MUTATORS
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public void setSin(String sin)
	{
		this.sin = sin;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
	
	private void setCustType(int type)
	{
		if(type == 1)
			this.custType = CustomerType.REGULAR;
		else if(type == 2)
			this.custType = CustomerType.STUDENT;
		else if(type == 3)
			this.custType = CustomerType.SENIOR;
		else if(type == 4)
			this.custType = CustomerType.BUSINESS;
	}

	// ACCESSORS
	public String getFirstName()
	{
		return this.firstName;
	}

	public String getLastName()
	{
		return this.lastName;
	}

	public String getSin()
	{
		return this.sin;
	}

	public String getAddress()
	{
		return this.address;
	}
	
	// this method will return an Account object based on the acctNumber assigned by the Account_Support class 
	public Account getAccount(int acctNumber)
	{
		int index = -1; // Variable to hold the index of the account we look for

		// This loop searches for the account with a given account number
		for (Account account : acctList)
		{
			if (account.getAcctNumber() == acctNumber)
				index = this.acctList.indexOf(account); // Here, we store the index of the account we are looking for
		}

		return this.acctList.get(index); // And use that index to extract our account from the arraylist
	}

	public ArrayList<Account> getAcctList()
	{
		return this.acctList;
	}

	@Override
	public String toString()
	{
		return String.format("Name: %20s | Surname: %20s | SIN: %20s | Address: %20s",
				this.getFirstName(), this.getLastName(), this.getSin(), this.getAddress());
	}
}

