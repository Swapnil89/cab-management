package com.swapnil.exception;

public class CityAlreadyExists extends Exception{
    public CityAlreadyExists(){
        super("City already exists.");
    }
}
