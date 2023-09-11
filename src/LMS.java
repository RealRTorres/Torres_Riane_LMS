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

import java.util.*;
import java.io.*;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LMS {
    private int bookID = 0;
    private String bookTitle = "";
    private String bookAuthor = "";
    List<String> list = new ArrayList<String>();
    List<Integer> idList = new ArrayList<Integer>();

    //constructor for the books
    public LMS(int id, String title, String author) {
        this.bookID = id;
        this.bookTitle = title;
        this.bookAuthor = author;
    }

    public void setBookID (int id) {
        bookID = id;
    }

    public int getBookID () {
        return bookID;
    }

    public void setBookTitle (String title) {
        bookTitle = title;
    }

    public String getBookTitle () {
        return bookTitle;
    }

    public void setBookAuthor (String author) {
        bookAuthor = author;
    }
    public String getBookAuthor () {
        return bookAuthor;
    }

    public List<String> getList() {
        return list;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    //format the output
    public String toString(){
        return bookID + ", " + bookTitle + ", " + bookAuthor;
    }

    //addBookInfo method
    //adds the id no., title, and author into the idList and list array
    //arguments: bookID, bookTitle, bookAuthor
    //returns adding the user input into the arrays
    public void addBookInfo(){
        idList.add(bookID);
        list.add(bookTitle);
        list.add(bookAuthor);

    }

    //removeBookInfo method
    //removes the id, title, and author from the arrays
    //arguments: bookID, bookTitle, bookAuthor
    //returns removing the user input from the arrays
    public void removeBookInfo(){
        //idList.removeIf(p -> p.bookID.equals(bookID));
        idList.remove(bookID);
        list.remove(bookTitle);
        list.remove(bookAuthor);

    }

    //removeBook method
    //removes the text from the textfile
    //arguments: filepath, deleteLine
    //supposed to return a newly overwritten file with one the texts gone
    public static void removeBook(String filepath, int deleteLine) throws IOException {
        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        int line = 0;
        String currentLine;

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null) {
                line++;

                if(deleteLine != line) {
                    pw.println(currentLine);
                }
            }
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bw.close();
            fw.close();

            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /* checkBooklist method
    * Prints out current booklist in the arraylist in the text file
    * arguments: filepath, content
    * returns content and e */
    public static String checkBooklist(String filepath) {
        String content;

        try {
            content = new String(Files.readAllBytes(Paths.get((filepath))));
        } catch (IOException e) {
            return e.toString();
        }
        return content;
    }
}
