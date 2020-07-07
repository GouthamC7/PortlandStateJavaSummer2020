package edu.pdx.cs410J.podili;

class InvalidArgumentException extends RuntimeException {
    InvalidArgumentException(String message) {
        super(message);
        System.err.println(message);
        System.exit(1);
    }
}
