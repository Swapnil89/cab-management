package com.swapnil.service;

import com.swapnil.dao.ITrips;
import com.swapnil.exception.TripNotExists;
import com.swapnil.model.City;
import com.swapnil.model.Trip;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
        for(Trip current : tripsInInterval){
            LocalDateTime start;
            LocalDateTime end;
            if(current.getStartTS().isBefore(fromTime))
                start = fromTime;
            else
                start = current.getStartTS();
            if(current.getEndTS().isAfter(toTime))
                end = toTime;
            else
                end = toTime;
            inTripTime += end.toInstant(ZoneOffset.UTC).toEpochMilli()-start.toInstant(ZoneOffset.UTC).toEpochMilli();
        }
        return toTime.toInstant(ZoneOffset.UTC).toEpochMilli()
                -fromTime.toInstant(ZoneOffset.UTC).toEpochMilli()
                -inTripTime;
    }

    public City getCityWithHighestDemandInInterval(@NonNull LocalDateTime fromTime,@NonNull LocalDateTime toTime){
        return trips.getCityWithDemandInInterval(fromTime,toTime).entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
    }

    public void endTrip(@NonNull String tripId) throws TripNotExists {
        trips.endTrip(tripId);
    }

}
