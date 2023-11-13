/* Riane Torres
 * Co. No. 14835
 * 11/12/23
 * CEN 3024C
 *  */
/* Brief Function of BookDao class
 * Book Data Access Object (BookDao)
 * Class for all the SQL queries methods go here.
 *  */

package com.valencia.lms.dao;

import com.valencia.lms.dto.BookDTO;
import java.util.List;

//TODO Fix this up, get the methods from BookDaoImpl class
//     and put it here
public interface BookDao {

    public String viewBooklist();

    public void updateData(String bookstatus, String barCodeOrTitle);

    public BookDTO getBookByTitle(String title);

    //TODO Fix up this query, don't think this'll work.
    void checkInBook(String bookstatus, String barCodeOrTitle);

    public void addBook(BookDTO bookdto );

    public void checkOutBook(BookDTO book);

    public String removeBook(String titleOrBarcode);
}
