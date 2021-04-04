package com.swapnil.service;

import com.swapnil.dao.ITrips;
import com.swapnil.model.City;
import com.swapnil.model.Trip;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class TripService {
    private final ITrips trips;

    public List<Trip> getCabHistory(@NonNull String cabNumber){
        return trips.getTripsInfoByCab(cabNumber);
    }

    public Long getCabIdleTimeInInterval(@NonNull String cabNumber,@NonNull LocalDateTime fromTime,@NonNull LocalDateTime toTime){
        List<Trip> tripsInInterval =  trips.getTripsInfoByCabInInterval(cabNumber,fromTime,toTime);
        Long inTripTime = 0L;
        LocalDateTime minStart = LocalDateTime.MAX;
        LocalDateTime maxEnd = LocalDateTime.MIN;
        for(Trip current : tripsInInterval){
            if(current.getStartTS().isBefore(minStart))
                minStart = current.getStartTS();
            if(current.getEndTS().isAfter(maxEnd))
                maxEnd = current.getEndTS();
            inTripTime += current.getEndTS().getSecond() - current.getStartTS().getSecond();
        }
        return maxEnd.getSecond()-minStart.getSecond()-inTripTime;
    }

    public City getCityWithHighestDemandInInterval(@NonNull LocalDateTime fromTime,@NonNull LocalDateTime toTime){
        return trips.getCityWithDemandInInterval(fromTime,toTime).entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
    }
}
