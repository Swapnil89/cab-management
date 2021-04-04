package com.swapnil.dao;

import com.swapnil.exception.CityAlreadyExists;
import com.swapnil.exception.CityDoesNotExists;
import com.swapnil.model.City;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class CityDao implements ICity {
    private final Map<String, City> cityMap;

    public CityDao(){
        this.cityMap = new HashMap<>();
    }

    public void addCity(@NonNull String cityName) throws CityAlreadyExists {
        if(cityMap.containsKey(cityName))
            throw new CityAlreadyExists();
        City city = new City(cityName);
        cityMap.put(cityName, city);
    }

    public City getCity(String cityName) throws CityDoesNotExists {
        if(!cityMap.containsKey(cityName))
            throw new CityDoesNotExists();
        return cityMap.get(cityName);
    }
}
