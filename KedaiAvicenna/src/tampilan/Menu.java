/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import database.connection;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author nurul
 */
public class Menu extends javax.swing.JFrame {
    private final Connection conn = connection.getCon();
    private DefaultTableModel model;
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        tcari.setFocusable(true);
        model = new DefaultTableModel();

        tabelmenu.setModel(model);

        model.addColumn("ID Tenant");
        model.addColumn("ID Menu");
        model.addColumn("Nama Menu");
        model.addColumn("Jenis Menu");
        model.addColumn("Harga Satuan");
        model.addColumn("Stok");
        loadData();
        tampilpilih();
    }
    
    public void FilterAngka(KeyEvent a){
       if(Character.isAlphabetic(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "Masukan Angka Saja!", "Peringatan", JOptionPane.WARNING_MESSAGE);
       }
   }
    
    public final void loadData() {
        aktif();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            try (Statement s = conn.createStatement()) {
                String sql = "SELECT * FROM menu";
                try (ResultSet r = s.executeQuery(sql)) {
                    while (r.next()) {
                        Object[] o = new Object[6];
                        o[0] = r.getString("id_tenant");
                        o[1] = r.getString("id_menu");
                        o[2] = r.getString("namamenu");
                        o[3] = r.getString("jenis");
                        o[4] = r.getString("harga");
                        o[5] = r.getString("stok");                      
                        model.addRow(o);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void tampilpilih() {
        try {           
            Statement st = conn.createStatement();
            String sql = "SELECT DISTINCT namatenant FROM tenant";
            ResultSet r = st.executeQuery(sql);

            while (r.next()) {
                cbTenant.addItem(r.getString("namatenant"));
            }
            r.last();
            r.first();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
    String datatenant;
    private void tampildata() {
        try {           
            Statement st = conn.createStatement();
            String sql = "SELECT DISTINCT id_tenant FROM tenant WHERE namatenant = '"
                + datatenant + "'";
            ResultSet r = st.executeQuery(sql);

            while (r.next()) {
                tidtenant.setText(r.getString("id_tenant"));                
            }

            r.last();
            r.first();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    public void carimenu(){
       String cari = tcari.getText();
        
       Object[] baris={"ID Tenant","ID Menu","Nama Menu","Jenis Menu","Harga Satuan","Stok"};
       model = new DefaultTableModel(null, baris);
       tabelmenu.setModel(model);

       //untuk menampilkan di table
       try{
           String sql="SELECT * from menu WHERE id_tenant like '%"+cari+"%' "
                   + "OR id_menu like '%"+cari+"%' "
                   + "OR namamenu like '%"+cari+"%' "
                   + "OR jenis like '%"+cari+"%' "
                   + "OR harga like '%"+cari+"%' "
                   + "OR stok like '%"+cari+"%' "
                   + "order by id_tenant asc";
           java.sql.Statement stmt=conn.createStatement();
           java.sql.ResultSet rslt=stmt.executeQuery(sql);
           while(rslt.next()){
               String idt=rslt.getString("id_tenant");
               String idm=rslt.getString("id_menu");
               String nama=rslt.getString("namamenu");
               String jenis=rslt.getString("jenis");
               String harga=rslt.getString("harga");
               String stok=rslt.getString("stok");
               
               String[] dataField={idt,idm,nama,jenis,harga,stok};
               model.addRow(dataField);
           }
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan", "Kedai Avicenna", JOptionPane.WARNING_MESSAGE);
       }
    }
    
    protected void kosong() {
        tidmenu.setText("");
        tnama.setText("");
        cbjenis.setSelectedItem("Pilih");
        tharga.setText("");
        tstok.setText("");
        tcari.setText("Pencarian ..");
        tcari.setForeground(new Color(102,102,102));
    }
    
    protected void aktif() {
        tidtenant.setEnabled(true);  
        tidmenu.setEnabled(true);
        tnama.setEnabled(true);
        cbjenis.setEnabled(true);
        tharga.setEnabled(true);
        tstok.setEnabled(true);        
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
        tabelmenu = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        tharga = new javax.swing.JTextField();
        tidmenu = new javax.swing.JTextField();
        cbjenis = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tidtenant = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tnama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tstok = new javax.swing.JTextField();
        btSimpan = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        cbTenant = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btcari = new javax.swing.JButton();
        btrefresh = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        tcari = new javax.swing.JTextField();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        topbar.add(lbMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, -1, 20));

        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-macos-close-20.png"))); // NOI18N
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbCloseMousePressed(evt);
            }
        });
        topbar.add(lbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(855, 10, -1, -1));

        getContentPane().add(topbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 40));

        jPanel1.setBackground(new java.awt.Color(26, 26, 46));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tabelmenu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelmenu.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelmenu.setFocusable(false);
        tabelmenu.setGridColor(new java.awt.Color(255, 255, 255));
        tabelmenu.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tabelmenu.setRowHeight(25);
        tabelmenu.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tabelmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelmenuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelmenu);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 770, 239));

        jPanel2.setBackground(new java.awt.Color(26, 26, 46));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tharga.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tharga.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        tharga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                thargaKeyTyped(evt);
            }
        });
        jPanel2.add(tharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 123, 23));

        tidmenu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tidmenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tidmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 137, 23));

        cbjenis.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbjenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Makanan", "Minuman" }));
        jPanel2.add(cbjenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 123, 23));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Harga Satuan");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jenis Menu");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 200, -1, -1));

        tidtenant.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tidtenant.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tidtenant, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 70, 23));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Menu");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 120, -1, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Menu");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 160, -1, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID Tenant");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 78, 60, 20));

        tnama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tnama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 190, 23));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Stok");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        tstok.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tstok.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        tstok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tstokKeyTyped(evt);
            }
        });
        jPanel2.add(tstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 123, 23));

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
        jPanel2.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 72, 25));

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
        jPanel2.add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 72, 25));

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
        jPanel2.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 72, 25));

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
        jPanel2.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, 72, 25));

        cbTenant.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbTenant.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTenantItemStateChanged(evt);
            }
        });
        jPanel2.add(cbTenant, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 190, 23));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nama Tenant");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 40, 80, 17));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 770, 249));

        btcari.setBackground(new java.awt.Color(86, 93, 71));
        btcari.setForeground(new java.awt.Color(255, 255, 255));
        btcari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/magnifying-glass.png"))); // NOI18N
        btcari.setBorder(null);
        btcari.setContentAreaFilled(false);
        btcari.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcariActionPerformed(evt);
            }
        });
        jPanel1.add(btcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 30, 40));

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
        jPanel1.add(btrefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 30, 30, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Daftar Menu");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, 50));

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
        jPanel1.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 210, 23));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow.png"))); // NOI18N
        back.setBorder(null);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 40, 32));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 39, 850, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelmenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelmenuMouseClicked
        // TODO add your handling code here:
        btSimpan.setEnabled(false);
        btEdit.setEnabled(true);
        btHapus.setEnabled(true);
        int i = tabelmenu.getSelectedRow();
        if (i == -1) {
            return;
        }
        String idt = (String) model.getValueAt(i, 0);
        tidtenant.setText(idt);
        tidtenant.setEnabled(false);

        String idm = (String) model.getValueAt(i, 1);
        tidmenu.setText(idm);
        tidmenu.setEnabled(false);

        String nama = (String) model.getValueAt(i, 2);
        tnama.setText(nama);

        String jenis = (String) model.getValueAt(i, 3);
        cbjenis.setSelectedItem(jenis);

        String harga = (String) model.getValueAt(i, 4);
        tharga.setText(harga);

        String stok = (String) model.getValueAt(i, 5);
        tstok.setText(stok);
    }//GEN-LAST:event_tabelmenuMouseClicked

    private void thargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_thargaKeyTyped
       FilterAngka(evt);
    }//GEN-LAST:event_thargaKeyTyped

    private void tstokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tstokKeyTyped
       FilterAngka(evt);
    }//GEN-LAST:event_tstokKeyTyped

    private void btSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSimpanActionPerformed
        if(tidtenant.getText().equals("") || tidmenu.getText().equals("") || tnama.getText().equals("") || cbjenis.getSelectedItem().equals("")|| tharga.getText().equals("") || tstok.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
        }else{
            String idt= tidtenant.getText();
            String idm = tidmenu.getText();
            String nama = tnama.getText();
            String jenis = cbjenis.getSelectedItem().toString();
            String harga = tharga.getText();
            String stok = tstok.getText();

            try {
                String sql = "INSERT INTO menu VALUES (?, ?, ?, ?, ?, ?)";

                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.setString(1, idt);
                    p.setString(2, idm);
                    p.setString(3, nama);
                    p.setString(4, stok);
                    p.setString(5, jenis);
                    p.setString(6, harga);
                    
                    p.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan", "Kedai Avicenna", JOptionPane.WARNING_MESSAGE);
            } finally {
                loadData();
                kosong();
            }
        }
    }//GEN-LAST:event_btSimpanActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        if(tidtenant.getText().equals("") || tidmenu.getText().equals("") || tnama.getText().equals("") || cbjenis.getSelectedItem().equals("")|| 
                tharga.getText().equals("") || tstok.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
        }else{
            int i = tabelmenu.getSelectedRow();
            if (i == -1) {
                return;
            }
            try {
                String sql = "UPDATE  menu SET namamenu =  '" + tnama.getText() + "', stok = '"+ tstok.getText() + "', "
                        + "jenis='"+ cbjenis.getSelectedItem().toString() + "', harga='"+ tharga.getText() +"' WHERE  id_tenant ='" + 
                        tidtenant.getText() + "' AND id_menu='"+tidmenu.getText()+"'";
                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                System.out.println("Terjadi Error");
            } finally {
                loadData();
                kosong();
                aktif();
                btSimpan.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btEditActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Ingin Menghapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if(confirm==JOptionPane.YES_OPTION) {

            try {
                String sql ="DELETE FROM menu WHERE id_menu='"+tidmenu.getText()+"' AND id_tenant='"+tidtenant.getText()+"'";

                java.sql.PreparedStatement ps=conn.prepareStatement(sql);
                ps.execute();
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            loadData();
        }
    }//GEN-LAST:event_btHapusActionPerformed

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        loadData();
        kosong();
        aktif();
        btSimpan.setEnabled(true);
        btEdit.setEnabled(false);
    }//GEN-LAST:event_btBatalActionPerformed

    private void cbTenantItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTenantItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            datatenant = cbTenant.getSelectedItem().toString();
            tampildata();
        }
    }//GEN-LAST:event_cbTenantItemStateChanged

    private void tcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcariActionPerformed
        carimenu();
    }//GEN-LAST:event_tcariActionPerformed

    private void btcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcariActionPerformed
        carimenu();
    }//GEN-LAST:event_btcariActionPerformed

    private void btrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrefreshActionPerformed
        loadData();
        kosong();
        aktif();
    }//GEN-LAST:event_btrefreshActionPerformed

    private void lbCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMousePressed
        int confirm = JOptionPane.showConfirmDialog(this, "Anda ingin menutup aplikasi?", "Konfirmasi Keluar Aplikasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_lbCloseMousePressed

    private void lbMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMinimizeMousePressed
        setState(ICONIFIED);
    }//GEN-LAST:event_lbMinimizeMousePressed

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

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        new Utama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Menu().setVisible(true);
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
    private javax.swing.JComboBox<String> cbTenant;
    private javax.swing.JComboBox<String> cbjenis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbMinimize;
    private javax.swing.JTable tabelmenu;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tharga;
    private javax.swing.JTextField tidmenu;
    private javax.swing.JTextField tidtenant;
    private javax.swing.JTextField tnama;
    private javax.swing.JPanel topbar;
    private javax.swing.JTextField tstok;
    // End of variables declaration//GEN-END:variables
}
