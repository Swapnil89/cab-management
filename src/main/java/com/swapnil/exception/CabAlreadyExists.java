package com.swapnil.exception;

public class CabAlreadyExists extends Exception{
    public CabAlreadyExists(){
        super("Cab already exists.");
    }
}
