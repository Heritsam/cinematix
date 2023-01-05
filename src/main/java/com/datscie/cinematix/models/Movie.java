package com.datscie.cinematix.models;

public class Movie {
    private int id;
    private String title, genre, director, synopsis;
    private int duration;

    public Movie() {
    }

    public Movie(int id, String title, String genre, String director, int duration, String synopsis) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.synopsis = synopsis;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
