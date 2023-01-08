package com.datscie.cinematix.models;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private Movie movie;
    private LocalDateTime dateTime;
    private Studio studio;
    private String seatNumber;

    public Ticket(int id, Movie movie, LocalDateTime dateTime, Studio studio, String seatNumber) {
        this.id = id;
        this.movie = movie;
        this.dateTime = dateTime;
        this.studio = studio;
        this.seatNumber = seatNumber;
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
