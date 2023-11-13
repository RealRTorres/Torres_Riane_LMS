/* Riane Torres
 * Co. No. 14835
 * 11/12/23
 * CEN 3024C
 *  */
/* Brief Function of BookDaoImpl class
 * Book Data Access Object Implementation (BookDaoImpl)
 * This is an implementation for the MySQL database.
 * In case if we ever need to swtich to another database,
 *  we don't need to change the MainFrame class.
 * It's a generic interface we can use.
 *  */

package com.valencia.lms.dao;

import com.valencia.lms.dto.BookDTO;

import java.sql.*;

//TODO Need to move some of the method stuff into the BookDao class.
//     And clean up this code/class
public class BookDaoImpl implements BookDao {

    /**
     * getConnection method
     * Establish connection and call upon the
     *  connect() method in the BookDBConnection class
     * Arguments: BookDBConnection
     */
    private Connection getConnection() {
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
     * viewBookist method
     * Use SELECT * query to display the whole booklist
     *  currently in the LMS MySQL database
     * Arguments: con
     */
    @Override
    public String viewBooklist() {
        String lmsBookData = "";
        String query = "SELECT * FROM book";
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                for (int j = 1; j <=1; j++) {
                    lmsBookData += result.getString(j) + "\n";
                    for (int i = 1; i <= 6; i++) {
                        lmsBookData += result.getString(i) + ", ";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lmsBookData;
    }

    @Override
    public void updateData(String bookstatus, String barCodeOrTitle) {

    }

    /**
     * checkInBook method
     * Uses UPDATE query to change checkedOutStatus to "Checked-In"
     * Arguments: con
     */
    //TODO Fix up this query, don't think this'll work.
    @Override
    public void checkInBook(String bookstatus, String barCodeOrTitle) {
        try {
            Connection con = getConnection(); //DriverManager.getConnection(url, uname, password);
            String query ="UPDATE book SET (checkedOutStatus = 'Checked-In' AND duedate = 'null') WHERE  checkedOutStatus = 'Checked-Out'";
            PreparedStatement st = con.prepareStatement(query);

            st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * addBook method
     * Use INSERT query to add book into the LMS MySQL Database
     * Arguments: con
     */

    //FIXME Fix up the setString(), cause it ain't working.
    @Override
    public void addBook(BookDTO bookdto ) {
        Connection con = null;
        String query = "INSERT INTO book(barcode, title, author, genre) VALUES(?, ?, ?, ?, ?)";
        try {
             con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, bookdto.getBarcode());
            ps.setString(2, bookdto.getTitle());
            ps.setString(3, bookdto.getAuthor());
            ps.setString(4, bookdto.getGenre());
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * checkOutBook method
     * Uses UPDATE query to change checkedOutStatus to "Checked-Out"
     * Arguments: con
     */
    //TODO Fix up the query
    @Override
    public void checkOutBook(BookDTO book) {
        Connection con;
        try {
            con = getConnection(); ;
            String query ="UPDATE book SET (checkedOutStatus = 'Checked-Out' AND duedate = 'null') WHERE  checkedOutStatus = 'Checked-In'";
            PreparedStatement st = con.prepareStatement(query);

            st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * removeBook method
     * Uses the DELETE query to remove book from the LMS MySQL database
     * Arguments: con
     */
    //TODO So far, only works with only title or only barcode at a time.
    //     It can remove books, but only if I code it with title in the code or barcode in the code.
    //     Have it so user can enter title or barcode and the code take in title or barcode.
    //     Something about the parameterIndexes is not making the code happy to take in title or barcode.
    public String removeBook(String titleOrBarcode) {
        String lmsBookData = "";
        Connection con;
        String query = "DELETE FROM book WHERE barcode=? OR title=?";
        try {
            con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, titleOrBarcode);
            ps.setString(2, titleOrBarcode);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Removing Successful" + lmsBookData;
    }

    /**
     * getBookByTitle method
     * Extra method I thought I might need when trying to do the removeBook()
     *  method. I might not need it.
     * Arguments: N/A
     */
    @Override
    public BookDTO getBookByTitle(String title) {
        //TODO
        return null;
    }
}
