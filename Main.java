/*
 * @name Lindsey Baccellieri
 * @project Course Directory
 * @note This program will create a directory and allow the user to add, edit, delete courses by
 * course ID numbers and course names.
 */

import java.util.Scanner;
public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        // variables
        int option = 9;         // to move through menu
        directoryEdit course_directory = new directoryEdit();       // will store course_directory and items

        // creating a text block
        String menu = """
            1 - Add New Course
            2 - Search for a Course
            3 - Update a Course
            4 - Remove a Course
            0 - Exit Program
                """;

        System.out.println("Welcome to the Course Directory!");

        // Loop until user decides to exit the menu/program
        while (option != 0) {
            System.out.println("\n" + menu);
            System.out.println("Enter a Menu Option Number: ");
            try {
                option = input.nextInt();
            } catch (Exception invalid) {
                // If user doesn't enter an INT, this loop repeats
                System.out.println("Please enter a valid number.");
                input.next();
            }

            // Action based on menu selection
            switch (option) {
                case 1:
                    directoryEdit.input_new();
                    System.out.println("\nMain Menu \nMake a selection: ");
                    break;
                case 2:
                    directoryEdit.search_directory();
                    System.out.println("\nMain Menu \nMake a selection: ");
                    break;
                case 3:
                    directoryEdit.update_course();
                    System.out.println("\nMain Menu \nMake a selection: ");
                    break;
                case 4:
                    directoryEdit.remove();
                    System.out.println("\nMain Menu \nMake a selection: ");
                    break;
                case 0:
                    break;
            }
        }
        System.out.println("Thank you for using the Course Directory program");
    }

}