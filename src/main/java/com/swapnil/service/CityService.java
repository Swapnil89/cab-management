package com.swapnil.service;

import com.swapnil.dao.ICity;
import com.swapnil.exception.CityAlreadyExists;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CityService {
    private final ICity city;

    public void addCity(@NonNull String cityName) throws CityAlreadyExists {
        city.addCity(cityName);
    }

}
