package com.paymentsystem.main;

import com.paymentsystem.exception.InvalidLoginException;
import com.paymentsystem.exception.UserAlreadyExistsException;
import com.paymentsystem.exception.UserNotFoundException;
import com.paymentsystem.model.User;
import com.paymentsystem.repository.IUserRepository;
import com.paymentsystem.repository.impl.UserMemoryRepository;
import com.paymentsystem.Services.IUserService;
import com.paymentsystem.Services.impl.UserServiceImpl;

// import java.util.InputMismatchException;
import java.util.Scanner;
public class BankingApp {

    private static IUserService userService;
    private static Scanner scanner;

    private static User currentUser=null;

    public static void main(String[] args){

        IUserRepository userRepository = new UserMemoryRepository();
        userService = new UserServiceImpl(userRepository);
        scanner = new Scanner(System.in);

        System.out.println("Welcome to the payment System Simulation");

        while(true){
            if(currentUser==null){
                showLoggedOutMenu();
            }else{
                showLoggedInMenu();
            }
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
        System.out.println("1. Logout");
        System.out.println("2. (Coming Soon : View Balance)");
        System.out.println("3. (Coming Soon : Make Payment)");
        System.out.print("Please enter your choice: ");

        int choice = readIntInput();

        switch(choice){
            case 1:
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

    private static int readIntInput(){
        try{
            int choice =Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e){
            System.out.println("Invalid input.Please enter a number.");
            return -1;
        }
    }

}
