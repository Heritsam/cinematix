package com.datscie.cinematix.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.datscie.cinematix.models.Studio;
import com.datscie.cinematix.utils.ApplicationException;
import com.datscie.cinematix.utils.SqlClient;

public class StudioDaoImpl implements StudioDao {
    @Override
    public ArrayList<Studio> getAllStudios() throws ApplicationException {
        String sql = "SELECT * FROM studios";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            ArrayList<Studio> studios = new ArrayList<Studio>();
            
            while (rs.next()) {
                Studio studio = new Studio();
                studio.setId(rs.getInt("id"));
                studio.setName(rs.getString("name"));
                studio.setSeats(rs.getString("seats"));
                studios.add(studio);
            }

            return studios;
        } catch (Exception e) {
            Logger.getLogger(StudioDaoImpl.class.getName()).severe(e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void addStudio(Studio studio) throws ApplicationException {
        String sql = "INSERT INTO studios (name, seats) VALUES (?, ?)";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, studio.getName());
            stmt.setString(2, studio.getSeatsString());

            stmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(StudioDaoImpl.class.getName());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void updateStudio(Studio studio) throws ApplicationException {
        String sql = "UPDATE studios SET name = ?, seats = ? WHERE id = ?";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setString(1, studio.getName());
            stmt.setString(2, studio.getSeatsString());
            stmt.setInt(3, studio.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(StudioDaoImpl.class.getName());
            throw new ApplicationException(e.getMessage());
        }
    }

    @Override
    public void deleteStudio(Studio studio) throws ApplicationException {
        String sql = "DELETE FROM studios WHERE id = ?";

        try (PreparedStatement stmt = SqlClient.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, studio.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(StudioDaoImpl.class.getName());
            throw new ApplicationException(e.getMessage());
        }
    }
}
