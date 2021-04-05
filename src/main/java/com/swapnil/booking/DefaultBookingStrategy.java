package com.swapnil.booking;

import com.swapnil.exception.CabNotFoundException;
import com.swapnil.model.Cab;
import com.swapnil.model.City;
import lombok.NonNull;

import java.util.List;

public class DefaultBookingStrategy implements IBookingStrategy {

    @Override
    public Cab assignCab(@NonNull List<Cab> candidateCabs,@NonNull City fromLocation,@NonNull City toLocation) throws CabNotFoundException {
        if(candidateCabs.size()==0)
            throw new CabNotFoundException();
        return candidateCabs.stream().min((o1,o2)->(o1.getLastTripFinishedAt().isBefore(o2.getLastTripFinishedAt())==true ? -1 : 1)).get();
    }
}
