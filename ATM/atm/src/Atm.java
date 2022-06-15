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
            try{
                System.out.println("Please input your full name: ");
                String name = sc.next();
                account.setName(name);
                break;
            } catch (Exception e){
                System.out.println("Please enter a valid full name.");
            }
        }
        
        while(true){
            System.out.println("\nPlease input your password: ");
            System.out.println("(Password must contains number, uppercase character, lowercase chacter");
            System.out.println("and at least 8 characters long)");
            String password = sc.next();
            String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
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
            try {
                System.out.println("Please enter the withdrawl limit of each transaction:");
                Integer limit = sc.nextInt();
                account.setLimit(limit);
                break;
            } catch (Exception e) {
                System.out.println("Please enter an Integer.");
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
            String name = getAccount.getName();
            System.out.println("=====Welcome, " + name + "=====");
            System.out.println("Please enter the corresponding number to proceed:");
        } else{
            System.out.println("User not exists or password is wrong.\n");
        }
    }
}
