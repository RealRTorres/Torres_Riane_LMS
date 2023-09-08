/*
 * Riane Torres
 * Co. No. 14835
 * 9/7/23
 * CEN 3024C
 *  */
/* Brief Function of LMS class
* This is where the getters and setters for the objects and methods will go
* Private the variables in the parameters so no one changes it
* Public the objects*/

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import java.io.IOException;

public class LMS {
    private String id;
    private String title;
    private String author;
    ArrayList<String> lmsBooklist = new ArrayList<String>();
    //create object for the arraylist
    LMS book = new LMS();

    //constructor for the books
    public void books (String id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }

    //format the output
    public String toString(){
        return String.format("%s, %s, %s", this.id, this.title, this.author);
    }

   //Add book to arraylist
    public void addBook(book(String id, String title, String author)){
        lmsBooklist.add(id, title, author);
    }

    public void createBookFile(String id, String title, String author) throws IOException {
        File bookFile = new File("myfile.txt");
        FileWriter bookFileWriter = new FileWriter("myfile.txt", true);
        BufferedWriter bookBufferedWriter = new BufferedWriter(bookFileWriter);
        bookBufferedWriter.write(id + "," + title + "," + author);
        bookBufferedWriter.close();
        bookFileWriter.close();
    }

}
