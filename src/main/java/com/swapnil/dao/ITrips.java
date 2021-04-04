package com.swapnil.dao;

import com.swapnil.exception.TripNotExists;
import com.swapnil.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ITrips {
    Trip createTrip(Cab cab, City fromLocation, City toLocation);
    void endTrip(String tripId) throws TripNotExists;
    Trip getTripInfo(String tripId) throws TripNotExists;
    List<Trip> getTripsInfoByCab(String cabNumber);
    List<Trip> getTripsInfoByCabInInterval(String cabNumber, LocalDateTime fromTime, LocalDateTime toTime);
    Map<City,Long> getCityWithDemandInInterval(LocalDateTime fromTime, LocalDateTime toTime);
}
