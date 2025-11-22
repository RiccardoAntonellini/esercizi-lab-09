package it.unibo.mvc;

import java.util.List;
import java.util.Objects;

import java.util.ArrayList;

/**
 * Class that implements the controller interface.
 */
public final class SimpleController implements Controller {
    private String currentString;
    private final List<String> history = new ArrayList<>();

    /**
     * Sets the next String to print.
     *
     * @param s the string to set as next
     *
     * @throws NullPointerException if s is null
     */
    @Override
    public void setString(final String s) {
        Objects.requireNonNull(s, "String cannot be null");
        this.currentString = s;
    }

    /**
     * Returns the current string.
     *
     * @return the currenString
     */
    @Override
    public String getString() {
        return this.currentString;
    }

    /**
     * Returns the history of the printed strings.
     *
     * @return the list containing printed strings
     */
    @Override
    public List<String> getPrintedStringsHistory() {
        return List.copyOf(this.history);
    }

    /**
     * Writes the currentString on standard output.
     *
     * @throws IllegalStateException if the current string is unset
     */
    @Override
    public void printCurrentString() {
        if (currentString != null) {
            System.out.println(getString()); // NOPMD: allowed in exercises
            this.history.addLast(getString());
        } else {
            throw new IllegalStateException("The string to be printed is unset");
        }
    }
}
