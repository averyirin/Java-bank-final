package source;

public enum AccountType
{
	CHEQUING(1),
	SAVINGS(2),
	CREDIT(3),
	BUSINESS(4);
	
	private int type;
		
	private AccountType(int type)
	{
		this.type = type;
	}
		
	public int getAccountType ()
	{
		return this.type;
	}
}
