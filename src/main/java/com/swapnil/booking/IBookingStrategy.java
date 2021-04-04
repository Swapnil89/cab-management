package com.swapnil.booking;

import com.swapnil.exception.CabNotFoundException;
import com.swapnil.model.Cab;
import com.swapnil.model.City;

import java.util.List;

public interface IBookingStrategy {
    Cab assignCab(List<Cab> candidateCabs, City fromLocation, City toLocation) throws CabNotFoundException;
}
