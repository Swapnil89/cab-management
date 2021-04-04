package com.swapnil.service;

import com.swapnil.booking.IBookingStrategy;
import com.swapnil.dao.ICabs;
import com.swapnil.dao.ICity;
import com.swapnil.dao.ITrips;
import com.swapnil.exception.CabNotFoundException;
import com.swapnil.model.Cab;
import com.swapnil.model.City;
import com.swapnil.model.Trip;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class BookingService {
    private final IBookingStrategy strategy;
    private final ICabs cabs;
    private final ICity city;
    private final ITrips trips;

    public Trip book(@NonNull City fromLocation,@NonNull City toLocation) throws CabNotFoundException {
        List<Cab> candidateCabs = cabs.getAvailableCabsByCity(fromLocation.getName());
        Cab cab =  strategy.assignCab(candidateCabs,fromLocation,toLocation);
        return trips.createTrip(cab,fromLocation,toLocation);
    }
}
