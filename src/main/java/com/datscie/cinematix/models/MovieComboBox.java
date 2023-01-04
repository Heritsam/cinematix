package com.datscie.cinematix.models;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class MovieComboBox extends AbstractListModel<Movie> implements ComboBoxModel<Movie> {
    ArrayList<Movie> movies;
    Movie selection = null;

    public MovieComboBox() {
        this.movies = new ArrayList<>();
    }

    public MovieComboBox(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getSize() {
        return movies.size();
    }

    @Override
    public Movie getElementAt(int index) {
        return movies.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (Movie) anItem;
    }

    @Override
    public Movie getSelectedItem() {
        return selection;
    }
}
