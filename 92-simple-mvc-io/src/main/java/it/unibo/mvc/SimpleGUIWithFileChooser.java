package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
public final class SimpleGUIWithFileChooser {
    private static final int PROPORTION = 2;
    private static final int TEXTFIELDCOLUMNS = 50;
    private static final int BUTTONWIDTH = 25;
    private static final int BUTTONHEIGHT = 5;
    private static final int AREAROWS = 30;
    private static final int AREACOLUMNS = 40;
    private final Controller controll = new Controller();
    private final JFrame frame = new JFrame("My second java graphical interface");

    /**
     * builds the gui with buttons, panels, ecc.
     */
    public SimpleGUIWithFileChooser() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel mainPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(mainPanel);
        final JPanel secondaryPanel = new JPanel(new BorderLayout());
        mainPanel.add(secondaryPanel, BorderLayout.NORTH);
        final JTextField mainTextField = new JTextField(TEXTFIELDCOLUMNS);
        mainTextField.setText(controll.getCurrentFilePath());
        mainTextField.setEditable(false);
        secondaryPanel.add(mainTextField, BorderLayout.WEST);
        final JButton secondaryButton = new JButton("Browse");
        secondaryButton.setSize(BUTTONWIDTH, BUTTONHEIGHT);
        secondaryPanel.add(secondaryButton, BorderLayout.CENTER);
        secondaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser(controll.getCurrentFilePath());
                final int chooserOutput = fileChooser.showSaveDialog(secondaryButton);
                if (chooserOutput == JFileChooser.APPROVE_OPTION) {
                    controll.setFile(fileChooser.getSelectedFile().getAbsolutePath());
                    mainTextField.setText(controll.getCurrentFilePath());
                    JOptionPane.showMessageDialog(frame, "Aggiornato file di destinazione scrittura: "
                    + controll.getCurrentFile(), "Successo", JOptionPane.INFORMATION_MESSAGE);
                } else if (chooserOutput != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(null,
                    "an error occurred during the setting of new CURRENT_FILE");
                }
            }
        });
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
     * Main entry point.
     *
     * @param args unused parameters
     */
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser().display();
    }
}
