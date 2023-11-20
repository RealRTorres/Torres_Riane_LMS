/* Riane Torres
 * Co. No. 14835
 * 11/12/23
 * CEN 3024C
 *  */
/* Brief Function of BookDBConnection class
 * Separate class to access the database connection with the connect() method.
 * Better to have a separate class for the method,
 *  so I don't have to keep opening the connection in the
 *  BookDaoImpl class.
 *  */

package com.valencia.lms.dao;

import com.valencia.lms.controller.LMSController;
import com.valencia.lms.controller.LMSControllerImpl;
import com.valencia.lms.dto.BookDTO;
import java.sql.*;
import java.util.List;

@Deprecated
public class BookDBConnection {

    private final String TABLE_NAME = "lmsbooks";
    final static String CHECKED_IN = "Checked-in";
    final static String CHECKED_OUT = "Checked-out";
    private final static String url = "jdbc:mysql://localhost:3306/lmsbooks";
    private final static String uname = "root";
    private final static String password = ")8X5i5E%crl%Rl@R,tz)";

    public static Connection connect() throws Exception {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        try {
            con = DriverManager.getConnection(url, uname, password);;
        } catch (SQLException e) {
            throw e;
        }
        return con;
    }
}