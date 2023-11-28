/**
* Riane Torres
* Co. No. 14835
* 11/27/23
* CEN 3024C
*
* Brief Function of LMSRunner class
* Provides show and choose options for the user.
* User's input the book details that'll be entered into the arraylist.
* This main class is to let the user add, check-in, check-out, remove, and view booklist.
*/

import java.util.Scanner;
import java.io.*;

@Deprecated
public class LMSRunner {
    public static void main(String[] args) throws IOException {
        //create a new LMS object
        LMS lms = new LMS();
        //creating a new Book object
        Scanner input = new Scanner(System.in);

        int optionChoice = 0;

        //let user choose option from menu
        while (optionChoice != 6) {
            System.out.println("Available Options:");
            System.out.println("1 - Check-In Book");
            System.out.println("2 - Add Book");
            System.out.println("3 - Check-Out Book");
            System.out.println("4 - Remove Book");
            System.out.println("5 - View Current Booklist");
            System.out.println("6 - Exit");
            System.out.println("\nPlease choose an option: ");
            Book book = new Book();
            String barcode = "";
            String title = "";
            String author = "";
            String genre = "";
            String titleOrBarcode ="";
            optionChoice = input.nextInt();

            switch (optionChoice) {
                case 1 -> {
                    System.out.println("Checking in book");
                    input.nextLine();
                    System.out.println("Enter title");
                    title = input.nextLine();
                    lms.checkInBookStatus(title);
                }

                case 2 -> {
                    System.out.println("Adding to Booklist");
                    input.nextLine();
                    System.out.println("Enter file name: ");
                    String fileName = input.nextLine();

                    File file = new File(fileName);

                    if (!file.exists()) {
                        System.out.println("File: " + fileName + " doesn't exist.");
                        return;
                    } else {
                        System.out.println("Add barcode");
                        barcode = input.nextLine();
                        //input.nextLine();
                        System.out.println("Add title");
                        title = input.nextLine();
                        System.out.println("Add author");
                        author = input.nextLine();
                        System.out.println("Add genre");
                        genre = input.nextLine();

                        book.setBarcode(barcode);
                        book.setTitle(title);
                        book.setAuthor(author);
                        book.setGenre(genre);

                        lms.addBook(book);
                    }

                }

                case 3 -> {
                    System.out.println("Checking out book");
                    input.nextLine();
                    System.out.println("Enter bar code or title: ");
                    titleOrBarcode = input.nextLine();

                    lms.checkOutBook(titleOrBarcode);
                }

                case 4 -> {
                    System.out.println("Remove from Booklist");
                    System.out.println("Enter Barcode or Title");
                    input.skip("\n");
                    titleOrBarcode = input.nextLine();

                    lms.removeBook(titleOrBarcode);
                    lms.saveBookListToFile(false);
                }
                case 5 -> {
                    lms.viewBooklistFromFile("bookfile.txt");
                    System.out.println("Viewing current booklist:");
                }
                case 6 -> {
                    System.out.println("Exiting....");
                    break;
                }
                default -> System.out.println("Error: Not an option. Please choose an option on the menu.");
            }
        }
        input.close();
    }
}
