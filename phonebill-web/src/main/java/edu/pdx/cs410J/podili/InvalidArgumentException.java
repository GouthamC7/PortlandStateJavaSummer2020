package edu.pdx.cs410J.podili;

class InvalidArgumentException extends RuntimeException {

    /**
     * Prints error message to the console and exits.
     * @param message contains the error message.
     */

    InvalidArgumentException(String message) {
        super(message);
        System.err.println(message);
        System.exit(1);
    }
}
