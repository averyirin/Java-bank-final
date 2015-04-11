package source;

public enum CustomerType
{
	REGULAR(1),
	STUDENT(2),
	SENIOR(3),
	BUSINESS(4);
	
	private int type;
		
	private CustomerType(int type)
	{
		this.type = type;
	}
		
	public int getCustomerType ()
	{
		return this.type;
	}
}
