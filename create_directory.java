/*
 * @name Lindsey Baccellieri
 * @project Course Directory - create directory
 * @note This class houses and changes the directory hash table used for course directory
 */

import java.util.Hashtable;
import java.util.Scanner;

abstract class create_directory {
    static Scanner input = new Scanner(System.in);
    static private Hashtable<Integer,String> directory = new Hashtable<>();         // storing course iD and name

    /*
    * @function getHashTable
    * @note allows access to directory with courses
    * @return returns the directory and its contents
    */
    static public Hashtable<Integer,String> getHashTable() {
        return directory;
    }

    /*
     * @function directoryEmpty
     * @return returns whether the directory is empty or not
     */
    static public boolean directoryEmpty() {
        if (directory.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * @function input_course
     * @param courseNum, courseName
     * @note method will take a course ID number and name, and store them as key/value into hash table
     * directory
     */
    static public void input_course(int courseNum, String courseName) {
        directory.put(courseNum,courseName);
        System.out.println("Course: " + courseNum + ", " + courseName + " added successfully.");
    }


    /*
     * @function input_course
     * @param search_num
     * @note method will use search number to search for the key and let the user know if this course ID
     * exists in the directory
     */
    static public void search_course_num(int search_num) {
        if (directory.containsKey(search_num)) {
            System.out.println("Course found. \nCourse number: " + search_num
                    + "\nCourse name: " + directory.get(search_num));
        } else {
            System.out.println("Course number not found in directory.");
        }
    }

    /*
     * @function input_course
     * @param search_name
     * @note method will use search name to search for the value let the user know if this course name
     * exists in the directory
     */
    static public void search_course_name(String search_name) {
        int find_num = 0;
        if (directory.containsValue(search_name)) {
            for (int value : directory.keySet()) {
                if (search_name.equals(directory.get(value))) {
                    find_num = value;
                }
            }
            System.out.println("Course found. \nCourse Number: " + find_num
                    + "\nCourse name: " + directory.get(find_num));
        } else {
            System.out.println("Course name not found in directory.");
        }
    }

    /*
     * @function update_course_num
     * @param numberID, orig_definition
     * @note method will use a new number key to replace the old key value in hash table
     */
    static public void update_course_num(int numberID,String orig_definition) {
        int new_num;
        System.out.print("\nWhat is the new course number?" );

        try {
            new_num = input.nextInt();
            directory.remove(numberID);
            directory.put(new_num, orig_definition);
            System.out.println("Course number updated.");
        } catch (Exception invalidNum) {
            System.out.println("Invalid number.");
            input.next();
        }
    }

    /*
     * @function update_course_name
     * @param ID_num, new_definition
     * @note method will use the same key and update the old value only (Changing the name)
     */
    static public void update_course_name(int ID_num,String new_definition) {
        String old_definition = directory.get(ID_num);
        directory.replace(ID_num,old_definition,new_definition);
        System.out.println("Course name updated.");
    }


    /*
     * @function remove_course
     * @param course_num
     * @note method completely remove course ID and name by removing key/value pair
     */
    static public void remove_course(int course_num) {
        if (directory.containsKey(course_num)) {
            directory.remove(course_num);
            System.out.println("\nCourse Number " + course_num + " removed.");
        } else {
            System.out.println("Course number: " + course_num + " not found in directory");
        }
    }


}