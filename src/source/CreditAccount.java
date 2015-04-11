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
			this.creditAmount = 5000.0;
		else if(custType == 2)
			this.creditAmount = 1000.0;
		else if(custType == 3)
			this.creditAmount = 3000.0;
		else if(custType == 4)
			this.creditAmount = 10000.0;
		
		this.updateAcctBalance(creditAmount);
		this.balanceOwing = 0.0;
	}
	
	// ACCESSORS
	public double getCreditAmount()
	{
		return this.creditAmount;
	}
	
	public double getBalanceOwing()
	{
		return this.balanceOwing;
	}
	// MUTATORS
	public double calculateBalanceOwing()
	{
		return this.balanceOwing = this.getCreditAmount() - this.getAcctBalance();
	}
	
	@Override
	public boolean withdraw(double amount)
	{
		if(super.withdraw(amount))
		{
			
			return true;
		}
		
	}
	
	@Override
	public String toString()
	{
		return super.toString() + String.format("Credit amount: %20.2f | Balance owing: %15.2f", this.getCreditAmount(), this.getBalanceOwing());
	}
}
