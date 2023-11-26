/* Riane Torres
 * Co. No. 14835
 * 11/12/23
 * CEN 3024C
 *  */
/* Brief Function of LMSController class
 * Makes it easier for the Mainframe to display the data.
 * Is responsible for receiving and modifying the data from the database (DAO layer)
 * Used for the implementation in the LMSControllerImpl class
 * Decides how the data is displayed
 * Is the Controller
 *  */

package com.valencia.lms.controller;

import com.valencia.lms.dto.BookDTO;

public interface LMSController {
    public String viewBooklist();

    BookDTO addBook(BookDTO newBook);

    String checkOutBook(String titleOrBarcode);

    BookDTO checkBookStatus(String titleOrBarcode);

    String removeBook(String titleOrBarcode);

    Object checkInBook(String title);
}
