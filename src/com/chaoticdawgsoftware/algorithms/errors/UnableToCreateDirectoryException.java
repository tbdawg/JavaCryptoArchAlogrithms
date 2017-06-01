package com.chaoticdawgsoftware.algorithms.errors;

/**
 * UnableToCreateDirectoryException.java
 * Created by ChaoticDawg on 6/1/17.
 */

public class UnableToCreateDirectoryException extends Exception {
    public UnableToCreateDirectoryException(String directory) {
        super("System was not able to create directory: " + directory);
    }
}
