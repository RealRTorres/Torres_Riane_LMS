/* Riane Torres
 * Co. No. 14835
 * 11/12/23
 * CEN 3024C
 *  */
/* Brief Function of LMSControllerImpl class
 * LMSController Implementation class (LMSControllerImpl)
 * Is the generic interface for the LMSController class.
 * Similar use to the BookDaoImpl class, if we need to switch to another database,
 *  then we don't need to change the Mainframe class.
 *  */

package com.valencia.lms.controller;

import com.valencia.lms.dao.BookDaoImpl;
import com.valencia.lms.dto.BookDTO;

import java.util.List;

public class LMSControllerImpl implements LMSController {
    BookDaoImpl dao;

    public LMSControllerImpl() {
        dao = new BookDaoImpl();
    }

    @Override
    public String viewBooklist() {
        String data = dao.viewBooklist();
        return data;
    }

    @Override
    public BookDTO addBook(BookDTO newBook) {
        return null;
    }

    @Override
    public BookDTO checkOutBook( String titleOrBarcode) {
        dao.updateData("checkout", titleOrBarcode);
        return null;
    }

    @Override
    public BookDTO checkBookStatus(String titleOrBarcode) {
        BookDTO data = dao.getBookByTitle(titleOrBarcode);
        return data;
    }

    @Override
    public Object removeBook(String titleOrBarcode) {
        //TODO
        return null;
    }

    @Override
    public Object checkInBook(String title) {
        //TODO
        return null;
    }
}