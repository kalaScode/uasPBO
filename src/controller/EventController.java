/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Database.DatabaseManager;
import model.Event;
import model.Peserta;

/**
 *
 * @author Dell
 */
public class EventController {
    
     public static List<Event> getAllEvents1() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, namaEvent FROM event";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int eventID = rs.getInt("event_id");
                String eventName = rs.getString("namaEvent");

                Event event = new Event(eventID, eventName);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public Event getEventByID(int eventID) {
        Event event = null;
        String sql = "SELECT * FROM event WHERE event_id = ?";
        
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, eventID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("event_id");
                String eventName = rs.getString("namaEvent");
                
                event = new Event(id, eventName); 
                
            } else {
                System.out.println("Event with ID " + eventID + " not found");
            }
            
        } catch (SQLException e) {
            System.out.println("Error in getEventByID: " + e.getMessage());
        }
        
        return event;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT event_id, namaEvent, deskripsi, tanggal, kuota FROM event";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("event_id");
                String namaEvent = rs.getString("namaEvent");
                String deskripsi = rs.getString("deskripsi");
                String tanggal = rs.getString("tanggal");
                int kuota = rs.getInt("kuota");

                Event event = new Event(id, namaEvent, deskripsi, tanggal, kuota);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return events;
    }

    public void insertEvent(Event event) {
        String query = "INSERT INTO event (namaEvent, deskripsi, tanggal, kuota) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, event.getNamaEvent());
            stmt.setString(2, event.getDeskripsi());
            stmt.setString(3, event.getTanggal());
            stmt.setInt(4, event.getKuota());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Event berhasil ditambahkan");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menambah event: " + ex.getMessage());
        }
    }

    public void updateEvent(Event event) {
        String query = "UPDATE event SET namaEvent=?, deskripsi=?, tanggal=?, kuota=? WHERE event_id=?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, event.getNamaEvent());
            stmt.setString(2, event.getDeskripsi());
            stmt.setString(3, event.getTanggal());
            stmt.setInt(4, event.getKuota());
            stmt.setInt(5, event.getEventId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Event berhasil diperbarui");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal memperbarui event: " + ex.getMessage());
        }
    }

    public void deleteEvent(int eventId) {
        String query = "DELETE FROM event WHERE event_id=?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, eventId);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Event berhasil dihapus");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus event: " + ex.getMessage());
        }
    }
    
     public List<Event> getAllEventsJumlah() {
        List<Event> events = new ArrayList<>();
        Connection conn = DatabaseManager.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT event_id, namaEvent, deskripsi, tanggal, kuota, status FROM event";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("event_id"));
                event.setNamaEvent(rs.getString("namaEvent"));
                event.setDeskripsi(rs.getString("deskripsi"));
                event.setTanggal(rs.getString("tanggal"));
                event.setKuota(rs.getInt("kuota"));
                event.setStatus(rs.getString("status"));

                List<Peserta> pesertaList = PesertaController.getPesertaByEventId(event.getEventId());
                event.updateJumlahPendaftar(pesertaList);

                events.add(event);
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

        return events;
    }
    
     public static List<Event> getAllEventsExport() {
        List<Event> events = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseManager.getInstance().getConnection();
            String sql = "SELECT * FROM event";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("event_id"));
                event.setNamaEvent(rs.getString("namaEvent"));
                event.setDeskripsi(rs.getString("deskripsi"));
                event.setTanggal(rs.getString("tanggal"));
                event.setKuota(rs.getInt("kuota"));
                events.add(event);
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

        return events;
    }
public static List<Event> getEventsByNIM(int nim) {
    List<Event> events = new ArrayList<>();
    String sql = "SELECT e.event_id, e.namaEvent, e.deskripsi, e.tanggal " +
                 "FROM event e " +
                 "JOIN peserta p ON e.event_id = p.event_id " +
                 "WHERE p.nim = ?";

    try (Connection conn = DatabaseManager.getInstance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, nim);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int eventId = rs.getInt("event_id");
            String namaEvent = rs.getString("namaEvent");
            String deskripsi = rs.getString("deskripsi");
            String tanggal = rs.getString("tanggal");
            
            Event event = new Event(eventId, namaEvent, deskripsi, tanggal);
            
            String status = event.getStatus(); 

            event.setStatus(status); 

            events.add(event);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return events;
}

    public String validateEvent(Event event) {

        if (event.getNamaEvent().isEmpty()) {
            return "Nama Event tidak boleh kosong.";
        }

        if (event.getDeskripsi().isEmpty()) {
            return "Deskripsi Event tidak boleh kosong.";
        }

        if (event.getTanggal().isEmpty()) {
            return "Tanggal Event tidak boleh kosong.";
        }

        if (event.getKuota() <= 0) {
            return "Kuota Event harus lebih besar dari 0.";
        }

        return null; 
    }
    
    public static List<Event> getUpcomingEvents() {
         List<Event> events = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseManager.getInstance().getConnection();

            LocalDate today = LocalDate.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String sql = "SELECT event_id, namaEvent, tanggal FROM event WHERE tanggal > ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, formatter.format(today));
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("event_id");
                String namaEvent = rs.getString("namaEvent");
                String tanggalEvent = rs.getString("tanggal");

                Event event = new Event(id, namaEvent, tanggalEvent);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Gagal mengambil data event.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return events;
    }
}



