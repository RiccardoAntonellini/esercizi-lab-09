package it.unibo.mvc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

/**
 * Application controller. Performs all I/O operations.
 */
public final class Controller {
    private static final String SEP = File.separator;
    private String currentFile = System.getProperty("user.home") + SEP + "output.txt"; // di default è output.txt;

    /**
     * Sets the current file.
     *
     * @param filename the new file name to use
     */
    public void setFile(final String filename) {
        if (filename != null && !filename.isEmpty()) {
            currentFile = filename;
        }
    }

    /**
     * Returns the current file as a {@link File} object.
     *
     * @return the current file
     */
    public File getCurrentFile() {
        return new File(currentFile);
    }

    /**
     * Returns the current file path as a string.
     *
     * @return the current file path
     */
    public String getCurrentFilePath() {
        return currentFile;
    }

    /**
     * Writes the given string content into the current file.
     *
     * @param input the text to write
     * @throws IOException if an error occurs during writing
     */
    public void write(final String input) throws IOException {
        try (
            OutputStream outp = new FileOutputStream(currentFile);
        ) {
            final byte[] b = input.getBytes(StandardCharsets.UTF_8);
            outp.write(b);
        }
    }
}
