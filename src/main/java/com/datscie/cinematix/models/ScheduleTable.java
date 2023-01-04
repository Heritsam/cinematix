package com.datscie.cinematix.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ScheduleTable extends AbstractTableModel {
    private String[] columnNames = {"id", "Movie", "Studio", "Time", "Price"};
    private List<Schedule> data;

    public ScheduleTable(List<Schedule> data) {
        this.data = data;
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getId();
            case 1:
                return data.get(rowIndex).getMovie().getTitle();
            case 2:
                return data.get(rowIndex).getStudio().getName();
            case 3:
                return data.get(rowIndex).getDateTime();
            case 4:
                return data.get(rowIndex).getPrice();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public Schedule getSchedule(int row) {
        return data.get(row);
    }
}
