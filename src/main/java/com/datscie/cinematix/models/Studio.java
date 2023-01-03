package com.datscie.cinematix.models;

import java.util.List;

public class Studio {
    private int id;
    private List<String> seats;

    public Studio(int id, List<String> seats) {
        this.id = id;
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void addSeat(String seat) {
        this.seats.add(seat);
    }

    public void removeSeat(String seat) {
        this.seats.remove(seat);
    }
}
