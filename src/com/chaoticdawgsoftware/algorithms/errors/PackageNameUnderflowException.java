package com.chaoticdawgsoftware.algorithms.errors;

/**
 * PackageNameUnderflowException.java
 * Created by ChaoticDawg on 6/1/17.
 */

public class PackageNameUnderflowException extends ArrayIndexOutOfBoundsException {
    public PackageNameUnderflowException() {
        super("Index must be greater than zero.");
    }
}
