/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import database.UserSession;
import database.connection;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author nurul
 */
public class ReservasiTempat extends javax.swing.JFrame {
    private final Connection conn = connection.getCon();
    private DefaultTableModel model;
    private String tgl;
    
    int id = UserSession.getU_id();
    /**
     * Creates new form DataReservasi
     */
    public ReservasiTempat() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        tcari.setFocusable(true);
        model = new DefaultTableModel();

        tabelreservasi.setModel(model);

        model.addColumn("ID Reservasi");
        model.addColumn("Acara");
        model.addColumn("Atas Nama");
        model.addColumn("No.Telp");
        model.addColumn("Tanggal");
        model.addColumn("Waktu");
        model.addColumn("Jumlah Orang");
        model.addColumn("Biaya Tambahan");
        model.addColumn("Letak");
        model.addColumn("Catatan");
        model.addColumn("Status");
        loadData();
        idreservasi();
        tampiluser();
    }
    
    public void FilterAngka(KeyEvent a){
       if(Character.isAlphabetic(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "Masukan Angka Saja!", "Peringatan", JOptionPane.WARNING_MESSAGE);
       }
    }
    
    private void tampiluser(){
        try {
            String sql = "SELECT user_id FROM admin WHERE user_id = '" + lbLogin.getText() + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lbLogin.setText(rs.getString("user_id"));
            }    
        } catch (SQLException ex) {
            Logger.getLogger(InfoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbLogin.setText(String.valueOf(id));
    }
    
    public final void loadData() {
    btSimpan.setEnabled(true);
    btHapus.setEnabled(false);
    btEdit.setEnabled(false);
    model.getDataVector().removeAllElements();
    model.fireTableDataChanged();

        try {
           try (Statement s = conn.createStatement()) {
            String sql = "SELECT * FROM reservasi_tempat";
            try (ResultSet r = s.executeQuery(sql)) {
                while (r.next()) {
                    Object[] o = new Object[11];
                    o[0] = r.getString("id_reservasi");
                    o[1] = r.getString("acara");
                    o[2] = r.getString("atasnama");
                    o[3] = r.getString("notelp");
                    o[4] = r.getString("tanggal");
                    o[5] = r.getString("waktu");
                    o[6] = r.getString("jumlahorg");
                    o[7] = r.getString("biayatambahan");
                    o[8] = r.getString("letak");
                    o[9] = r.getString("catatan");
                    o[10] = r.getString("status");
                    
                    model.addRow(o);
                }
            }
        }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        idreservasi();
    }
    
    public void carireservasi(){
       String cari = tcari.getText();
        //String to=jCombo.getItemAt(i).toString();
        
       Object[] baris={"ID Reservasi","Acara","Atas Nama","No.Telp","Tanggal","Waktu","Jumlah Orang","Biaya Tambahan","Letak","Catatan","Status"};
       model = new DefaultTableModel(null, baris);
       tabelreservasi.setModel(model);

       
       //untuk menampilkan di table
       try{
           String sql="SELECT * from reservasi_tempat WHERE id_reservasi like '%"+cari+"%' "
                   + "OR acara like '%"+cari+"%' "
                   + "OR atasnama like '%"+cari+"%' "
                   + "OR notelp like '%"+cari+"%' "
                   + "OR tanggal like '%"+cari+"%' "
                   + "OR waktu like '%"+cari+"%' "
                   + "OR jumlahorg like '%"+cari+"%' "
                   + "OR biayatambahan like '%"+cari+"%' "
                   + "OR letak like '%"+cari+"%' "
                   + "OR status like '%"+cari+"%' "
                   + "order by id_reservasi asc";
           java.sql.Statement stmt=conn.createStatement();
           java.sql.ResultSet rslt=stmt.executeQuery(sql);
           while(rslt.next()){
               String idr = rslt.getString("id_reservasi");
               String acara = rslt.getString("acara");
               String nama = rslt.getString("atasnama");
               String notelp = rslt.getString("notelp");
               String tgl2 = rslt.getString("tanggal");
               String waktu = rslt.getString("waktu");
               String jumlah = rslt.getString("jumlahorg");
               String biaya = rslt.getString("biayatambahan");
               String letak = rslt.getString("letak");
               String cat = rslt.getString("catatan");
               String stat = rslt.getString("status");
               String[] dataField={idr,acara,nama,notelp,tgl2,waktu,jumlah,biaya,letak,cat, stat};
               model.addRow(dataField);
           }
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    public void cetak(){          
        try {         
            String reportName = connection.PathReport + "buktireservasi.jasper";
            HashMap parameter = new HashMap();
            File reportFile = new File (reportName);
            parameter.put("id", tid.getText());
            
            JasperReport jReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, parameter, connection.getCon());
            JasperViewer.viewReport(jPrint, false);
     } catch (JRException e) {
         JOptionPane.showMessageDialog(null, e);
     }
   }
    
    protected void kosong() {
        tacara.setText("");
        tnama.setText("");
        ttelp.setText("");
        tglreserv.setDate(null);
        twaktu.setText("");
        tjumlah.setText("");
        tbiaya.setText("");
        cbletak.setSelectedItem("Pilih");
        tcat.setText("");
        cbstatus.setSelectedItem("Pilih");
        tcari.setText("Pencarian ..");
        tcari.setForeground(new Color(102,102,102));
    }
    
    protected void aktif() {
        tid.setEnabled(true);       // Pengecekan apakah textfield aktif atau tidak
        tacara.setEnabled(true);
        tnama.setEnabled(true);
        ttelp.setEnabled(true);
        tglreserv.setEnabled(true);
        twaktu.setEnabled(true);
        tjumlah.setEnabled(true);
        cbletak.setEnabled(true);
        tcat.setEnabled(true);
        cbstatus.setEnabled(true);
        tbiaya.setEnabled(true);
        btSimpan.setEnabled(true);
        btEdit.setEnabled(false);
        btHapus.setEnabled(false);
        btBatal.setEnabled(true);
    }
    
     private void idreservasi() {
      try {
            Connection c = connection.getCon();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM reservasi_tempat ORDER by id_reservasi desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String noid = r.getString("id_reservasi").substring(1);
                String AN = "" + (Integer.parseInt(noid) + 1);
                String Nol = "";

                switch (AN.length()) {
                    case 1:
                        Nol = "000";
                        break;
                    case 2:
                        Nol = "00";
                        break;
                    case 3:
                        Nol = "0";
                        break;
                    case 4:
                        Nol = "";
                        break;
                    default:
                        break;
                }

                tid.setText("R" + Nol + AN);
            } else {
                tid.setText("R0001");
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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

        jPanel3 = new javax.swing.JPanel();
        lbMinimize = new javax.swing.JLabel();
        lbClose = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tacara = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ttelp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tglreserv = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tjumlah = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbletak = new javax.swing.JComboBox<>();
        twaktu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tnama = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btSimpan = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        tbiaya = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbstatus = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tcat = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelreservasi = new javax.swing.JTable();
        btcari = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btrefresh = new javax.swing.JButton();
        back = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(19, 19, 39));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-minimize-window-20.png"))); // NOI18N
        lbMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbMinimizeMousePressed(evt);
            }
        });
        jPanel3.add(lbMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 10, -1, 20));

        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-macos-close-20.png"))); // NOI18N
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbCloseMousePressed(evt);
            }
        });
        jPanel3.add(lbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1175, 10, -1, -1));

        jPanel1.setBackground(new java.awt.Color(26, 26, 46));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(26, 26, 46));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Reservasi");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        tid.setEditable(false);
        tid.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tid.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tid, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 116, 23));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Acara");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 20));

        tacara.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tacara.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tacara, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 199, 23));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("No.Telp");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 20));

        ttelp.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        ttelp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(ttelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 199, 23));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tanggal Reservasi");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 100, 20));

        tglreserv.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tglreserv.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglreservPropertyChange(evt);
            }
        });
        jPanel2.add(tglreserv, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 129, 23));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Waktu");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 40, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Biaya Tambahan");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, -1, 20));

        tjumlah.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tjumlah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 48, 23));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Letak");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, -1, 20));

        cbletak.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbletak.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lantai I", "Lantai II", "Lantai I dan II" }));
        jPanel2.add(cbletak, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 116, 23));

        twaktu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        twaktu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(twaktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 127, 23));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Atas Nama");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 20));

        tnama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tnama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 199, 23));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Catatan [Opsional]");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, -1, -1));

        btSimpan.setBackground(new java.awt.Color(0, 88, 122));
        btSimpan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btSimpan.setText("Simpan");
        btSimpan.setBorder(null);
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 50, 70, 25));

        btEdit.setBackground(new java.awt.Color(0, 88, 122));
        btEdit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btEdit.setForeground(new java.awt.Color(255, 255, 255));
        btEdit.setText("Edit");
        btEdit.setBorder(null);
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });
        jPanel2.add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 80, 70, 25));

        btBatal.setBackground(new java.awt.Color(0, 88, 122));
        btBatal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btBatal.setForeground(new java.awt.Color(255, 255, 255));
        btBatal.setText("Batal");
        btBatal.setBorder(null);
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });
        jPanel2.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 110, 70, 25));

        btHapus.setBackground(new java.awt.Color(0, 88, 122));
        btHapus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btHapus.setForeground(new java.awt.Color(255, 255, 255));
        btHapus.setText("Hapus");
        btHapus.setBorder(null);
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        jPanel2.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 140, 70, 25));

        tbiaya.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbiaya.setText("0");
        tbiaya.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tbiaya, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 130, 23));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Jumlah Orang");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, 20));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Status");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, -1, 20));

        cbstatus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Booked", "Cancel" }));
        jPanel2.add(cbstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 110, 23));

        jButton1.setBackground(new java.awt.Color(0, 88, 122));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cetak Bukti Reservasi");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, 170, 29));

        tcat.setColumns(20);
        tcat.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tcat.setRows(5);
        tcat.setText("-");
        tcat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jScrollPane2.setViewportView(tcat);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 260, 100));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID User Login:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 240, 80, 20));

        lbLogin.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbLogin.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 240, 70, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 1120, 270));

        tabelreservasi.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelreservasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelreservasi.setGridColor(new java.awt.Color(255, 255, 255));
        tabelreservasi.setRowHeight(25);
        tabelreservasi.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tabelreservasi.setShowVerticalLines(false);
        tabelreservasi.getTableHeader().setReorderingAllowed(false);
        tabelreservasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelreservasiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelreservasi);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 110, 1110, 222));

        btcari.setBackground(new java.awt.Color(86, 93, 71));
        btcari.setForeground(new java.awt.Color(255, 255, 255));
        btcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/magnifying-glass.png"))); // NOI18N
        btcari.setBorderPainted(false);
        btcari.setContentAreaFilled(false);
        btcari.setFocusPainted(false);
        btcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcariActionPerformed(evt);
            }
        });
        jPanel1.add(btcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 30, 30, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Reservasi Tempat");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, 50));

        btrefresh.setBackground(new java.awt.Color(0, 88, 122));
        btrefresh.setForeground(new java.awt.Color(255, 255, 255));
        btrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refreshing.png"))); // NOI18N
        btrefresh.setBorder(null);
        btrefresh.setContentAreaFilled(false);
        btrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btrefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1115, 30, 30, 40));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow.png"))); // NOI18N
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 40, 32));

        tcari.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tcari.setForeground(new java.awt.Color(102, 102, 102));
        tcari.setText("Pencarian ..");
        tcari.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        tcari.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tcari.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        tcari.setName(""); // NOI18N
        tcari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tcariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tcariFocusLost(evt);
            }
        });
        tcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcariActionPerformed(evt);
            }
        });
        jPanel1.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, 210, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcariActionPerformed
        carireservasi();
    }//GEN-LAST:event_btcariActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        try{
            String sql="UPDATE reservasi_tempat SET acara=? ,atasnama=?, notelp=?, tanggal=?, waktu=?, jumlahorg=?, "
                    + "biayatambahan=?, letak=?, catatan=?, status=?, user_id=? WHERE id_reservasi='"+tid.getText()+"'";
            PreparedStatement stat=conn.prepareStatement(sql);
            
        stat.setString(1, tacara.getText());
        stat.setString(2, tnama.getText());
        stat.setString(3, ttelp.getText());
        stat.setString(4, tgl);
        stat.setString(5, twaktu.getText());
        stat.setString(6, tjumlah.getText());
        stat.setString(7, tbiaya.getText());
        stat.setString(8, cbletak.getSelectedItem().toString());
        stat.setString(9, tcat.getText());
        stat.setString(10, cbstatus.getSelectedItem().toString());
        stat.setString(11, lbLogin.getText());
        stat.executeUpdate();
        
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            loadData();
            idreservasi();
            btSimpan.setEnabled(true);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"+e);
        }        
    }//GEN-LAST:event_btEditActionPerformed

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        // TODO add your handling code here:
         if(tid.getText().equals("") ||tacara.getText().equals("") || tnama.getText().equals("")|| ttelp.getText().equals("") || tglreserv.getDate() == null || twaktu.getText().equals("")||
            tjumlah.getText().equals("") || tbiaya.getText().equals("") || cbletak.getSelectedItem().equals("") || cbstatus.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
         }else{
            String id = tid.getText();
            String acara = tacara.getText();
            String nama = tnama.getText();
            String telp = ttelp.getText();
            String waktu = twaktu.getText();
            String jum = tjumlah.getText();
            String letak = (String) cbletak.getSelectedItem();
            String stat = (String) cbstatus.getSelectedItem();
            String biaya = tbiaya.getText();
            String cat = tcat.getText();
            String user = lbLogin.getText();
            
            try {

                String sql = "INSERT INTO reservasi_tempat VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.setString(1, id);
                    p.setString(2, user);
                    p.setString(3, acara);
                    p.setString(4, nama);
                    p.setString(5, telp);
                    p.setString(6, tgl);
                    p.setString(7, waktu);
                    p.setString(8, jum);
                    p.setString(9, biaya);
                    p.setString(10, letak);
                    p.setString(11, cat);
                    p.setString(12, stat);
                    
                    p.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan", "Kedai Avicenna", JOptionPane.WARNING_MESSAGE);
            } finally {
                loadData();
                kosong();
                aktif();
                idreservasi();
            }
        }
    }//GEN-LAST:event_btSimpanActionPerformed

    private void tglreservPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglreservPropertyChange
        // TODO add your handling code here:
        if (tglreserv.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tgl = format.format(tglreserv.getDate());
                }
    }//GEN-LAST:event_tglreservPropertyChange

    private void tabelreservasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelreservasiMouseClicked
        btSimpan.setEnabled(false);
        btEdit.setEnabled(true);
        btHapus.setEnabled(true);
        try{
            int bar = tabelreservasi.getSelectedRow();
            String a = model.getValueAt(bar, 0).toString();
            String b = model.getValueAt(bar, 1).toString();
            String c = model.getValueAt(bar, 2).toString();
            String d = model.getValueAt(bar, 3).toString();  
            String e = model.getValueAt(bar, 4).toString();
                 try{
                     SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");
                     java.util.Date dt = frmt.parse(e);
                     tglreserv.setDate(dt);
                 } catch(ParseException ex) {
                 }
            String f = model.getValueAt(bar, 5).toString();
            String g = model.getValueAt(bar, 6).toString();
            String h = model.getValueAt(bar, 7).toString();
            String i = model.getValueAt(bar, 8).toString();
            String j = model.getValueAt(bar, 9).toString();
            String k = model.getValueAt(bar, 10).toString();         
             
            tid.setText(a);
            tacara.setText(b);
            tnama.setText(c);
            ttelp.setText(d);
            twaktu.setText(f);
            tjumlah.setText(g);
            tbiaya.setText(h);
            cbletak.setSelectedItem(i);
            tcat.setText(j);
            cbstatus.setSelectedItem(k);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_tabelreservasiMouseClicked

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        kosong();
        loadData();
        btSimpan.setEnabled(true);
        btEdit.setEnabled(false);
    }//GEN-LAST:event_btBatalActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Ingin Menghapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if(confirm==JOptionPane.YES_OPTION) {
            try {
                String sql ="DELETE FROM reservasi_tempat WHERE id_reservasi='"+tid.getText()+"'";

                java.sql.PreparedStatement ps=conn.prepareStatement(sql);
                ps.execute();
                JOptionPane.showMessageDialog(this, "Berhasil Dihapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            loadData();
            kosong();
            aktif();
            idreservasi();
        }
    }//GEN-LAST:event_btHapusActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cetak();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrefreshActionPerformed
        loadData();
        kosong();
        aktif();
    }//GEN-LAST:event_btrefreshActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        new Utama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backActionPerformed

    private void lbMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMinimizeMousePressed
        setState(ICONIFIED);
    }//GEN-LAST:event_lbMinimizeMousePressed

    private void lbCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMousePressed
        int confirm = JOptionPane.showConfirmDialog(this, "Anda ingin menutup aplikasi?", "Konfirmasi Keluar Aplikasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_lbCloseMousePressed

    private void tcariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tcariFocusGained
        if (tcari.getText().equals("Pencarian ..")) {
            tcari.setText("");
            tcari.setForeground(new Color(0,0,0));
        }

    }//GEN-LAST:event_tcariFocusGained

    private void tcariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tcariFocusLost
        if (tcari.getText().equals("")) {
            tcari.setText("Pencarian ..");
            tcari.setForeground(new Color(102,102,102));
        }
    }//GEN-LAST:event_tcariFocusLost

    private void tcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcariActionPerformed
        carireservasi();
    }//GEN-LAST:event_tcariActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservasiTempat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ReservasiTempat().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton btBatal;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btSimpan;
    private javax.swing.JButton btcari;
    private javax.swing.JButton btrefresh;
    private javax.swing.JComboBox<String> cbletak;
    private javax.swing.JComboBox<String> cbstatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbMinimize;
    private javax.swing.JTable tabelreservasi;
    private javax.swing.JTextField tacara;
    private javax.swing.JTextField tbiaya;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextArea tcat;
    private com.toedter.calendar.JDateChooser tglreserv;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tjumlah;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField ttelp;
    private javax.swing.JTextField twaktu;
    // End of variables declaration//GEN-END:variables
}
