package com.datscie.cinematix.controllers;

import java.util.List;

import javax.swing.JOptionPane;

import com.datscie.cinematix.dao.StudioDao;
import com.datscie.cinematix.dao.StudioDaoImpl;
import com.datscie.cinematix.models.Studio;
import com.datscie.cinematix.models.StudioTable;
import com.datscie.cinematix.utils.ApplicationException;
import com.datscie.cinematix.views.StudioAddView;
import com.datscie.cinematix.views.StudioPanel;

public class StudioController {
    private StudioPanel view;
    private StudioAddView addView;
    private StudioDao dao;

    public StudioController(StudioPanel view) {
        this.view = view;
        this.dao = new StudioDaoImpl();
        fetchStudioToTable();
    }

    public void showAddStudioFrame() {
        addView = new StudioAddView(this);
        addView.setTitle("Add Studio");
        addView.setLocationRelativeTo(null);
        addView.setVisible(true);
    }

    public void showEditStudioFrame() {
        int selectedRow = view.getTable().getSelectedRow();

        StudioTable tableModel = (StudioTable) view.getTable().getModel();
        Studio studio = tableModel.getStudio(selectedRow);

        addView = new StudioAddView(this, studio);
        addView.setTitle("Edit Studio");
        addView.setLocationRelativeTo(null);
        addView.setVisible(true);
    }

    public void closeAddStudioFrame() {
        addView = null;
    }

    public void fetchStudioToTable() {
        try {
            List<Studio> studios = dao.getAllStudios();
            StudioTable tableModel = new StudioTable(studios);
            view.getTable().setModel(tableModel);
        } catch (ApplicationException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void addStudio() {
        if (addView.getInputStudioNo().getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Studio number cannot be empty");
            return;
        }

        if (addView.getInputSeats().getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Seats cannot be empty");
            return;
        }

        String studioNo = addView.getInputStudioNo().getText();
        String seats = addView.getInputSeats().getText();

        Studio studio = new Studio();
        studio.setName(studioNo);
        studio.setSeats(seats);

        try {
            dao.addStudio(studio);

            JOptionPane.showMessageDialog(addView, "Studio added successfully");
            addView.dispose();

            fetchStudioToTable();
        } catch (ApplicationException e) {
            JOptionPane.showMessageDialog(addView, e.getMessage());
            return;
        }
    }

    public void updateStudio(int id) {
        if (addView.getInputStudioNo().getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Studio number cannot be empty");
            return;
        }

        if (addView.getInputSeats().getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Seats cannot be empty");
            return;
        }

        String studioNo = addView.getInputStudioNo().getText();
        String seats = addView.getInputSeats().getText();

        Studio studio = new Studio();
        studio.setId(id);
        studio.setName(studioNo);
        studio.setSeats(seats);

        try {
            dao.updateStudio(studio);

            JOptionPane.showMessageDialog(addView, "Studio updated successfully");
            addView.dispose();

            fetchStudioToTable();
            view.getEditButton().setEnabled(false);
            view.getDeleteButton().setEnabled(false);
        } catch (ApplicationException e) {
            JOptionPane.showMessageDialog(addView, e.getMessage());
            return;
        }
    }

    public void deleteStudio() {
        int row = view.getTable().getSelectedRow();

        String title = view.getTable().getValueAt(row, 1).toString();

        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure to delete " + title + "?", "Delete Studio",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            StudioTable tableModel = (StudioTable) view.getTable().getModel();
            Studio studio = tableModel.getStudio(row);

            try {
                dao.deleteStudio(studio);
                JOptionPane.showMessageDialog(view, "Studio deleted successfully");
                fetchStudioToTable();
                view.getEditButton().setEnabled(false);
                view.getDeleteButton().setEnabled(false);
            } catch (ApplicationException e) {
                JOptionPane.showMessageDialog(view, e.getMessage());
                return;
            }
        }

    }

    public void reset() {
        addView.getInputStudioNo().setText("");
        addView.getInputSeats().setText("");
    }

    public void toggleButtonEditDelete(boolean enabled) {
        view.getEditButton().setEnabled(enabled);
        view.getDeleteButton().setEnabled(enabled);
    }
}
