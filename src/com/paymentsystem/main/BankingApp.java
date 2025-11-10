package com.paymentsystem.main;

import com.paymentsystem.exception.BankNotFoundException;
import com.paymentsystem.exception.InvalidLoginException;
import com.paymentsystem.exception.UserAlreadyExistsException;
import com.paymentsystem.exception.UserNotFoundException;
import com.paymentsystem.model.Account;
import com.paymentsystem.model.User;
import com.paymentsystem.repository.IAccountRepository;
import com.paymentsystem.repository.IBankRepository;
import com.paymentsystem.repository.IUserRepository;
import com.paymentsystem.repository.impl.AccountMemoryRepository;
import com.paymentsystem.repository.impl.BankMemoryRepository;
import com.paymentsystem.repository.impl.UserMemoryRepository;
import com.paymentsystem.Services.IBankService;
import com.paymentsystem.Services.IUserService;
import com.paymentsystem.Services.impl.BankServiceImpl;
import com.paymentsystem.Services.impl.UserServiceImpl;

// import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class BankingApp {

    private static IUserService userService;
    
    // change 1
    private static IBankService bankService;
    private static IUserRepository userRepository;
    private static IAccountRepository accountRepository;
    private static IBankRepository bankRepository;    
    //end 1

    private static Scanner scanner;
    private static User currentUser=null;
    
    // change 1
    private static final String DEFAULT_BANK_IFSC = "PROJ1234";
    // end 1 

    public static void main(String[] args){
        // repo initiate
        userRepository = new UserMemoryRepository();
        accountRepository= new AccountMemoryRepository();
        bankRepository = new BankMemoryRepository();

        // create services
        userService = new UserServiceImpl(userRepository);
        bankService = new BankServiceImpl(bankRepository, accountRepository, userRepository);
        scanner = new Scanner(System.in);

        // existing bank;
        setupDefaultBank();
        System.out.println("Welcome to the payment System Simulation");

        while(true){
            if(currentUser==null){
                showLoggedOutMenu();
            }else{
                showLoggedInMenu();
            }
        }
    }

    private static void setupDefaultBank(){
        try{
            // create new bank;
            bankService.createBank("Payment System Bank", DEFAULT_BANK_IFSC);
            System.out.println("Defaultbank (PSB, " + DEFAULT_BANK_IFSC + ") created.");
        }catch(Exception e){
            System.out.println("FATAL ERROR : Could not create default bank.");
        }
    }

    public static void showLoggedOutMenu(){
        System.out.println("\n-- Main Menu (Logged Out) --");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Please enter your choice: ");

        int choice = readIntInput();

        switch(choice){
            case 1:
                handleRegister();
                break;
            case 2: 
                handleLogin();
                break;
            case 3:
                System.out.println("Thank you for using the system. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again");
        }
    } 

    private static void showLoggedInMenu(){
        System.out.println("\n-- Main Menu (Logged in as "+ currentUser.getName()+ ")");
        System.out.println("1. View My Account");
        System.out.println("2. Create New Bank Account");
        System.out.println("3. (Coming Soon : Make Payment)");
        System.out.println("4. Logout");
        System.out.print("Please enter your choice: ");

        int choice = readIntInput();

        switch(choice){
            case 1:
                handleViewMyAccounts();
                break;
            case 2:
                handleCreateAccount();
                break;
            case 4:
                handleLogout();
                break;
            default :
                System.out.println("Invalid choice. Please try again.");
        }
    }
    
    private static void handleRegister() {
        System.out.println("\n -- New User Registration --");
        try{
            System.out.print("Enter your name: ");
            String name= scanner.nextLine();
            System.out.print("Enter your email: ");
            String email=scanner.nextLine();
            System.out.print("Enter your password: ");
            String password =scanner.nextLine();

            User newUser = userService.register(name, email, password);
            System.out.println("Registration successfull! Your new User Id is: " + newUser.getUserId());    
        } catch(UserAlreadyExistsException e){
            System.err.println("Registration Failed: " + e.getMessage());
        } catch (Exception e){
            System.err.println("An unexpected error occured: " + e.getMessage());
        }
    }

    private static void handleLogin(){
        System.out.println("\n -- User Login -- ");
        try{
            System.out.print("Enter your email: ");
            String email =scanner.nextLine();
            System.out.print("Enter your password: ");
            String password =scanner.nextLine();

            currentUser =userService.login(email, password);
            System.out.println("Login successful! Welcome, "+ currentUser.getName() + ".");
        } catch(UserNotFoundException | InvalidLoginException e){
            System.err.print("Login Filed : " + e.getMessage());
        } catch(Exception e){
            System.err.println("An unexpected error occured: " + e.getMessage());
        }
    }

    private static void handleLogout(){
        currentUser=null;
        System.out.println("You have been loggged out successfully.");
    }
    
    private static void handleViewMyAccounts(){
        System.out.println("\n -- Your Bank Accounts -- ");
        try{
            // servise call for loged-in-user
            List<Account>accounts = bankService.getUserAccounts(currentUser.getUserId());
            if(accounts.isEmpty()){
                System.out.println("You do not have any bank accounts yet .");
            }else{
                for(Account acc:accounts){
                    System.out.println("--------------------------------------");
                    System.out.println(" Bank:  " + acc.getBank().getBankName());
                    System.out.println(" Account Number: " + acc.getAccountNumber());
                    System.out.println(" Balance:  $" + String.format("%.2f",acc.getBalance()));   
                }
                System.out.println("------------------------------------------");   
            }
        }catch(UserNotFoundException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleCreateAccount(){
        System.out.println("\n --- Create New Bank Account ---");
        try{
            System.out.print("Enter the initial deposite amount: ");
            double initialDeposit =readDoubleInput();

            if(initialDeposit<0){
                System.out.println("Initial deposit can not be negative.");
                return;
            }

            Account newAccount =bankService.createAccount(currentUser.getUserId(), DEFAULT_BANK_IFSC, initialDeposit);

            System.out.println("Success, Your new account has been created");
            System.out.println("Account Number: " + newAccount.getAccountNumber());
            System.out.println("New Balance : $"+ newAccount.getBalance() );
        }catch (UserNotFoundException | BankNotFoundException e){
            System.err.println("Error creating account: "+ e.getMessage());
        } catch(Exception e){
            System.err.println("An unexpected error occurred: "+ e.getMessage());
        }
    }

    private static int readIntInput(){
        try{
            int choice =Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e){
            System.out.println("Invalid input.Please enter a number.");
            return -1;
        }
    }

    private static double readDoubleInput(){
        try{
            double amount = Double.parseDouble(scanner.nextLine());
            return amount;
        } catch(NumberFormatException e){
            System.out.println("Invalid input. Please enter a valid number .");
            return -1;
        }
    }
}