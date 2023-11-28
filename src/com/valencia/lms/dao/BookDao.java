/** Riane Torres
 * Co. No. 14835
 * 11/27/23
 * CEN 3024C
 *
 * Brief Function of BookDao class
 * Book Data Access Object (BookDao).
 * Mainly used for the implementation for the BookDaoImpl class.
 * Provides the MySQL data from the MysSQL database.
 * Hides the implementation detail as a single interface.
 * Is the abstraction.
 * Acts between the Controllers and Model.
 *  */

package com.valencia.lms.dao;

import com.valencia.lms.dto.BookDTO;

import java.sql.Connection;
import java.util.List;

public interface BookDao {

    public List<BookDTO> viewBooklist();

    public void updateData(String bookstatus, String barcodeOrTitle);

    public BookDTO getBookByTitle(String title);

    public BookDTO checkInBook(String title);

    public Connection getConnection();

    public void addBook(BookDTO bookdto);

    public BookDTO checkOutBook(String title) ;

    public Boolean removeBook(String titleOrBarcode);

    public BookDTO getBookByTitleOrBarcode(String titleOrBarcode);
}
