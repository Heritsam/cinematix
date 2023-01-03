package com.datscie.cinematix.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Schedule {
    private int id;
    private Movie movie;
    private Studio studio;
    private LocalDateTime dateTime;
    private ArrayList<Ticket> tickets;
    private int price;
    
    public Schedule(int id, Movie movie, Studio studio, LocalDateTime dateTime, ArrayList<Ticket> tickets, int price) {
        this.id = id;
        this.movie = movie;
        this.studio = studio;
        this.dateTime = dateTime;
        this.tickets = tickets;
        this.price = price;
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

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
