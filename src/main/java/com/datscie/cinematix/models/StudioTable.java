package com.datscie.cinematix.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class StudioTable extends AbstractTableModel {
    private String[] columnNames = {"id", "Studio no", "Seats"};
    private List<Studio> data;

    public StudioTable(List<Studio> data) {
        this.data = data;
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getId();
            case 1:
                return data.get(rowIndex).getName();
            case 2:
                return data.get(rowIndex).getSeats();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public Studio getStudio(int row) {
        return data.get(row);
    }
}
