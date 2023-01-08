package com.datscie.cinematix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.datscie.cinematix.models.Movie;
import com.datscie.cinematix.models.Studio;
import com.datscie.cinematix.models.Ticket;
import com.datscie.cinematix.models.User;
import com.datscie.cinematix.utils.ApplicationException;
import com.datscie.cinematix.utils.SqlClient;

public class TicketDaoImpl implements TicketDao {

    @Override
    public ArrayList<Ticket> getAllTicket() throws ApplicationException {
        String sql = "select * from tickets left join movies m on tickets.movie_id = m.id left join studios on tickets.studio_id = studios.id";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            ArrayList<Ticket> tickets = new ArrayList<>();

            while (rs.next()) {
                Ticket ticket = new Ticket();

                ticket.setId(rs.getInt("tickets.id"));
                ticket.setDateTime(LocalDateTime.parse(rs.getString("date")));
                ticket.setSeatNumber(rs.getString("tickets.seats"));

                Movie movie = new Movie();
                movie.setId(rs.getInt("m.id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setGenre(rs.getString("genre"));
                movie.setDuration(rs.getInt("duration"));
                movie.setSynopsis(rs.getString("synopsis"));
                Studio studio = new Studio();
                studio.setId(rs.getInt("studios.id"));
                studio.setName(rs.getString("studios.name"));
                studio.setSeats(rs.getString("studios.seats"));

                ticket.setMovie(movie);
                ticket.setStudio(studio);
                
                tickets.add(ticket);
            }

            return tickets;
        } catch (Exception e) {
            Logger.getLogger(TicketDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Ticket> getAllTicketByUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Ticket> getTicketsByDate(String date) throws ApplicationException {
        String sql = "select * from tickets left join movies m on tickets.movie_id = m.id left join studios on tickets.studio_id = studios.id";

        if (date.equals("Today")) {
            sql += " where date = CURDATE()";
        } else if (date.equals("This week")) {
            sql += " where date <= CURDATE() + INTERVAL 7 DAY";
        } else if (date.equals("This month")) {
            sql += " where date <= CURDATE() + INTERVAL 30 DAY";
        } else if (date.equals("This year")) {
            sql += " where date <= CURDATE() + INTERVAL 365 DAY";
        } else if (date.equals("All time")) {
            sql += "";
        }

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            ArrayList<Ticket> tickets = new ArrayList<>();

            while (rs.next()) {
                Ticket ticket = new Ticket();

                ticket.setId(rs.getInt("tickets.id"));
                ticket.setDateTime(LocalDateTime.parse(rs.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                ticket.setSeatNumber(rs.getString("tickets.seats"));

                Movie movie = new Movie();
                movie.setId(rs.getInt("m.id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setGenre(rs.getString("genre"));
                movie.setDuration(rs.getInt("duration"));
                movie.setSynopsis(rs.getString("synopsis"));
                Studio studio = new Studio();
                studio.setId(rs.getInt("studios.id"));
                studio.setName(rs.getString("studios.name"));
                studio.setSeats(rs.getString("studios.seats"));

                ticket.setMovie(movie);
                ticket.setStudio(studio);
                
                tickets.add(ticket);
            }

            return tickets;
        } catch (Exception e) {
            Logger.getLogger(TicketDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Ticket> getTicketsByMovieAndDate(String movieName, String date) throws ApplicationException {
        String sql = "select * from tickets left join movies m on tickets.movie_id = m.id left join studios on tickets.studio_id = studios.id";

        if (date.equals("Today")) {
            sql += " where date = CURDATE()";
        } else if (date.equals("This week")) {
            sql += " where date <= CURDATE() + INTERVAL 7 DAY";
        } else if (date.equals("This month")) {
            sql += " where date <= CURDATE() + INTERVAL 30 DAY";
        } else if (date.equals("This year")) {
            sql += " where date <= CURDATE() + INTERVAL 365 DAY";
        } else if (date.equals("All time")) {
            sql += "";
        }

        sql += " and movie_id = (select id from movies where title = ?)";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, movieName);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Ticket> tickets = new ArrayList<>();

            while (rs.next()) {
                Ticket ticket = new Ticket();

                ticket.setId(rs.getInt("tickets.id"));
                ticket.setDateTime(LocalDateTime.parse(rs.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                ticket.setSeatNumber(rs.getString("tickets.seats"));

                Movie movie = new Movie();
                movie.setId(rs.getInt("m.id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setGenre(rs.getString("genre"));
                movie.setDuration(rs.getInt("duration"));
                movie.setSynopsis(rs.getString("synopsis"));
                Studio studio = new Studio();
                studio.setId(rs.getInt("studios.id"));
                studio.setName(rs.getString("studios.name"));
                studio.setSeats(rs.getString("studios.seats"));

                ticket.setMovie(movie);
                ticket.setStudio(studio);
                
                tickets.add(ticket);
            }

            return tickets;
        } catch (Exception e) {
            Logger.getLogger(TicketDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new ApplicationException(e.getMessage());
        }
    }
}
