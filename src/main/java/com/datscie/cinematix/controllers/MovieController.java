package com.datscie.cinematix.controllers;

import java.util.List;

import javax.swing.JOptionPane;

import com.datscie.cinematix.dao.MovieDao;
import com.datscie.cinematix.dao.MovieDaoImpl;
import com.datscie.cinematix.models.Movie;
import com.datscie.cinematix.models.MovieTable;
import com.datscie.cinematix.utils.ApplicationException;
import com.datscie.cinematix.views.MovieAddView;
import com.datscie.cinematix.views.MoviePanel;

public class MovieController {
    private MoviePanel view;
    private MovieAddView addView;
    private MovieDao dao;

    public MovieController(MoviePanel view) {
        this.view = view;
        this.dao = new MovieDaoImpl();
        fetchMovieToTable();
    }

    public void showAddMovieFrame() {
        addView = new MovieAddView(this);
        addView.setTitle("Add Movie");
        addView.setLocationRelativeTo(null);
        addView.setVisible(true);
    }

    public void showEditMovieFrame() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Please select a movie to edit");
            return;
        }

        MovieTable tableModel = (MovieTable) view.getTable().getModel();
        Movie movie = tableModel.getMovie(selectedRow);

        addView = new MovieAddView(this, movie);
        addView.setTitle("Edit Movie");
        addView.setLocationRelativeTo(null);
        addView.setVisible(true);
    }

    public void closeAddMovieFrame() {
        addView = null;
    }

    public void fetchMovieToTable() {
        try {
            List<Movie> movies = dao.getMovies();
            MovieTable tableModel = new MovieTable(movies);
            view.getTable().setModel(tableModel);
        } catch (ApplicationException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void addMovie() {
        if (addView.getInputTitle().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Title cannot be empty");
            return;
        }

        if (addView.getComboGenre().getSelectedItem().toString().equals("Select one")) {
            JOptionPane.showMessageDialog(addView, "Please select a genre");
            return;
        }

        if (addView.getInputDirector().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Director cannot be empty");
            return;
        }

        if (addView.getInputHours().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Hours cannot be empty");
            return;
        }

        if (addView.getInputMinutes().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Minutes cannot be empty");
            return;
        }

        if (addView.getInputHours().getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(addView, "Hours must be a number");
            return;
        }

        if (addView.getInputMinutes().getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(addView, "Minutes must be a number");
            return;
        }

        int hours = Integer.parseInt(addView.getInputHours().getText());
        int minutes = Integer.parseInt(addView.getInputMinutes().getText());

        if (hours < 0 || hours > 23) {
            JOptionPane.showMessageDialog(addView, "Hours must be between 0 and 23");
            return;
        }

        if (minutes < 0 || minutes > 59) {
            JOptionPane.showMessageDialog(addView, "Minutes must be between 0 and 59");
            return;
        }

        if (addView.getInputSynopsis().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Synopsis cannot be empty");
            return;
        }

        String title = addView.getInputTitle().getText();
        String genre = addView.getComboGenre().getSelectedItem().toString();
        String director = addView.getInputDirector().getText();
        int duration = hours * 60 + minutes;
        String synopsis = addView.getInputSynopsis().getText();

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setDirector(director);
        movie.setDuration(duration);
        movie.setSynopsis(synopsis);

        try {
            dao.addMovie(movie);

            JOptionPane.showMessageDialog(addView, "Movie added successfully");
            addView.dispose();

            fetchMovieToTable();
        } catch (ApplicationException e) {
            JOptionPane.showMessageDialog(addView, e.getMessage());
            return;
        }
    }

    public void updateMovie(int id) {
        if (addView.getInputTitle().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Title cannot be empty");
            return;
        }

        if (addView.getComboGenre().getSelectedItem().toString().equals("Select one")) {
            JOptionPane.showMessageDialog(addView, "Please select a genre");
            return;
        }

        if (addView.getInputDirector().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Director cannot be empty");
            return;
        }

        if (addView.getInputHours().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Hours cannot be empty");
            return;
        }

        if (addView.getInputMinutes().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Minutes cannot be empty");
            return;
        }

        if (addView.getInputHours().getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(addView, "Hours must be a number");
            return;
        }

        if (addView.getInputMinutes().getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(addView, "Minutes must be a number");
            return;
        }

        int hours = Integer.parseInt(addView.getInputHours().getText());
        int minutes = Integer.parseInt(addView.getInputMinutes().getText());

        if (hours < 0 || hours > 23) {
            JOptionPane.showMessageDialog(addView, "Hours must be between 0 and 23");
            return;
        }

        if (minutes < 0 || minutes > 59) {
            JOptionPane.showMessageDialog(addView, "Minutes must be between 0 and 59");
            return;
        }

        if (addView.getInputSynopsis().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Synopsis cannot be empty");
            return;
        }

        String title = addView.getInputTitle().getText();
        String genre = addView.getComboGenre().getSelectedItem().toString();
        String director = addView.getInputDirector().getText();
        int duration = hours * 60 + minutes;
        String synopsis = addView.getInputSynopsis().getText();

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setDirector(director);
        movie.setDuration(duration);
        movie.setSynopsis(synopsis);

        try {
            dao.updateMovie(movie);

            JOptionPane.showMessageDialog(addView, "Movie updated successfully");
            addView.dispose();

            fetchMovieToTable();
        } catch (ApplicationException e) {
            JOptionPane.showMessageDialog(addView, e.getMessage());
            return;
        }
    }

    public void deleteMovie() {
        int row = view.getTable().getSelectedRow();

        String title = view.getTable().getValueAt(row, 1).toString();

        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete " + title + "?",
                "Delete Movie", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            MovieTable movieTable = (MovieTable) view.getTable().getModel();
            Movie movie = movieTable.getMovie(row);

            try {
                dao.deleteMovie(movie);
                JOptionPane.showMessageDialog(view, "Movie deleted successfully");
                fetchMovieToTable();
            } catch (ApplicationException e) {
                JOptionPane.showMessageDialog(view, e.getMessage());
                return;
            }
        }
    }

    public void reset() {
        this.toggleButtonEditDelete(false);
    }

    public void toggleButtonEditDelete(boolean status) {
        view.getButtonEdit().setEnabled(status);
        view.getButtonDelete().setEnabled(status);
    }
}
