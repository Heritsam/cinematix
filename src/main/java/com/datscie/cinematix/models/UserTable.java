/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.datscie.cinematix.models;

import javax.swing.table.AbstractTableModel;

import java.util.List;

/**
 *
 * @author akmal
 */
public class UserTable extends AbstractTableModel {
    private String[] columnNames = { "id", "name", "email", "phone" };
    private List<User> data;

    public UserTable(List<User> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getId();
            case 1:
                return data.get(rowIndex).getName();
            case 2:
                return data.get(rowIndex).getEmail();
            case 3:
                return data.get(rowIndex).getPhone();
            default:
                return null;
        }
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public User getUser(int row) {
        return data.get(row);
    }
}
