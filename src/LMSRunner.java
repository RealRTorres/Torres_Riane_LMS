/*
* Riane Torres
* Co. No. 14835
* 9/7/23
* CEN 3024C
*  */
/*Brief Function of LMSRunner class
Provides user functions: show and choose options
User's input the book details that'll be entered into the arraylist
This main class is to let the user add, check-in, check-out, remove, and view booklist

While everything is functional, there's one problem with the adding in
When you add in a book, the status is set to null and the current date
This is remedied as long as you check in the book after adding it the status and due dates are normal
Needs to be fixed up by the next phase

Another problem is if the bookfile.txt is deleted, then it's not possible to write a file
Need to fix that

Also, the menu needs rearranging with this weird order
*/

import java.util.Scanner;
import java.io.*;

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

                    //create a file object for the user to open
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

                        //put the user input into the object and setters
                        book.setBarcode(barcode);
                        book.setTitle(title);
                        book.setAuthor(author);
                        book.setGenre(genre);
                        //add user input book into the books array in LMS class
                        lms.addBook(book);
                    }

                }

                case 3 -> {
                    System.out.println("Checking out book");
                    input.nextLine();
                    System.out.println("Enter bar code or title: ");
                    titleOrBarcode = input.nextLine();
                    //Checks out the book and changes status to "checked-out"
                    lms.checkOutBook(titleOrBarcode);
                }

                case 4 -> {
                    System.out.println("Remove from Booklist");
                    System.out.println("Enter Barcode or Title");
                    input.skip("\n");
                    titleOrBarcode = input.nextLine();

                    //remove book from the array
                    lms.removeBook(titleOrBarcode);
                    //overwrite bookfile.txt that without the removed book
                    lms.saveBookListToFile(false);
                }
                case 5 -> {
                    //Checks current books on the booklist
                    lms.viewBooklistFromFile("bookfile.txt");
                    System.out.println("Viewing current booklist:");
                }
                case 6 -> {
                    //Exit program option
                    System.out.println("Exiting....");
                    break;
                }
                default -> System.out.println("Error: Not an option. Please choose an option on the menu.");
            }
        }
        input.close();
    }
}
