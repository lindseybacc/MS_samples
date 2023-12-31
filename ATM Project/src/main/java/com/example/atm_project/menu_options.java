package com.example.atm_project;

import java.util.Scanner;
public class menu_options extends user_accounts {

    Scanner input = new Scanner(System.in);
    private double pinNum;         // Used to set and verify PIN

    /*-------------------------------------------------------------
     @function userType()
     This function will determine if new or returning user
     If new user, we will create an account
     -------------------------------------------------------------*/
    public void userType() {
        double accNum = 0.0;
        System.out.println("Are you a new user, or an existing user?");
        System.out.println("Select 1 - New User");
        System.out.println("Select 2 - Existing User");
        int userSelection = input.nextInt();


        switch (userSelection) {
            // if new account, create a new account number, customer will set a PIN
            case 1:
                //accNum = setAccNum();
                System.out.println("Your new customer number is: " + accNum);
                System.out.println("Enter a unique PIN");
                pinNum = input.nextInt();
                setPinNum(pinNum);
                break;
            case 2:
                // verifying if there is an existing account
                if (accNum == 0.0) {
                    System.out.println("You don't have an account yet. Making one now...");
                    //accNum = setAccNum();
                    System.out.println("Your new customer number is: " + accNum);
                    System.out.println("Enter a unique PIN");
                    pinNum = input.nextInt();
                    setPinNum(pinNum);
                }
                break;
            default:
                System.out.println("Invalid entry.");
                userType();
        }
    }

    /*-------------------------------------------------------------
    @function userProfile()
    This function will ensure username is created and stored
    -------------------------------------------------------------*/
    public boolean userProfile(String username) {
        boolean check = false;

        if (getUserName().isBlank()) {
            setUserName(username);
            check = true;
        } else if (getUserName().equals(username)) {
            check = true;
        } else {
            check = false;
        }

        return check;
    }

    /*-------------------------------------------------------------
    @function verifyUser()
    This function will verify user PIN in order to continue
    -------------------------------------------------------------*/
    public void verifyUser() {
        do {
            System.out.println("Verify your PIN");
            pinNum = input.nextInt();
        } while (pinNum != getPinNum());
    }

    public String comparePIN() {
        String changePIN = Double.toString(getPinNum());
        return changePIN;
    }
    /*-------------------------------------------------------------
    @function chooseAccount()
    This function will ask the user to choose which account they'd
    like to access
    -------------------------------------------------------------*/
    public void chooseAccount() {

        System.out.println("Which account do you want to access? Choose one of the following:");
        System.out.println("1 - Checking");
        System.out.println("2 - Savings");
        System.out.println("3 - Exit ATM");

        int userSelection = input.nextInt();
        switch (userSelection) {
            case 1:
                accessChecking();
                break;
            case 2:
                accessSavings();
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid entry.");
                userType();
        }
    }

    /*-------------------------------------------------------------
     @function accessChecking()
     This function will perform deposit, balance checks, or
     withdrawals depending on user selection.
    -------------------------------------------------------------*/
    public void accessChecking() {
        System.out.println("What would you like to do today? Choose one of the following numbers:");
        System.out.println("1 - Check Balance");
        System.out.println("2 - Deposit");
        System.out.println("3 - Withdraw");

        int userSelection = input.nextInt();
        switch (userSelection) {
            case 1:
                System.out.print("Checking Account balance: $" + getCheckingBalance() + "\n");
                break;
            case 2:
                //depositChecking();
                System.out.print("Checking Account balance: $" + getCheckingBalance() + "\n");
                break;
            case 3:
                //withdrawChecking();
                System.out.print("Checking Account balance: $" + getCheckingBalance() + "\n");
            default:
                System.out.println("Invalid entry.");
                accessChecking();
        }
    }

    /*-------------------------------------------------------------
     @function accessSavings()
     This function will perform deposit, balance checks, or
     withdrawals depending on user selection.
    -------------------------------------------------------------*/
    public void accessSavings(){
        System.out.println("What would you like to do today? Choose one of the following numbers:");
        System.out.println("1 - Check Balance");
        System.out.println("2 - Deposit");
        System.out.println("3 - Withdraw");

        int userSelection = input.nextInt();
        switch (userSelection) {

            case 1:
                System.out.print("Savings Account balance: $" + (getSavingBalance()) + "\n");
                break;
            case 2:
                //depositSavings();
                System.out.print("Savings Account new balance: $" + (getSavingBalance()) + "\n");
                break;
            case 3:
                //withdrawSavings();
                System.out.print("Savings Account new balance: $" + (getSavingBalance()) + "\n");
            default:
                System.out.println("Invalid entry.");
                accessSavings();
        }

    }

}
