package com.example.atm_project;

import java.text.*;
import java.text.Format;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Random;

abstract class user_accounts {
    Scanner input = new Scanner(System.in);
    Random randGenerator = new Random();         // to create account numbers
    private static double checkAccNum;                  // to set customer checking account number
    private static double savingAccNum;                 // to set customer savings account number
    private static String atmUserName;
    private static double calculate;                    // to calculate and store new balances into table
    private static Hashtable<String,Double> userInfo = new Hashtable<>(); // storing user info into a hash table
    private static String menu;



    /*-------------------------------------------------------------
    @function setAccNum()
    This function will create and return a checking or savings
    account number.
    @return checkingAccNum The account number for checking
    @return savingAccNum The account number for savings
    -------------------------------------------------------------*/
    public void setAccNum(){
//        double answer;
//        System.out.println("Which account would you like to make? Select a '1' or '2':");
//        System.out.println("1 - Checking Account");
//        System.out.println("2 - Savings Account");
//        answer = input.nextInt();
//
//        // if entry is invalid, loop executes
//        while (answer != 1 && answer != 2) {
//            System.out.println("Make a valid selection. Select a '1' or '2':");
//            answer = input.nextInt();
//        }

        // creating account numbers and returning them
        userInfo.put("CheckingAccountNum",setCheckNum());
        userInfo.put("CheckingAccBalance",0.00);

        userInfo.put("SavingsAccountNum",setSavingNum());
        userInfo.put("SavingsAccBalance",0.00);

    }

    /*-------------------------------------------------------------
    @function setUserName()
    This function will take user-entered name and store as username
    -------------------------------------------------------------*/
    public void setUserName(String name) {
        atmUserName = name;
    }

    /*-------------------------------------------------------------
     @function getUserName()
     This function return the UserName for bank account
    -------------------------------------------------------------*/
    public String getUserName() {
        return atmUserName;
    }

    /*-------------------------------------------------------------
    @function setCheckNum()
    This function will create and return a checking account number
    @return checkAccNum - The account number for checking account
    -------------------------------------------------------------*/
    public double setCheckNum(){
        this.checkAccNum = randGenerator.nextInt(999999);
        return checkAccNum;
    }


    /*-------------------------------------------------------------
    @function setSavingNum()
    This function will create and return a savings account number
    @return savingAccNum - The account number for savings
    -------------------------------------------------------------*/
    public double setSavingNum(){
        this.savingAccNum = randGenerator.nextInt(999999);
        return savingAccNum;
    }

    /*-------------------------------------------------------------
    @function setPinNum(int pin)
    This function set a pin
    @param pin - the Number given by customer
    @return pinNum - The pin number for user
    -------------------------------------------------------------*/
    public void setPinNum(double pin) {
        userInfo.put("PIN",pin);
    }


    /*-------------------------------------------------------------
    @function getPinNum()
    This function return the number
    @return pinNum - the pin number for user
    -------------------------------------------------------------*/
    public double getPinNum() {
        return userInfo.get("PIN");
    }

    /*-------------------------------------------------------------
    @function verifyUser()
    This function return the number
    @return pinNum - the pin number for user
    -------------------------------------------------------------*/
    public boolean verifyUser(String username, int PIN) {
        if (userInfo.get("PIN") == PIN && username == atmUserName);
        return true;
    }


    /*-------------------------------------------------------------
    @function getCheckingBalance()
    This function will get the balance for the checking account
    @return checkingBalance - total amount in checking account
    -------------------------------------------------------------*/
    public static double getCheckingBalance() {
        double checking = userInfo.get("CheckingAccBalance");
        return checking;
    }


    /*-------------------------------------------------------------
    @function getSavingBalance()
    This function will get the balance for the savings account
    @return savingBalance - total amount in savings account
    -------------------------------------------------------------*/
    public static double getSavingBalance() {
        double saving = userInfo.get("SavingsAccBalance");
        return saving;
    }

    /*----------------------------------------------------------------
    @function calcCheckingDeposit()
    This function will calculate checking account balance after deposit
    @param deposit - money to add to account
    -----------------------------------------------------------------*/
    public void calcCheckingDeposit(double deposit) {
        calculate = userInfo.get("CheckingAccBalance");
        calculate += deposit;
        userInfo.put("CheckingAccBalance",calculate);
    }


    /*----------------------------------------------------------------
    @function calcSavingsDeposit()
    This function will calculate savings account balance after deposit
    @param deposit - money to add to account
    -----------------------------------------------------------------*/
    public void calcSavingsDeposit(double deposit) {
        calculate = userInfo.get("SavingsAccBalance");
        calculate += deposit;
        userInfo.put("SavingsAccBalance",calculate);
    }

    /*--------------------------------------------------------------------
    @function calcCheckingWithdrawal( double withdrawal )
    This function will calculate checking account balance after withdrawal
    @param withdrawal - money to remove from account
    @return checkingBalance - total after withdrawal
    --------------------------------------------------------------------*/
    public double calcCheckingWithdrawal(double withdrawal) {
        calculate = userInfo.get("CheckingAccBalance");
        calculate -= withdrawal;
        return userInfo.put("CheckingAccBalance",calculate);
    }

    /*--------------------------------------------------------------------
    @function calcSavingsWithdrawal( double withdrawal )
    This function will calculate checking account balance after withdrawal
    @param withdrawal - money to remove from account
    @return saving - total after withdrawal
    --------------------------------------------------------------------*/
    public double calcSavingsWithdrawal(double withdrawal) {
        calculate = userInfo.get("SavingsAccBalance");
        calculate -= withdrawal;
        return userInfo.put("SavingsAccBalance",calculate);
    }

    /*--------------------------------------------------------------------
    @function withdrawChecking()
    This function will perform withdrawal from checking account
    --------------------------------------------------------------------*/
    public void withdrawChecking(double amount) {
        if (amount < 0) {
            System.out.println("Amount cannot be negative.");
        } else if ( (getCheckingBalance() - amount) < 0) {
            System.out.println("Insufficient funds.");
        } else {
            calcCheckingWithdrawal(amount);
        }
    }

    /*--------------------------------------------------------------------
    @function withdrawSavings()
    This function will perform withdrawal from savings account
    --------------------------------------------------------------------*/
    public void withdrawSavings(double amount) {

        if (amount < 0) {
            System.out.println("Amount cannot be negative.");
        } else if ( (getSavingBalance() - amount) < 0) {
            System.out.println("Insufficient funds.");
        } else {
            calcSavingsWithdrawal(amount);
        }
    }

    /*--------------------------------------------------------------------
    @function depositChecking()
    This function will perform a deposit into a checking account
    --------------------------------------------------------------------*/
    public void depositChecking(double amount) {
        //System.out.println("Your checking account balance is: $" + userInfo.get("CheckingAccBalance"));
        //System.out.println("Enter the amount you'd like to deposit:");
        if (amount < 0) {
            System.out.println("Please enter a non-negative amount to deposit.");
            amount = input.nextDouble();
        } else {
            calcCheckingDeposit(amount);
        }
    }

    /*--------------------------------------------------------------------
    @function depositSavings()
    This function will perform a deposit into a savings account
    --------------------------------------------------------------------*/
    public void depositSavings(double amount) {
        //System.out.println("Your savings account balance is: $" + userInfo.get("SavingsAccBalance"));
        //System.out.println("Enter the amount you'd like to deposit:");
        if (amount < 0) {
            System.out.println("Please enter a non-negative amount to deposit.");
            amount = input.nextDouble();
        } else {
            calcSavingsDeposit(amount);
        }
    }

}
