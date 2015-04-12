//@misstiphany

package source;

public class BusinessAccount  extends Account
{

	
	private double overdraftLimit = 10000;

	public BusinessAccount (ACCT_TYPE, acctBalance, maxWithdrawal, monthlyFee, freeTransactionCount, interestRate)
	{
		this.setBusAcctNum(); //Assigns an acct Num.
		this.busAcctBalance = 0.00;//initializes the account balance to 0
	}

	public BusinessAccount(double busAcctBalance)
	{
		this.setBusAcctNum();
		this.busAcctBalance = busAcctBalance;
	}

	public double getOverdraftLimit()
	{
		return overdraftLimit = 10000;
	}
    

            
    
}

