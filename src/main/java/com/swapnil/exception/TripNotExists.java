package com.swapnil.exception;

public class TripNotExists extends  Exception{
    public TripNotExists(){
        super("Trip does not exists.");
    }
}
