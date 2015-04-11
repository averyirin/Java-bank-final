package source;

import java.util.ArrayList;
import java.util.Scanner;

public class Teller
{
	// Variables to hold user input
	private static String firstName;
	private static String lastName;
	private static String sin;
	private static String address;
	private static int acctNumber;
	private static double amount = 0;
	private static String filter;
	private static int userInput = -1; // this variable stores menu choices made by a user
	private static int userInput_2 = -1; // this variable stores the index of the customer we choose to edit
	private static Scanner scan = new Scanner(System.in);

	// Variable to keep the menus going
	private static boolean exitMenu1 = false;
	private static boolean exitMenu2 = false;
	// Customer class wrapper
	private static Customer customer_temp;
	// Collection for holding customer records
	private static ArrayList<Customer> customerList = new ArrayList<Customer>();
	// Collection for holding indexes of customers from customerList that match searching criteria
	private static ArrayList<Integer> customerSearch = new ArrayList<Integer>();

	// adds a customer
	public static void addCustomer(Customer customer)
	{
		customerList.add(customer);
	}

	// accessor for customer record
	public static Customer getCustomer(int index)
	{
		return customerList.get(customerSearch.get(index - 1));
	}

	// looks up for a sequence of characters(filter) in customer names
	public static String searchCustomer(String filter)
	{
		for (Customer customer : customerList)
		{
			if (customer.getFirstName().contains(filter))
			{
				customerSearch.add(customerList.indexOf(customer));
			}
		}
		return String.format("Records found: %d", customerSearch.size());
	}

	// clears array that holds matching indexes from our search
	public static void clearCustomerSearch()
	{
		customerSearch.clear();
	}

	// prints search results to the screen
	public static StringBuilder displaySearchResults()
	{
		StringBuilder sb = new StringBuilder();

		for (Customer customer : customerList)
		{
			for (int i = 0; i < customerSearch.size(); ++i)
			{
				if (customerList.indexOf(customer) == customerSearch.get(i))
				{
					sb.append(String.format("%10sRECORD# %d%n", " ", (customerSearch.indexOf(i) + 1)));
					sb.append(customer.toString());
					sb.append('\n');

					for (Account account : customer.getAcctList())
					{
						sb.append(account.toString());
						sb.append('\n');
					}
				}
			}
		}
		return sb;
	}

// START OF THE MAIN METHOD
	public static void main(String[] args)
	{
// FIRST LEVEL MENU
		try
		{
			while (!exitMenu1) // First level menu
			{
				exitMenu2 = false; // We want to be able to enter the 2nd level menu
				System.out.println("-1- (create a customer)\n-2- (view/edit a customer record)\n-9- (exit the program)");
				userInput = Integer.parseInt(scan.nextLine());
				switch (userInput)
					{
					case 1:
						// Obtaining input from user
						System.out.print("Enter your first name: ");
						firstName = scan.nextLine();
						System.out.print("Enter your last name: ");
						lastName = scan.nextLine();
						System.out.print("Enter your SIN: ");
						sin = scan.nextLine();
						System.out.print("Enter your address: ");
						address = scan.nextLine();

						// Creating and saving new customer record
						customer_temp = new Customer(firstName, lastName, sin, address);
						addCustomer(customer_temp);
						break;
					case 2:
						if (!customerList.isEmpty())
						{
							System.out.print("Enter a name to search by: ");
							filter = scan.nextLine();

							System.out.println(searchCustomer(filter));

							if (!customerSearch.isEmpty())
							{
								System.out.println(displaySearchResults());
	// SECOND LEVEL MENU
								while (!exitMenu2)
								{
									// Get the index of a customer record (in customerList) from customerSearch
									System.out.print("Choose a record number to edit: \n-9-(back to the main menu)\n");
									userInput_2 = Integer.parseInt(scan.nextLine());
									if (userInput_2 == 9)
									{
										clearCustomerSearch(); //purge our customerSearch array
										exitMenu2 = true;
									}
									else
									{
										System.out.printf("%s%53s%n", "Change credentials: ", "Account operations: ");
										System.out.printf("%s%52s%n%s%n", "-1-(name) -2-(surname) -3-(sin) -4-(address)", "-5-(deposit) -6-(withdraw) -7-(add account)", "-9-(back to the main menu)");
										userInput = Integer.parseInt(scan.nextLine());
										switch (userInput)
											{
											case 1:
												System.out.print("Enter new name: ");
												firstName = scan.nextLine();
												getCustomer(userInput_2).setFirstName(firstName);
												break;
											case 2:
												System.out.print("Enter new surname: ");
												lastName = scan.nextLine();
												getCustomer(userInput_2).setLastName(lastName);
												break;
											case 3:
												System.out.print("Enter new SIN: ");
												sin = scan.nextLine();
												getCustomer(userInput_2).setSin(sin);
												break;
											case 4:
												System.out.print("Enter new address: ");
												address = scan.nextLine();
												getCustomer(userInput_2).setAddress(address);
												break;
											case 5:
												System.out.print("Enter account number: ");
												acctNumber = Integer.parseInt(scan.nextLine());
												System.out.print("Enter deposit amount: ");
												amount = Double.parseDouble(scan.nextLine());
												if (getCustomer(userInput_2).getAccount(acctNumber).Deposit(amount))
													System.out.println("Successful deposit!");
												else
													System.out.println("Invalid amount specified. Operation cancelled.");
												break;
											case 6:
												System.out.print("Enter account number: ");
												acctNumber = Integer.parseInt(scan.nextLine());
												System.out.print("Enter withdraw amount: ");
												amount = Double.parseDouble(scan.nextLine());
												if (getCustomer(userInput_2).getAccount(acctNumber).Withdraw(amount))
													System.out.println("Successful withdraw!");
												else
													System.out.println("Insufficient balance/Limit exceeded. Operation cancelled.");
												break;
											case 7:
												if(getCustomer(userInput_2).createAcct())
												{
													System.out.println("A new account has been added");
												}
												else
												{
													System.out.println("A maximum allowed number of accounts has been exceeded");
												}
												break;
											case 9:
												clearCustomerSearch(); //we purge our customerSearch array
												exitMenu2 = true;
												break;
											default:
												System.out.println("Invalid input");
											}
									}
								}
							}
						}
						else
							System.out.println("There are no records present");
						break;
					case 9:
						exitMenu1 = true;
						break;
					default:
						System.out.println("Invalid input");
					}
			}
		}
		catch (Exception ex)
		{
			System.out.println("Terminating...");
		}
	} // END OF THE MAIN METHOD
}

