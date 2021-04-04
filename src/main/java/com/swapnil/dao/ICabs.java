package com.swapnil.dao;


import com.swapnil.exception.CabAlreadyExists;
import com.swapnil.exception.CabDoesNotExists;
import com.swapnil.model.Cab;
import com.swapnil.model.City;

import java.util.List;

public interface ICabs {
    void registerCab(String cabNumber) throws CabAlreadyExists;
    List<Cab> getAvailableCabsByCity(String cityName);
    void addCabToCity(City city, String cabNumber) throws CabDoesNotExists;
    void changeCabStatus(String cabNumber, Cab.Status status) throws CabDoesNotExists;
}
