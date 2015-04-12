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
public class MainTest
{
	public static void main(String[] args)
	{
		Account account = new CreditAccount(5000, 1000, 8, 0, 1.1495);
		CreditAccount acct = (CreditAccount) account;
		System.out.println(account);
		account.withdraw(1000);
		System.out.println(account);
		account.deposit(1000);
		System.out.println(account);
		account.withdraw(1000);
		System.out.println(account);
		System.out.println(account.getTotalOwed());
	}
}
