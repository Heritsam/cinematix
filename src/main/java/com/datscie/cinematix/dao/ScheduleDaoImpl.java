package com.datscie.cinematix.dao;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.datscie.cinematix.models.Movie;
import com.datscie.cinematix.models.Schedule;
import com.datscie.cinematix.models.Studio;
import com.datscie.cinematix.utils.ApplicationException;
import com.datscie.cinematix.utils.SqlClient;
import java.sql.ResultSet;
import java.sql.Statement;

public class ScheduleDaoImpl implements ScheduleDao {
    @Override
    public ArrayList<Schedule> getAllSchedule() throws ApplicationException {
        String sql = "select * from schedules inner join movies m on schedules.movie_id = m.id inner join studios s on schedules.studio_id = s.id";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            ArrayList<Schedule> schedules = new ArrayList<Schedule>();

            while (rs.next()) {
                Schedule schedule = new Schedule();

                schedule.setId(rs.getInt("id"));
                schedule.setMovie(new Movie(rs.getInt("m.id"), rs.getString("title"), rs.getString("genre"),
                        rs.getString("director"), rs.getInt("duration"), rs.getString("synopsis")));
                schedule.setStudio(new Studio(rs.getInt("s.id"), rs.getString("name"), rs.getString("seats")));
                schedule.setDateTime(LocalDateTime.parse(rs.getString("date_time"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                schedule.setPrice(rs.getInt("price"));

                schedules.add(schedule);
            }

            return schedules;
        } catch (Exception e) {
            Logger.getLogger(ScheduleDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void addSchedule(Schedule schedule) throws ApplicationException {
        String sql = "insert into schedules (movie_id, studio_id, date_time, price) values (?, ?, ?, ?)";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, schedule.getMovie().getId());
            stmt.setInt(2, schedule.getStudio().getId());
            stmt.setString(3, schedule.getDateTimeString());
            stmt.setInt(4, schedule.getPrice());

            stmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(ScheduleDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void updateSchedule(Schedule schedule) throws ApplicationException {
        String sql = "update schedules set movie_id = ?, studio_id = ?, date_time = ?, price = ? where id = ?";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, schedule.getMovie().getId());
            stmt.setInt(2, schedule.getStudio().getId());
            stmt.setString(3, schedule.getDateTimeString());
            stmt.setInt(4, schedule.getPrice());
            stmt.setInt(5, schedule.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(ScheduleDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void deleteSchedule(Schedule schedule) throws ApplicationException {
        String sql = "delete from schedules where id = ?";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, schedule.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(ScheduleDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Schedule> getScheduleByMovieId(int movieId) throws ApplicationException {
        String sql = "select * from schedules inner join movies m on schedules.movie_id = m.id inner join studios s on schedules.studio_id = s.id where m.id = ?";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, movieId);

            ResultSet rs = stmt.executeQuery();

            ArrayList<Schedule> schedules = new ArrayList<Schedule>();

            while (rs.next()) {
                Schedule schedule = new Schedule();

                schedule.setId(rs.getInt("id"));
                schedule.setMovie(new Movie(rs.getInt("m.id"), rs.getString("title"), rs.getString("genre"),
                        rs.getString("director"), rs.getInt("duration"), rs.getString("synopsis")));
                schedule.setStudio(new Studio(rs.getInt("s.id"), rs.getString("name"), rs.getString("seats")));
                schedule.setDateTime(LocalDateTime.parse(rs.getString("date_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                schedule.setPrice(rs.getInt("price"));

                schedules.add(schedule);
            }

            return schedules;
        } catch (Exception e) {
            Logger.getLogger(ScheduleDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }
}
