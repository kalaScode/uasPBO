package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Database.DatabaseManager;
import model.Event;
import model.Peserta;

public class PesertaController {

    public static List<Peserta> getPesertaByEventId(int eventId) {
        List<Peserta> pesertaList = new ArrayList<>();
        Connection conn = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT peserta_id, nama, nim, email, whatsapp FROM peserta WHERE event_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, eventId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Peserta peserta = new Peserta();
                peserta.setId(rs.getInt("peserta_id"));
                peserta.setNama(rs.getString("nama"));
                peserta.setNim(rs.getString("nim"));
                peserta.setEmail(rs.getString("email"));
                peserta.setWhatsapp(rs.getString("whatsapp"));
                peserta.setEventID(eventId);
                pesertaList.add(peserta);
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

        return pesertaList;
        }   
    }
    
    public static List<Peserta> getAllPeserta() {
        List<Peserta> pesertaList = new ArrayList<>();
        String sql = "SELECT p.id_peserta, p.nama, p.nim, p.email, p.wa, p.event_id, e.namaEvent " +
                     "FROM peserta p JOIN event e ON p.event_id = e.event_id";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_peserta");
                String nama = rs.getString("nama");
                String nim = rs.getString("nim");
                String email = rs.getString("email");
                String whatsapp = rs.getString("wa");
                int eventID = rs.getInt("event_id");
                String namaEvent = rs.getString("namaEvent");

                Peserta peserta = new Peserta(id, nama, nim, email, whatsapp, eventID, namaEvent);
                pesertaList.add(peserta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pesertaList;
    }
    
    public static void deleteParticipantFromDatabase(int idPeserta) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseManager.getInstance().getConnection();
            String sql = "DELETE FROM peserta WHERE id_peserta = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPeserta);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public static Peserta getParticipantById(int idPeserta) {
        Peserta peserta = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT id_peserta, nama, nim, email, wa, event_id FROM peserta WHERE id_peserta = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPeserta);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_peserta");
                String nama = rs.getString("nama");
                String nim = rs.getString("nim");
                String email = rs.getString("email");
                String whatsapp = rs.getString("wa");
                int eventId = rs.getInt("event_id");

                EventController eventController = new EventController();
                Event event = eventController.getEventByID(eventId);

                peserta = new Peserta(id, nama, nim, email, whatsapp, event.getEventId(), event.getNamaEvent());
            } else {
                JOptionPane.showMessageDialog(null, "Participant with ID " + idPeserta + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Failed to retrieve participant data.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return peserta;
    }
    
    public static void updateParticipantInDatabase(int idPeserta, String nama, String nim, String email, String whatsapp, int eventId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseManager.getInstance().getConnection();
            String sql = "UPDATE peserta SET nama = ?, nim = ?, email = ?, wa = ?, event_id = ? WHERE id_peserta = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            stmt.setString(2, nim);
            stmt.setString(3, email);
            stmt.setString(4, whatsapp);
            stmt.setInt(5, eventId);
            stmt.setInt(6, idPeserta);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Failed to update participant data.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void insertParticipantIntoDatabase(String nama, String nim, String email, String whatsapp, Event event) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseManager.getInstance().getConnection();
            String sql = "INSERT INTO peserta (nama, nim, email, wa, event_id) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            stmt.setString(2, nim);
            stmt.setString(3, email);
            stmt.setString(4, whatsapp);
            stmt.setInt(5, event.getEventId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Peserta baru berhasil ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Gagal menambahkan peserta baru.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String validasiPeserta(String nama, String nim, String email, String whatsapp, Event event) {
        // Validasi Nama
        if (nama.isEmpty()) {
            return "Nama harus diisi.";
        }

        // Validasi NIM harus angka dan terdiri dari 9 digit
        if (!nim.matches("\\d{9}")) {
            return "NIM harus terdiri dari 9 digit angka.";
        }

        // Validasi Email harus mengandung @stis.ac.id
        if (!email.toLowerCase().endsWith("@stis.ac.id")) {
            return "Email harus berakhiran dengan @stis.ac.id.";
        }

        // Validasi Nomor WhatsApp maksimal 13 karakter dan harus angka
        if (whatsapp.length() > 13 || !whatsapp.matches("\\d+")) {
            return "Nomor WhatsApp maksimal 13 karakter dan harus berupa angka.";
        }

        // Validasi Event
        if (event == null) {
            return "Pilih event terlebih dahulu.";
        }

        return null; 
    }

    
}