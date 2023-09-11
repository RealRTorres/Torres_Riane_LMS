/*
* Riane Torres
* Co. No. 14835
* 9/7/23
* CEN 3024C
*  */
/*Brief Function of LMSRunner class
Provides user functions: show and choose options
*/

import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LMSRunner {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new FileWriter("bookfile.txt", true));

        System.out.println("Available Options:");
        System.out.println("1 - Add/Check-in Book");
        System.out.println("2 - Remove/Check-out Book");
        System.out.println("3 - Check Booklist");
        System.out.println("4 - Exit anytime");
        System.out.println("\n Please choose an option: ");
        int optionChoice = input.nextInt();

        //let user choose option from menu
        switch (optionChoice) {
            case 1:
                System.out.println("Adding/Checking-in Book");
                System.out.println("Add ID No.");
                int bookID = input.nextInt();
                pw.println(bookID +", ");
                input.nextLine();
                System.out.println("Add Title");
                String bookTitle = input.nextLine();
                pw.println(bookTitle + ", ");
                System.out.println("Add author");
                String bookAuthor = input.nextLine();
                pw.println(bookAuthor);
                pw.close();

                LMS bookUI = new LMS(bookID, bookTitle, bookAuthor);
                bookUI.addBookInfo();
                System.out.println("Adding/Checking-in: Successful");
                break;
            case 2:
                System.out.println("Removing/Checking-out Book");
                System.out.println("Enter ID No.: ");
                bookID = input.nextInt();

                //LMS obj = new LMS();
                //LMS.removeBook("bookfile.txt", bookID);
                //LMS.removeBookInfo();
                System.out.println("Removing/Checking out: Successful");
                break;
            case 3:
                System.out.println("Checking current booklist");
                String data = LMS.checkBooklist("bookfile.txt");
                System.out.println(data);
                //Just going to make case 3 automatically check the list
                break;
            case 4:
                return;
            default:
                System.out.println("Error: Not an option. Please choose an option on the menu.");
        }
    }
}
