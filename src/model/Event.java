/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Event {
    private int id;
    private String namaEvent;
    private String deskripsi;
    private String tanggal;
    private int kuota;
    private String status;
    private int jumlahPendaftar;
    

    public Event(int id, String namaEvent, String deskripsi, String tanggal, int kuota) {
        this.id = id;
        this.namaEvent = namaEvent;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
        this.kuota = kuota;
    }

    public Event() {
    }

    public Event(int id, String namaEvent, String tanggal) {
        this.id = id;
        this.namaEvent = namaEvent;
        this.tanggal = tanggal;
    }
    
    
    public Event(int id, String namaEvent, String deskripsi, String tanggal, String status) {
        this.id = id;
        this.namaEvent = namaEvent;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
        this.status = status;
    }
    public Event(int event_Id, String namaEvent, String deskripsi, String tanggal) {
        this.id = event_Id;
        this.namaEvent = namaEvent;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
    }

    public Event(int id, String namaEvent) {
        this.id = id;
        this.namaEvent = namaEvent;
    }
   

    public int getEventId() {
        return id;
    }

    public void setEventId(int id) {
        this.id = id;
    }

    public String getNamaEvent() {
        return namaEvent;
    }

    public void setNamaEvent(String namaEvent) {
        this.namaEvent = namaEvent;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
        this.kuota = kuota;
    }
    
    public String getStatus() {
        if (tanggal == null) {
            return "Undefined";
        }

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate eventDate = LocalDate.parse(tanggal, formatter);
            if (eventDate.isAfter(today)) {
                return "Akan Datang";
            } else if (eventDate.equals(today)) {
                return "Sedang Berlangsung";
            } else {
                return "Selesai";
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return "Parse Error";
        }
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public void updateJumlahPendaftar(List<Peserta> pesertaList) {
        this.jumlahPendaftar = 0;

        for (Peserta peserta : pesertaList) {
            if (peserta.getEventID() == this.id) {
                this.jumlahPendaftar++;
            }
        }
    }

    public int getJumlahPendaftar() {
        return jumlahPendaftar;
    }

    public void setJumlahPendaftar(int jumlahPendaftar) {
        this.jumlahPendaftar = jumlahPendaftar;
    }

    @Override
    public String toString() {
        return id + " - " + namaEvent;
    }
}

