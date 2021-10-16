/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author nurul
 */
public class Utama extends javax.swing.JFrame {
    public Utama() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbMinimize = new javax.swing.JLabel();
        lbClose = new javax.swing.JLabel();
        lbMenubar = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btTenant = new javax.swing.JButton();
        btMenu = new javax.swing.JButton();
        btRekap = new javax.swing.JButton();
        btLaporan = new javax.swing.JButton();
        btPengeluaran = new javax.swing.JButton();
        btPesan = new javax.swing.JButton();
        btReserve = new javax.swing.JButton();
        btHome = new javax.swing.JButton();
        btInfo = new javax.swing.JButton();
        btLogout = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cover = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(932, 670));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-minimize-window-20.png"))); // NOI18N
        lbMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbMinimizeMousePressed(evt);
            }
        });
        getContentPane().add(lbMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(875, 10, 20, 20));

        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-macos-close-20.png"))); // NOI18N
        lbClose.setText("jLabel1");
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbCloseMousePressed(evt);
            }
        });
        getContentPane().add(lbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 20, 20));

        lbMenubar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menubar.png"))); // NOI18N
        getContentPane().add(lbMenubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 932, 40));

        jPanel4.setBackground(new java.awt.Color(26, 26, 46));
        jPanel4.setPreferredSize(new java.awt.Dimension(200, 518));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btTenant.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btTenant.setForeground(new java.awt.Color(255, 255, 255));
        btTenant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-shop-24.png"))); // NOI18N
        btTenant.setText("Tenant");
        btTenant.setBorder(null);
        btTenant.setContentAreaFilled(false);
        btTenant.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btTenant.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btTenant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTenantActionPerformed(evt);
            }
        });
        jPanel4.add(btTenant, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 140, 25));

        btMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btMenu.setForeground(new java.awt.Color(255, 255, 255));
        btMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-food-24.png"))); // NOI18N
        btMenu.setText("Menu");
        btMenu.setBorder(null);
        btMenu.setContentAreaFilled(false);
        btMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btMenu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMenuActionPerformed(evt);
            }
        });
        jPanel4.add(btMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 140, 25));

        btRekap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btRekap.setForeground(new java.awt.Color(255, 255, 255));
        btRekap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-calculator-24.png"))); // NOI18N
        btRekap.setText("Rekapitulasi");
        btRekap.setBorder(null);
        btRekap.setContentAreaFilled(false);
        btRekap.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btRekap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btRekap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRekapActionPerformed(evt);
            }
        });
        jPanel4.add(btRekap, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 140, 25));

        btLaporan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-business-report-24.png"))); // NOI18N
        btLaporan.setText("Laporan & Grafik");
        btLaporan.setBorder(null);
        btLaporan.setContentAreaFilled(false);
        btLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btLaporan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLaporanActionPerformed(evt);
            }
        });
        jPanel4.add(btLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 140, 25));

        btPengeluaran.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btPengeluaran.setForeground(new java.awt.Color(255, 255, 255));
        btPengeluaran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-money-transfer-24.png"))); // NOI18N
        btPengeluaran.setText("Pengeluaran");
        btPengeluaran.setBorder(null);
        btPengeluaran.setContentAreaFilled(false);
        btPengeluaran.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btPengeluaran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btPengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPengeluaranActionPerformed(evt);
            }
        });
        jPanel4.add(btPengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 140, 25));

        btPesan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btPesan.setForeground(new java.awt.Color(255, 255, 255));
        btPesan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-create-order-24.png"))); // NOI18N
        btPesan.setText("Pemesanan");
        btPesan.setBorder(null);
        btPesan.setContentAreaFilled(false);
        btPesan.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btPesan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btPesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesanActionPerformed(evt);
            }
        });
        jPanel4.add(btPesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 140, 25));

        btReserve.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btReserve.setForeground(new java.awt.Color(255, 255, 255));
        btReserve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-reservation-24.png"))); // NOI18N
        btReserve.setText("Reservasi");
        btReserve.setBorder(null);
        btReserve.setContentAreaFilled(false);
        btReserve.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btReserve.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReserveActionPerformed(evt);
            }
        });
        jPanel4.add(btReserve, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 140, 25));

        btHome.setBackground(new java.awt.Color(0, 88, 122));
        btHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-home-64.png"))); // NOI18N
        btHome.setBorder(null);
        btHome.setContentAreaFilled(false);
        btHome.setFocusPainted(false);
        btHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHomeActionPerformed(evt);
            }
        });
        jPanel4.add(btHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, 90));

        btInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btInfo.setForeground(new java.awt.Color(255, 255, 255));
        btInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-user-24.png"))); // NOI18N
        btInfo.setText("Info Pengguna");
        btInfo.setBorder(null);
        btInfo.setContentAreaFilled(false);
        btInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btInfo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInfoActionPerformed(evt);
            }
        });
        jPanel4.add(btInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 140, -1));

        btLogout.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btLogout.setForeground(new java.awt.Color(255, 255, 255));
        btLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-logout-rounded-left-24.png"))); // NOI18N
        btLogout.setText("Keluar");
        btLogout.setBorder(null);
        btLogout.setContentAreaFilled(false);
        btLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btLogout.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoutActionPerformed(evt);
            }
        });
        jPanel4.add(btLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 140, 25));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 180, 600));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/homen.png"))); // NOI18N
        jPanel2.add(cover, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 720, 610));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 710, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btTenantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTenantActionPerformed
        new Tenant().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btTenantActionPerformed

    private void btMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMenuActionPerformed
        new Menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btMenuActionPerformed

    private void btRekapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRekapActionPerformed
        new RekapPendapatan().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btRekapActionPerformed

    private void btPengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPengeluaranActionPerformed
        new Pengeluaran().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btPengeluaranActionPerformed

    private void btPesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesanActionPerformed
        new TransaksiPemesanan().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btPesanActionPerformed

    private void btReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReserveActionPerformed
        new ReservasiTempat().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btReserveActionPerformed

    private void btHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHomeActionPerformed
        new Utama().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btHomeActionPerformed

    private void btLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Anda ingin keluar?", "Konfirmasi Keluar Pengguna", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new LoginAdmin().setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btLogoutActionPerformed

    private void btLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLaporanActionPerformed
       new LaporanKedai().setVisible(true);
    }//GEN-LAST:event_btLaporanActionPerformed

    private void btInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInfoActionPerformed
        new InfoUser().setVisible(true);
    }//GEN-LAST:event_btInfoActionPerformed

    private void lbCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMousePressed
        int confirm = JOptionPane.showConfirmDialog(this, "Anda ingin menutup aplikasi?", "Konfirmasi Keluar Aplikasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_lbCloseMousePressed

    private void lbMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMinimizeMousePressed
        setState(ICONIFIED);
    }//GEN-LAST:event_lbMinimizeMousePressed

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
            java.util.logging.Logger.getLogger(Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Utama().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btHome;
    private javax.swing.JButton btInfo;
    private javax.swing.JButton btLaporan;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btMenu;
    private javax.swing.JButton btPengeluaran;
    private javax.swing.JButton btPesan;
    private javax.swing.JButton btRekap;
    private javax.swing.JButton btReserve;
    private javax.swing.JButton btTenant;
    private javax.swing.JLabel cover;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbMenubar;
    private javax.swing.JLabel lbMinimize;
    // End of variables declaration//GEN-END:variables
}