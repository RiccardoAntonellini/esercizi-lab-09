package it.unibo.mvc;

import java.util.List;

/**
 * Controller interface.
 */
public interface Controller {

    /**
     * Method for setting the next string to print.
     *
     * @param s the string to set as next
     */
    void setString(String s);

    /**
     * Method for getting the next string to print.
     *
     * @return the current String
     */
    String getString();

    /**
     * Returns the history of printed Strings as a List.
     *
     * @return a list<String> of the printed Strings
     */
    List<String> getPrintedStringsHistory();

    /**
     * Prints the current String.
     */
    void printCurrentString();
}
