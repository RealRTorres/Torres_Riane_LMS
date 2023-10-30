/*
 * Riane Torres
 * Co. No. 14835
 * 10/29/23
 * CEN 3024C
 */
/* Brief function of the MainFrame class
 * Provides the GUI portion of the LMSRunner class
 * Has buttons on the menu instead of taking numbers for the switch case
 * Menu is created from the MainFrame.form by using the GUI builder
 * Created modified versions of the switch cases into their respective buttons here
 * Lets the user check-in, add, check-out, remove, view booklist, and exit */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton btnRemoveBook;
    private JButton btnCheckOutBook;
    private JButton btnViewBooklist;
    private JButton btnExit;
    private JButton checkInBookbtn;
    private JButton btnAddBoook;

    //create new LMS object to connect to LMS class
    LMS lms = new LMS();

    //TODO
    //  Everything is mainly printing out on JOptionPane
    //  Eventually we should print out on a Jtable
    //  Or check out how everything will be printed once we combine the SQLite database in phase 3 and plan from there

    // FIXME
    //  And then part of the pane will print out the "Checking In/Out", "Adding/Removing", "Printing", etc. dialogue on JPanel or something
    //  Has to do with the toString method in the Book class
    //  Plus there's tons of "," where it shouldn't be
    //  Maybe try putting on JOptionPane on LMS class OR we put all those dialogs here on the MainFrame class
    //  Or maybe try just using JTextArea from now on
    public MainFrame() {
        //The main panel that holds all the buttons, generated from MainFrame.form
        setContentPane(mainPanel);
        setTitle("LMS Database");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);

        /*
        * If the LMSTest class is running while you add a book
        * It will not show up on the View Booklist and run into a problem
        * Solution is to comment out all of LMSTest class */
        checkInBookbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(null, "Enter title: ", "Checking in book", JOptionPane.PLAIN_MESSAGE);
                System.out.println(title);
                //checks in book from the title the user entered
                JOptionPane.showMessageDialog(null, lms.checkInBookStatus(title));

            }
        });

        //TODO
        //  Would be nice to eventually have a form with multiple text fields to input all the book details
        btnAddBoook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creates a FileDialog object so the user can open the file
                FileDialog fd = new FileDialog(new Frame(), "Open File", FileDialog.LOAD);
                fd.show();
                String file = fd.getFile();
                //create a Book object in order to connect all the user added in details to the Book class
                Book book = new Book();
                if (file != null && file.equals("bookfile.txt")) {
                    String barcode = JOptionPane.showInputDialog(null, "Enter bar code: ");
                    String title = JOptionPane.showInputDialog(null, "Enter title: ");
                    String author = JOptionPane.showInputDialog(null, "Enter author: ");
                    String genre = JOptionPane.showInputDialog(null, "Enter genre: ");

                    //puts the user inputs into the object and setters
                    book.setBarcode(barcode);
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setGenre(genre);
                    //adds user input book into the books array in LMS class
                    JOptionPane.showMessageDialog(null, lms.addBook(book));
                } else {
                    JOptionPane.showMessageDialog(null, "File: " + file + " doesn't exist.", "File opener error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCheckOutBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titleOrBarcode = JOptionPane.showInputDialog(null, "Enter bar code or title: ", "Checking Out Book", JOptionPane.PLAIN_MESSAGE);
                System.out.println(titleOrBarcode);
                //Checks out the book and changes status to "checked out" and gives a due date 28 days from checkout date
                JOptionPane.showMessageDialog(null,"Checking out " + lms.checkOutBook(titleOrBarcode));

            }
        });
        btnRemoveBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titleOrBarcode = JOptionPane.showInputDialog(null, "Enter bar code or title: ", "Removing Book", JOptionPane.PLAIN_MESSAGE);
                System.out.println(titleOrBarcode);
                try {
                    //removes book from the array via user entered title or barcode
                    JOptionPane.showMessageDialog(null, lms.removeBook(titleOrBarcode));
                    //overwrites bookfile.txt without said removed book
                    lms.saveBookListToFile(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /* If you're running and testing this, this won't work after you add in a book
        * because the LMSTest class is running
        * Solution is to comment out the whole LMSTest class */
        btnViewBooklist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checks current books on the booklist
                JFrame jf = new JFrame("Viewing Booklist");
                JTextArea myOutput = new JTextArea();
                JPanel p = new JPanel();

                myOutput.setText("Printing.... " + lms.viewBooklistFromFile("bookfile.txt"));

                p.add(myOutput);
                jf.add(myOutput);
                jf.setSize(600,300);
                jf.setVisible(true);
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            //Exit button
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        //main for MainFrame.java to run the menu and GUI buttons
       new MainFrame();
    }
}