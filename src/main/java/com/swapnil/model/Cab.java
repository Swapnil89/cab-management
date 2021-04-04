package com.swapnil.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Cab {
    private final String id;
    private String number;
    @Setter private City currentLocation;
    @Setter private LocalDateTime lastTripFinishedAt;

    public enum Status{
        ON_TRIP,
        IDLE
    }
    @Setter Status status;
    public Cab(@NonNull String number, City location){
        this.id = UUID.randomUUID().toString();
        this.number = number;
        this.currentLocation = location;
        this.status = Status.IDLE;
        this.lastTripFinishedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Cab{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", currentLocation=" + currentLocation.getName() +
                ", lastTripFinishedAt=" + lastTripFinishedAt +
                ", status=" + status +
                '}';
    }
}
