/**
 * Riane Torres
 * Co. No. 14835
 * 11/27/23
 * CEN 3024C
 *
 * Brief Function of Book class
 * Provides Helps organize my code as a place to store all the information for the books.
 * Formats the strings and dates.
 * And converts dates into strings.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Book {
    private String barcode;
    private String title;
    private String author;
    private String genre;
    private String checkedOutStatus ="Checked-in";
    private Date dueDate; // = new Date();


    public Book() {

    }

    public Book(String barcode, String title, String author, String genre,
                String checkedOutStatus, Date bookDueDate) {
        this.barcode = barcode;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.checkedOutStatus = checkedOutStatus;
        this.dueDate = bookDueDate;
    }

    //constructors for the books
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCheckedOutStatus() {
        return checkedOutStatus;
    }

    public void setCheckedOutStatus(String checkedOutStatus) {
        this.checkedOutStatus = checkedOutStatus;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("\n");
        sb.append(",").append(barcode);
        sb.append(",").append(title);
        sb.append(",").append(author);
        sb.append(",").append(genre);
        sb.append(",").append(checkedOutStatus);
        sb.append(",").append(dueDate);
        sb.append("\n");

        return sb.toString();
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(barcode);
        sb.append(",").append(title);
        sb.append(",").append(author);
        sb.append(",").append(genre);
        sb.append(",").append(checkedOutStatus);
        if (dueDate != null) {
            sb.append(",").append(dateToString(dueDate));
        }
        else {
            sb.append(",null");
        }
        return sb.toString();
    }

    public String dateToString(Date date) {
        if (date == null) {
            return "null";
        } else {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(date);
            return formattedDate;
        }
    }

}