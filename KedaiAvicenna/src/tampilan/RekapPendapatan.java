/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import database.UserSession;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import database.connection;
import java.awt.Color;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nurul
 */
public class RekapPendapatan extends javax.swing.JFrame {
    private Connection conn = connection.getCon();
    private ResultSet hasil;
    private Statement stat;
    private String dateDay;
    private String dateWeek1;
    private String dateWeek2;
    private String dateMonth;
    int id = UserSession.getU_id();
    
     DefaultTableModel tbl1 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel tbl2 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel tbl3 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    DefaultTableModel tbl4 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    DefaultTableModel tbl5 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    DefaultTableModel tbl6 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    DefaultTableModel tbl7 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    DefaultTableModel tbl8 = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    /**
     * Creates new form Rekapitulasi
     */
    public RekapPendapatan() {
        initComponents();
     
        this.setBackground(new Color(0,0,0,0));
        tampiluser();
        tampiluser1();
        tampiluser2();
    }

     private void loadDataHarian() {
        Object [] baris = {"Tanggal","ID Tenant", "Nama Tenant", "Total Pendapatan"};
        tbl4 = new DefaultTableModel(null, baris) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableInput.setModel(tbl4);
        
        java.util.Date utilStartDate = tanggalharian.getDate();
        java.sql.Date tgl = new java.sql.Date(utilStartDate.getTime());        
        try {   
            String sql = "SELECT tglpesan, id_tenant, namatenant, SUM(subtotal) AS subtot, COUNT(*) FROM pemesanan WHERE tglpesan ='"+tgl+"' GROUP BY id_tenant";
            stat = conn.createStatement();
            hasil = stat.executeQuery(sql);
            
            while (hasil.next()) {
                String tanggall = hasil.getString("tglpesan");
                String idtenant = hasil.getString("id_tenant");
                String nama = hasil.getString("namatenant");
                String subtotal = hasil.getString("subtot");
                
                String[] data = {tanggall, idtenant, nama, subtotal};
                tbl4.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan DATA!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
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
    
        private void tampiluser1(){
        try {
            String sql = "SELECT user_id FROM admin WHERE user_id = '" + lbLogin1.getText() + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lbLogin1.setText(rs.getString("user_id"));
            }    
        } catch (SQLException ex) {
            Logger.getLogger(InfoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbLogin1.setText(String.valueOf(id));
    }
        
            private void tampiluser2(){
        try {
            String sql = "SELECT user_id FROM admin WHERE user_id = '" + lbLogin2.getText() + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lbLogin2.setText(rs.getString("user_id"));
            }    
        } catch (SQLException ex) {
            Logger.getLogger(InfoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbLogin2.setText(String.valueOf(id));
    }
     
    private void loadDataMingguan1() {
        Object []baris = {"Tanggal","ID Tenant", "Nama Tenant", "Pendapatan Kotor"};
        tbl5 = new DefaultTableModel(null, baris) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelInputMingguan1.setModel(tbl5);
        java.util.Date utilStartDate = tglmulai.getDate();
        java.sql.Date tglmingguan1 = new java.sql.Date(utilStartDate.getTime());
    
        java.util.Date utilEndDate = tglakhir.getDate();
        java.sql.Date tglmingguan2 = new java.sql.Date(utilEndDate.getTime());         
        try {   
            String sql = "SELECT tanggal, id_tenant, namatenant, SUM(pendapatankotor) AS pendapatan, COUNT(*) FROM rekapitulasi_harian "
                          + "WHERE tanggal between '"+tglmingguan1+"' AND '"+tglmingguan2+"' GROUP BY id_tenant";
            stat = conn.createStatement();
            hasil = stat.executeQuery(sql);
            
            while (hasil.next()) {
                String tanggal = hasil.getString("tanggal");
                String id = hasil.getString("id_tenant");
                String nama = hasil.getString("namatenant");
                String pendapatan = hasil.getString("pendapatan");
                
                String[] data = {tanggal, id, nama, pendapatan};
                tbl5.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan Data", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            dispose();
        }
    }
    
    private void loadDataMingguan2() {
        Object []baris = {"Tanggal", "ID Reservasi", "Biaya Tambahan", "Acara"};
        tbl6 = new DefaultTableModel(null, baris) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelInputMingguan2.setModel(tbl6);
        java.util.Date utilStartDate = tglmulai.getDate();
        java.sql.Date tglmingguan3 = new java.sql.Date(utilStartDate.getTime());
    
        java.util.Date utilEndDate = tglakhir.getDate();
        java.sql.Date tglmingguan4 = new java.sql.Date(utilEndDate.getTime());         
        try {   
            String sql = "SELECT tanggal, id_reservasi, biayatambahan, acara FROM reservasi_tempat WHERE tanggal between '"+tglmingguan3+"' AND '"+tglmingguan4+"'";
            stat = conn.createStatement();
            hasil = stat.executeQuery(sql);
            
            while (hasil.next()) {
                String tanggal = hasil.getString("tanggal");
                String id = hasil.getString("id_reservasi");
                String biaya = hasil.getString("biayatambahan");
                String acara = hasil.getString("acara");
                
                String[] data = {tanggal, id, biaya, acara};
                tbl6.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan DATA!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
  
    private void tampil_harian() {
        Object [] baris = {"No.", "Tanggal","ID Tenant", "Nama Tenant", "Total Pendapatan"};
        tbl3 = new DefaultTableModel(null, baris) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelharian.setModel(tbl3);
        java.util.Date utilStartDate = tanggalharian.getDate();
        java.sql.Date tanggal = new java.sql.Date(utilStartDate.getTime());        
        try {   
            String sql = "SELECT * FROM rekapitulasi_harian WHERE tanggal='"+tanggal+"' ORDER BY id_tenant ASC";
            stat = conn.createStatement();
            hasil = stat.executeQuery(sql);
            
            while (hasil.next()) {
                String id = hasil.getString("id_rekapharian");
                String tanggall = hasil.getString("tanggal");
                String idtenant = hasil.getString("id_tenant");
                String nama = hasil.getString("namatenant");
                String pendkot = hasil.getString("pendapatankotor");
                
                String[] data = {id, tanggall, idtenant, nama, pendkot};
                tbl3.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan DATA!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void tampil_mingguan() {
        Object []baris = {"No.", "Dari Tanggal", "Hingga Tanggal", "ID Tenant", "Nama Tenant", "Pend. Kotor", "Potongan", "Pend.Kedai", "Pend.Bersih"};
        tbl2 = new DefaultTableModel(null, baris) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelmingguan.setModel(tbl2);
        java.util.Date utilStartDate = tglmulai.getDate();
        java.sql.Date tanggal2 = new java.sql.Date(utilStartDate.getTime());        
        try {   
            String sql = "SELECT * FROM rekapitulasi_mingguan WHERE tanggalakhir='"+tanggal2+"' OR tanggalmulai='"+tanggal2+"' ORDER BY id_tenant ASC";
            stat = conn.createStatement();
            hasil = stat.executeQuery(sql);
            
            while (hasil.next()) {
                String no = hasil.getString("id_rekapmingguan");
                String tgll1 = hasil.getString("tanggalmulai");
                String tgll2 = hasil.getString("tanggalakhir");
                String idtenant = hasil.getString("id_tenant");
                String nama = hasil.getString("namatenant");
                String pendkot = hasil.getString("pendapatankotor");
                String pot = hasil.getString("potongan");
                String pkd = hasil.getString("pendapatankedai");
                String pendber = hasil.getString("pendapatanbersih");
                
                String[] data = {no, tgll1, tgll2, idtenant, nama, pendkot, pot, pkd, pendber};
                tbl2.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan DATA!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            dispose();
        }
    }
    
    private void loadDataPendapatanBulanan() throws SQLException {
        Object [] baris = {"Tanggal","Total Pendapatan"};
        tbl7 = new DefaultTableModel(null, baris) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelpendapatan.setModel(tbl7);
        java.util.Date utilStartDate = tanggalbulanan.getDate();
        java.sql.Date tanggaal = new java.sql.Date(utilStartDate.getTime());
        
        try {   
            String sql = "SELECT DISTINCT tanggalakhir, pendapatankedai FROM rekapitulasi_mingguan WHERE MONTH(tanggalakhir)=MONTH('"+tanggaal+"') "
                    + "AND YEAR(tanggalakhir)=YEAR('"+tanggaal+"') GROUP BY id_tenant";
            stat = conn.createStatement();
            hasil = stat.executeQuery(sql);
            
            try (ResultSet r = stat.executeQuery(sql)) {
                while (r.next()) {
                    Object[] o = new Object[2];
                    o[0]= r.getString("tanggalakhir");
                    o[1]= r.getString("pendapatankedai");
                    
                    tbl7.addRow(o);
                }
            }   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan DATA YAAA!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        try {
            try (Statement s = conn.createStatement()) {
                String sql = "SELECT SUM(jumlah) AS jum FROM pengeluaran WHERE MONTH(tanggal)=MONTH('"+tanggaal+"') AND YEAR(tanggal)=YEAR('"+tanggaal+"')";

                try (ResultSet r = s.executeQuery(sql)) {
                    while (r.next()) {
                        tpengeluaranbulanan.setText(r.getString("jum"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }
     
    private void loadDataPengeluaranBulanan() throws SQLException {
        Object [] baris = {"Tanggal Pengeluaran","Jumlah Pengeluaran", "Keterangan"};
        tbl8 = new DefaultTableModel(null, baris) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelpengeluaran.setModel(tbl8);
        java.util.Date utilStartDate = tanggalbulanan.getDate();
        java.sql.Date tanggaal = new java.sql.Date(utilStartDate.getTime());
        
        try {   
            String sql = "SELECT tanggal, jumlah, keterangan FROM pengeluaran WHERE MONTH(tanggal)=MONTH('"+tanggaal+"') AND YEAR(tanggal)=YEAR('"+tanggaal+"')";
            stat = conn.createStatement();
            hasil = stat.executeQuery(sql);
            
            while (hasil.next()) {
                String tgl = hasil.getString("tanggal");
                String jumlah = hasil.getString("jumlah");
                String ket = hasil.getString("keterangan");
                
                String[] data = { tgl, jumlah, ket };
                tbl8.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan DATA GGGG!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }       
        try (Statement s = conn.createStatement()) {
            String sql = "SELECT SUM(pendapatankedai) AS pendkedai FROM rekapitulasi_mingguan WHERE MONTH(tanggalakhir)=MONTH('"+tanggaal+"') AND YEAR(tanggalakhir)=YEAR('"+tanggaal+"')";

            try (ResultSet r = s.executeQuery(sql)) {
                while (r.next()) {
                    tpendapatanbulanan.setText(r.getString("pendkedai"));
                }
            } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }  
        }
    }
    
    private void tampil_bulanan() {
        Object [] baris = {"No.","Tanggal Rekap","Pendapatan", "Pengeluaran", "Keuntungan"};
        tbl1 = new DefaultTableModel(null, baris) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelbulanan.setModel(tbl1);
        java.util.Date utilStartDate = tanggalbulanan.getDate();
        java.sql.Date tanggaal = new java.sql.Date(utilStartDate.getTime());
        
        try {   
            String sql = "SELECT * FROM rekapitulasi_bulanan WHERE MONTH(tanggalrekap)=MONTH('"+tanggaal+"') AND YEAR(tanggalrekap)=YEAR('"+tanggaal+"')";
            stat = conn.createStatement();
            hasil = stat.executeQuery(sql);
            
            while (hasil.next()) {
                String no = hasil.getString("id_rekapbulanan");
                String bulan = hasil.getString("tanggalrekap");
                String tpend = hasil.getString("totalpendapatan");
                String tpeng = hasil.getString("totalpengeluaran");
                String untung = hasil.getString("keuntungan");
                
                String[] data = { no, bulan, tpend, tpeng, untung };
                tbl1.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal Menampilkan DATA!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
     
    protected void kosong1() {
        tid.setText("");
        tnama.setText("");
        tidtenant.setText("");
        tpend1.setText("");
    }
    
    protected void aktif1() {
        tid.setEnabled(true);       // Pengecekan apakah textfield aktif atau tidak
        tidtenant.setEnabled(true);
        tnama.setEnabled(true);
        tpend1.setEnabled(true);
        tanggalharian.setEnabled(true);
        btInputHarian.setEnabled(true);
        btedit1.setEnabled(false);
        bthapus1.setEnabled(false);
        btbatal1.setEnabled(true);
    }
    
    protected void kosong2() {
        tid2.setText("");
        tnama2.setText("");
        tidtenant2.setText("");
        tpendkotor.setText("");
        tpendkedai.setText("");
        tpendbersih.setText("");
    }
    
    protected void aktif2() {
        tid2.setEnabled(true);       // Pengecekan apakah textfield aktif atau tidak
        tidtenant2.setEnabled(true);
        tnama2.setEnabled(true);
        tpendkotor.setEnabled(true);
        tpendbersih.setEnabled(true);
        tpendkedai.setEnabled(true);
        tpot.setEnabled(true);
        tglmulai.setEnabled(true);
        tglakhir.setEnabled(true);
        btInputMingguan.setEnabled(true);
        btedit2.setEnabled(false);
        btHapus1.setEnabled(false);
        btbatal2.setEnabled(true);
    }
    
    protected void kosong3() {
        tid3.setText("");
        tpendapatanbulanan.setText("");
        tpengeluaranbulanan.setText("");
        tanggalbulanan.setDate(null);
        tkeuntunganbulanan.setText("");
    }
    
    protected void aktif3() {
        tid3.setEnabled(true);       // Pengecekan apakah textfield aktif atau tidak
        tpendapatanbulanan.setEnabled(true);
        tpengeluaranbulanan.setEnabled(true);
        tkeuntunganbulanan.setEnabled(true);
        tanggalbulanan.setEnabled(true);
        btInputBulanan.setEnabled(true);
        btedit3.setEnabled(false);
        bthapus3.setEnabled(false);
        btbatal3.setEnabled(true);
    }
    
    protected void Reserve() {
        tidtenant.setEnabled(false);
        tnama2.setEnabled(false);
        tpendkotor.setEnabled(false);
        tpendkedai.setEnabled(true);
        tpendbersih.setEnabled(false);
        tpot.setEnabled(false);
        btHitung.setEnabled(false);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelharian = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tanggalharian = new com.toedter.calendar.JDateChooser();
        tampilharian = new javax.swing.JButton();
        btrefresh2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tidtenant = new javax.swing.JTextField();
        tnama = new javax.swing.JTextField();
        tpend1 = new javax.swing.JTextField();
        btedit1 = new javax.swing.JButton();
        btbatal1 = new javax.swing.JButton();
        bthapus1 = new javax.swing.JButton();
        btInputHarian = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        tid = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableInput = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbLogin2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelmingguan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        tglmulai = new com.toedter.calendar.JDateChooser();
        tampilmingguan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tglakhir = new com.toedter.calendar.JDateChooser();
        btrefresh1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tidtenant2 = new javax.swing.JTextField();
        tnama2 = new javax.swing.JTextField();
        tpendkotor = new javax.swing.JTextField();
        btedit2 = new javax.swing.JButton();
        btbatal2 = new javax.swing.JButton();
        btHapus1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btInputMingguan = new javax.swing.JButton();
        tpot = new javax.swing.JTextField();
        tpendkedai = new javax.swing.JTextField();
        tpendbersih = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        tid2 = new javax.swing.JTextField();
        btHitung = new javax.swing.JButton();
        btreset = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabelInputMingguan1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelInputMingguan2 = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lbLogin1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabelbulanan = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        tanggalbulanan = new com.toedter.calendar.JDateChooser();
        tampilbulanan = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tpendapatanbulanan = new javax.swing.JTextField();
        tpengeluaranbulanan = new javax.swing.JTextField();
        tkeuntunganbulanan = new javax.swing.JTextField();
        btedit3 = new javax.swing.JButton();
        btbatal3 = new javax.swing.JButton();
        bthapus3 = new javax.swing.JButton();
        btInputBulanan = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        tid3 = new javax.swing.JTextField();
        bthitung = new javax.swing.JButton();
        btrefresh = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelpendapatan = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelpengeluaran = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lbLogin = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topbar.setBackground(new java.awt.Color(19, 19, 39));
        topbar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-minimize-window-20.png"))); // NOI18N
        lbMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbMinimizeMousePressed(evt);
            }
        });
        topbar.add(lbMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 10, -1, 20));

        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-macos-close-20.png"))); // NOI18N
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbCloseMousePressed(evt);
            }
        });
        topbar.add(lbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 10, -1, -1));

        getContentPane().add(topbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 40));

        jPanel1.setBackground(new java.awt.Color(26, 26, 46));
        jPanel1.setPreferredSize(new java.awt.Dimension(1119, 590));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(26, 26, 46));
        jTabbedPane1.setForeground(new java.awt.Color(153, 153, 153));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1119, 590));

        jPanel3.setBackground(new java.awt.Color(26, 26, 46));
        jPanel3.setPreferredSize(new java.awt.Dimension(1119, 590));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setHorizontalScrollBar(null);

        tabelharian.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelharian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Tanggal", "ID Tenant", "Nama Tenant", "Pendapatan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelharian.setGridColor(new java.awt.Color(255, 255, 255));
        tabelharian.setRowHeight(25);
        tabelharian.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tabelharian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelharianMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelharian);
        if (tabelharian.getColumnModel().getColumnCount() > 0) {
            tabelharian.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 660, 180));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Masukan Tanggal");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 100, 20));

        tanggalharian.setDateFormatString("dd/MM/yyyy");
        tanggalharian.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggalharianPropertyChange(evt);
            }
        });
        jPanel3.add(tanggalharian, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 140, 25));

        tampilharian.setBackground(new java.awt.Color(0, 88, 122));
        tampilharian.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tampilharian.setForeground(new java.awt.Color(255, 255, 255));
        tampilharian.setText("Tampilkan");
        tampilharian.setBorder(null);
        tampilharian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tampilharianActionPerformed(evt);
            }
        });
        jPanel3.add(tampilharian, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 100, 25));

        btrefresh2.setBackground(new java.awt.Color(86, 93, 71));
        btrefresh2.setForeground(new java.awt.Color(255, 255, 255));
        btrefresh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refreshing.png"))); // NOI18N
        btrefresh2.setBorder(null);
        btrefresh2.setContentAreaFilled(false);
        btrefresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrefresh2ActionPerformed(evt);
            }
        });
        jPanel3.add(btrefresh2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 30, -1));

        jPanel2.setBackground(new java.awt.Color(26, 26, 46));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID Tenant");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 61, 23));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nama Tenant");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 75, 23));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Pendapatan");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 75, 23));

        tidtenant.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tidtenant.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tidtenant, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 119, 23));

        tnama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tnama.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 160, 23));

        tpend1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tpend1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        jPanel2.add(tpend1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 163, 23));

        btedit1.setBackground(new java.awt.Color(0, 88, 122));
        btedit1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btedit1.setForeground(new java.awt.Color(255, 255, 255));
        btedit1.setText("Edit");
        btedit1.setBorder(null);
        btedit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btedit1ActionPerformed(evt);
            }
        });
        jPanel2.add(btedit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 72, 25));

        btbatal1.setBackground(new java.awt.Color(0, 88, 122));
        btbatal1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btbatal1.setForeground(new java.awt.Color(255, 255, 255));
        btbatal1.setText("Batal");
        btbatal1.setBorder(null);
        btbatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatal1ActionPerformed(evt);
            }
        });
        jPanel2.add(btbatal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 72, 25));

        bthapus1.setBackground(new java.awt.Color(0, 88, 122));
        bthapus1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        bthapus1.setForeground(new java.awt.Color(255, 255, 255));
        bthapus1.setText("Hapus");
        bthapus1.setBorder(null);
        bthapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapus1ActionPerformed(evt);
            }
        });
        jPanel2.add(bthapus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 72, 25));

        btInputHarian.setBackground(new java.awt.Color(0, 88, 122));
        btInputHarian.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btInputHarian.setForeground(new java.awt.Color(255, 255, 255));
        btInputHarian.setText("Input Rekapitulasi Harian");
        btInputHarian.setBorder(null);
        btInputHarian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btInputHarian.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btInputHarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInputHarianActionPerformed(evt);
            }
        });
        jPanel2.add(btInputHarian, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 250, 27));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("ID");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 40, 23));

        tid.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tid.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        tid.setEnabled(false);
        jPanel2.add(tid, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 91, 23));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 90, 350, 350));

        tableInput.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tableInput.setModel(new javax.swing.table.DefaultTableModel(
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
        tableInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableInputMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableInput);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 660, 170));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pendapatan Per Hari");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("ID User Login:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 450, 80, 20));

        lbLogin2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbLogin2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbLogin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 450, 70, 20));

        jTabbedPane1.addTab("Rekapitulasi Harian", jPanel3);

        jPanel4.setBackground(new java.awt.Color(26, 26, 46));
        jPanel4.setPreferredSize(new java.awt.Dimension(1119, 590));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelmingguan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelmingguan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Dari Tanggal", "Hingga Tanggal", "ID Tenant", "Nama Tenant", "Pendapatan Kotor", "Potongan", "Pendapatan Bersih"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelmingguan.setGridColor(new java.awt.Color(255, 255, 255));
        tabelmingguan.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tabelmingguan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelmingguanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelmingguan);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 800, 180));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Masukan Tanggal");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 100, 20));

        tglmulai.setDateFormatString("yyyy-MM-dd");
        tglmulai.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglmulaiPropertyChange(evt);
            }
        });
        jPanel4.add(tglmulai, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 119, 23));

        tampilmingguan.setBackground(new java.awt.Color(0, 88, 122));
        tampilmingguan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tampilmingguan.setForeground(new java.awt.Color(255, 255, 255));
        tampilmingguan.setText("Tampilkan");
        tampilmingguan.setBorder(null);
        tampilmingguan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tampilmingguanActionPerformed(evt);
            }
        });
        jPanel4.add(tampilmingguan, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 100, 25));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("s/d");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, 20));

        tglakhir.setDateFormatString("yyyy-MM-dd");
        tglakhir.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglakhirPropertyChange(evt);
            }
        });
        jPanel4.add(tglakhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 119, 23));

        btrefresh1.setBackground(new java.awt.Color(0, 88, 122));
        btrefresh1.setForeground(new java.awt.Color(255, 255, 255));
        btrefresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refreshing.png"))); // NOI18N
        btrefresh1.setToolTipText("");
        btrefresh1.setBorder(null);
        btrefresh1.setContentAreaFilled(false);
        btrefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrefresh1ActionPerformed(evt);
            }
        });
        jPanel4.add(btrefresh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 30, -1));

        jPanel7.setBackground(new java.awt.Color(26, 26, 46));
        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ID Tenant");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nama Tenant");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Pendapatan Kotor Tenant");

        tidtenant2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));

        tnama2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));

        tpendkotor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));

        btedit2.setBackground(new java.awt.Color(0, 88, 122));
        btedit2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btedit2.setForeground(new java.awt.Color(255, 255, 255));
        btedit2.setText("Edit");
        btedit2.setBorder(null);
        btedit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btedit2ActionPerformed(evt);
            }
        });

        btbatal2.setBackground(new java.awt.Color(0, 88, 122));
        btbatal2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btbatal2.setForeground(new java.awt.Color(255, 255, 255));
        btbatal2.setText("Batal");
        btbatal2.setBorder(null);
        btbatal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatal2ActionPerformed(evt);
            }
        });

        btHapus1.setBackground(new java.awt.Color(0, 88, 122));
        btHapus1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btHapus1.setForeground(new java.awt.Color(255, 255, 255));
        btHapus1.setText("Hapus");
        btHapus1.setBorder(null);
        btHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapus1ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Potongan");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Pendapatan Restoran");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Pendapatan Bersih Tenant");

        btInputMingguan.setBackground(new java.awt.Color(0, 88, 122));
        btInputMingguan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btInputMingguan.setForeground(new java.awt.Color(255, 255, 255));
        btInputMingguan.setText("Input Rekapitulasi Mingguan");
        btInputMingguan.setBorder(null);
        btInputMingguan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btInputMingguan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btInputMingguan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInputMingguanActionPerformed(evt);
            }
        });

        tpot.setBackground(new java.awt.Color(240, 240, 240));
        tpot.setText("0.1");
        tpot.setToolTipText("");
        tpot.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        tpot.setEnabled(false);

        tpendkedai.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));

        tpendbersih.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("ID");

        tid2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        tid2.setEnabled(false);

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

        btreset.setBackground(new java.awt.Color(86, 93, 71));
        btreset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/settings.png"))); // NOI18N
        btreset.setBorderPainted(false);
        btreset.setContentAreaFilled(false);
        btreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btresetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(tid2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(tidtenant2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(tnama2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tpendkotor, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(tpot, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(btreset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tpendkedai, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tpendbersih, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(btHitung, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btInputMingguan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btedit2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btbatal2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btHapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tid2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tidtenant2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tnama2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpendkotor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btreset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tpot, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpendkedai, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpendbersih, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btHitung, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btInputMingguan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btedit2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btbatal2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btHapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 350, 400));

        tabelInputMingguan1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelInputMingguan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelInputMingguan1tabelmingguanMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tabelInputMingguan1);

        jPanel4.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 410, 190));

        tabelInputMingguan2.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelInputMingguan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelInputMingguan2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelInputMingguan2);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, 360, 190));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Pendapatan Lain");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 280, -1, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Pendapatan Per Minggu");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, -1, -1));

        lbLogin1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbLogin1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbLogin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 470, 70, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ID User Login:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 470, 80, 20));

        jTabbedPane1.addTab("Rekapitulasi Mingguan", jPanel4);

        jPanel5.setBackground(new java.awt.Color(26, 26, 46));
        jPanel5.setPreferredSize(new java.awt.Dimension(1119, 590));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelbulanan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelbulanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tanggal Rekap", "Pendapatan", "Pengeluaran", "Keuntungan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelbulanan.setGridColor(new java.awt.Color(255, 255, 255));
        tabelbulanan.setRowHeight(25);
        tabelbulanan.setSelectionBackground(new java.awt.Color(255, 204, 102));
        tabelbulanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelbulananMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tabelbulanan);

        jPanel5.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 62, 750, 190));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Masukan Tanggal");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 110, 20));

        tanggalbulanan.setDateFormatString("yyyy-MM-dd");
        tanggalbulanan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggalbulananPropertyChange(evt);
            }
        });
        jPanel5.add(tanggalbulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 140, 25));

        tampilbulanan.setBackground(new java.awt.Color(0, 88, 122));
        tampilbulanan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tampilbulanan.setForeground(new java.awt.Color(255, 255, 255));
        tampilbulanan.setText("Tampilkan");
        tampilbulanan.setBorder(null);
        tampilbulanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tampilbulananActionPerformed(evt);
            }
        });
        jPanel5.add(tampilbulanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 100, 25));

        jPanel8.setBackground(new java.awt.Color(26, 26, 46));
        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Total Pendapatan");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Total Pengeluaran");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Keuntungan");

        tpendapatanbulanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));

        tpengeluaranbulanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));

        tkeuntunganbulanan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));

        btedit3.setBackground(new java.awt.Color(0, 88, 122));
        btedit3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btedit3.setForeground(new java.awt.Color(255, 255, 255));
        btedit3.setText("Edit");
        btedit3.setBorder(null);
        btedit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btedit3ActionPerformed(evt);
            }
        });

        btbatal3.setBackground(new java.awt.Color(0, 88, 122));
        btbatal3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btbatal3.setForeground(new java.awt.Color(255, 255, 255));
        btbatal3.setText("Batal");
        btbatal3.setBorder(null);
        btbatal3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatal3ActionPerformed(evt);
            }
        });

        bthapus3.setBackground(new java.awt.Color(0, 88, 122));
        bthapus3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        bthapus3.setForeground(new java.awt.Color(255, 255, 255));
        bthapus3.setText("Hapus");
        bthapus3.setBorder(null);
        bthapus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapus3ActionPerformed(evt);
            }
        });

        btInputBulanan.setBackground(new java.awt.Color(0, 88, 122));
        btInputBulanan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btInputBulanan.setForeground(new java.awt.Color(255, 255, 255));
        btInputBulanan.setText("Input Rekapitulasi Bulanan");
        btInputBulanan.setBorder(null);
        btInputBulanan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btInputBulanan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btInputBulanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInputBulananActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("ID");

        tid3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        tid3.setEnabled(false);
        tid3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tid3ActionPerformed(evt);
            }
        });

        bthitung.setBackground(new java.awt.Color(0, 88, 122));
        bthitung.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        bthitung.setForeground(new java.awt.Color(255, 255, 255));
        bthitung.setText("Hitung");
        bthitung.setBorder(null);
        bthitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthitungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(tpengeluaranbulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(tkeuntunganbulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(bthitung, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btInputBulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btedit3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btbatal3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(bthapus3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel26)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(tid3)
                                .addGap(51, 51, 51))
                            .addComponent(tpendapatanbulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(tid3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel20))
                    .addComponent(tpendapatanbulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel21))
                    .addComponent(tpengeluaranbulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel22))
                    .addComponent(tkeuntunganbulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(bthitung, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(btInputBulanan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btedit3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btbatal3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bthapus3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 360, 420));

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
        jPanel5.add(btrefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 30, -1));

        tabelpendapatan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelpendapatan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tabelpendapatan);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 360, 190));

        tabelpengeluaran.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tabelpengeluaran.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(tabelpengeluaran);

        jPanel5.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(429, 310, 360, 190));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Pengeluaran Per Bulan");
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, -1, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Pendapatan Per Bulan");
        jPanel5.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, -1, -1));

        lbLogin.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbLogin.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lbLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 490, 70, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID User Login:");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 490, 80, 20));

        jTabbedPane1.addTab("Rekapitulasi Bulanan", jPanel5);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 98, 1210, 550));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Rekapitulasi");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, 50));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow.png"))); // NOI18N
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 40, 32));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 39, 1250, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tampilharianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tampilharianActionPerformed
        try {
            java.util.Date utilStartDate = tanggalharian.getDate();
            java.sql.Date tgal = new java.sql.Date(utilStartDate.getTime());
            if ( tgal == null ) {
            } else {
                loadDataHarian();
                tampil_harian();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tampilharianActionPerformed

    private void btInputHarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInputHarianActionPerformed
        if(tidtenant.getText().equals("") ||tnama.getText().equals("") || tpend1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.ERROR_MESSAGE);
        }else{
            String id = tidtenant.getText();
            String nama = tnama.getText();
            String pend = tpend1.getText();
            String user = lbLogin2.getText();
            try {
                String sql = "INSERT INTO rekapitulasi_harian VALUES (?, ?, ?, ?, ?, ?)";

                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.setString(1, null);
                    p.setString(2, user);
                    p.setString(3, dateDay);
                    p.setString(4, id);
                    p.setString(5, nama);
                    p.setString(6, pend);
                   
                    p.executeUpdate();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Terjadi Error");
            } finally {
                tampil_harian();
                kosong1();
                aktif1();
                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btInputHarianActionPerformed

    private void btrefresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrefresh2ActionPerformed
        tanggalharian.setDate(null);
    }//GEN-LAST:event_btrefresh2ActionPerformed

    private void tampilmingguanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tampilmingguanActionPerformed
        java.util.Date utilStartDate = tglmulai.getDate(); 
        java.util.Date utilEndDate = tglakhir.getDate();
        if (utilStartDate == null || utilEndDate == null){
               JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.ERROR_MESSAGE);
        }
        else {
        tampil_mingguan();
        loadDataMingguan1();
        loadDataMingguan2();
        }
    }//GEN-LAST:event_tampilmingguanActionPerformed

    private void btInputMingguanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInputMingguanActionPerformed
    if(tpendkotor.getText().equals("") || tpendbersih.getText().equals("") || tpendkedai.getText().equals("") || 
    tpot.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.ERROR_MESSAGE);
    } else {
            btHitung.setEnabled(true);
            String id = tidtenant2.getText();
            String nama = tnama2.getText();
            String pendkotor = tpendkotor.getText();
            String pot = tpot.getText();
            String profit = tpendkedai.getText();
            String pendbersih = tpendbersih.getText();
            String user = lbLogin1.getText();
            try {
                String sql = "INSERT INTO rekapitulasi_mingguan VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.setString(1, null);
                    p.setString(2, user);
                    p.setString(3, dateWeek1);
                    p.setString(4, dateWeek2);
                    p.setString(5, id);
                    p.setString(6, nama);
                    p.setString(7, pendkotor);
                    p.setString(8, pot);
                    p.setString(9, profit);
                    p.setString(10, pendbersih);
                    
                    p.executeUpdate();
                }          
                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            } finally {
                tampil_mingguan();
                kosong2();
                aktif2();
            }
        }
    }//GEN-LAST:event_btInputMingguanActionPerformed

    private void tampilbulananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tampilbulananActionPerformed
        java.util.Date tgl = tanggalbulanan.getDate();
        if ( tgl == null ) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.ERROR_MESSAGE);
        }else {
            tampil_bulanan();
            try {
                loadDataPendapatanBulanan();
            } catch (SQLException ex) {
                Logger.getLogger(RekapPendapatan.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                loadDataPengeluaranBulanan();
            } catch (SQLException ex) {
                Logger.getLogger(RekapPendapatan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tampilbulananActionPerformed

    private void btInputBulananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInputBulananActionPerformed
        if ( tpendapatanbulanan.getText().equals("") || tpengeluaranbulanan.getText().equals("") || tkeuntunganbulanan.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi Data!","Kedai Avicenna", JOptionPane.ERROR_MESSAGE);
        }else{
            String pendapatan = tpendapatanbulanan.getText();
            String pengeluaran = tpengeluaranbulanan.getText();
            String keuntungan = tkeuntunganbulanan.getText();
            String user = lbLogin.getText();
            try {
                String sql = "INSERT INTO rekapitulasi_bulanan VALUES (?, ?, ?, ?, ?, ?)";

                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.setString(1, null);
                    p.setString(2, user);
                    p.setString(3, dateMonth);
                    p.setString(4, pendapatan);
                    p.setString(5, pengeluaran);
                    p.setString(6, keuntungan);
                                      
                    p.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan!", "Kedai Avicenna", JOptionPane.WARNING_MESSAGE);
            } finally {
                tampil_bulanan();
                kosong3();
            }
        }
    }//GEN-LAST:event_btInputBulananActionPerformed

    private void btrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrefreshActionPerformed
        tanggalbulanan.setDate(null);
    }//GEN-LAST:event_btrefreshActionPerformed

    private void btedit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btedit1ActionPerformed
        if(tidtenant.getText().equals("") || tid.getText().equals("") || tnama.getText().equals("") || tidtenant.getText().equals("")|| tanggalharian.getDate().equals("") || tpend1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
        }else{
            int i = tabelharian.getSelectedRow();
            if (i == -1) {
                return;
            }
            try {
                String sql = "UPDATE rekapitulasi_harian SET tanggal =  '" + dateDay + "', user_id = '"+ lbLogin2.getText() + "',namatenant = '"+ tnama.getText() + "', id_tenant='"+ tidtenant.getText() + "', pendapatankotor='"+ tpend1.getText() +"' WHERE  id_rekapharian ='" + tid.getText() + "'";
                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah", "Kedai Avicenna", JOptionPane.ERROR_MESSAGE);
            } finally {
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
                kosong1();
                aktif1();
                tampil_harian();
            }
        }
    }//GEN-LAST:event_btedit1ActionPerformed

    private void btbatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatal1ActionPerformed
        kosong1();
        aktif1();
        btedit1.setEnabled(false);
    }//GEN-LAST:event_btbatal1ActionPerformed

    private void bthapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapus1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Ingin Menghapus Data?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if(confirm==JOptionPane.YES_OPTION) {

            try {
                String sql ="DELETE FROM rekapitulasi_harian WHERE id_rekapharian='"+tid.getText()+"'";

                java.sql.PreparedStatement ps=conn.prepareStatement(sql);
                ps.execute();
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            aktif1();
            tampil_harian();
        }
    }//GEN-LAST:event_bthapus1ActionPerformed

    private void tabelharianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelharianMouseClicked
        btInputHarian.setEnabled(false);
        btedit1.setEnabled(true);
        bthapus1.setEnabled(true);
        try{
            int bar = tabelharian.getSelectedRow();
            String a = tbl3.getValueAt(bar, 0).toString();
            String b = tbl3.getValueAt(bar, 1).toString();
                 try{
                     SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");
                     java.util.Date dt = frmt.parse(b);
                     tanggalharian.setDate(dt);
                 } catch(ParseException ex) {
                 }
            String c = tbl3.getValueAt(bar, 2).toString();
            String d = tbl3.getValueAt(bar, 3).toString();
            String e = tbl3.getValueAt(bar, 4).toString();  
               
     tid.setText(a);
     tidtenant.setText(c);
     tnama.setText(d);
     tpend1.setText(e);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tabelharianMouseClicked

    private void btedit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btedit2ActionPerformed
        if(tid2.getText().equals("") ||  tnama2.getText().equals("") || tidtenant2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
        }else{
            int i = tabelmingguan.getSelectedRow();
            if (i == -1) {
                return;
            }
            try {
                String sql = "UPDATE rekapitulasi_mingguan SET tanggalmulai =  '" + dateWeek1 + "', tanggalakhir =  '" + dateWeek2 + "', id_tenant = '"+ tidtenant2.getText() +
                              "', namatenant='"+ tnama2.getText() + "', user_id='"+ lbLogin1.getText() + "', pendapatankotor='"+ tpendkotor.getText() +"', potongan='"+ tpot.getText() +
                              "', pendapatankedai='"+ tpendkedai.getText() +"' , pendapatanbersih='"+ tpendbersih.getText() +"' "
                              + "WHERE  id_rekapmingguan ='" + tid2.getText() + "'";
                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.executeUpdate();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } finally {
                kosong2();
                aktif2();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
                tampil_mingguan();
            }
        }
    }//GEN-LAST:event_btedit2ActionPerformed

    private void btbatal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatal2ActionPerformed
        kosong2();
        aktif2();
        btedit2.setEnabled(false);
    }//GEN-LAST:event_btbatal2ActionPerformed

    private void btHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapus1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Ingin Menghapus Data?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if(confirm==JOptionPane.YES_OPTION) {

            try {
                String sql ="DELETE FROM rekapitulasi_mingguan WHERE id_rekapmingguan='"+tid2.getText()+"'";

                java.sql.PreparedStatement ps=conn.prepareStatement(sql);
                ps.execute();
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            aktif2();
            tampil_mingguan();
        }
    }//GEN-LAST:event_btHapus1ActionPerformed

    private void btedit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btedit3ActionPerformed
        if(tid3.getText().equals("") || tpendapatanbulanan.getText().equals("") || tpengeluaranbulanan.getText().equals("") || tkeuntunganbulanan.getText().equals("")|| tanggalbulanan.getDate().equals("")) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
        }else{
            int i = tabelbulanan.getSelectedRow();
            if (i == -1) {
                return;
            }
            try {
                String sql = "UPDATE rekapitulasi_bulanan SET user_id = '"+ lbLogin.getText()+ "', tanggalrekap =  '" + dateMonth + "', totalpendapatan = '"+ tpendapatanbulanan.getText() + "', totalpengeluaran='"+ tpengeluaranbulanan.getText() + "', keuntungan='"+ tkeuntunganbulanan.getText() +"' WHERE  id_rekapbulanan ='" + tid3.getText() + "'";
                try (PreparedStatement p = conn.prepareStatement(sql)) {
                    p.executeUpdate();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());;
            } finally {
                kosong3();
                aktif3();
                tampil_bulanan();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btedit3ActionPerformed

    private void tabelmingguanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelmingguanMouseClicked
        btInputMingguan.setEnabled(false);
        btedit2.setEnabled(true);
        btHapus1.setEnabled(true);
        try{
        int bar = tabelmingguan.getSelectedRow();
        String a = tbl2.getValueAt(bar, 0).toString();
        String b = tbl2.getValueAt(bar, 1).toString();
                 try{
                     SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");
                     java.util.Date dt = frmt.parse(b);
                     tglmulai.setDate(dt);
                 } catch(ParseException ex) {
                 }
         String c = tbl2.getValueAt(bar, 2).toString();
         try{
                     SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");
                     java.util.Date dt = frmt.parse(c);
                     tglakhir.setDate(dt);
                 } catch(ParseException ex) {
                 }
         String d = tbl2.getValueAt(bar, 3).toString();
         String e = tbl2.getValueAt(bar, 4).toString();  
         String f = tbl2.getValueAt(bar, 5).toString();
         String g = tbl2.getValueAt(bar, 6).toString();
         String h = tbl2.getValueAt(bar, 7).toString();
         String i = tbl2.getValueAt(bar, 8).toString();
               
     tid2.setText(a);
     tidtenant2.setText(d);
     tnama2.setText(e);
     tpendkotor.setText(f);
     tpot.setText(g);
     tpendkedai.setText(h);
     tpendbersih.setText(i);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tabelmingguanMouseClicked

    private void tabelbulananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelbulananMouseClicked
        btInputBulanan.setEnabled(false);
        btedit3.setEnabled(true);
        bthapus3.setEnabled(true);
        try{
        int bar = tabelbulanan.getSelectedRow();
        String a = tbl1.getValueAt(bar, 0).toString();
        String b = tbl1.getValueAt(bar, 1).toString();
                 try{
                     SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");
                     java.util.Date dt = frmt.parse(b);
                     tanggalbulanan.setDate(dt);
                 } catch(ParseException e) {
                     JOptionPane.showMessageDialog(this, e.getMessage());
                 }
         String c = tbl1.getValueAt(bar, 2).toString();
         String d = tbl1.getValueAt(bar, 3).toString();
         String e = tbl1.getValueAt(bar, 4).toString();  
                     
     tid3.setText(a);
     tpendapatanbulanan.setText(c);
     tpengeluaranbulanan.setText(d);
     tkeuntunganbulanan.setText(e);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tabelbulananMouseClicked

    private void btbatal3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatal3ActionPerformed
        kosong3();
        aktif3();
        btedit3.setEnabled(false);
    }//GEN-LAST:event_btbatal3ActionPerformed

    private void btrefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrefresh1ActionPerformed
        tglmulai.setDate(null);
        tglakhir.setDate(null);
    }//GEN-LAST:event_btrefresh1ActionPerformed

    private void bthapus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapus3ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Ingin Menghapus Data?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if(confirm==JOptionPane.YES_OPTION) {

            try {
                String sql ="DELETE FROM rekapitulasi_bulanan WHERE id_rekapbulanan='"+tid3.getText()+"'";

                java.sql.PreparedStatement ps=conn.prepareStatement(sql);
                ps.execute();
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            aktif3();
        }
    }//GEN-LAST:event_bthapus3ActionPerformed

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

    private void tableInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableInputMouseClicked
        btInputHarian.setEnabled(true);
        btbatal1.setEnabled(true);
        tid.setText(null);
        int i = tableInput.getSelectedRow();
        if (i == -1) {
            return;
        }
            int bar = tableInput.getSelectedRow();
            String a = tbl4.getValueAt(bar, 0).toString();
                 try{
                     SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");
                     java.util.Date dt = frmt.parse(a);
                     tanggalharian.setDate(dt);
                 } catch(ParseException ex) {
                 }
            String b = tbl4.getValueAt(bar, 1).toString();
            String c = tbl4.getValueAt(bar, 2).toString();
            String d = tbl4.getValueAt(bar, 3).toString();  
               
     tidtenant.setText(b);
     tnama.setText(c);
     tpend1.setText(d);
    }//GEN-LAST:event_tableInputMouseClicked

    private void tabelInputMingguan1tabelmingguanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelInputMingguan1tabelmingguanMouseClicked
        tid2.setText(null);
        int i = tabelInputMingguan1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String idt = (String) tbl5.getValueAt(i,1);
        tidtenant2.setText(idt);
        
        String nama = (String) tbl5.getValueAt(i,2);
        tnama2.setText(nama);
       
        String pend = (String) tbl5.getValueAt(i, 3);
        tpendkotor.setText(pend); 
    }//GEN-LAST:event_tabelInputMingguan1tabelmingguanMouseClicked

    private void tabelInputMingguan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelInputMingguan2MouseClicked
        tid2.setText(null);
        int i = tabelInputMingguan2.getSelectedRow();
        Reserve();
        if (i == -1) {
            return;
        }
        String biaya = (String) tbl6.getValueAt(i,2);
        tpendkedai.setText(biaya);
        tidtenant.setText("-");
        tnama2.setText("-");
        tpendkotor.setText("0");
        tpendbersih.setText("0");
        tpot.setText("0");
    }//GEN-LAST:event_tabelInputMingguan2MouseClicked

    private void btHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHitungActionPerformed
        if ( tidtenant2.getText().equals("") || tnama2.getText().equals("") || tpendkotor.getText().equals("") ) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!","Kedai Avicenna", JOptionPane.ERROR_MESSAGE);
        } else {
            double pendkotor, profit, pot, pendbersih;

            pendkotor = Double.parseDouble(tpendkotor.getText());
            pot = Double.parseDouble(tpot.getText());
            profit = (pendkotor * pot);
            pendbersih = pendkotor - profit;

            tpendkedai.setText(Double.toString(profit));
            tpendbersih.setText(Double.toString(pendbersih));
        }
    }//GEN-LAST:event_btHitungActionPerformed

    private void tglmulaiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglmulaiPropertyChange
        if (tglmulai.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            dateWeek1 = format.format(tglmulai.getDate());
        }
    }//GEN-LAST:event_tglmulaiPropertyChange

    private void tglakhirPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglakhirPropertyChange
        if (tglakhir.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            dateWeek2 = format.format(tglakhir.getDate());
        }
    }//GEN-LAST:event_tglakhirPropertyChange

    private void bthitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthitungActionPerformed
        if ( tpendapatanbulanan.getText().equals("") || tpengeluaranbulanan.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi Data!","Kedai Avicenna", JOptionPane.ERROR_MESSAGE);
        } else {
            int pendapatan, pengeluaran, totaal;

            pendapatan = Integer.parseInt(tpendapatanbulanan.getText());
            pengeluaran = Integer.parseInt(tpengeluaranbulanan.getText());
            totaal = pendapatan - pengeluaran;

            tkeuntunganbulanan.setText(Integer.toString(totaal));
        }
    }//GEN-LAST:event_bthitungActionPerformed

    private void btresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btresetActionPerformed
        String isi = JOptionPane.showInputDialog("Masukkan Potongan :");
        tpot.setText(isi);
        JOptionPane.showMessageDialog(null,"Nilai Potongan :"+isi, "Kedai Avicenna", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btresetActionPerformed

    private void tid3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tid3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tid3ActionPerformed

    private void tanggalharianPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggalharianPropertyChange
        if (tanggalharian.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            dateDay = format.format(tanggalharian.getDate());
                }
    }//GEN-LAST:event_tanggalharianPropertyChange

    private void tanggalbulananPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggalbulananPropertyChange
        if (tanggalbulanan.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            dateMonth = format.format(tanggalbulanan.getDate());
        }
    }//GEN-LAST:event_tanggalbulananPropertyChange


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
            java.util.logging.Logger.getLogger(RekapPendapatan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RekapPendapatan().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton btHapus1;
    private javax.swing.JButton btHitung;
    private javax.swing.JButton btInputBulanan;
    private javax.swing.JButton btInputHarian;
    private javax.swing.JButton btInputMingguan;
    private javax.swing.JButton btbatal1;
    private javax.swing.JButton btbatal2;
    private javax.swing.JButton btbatal3;
    private javax.swing.JButton btedit1;
    private javax.swing.JButton btedit2;
    private javax.swing.JButton btedit3;
    private javax.swing.JButton bthapus1;
    private javax.swing.JButton bthapus3;
    private javax.swing.JButton bthitung;
    private javax.swing.JButton btrefresh;
    private javax.swing.JButton btrefresh1;
    private javax.swing.JButton btrefresh2;
    private javax.swing.JButton btreset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbLogin;
    private javax.swing.JLabel lbLogin1;
    private javax.swing.JLabel lbLogin2;
    private javax.swing.JLabel lbMinimize;
    private javax.swing.JTable tabelInputMingguan1;
    private javax.swing.JTable tabelInputMingguan2;
    private javax.swing.JTable tabelbulanan;
    private javax.swing.JTable tabelharian;
    private javax.swing.JTable tabelmingguan;
    private javax.swing.JTable tabelpendapatan;
    private javax.swing.JTable tabelpengeluaran;
    private javax.swing.JTable tableInput;
    private javax.swing.JButton tampilbulanan;
    private javax.swing.JButton tampilharian;
    private javax.swing.JButton tampilmingguan;
    private com.toedter.calendar.JDateChooser tanggalbulanan;
    private com.toedter.calendar.JDateChooser tanggalharian;
    private com.toedter.calendar.JDateChooser tglakhir;
    private com.toedter.calendar.JDateChooser tglmulai;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tid2;
    private javax.swing.JTextField tid3;
    private javax.swing.JTextField tidtenant;
    private javax.swing.JTextField tidtenant2;
    private javax.swing.JTextField tkeuntunganbulanan;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tnama2;
    private javax.swing.JPanel topbar;
    private javax.swing.JTextField tpend1;
    private javax.swing.JTextField tpendapatanbulanan;
    private javax.swing.JTextField tpendbersih;
    private javax.swing.JTextField tpendkedai;
    private javax.swing.JTextField tpendkotor;
    private javax.swing.JTextField tpengeluaranbulanan;
    private javax.swing.JTextField tpot;
    // End of variables declaration//GEN-END:variables
}
