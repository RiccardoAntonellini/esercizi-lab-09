package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {
    private static final int PROPORTION = 2;
    private static final int BUTTONHEIGHT = 10;
    private final Controller controll = new SimpleController();
    private final JFrame frame = new JFrame("Graphical interface of 9.3");
    /*
        5. The behavior of the program is that, if "Print" is pressed, the controller is asked
        to show the string contained in the text field on standard output.
        If "show history" is pressed instead, the GUI must show all the prints that have
        been done to this moment in the text area.
    */

    /**
     * builds the gui with buttons, panels, ecc.
     */
    public SimpleGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel mainPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(mainPanel);
        final JTextField mainTextField = new JTextField();
        mainPanel.add(mainTextField, BorderLayout.NORTH);
        final JTextArea mainTextArea = new JTextArea();
        mainPanel.add(mainTextArea, BorderLayout.CENTER);
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        final JButton printButton = new JButton("Print");
        printButton.setSize(frame.getWidth() / PROPORTION, BUTTONHEIGHT);
        final JButton historyButton = new JButton("Show history");
        historyButton.setSize(frame.getWidth() / PROPORTION, BUTTONHEIGHT);
        bottomPanel.add(printButton, BorderLayout.CENTER);
        bottomPanel.add(historyButton, BorderLayout.WEST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controll.setString(mainTextField.getText());
                controll.printCurrentString();
            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder strBuilder = new StringBuilder();
                for (final String s : controll.getPrintedStringsHistory()) {
                    strBuilder.append(s);
                }
                mainTextArea.setText(strBuilder.toString());
            }
        });
    }

    /**
     * sets the correct dimensions for the gui.
     */
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Main entry point.
     *
     * @param args unused parameters
     */
    public static void main(final String... args) {
        new SimpleGUI().display();
    }
}
