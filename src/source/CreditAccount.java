package source;

public class CreditAccount extends Account
{
	private static final double TRANSFER_FEE = 25.0;
	private double creditAmount;
	private double balanceOwing; // What our customer owes us
	
	public CreditAccount(double maxWithdrawal, double monthlyFee, double interestRate, int custType)
	{
		super(3, maxWithdrawal, monthlyFee, interestRate);
		
		if(custType == 1)
			this.creditAmount = 5000.00;
		else if(custType == 2)
			this.creditAmount = 1000.00;
		else if(custType == 3)
			this.creditAmount = 3000.00;
		else if(custType == 4)
			this.creditAmount = 10000.00;
		
		this.balanceOwing = 0.00;
	}
}
