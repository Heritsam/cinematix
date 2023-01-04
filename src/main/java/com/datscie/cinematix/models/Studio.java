package com.datscie.cinematix.models;

import java.util.ArrayList;
import java.util.List;

public class Studio {
    private int id;
    private String name;
    private List<String> seats = new ArrayList<>();

    public Studio() {}

    public Studio(int id, String name, List<String> seats) {
        this.id = id;
        this.name = name;
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

    public String getSeatsString() {
        String seatsString = "";

        for (String seat : seats) {
            seatsString += seat + ",";
        }

        return seatsString;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public void setSeats(String seats) {
        String[] seatsArray = seats.split(",");

        for (String seat : seatsArray) {
            this.seats.add(seat);
        }
    }
    
    public void addSeat(String seat) {
        this.seats.add(seat);
    }

    public void removeSeat(String seat) {
        this.seats.remove(seat);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
