package com.swapnil;

import com.swapnil.booking.DefaultBookingStrategy;
import com.swapnil.dao.*;
import com.swapnil.exception.*;
import com.swapnil.model.Trip;
import com.swapnil.service.BookingService;
import com.swapnil.service.CabService;
import com.swapnil.service.CityService;
import com.swapnil.service.TripService;

import java.time.LocalDateTime;

public class Driver {
    public static void main(String[] args) throws CityAlreadyExists, CabAlreadyExists, CityDoesNotExists, CabDoesNotExists, CabNotFoundException, TripNotExists, InterruptedException {
        ICabs cabs = new CabsDao();
        ICity cities = new CityDao();
        ITrips trips = new TripsDao();

        CityService cityService = new CityService(cities);
        CabService cabService = new CabService(cabs);
        BookingService bookingService = new BookingService(new DefaultBookingStrategy(), cabs, cities, trips);
        TripService tripService = new TripService(trips);

        cityService.addCity("Banglore");
        cityService.addCity("Mumbai");
        cityService.addCity("Delhi");
        //System.out.println(cities.toString());

        cabService.registerCab("KA12B1234");
        Thread.sleep(1000);
        cabService.registerCab("KA12B1235");
        cabService.registerCab("KA12B1236");
        //System.out.println(cabs.toString());
        cabService.changeCabLocation(cities.getCity("Banglore"),"KA12B1234");
        cabService.changeCabLocation(cities.getCity("Banglore"),"KA12B1235");
        //System.out.println(cabs.toString());


        Trip bookedTrip1 = bookingService.book(cities.getCity("Banglore"),cities.getCity("Mumbai"));
        System.out.println(bookedTrip1.getCab().getNumber());
        Trip bookedTrip2 = bookingService.book(cities.getCity("Banglore"),cities.getCity("Mumbai"));
        System.out.println(bookedTrip2.getCab().getNumber());

        trips.endTrip(bookedTrip2.getId());
        Thread.sleep(1000);
        trips.endTrip(bookedTrip1.getId());
        //bookingService.book(cities.getCity("Banglore"),cities.getCity("Mumbai"));
        Trip bookedTrip3 = bookingService.book(cities.getCity("Mumbai"),cities.getCity("Delhi"));
        System.out.println(bookedTrip3.getCab().getNumber());
        trips.endTrip(bookedTrip3.getId());
        System.out.println(tripService.getCabHistory("KA12B1235"));
        System.out.println(tripService.getCabIdleTimeInInterval("KA12B1235", LocalDateTime.MIN, LocalDateTime.MAX));
        System.out.println(tripService.getCityWithHighestDemandInInterval(LocalDateTime.MIN,LocalDateTime.MAX));
    }
}
