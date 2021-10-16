/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import java.sql.*;
import database.connection;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author nurul
 */
public class Tenant extends javax.swing.JFrame {
    private final Connection conn = connection.getCon();
    private DefaultTableModel model;

    /**
     * Creates new form Tenant
     */
    public Tenant() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        tcari.setFocusable(true);
        model = new DefaultTableModel();

        tabelTenant.setModel(model);

        model.addColumn("ID Tenant");
        model.addColumn("Nama Tenant");
        model.addColumn("Penanggung Jawab");
        model.addColumn("No.Telepon");
        loadData();
        idtenant();
    }
    
    public void FilterAngka(KeyEvent a){
       if(Character.isAlphabetic(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "Masukan Angka Saja!", "Peringatan", JOptionPane.WARNING_MESSAGE);
       }
   }
    
    public final void loadData() {
    btSimpan.setEnabled(true);
    btHapus.setEnabled(false);
    btEdit.setEnabled(false);
    model.getDataVector().removeAllElements();
    model.fireTableDataChanged();

        try {
        try (Statement s = conn.createStatement()) {
            String sql = "SELECT * FROM tenant";
            try (ResultSet r = s.executeQuery(sql)) {
                while (r.next()) {
                    Object[] o = new Object[4];
                    o[0] = r.getString("id_tenant");
                    o[1] = r.getString("namatenant");
                    o[2] = r.getString("penanggungjawab");
                    o[3] = r.getString("notlp");
                                   
                    model.addRow(o);
                }
            }
        }
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }
     
     public void caritenant(){
        String cari = tcari.getText();
        //String to=jCombo.getItemAt(i).toString();
        
       Object[] baris={"ID Tenant","Nama Tenant","Penanggung Jawab","No.Telepon"};
       model = new DefaultTableModel(null, baris);
       tabelTenant.setModel(model);

       
       //untuk menampilkan di table
       try{
           String sql="SELECT * from tenant WHERE "
                   + "id_tenant like '%"+cari+"%' "
                   + "OR namatenant like '%"+cari+"%' "
                   + "OR penanggungjawab like '%"+cari+"%' "
                   + "OR notlp like '%"+cari+"%' "
                   + "order by id_tenant asc";
           java.sql.Statement stmt=conn.createStatement();
           java.sql.ResultSet rslt=stmt.executeQuery(sql);
           while(rslt.next()){
               String idt=rslt.getString("id_tenant");
               String nama=rslt.getString("namatenant");
               String pj=rslt.getString("penanggungjawab");
               String notlp=rslt.getString("notlp");
               String[] dataField={idt,nama,pj,notlp};
               model.addRow(dataField);
           }
       }
       catch(SQLException ex){
       }
    }
     
     protected void aktif() {
        tId.setEnabled(true);       // Pengecekan apakah textfield aktif atau tidak
        tNama.setEnabled(true);
        tPj.setEnabled(true);
        tHp.setEnabled(true);
        
        idtenant();
    }
    
    protected void kosong() {
        tNama.setText("");
        tPj.setText("");
        tHp.setText("");
        tcari.setText("Pencarian ..");
        tcari.setForeground(new Color(102,102,102));
    }
    
private void idtenant() {
        try {
            Connection c = connection.getCon();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM tenant ORDER by id_tenant desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String noten = r.getString("id_tenant").substring(1);
                String AN = "" + (Integer.parseInt(noten) + 1);
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

                tId.setText("T" + Nol + AN);
            } else {
                tId.setText("T0001");
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

        topbar = new javax.swing.JPanel();
        lbMinimize = new javax.swing.JLabel();
        lbClose = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTenant = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tNama = new javax.swing.JTextField();
        tId = new javax.swing.JTextField();
        tHp = new javax.swing.JTextField();
        tPj = new javax.swing.JTextField();
        btSimpan = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        btcari = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btrefresh1 = new javax.swing.JButton();
        back = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topbar.setBackground(new java.awt.Color(19, 19, 39));
        topbar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-minimize-window-20.png"))); // NOI18N
        lbMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbMinimizeMousePressed(evt);
            }
        });
        topbar.add(lbMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 10, -1, 20));

        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-macos-close-20.png"))); // NOI18N
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbCloseMousePressed(evt);
            }
        });
        topbar.add(lbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, -1, -1));

        getContentPane().add(topbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 755, 40));

        jPanel1.setBackground(new java.awt.Color(26, 26, 46));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelTenant.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelTenant.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Tenant", "Nama Tenant", "Penanggung Jawab", "No.Telepon"
            }
        ));
        tabelTenant.setFillsViewportHeight(true);
        tabelTenant.setGridColor(new java.awt.Color(255, 255, 255));
        tabelTenant.setRowHeight(25);
        tabelTenant.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tabelTenant.getTableHeader().setReorderingAllowed(false);
        tabelTenant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelTenantMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelTenant);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 640, 253));

        jPanel2.setBackground(new java.awt.Color(26, 26, 46));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Tenant");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 44, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama Tenant");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 84, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Penanggung Jawab");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("No.HP");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        tNama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tNama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 81, 180, 23));

        tId.setEditable(false);
        tId.setBackground(new java.awt.Color(255, 255, 255));
        tId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tId.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tId, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 41, 180, 23));

        tHp.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tHp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        tHp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tHpKeyTyped(evt);
            }
        });
        jPanel2.add(tHp, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 180, 23));

        tPj.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tPj.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tPj, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 180, 23));

        btSimpan.setBackground(new java.awt.Color(0, 88, 122));
        btSimpan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btSimpan.setText("Simpan");
        btSimpan.setBorder(null);
        btSimpan.setFocusPainted(false);
        btSimpan.setMaximumSize(new java.awt.Dimension(35, 12));
        btSimpan.setMinimumSize(new java.awt.Dimension(35, 12));
        btSimpan.setPreferredSize(new java.awt.Dimension(35, 10));
        btSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSimpanActionPerformed(evt);
            }
        });
        jPanel2.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 70, 25));

        btEdit.setBackground(new java.awt.Color(0, 88, 122));
        btEdit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btEdit.setForeground(new java.awt.Color(255, 255, 255));
        btEdit.setText("Edit");
        btEdit.setBorder(null);
        btEdit.setFocusPainted(false);
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });
        jPanel2.add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 70, 25));

        btHapus.setBackground(new java.awt.Color(0, 88, 122));
        btHapus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btHapus.setForeground(new java.awt.Color(255, 255, 255));
        btHapus.setText("Hapus");
        btHapus.setBorder(null);
        btHapus.setFocusPainted(false);
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        jPanel2.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 70, 25));

        btBatal.setBackground(new java.awt.Color(0, 88, 122));
        btBatal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btBatal.setForeground(new java.awt.Color(255, 255, 255));
        btBatal.setText("Batal");
        btBatal.setBorder(null);
        btBatal.setFocusPainted(false);
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });
        jPanel2.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 70, 25));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 640, 240));

        btcari.setBackground(new java.awt.Color(86, 93, 71));
        btcari.setForeground(new java.awt.Color(255, 255, 255));
        btcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/magnifying-glass.png"))); // NOI18N
        btcari.setBorder(null);
        btcari.setContentAreaFilled(false);
        btcari.setFocusPainted(false);
        btcari.setFocusable(false);
        btcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcariActionPerformed(evt);
            }
        });
        jPanel1.add(btcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 40, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tenant");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, 60));

        btrefresh1.setBackground(new java.awt.Color(0, 88, 122));
        btrefresh1.setForeground(new java.awt.Color(255, 255, 255));
        btrefresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refreshing.png"))); // NOI18N
        btrefresh1.setBorder(null);
        btrefresh1.setContentAreaFilled(false);
        btrefresh1.setFocusPainted(false);
        btrefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrefresh1ActionPerformed(evt);
            }
        });
        jPanel1.add(btrefresh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, -1, 40));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow.png"))); // NOI18N
        back.setBorder(null);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 40, 32));

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
        jPanel1.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 210, 23));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 39, 699, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelTenantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTenantMouseClicked
        // TODO add your handling code here:
        btSimpan.setEnabled(false);
        btEdit.setEnabled(true);
        btHapus.setEnabled(true);
        int i = tabelTenant.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) model.getValueAt(i, 0);
        tId.setText(id);
        tId.setEnabled(false);

        String nama = (String) model.getValueAt(i, 1);
        tNama.setText(nama);

        String pj = (String) model.getValueAt(i, 2);
        tPj.setText(pj);

        String tlp = (String) model.getValueAt(i, 3);
        tHp.setText(tlp);
    }//GEN-LAST:event_tabelTenantMouseClicked

    private void tHpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tHpKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_tHpKeyTyped

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        if(tId.getText().equals("") ||tNama.getText().equals("") || tPj.getText().equals("")|| tHp.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
        }else{
            String id = tId.getText();
            String nama = tNama.getText();
            String pj = tPj.getText();
            String hp = tHp.getText();

            try {

                String sql = "INSERT INTO tenant VALUES (?, ?, ?, ?)";

                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.setString(1, id);
                    p.setString(2, nama);
                    p.setString(3, pj);
                    p.setString(4, hp);
                    
                    p.executeUpdate();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                loadData();
                kosong();
                aktif();
                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btSimpanActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        if(tId.getText().equals("") ||tNama.getText().equals("") || tPj.getText().equals("")|| tHp.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
        }else{
            int i = tabelTenant.getSelectedRow();
            if (i == -1) {
                return;
            }
            String user = (String) model.getValueAt(i, 0);
            try {
                String sql = "UPDATE  tenant SET namatenant =  '" + tNama.getText() + "', penanggungjawab = '"+ tPj.getText() + "', notlp='"+ tHp.getText() + "' WHERE  id_tenant ='" + tId.getText() + "'";
                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println("Terjadi Error");
            } finally {
                loadData();
                kosong();
                aktif();
                btSimpan.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btEditActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Ingin Menghapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if(confirm==JOptionPane.YES_OPTION) {

            try {
                String sql ="DELETE FROM tenant WHERE id_tenant='"+tId.getText()+"'";

                java.sql.PreparedStatement ps=conn.prepareStatement(sql);
                ps.execute();
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            loadData();
            kosong();
            idtenant();
        }
    }//GEN-LAST:event_btHapusActionPerformed

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        kosong();
        aktif();
        btSimpan.setEnabled(true);
        btEdit.setEnabled(false);
    }//GEN-LAST:event_btBatalActionPerformed

    private void btcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcariActionPerformed
        caritenant();
    }//GEN-LAST:event_btcariActionPerformed

    private void btrefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrefresh1ActionPerformed
        loadData();
        kosong();
        aktif();
    }//GEN-LAST:event_btrefresh1ActionPerformed

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
        caritenant();
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
            java.util.logging.Logger.getLogger(Tenant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Tenant().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton btBatal;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btSimpan;
    private javax.swing.JButton btcari;
    private javax.swing.JButton btrefresh1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbMinimize;
    private javax.swing.JTextField tHp;
    private javax.swing.JTextField tId;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tPj;
    private javax.swing.JTable tabelTenant;
    private javax.swing.JTextField tcari;
    private javax.swing.JPanel topbar;
    // End of variables declaration//GEN-END:variables
}
