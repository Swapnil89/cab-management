package com.swapnil.service;

import com.swapnil.dao.ICabs;
import com.swapnil.exception.*;
import com.swapnil.model.Cab;
import com.swapnil.model.City;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CabService {
    private final ICabs cabs;

    public void registerCab(@NonNull String number) throws CabAlreadyExists {
        cabs.registerCab(number);
    }

    public void changeCabLocation(@NonNull City city,@NonNull String cabNumber) throws CabDoesNotExists {
        cabs.addCabToCity(city,cabNumber);
    }

    public void changeCabStatus(@NonNull String cabNumber,@NonNull Cab.Status status) throws CabDoesNotExists {
        cabs.changeCabStatus(cabNumber,status);
    }

}
