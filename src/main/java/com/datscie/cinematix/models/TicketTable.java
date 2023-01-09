package com.datscie.cinematix.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TicketTable extends AbstractTableModel {
    private String[] columnNames = {"id", "movie", "studio", "date", "seat"};
    private List<Ticket> data;

    public TicketTable(List<Ticket> data) {
        this.data = data;
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
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
                return data.get(rowIndex).getDateTime().toString();
            case 4:
                return data.get(rowIndex).getSeatNumber();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public Ticket getStudio(int row) {
        return data.get(row);
    }
}
