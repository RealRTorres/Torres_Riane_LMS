/*
 * Riane Torres
 * Co. No. 14835
 * 9/7/23
 * CEN 3024C
 *  */
/* Brief Function of LMS class
 * This is where the getters and setters for the objects and methods will go
 * Private the variables in the parameters so no one changes it
 * Public the objects
 * This is where the methods for the menu options are
 *
 * Something in this class is causing the status to be null and due date as today's current date when adding a book
 * It's fixed once you check in a book, but it's still a nuisance to check in as you add a book
 *
 * Probably best to rearrange the methods, if it doesn't mess up the program, again
 * */

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class LMS {
    private ArrayList<Book> books = new ArrayList<>();
    final static String FILENAME = "bookfile.txt";
    final static String CHECKED_IN = "Checked-in";
    final static String CHECKED_OUT = "Checked-out";

    //constructor for the readFromFile() method
    public LMS() throws IOException {
        // Retrieve any existing book list from the file
        this.readFromFile(FILENAME);
    }

     /**
     * readFromFile method
     * purpose is to read every line of text that is in bookfile.txt every time LMS program is used
     * Uses a stringtokenizer to pass the integer and strings
     * Uses a try and catch for the IOException and e
     * @param filepath
     * arguments: stringDate, book
     * Returns either an error message if the file can't be read or create book objects
     */
    public void readFromFile(String filepath)  {
       String currentLine = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            currentLine = reader.readLine();

            while (currentLine != null) {
                StringTokenizer st = new StringTokenizer(currentLine, ",");
                String barcode = st.nextToken();
                String bookTitle = st.nextToken();
                String bookAuthor = st.nextToken();
                String bookGenre = st.nextToken();

                String bookCheckedOutStatus = st.nextToken();

                //converts the string value "2023-11-05" from the file to Date object
                Date bookDueDate = null;
                String strDate = st.nextToken();
                if (strDate != null || strDate.trim().length() > 0) {
                    strDate = strDate.trim();
                    if (!strDate.equalsIgnoreCase("null")) {
                        LocalDate initialDate = LocalDate.parse(strDate);
                        bookDueDate = java.util.Date.from(initialDate.atStartOfDay()
                                .atZone(ZoneId.systemDefault())
                                .toInstant());
                    }
                }
                Book book = new Book(barcode, bookTitle, bookAuthor, bookGenre, bookCheckedOutStatus, bookDueDate);
                // initializes the book list with values read from the file
                this.books.add(book);
                currentLine = reader.readLine();
            }

        } catch(IOException e){
            System.err.println("Error encounter,msg: " + e.getMessage());
        }
    }

    /**
     * addBookInfo method
     * adds user's input of ID no./now barcode, title, author, and genre into the idList and arraylist
     * calls the toCSV in the Book class to print out the user's input
     * also invokes the saveBookToFile method to write the text into bookfile.txt
     * Arguments: newBook
     * @param newBook
     * returns whatever the user inputted by adding it in the arraylist
     */
    public void addBook(Book newBook) {
        this.books.add(newBook);
        System.out.println("Added: " + newBook.toCSV());
        System.out.println("Adding...Successful\n");
        this.saveBookToFile(newBook);
    }

    /**
     * saveBooktoFile method
     * Saves the book to the file and append to the last line
     * @param book
     * Arguments: n/a
     * Returns user's input text into the file
     */
    private void saveBookToFile(Book book)  {
        try {
            PrintWriter pWriter = new PrintWriter(new FileWriter(FILENAME, true));
            pWriter.println(book.toCSV());
            pWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

      /**
     * saveToFile method
     * overwrites the bookfile.txt whenever adding/removing a book
     * It saves the book list as comma seperated values
     * uses the getters to get the book details
     * Arguments: n/a
     * @param append
     * Returns the overwritten text file
     */
    public void saveBookListToFile(boolean append)  {
        PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new FileWriter(FILENAME, append));
            for(Book book : this.books) {
                pWriter.println(book.toCSV());
            }
            pWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * checkInBookStatus method
     * Checks in a book from the user via book title
     * Arguments:CHECKED_IN
     * @param titleOrBarcode
     * Returns bookCheckedOutStatus as "checked-out",
     *  save and overwrites the file to update the status,
     *  print out confirmation and current booklist
     */

    public void checkInBookStatus(String titleOrBarcode) {
        titleOrBarcode = titleOrBarcode.trim();
        for(Book book : this.books) {
            if (book.getBarcode().equalsIgnoreCase(titleOrBarcode) ||
                    book.getTitle().equalsIgnoreCase(titleOrBarcode))
            {
                book.setCheckedOutStatus(CHECKED_IN);
                book.setDueDate(null);
                saveBookListToFile(false);
                System.out.println("Checking In " + book.toCSV());
                break;
            }
        }
        printBookList();

    }

    /**
     * checkOutBook method
     * Changes book status to check out via user's input title or barcode
     * Also calculates the due date
     * Arguments: titleOrBarcode, CHECKED_OUT, Calendar.DATE, 28
     * @param titleOrBarcode
     * returns the claculated due date and updated status
     */
    public void checkOutBook(String titleOrBarcode){
        titleOrBarcode = titleOrBarcode.trim();
        for(Book book : this.books) {
            if (book.getBarcode().equalsIgnoreCase(titleOrBarcode) ||
                    book.getTitle().equalsIgnoreCase(titleOrBarcode))
            {
                if (book.getCheckedOutStatus().equalsIgnoreCase(CHECKED_OUT)) {
                    System.out.println(book.getTitle() + " is not available.\n");
                }
                else {
                    book.setCheckedOutStatus(CHECKED_OUT);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.add(Calendar.DATE, 28);
                    Date dueDate = calendar.getTime();
                    book.setDueDate(dueDate);
                    saveBookListToFile(false);
                    System.out.println("Checking out " + book.toCSV());
                    printBookList();
                }
                break;
            }
        }
    }

    /**
     * removeBookInfo method
     * Removes barcode, title, author, genre from the arraylist
     * Arguments: titleOrBarcode
     * @param titleOrBarcode
     * @return
     * Returns by deleting the user's input from the arrays
     * @throws IOException
     */
    public void removeBook(String titleOrBarcode) throws IOException {
        for(Book book : this.books) {
            if (book.getBarcode().equalsIgnoreCase(titleOrBarcode) ||
                    book.getTitle().equalsIgnoreCase(titleOrBarcode))
            {
                this.books.remove(book);
                System.out.println("Removed: " +  book.toCSV());
                System.out.println("Removing: Successful\n");
                break;
            }
        }
        printBookList();
    }

    /**
     * printBookList method
     * Print the current values of the booklist (barcode, title, author, genre, status, and due date)
        * that's currently in the memory
     * Arguments: n/a
     * Returns the values printed out via CSV format
     */
    public void printBookList() {
        for(Book book : this.books) {
            System.out.println(book.toCSV());
        }
    }

    /**
     * viewBooklistFromFile method
     * Prints out current booklist in the text written in bookfile.txt
     * arguments: filepath
     * @param filepath
     * returns the values/book details via CSV format
     */
    public void viewBooklistFromFile(String filepath) {
        System.out.println("Printing..." );
        this.books = new ArrayList<>();
        this.readFromFile(filepath);
        for(Book book : this.books) {
            System.out.println(book.toCSV());
        }
        System.out.println("\n");
    }
}