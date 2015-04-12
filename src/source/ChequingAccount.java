/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author Oleksandr
 */
public class ChequingAccount extends Account
{
	private final static int ACCT_TYPE = 1;
	private double overdraftLimit;
	private final static double INTEREST_RATE = 0.015; // Interest rate of 1.5% for every type of customer holding this account
	private final static double OVERDRAFT_INTEREST_RATE = 0.1295; // Overdraft interest rate of 12.95% for every type of customer holding this account
	
	
	public ChequingAccount(double acctBalance, double maxWithdrawal, double monthlyFee, int freeTransactionCount, double overdraftLimit)
	{
		super(ACCT_TYPE, acctBalance, maxWithdrawal, monthlyFee, freeTransactionCount, INTEREST_RATE);
		this.overdraftLimit = overdraftLimit;
	}
	
	// ACCESSORS
	public double getOverdraftLimit()
	{
		return this.overdraftLimit;
	}
	
	//MUTATORS
	public void setOverdraftLimit(double overdraftLimit)
	{
		this.overdraftLimit = overdraftLimit;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + String.format("Overdraft limit : %7.2f | Interest rate: %7.3f | Overdraft interest rate: %7.4f", this.overdraftLimit, this.getInterestRate(), OVERDRAFT_INTEREST_RATE);
	}
}
