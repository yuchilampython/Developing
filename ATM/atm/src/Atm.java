import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Atm {
    public static void main(String[] args) {

        ArrayList<Account> accountList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("===== Welcome to Griffin\'s Bank ATM System =====");
            System.out.println("1. Create an account");
            System.out.println("2. Login to your account");

            System.out.println("Please enter the corresponding number to proceed: ");
            Integer command = sc.nextInt();
            
            switch(command){
                case 1: // register
                    register(accountList, sc);
                    break;
                case 2: // login
                    login(accountList, sc);
                    break;
                default:
                    System.out.println("\n Wrong input \n");
            }
        }
    }

    private static void register(ArrayList<Account> accountList, Scanner sc){
        System.out.println("Create your account");
        Account account = new Account();
        
        while(true){

            System.out.println("Please input your full name: ");
            String name = sc.next();
            account.setName(name);
            break;

        }
        
        while(true){
            System.out.println("\nPlease input your password: ");
            System.out.println("(Password must contains number, uppercase character, lowercase chacter");
            System.out.println("and at least 8 characters long)");
            String password = sc.next();
            //String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
            String pattern = "[a-z]";
            // check if the password match the password policy
            if (password.matches(pattern)){
                System.out.println("Please re-enter your password: ");
                String confirm = sc.next();
                // check if the password and confirm password are the same
                if (password.equals(confirm)){
                    account.setPassword(password);
                    break;
                } else{
                    System.out.println("\nPassword cannot be confirmed, please enter the password again. \n");
                }
            } else {
                System.out.println("Please follow the password policy.");
            }
        }
        
        while(true){
            System.out.println("Please enter the withdrawal limit of each transaction:");

            try {
                Integer limit = sc.nextInt();
                account.setLimit(limit);
                break;
            } catch (Exception e) {
                String bad_input = sc.nextLine();
                System.out.println("Please enter a valid integer.\n");
            }
        }

        // generate card number without repeating previous card number
        String cardNumber = getRandomCardNumber(accountList);
        account.setCardNumber(cardNumber);

        // add user input info to the accountList
        accountList.add(account);

        System.out.println("\n" + account.getName() + " ,your account has been created.");
        System.out.println("Please remember your card number for the purpose of logging in the system.");
        System.out.println("Your card number is: " + account.getCardNumber());
        System.out.println("Your withdrawal limit is: " + account.getLimit());
        System.out.println("Your balance is: " + account.getBalance() + "\n");

    }

    private static String getRandomCardNumber(ArrayList<Account> accountList){
        
        while(true){
            String cardNumber = "";
            Random random = new Random();
            // generate an 8-digit random number;
            for (int i=0; i < 8; i++) {
                cardNumber += random.nextInt(10);
            }

            // check if the card number already exist
            Account acc = getAccountByCardNumber(cardNumber, accountList);
            if (acc == null){
                return cardNumber;
            }
        }
    }

    private static Account getAccountByCardNumber(String cardNumber, ArrayList<Account> accountList) {
        for (Account each: accountList){
            String accCardNumber = each.getCardNumber();
            if (accCardNumber.equals(cardNumber)){
                return each;
            }
        }
        return null;
    }

    private static void login(ArrayList<Account> accountList, Scanner sc){
        // login successful = true, failed = false
        System.out.println("Card Number: ");
        String cardNumber = sc.next();
        System.out.println("Password: ");
        String password = sc.next();
        
        // check if the user exist and password are correct
        Account getAccount = getAccountByCardNumber(cardNumber, accountList);
        if (getAccount != null && getAccount.getPassword().equals(password)){
            System.out.println("Login successful \n");
            loggedInMenu(getAccount, sc);

        } else {
            System.out.println("User not exists or password is wrong.\n");
        }
    }

    private static void loggedInMenu(Account account, Scanner sc) {
        String name = account.getName();
        System.out.println("\n=====Welcome, " + name + "=====");
        System.out.println("Please enter the corresponding number to proceed:");
        System.out.println("1. Account inquiry");
        System.out.println("2. Deposit");
        System.out.println("3. Withdrawal");
        System.out.println("4. Transfer");
        System.out.println("5. Change password");
        System.out.println("6. Quit");
        System.out.println("7. Cancel account");
        System.out.println("Please enter the corresponding number to proceed: ");
        Integer command = sc.nextInt();
        switch (command) {
            case 1: // account inquiry
                accountInquiry(account, sc);
            case 2: // deposit
                deposit(account, sc);
            case 3: // withdrawal
                withdrawal(account, sc);
        }
    }

    private static void accountInquiry(Account account, Scanner sc){
        System.out.println("\n=====Account inquiry=====");
        System.out.println("Name: " + account.getName());
        System.out.println("Card Number: " + account.getCardNumber());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("Withdrawal Limit: " + account.getLimit());
        backToMenu(account, sc);
    }

    private static void backToMenu(Account account, Scanner sc){
        while(true) {
            System.out.println("Please enter '1' to go back to the previous page");
            try {
                Integer command = sc.nextInt();
                if (command == 1) {
                    loggedInMenu(account, sc);
                    break;
                }
            } catch (Exception e) {
                    String bad_input = sc.nextLine();
            }
        }
    }

    private static void deposit(Account account, Scanner sc) {
        while(true) {
            System.out.println("Input the amount to be deposit: ");
            try {
                Integer amount = sc.nextInt();
                Double balance = account.getBalance() + amount;
                account.setBalance(balance);
                System.out.println("You have deposited " + amount + " dollars.");
                backToMenu(account, sc);
            } catch (Exception e) {
                String bad_input = sc.nextLine();
                System.out.println("The input amount is wrong, please input again.");
            }
        }
    }

    private static void withdrawal(Account account, Scanner sc) {
        System.out.println("Input the amount of money you would like to withdraw: ");
        try {
            Integer amount = sc.nextInt();

            // check the balance
            if (account.getBalance() > amount){
                // check the withdrawal limit
                if (account.getLimit() > amount) {
                    // change the money in the balance
                    Double current_balance = account.getBalance();
                    account.setBalance(current_balance - amount);
                    System.out.println("You have taken out " + amount + " dollars");
                    backToMenu(account, sc);
                } else {
                    System.out.println("Your withdrawal limit is lower than the money you want to get.");
                    backToMenu(account, sc);
                }
            } else {
                System.out.println("You do not have enough money.");
                backToMenu(account, sc);
            }
        } catch (Exception e) {
            String bad_input = sc.nextLine();
            System.out.println("Please enter an integer.");
        }
    }
}
