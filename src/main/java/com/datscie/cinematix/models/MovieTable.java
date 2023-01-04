package com.datscie.cinematix.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MovieTable extends AbstractTableModel {
    private String[] columnNames = { "id", "Title", "Genre", "Director", "Duration", "Synopsis" };
    private List<Movie> data;

    public MovieTable(List<Movie> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getId();
            case 1:
                return data.get(rowIndex).getTitle();
            case 2:
                return data.get(rowIndex).getGenre();
            case 3:
                return data.get(rowIndex).getDirector();
            case 4:
                return data.get(rowIndex).getDuration();
            case 5:
                return data.get(rowIndex).getSynopsis();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Movie getMovie(int row) {
        return data.get(row);
    }
}
