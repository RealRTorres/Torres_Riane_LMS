import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class LMSTest {
    LMS lms = new LMS();
    String title = "Artificial Unintelligence";
    String barcode = "12345";
    String checkOutBookTitle = "Artificial Unintelligence";

    @Test
    @DisplayName("1")
    void createFile() {
        try {
            PrintWriter pWriter = new PrintWriter(new FileWriter(LMS.FILENAME, false));
            pWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    @Test
    @DisplayName("2")
    void addBook() {
        Book book = new Book();
        barcode = "12345";
        title = "Algorithms of Oppression";
        String author = "Safiya Noble";
        String genre = "Social Science & Tech";
        book.setBarcode(barcode);
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        lms.addBook(book);

        book = new Book();
        barcode = "54321";
        title = "Artificial Unintelligence";
        author = "Meredith Broussard";
        genre = "Tech";
        book.setBarcode(barcode);
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        lms.addBook(book);

        book = new Book();
        barcode = "1111";
        title = "Weapons of Math Destruction";
        author = "Cathy O'Neil";
        genre = "Statistics";
        book.setBarcode(barcode);
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        lms.addBook(book);

        ArrayList<Book> result = lms.getBooks();
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("3")
    void checkOutBook() {
        Book result = lms.checkOutBook(checkOutBookTitle);
        assertNotNull(result);
        String status = result.getCheckedOutStatus();
        assertEquals("Checked-out", status);
    }

    @Test
    @DisplayName("4")
    void checkInBook() {
        Book result = lms.checkInBookStatus(checkOutBookTitle);
        assertNotNull(result);
        String status = result.getCheckedOutStatus();
        assertEquals("Checked-in", status);
    }

    @Test
    @DisplayName("5")
    void removeBook() {
        try {
            ArrayList<Book> books = lms.getBooks();
            int size = books.size();

            title = "Weapons of Math Destruction";
            lms.removeBook(title);

            barcode = "1111";
            lms.removeBook(barcode);

            int result = books.size();
            assertEquals(size - 1, result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}