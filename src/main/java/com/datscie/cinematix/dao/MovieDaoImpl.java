package com.datscie.cinematix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.datscie.cinematix.models.Movie;
import com.datscie.cinematix.utils.ApplicationException;
import com.datscie.cinematix.utils.SqlClient;

public class MovieDaoImpl implements MovieDao {

    @Override
    public void addMovie(Movie movie) throws ApplicationException {
        String sql = "INSERT INTO movies (title, genre, director, duration) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setString(3, movie.getDirector());
            stmt.setInt(4, movie.getDuration());

            stmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(MovieDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void updateMovie(Movie movie) throws ApplicationException {
        String sql = "UPDATE movies SET title = ?, genre = ?, director = ?, duration = ? WHERE id = ?";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setString(3, movie.getDirector());
            stmt.setInt(4, movie.getDuration());
            stmt.setInt(5, movie.getId());

            stmt.executeUpdate();
            System.out.println("Movie updated");
        } catch (Exception e) {
            Logger.getLogger(MovieDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws ApplicationException {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, movie.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(MovieDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Movie> findMovieByTitle(String title) throws ApplicationException {
        String sql = "SELECT * FROM movies WHERE title LIKE ?";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, "%" + title + "%");
            
            ResultSet rs = stmt.executeQuery();
            ArrayList<Movie> movies = new ArrayList<Movie>();

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setDirector(rs.getString("director"));
                movie.setDuration(rs.getInt("duration"));

                movies.add(movie);
            }

            return movies;
        } catch (Exception e) {
            Logger.getLogger(MovieDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Movie> getMovies() throws ApplicationException {
        String sql = "SELECT * FROM movies";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<Movie> movies = new ArrayList<Movie>();

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setDirector(rs.getString("director"));
                movie.setDuration(rs.getInt("duration"));

                movies.add(movie);
            }

            return movies;
        } catch (Exception e) {
            Logger.getLogger(MovieDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

}
