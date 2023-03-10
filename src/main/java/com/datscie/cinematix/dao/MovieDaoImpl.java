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
        String sql = "INSERT INTO movies (title, genre, director, duration, synopsis) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setString(3, movie.getDirector());
            stmt.setInt(4, movie.getDuration());
            stmt.setString(5, movie.getSynopsis());

            stmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(MovieDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void updateMovie(Movie movie) throws ApplicationException {
        String sql = "UPDATE movies SET title = ?, genre = ?, director = ?, duration = ?, synopsis = ? WHERE id = ?";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setString(3, movie.getDirector());
            stmt.setInt(4, movie.getDuration());
            stmt.setString(5, movie.getSynopsis());
            stmt.setInt(6, movie.getId());

            stmt.executeUpdate();
            System.out.println(movie.getId() + " " + movie.getTitle() + " " + movie.getGenre() + " " + movie.getDirector() + " " + movie.getDuration());
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
                movie.setSynopsis(rs.getString("synopsis"));

                movies.add(movie);
            }

            stmt.close();

            return movies;
        } catch (Exception e) {
            Logger.getLogger(MovieDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Movie> searchMovie(String title) throws ApplicationException {
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
                movie.setSynopsis(rs.getString("synopsis"));

                movies.add(movie);
            }

            stmt.close();

            return movies;
        } catch (Exception e) {
            Logger.getLogger(MovieDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    public ArrayList<Movie> getNowPlayingMovies() throws ApplicationException {
        String sql = "select distinct m.id, title, genre, director, duration, synopsis from schedules inner join movies m on schedules.movie_id = m.id inner join studios s on schedules.studio_id = s.id;";

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
                movie.setSynopsis(rs.getString("synopsis"));

                movies.add(movie);
            }

            stmt.close();

            return movies;
        } catch (Exception e) {
            Logger.getLogger(MovieDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public Movie getMoviesById(int id) throws ApplicationException {
        String sql = "SELECT * FROM movies WHERE id = ?";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Movie movie = new Movie();

            while (rs.next()) {
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setDirector(rs.getString("director"));
                movie.setDuration(rs.getInt("duration"));
                movie.setSynopsis(rs.getString("synopsis"));
            }

            stmt.close();

            return movie;
        } catch (Exception e) {
            Logger.getLogger(MovieDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public Movie getMoviesByTitle(String title) throws ApplicationException {
        String sql = "SELECT * FROM movies WHERE title = ?";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, title);

            ResultSet rs = stmt.executeQuery();
            Movie movie = new Movie();

            while (rs.next()) {
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setGenre(rs.getString("genre"));
                movie.setDirector(rs.getString("director"));
                movie.setDuration(rs.getInt("duration"));
                movie.setSynopsis(rs.getString("synopsis"));
            }

            stmt.close();

            return movie;
        } catch (Exception e) {
            Logger.getLogger(MovieDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

}
