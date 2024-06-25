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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.MenuAdmin;
import view.MenuUser;
import Database.DatabaseManager;
import model.LoggedInUser;
import model.User;
import view.MenuAkun;

public class LoginController {

    public User authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int nim = rs.getInt("nim");
                String email = rs.getString("email");
                int role = rs.getInt("role");

                User user = new User(nim, username, password, role, email);
                JOptionPane.showMessageDialog(null, "Login Berhasil");
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Koneksi ke database gagal.");
            return null;
        }
    }

   public void login(String username, String password) {
    User user = authenticateUser(username, password);
    if (user != null) {
        LoggedInUser.setUser(user);

        MenuAkun menuAkun = new MenuAkun(user);
        menuAkun.setVisible(true);

        switch (user.getRole()) {
            case 1: 
                MenuAdmin menuAdmin = new MenuAdmin(user);
                menuAdmin.setVisible(true);
                break;
            case 2: 
                MenuUser menuUser = new MenuUser(user);
                menuUser.setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Peran pengguna tidak diketahui.");
                break;
        }
    } else {
        JOptionPane.showMessageDialog(null, "Autentikasi gagal. Periksa username dan password Anda.");
    }
    }
    
    public void registerNewUser(User newUser) {
        UserController userController = new UserController();
        
        boolean success = userController.registerUser(newUser);
        if (success) {
            JOptionPane.showMessageDialog(null, "Pendaftaran pengguna berhasil.");
        } else {
            JOptionPane.showMessageDialog(null, "Gagal melakukan pendaftaran pengguna.");
        }
    }
}






