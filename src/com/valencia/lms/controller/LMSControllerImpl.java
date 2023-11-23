/* Riane Torres
 * Co. No. 14835
 * 11/12/23
 * CEN 3024C
 *  */
/* Brief Function of LMSControllerImpl class
 * LMSController Implementation class (LMSControllerImpl)
 * Is the generic interface for the LMSController class.
 *  Similar use to the BookDaoImpl class
 *  Main use is if we need to switch to another database then we don't need to change the Mainframe class
 * Acts as the intermediary between the UI/Mainframe class and application logic/model
 *  */

package com.valencia.lms.controller;

import com.valencia.lms.LMSConstant;
import com.valencia.lms.dao.BookDaoImpl;
import com.valencia.lms.dto.BookDTO;

import javax.swing.*;
import java.util.List;

public class LMSControllerImpl implements LMSController {
    BookDaoImpl dao;

    public LMSControllerImpl() {
        dao = new BookDaoImpl();
    }

    @Override
    public String viewBooklist() {
        List<BookDTO> bookList = dao.viewBooklist();
        StringBuilder sb = new StringBuilder();
        for(BookDTO book : bookList) {
            sb.append(book.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public BookDTO addBook(BookDTO newBook) {
         dao.addBook(newBook);
         return newBook;
    }

    @Override
    public String checkOutBook(String title) {
        BookDTO data =  dao.getBookByTitle(title);
        if (data.getCheckedOutStatus().equalsIgnoreCase(LMSConstant.CHECKED_OUT)) {
            JOptionPane.showMessageDialog(null,  data.getTitle() + " is not available", "Check Out Book Error", JOptionPane.ERROR_MESSAGE);
            return "Already Checked-out: " + data.toString() + "\n\n";
        }
        else {
            BookDTO value = dao.checkOutBook(title);
            return "Checked-out: " + value.toString() + "\n\n";
        }
    }

    /**
     * checkBookStatus method
     * While no method and returns null, still need it for the implementation at line 20
     * Arguments: titleOrBarcode
     * */
    @Override
    public BookDTO checkBookStatus(String titleOrBarcode) {
        return null;
    }

    @Override
    public String removeBook(String titleOrBarcode) {
        Boolean result = dao.removeBook(titleOrBarcode);
        if (result) {
            return titleOrBarcode + " was removed\n";
        }
        else {
            return titleOrBarcode + " was NOT removed\n";
        }
    }

    @Override
    public Object checkInBook(String title) {
        BookDTO data = dao.checkInBook(title);
        return "Checked-In: " + data.toString() + "\n\n";
    }
}