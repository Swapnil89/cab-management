package com.swapnil.dao;

import com.swapnil.exception.TripNotExists;
import com.swapnil.model.*;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TripsDao implements ITrips {
    private final Map<String, Trip> tripMap;

    public TripsDao(){
        this.tripMap = new HashMap<>();
    }

    @Override
    public Trip createTrip(@NonNull Cab cab,@NonNull City fromLocation,@NonNull City toLocation){
        cab.setStatus(Cab.Status.ON_TRIP);
        Trip trip = new Trip(cab, fromLocation,toLocation);
        tripMap.put(trip.getId(),trip);
        return trip;
    }

    @Override
    public void endTrip(String tripId) throws TripNotExists {
        if(!tripMap.containsKey(tripId))
            throw new TripNotExists();
        Trip trip = tripMap.get(tripId);
        trip.endTrip();
    }

    @Override
    public Trip getTripInfo(String tripId) throws TripNotExists {
        if(!tripMap.containsKey(tripId))
            throw new TripNotExists();
        return tripMap.get(tripId);
    }

    @Override
    public List<Trip> getTripsInfoByCab(@NonNull String cabNumber){
        return tripMap.values().stream().filter(x->x.getCab().getNumber().equals(cabNumber)).collect(Collectors.toList());
    }

    @Override
    public List<Trip> getTripsInfoByCabInInterval(@NonNull String cabNumber,@NonNull LocalDateTime fromTime,@NonNull LocalDateTime toTime){
        return tripMap.values().stream().filter(x->x.getCab().getNumber().equals(cabNumber)
                                                && x.getStatus().equals(Trip.Status.COMPLETED)
                                                && (x.getStartTS().isAfter(fromTime) || x.getEndTS().isBefore(toTime)))
                                                .collect(Collectors.toList());
    }

    @Override
    public Map<City,Long> getCityWithDemandInInterval(@NonNull LocalDateTime fromTime,@NonNull LocalDateTime toTime){
        return (tripMap.values().stream().filter(x -> x.getStatus().equals(Trip.Status.COMPLETED)
                && x.getStartTS().isAfter(fromTime) && x.getEndTS().isBefore(toTime))
                .collect(Collectors.groupingBy(x->x.getFromLocation(),Collectors.counting())));
    }

}
