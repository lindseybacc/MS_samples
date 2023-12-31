/*
 @name Lindsey Baccellieri
 @file Main.java
 @note This is the file to run the ATM program.
*/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        int useATM = 0;

        // Creating an object based off ATMmenu class to utilize
        ATMmenu user = new ATMmenu();
        // Verifying user PIN
        user.userType();
        user.verifyUser();

        // Whether to continue or exit.
        System.out.println("Would you like to perform a transaction?");
        System.out.println("1 = Yes");
        System.out.println("2 = No");
        while (!valid) {
            try {
                useATM = input.nextInt();
                valid = true;
            } catch (Exception invalidInt) {
                System.out.println("You made an invalid entry.");
            }
        }

        // continue going through ATM
        while (useATM == 1) {
            user.chooseAccount();
            System.out.println("Would you like to perform another transaction? Enter an option.");
            System.out.println("1 = Yes");
            System.out.println("2 = No");

            try {
                useATM = input.nextInt();
            } catch (Exception invalidInt) {
                System.out.println("You made an invalid entry");
                input.next();
            }
        }
        System.out.println("Thank you for using the ATM machine.");
    }
}