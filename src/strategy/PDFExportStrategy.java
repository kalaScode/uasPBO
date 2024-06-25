/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

/**
 *
 * @author Dell
 */

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Event;
import model.Peserta;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PDFExportStrategy<T> implements ExportStrategy<T> {

    @Override
    public void export(List<T> data, String filePath) throws IOException, DocumentException {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Paragraph title = new Paragraph("Data Export\n\n", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph dateParagraph = new Paragraph();
        dateParagraph.add(new Paragraph("Data diambil pada tanggal: " + getCurrentDate()) + "\n\n");
        dateParagraph.setAlignment(Element.ALIGN_LEFT);
        document.add(dateParagraph);

        PdfPTable table = new PdfPTable(getColumnCount(data.get(0)));
        table.setWidthPercentage(100);

        addTableHeaders(table, data.get(0));

        for (T item : data) {
            if (item instanceof Event) {
                Event event = (Event) item;
                addEventTableRow(table, event);
            } else if (item instanceof Peserta) {
                Peserta peserta = (Peserta) item;
                addPesertaTableRow(table, peserta);
            }
        }

        document.add(table);
        document.close();
    }

    private int getColumnCount(Object obj) {
        if (obj instanceof Event) {
            return 5; // Number of columns for Event
        } else if (obj instanceof Peserta) {
            return 6; // Number of columns for Peserta
        }
        return 0;
    }

    private void addTableHeaders(PdfPTable table, Object obj) {
        if (obj instanceof Event) {
            String[] headers = {"Nama Event", "Deskripsi", "Tanggal", "Kuota", "Status"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(header));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
        } else if (obj instanceof Peserta) {
            String[] headers = {"ID", "Nama", "NIM", "Email", "WhatsApp", "Event"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(header));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
        }
    }

    private void addEventTableRow(PdfPTable table, Event event) {
        table.addCell(event.getNamaEvent());
        table.addCell(event.getDeskripsi());
        table.addCell(event.getTanggal());
        table.addCell(String.valueOf(event.getKuota()));
        table.addCell(event.getStatus());
    }

    private void addPesertaTableRow(PdfPTable table, Peserta peserta) {
        table.addCell(String.valueOf(peserta.getIdPeserta()));
        table.addCell(peserta.getNama());
        table.addCell(peserta.getNim());
        table.addCell(peserta.getEmail());
        table.addCell(peserta.getWhatsapp());
        table.addCell(peserta.getNamaEvent());
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}

