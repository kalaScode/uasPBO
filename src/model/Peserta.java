/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */
public class Peserta {
    private int id;
    private String nama;
    private String nim;
    private String email;
    private String whatsapp;
    private int event_id; 
    private String namaEvent;
    
    public Peserta(int id, String nama, String nim, String email, String whatsapp, int event_id, String namaEvent) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.email = email;
        this.whatsapp = whatsapp;
        this.event_id = event_id;
        this.namaEvent = namaEvent;
    }

    public Peserta(int id, String nama, String nim, String email, String whatsapp, String namaEvent) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.email = email;
        this.whatsapp = whatsapp;
        this.namaEvent = namaEvent;
    }

    public Peserta() {
    }    

    
    public String getNamaEvent() {
        return namaEvent;
    }

    public int getIdPeserta() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getEmail() {
        return email;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public int getEventID() {
        return event_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public void setEventID(int event_id) {
        this.event_id = event_id;
    }

    public void setNamaEvent(String namaEvent) {
        this.namaEvent = namaEvent;
    }
    
    
}

