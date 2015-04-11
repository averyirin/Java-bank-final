package source;

public class Account_Support
{
	private static int acctNumber = 0;

	protected static int getAcctNumber()
	{
		return ++acctNumber;
	}
}

