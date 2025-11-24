package com.jsp.the_green_corner.exception;

public class DuplicateUser extends RuntimeException {
    public DuplicateUser(String message) {
        super(message);
    }
}
