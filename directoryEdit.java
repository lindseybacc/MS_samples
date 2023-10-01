/*
 * @name Lindsey Baccellieri
 * @project Course Directory - directory edit
 * @note This class gives options for specific edits to course directory
 */

import java.util.Hashtable;
import java.util.Scanner;
class directoryEdit extends create_directory {
    static Scanner input = new Scanner(System.in);

    /*
     * @function search_directory
     * @note method will allow user to search directory by either name or course id
     * it will return the name or course ID number depending on search method used
     */
    static void search_directory() {
        // variables
        int selecion = 0;               // to navigate choices
        int course_num_search = 0;      // will store course ID numbers user enters
        String search_name;             // store course name

        System.out.println("\nEnter the way you want to search \n1 - Course Number \n2 - Course Name ");

        try {
            selecion = input.nextInt();
        } catch (Exception invalidEntry) {
            System.out.println("You made an invalid choice");
            input.next();
        }
        switch (selecion) {
            case 1:
                System.out.println("Enter course number: ");
                try {
                    course_num_search = input.nextInt();
                } catch (Exception invalidInt){
                    System.out.println("That wasn't a valid number");
                }
                // call method to search by course ID number
                create_directory.search_course_num(course_num_search);
                break;
            case 2:
                System.out.println("Enter the course name: ");
                search_name = input.next();
                // call method to search by course ID name
                create_directory.search_course_name(search_name);
        }
    }

    /*
     * @function input_new
     * @note method will allow user to create a course ID and corresponding name to store in directory
     */
    static public void input_new() {
        boolean valid = false;              // to ensure a proper entry was made
        int course_num = 0;                 // for users to create course ID number
        String course_name;                 // for users to enter course name

        while (!valid) {
            System.out.println("Welcome to: Add New Course");
            System.out.print("Enter the course number: ");
            // validating numerical entry for key value
            try {
                course_num = input.nextInt();
                valid = true;
            } catch (Exception invalidCourse){
                System.out.println("Invalid - Course number can only consist of numbers.");
                input.next();
            }
        }
        System.out.print("Enter the course name: ");
        course_name = input.next();

        // calling function from create_directory to add into our directory
        create_directory.input_course(course_num,course_name);
    }

    /*
     * @function input_new
     * @note method will allow user to update a course ID and/or name existing in directory
     */
    static public void update_course() {
        int selection = 9;                  // to choose how to update
        int update_num = 0;                 // storing Course ID number to edit
        boolean check_directory;            // See if a course exists in order to continue editing
        String update_name;                 // storing update_name

        // Can't update directory if it is empty
        check_directory = create_directory.directoryEmpty();
        if (!check_directory) {
            System.out.println("What do you want to update? \n1 - Course Number ID \n2 - Course Name");
            try {
                selection = input.nextInt();
                switch (selection) {
                    case 1:
                        System.out.print("Enter the ID number to update: ");
                        try {
                            update_num = input.nextInt();
                            // calling function to update a course ID number
                            create_directory.update_course_num(update_num, create_directory.getHashTable().get(update_num));
                        } catch (Exception invalidInt) {
                            System.out.println("That ID number is invalid");
                        }
                        break;

                    case 2:
                        System.out.print("Enter existing ID number: ");
                        try {
                            update_num = input.nextInt();
                        } catch (Exception invalidInt) {
                            System.out.println("ID Number invalid");
                        }
                        System.out.println("Enter the new course name: ");
                        update_name = input.next();
                        // calling function to update a course ID name
                        create_directory.update_course_name(update_num, update_name);
                        break;
                    default:
                        System.out.println("No information entered.");
                        break;
                }
            } catch (Exception invalidInt) {
                System.out.println("You did not enter a valid number: ");
                input.next();
            }
            System.out.println("\nDirectory Update Complete.");
        } else {
            System.out.println("There is nothing in the directory to update.");
        }
    }

    /*
     * @function input_new
     * @note method will allow user to remove a course ID and name existing in directory
     */
    static public void remove() {
        boolean check_directory;            // to see if course exists in directory
        int course_num = 0;                 // for user to enter course ID of their choice

        // Can't update directory if it is empty
        check_directory = create_directory.directoryEmpty();
        if (!check_directory) {
            System.out.print("Enter the course ID number to remove: ");
            try {
                course_num = input.nextInt();
                // calling function to delete course from directory
                directoryEdit.remove_course(course_num);
            } catch (Exception invalidInt) {
                System.out.println("ID Number invalid");
            }
        } else {
            System.out.println("There is nothing in the directory to remove");
        }

    }
}
