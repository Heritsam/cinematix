package com.datscie.cinematix.dao;

import java.util.ArrayList;

import com.datscie.cinematix.models.Movie;
import com.datscie.cinematix.utils.ApplicationException;

public interface MovieDao {
    public void addMovie(Movie movie) throws ApplicationException;
    public void updateMovie(Movie movie) throws ApplicationException;
    public void deleteMovie(Movie movie) throws ApplicationException;
    public ArrayList<Movie> getMovies() throws ApplicationException;
}
