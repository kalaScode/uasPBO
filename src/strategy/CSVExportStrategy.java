/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

/**
 *
 * @author Dell
 */
import model.Event;
import model.Peserta;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExportStrategy<T> implements ExportStrategy<T> {

    @Override
    public void export(List<T> data, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            if (data.get(0) instanceof Event) {
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Nama Event", "Deskripsi", "Tanggal", "Kuota", "Status"));
                for (T item : data) {
                    Event event = (Event) item;
                    csvPrinter.printRecord(event.getNamaEvent(), event.getDeskripsi(), event.getTanggal(), event.getKuota(), event.getStatus());
                }
                csvPrinter.flush();
            } else if (data.get(0) instanceof Peserta) {
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Nama", "NIM", "Email", "WhatsApp", "Event"));
                for (T item : data) {
                    Peserta peserta = (Peserta) item;
                    csvPrinter.printRecord(peserta.getIdPeserta(), peserta.getNama(), peserta.getNim(), peserta.getEmail(), peserta.getWhatsapp(), peserta.getNamaEvent());
                }
                csvPrinter.flush();
            }
        }
    }
}
