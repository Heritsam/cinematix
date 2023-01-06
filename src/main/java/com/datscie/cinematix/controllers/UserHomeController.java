/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datscie.cinematix.controllers;

import com.datscie.cinematix.dao.MovieDao;
import com.datscie.cinematix.dao.MovieDaoImpl;
import com.datscie.cinematix.models.Movie;
import com.datscie.cinematix.utils.ApplicationException;
import java.util.ArrayList;
import java.util.logging.*;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;



/**
 *
 * @author Quin Derbi
 */
public class UserHomeController {
    MovieDao movieDao;

    public UserHomeController() {
        movieDao = new MovieDaoImpl();
    }

    public void fecthMovies(JList<String> jListMovies) {
        try {
            ArrayList<Movie> movies = movieDao.getMovies();
            DefaultListModel<String> model = new DefaultListModel<>();
            movies.forEach((movie) -> {
                model.addElement(movie.getTitle());
            });
            jListMovies.setModel(model);
        } catch (ApplicationException ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayMovieDetails(String selectedTitle ,JLabel jLabelMovieTitle, JLabel jLabelMovieGenre) {
        // TODO: Implement this method
        try {
            Movie movie = movieDao.getMoviesByTitle(selectedTitle);
            jLabelMovieTitle.setText(movie.getTitle());
            jLabelMovieGenre.setText(movie.getGenre());
        } catch (ApplicationException ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
