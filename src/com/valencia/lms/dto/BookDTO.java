/*
 * Riane Torres
 * Co. No. 14835
 * 11/2/23
 * CEN 3024C
 *  */
/* Brief Function of BookDTO class
 * Book Data Transfer Object (BookDTO)
 * This is now taking over the function of the Book Class from earlier
 * Create the details of the books as private strings
 * Create the book's objects (barcode, title, etc.)
 * The objects are then passed between the Mainframe class, both controller classes, and DAO layer.
 * Used as a medium to transfer the core objects (book details data)
 * Is the Model
 * */

package com.valencia.lms.dto;
import java.util.Date;

public class BookDTO {
    //Ask
    private static final long serialVersionUID = 417271472650760004L;

    private String barcode;
    private String title;
    private String author;
    private String genre;
    private String checkedOutStatus ="Checked-in";
    private Date dueDate;

    public BookDTO(String barcode, String title, String author, String genre,
                String checkedOutStatus, Date bookDueDate) {
        this.barcode = barcode;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.checkedOutStatus = checkedOutStatus;
        this.dueDate = bookDueDate;
    }

    public BookDTO() {

    }

    public String getBarcode() {
        return barcode;
    }

    public String setBarcode(String barcode) {
        this.barcode = barcode;
        return barcode;
    }

    public String getTitle() {
        return title;
    }

    public String setTitle(String title) {
        this.title = title;
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String setAuthor(String author) {
        this.author = author;
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String setGenre(String genre) {
        this.genre = genre;
        return genre;
    }

    public String getCheckedOutStatus() {
        return checkedOutStatus;
    }

    public String setCheckedOutStatus(String checkedOutStatus) {
        this.checkedOutStatus = checkedOutStatus;
        return checkedOutStatus;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return dueDate;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("").append(barcode);
        sb.append(", ").append(title);
        sb.append(", ").append(author);
        sb.append(", ").append(genre);
        sb.append(", ").append(checkedOutStatus);
        sb.append(", ").append(dueDate);
        return sb.toString();
    }
}
