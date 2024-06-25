/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Dell
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import Database.DatabaseManager;
import model.User;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserController {

    public boolean registerUser(User user) {
        String query = "INSERT INTO users (nim, username, password, role, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, user.getNim());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getRole());
            stmt.setString(5, user.getEmail());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal mendaftarkan pengguna: " + ex.getMessage());
            return false;
        }
    }

    public boolean updateUser(User user) {
        String query = "UPDATE users SET username = ?, password = ?, role = ?, email = ? WHERE nim = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getRole());
            stmt.setString(4, user.getEmail());
            stmt.setInt(5, user.getNim());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal mengubah informasi pengguna: " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteUser(int nim) {
        String query = "DELETE FROM users WHERE nim = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nim);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menghapus pengguna: " + ex.getMessage());
            return false;
        }
    }
    

    public boolean isRegistered(int nim, String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean registered = false;

        try {
            conn = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT * FROM users WHERE nim = ? or username=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nim);
            stmt.setString(2, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                registered = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
         
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return registered;
    }
}

