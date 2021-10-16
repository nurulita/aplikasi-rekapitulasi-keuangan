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
public class Pengeluaran extends javax.swing.JFrame {
    private final Connection conn = connection.getCon();
    private DefaultTableModel model;
    int id = UserSession.getU_id();

    /**
     * Creates new form Pengeluaran
     */
    public Pengeluaran() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        tcari.setFocusable(true);
        model = new DefaultTableModel();

        tabelpengeluarankedai.setModel(model);

        model.addColumn("Tanggal");
        model.addColumn("ID Pengeluaran");
        model.addColumn("Jumlah Pengeluaran");
        model.addColumn("Keterangan");
        loadData();
        idpengeluaran();
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
            String sql = "SELECT * FROM pengeluaran";
            try (ResultSet r = s.executeQuery(sql)) {
                while (r.next()) {
                    Object[] o = new Object[4];
                    o[0] = r.getString("tanggal");
                    o[1] = r.getString("id_pengeluaran");
                    o[2] = r.getString("jumlah");
                    o[3] = r.getString("keterangan");
                    
                    model.addRow(o);
                }
            }
        }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
   
    
    public void caridata2(){
        String cari = tcari.getText();
        //String to=jCombo.getItemAt(i).toString();
        
       Object[] baris={"Tanggal","ID Pengeluaran","Jumlah","Keterangan"};
       model = new DefaultTableModel(null, baris);
       tabelpengeluarankedai.setModel(model);
  
       
       //untuk menampilkan di table
       try{
           String sql="SELECT * from pengeluaran WHERE "
                   + "tanggal like '%"+cari+"%' "
                   + "OR id_pengeluaran like '%"+cari+"%' "
                   + "OR jumlah like '%"+cari+"%' "
                   + "OR keterangan like '%"+cari+"%' "
                   + "order by id_pengeluaran asc";
           java.sql.Statement stmt=conn.createStatement();
           java.sql.ResultSet rslt=stmt.executeQuery(sql);
           while(rslt.next()){
               String tgl=rslt.getString("tanggal");
               String idp=rslt.getString("id_pengeluaran");
               String jum=rslt.getString("jumlah");
               String ket=rslt.getString("keterangan");
               
               String[] dataField={tgl,idp,jum,ket};
               model.addRow(dataField);
           }
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    private void idpengeluaran() {
        try {
            Connection c = connection.getCon();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM pengeluaran ORDER by id_pengeluaran desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String nofak = r.getString("id_pengeluaran").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
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

                tId.setText("K" + Nol + AN);
            } else {
                tId.setText("K0001");
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
     
     public void kosong2() {
        tJumlah.setText("");
        tKet.setText("");
        tcari.setText("Pencarian ..");
        tcari.setForeground(new Color(102,102,102));        
    }
     
     public void cetak1(){          
        
        try {         
            String reportName = connection.PathReport + "pengeluarankedai.jasper";
            HashMap parameter = new HashMap();
            File reportFile = new File (reportName);
            parameter.put("cari", tcari.getText());
            
            JasperReport jReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, parameter, connection.getCon());
            JasperViewer.viewReport(jPrint, false);
     } catch (JRException e) {
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

        topbar = new javax.swing.JPanel();
        lbMinimize = new javax.swing.JLabel();
        lbClose = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelpengeluarankedai = new javax.swing.JTable();
        btcari = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btSimpan = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tId = new javax.swing.JTextField();
        tJumlah = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btEdit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tKet = new javax.swing.JTextArea();
        btHapus = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btrefresh = new javax.swing.JButton();
        back = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        topbar.setBackground(new java.awt.Color(19, 19, 39));
        topbar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-minimize-window-20.png"))); // NOI18N
        lbMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbMinimizeMousePressed(evt);
            }
        });
        topbar.add(lbMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 10, -1, 20));

        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-macos-close-20.png"))); // NOI18N
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbCloseMousePressed(evt);
            }
        });
        topbar.add(lbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, -1, -1));

        jPanel1.setBackground(new java.awt.Color(26, 26, 46));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelpengeluarankedai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelpengeluarankedai.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelpengeluarankedai.setGridColor(new java.awt.Color(255, 255, 255));
        tabelpengeluarankedai.setRowHeight(25);
        tabelpengeluarankedai.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tabelpengeluarankedai.getTableHeader().setReorderingAllowed(false);
        tabelpengeluarankedai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpengeluarankedaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelpengeluarankedai);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 720, 220));

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
        jPanel1.add(btcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, 31, 40));

        jPanel6.setBackground(new java.awt.Color(26, 26, 46));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel6.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 70, 25));

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
        jPanel6.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 70, 25));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID Pengeluaran");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 47, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Pengeluaran");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 90, -1, -1));

        tId.setEditable(false);
        tId.setBackground(new java.awt.Color(204, 204, 204));
        tId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tId.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel6.add(tId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 44, 108, 23));

        tJumlah.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tJumlah.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        tJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tJumlahKeyTyped(evt);
            }
        });
        jPanel6.add(tJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 87, 158, 23));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Keterangan");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 135, -1, -1));

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
        jPanel6.add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 70, 25));

        tKet.setColumns(20);
        tKet.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tKet.setRows(5);
        tKet.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jScrollPane1.setViewportView(tKet);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 135, 261, -1));

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
        jPanel6.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 70, 25));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Rp");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 90, -1, -1));

        lbLogin.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbLogin.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.add(lbLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, 70, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID User Login:");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, 80, 20));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 720, 270));

        jButton2.setBackground(new java.awt.Color(0, 88, 122));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cetak");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 630, 60, 25));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pengeluaran");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 260, 50));

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
        jPanel1.add(btrefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 30, 40));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow.png"))); // NOI18N
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 40, 32));

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
        jPanel1.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 210, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(topbar, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(topbar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelpengeluarankedaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpengeluarankedaiMouseClicked
        // TODO add your handling code here:
        btSimpan.setEnabled(false);
        btEdit.setEnabled(true);
        btHapus.setEnabled(true);
        int i = tabelpengeluarankedai.getSelectedRow();
        if (i == -1) {
            return;
        }
        String idp = (String) model.getValueAt(i, 1);
        tId.setText(idp);
        tId.setEnabled(false);

        String jumlah = (String) model.getValueAt(i, 2);
        tJumlah.setText(jumlah);

        String ket = (String) model.getValueAt(i, 3);
        tKet.setText(ket);
    }//GEN-LAST:event_tabelpengeluarankedaiMouseClicked

    private void btcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcariActionPerformed
        caridata2();
    }//GEN-LAST:event_btcariActionPerformed

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        if (tId.getText().equals("") || tJumlah.getText().equals("") || tKet.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.WARNING_MESSAGE);
        } else {
            String id = tId.getText();
            String jum = tJumlah.getText();
            String ket = tKet.getText();
            String user = lbLogin.getText();

            try {
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                String tgl = date.toString();

                String sql = "INSERT INTO pengeluaran VALUES (?, ?, ?, ?, ?)";

                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, id);
                    ps.setString(2, user);
                    ps.setString(3, tgl);
                    ps.setString(4, ket);
                    ps.setString(5, jum);

                    ps.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
            } finally {
                idpengeluaran();
                loadData();
                kosong2();
            }
        }
    }//GEN-LAST:event_btSimpanActionPerformed

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        kosong2();
        idpengeluaran();
        btSimpan.setEnabled(true);
        btEdit.setEnabled(false);
    }//GEN-LAST:event_btBatalActionPerformed

    private void tJumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tJumlahKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_tJumlahKeyTyped

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
       try {
           long millis = System.currentTimeMillis();
           java.sql.Date date = new java.sql.Date(millis);
           String tgl = date.toString();
                
            String sql = "UPDATE pengeluaran SET user_id=?, jumlah=?, keterangan=?, tanggal=? WHERE id_pengeluaran ='" + tId.getText() + "'";
            try (PreparedStatement p = conn.prepareStatement(sql)) {
                p.setString(1, lbLogin.getText());
                p.setString(2, tJumlah.getText());
                p.setString(3, tKet.getText());
                p.setString(4, tgl);
                
                p.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "Elektronik Berkah", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
        } finally {
            loadData();
            kosong2();
            btSimpan.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btEditActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        // TODO add your handling code here:
        if ( tId.getText().equals("") || tJumlah.getText().equals("") || tKet.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Pilih Data","Kedai Avicenna", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Ingin Menghapus Data?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION) {

                try {
                    String sql ="DELETE FROM pengeluaran WHERE id_pengeluaran='"+tId.getText()+"'";
                    java.sql.PreparedStatement ps=conn.prepareStatement(sql);
                    ps.execute();
                    JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
                loadData();
                kosong2();
            }
        }
    }//GEN-LAST:event_btHapusActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cetak1();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrefreshActionPerformed
        loadData();
    }//GEN-LAST:event_btrefreshActionPerformed

    private void lbMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMinimizeMousePressed
        setState(ICONIFIED);
    }//GEN-LAST:event_lbMinimizeMousePressed

    private void lbCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMousePressed
        int confirm = JOptionPane.showConfirmDialog(this, "Anda ingin menutup aplikasi?", "Konfirmasi Keluar Aplikasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_lbCloseMousePressed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        new Utama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backActionPerformed

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
        caridata2();
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
            java.util.logging.Logger.getLogger(Pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Pengeluaran().setVisible(true);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbMinimize;
    private javax.swing.JTextField tId;
    private javax.swing.JTextField tJumlah;
    private javax.swing.JTextArea tKet;
    private javax.swing.JTable tabelpengeluarankedai;
    private javax.swing.JTextField tcari;
    private javax.swing.JPanel topbar;
    // End of variables declaration//GEN-END:variables
}
