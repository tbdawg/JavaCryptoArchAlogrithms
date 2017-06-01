package com.chaoticdawgsoftware.algorithms.errors;

/**
 * FilePathUnderflowException.java
 * Created by ChaoticDawg on 6/1/17.
 */

public class FilePathUnderflowException extends ArrayIndexOutOfBoundsException {
    public FilePathUnderflowException() {
        super("Index must be greater than zero.");
    }
}
