/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datscie.cinematix.controllers;

import com.datscie.cinematix.dao.MovieDao;
import com.datscie.cinematix.dao.MovieDaoImpl;
import com.datscie.cinematix.dao.ScheduleDao;
import com.datscie.cinematix.dao.ScheduleDaoImpl;
import com.datscie.cinematix.dao.StudioDao;
import com.datscie.cinematix.dao.StudioDaoImpl;
import com.datscie.cinematix.dao.TicketDao;
import com.datscie.cinematix.dao.TicketDaoImpl;
import com.datscie.cinematix.models.Movie;
import com.datscie.cinematix.models.Schedule;
import com.datscie.cinematix.models.Studio;
import com.datscie.cinematix.models.Ticket;
import com.datscie.cinematix.utils.ApplicationException;

import java.awt.Dimension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.*;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;

/**
 *
 * @author Quin Derbi
 */
public class UserHomeController {
    MovieDao movieDao;
    ScheduleDao scheduleDao;
    TicketDao ticketDao;
    StudioDao studioDao;

    public UserHomeController() {
        movieDao = new MovieDaoImpl();
        scheduleDao = new ScheduleDaoImpl();
        ticketDao = new TicketDaoImpl();
        studioDao = new StudioDaoImpl();
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

    public void displayMovieDetails(String selectedTitle, JLabel jLabelMovieTitle, JLabel jLabelMovieGenre,
            JLabel jLabelMovieDirector, JLabel jLabelDuration, JTextPane jLabelSynopsis, JList<String> jList2) {
        // TODO: Implement this method
        try {
            Movie movie = movieDao.getMoviesByTitle(selectedTitle);
            ArrayList<Schedule> schedules = scheduleDao.getScheduleByMovieId(movie.getId());
            jLabelMovieTitle.setText("Title   : " + movie.getTitle());
            jLabelMovieGenre.setText("Genre   : " + movie.getGenre());
            jLabelMovieDirector.setText("Director: " + movie.getDirector());
            jLabelDuration.setText("Duration: " + movie.getDuration());
            jLabelSynopsis.setText(movie.getSynopsis());

            DefaultListModel<String> model = new DefaultListModel<>();
            schedules.forEach((schedule) -> {
                model.addElement(schedule.getStudio().getId() + "#" + schedule.getDateTimeString());
            });
            jList2.setModel(model);

        } catch (ApplicationException ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void bookTicket(String selectedTitle, String selectedDateTime) {
        String[] split = selectedDateTime.split("#");
        String inputstudio = split[0];
        String inputdateTime = split[1];
        try {
            Ticket ticket = new Ticket();
            Movie movie;
            movie = movieDao.getMoviesByTitle(selectedTitle);
            ticket.setMovie(movie);
            LocalDateTime dateTime = LocalDateTime.parse(inputdateTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            ticket.setDateTime(dateTime);
            ticket.setSeatNumber("");
            Studio studio = studioDao.getStudioById(Integer.parseInt(inputstudio));
            ticket.setStudio(studio);
            ticketDao.addTicket(ticket);

        } catch (ApplicationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
