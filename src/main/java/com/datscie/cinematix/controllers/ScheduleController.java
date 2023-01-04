package com.datscie.cinematix.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.datscie.cinematix.dao.MovieDao;
import com.datscie.cinematix.dao.MovieDaoImpl;
import com.datscie.cinematix.dao.ScheduleDao;
import com.datscie.cinematix.dao.ScheduleDaoImpl;
import com.datscie.cinematix.dao.StudioDao;
import com.datscie.cinematix.dao.StudioDaoImpl;
import com.datscie.cinematix.models.Movie;
import com.datscie.cinematix.models.Schedule;
import com.datscie.cinematix.models.ScheduleTable;
import com.datscie.cinematix.models.Studio;
import com.datscie.cinematix.utils.ApplicationException;
import com.datscie.cinematix.views.ScheduleAddView;
import com.datscie.cinematix.views.SchedulePanel;

public class ScheduleController {
    private SchedulePanel view;
    private ScheduleAddView addView;
    private ScheduleDao scheduleDao;
    private MovieDao movieDao;
    private StudioDao studioDao;

    public ScheduleController(SchedulePanel view) {
        this.view = view;
        this.scheduleDao = new ScheduleDaoImpl();
        this.movieDao = new MovieDaoImpl();
        this.studioDao = new StudioDaoImpl();
        fetchScheduleToTable();
    }

    public void showAddScheduleForm() {
        addView = new ScheduleAddView(this);
        addView.setLocationRelativeTo(null);
        addView.setVisible(true);
    }

    public void showEditScheduleForm() {
        int selectedRow = view.getTable().getSelectedRow();

        ScheduleTable scheduleTable = (ScheduleTable) view.getTable().getModel();
        Schedule schedule = scheduleTable.getSchedule(selectedRow);

        addView = new ScheduleAddView(this, schedule);
        addView.setLocationRelativeTo(null);
        addView.setVisible(true);
    }

    public void closeAddSchefuleForm() {
        addView = null;
    }

    public void fetchScheduleToTable() {
        try {
            ArrayList<Schedule> schedules = scheduleDao.getAllSchedule();
            ScheduleTable tableModel = new ScheduleTable(schedules);
            view.getTable().setModel(tableModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void addSchedule() {
        if (addView.getComboMovie().getSelectedItem().equals("Select one")) {
            JOptionPane.showMessageDialog(addView, "Please select a movie");
            return;
        }

        if (addView.getComboStudio().getSelectedItem().equals("Select one")) {
            JOptionPane.showMessageDialog(addView, "Please select a studio");
            return;
        }

        String month = addView.getComboMonth().getSelectedIndex() + 1 < 10
                ? "0" + (addView.getComboMonth().getSelectedIndex() + 1)
                : "" + (addView.getComboMonth().getSelectedIndex() + 1);

        LocalDate date = LocalDate
                .parse(addView.getComboYear().getSelectedItem().toString() + "-" + month + "-"
                        + addView.getComboDay().getSelectedItem().toString());
        LocalTime time = LocalTime.parse(
                addView.getComboHour().getSelectedItem() + ":" + addView.getComboMinutes().getSelectedItem() + ":00");
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        if (dateTime.isBefore(LocalDateTime.now())) {
            JOptionPane.showMessageDialog(addView, "Please select a valid date and time");
            return;
        }

        if (addView.getInputPrice().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Please input a price");
            return;
        }

        try {
            Schedule schedule = new Schedule();
            schedule.setMovie((Movie) addView.getComboMovie().getSelectedItem());
            schedule.setStudio((Studio) addView.getComboStudio().getSelectedItem());
            schedule.setDateTime(dateTime);
            schedule.setPrice(Integer.parseInt(addView.getInputPrice().getText()));

            scheduleDao.addSchedule(schedule);
            JOptionPane.showMessageDialog(addView, "Schedule added successfully");
            fetchScheduleToTable();
            addView.dispose();
        } catch (ApplicationException e) {
            JOptionPane.showMessageDialog(addView, e.getMessage());
        }
    }

    public void updateSchedule(int id) {
        if (addView.getComboMovie().getSelectedItem().equals("Select one")) {
            JOptionPane.showMessageDialog(addView, "Please select a movie");
            return;
        }

        if (addView.getComboStudio().getSelectedItem().equals("Select one")) {
            JOptionPane.showMessageDialog(addView, "Please select a studio");
            return;
        }

        String month = addView.getComboMonth().getSelectedIndex() + 1 < 10
                ? "0" + (addView.getComboMonth().getSelectedIndex() + 1)
                : "" + (addView.getComboMonth().getSelectedIndex() + 1);

        LocalDate date = LocalDate
                .parse(addView.getComboYear().getSelectedItem().toString() + "-" + month + "-"
                        + addView.getComboDay().getSelectedItem().toString());
        LocalTime time = LocalTime.parse(
                addView.getComboHour().getSelectedItem() + ":" + addView.getComboMinutes().getSelectedItem() + ":00");
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        if (dateTime.isBefore(LocalDateTime.now())) {
            JOptionPane.showMessageDialog(addView, "The date and time must be in the future");
            return;
        }

        if (addView.getInputPrice().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addView, "Please input a price");
            return;
        }

        try {
            Schedule schedule = new Schedule();
            schedule.setId(id);
            schedule.setMovie((Movie) addView.getComboMovie().getSelectedItem());
            schedule.setStudio((Studio) addView.getComboStudio().getSelectedItem());
            schedule.setDateTime(dateTime);
            schedule.setPrice(Integer.parseInt(addView.getInputPrice().getText()));

            scheduleDao.updateSchedule(schedule);
            JOptionPane.showMessageDialog(addView, "Schedule updated successfully");
            fetchScheduleToTable();
            addView.dispose();
        } catch (ApplicationException e) {
            JOptionPane.showMessageDialog(addView, e.getMessage());
        }
    }

    public void deleteSchedule() {
        int row = view.getTable().getSelectedRow();

        String title = view.getTable().getValueAt(row, 1).toString();

        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete " + title + "?",
                "Delete Schedule", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                ScheduleTable scheduleTable = (ScheduleTable) view.getTable().getModel();
                Schedule schedule = scheduleTable.getSchedule(row);

                scheduleDao.deleteSchedule(schedule);
                JOptionPane.showMessageDialog(view, "Schedule deleted successfully");
                fetchScheduleToTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, e.getMessage());
            }
        }
    }

    public void reset() {
        view.getButtonEdit().setEnabled(false);
        view.getButtonDelete().setEnabled(false);
    }

    public ArrayList<Movie> getMovies() {
        try {
            return movieDao.getMovies();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
            return null;
        }
    }

    public ArrayList<Studio> getStudios() {
        try {
            return studioDao.getAllStudios();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
            return null;
        }
    }
}
