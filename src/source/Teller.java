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
            } else if (accChosen == 2) {

                tempAcctType = AccountType.SAVINGS;
            } else if (accChosen == 3) {

                tempAcctType = AccountType.CREDIT;
            }
        } else if (tempCustType == CustomerType.STUDENT) {
            if (accChosen == 1) {

                tempAcctType = AccountType.SAVINGS;
            } else if (accChosen == 2) {

                tempAcctType = AccountType.CREDIT;
            }
        } else if (custType == CustomerType.SENIOR) {
            if (accChosen == 1) {
                tempAcctType = AccountType.CHEQUING;

            } else if (accChosen == 2) {

                tempAcctType = AccountType.SAVINGS;
            } else if (accChosen == 3) {

                tempAcctType = AccountType.CREDIT;
            }
        } else if (custType == CustomerType.BUSINESS) {
            if (accChosen == 1) {

                tempAcctType = AccountType.CHEQUING;
            } else if (accChosen == 2) {

                tempAcctType = AccountType.CREDIT;
            } else if (accChosen == 3) {

                tempAcctType = AccountType.BUSINESS;
            }
        }
        //create the acount
        switch (tempAcctType) {
            case CHEQUING:
                // chequing account
                System.out.println("Chequing Created");
                /*
                 * RegularCust regCust = new RegularCust(firstName, lastName,
                 * address, PIN); customerList.add(regCust);
                 */

                break;
            case SAVINGS:
                // student customer
                System.out.println("Savings Created");
                /*
                 * System.out.println("Student Number?"); int stuNum =
                 * keyboard.nextInt(); StudentCust studCust = new
                 * StudentCust(firstName, lastName, address, PIN, stuNum);
                 * customerList.add(studCust);
                 */
                break;
            case CREDIT:
                // Senior Customer
                System.out.println("Credit Created");
                // Customer seniorCust = new Customer(custType.SENIOR, firstName,
                // lastName, address, PIN);

                /*
                 * SeniorCust senCust = new SeniorCust(firstName, lastName, address,
                 * PIN); customerList.add(senCust);
                 */
                break;
            case BUSINESS:
                // Business Customer
                System.out.println("Business Created");
                /*
                 * System.out.println("Stocks Available?"); int stockAvail =
                 * keyboard.nextInt(); BusinessCust busCust = new
                 * BusinessCust(firstName, lastName, address, PIN, stockAvail);
                 * customerList.add(busCust);
                 */
                break;

            default:
                System.out.println("An error has occured");
        }
    }

    private void lookupCustomer() {

        System.out.println("-Listing Customers------");
        if (customerList.isEmpty()) {
            System.out.println("There are no customers in your application.");
        }else{
        displayCustomerList();
        Customer foundCust = getManageCustomer();
        if (foundCust != null) {
            manageCustomer(foundCust);
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
        try{
        System.out.println("Please enter the customer's ID");
        int id = keyboard.nextInt();
        return customerList.get(id-1);
        }catch(IndexOutOfBoundsException e){
            return null;
        }
        
       
    }

    private void manageCustomer(Customer cust) {

        System.out.println("-Edit "+cust.getFirstName()+" "+cust.getLastName()+"------");
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
                    getAllAccountBalance(cust);
                    manageCustomer(cust);
                    break;
                case 2:
                    chooseAccount(cust, TransactionType.DEPOSIT);
                    break;
                case 3:
                    chooseAccount(cust,  TransactionType.WITHDRAWAL);
                    break;
                case 4:
                    // create account
                    break;
                case 5:
                    editInformation(cust);
                    break;
                default:
                    System.out.println("Please enter a valid command");
                    manageCustomer(cust);
                    break;
            }
            input = keyboard.next();
        }

        mainMenu();
    }
 public void getAllAccountBalance(Customer cust){
            String accBalance="";
            String accType="";
            System.out.println("Account Type    Account Balance");
            for(Account tempAccount:cust.getAcctList()){
                tempAcctType = AccountType.values()[tempAccount.getAcctType()];
                accBalance += tempAcctType.toString()+": "+Double.toString(tempAccount.getAcctBalance());
                System.out.print(accBalance);
            }
        }
    private void chooseAccount(Customer cust, TransactionType transType) {
        // Display account info
        // cust.displayAccountInfo();
        if(transType == TransactionType.DEPOSIT){
            
        }else if(transType == TransactionType.WITHDRAWAL){
            
        }
       
        manageCustomer(cust);
    }

    private void editInformation(Customer cust) {

        System.out.println("-Edit Information------");
        // display account info
        // cust.displayCustomerInfo();
        System.out.println("Input a command");
        System.out.println("0. Back to main");
        System.out.println("1. Edit First Name");
        System.out.println("2. Edit Last Name");
        System.out.println("3. Edit Address");
        System.out.println("4. Edit Access Card");
        System.out.println("5. Edit PIN");

        String input = keyboard.next();
        while (Integer.parseInt(input) != 0) {
            switch (Integer.parseInt(input)) {
                case 1:
                    //edit first names
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
                    editInformation(cust);
                    break;
                default:
                    System.out.println("Please enter a valid command");
                    manageCustomer(cust);
                    break;
            }
            input = keyboard.next();
        }

        manageCustomer(cust);
    }

}

