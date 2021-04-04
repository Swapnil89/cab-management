package com.swapnil.dao;

import com.swapnil.exception.CityAlreadyExists;
import com.swapnil.exception.CityDoesNotExists;
import com.swapnil.model.City;

public interface ICity {
    void addCity(String cityName) throws CityAlreadyExists;
    City getCity(String cityName) throws CityDoesNotExists;
}
