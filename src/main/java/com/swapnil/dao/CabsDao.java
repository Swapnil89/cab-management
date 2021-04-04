package com.swapnil.dao;

import com.swapnil.exception.CabAlreadyExists;
import com.swapnil.exception.CabDoesNotExists;
import com.swapnil.model.Cab;
import com.swapnil.model.City;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

public class CabsDao implements ICabs {
    private final Map<String,Cab> cabMap;

    public CabsDao(){
        cabMap = new HashMap<>();
    }

    @Override
    public void registerCab(@NonNull String cabNumber) throws CabAlreadyExists {
        if(cabMap.containsKey(cabNumber))
            throw new CabAlreadyExists();
        Cab cab = new Cab(cabNumber,null);
        cabMap.put(cabNumber,cab);
    }

    @Override
    public List<Cab> getAvailableCabsByCity(@NonNull String cityName) {
        return cabMap.values().stream().filter( x -> x.getCurrentLocation()!=null && x.getCurrentLocation().getName().equals(cityName)&&x.getStatus().equals(Cab.Status.IDLE) )
                .collect(Collectors.toList());
    }

    @Override
    public void addCabToCity(@NonNull City city,@NonNull String cabNumber) throws CabDoesNotExists {
        if(!cabMap.containsKey(cabNumber))
            throw new CabDoesNotExists();
        cabMap.get(cabNumber).setCurrentLocation(city);
    }

    @Override
    public void changeCabStatus(@NonNull String cabNumber,@NonNull Cab.Status status) throws CabDoesNotExists {
        if(!cabMap.containsKey(cabNumber))
            throw new CabDoesNotExists();
        cabMap.get(cabNumber).setStatus(status);
    }
}
