/*
 * Riane Torres
 * Co. No. 14835
 * 11/2/23
 * CEN 3024C
 */
/* Brief function of the MainFrame class
 * Provides the GUI portion of the LMSRunner class
 * Has buttons on the menu instead of taking numbers for the switch case
 * Menu is created from the MainFrame.form by using the GUI builder
 * Created modified versions of the switch cases into their respective buttons here
 * Lets the user check-in, add, check-out, remove, view booklist, and exit
 * Taking over the function that LMSRunner was doing earlier
 * This is the View/User Interface
 * */

import com.valencia.lms.controller.LMSController;
import com.valencia.lms.controller.LMSControllerImpl;
import com.valencia.lms.dto.BookDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton btnRemoveBook;
    private JButton btnCheckOutBook;
    private JButton btnViewBooklist;
    private JButton btnExit;
    private JButton btnCheckInBook;
    private JButton btnAddBoook;

    LMSController controller = new LMSControllerImpl();
      public MainFrame() {
        //The main panel that holds all the buttons, generated from MainFrame.form
        setContentPane(mainPanel);
        setTitle("LMS Database");
        setSize(450, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnAddBoook.setBackground(Color.darkGray);
        btnRemoveBook.setBackground(Color.darkGray);
        btnViewBooklist.setBackground(Color.darkGray);
        btnCheckOutBook.setBackground(Color.darkGray);
        btnCheckInBook.setBackground(Color.darkGray);
        btnExit.setBackground(Color.darkGray);

        btnAddBoook.setForeground(Color.white);
        btnRemoveBook.setForeground(Color.white);
        btnViewBooklist.setForeground(Color.white);
        btnCheckOutBook.setForeground(Color.white);
        btnCheckInBook.setForeground(Color.white);
        btnExit.setForeground(Color.white);

          btnAddBoook.setFont(new Font("Helvetica", Font.PLAIN, 18));
          btnRemoveBook.setFont(new Font("Helvetica", Font.PLAIN, 18));
          btnViewBooklist.setFont(new Font("Helvetica", Font.PLAIN, 18));
          btnCheckOutBook.setFont(new Font("Helvetica", Font.PLAIN, 18));
          btnCheckInBook.setFont(new Font("Helvetica", Font.PLAIN, 18));
          btnExit.setFont(new Font("Helvetica", Font.PLAIN, 18));

          getContentPane().setBackground(Color.black);
        setVisible(true);

        btnCheckInBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Checking In Book");
                JPanel p = new JPanel();
                JTextArea myOutput = new JTextArea();

                String title = JOptionPane.showInputDialog(null, "Enter title: ", "Checking in book", JOptionPane.PLAIN_MESSAGE);
                System.out.println(title);
                //checks in book from the title the user entered
                JOptionPane.showMessageDialog(null, controller.checkInBook(title));

                myOutput.setBackground(Color.BLACK);
                myOutput.setForeground(Color.white);
                myOutput.setText(String.valueOf(controller.checkInBook(title)) + controller.viewBooklist());

                p.add(myOutput);
                jf.add(myOutput);
                jf.setSize(600,400);
                jf.setVisible(true);

            }
        });

        btnCheckOutBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Checking Out Book");
                JTextArea myOutput = new JTextArea();
                JPanel p = new JPanel();

                String title = JOptionPane.showInputDialog(null, "Enter title to check out: ", "Checking Out Book", JOptionPane.PLAIN_MESSAGE);
                System.out.println(title);
                //Checks out the book and changes status to "checked out" and gives a due date 28 days from checkout date
                String result = controller.checkOutBook(title);
                JOptionPane.showMessageDialog(null, result);

                myOutput.setBackground(Color.BLACK);
                myOutput.setForeground(Color.white);
                myOutput.setText(String.valueOf( result + controller.viewBooklist()));

                p.add(myOutput);
                jf.add(myOutput);
                jf.setSize(600,400);
                jf.setVisible(true);
            }
        });

        btnAddBoook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String barcode = JOptionPane.showInputDialog(null, "Enter bar code: ", "Adding Book", JOptionPane.PLAIN_MESSAGE);
                String title = JOptionPane.showInputDialog(null, "Enter title: ", "Adding Book", JOptionPane.PLAIN_MESSAGE);
                String author = JOptionPane.showInputDialog(null, "Enter author: ", "Adding Book", JOptionPane.PLAIN_MESSAGE);
                String genre = JOptionPane.showInputDialog(null, "Enter genre: ", "Adding Book", JOptionPane.PLAIN_MESSAGE);

                BookDTO book = new BookDTO();

                book.setBarcode(barcode);
                book.setTitle(title);
                book.setAuthor(author);
                book.setGenre(genre);

                JFrame jf = new JFrame("Adding Book");
                JTextArea myOutput = new JTextArea();
                JPanel p = new JPanel();

                myOutput.setBackground(Color.BLACK);
                myOutput.setForeground(Color.white);
                myOutput.setText(String.valueOf("Added: " + controller.addBook(book)) + "\n" +  controller.viewBooklist());

                p.add(myOutput);
                jf.add(myOutput);
                jf.setSize(600, 400);
                jf.setVisible(true);

            }
        });

        btnRemoveBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("Removing Book from Booklist");
                JTextArea myOutput = new JTextArea();
                JPanel p = new JPanel();

                String titleOrBarcode = JOptionPane.showInputDialog(null, "Enter bar code or title: ", "Removing Book", JOptionPane.PLAIN_MESSAGE);
                System.out.println(titleOrBarcode);

                myOutput.setBackground(Color.BLACK);
                myOutput.setForeground(Color.white);
                myOutput.setText(controller.removeBook(titleOrBarcode) + controller.viewBooklist());

                p.add(myOutput);
                jf.add(myOutput);
                jf.setSize(600,400);
                jf.setVisible(true);
            }
        });

        btnViewBooklist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checks current books on the booklist
                JFrame jf = new JFrame("Viewing Booklist");
                JTextArea myOutput = new JTextArea();
                JPanel p = new JPanel();

                myOutput.setBackground(Color.BLACK);
                myOutput.setForeground(Color.white);
                myOutput.setText("Printing.... \n" + controller.viewBooklist());

                jf.add(p.add(myOutput));
                jf.add(myOutput);
                jf.setSize(600,400);
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