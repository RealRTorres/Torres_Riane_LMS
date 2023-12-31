/**
 * Riane Torres
 * Co. No. 14835
 * 11/27/23
 * CEN 3024C
 *
 * Brief Function of BookDaoImpl class
 * Book Data Access Object Implementation (BookDaoImpl)
 * This is an implementation for the MySQL database.
 * In case if we ever need to switch to another database, we don't need to change the MainFrame class.
 * It's a generic interface we can use.
 *  */

package com.valencia.lms.dao;

import com.valencia.lms.LMSConstant;
import com.valencia.lms.dto.BookDTO;


import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookDaoImpl implements BookDao {

    /**
     * getConnection method
     * Establish connection and call upon the
     * connect() method in the BookDBConnection class.
     * Arguments: BookDBConnection
     * @return conn Connection to SQLite Database.
     */
    public Connection getConnection() {
        Connection conn;
        try {
            conn = BookDBConnection.connect();
            System.out.println("Connection Success");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * updateData Method
     * No method inside, but helps to override stuff.
     * Also used for implementation from for updateData(String bookstatus, String barcodeOrTitle) in BookDao class
     * Don't delete or else it doesn't work.
     * Arguments: bookstatus, barcodeOrTitle
     * @param bookstatus Implements string.
     * @param barcodeOrTitle Implements string.
     * */
    @Override
    public void updateData(String bookstatus, String barcodeOrTitle) {

    }

    /**
     * checkInBook method
     * Uses UPDATE query to change checkedOutStatus to "Checked-In".
     * Having the LIKE statement is what makes it work with titles now.
     * Having multiple words in a string will not make the method work without the LIKE.
     * Arguments: title
     * @param title String containing the book's title.
     * @return data Updates book's checkout status and Due date.
     */
    @Override
    public BookDTO checkInBook(String title) {
        BookDTO data;
        Connection con;
        try {
            con = getConnection();
            String query = "UPDATE book\n" +
                    "SET CheckedOutStatus=?, DueDate = ? \n" +
                    "WHERE Title LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, LMSConstant.CHECKED_IN);
            ps.setNull(2, Types.DATE);
            ps.setString(3, title);

            ps.executeUpdate();
            data = getBookByTitle(title);
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    /**
     * checkOutBook method
     * Uses UPDATE query to change checkedOutStatus to "Checked-Out".
     * Decided to create a Calendar object to calculate date instead of DATEADD() because that syntax isn't working for me.
     * Arguments: title
     * @param title String containing the book's title.
     * @return data Updates the book's checkout status and due date associated with the respective title.
     */
    @Override
    public BookDTO checkOutBook(String title) {
        BookDTO data;
        Connection con;
        try {
            con = getConnection();
                Date now = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                calendar.add(Calendar.DATE, 28);
                Date dd = calendar.getTime();
                java.sql.Date dueDate = new java.sql.Date(dd.getTime());
                String query = "UPDATE book\n" +
                        "SET CheckedOutStatus=?, DueDate = ?\n" +
                        "WHERE Title LIKE ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, LMSConstant.CHECKED_OUT);
                ps.setDate(2, dueDate);
                ps.setString(3, title);

                ps.executeUpdate();
                data = getBookByTitle(title); // get the updated book value
                con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    /**
     * getBookByTitleOrBarcode method
     * Uses SELECT * to search through the database to find the book by barcode or title.
     * Mainly useful for the removeBook() method.
     * Arguments: titleOrBarcode
     * @param titleOrBarcode String containing the book's barcode or title
     * @return data The book's details that associated with the respective barcode or title.
     */
    @Override
    public BookDTO getBookByTitleOrBarcode(String titleOrBarcode) {
        BookDTO data = null;
        String query = "SELECT * FROM book WHERE barcode =? or title LIKE ?";;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,titleOrBarcode);
            ps.setString(2,titleOrBarcode);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();;

            while (rs.next()) {
                data = new BookDTO();
                data.setBarcode(rs.getString("barcode"));
                data.setTitle(rs.getString("title"));
                data.setGenre(rs.getString("genre"));
                data.setAuthor(rs.getString("author"));
                data.setCheckedOutStatus(rs.getString("checkedoutstatus"));
                data.setDueDate(rs.getDate("duedate"));

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * addBook method
     * Use INSERT query to add book into the LMS MySQL Database.
     * Arguments: BookDTO
     * @param bookdto Object of the book.
     */
    @Override
    public void addBook(BookDTO bookdto) {
        Connection con = null;
        String query = "INSERT INTO book(barcode, title, author, genre, CheckedOutStatus) VALUES(?, ?, ?, ?, ?)";
        try {
            con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, bookdto.getBarcode());
            ps.setString(2, bookdto.getTitle());
            ps.setString(3, bookdto.getAuthor());
            ps.setString(4, bookdto.getGenre());
            ps.setString(5, "Checked-In");
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * removeBook method
     * First goes through the getBookByTitleOrBarcode() method to search through the database
     * for matching barcode or title strings.
     * Uses a boolean data to indicate a book was deleted.
     * Then uses the DELETE query to remove a book from the LMS MySQL database.
     * Arguments: titleOrBarcode
     * @param titleOrBarcode String containing the book's barcode or title.
     * @return data The book's details that associated with the respective barcode or title to be removed.
     */
    public Boolean removeBook(String titleOrBarcode) {
        Boolean data = Boolean.FALSE;
        Connection con = getConnection();
        try {
            BookDTO bookdto = getBookByTitleOrBarcode(titleOrBarcode);
            if (bookdto != null) {
                Statement stmt = con.createStatement();
                String sql = "DELETE FROM BOOK " +
                        "WHERE barcode = " + bookdto.getBarcode();
                stmt.executeUpdate(sql);
                data = Boolean.TRUE;
            }
         con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * viewBookist method
     * Create an arraylist of the data in the database and display as strings.
     * Use SELECT * query to display the whole booklist currently in the LMS MySQL database.
     * Arguments: viewBooklist() implicit argument, the "()" part.
     * @return data The book's details.
     */
    @Override
    public List<BookDTO>  viewBooklist() {
        List<BookDTO> data = new ArrayList();
        String query = "SELECT * FROM book";
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                BookDTO book = new BookDTO();
                book.setBarcode(rs.getString("barcode"));
                book.setTitle(rs.getString("title"));
                book.setGenre(rs.getString("genre"));
                book.setAuthor(rs.getString("author"));
                book.setCheckedOutStatus(rs.getString("checkedoutstatus"));
                book.setDueDate(rs.getDate("duedate"));
                data.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * getBookByTitle method
     * Need it for the checkInBook and checkOutBook methods.
     * Helps with using SELECT * to search through title column in the database for the matching string input.
     * Returns back the book data that'll be checked-in/out.
     * Arguments: title
     * @param title String containing title.
     * @return data The book's details associated with the title input.
     */
    @Override
    public BookDTO getBookByTitle(String title) {
        BookDTO data = null;
        String query = "SELECT * FROM book WHERE  title like ?";;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,title);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();;

            while (rs.next()) {
                data = new BookDTO();
                data.setBarcode(rs.getString("barcode"));
                data.setTitle(rs.getString("title"));
                data.setGenre(rs.getString("genre"));
                data.setAuthor(rs.getString("author"));
                data.setCheckedOutStatus(rs.getString("checkedoutstatus"));
                data.setDueDate(rs.getDate("duedate"));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
