package PJ.atm;
import java.util.Scanner;
public class Atm {
    private double balance;
    private int pin = 2004;
    Scanner scanner = new Scanner(System.in);   

    public boolean authenticate(){
        System.out.print("Enter your PIN: ");
        int inputPin = scanner.nextInt();
        if (inputPin == pin) {
            System.out.println("Authentication successful.");
            return true;
        } else {
            System.out.println("Authentication failed. Incorrect PIN.");
            return false;
        }

    }
    public void showMenu(){
        int choice;
        do{
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.println("CHOOSE ANN OPTION:");
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2: 
                depositMoney();
                break;
            case 3:
                withdrawMoney();    
                break;
            case 4:
                System.out.println("Exiting ATM. Thank you!");
                break;
            default:    
                System.out.println("Invalid choice. Please try again.");

    }
        } while (choice != 4);


    }

    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }   
    public void depositMoney() {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
            checkBalance();
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    public void withdrawMoney() {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            checkBalance();
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }

}
    
}
