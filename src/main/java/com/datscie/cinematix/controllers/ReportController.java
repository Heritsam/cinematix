package com.datscie.cinematix.controllers;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.datscie.cinematix.dao.MovieDao;
import com.datscie.cinematix.dao.MovieDaoImpl;
import com.datscie.cinematix.dao.ReportDao;
import com.datscie.cinematix.dao.ReportDaoImpl;
import com.datscie.cinematix.dao.TicketDao;
import com.datscie.cinematix.dao.TicketDaoImpl;
import com.datscie.cinematix.models.Report;
import com.datscie.cinematix.models.TicketTable;

public class ReportController {
    private ReportDao dao;
    private MovieDao movieDao;
    private TicketDao ticketDao;

    public ReportController() {
        this.dao = new ReportDaoImpl();
        this.movieDao = new MovieDaoImpl();
        this.ticketDao = new TicketDaoImpl();
    }

    public void generateReport(JPanel panel, JLabel ticketSold, JLabel totalIncome, String date, String movieName) {
        Report report = new Report();

        try {
            if (movieName == null || movieName.equals("Select one")) {
                report = dao.getTotalTicketsByDate(date);
            } else {
                report = dao.getTotalTicketsByMovieAndDate(date, movieName);
            }

            ticketSold.setText(String.valueOf(report.getTicketsSold()));
            totalIncome.setText("Rp" + String.valueOf(report.getTotalIncome()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage());
        }
    }

    public void fetchMovieToComboBox(JPanel panel, javax.swing.JComboBox<String> movieComboBox) {
        try {
            movieComboBox.removeAllItems();
            movieComboBox.addItem("Select one");
            movieDao.getMovies().forEach(movie -> {
                movieComboBox.addItem(movie.getTitle());
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage());
        }
    }

    public void fetchTicketsToTable(JPanel panel, javax.swing.JTable table, String date, String movieName) {
        try {
            if (movieName == null || movieName.equals("Select one")) {
                table.setModel(new TicketTable(ticketDao.getTicketsByDate(date)));
            } else {
                table.setModel(new TicketTable(ticketDao.getTicketsByMovieAndDate(movieName, date)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage());
        }
    }
}
