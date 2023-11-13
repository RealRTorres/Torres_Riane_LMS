/* Riane Torres
 * Co. No. 14835
 * 11/12/23
 * CEN 3024C
 *  */
/* Brief Function of LMSController class
 * Makes it easier for the Mainframe to display the data.
 * Is responsible for receiving and modifying the data from the
 *  database (DAO layer).
 *  */

package com.valencia.lms.controller;

import com.valencia.lms.dto.BookDTO;
import java.util.List;

public interface LMSController {
    public String viewBooklist();

    BookDTO addBook(BookDTO newBook);

    BookDTO checkOutBook(String titleOrBarcode);

    BookDTO checkBookStatus(String titleOrBarcode);

    Object removeBook(String titleOrBarcode);

    Object checkInBook(String title);
}
