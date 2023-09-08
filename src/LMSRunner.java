/*
* Riane Torres
* Co. No. 14835
* 9/7/23
* CEN 3024C
*  */
/*Brief Function of LMSRunner class
Provides user functions: show and choose options
*/

import java.awt.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class LMSRunner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<LMS> books = new ArrayList<LMS>();

        System.out.println("Available Options:");
        System.out.println("1 - Add/Check-in Book");
        System.out.println("2 - Remove/Check-out Book");
        System.out.println("3 - Check Booklist");
        System.out.println("/n Please choose an option: ");
        int optionChoice = input.nextInt();

        //let user choose option from menu
        switch (optionChoice) {
            case 1:
                System.out.println("Adding/Checking-in Book");
                System.out.println("/nAdd ID No.: ");
                String addBookId = input.nextLine();
                System.out.println("Add Title: ");
                String addBookTitle = input.nextLine();
                System.out.println("Add author: ");
                String addBookAuthor = input.nextLine();

                System.out.println("Adding/Checking-in: Successful");
                break;
            case 2:
                System.out.println("Removing/Checking-out Book");
                System.out.println("/nEnter ID No.: ");
                String removeBook = input.next();
                //Add in id object thingy here
                System.out.println("Removing/Checking out: Successful");
                break;
            case 3:
                System.out.println("Checking current booklist");
                System.out.println(books.toString());
                //Just going to make case 3 automatically check the list
                break;
            default:
                System.out.println("Error: Not an option. Please choose an option on the menu.");
        }
    }
}
