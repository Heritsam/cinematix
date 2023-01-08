package com.datscie.cinematix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.datscie.cinematix.models.Report;
import com.datscie.cinematix.utils.ApplicationException;
import com.datscie.cinematix.utils.SqlClient;

public class ReportDaoImpl implements ReportDao {
    @Override
    public Report getTotalTicketsByDate(String date) throws ApplicationException {
        String sql;

        if (date.equals("Today")) {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets where date = CURDATE()";
        } else if (date.equals("This week")) {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets where date > DATE_SUB(CURDATE(), INTERVAL 7 DAY)";
        } else if (date.equals("This month")) {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets where date > DATE_SUB(CURDATE(), INTERVAL 30 DAY)";
        } else if (date.equals("This year")) {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets where date > DATE_SUB(CURDATE(), INTERVAL 365 DAY)";
        } else if (date.equals("All time")) {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets";
        } else {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets where date = curdate()";
        }

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            Report report = new Report();

            if (rs.next()) {
                report.setTicketsSold(rs.getInt("tickets_sold"));
                report.setTotalIncome(rs.getInt("total_income"));
            }

            return report;
        } catch (Exception e) {
            Logger.getLogger(ReportDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public Report getTotalTicketsByMovieAndDate(String date, String movie) throws ApplicationException {
        String sql;

        if (date.equals("Today")) {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets where date = CURDATE() and movie_id = (select id from movies where title = ?)";
        } else if (date.equals("This week")) {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets where date > DATE_SUB(CURDATE(), INTERVAL 7 DAY) and movie_id = (select id from movies where title = ?)";
        } else if (date.equals("This month")) {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets where date > DATE_SUB(CURDATE(), INTERVAL 30 DAY) and movie_id = (select id from movies where title = ?)";
        } else if (date.equals("This year")) {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets where date > DATE_SUB(CURDATE(), INTERVAL 365 DAY) and movie_id = (select id from movies where title = ?)";
        } else if (date.equals("All time")) {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets where movie_id = (select id from movies where title = ?)";
        } else {
            sql = "select count(*) tickets_sold, sum(total_price) total_income from tickets where date = CURDATE() and movie_id = (select id from movies where title = ?)";
        }

        System.out.println(sql + " " + movie);

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, movie);
            ResultSet rs = stmt.executeQuery();

            Report report = new Report();

            if (rs.next()) {
                report.setTicketsSold(rs.getInt("tickets_sold"));
                report.setTotalIncome(rs.getInt("total_income"));
            }

            return report;
        } catch (Exception e) {
            Logger.getLogger(ReportDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }
}
