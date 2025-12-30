package basics;

import java.util.Scanner;

// Interface for banking operations
interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount) throws InsufficientBalanceException;
    double checkBalance();
}

// Interface for customer related operations
interface CustomerOperations {
    void displayCustomerDetails();
}

// Custom exception
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

// BankAccount class implementing multiple interfaces
class BankAccount implements BankOperations, CustomerOperations {

    private int accountNumber;
    private String customerName;
    private double balance;

    // Constructor
    public BankAccount(int accNo, String name, double bal) {
        accountNumber = accNo;
        customerName = name;
        balance = bal;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        System.out.println("Deposit successful");
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        balance -= amount;
        System.out.println("Withdrawal successful");
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public void displayCustomerDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Balance: " + balance);
    }
}

public class BankAccountInterfaces {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Customer Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Balance: ");
            double bal = sc.nextDouble();

            BankAccount account = new BankAccount(accNo, name, bal);

            int choice;
            do {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Display Account Details");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter deposit amount: ");
                        account.deposit(sc.nextDouble());
                        break;

                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        account.withdraw(sc.nextDouble());
                        break;

                    case 3:
                        System.out.println("Current Balance: " + account.checkBalance());
                        break;

                    case 4:
                        account.displayCustomerDetails();
                        break;

                    case 5:
                        System.out.println("Exiting program");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }

            } while (choice != 5);

        } catch (InsufficientBalanceException e) {
            System.out.println("Transaction Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e);
        } finally {
            sc.close();
        }
    }
}

