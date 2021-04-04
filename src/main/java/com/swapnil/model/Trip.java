package com.swapnil.model;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Trip {
    private final String id;
    private Cab cab;
    private LocalDateTime startTS;
    private LocalDateTime endTS;
    private City fromLocation;
    private City toLocation;
    private Status status;
    public enum Status{
        IN_PROGRESS,
        COMPLETED
    }

    public Trip(@NonNull Cab cab,
                @NonNull City fromLocation, @NonNull City toLocation){
        this.id = UUID.randomUUID().toString();
        this.cab = cab;
        this.startTS = LocalDateTime.now();
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.status = Status.IN_PROGRESS;
    }

    public void endTrip(){
        this.status = Status.COMPLETED;
        this.endTS = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id='" + id + '\'' +
                ", cab=" + cab.getNumber() +
                ", startTS=" + startTS +
                ", endTS=" + endTS +
                ", fromLocation=" + fromLocation.getName() +
                ", toLocation=" + toLocation.getName() +
                ", status=" + status +
                '}';
    }
}
