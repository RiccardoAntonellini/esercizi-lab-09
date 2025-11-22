package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Dimension;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {
    private static final int PROPORTION = 5;
    private static final int AREAROWS = 30;
    private static final int AREACOLUMNS = 40;
    private final Controller controll = new Controller();
    private final JFrame frame = new JFrame("My first java graphical interface");

    /**
     * builds the gui with buttons, panels, ecc.
     */
    public SimpleGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel mainPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(mainPanel);
        final JTextArea mainTextArea = new JTextArea(AREAROWS, AREACOLUMNS);
        mainPanel.add(mainTextArea, BorderLayout.CENTER);
        final JButton mainButton = new JButton("Save");
        mainPanel.add(mainButton, BorderLayout.SOUTH);
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controll.write(mainTextArea.getText());
                    JOptionPane.showMessageDialog(frame,
                        "File salvato con successo in: " + controll.getCurrentFilePath(),
                        "Successo", JOptionPane.INFORMATION_MESSAGE);
                } catch (final IOException exc) {
                    JOptionPane.showMessageDialog(frame,
                        "Errore durante il salvataggio: " + exc.getMessage(),
                        "Errore", JOptionPane.ERROR_MESSAGE);
                }
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
     * Main entry point of the program.
     *
     * @param args unused arguments
     */
    public static void main(final String... args) {
        new SimpleGUI().display();
    }
}
