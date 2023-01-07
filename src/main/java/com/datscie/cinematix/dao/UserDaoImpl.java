package com.datscie.cinematix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.datscie.cinematix.models.Admin;
import com.datscie.cinematix.models.User;
import com.datscie.cinematix.utils.ApplicationException;
import com.datscie.cinematix.utils.SqlClient;

public class UserDaoImpl implements UserDao {
    @Override
    public User login(String email, String password) throws ApplicationException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND type = 'user'";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            User user = new User();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
            }

            if (user.getId() == 0) {
                throw new ApplicationException("These credentials do not match our records.");
            }

            return user;
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public User register(String name, String email, String password, String phone) throws ApplicationException {
        String sql = "INSERT INTO users (name, email, password, phone, type) VALUES (?, ?, ?, ?, 'user')";
        
        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, phone);

            stmt.executeUpdate();
            return login(email, password);
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public Admin loginAsAdmin(String email, String password) throws ApplicationException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND type = 'admin'";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            Admin admin = new Admin();

            while (rs.next()) {
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setPhone(rs.getString("phone"));
            }

            if (admin.getId() == 0) {
                throw new ApplicationException("Email or password is incorrect or you are not an admin.");
            }

            return admin;
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public ArrayList<User> getAllUsers() throws ApplicationException {
        String sql = "SELECT * FROM users WHERE type = 'user'";
        ArrayList<User> users = new ArrayList<>();

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                users.add(user);
            }

            return users;
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public User getUserById(int id) throws ApplicationException {
        String sql = "SELECT * FROM users WHERE id = ? AND type = 'user'";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            User user = new User();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
            }

            if (user.getId() == 0) {
                throw new ApplicationException("User not found.");
            }

            return user;
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public boolean passwordConfirmation(int id, String password) throws ApplicationException{
        String sql = "SELECT * FROM users WHERE id = ? AND password = ? AND type = 'user'";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                return true;
            }

            return false;
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public User updateName(int id, String name) throws ApplicationException {
        String sql = "UPDATE users SET name = ? WHERE id = ? AND type = 'user'";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);

            stmt.executeUpdate();
            return getUserById(id);
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    public User updateEmail(int id, String email) throws ApplicationException {
        String sql = "UPDATE users SET email = ? WHERE id = ? AND type = 'user'";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, id);

            stmt.executeUpdate();
            return getUserById(id);
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public User updatePhone(int id, String phone) throws ApplicationException {
        String sql = "UPDATE users SET phone = ? WHERE id = ? AND type = 'user'";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, phone);
            stmt.setInt(2, id);

            stmt.executeUpdate();
            return getUserById(id);
        } catch (Exception e) {
            Logger.getLogger(UserDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }
}
