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
import java.awt.event.ItemEvent;
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
public class TransaksiPemesanan extends javax.swing.JFrame {
        public Statement st;       
        Connection conn = connection.getCon();   
        private DefaultTableModel model;
        public long total;
        public long bayar;
        public long kembali;
        
        int id = UserSession.getU_id();
    /**
     * Creates new form EditPesanan
     */
    public TransaksiPemesanan() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        model = new DefaultTableModel();
        tabelpesan.setModel(model);
        model.addColumn("No");
        model.addColumn("No Meja");
        model.addColumn("Nama Menu");
        model.addColumn("Harga Satuan");
        model.addColumn("Jumlah");
        model.addColumn("Subtotal");
        
        idpesan();
        loadData();
        tampilpilih();
        aktif();
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
        btFinish.setEnabled(true);
        btClear.setEnabled(true);
        btEdit.setEnabled(false);
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
                    
            try {
                Connection c = connection.getCon();
            try (Statement s = c.createStatement()) {
                String sql = "SELECT id_tmp, no_meja, namamenu, hargasatuan, jumlahpesan, subtotal FROM temporary_pemesanan";
                    try (ResultSet rs = s.executeQuery(sql)) {
                        while (rs.next()) {
                            Object[] o = new Object[6];
                            o[0] = rs.getString("id_tmp");
                            o[1] = rs.getString("no_meja");
                            o[2] = rs.getString("namamenu");
                            o[3] = rs.getString("hargasatuan");
                            o[4] = rs.getString("jumlahpesan");
                            o[5] = rs.getString("subtotal");
                                                        
                            model.addRow(o);
                        }   
                    }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            btFinish.setEnabled(false);
        }
   }
    
     public void caripesanan(){
       String cari = tcari.getText();
        //String to=jCombo.getItemAt(i).toString();
        
       Object[] baris={"No","No Meja","Nama Menu","Harga Satuan","Jumlah","Subtotal"};
       model = new DefaultTableModel(null, baris);
       tabelpesan.setModel(model);

       //untuk menampilkan di table
       try{
           String sql="SELECT no_pemesanan, no_meja, namamenu, hargasatuan, jumlahpesan, subtotal, id_pemesanan from pemesanan WHERE " 
                   + "no_pemesanan like '%"+cari+"%' "
                   + "OR no_meja like '%"+cari+"%' "
                   + "OR namamenu like '%"+cari+"%' "
                   + "OR hargasatuan like '%"+cari+"%' "
                   + "OR jumlahpesan like '%"+cari+"%' "
                   + "OR subtotal like '%"+cari+"%' "
                   + "OR id_pemesanan like '%"+cari+"%' "
                   + "order by no_pemesanan asc";
           java.sql.Statement stmt=conn.createStatement();
           java.sql.ResultSet rslt=stmt.executeQuery(sql);
           while(rslt.next()){
               String idp = rslt.getString("id_pemesanan");
               String idt = rslt.getString("no_pemesanan");
               String nomeja = rslt.getString("no_meja");
               String nama = rslt.getString("namamenu");
               String harga = rslt.getString("hargasatuan");
               String jumlah = rslt.getString("jumlahpesan");
               String subtotal = rslt.getString("subtotal");
               
               String[] dataField={idt,nomeja,nama,harga,jumlah,subtotal};
               model.addRow(dataField);
               tId.setText(idp);
           }
       }
       catch(SQLException ex){
           JOptionPane.showMessageDialog(null, ex);
       }
    }
        
    private void tampilpilih() {
        try {           
            Statement stm = conn.createStatement();
            String sql = "SELECT DISTINCT namatenant FROM tenant";
            ResultSet r = stm.executeQuery(sql);

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
    private void tampilmenu() {
        try {           
            Statement stm = conn.createStatement();
            String sql = "SELECT DISTINCT menu.namamenu, tenant.id_tenant, menu.stok FROM menu, tenant WHERE tenant.namatenant = '"
                + datatenant + "' AND tenant.id_tenant = menu.id_tenant";
            ResultSet r = stm.executeQuery(sql);
            while (r.next()) {
                cbMenu.setSelectedItem("Pilih");
                cbMenu.addItem(r.getString("namamenu"));
                kedai.setText(r.getString("id_tenant"));
                stok.setText(r.getString("stok"));             
            }
            r.last();
            r.first();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    String namamenu;
    private void tampilharga() {
        try {           
            Statement stm = conn.createStatement();
            String sql = "SELECT harga, id_menu FROM menu WHERE namamenu = '"+namamenu+"'";
            ResultSet r = stm.executeQuery(sql);

             while (r.next()) {
                    tHarga.setText(r.getString("harga"));
                    menu.setText(r.getString("id_menu"));
                }

            r.last();
            r.first();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void idpesan() {
        try {
            Connection c = connection.getCon();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM pemesanan ORDER by id_pemesanan desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String nofak = r.getString("id_pemesanan").substring(1);
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
                tId.setText("P" + Nol + AN);
            } else {
                tId.setText("P0001");
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
      
    protected void kosong() {
        tNomeja.setText("");
        cbTenant.setSelectedItem("Pilih");
        cbMenu.setSelectedItem("Pilih");
        tQty.setText("");
        tHarga.setText("");
        tSubtotal.setText("");
        menu.setText("");
        kedai.setText(""); 
        lbJum.setText("");
        tBayar.setText("");
        tKembalian.setText("");
        tcari.setText("Pencarian ..");
        tcari.setForeground(new Color(102,102,102));
        idpesan();
    }
    
    protected void aktif() {
        tNomeja.setEnabled(true);
        cbTenant.setEnabled(true);
        cbMenu.setEnabled(true);
        tQty.setEnabled(true);
        tHarga.setEnabled(true);
        tSubtotal.setEnabled(true);
        menu.setEnabled(true);
        kedai.setEnabled(true); 
        tNomeja.requestFocus();
        
        btTambah.setEnabled(true);
        btHitung.setEnabled(true);
        btFinish.setEnabled(true);
        idpesan();        
    }
    
    protected void nonaktif() {
        btTambah.setEnabled(false);
        btEdit.setEnabled(false);
        btClear.setEnabled(false);
        btCount.setEnabled(false);
        btPrintStruk.setEnabled(false);
        btFinish.setEnabled(false);
        btHitung.setEnabled(false);
        idpesan();        
    }
    
    public void cetak(){
        try {
            String reportName = connection.PathReport + "struk.jasper";
            HashMap parameter = new HashMap();
            File reportFile = new File (reportName);
            parameter.put("id_pemesanan",tId.getText());
            
            JasperReport jReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, parameter, connection.getCon());
            JasperViewer.viewReport(jPrint, false);
     } catch (JRException e) {
         System.out.println(e);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tNomeja = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbTenant = new javax.swing.JComboBox<>();
        kedai = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbMenu = new javax.swing.JComboBox<>();
        menu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tQty = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tHarga = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tSubtotal = new javax.swing.JTextField();
        btHitung = new javax.swing.JButton();
        btTambah = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btClear = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        btFinish = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelpesan = new javax.swing.JTable();
        lbJum = new javax.swing.JLabel();
        btCount = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tBayar = new javax.swing.JTextField();
        tKembalian = new javax.swing.JTextField();
        btPrintStruk = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tno = new javax.swing.JLabel();
        stok = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btSearch = new javax.swing.JButton();
        btRefresh = new javax.swing.JButton();
        back = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        topbar.add(lbMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1065, 10, -1, 20));

        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-macos-close-20.png"))); // NOI18N
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbCloseMousePressed(evt);
            }
        });
        topbar.add(lbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 10, -1, -1));

        jPanel1.setBackground(new java.awt.Color(26, 26, 46));
        jPanel1.setPreferredSize(new java.awt.Dimension(1190, 585));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(26, 26, 46));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Pesanan");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 33, 76, -1));

        tId.setEditable(false);
        tId.setBackground(new java.awt.Color(204, 204, 204));
        tId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tId.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tId, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 33, 170, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("No.Meja");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 73, -1, -1));

        tNomeja.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tNomeja.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tNomeja, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 71, 71, 23));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tenant");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 113, -1, -1));

        cbTenant.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbTenant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cbTenant.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTenantItemStateChanged(evt);
            }
        });
        jPanel2.add(cbTenant, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 109, 166, 23));

        kedai.setEditable(false);
        kedai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        kedai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(kedai, new org.netbeans.lib.awtextra.AbsoluteConstraints(293, 109, 86, 23));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Menu");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 150, -1, -1));

        cbMenu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cbMenu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMenuItemStateChanged(evt);
            }
        });
        jPanel2.add(cbMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 147, 166, 23));

        menu.setEditable(false);
        menu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        menu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(293, 147, 86, 23));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Jumlah");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(457, 33, 51, 20));

        tQty.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tQty.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        tQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tQtyKeyTyped(evt);
            }
        });
        jPanel2.add(tQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 33, 80, 23));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Harga");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(457, 73, 42, -1));

        tHarga.setEditable(false);
        tHarga.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tHarga.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 71, 134, 23));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Subtotal");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(457, 113, 51, -1));

        tSubtotal.setEditable(false);
        tSubtotal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tSubtotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 109, 134, 23));

        btHitung.setBackground(new java.awt.Color(0, 88, 122));
        btHitung.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btHitung.setForeground(new java.awt.Color(255, 255, 255));
        btHitung.setText("Hitung");
        btHitung.setBorder(null);
        btHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHitungActionPerformed(evt);
            }
        });
        jPanel2.add(btHitung, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 109, 78, 25));

        btTambah.setBackground(new java.awt.Color(0, 88, 122));
        btTambah.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btTambah.setForeground(new java.awt.Color(255, 255, 255));
        btTambah.setText("Tambah Pesanan");
        btTambah.setBorder(null);
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });
        jPanel2.add(btTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 148, 134, 30));

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
        jPanel2.add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 40, 81, 25));

        btClear.setBackground(new java.awt.Color(0, 88, 122));
        btClear.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btClear.setForeground(new java.awt.Color(255, 255, 255));
        btClear.setText("Hapus");
        btClear.setBorder(null);
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });
        jPanel2.add(btClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 100, 81, 25));

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
        jPanel2.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 70, 81, 25));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID User Login:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 200, 80, 20));

        lbLogin.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbLogin.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 200, 60, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 1010, 230));

        btFinish.setBackground(new java.awt.Color(0, 88, 122));
        btFinish.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btFinish.setForeground(new java.awt.Color(255, 255, 255));
        btFinish.setText("Selesai Transaksi");
        btFinish.setBorder(null);
        btFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinishActionPerformed(evt);
            }
        });
        jPanel1.add(btFinish, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 330, 160, 30));

        tabelpesan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelpesan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelpesan.setGridColor(new java.awt.Color(255, 255, 255));
        tabelpesan.setRowHeight(25);
        tabelpesan.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tabelpesan.setShowVerticalLines(false);
        tabelpesan.getTableHeader().setReorderingAllowed(false);
        tabelpesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpesanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelpesan);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 119, 710, 250));

        lbJum.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbJum.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbJum, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 120, 158, 25));

        btCount.setBackground(new java.awt.Color(0, 88, 122));
        btCount.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btCount.setForeground(new java.awt.Color(255, 255, 255));
        btCount.setText("Hitung Total");
        btCount.setBorder(null);
        btCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCountActionPerformed(evt);
            }
        });
        jPanel1.add(btCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 160, 160, 32));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rp");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 110, -1, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bayar");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kembalian");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, -1, 20));

        tBayar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tBayar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        tBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tBayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tBayarKeyTyped(evt);
            }
        });
        jPanel1.add(tBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 210, 161, 23));

        tKembalian.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tKembalian.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel1.add(tKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 250, 161, 23));

        btPrintStruk.setBackground(new java.awt.Color(0, 88, 122));
        btPrintStruk.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btPrintStruk.setForeground(new java.awt.Color(255, 255, 255));
        btPrintStruk.setText("Cetak Struk");
        btPrintStruk.setBorder(null);
        btPrintStruk.setPreferredSize(new java.awt.Dimension(113, 23));
        btPrintStruk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintStrukActionPerformed(evt);
            }
        });
        jPanel1.add(btPrintStruk, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, 160, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Rp");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 210, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Rp");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 250, -1, 20));
        jPanel1.add(tno, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 493, -1, -1));
        jPanel1.add(stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 487, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Transaksi Pemesanan");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, 50));

        btSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/magnifying-glass.png"))); // NOI18N
        btSearch.setBorder(null);
        btSearch.setContentAreaFilled(false);
        btSearch.setFocusable(false);
        btSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 30, 40));

        btRefresh.setBackground(new java.awt.Color(0, 88, 122));
        btRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refreshing.png"))); // NOI18N
        btRefresh.setBorder(null);
        btRefresh.setContentAreaFilled(false);
        btRefresh.setFocusable(false);
        btRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshActionPerformed(evt);
            }
        });
        jPanel1.add(btRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 50, 40, 40));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow.png"))); // NOI18N
        back.setBorder(null);
        back.setContentAreaFilled(false);
        back.setFocusable(false);
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
        jPanel1.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 210, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(topbar, javax.swing.GroupLayout.PREFERRED_SIZE, 1130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topbar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tBayarKeyReleased
        bayar = Integer.parseInt(String.valueOf(tBayar.getText()));
        total = Integer.parseInt(String.valueOf(lbJum.getText()));
        kembali = bayar - total;

        tKembalian.setText(Long.toString(kembali));
    }//GEN-LAST:event_tBayarKeyReleased

    private void btCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCountActionPerformed
        btPrintStruk.setEnabled(true);
        try {
            Connection c = connection.getCon();
            try (Statement s = c.createStatement()) {
                String sql = "SELECT SUM(`subtotal`) AS stotal FROM temporary_pemesanan WHERE id_pemesanan = '"+tId.getText()+"'";
                try (ResultSet r = s.executeQuery(sql)) {
                    while (r.next()) {
                        lbJum.setText(r.getString(""+"stotal"));
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btCountActionPerformed

    private void tabelpesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpesanMouseClicked
        btEdit.setEnabled(true);
        int i = tabelpesan.getSelectedRow();
        if (i == -1) {
            return;
        }
        String no = (String) model.getValueAt(i, 0);
        tno.setText(no);
        String nomeja = (String) model.getValueAt(i, 1);
        tNomeja.setText(nomeja);
        String mn = (String) model.getValueAt(i, 2);
        cbMenu.setSelectedItem(mn);
        String harga = (String) model.getValueAt(i, 3);
        tHarga.setText(harga);
        tHarga.setEnabled(false);
        String jumlah = (String) model.getValueAt(i, 4);
        tQty.setText(jumlah);
        String tot = (String) model.getValueAt(i, 5);
        tSubtotal.setText(tot);
    }//GEN-LAST:event_tabelpesanMouseClicked

    private void btFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinishActionPerformed
    if(tId.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                try {
                    Connection c = connection.getCon();
                    try (Statement s = c.createStatement()) {
                        String sql = "SELECT * FROM temporary_pemesanan WHERE id_pemesanan = '"+tId.getText()+"'";
                        try (ResultSet r = s.executeQuery(sql)) {
                            while (r.next()) {
                                String id = r.getString("id_pemesanan");
                                String id_tmp = r.getString("id_tmp");
                                String nomeja = r.getString("no_meja");
                                String idmn = r.getString("id_menu");
                                String menuu = r.getString("namamenu");
                                String harga = r.getString("hargasatuan");
                                String jumlah = r.getString("jumlahpesan");
                                String subtotal = r.getString("subtotal");
                                String idt = r.getString("id_tenant");
                                String ten = r.getString("namatenant");
                                
                                long millis=System.currentTimeMillis();
                                java.sql.Date date=new java.sql.Date(millis);
                                String tgl = date.toString();
                                
                                String sqla = "INSERT INTO pemesanan VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                try (PreparedStatement ps = c.prepareStatement(sqla)) {
                                    ps.setString(1, null);
                                    ps.setString(2, lbLogin.getText());
                                    ps.setString(3, id_tmp);
                                    ps.setString(4, tgl);
                                    ps.setString(5, id);
                                    ps.setString(6, idt);
                                    ps.setString(7, ten);
                                    ps.setString(8, nomeja);
                                    ps.setString(9, idmn);
                                    ps.setString(10, menuu);
                                    ps.setString(11, harga);
                                    ps.setString(12, jumlah);
                                    ps.setString(13, subtotal);
                                    ps.setString(14, lbJum.getText());
                                    ps.setString(15, tBayar.getText());
                                    ps.setString(16, tKembalian.getText());
                                    
                                    ps.executeUpdate();
                                }
                            }
                        }
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
                }
                String emptyData ="TRUNCATE temporary_pemesanan";
                java.sql.Connection connn=(Connection)connection.getCon();
                java.sql.PreparedStatement pst=connn.prepareStatement(emptyData);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Transaksi Berhasil");
                loadData();
                idpesan();
                kosong();
            } catch (SQLException ex) {
            Logger.getLogger(TransaksiPemesanan.class.getName()).log(Level.SEVERE,null, ex);
            }
        }
    }//GEN-LAST:event_btFinishActionPerformed

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        if ( tId.getText().equals("") || tQty.getText().equals("") || tHarga.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Pilih Data","Kedai Avicenna", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Ingin Menghapus Data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION) {
                try {
                    String sql ="DELETE FROM temporary_pemesanan WHERE id_tmp='"+tno.getText()+"'";

                    java.sql.PreparedStatement ps=conn.prepareStatement(sql);
                    ps.execute();
                    JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            kosong();
            loadData();
           }
        }
    }//GEN-LAST:event_btClearActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        if(tId.getText().equals("") ||cbTenant.getSelectedItem().equals("") || cbMenu.getSelectedItem().equals("")|| tQty.getText().equals("") 
                || tSubtotal.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
                String sql = "UPDATE temporary_pemesanan SET no_meja=?, id_menu=?, namamenu=?, hargasatuan=?, jumlahpesan=?, subtotal=?, id_tenant=?, "
                        + "namatenant=? WHERE id_tmp='" + tno.getText()+ "'";
                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.setString(1, tNomeja.getText());
                    p.setString(2, menu.getText());
                    p.setString(3, cbMenu.getSelectedItem().toString());
                    p.setString(4, tHarga.getText());
                    p.setString(5, tQty.getText());
                    p.setString(6, tSubtotal.getText());
                    p.setString(7, kedai.getText());
                    p.setString(8, cbTenant.getSelectedItem().toString());
                    
                    p.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Data Tidak Dapat Diubah", "Kedai Avicenna", JOptionPane.ERROR_MESSAGE);
            } finally {
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
                loadData();
                kosong();
                btFinish.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btEditActionPerformed

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        btCount.setEnabled(true);
        if(tId.getText().equals("") || cbTenant.getSelectedItem().equals("") || cbMenu.getSelectedItem().equals("")|| tQty.getText().equals("")||
            tHarga.getText().equals("")|| tSubtotal.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.WARNING_MESSAGE);
        }else{
            btFinish.setEnabled(true);
            
            String idt = kedai.getText();
            String meja = tNomeja.getText();
            String idmn = menu.getText();
            String ten = cbTenant.getSelectedItem().toString();
            String menuu = cbMenu.getSelectedItem().toString();
            String jumlah = tQty.getText();
            String harga = tHarga.getText();
            String subtotal = tSubtotal.getText();           
            String id = tId.getText();

            String sql = "INSERT INTO temporary_pemesanan VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, null);
                    ps.setString(2, id);
                    ps.setString(3, meja);
                    ps.setString(4, idmn);
                    ps.setString(5, menuu);
                    ps.setString(6, harga);
                    ps.setString(7, jumlah);
                    ps.setString(8, subtotal);
                    ps.setString(9, idt);
                    ps.setString(10, ten);
                    ps.setString(11, "0");
                    ps.setString(12, "0");
                    
                    ps.executeUpdate();
                }
                
                JOptionPane.showMessageDialog(null, "Pesanan Ditambahkan", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Pesanan Gagal");
            } finally {
                tNomeja.setText(null);
                cbTenant.setSelectedItem("Pilih");
                cbMenu.setSelectedItem("Pilih");
                tQty.setText(null);
                tHarga.setText(null);
                tSubtotal.setText(null);
                menu.setText(null);
                kedai.setText(null);
                loadData();
            }     
        }
    }//GEN-LAST:event_btTambahActionPerformed

    private void btHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHitungActionPerformed
        aktif();
        if ( tId.getText().equals("") || cbTenant.getSelectedItem().equals("") || cbMenu.getSelectedItem().equals("") 
            || tQty.getText().equals("") || tHarga.getText().equals("") || kedai.getText().equals("") || menu.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi Pesanan!","Kedai Avicenna", JOptionPane.WARNING_MESSAGE);
        } else {
            String lbJumlah = tQty.getText();
            int jumlahPesan = Integer.parseInt(lbJumlah);
        
            String stokMenu = stok.getText();
            int totalMenu = Integer.parseInt(stokMenu);
        if (jumlahPesan > totalMenu) {
            JOptionPane.showMessageDialog(null, "Jumlah Melebihi Stok", "Kedai Avicenna", JOptionPane.WARNING_MESSAGE);
            tQty.setText("");
        } else {       
            if(tQty.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Isi Jumlah Beli!");
        } else {
            int jumlah, harga, totall;

            jumlah = Integer.parseInt(tQty.getText());
            harga = Integer.parseInt(tHarga.getText());
            totall = jumlah * harga;

            tSubtotal.setText(Integer.toString(totall));
                }
            }
        }
    }//GEN-LAST:event_btHitungActionPerformed

    private void cbMenuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMenuItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            namamenu = cbMenu.getSelectedItem().toString();
            tampilharga();
        }
    }//GEN-LAST:event_cbMenuItemStateChanged

    private void cbTenantItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTenantItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            datatenant = cbTenant.getSelectedItem().toString();
            cbMenu.removeAllItems();
            tampilmenu();
        }
    }//GEN-LAST:event_cbTenantItemStateChanged

    private void tQtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tQtyKeyTyped
        FilterAngka(evt);
    }//GEN-LAST:event_tQtyKeyTyped

    private void btPrintStrukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintStrukActionPerformed
        if(tBayar.getText().equals("") ||lbJum.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.WARNING_MESSAGE);
        
        }else{
            String a = tKembalian.getText();
            int ab = Integer.parseInt(String.valueOf(tKembalian.getText()));
              if(ab < 0){
                JOptionPane.showMessageDialog(null, "Maaf, Uang Anda Kurang", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
                tBayar.setText("");
                tKembalian.setText("");
              }else{
         try {
                String sql = "UPDATE temporary_pemesanan SET bayar=?, kembalian=? WHERE id_pemesanan='" + tId.getText()+ "'";

             try (PreparedStatement p = conn.prepareStatement(sql)) {
                 p.setString(1, tBayar.getText());
                 p.setString(2, tKembalian.getText());
                 p.executeUpdate();
             }
                JOptionPane.showMessageDialog(null, "Pembayaran Sukses!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
            } finally {
                loadData();
                btFinish.setEnabled(true);
                cetak();
                }
              }
            }
    }//GEN-LAST:event_btPrintStrukActionPerformed

    private void tBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tBayarKeyTyped
         FilterAngka(evt);
    }//GEN-LAST:event_tBayarKeyTyped

    private void btSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchActionPerformed
        caripesanan();
        nonaktif();
    }//GEN-LAST:event_btSearchActionPerformed

    private void btRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshActionPerformed
        loadData();
        kosong();
        aktif();
    }//GEN-LAST:event_btRefreshActionPerformed

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        loadData();
        kosong();
        aktif();
    }//GEN-LAST:event_btBatalActionPerformed

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
        caripesanan();
        nonaktif();
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
            java.util.logging.Logger.getLogger(TransaksiPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TransaksiPemesanan().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton btBatal;
    private javax.swing.JButton btClear;
    private javax.swing.JButton btCount;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btFinish;
    private javax.swing.JButton btHitung;
    private javax.swing.JButton btPrintStruk;
    private javax.swing.JButton btRefresh;
    private javax.swing.JButton btSearch;
    private javax.swing.JButton btTambah;
    private javax.swing.JComboBox<String> cbMenu;
    private javax.swing.JComboBox<String> cbTenant;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kedai;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbJum;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbMinimize;
    private javax.swing.JTextField menu;
    private javax.swing.JLabel stok;
    private javax.swing.JTextField tBayar;
    private javax.swing.JTextField tHarga;
    private javax.swing.JTextField tId;
    private javax.swing.JTextField tKembalian;
    private javax.swing.JTextField tNomeja;
    private javax.swing.JTextField tQty;
    private javax.swing.JTextField tSubtotal;
    private javax.swing.JTable tabelpesan;
    private javax.swing.JTextField tcari;
    private javax.swing.JLabel tno;
    private javax.swing.JPanel topbar;
    // End of variables declaration//GEN-END:variables
}
