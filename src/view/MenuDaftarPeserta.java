/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;


import controller.EventController;
import controller.PesertaController;
import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Event;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Peserta;
import model.User;


/**
 *
 * @author Dell
 */
public class MenuDaftarPeserta extends javax.swing.JPanel {
private User loggedInUser;

    /**
     * Creates new form menuDashboard
     */
    private DefaultTableModel tableModel;

    public MenuDaftarPeserta(User user) {
        initComponents();
        populateTable();
        this.loggedInUser = user;
        
        if (loggedInUser != null && loggedInUser.getRole() != 1) {
            buttonEdit.setVisible(false);
            buttonDelete.setVisible(false);
        }
        
    }

    private void populateTable() {
        tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);

        List<Peserta> participants = PesertaController.getAllPeserta();

        for (Peserta p : participants) {
            Object[] row = {p.getIdPeserta(), p.getNama(), p.getNim(), p.getNamaEvent()};
            tableModel.addRow(row);
        }
    }

    private void showEditDialog(Peserta peserta) {
        JTextField fieldNamaEdit = new JTextField(peserta.getNama(), 20);
        JTextField fieldNIMEdit = new JTextField(peserta.getNim(), 20);
        JTextField fieldEmailEdit = new JTextField(peserta.getEmail(), 20);
        JTextField fieldWhatsappEdit = new JTextField(peserta.getWhatsapp(), 20);
        JComboBox<Event> eventComboBox = new JComboBox<>();

        JPanel editPanel = new JPanel(new GridLayout(0, 1));
        editPanel.add(new JLabel("Nama:"));
        editPanel.add(fieldNamaEdit);
        editPanel.add(new JLabel("NIM:"));
        editPanel.add(fieldNIMEdit);
        editPanel.add(new JLabel("Email:"));
        editPanel.add(fieldEmailEdit);
        editPanel.add(new JLabel("WhatsApp:"));
        editPanel.add(fieldWhatsappEdit);
        editPanel.add(new JLabel("Event:"));
        
        List<Event> events = EventController.getUpcomingEvents();
        DefaultComboBoxModel<Event> eventModel = new DefaultComboBoxModel<>(events.toArray(new Event[0]));
        eventComboBox.setModel(eventModel);
        editPanel.add(eventComboBox);

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getNamaEvent().equals(peserta.getNamaEvent())) {
                eventComboBox.setSelectedIndex(i);
                break;
            }
        }

        while (true) {
            int result = JOptionPane.showConfirmDialog(null, editPanel,
                    "Edit Data Peserta", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String nama = fieldNamaEdit.getText();
                String nim = fieldNIMEdit.getText();
                String email = fieldEmailEdit.getText();
                String whatsapp = fieldWhatsappEdit.getText();
                Event selectedEvent = (Event) eventComboBox.getSelectedItem();

                // Validasi input sebelum mengubah data
                String validationMessage = PesertaController.validasiPeserta(nama, nim, email, whatsapp, selectedEvent);
                if (validationMessage != null) {
                    JOptionPane.showMessageDialog(null, validationMessage, "Error", JOptionPane.ERROR_MESSAGE);
                    continue; 
                }

                // Update peserta ke database
                PesertaController.updateParticipantInDatabase(peserta.getIdPeserta(), nama, nim, email, whatsapp, selectedEvent.getEventId());

                JOptionPane.showMessageDialog(null, "Data peserta berhasil diubah.");
                break; 
            } else {
                break;
            }
        }
    }


    private void showInsertDialog() {
        
        JTextField fieldNama = new JTextField(20);
        JTextField fieldNIM = new JTextField(20);
        JTextField fieldEmail = new JTextField(20);
        JTextField fieldWhatsApp = new JTextField(20);
        JComboBox<Event> eventComboBox = new JComboBox<>();

        if (loggedInUser.getRole() != 1) {
            fieldNama.setText(loggedInUser.getUsername());
            fieldNIM.setText(String.valueOf(loggedInUser.getNim()));
            fieldEmail.setText(loggedInUser.getEmail());
            fieldNama.setEditable(false);
            fieldNIM.setEditable(false);
            fieldEmail.setEditable(false);
        }

        JPanel insertPanel = new JPanel(new GridLayout(0, 1));
        insertPanel.add(new JLabel("Nama:"));
        insertPanel.add(fieldNama);
        insertPanel.add(new JLabel("NIM:"));
        insertPanel.add(fieldNIM);
        insertPanel.add(new JLabel("Email:"));
        insertPanel.add(fieldEmail);
        insertPanel.add(new JLabel("WhatsApp:"));
        insertPanel.add(fieldWhatsApp);
        insertPanel.add(new JLabel("Event:"));

        List<Event> events = EventController.getUpcomingEvents();
        DefaultComboBoxModel<Event> eventModel = new DefaultComboBoxModel<>(events.toArray(new Event[0]));
        eventComboBox.setModel(eventModel);
        insertPanel.add(eventComboBox);

        while (true) {
            int result = JOptionPane.showConfirmDialog(null, insertPanel,
                    "Tambah Peserta Baru", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String nama = fieldNama.getText();
                String nim = fieldNIM.getText();
                String email = fieldEmail.getText();
                String whatsapp = fieldWhatsApp.getText();
                Event selectedEvent = (Event) eventComboBox.getSelectedItem();

                String validationMessage = PesertaController.validasiPeserta(nama, nim, email, whatsapp, selectedEvent);
                if (validationMessage != null) {
                    JOptionPane.showMessageDialog(null, validationMessage, "Error", JOptionPane.ERROR_MESSAGE);
                    continue; 
                }

                PesertaController.insertParticipantIntoDatabase(nama, nim, email, whatsapp, selectedEvent);
                populateTable();
                break; 
            } else {
                break;
            }
        }
    }
    
   /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        buttonEdit = new javax.swing.JButton();
        buttonInsert = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(16, 56));

        jTable1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Peserta", "NIM", "Nama Event"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(7);
        }

        jLabel16.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/event.png"))); // NOI18N
        jLabel16.setText("Menu > Management Daftar Peserta");

        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonInsert.setText("+Daftar");
        buttonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInsertActionPerformed(evt);
            }
        });

        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Peserta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonInsert))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(11, 11, 11)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEdit)
                    .addComponent(buttonDelete)
                    .addComponent(buttonInsert))
                .addContainerGap())
        );

        add(jPanel1, "card2");

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        // TODO add your handling code here:
         int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            int idPeserta = (int) jTable1.getValueAt(selectedRow, 0);

            Peserta selectedPeserta = PesertaController.getParticipantById(idPeserta);

            if (selectedPeserta != null) {
                showEditDialog(selectedPeserta);

                populateTable();
            } else {
                JOptionPane.showMessageDialog(null, "Data peserta tidak ditemukan.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih peserta untuk diubah.");
        }
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInsertActionPerformed
        // TODO add your handling code here:
        showInsertDialog();
    }//GEN-LAST:event_buttonInsertActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            int idPeserta = (int) jTable1.getValueAt(selectedRow, 0);
            int confirmation = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus peserta ini?",
                    "Hapus Peserta", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);

                try {
                    PesertaController.deleteParticipantFromDatabase(idPeserta);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuDaftarPeserta.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Peserta berhasil dihapus.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih peserta untuk dihapus.");
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonInsert;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
