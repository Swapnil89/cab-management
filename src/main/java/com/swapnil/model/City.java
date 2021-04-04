package com.swapnil.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class City {
    private final String id;
    private String name;
    public City(@NonNull String name){
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
