package source;

import java.util.ArrayList;
import java.util.Scanner;

public class Teller {

    // arraylist of customers
    private static ArrayList<Customer> customerList = new ArrayList<>();
    private static Scanner keyboard;
    private static Customer tempCust;
    private static ArrayList<Integer> customerSearch = new ArrayList<Integer>();
    private static String input;
    private static CustomerType tempCustType;
    private static AccountType tempAcctType;
    private static Account tempAcct;

    // menu driven interface
    public static void main(String[] args) {
        Teller teller = new Teller();
        keyboard = new Scanner(System.in);
        teller.mainMenu();
    }

    private void mainMenu() {
        System.out.println("-Main-----------");
        System.out.println("Input a command");
        System.out.println("0. Exit program");
        System.out.println("1. Create a new customer");
        System.out.println("2. Search and manage customer information");
        input = keyboard.next();
        while (Integer.parseInt(input) != 0) {
            switch (Integer.parseInt(input)) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    lookupCustomer();
                    break;
                default:
                    System.out.println("Please enter a valid command");
                    break;
            }
            input = keyboard.next();
        }

        System.out.println("Program ended");
        System.exit(0);
    }

    private void createCustomer() {
        System.out.println("-Customer Creation-----");
        // set up customer information

        System.out.println("Customer Type");
        System.out.println("1. Regular 2. Student 3. Senior 4. Business");
        int custType = keyboard.nextInt();
        tempCustType = CustomerType.values()[custType - 1];

        System.out.println("First Name:");
        String firstName = keyboard.next();
        System.out.println("Last Name:");
        String lastName = keyboard.next();
        System.out.println("Address:");
        keyboard.nextLine();
        String address = keyboard.nextLine();
        System.out.println("SIN:");
        String sin = keyboard.next();

        tempCust = new Customer(firstName, lastName, sin, address, tempCustType.getCustomerType());

        customerList.add(tempCust);
        addAccount(tempCustType);
        mainMenu();
    }

    private void addAccount(CustomerType custType) {
        System.out.println("What account would you like to make?");
        int index = 1;
        if (custType != CustomerType.STUDENT) {
            System.out.print(index + ". Chequing ");
            index++;
        }
        if (custType != CustomerType.BUSINESS) {
            System.out.print(index + ". Savings ");
            index++;
        }
        System.out.print(index + ". Credit ");
        index++;
        if (custType == CustomerType.BUSINESS) {
            System.out.print(index + ". Business");
            index++;
        }
        System.out.println("");
        int accChosen = keyboard.nextInt();
        if (custType == CustomerType.REGULAR) {
            if (accChosen == 1) {

                tempAcctType = AccountType.CHEQUING;
                tempAcct = new CreditAccount(5000, 1000, 8, 0, 1.1495);

            } else if (accChosen == 2) {

                tempAcctType = AccountType.SAVINGS;
                tempAcct = new CreditAccount(5000, 1000, 8, 0, 1.1495);
            } else if (accChosen == 3) {

                tempAcctType = AccountType.CREDIT;
                tempAcct = new CreditAccount(5000, 1000, 8, 0, 1.1495);
            } else {
                System.out.println("Invalid account");
                mainMenu();
            }
        } else if (tempCustType == CustomerType.STUDENT) {
            if (accChosen == 1) {

                tempAcctType = AccountType.SAVINGS;
                tempAcct = new CreditAccount(5000, 1000, 8, 0, 1.1495);
            } else if (accChosen == 2) {

                tempAcctType = AccountType.CREDIT;
                tempAcct = new CreditAccount(5000, 1000, 8, 0, 1.1495);
            } else {
                System.out.println("Invalid account");
                mainMenu();
            }
        } else if (custType == CustomerType.SENIOR) {
            if (accChosen == 1) {
                tempAcctType = AccountType.CHEQUING;
                tempAcct = new CreditAccount(5000, 1000, 8, 0, 1.1495);

            } else if (accChosen == 2) {

                tempAcctType = AccountType.SAVINGS;
                tempAcct = new CreditAccount(5000, 1000, 8, 0, 1.1495);
            } else if (accChosen == 3) {

                tempAcctType = AccountType.CREDIT;
                tempAcct = new CreditAccount(5000, 1000, 8, 0, 1.1495);
            } else {
                System.out.println("Invalid account");
                mainMenu();
            }
        } else if (custType == CustomerType.BUSINESS) {
            if (accChosen == 1) {

                tempAcctType = AccountType.CHEQUING;
                tempAcct = new CreditAccount(5000, 1000, 8, 0, 1.1495);
            } else if (accChosen == 2) {

                tempAcctType = AccountType.CREDIT;
                tempAcct = new CreditAccount(5000, 1000, 8, 0, 1.1495);
            } else if (accChosen == 3) {

                tempAcctType = AccountType.BUSINESS;
                tempAcct = new CreditAccount(5000, 1000, 8, 0, 1.1495);
            } else {
                System.out.println("Invalid account");
                mainMenu();
            }
        }

        //create the acount
        if (tempCust.createAcct(tempAcct)) {
            System.out.println("Account creation successful.");
        } else {
            System.out.println("Customer already has max limit of accounts.");
        }

    }

    private void lookupCustomer() {

        System.out.println("-Listing Customers------");
        if (customerList.isEmpty()) {
            System.out.println("There are no customers in your application.");
        } else {
            displayCustomerList();
            Customer foundCust = getManageCustomer();
            if (foundCust != null) {
                tempCust = foundCust;
                manageCustomer();
            } else {
                System.out.println("No matching customer found");
            }
        }
        mainMenu();
    }

    private void displayCustomerList() {

        int i = 1;

        String head = String.format("%s %20s %20s %20s %20s",
                "ID", "First Name", "Last Name", "SIN", "Address");
        System.out.println(head);

        for (Customer tempCust : customerList) {
            System.out.println(i + " " + tempCust.toString());
            i++;
        }

    }

    private Customer getManageCustomer() {
        try {
            System.out.println("Please enter the customer's ID");
            int id = keyboard.nextInt();
            return customerList.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }

    private void manageCustomer() {

        System.out.println("-Edit " + tempCust.getFirstName() + " " + tempCust.getLastName() + "------");
        // cust.displayCustomerInfo();
        // cust.displayAccountInfo();
        System.out.println("Input a command");
        System.out.println("0. Back to main");
        System.out.println("1. View balance");
        System.out.println("2. Deposit into an account");
        System.out.println("3. Withdraw from an account");
        System.out.println("4. Add new account");
        System.out.println("5. Edit customer information");
        String input = keyboard.next();
        while (Integer.parseInt(input) != 0) {
            switch (Integer.parseInt(input)) {
                case 1:
                    getAllAccount();
                    manageCustomer();
                    break;
                case 2:
                    chooseAccount(TransactionType.DEPOSIT);
                    break;
                case 3:
                    chooseAccount(TransactionType.WITHDRAWAL);
                    break;
                case 4:
                    System.out.println(tempCust.getCustType());
                    addAccount(tempCust.getCustType());
                    manageCustomer();
                    break;
                case 5:
                    editInformation();
                    break;
                default:
                    System.out.println("Please enter a valid command");
                    manageCustomer();
                    break;
            }
            input = keyboard.next();
        }

        mainMenu();
    }

    public void getAllAccount() {
        String accBalance = "";
        String accType = "";
        System.out.println("-Accounts in " + tempCust.getFirstName() + " " + tempCust.getLastName() + "----");

        for (Account tempAccount : tempCust.getAcctList()) {
            tempAcctType = AccountType.values()[tempAccount.getAcctType() - 1];
            //  accBalance += String.format("%s %20s",
            // tempAcctType.toString(), Double.toString(tempAccount.getAcctBalance()));
            accBalance += tempAccount;
            System.out.println(accBalance);
        }
    }

    private void chooseAccount(TransactionType transType) {
        // Display account info
        // cust.displayAccountInfo();
        try {
            getAllAccount();
            System.out.println("Choose account for your " + transType);
            int acctChoose = keyboard.nextInt();
            tempAcct = tempCust.getAcctList().get(acctChoose - 1);

            System.out.println("Amount of your " + transType);
            double amount = keyboard.nextDouble();
            if (transType == TransactionType.DEPOSIT) {
                tempAcct.deposit(amount);
            } else if (transType == TransactionType.WITHDRAWAL) {
                tempAcct.withdraw(amount);

            }

            System.out.println("Transaction successful");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("No matching account found");

        } finally {

            manageCustomer();
        }

    }

    private void editInformation() {

        System.out.println("-Edit Information------");
        // display account info
        // cust.displayCustomerInfo();
        System.out.println("Input a command");
        System.out.println("0. Back to main");
        System.out.println("1. Edit First Name");
        System.out.println("2. Edit Last Name");
        System.out.println("3. Edit Address");
        System.out.println("4. Edit SIN");

        String input = keyboard.next();
        while (Integer.parseInt(input) != 0) {
            switch (Integer.parseInt(input)) {
                case 1:
                    //edit first names
                    String fName = keyboard.next();
                    break;
                case 2:
                    //edit last name
                    break;
                case 3:
                    //edit address
                    break;
                case 4:
                    // create account
                    break;
                case 5:
                    editInformation();
                    break;
                default:
                    System.out.println("Please enter a valid command");
                    manageCustomer();
                    break;
            }
            input = keyboard.next();
        }

        manageCustomer();
    }

}
