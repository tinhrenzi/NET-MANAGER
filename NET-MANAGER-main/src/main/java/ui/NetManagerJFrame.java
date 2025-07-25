/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;


import controller.GiaoDienChinhController;
import javax.swing.JOptionPane;
import util.XAuth;

/**
 *
 * @author NITRO 5
 */
public class NetManagerJFrame extends javax.swing.JFrame implements GiaoDienChinhController{

    /**
     * Creates new form NetManagerJFrem
     * w14
     * h410
     */
    int w = 155;
    int h = 410;
    public NetManagerJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        this.init();
    }
    void btnopmenu(){
    new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < w; i++) {
                menuHeThong.setSize(i, h);
                try {
                    Thread.sleep(2);
                } catch (Exception e) {
                }
            }
        }
    }).start();
    }
    void btnclmenu(){
    new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = w; i > 0; i--) {
                menuHeThong.setSize(i, h);
                try {
                    Thread.sleep(2);
                } catch (Exception e) {
                }           
            }
        }
    }).start();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOrder = new javax.swing.JButton();
        btnMoMay = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        menuHeThong = new javax.swing.JPanel();
        btnQLNV = new javax.swing.JButton();
        btnQLTD = new javax.swing.JButton();
        btnQLMT = new javax.swing.JButton();
        btnQLTK = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lbclmenu = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnTrandPass = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOrder.setBackground(new java.awt.Color(204, 204, 204));
        btnOrder.setIcon(new javax.swing.ImageIcon("H:\\XUONG\\SUM2025\\NET-MANAGER\\NET-MANAGER-main\\src\\main\\java\\img\\8665027_basket_shopping_icon.png")); // NOI18N
        btnOrder.setText("Order");
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });
        getContentPane().add(btnOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 160, -1));

        btnMoMay.setBackground(new java.awt.Color(204, 204, 204));
        btnMoMay.setIcon(new javax.swing.ImageIcon("H:\\XUONG\\SUM2025\\NET-MANAGER\\NET-MANAGER-main\\src\\main\\java\\img\\185023_imac_computer_icon.png")); // NOI18N
        btnMoMay.setText("Mở máy");
        btnMoMay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoMayActionPerformed(evt);
            }
        });
        getContentPane().add(btnMoMay, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 160, -1));

        btnLogOut.setBackground(new java.awt.Color(204, 204, 204));
        btnLogOut.setForeground(new java.awt.Color(255, 0, 0));
        btnLogOut.setText("Đăng xuất");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 130, 40));

        btnThanhToan.setBackground(new java.awt.Color(204, 204, 204));
        btnThanhToan.setIcon(new javax.swing.ImageIcon("H:\\XUONG\\SUM2025\\NET-MANAGER\\NET-MANAGER-main\\src\\main\\java\\img\\211635_card_icon.png")); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        getContentPane().add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 163, -1));

        menuHeThong.setBackground(new java.awt.Color(66, 116, 176));
        menuHeThong.setPreferredSize(new java.awt.Dimension(140, 410));

        btnQLNV.setText("Quản lý NV");
        btnQLNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLNVActionPerformed(evt);
            }
        });

        btnQLTD.setText("Quản lý thực đơn");
        btnQLTD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLTDActionPerformed(evt);
            }
        });

        btnQLMT.setText("Quản lý máy tính");
        btnQLMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLMTActionPerformed(evt);
            }
        });

        btnQLTK.setText("Quản lý thống kê");
        btnQLTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLTKActionPerformed(evt);
            }
        });

        lbclmenu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbclmenu.setText("X");
        lbclmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbclmenuActionPerformed(evt);
            }
        });

        btnTrandPass.setText("Đổi mật khẩu");
        btnTrandPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrandPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuHeThongLayout = new javax.swing.GroupLayout(menuHeThong);
        menuHeThong.setLayout(menuHeThongLayout);
        menuHeThongLayout.setHorizontalGroup(
            menuHeThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuHeThongLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbclmenu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(menuHeThongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuHeThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQLTD)
                    .addComponent(btnQLMT)
                    .addComponent(btnQLTK)
                    .addComponent(btnTrandPass))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuHeThongLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnQLMT, btnQLNV, btnQLTD, btnQLTK, btnTrandPass});

        menuHeThongLayout.setVerticalGroup(
            menuHeThongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuHeThongLayout.createSequentialGroup()
                .addComponent(lbclmenu, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(35, 35, 35)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQLNV)
                .addGap(18, 18, 18)
                .addComponent(btnQLTD)
                .addGap(18, 18, 18)
                .addComponent(btnQLMT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQLTK, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrandPass)
                .addGap(32, 32, 32))
        );

        menuHeThongLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnQLMT, btnQLNV, btnQLTD, btnQLTK});

        getContentPane().add(menuHeThong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 410));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1010, 10));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setIcon(new javax.swing.ImageIcon("H:\\XUONG\\SUM2025\\NET-MANAGER\\NET-MANAGER-main\\src\\main\\java\\img\\menu.png")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon("H:\\XUONG\\SUM2025\\NET-MANAGER\\NET-MANAGER-main\\src\\main\\java\\img\\ChatGPT Image 22_27_18 16 thg 7, 2025.png")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoMayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoMayActionPerformed
        // TODO add your handling code here:
        this.showMoMay(this);
    }//GEN-LAST:event_btnMoMayActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        //this.dispose();
      //new DangNhapJDialog(null, true).setVisible(true);
      dangXuat();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        // TODO add your handling code here:
        this.showDichVu(this);
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnQLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLNVActionPerformed
        // TODO add your handling code here:
        this.showNhanVien(this);
    }//GEN-LAST:event_btnQLNVActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        this.showThanhtoan(this);
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnQLMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLMTActionPerformed
        // TODO add your handling code here:
        this.showQLmay(this);
    }//GEN-LAST:event_btnQLMTActionPerformed

    private void btnQLTDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLTDActionPerformed
        // TODO add your handling code here:
        this.showQLOrder(this);
    }//GEN-LAST:event_btnQLTDActionPerformed

    private void btnQLTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLTKActionPerformed
        // TODO add your handling code here:
        this.showQLThongke(this);
    }//GEN-LAST:event_btnQLTKActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void lbclmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbclmenuActionPerformed
        // TODO add your handling code here:
        btnclmenu();
    }//GEN-LAST:event_lbclmenuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        btnopmenu();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTrandPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrandPassActionPerformed
        new DoiMatKhauJDialog(null, true).setVisible(true);
    }//GEN-LAST:event_btnTrandPassActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NetManagerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NetManagerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NetManagerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NetManagerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NetManagerJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnMoMay;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnQLMT;
    private javax.swing.JButton btnQLNV;
    private javax.swing.JButton btnQLTD;
    private javax.swing.JButton btnQLTK;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTrandPass;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton lbclmenu;
    private javax.swing.JPanel menuHeThong;
    // End of variables declaration//GEN-END:variables

    @Override
    public void init() {
        this.showWelcome(this);
        this.showLogin(this);
        if(XAuth.user != null){
        setRole();
        }else{
            dispose();
        }
    }
    public void setRole() {
        int role = XAuth.user.getVaiTro();
    if (role == 2) { // Nhân viên
        btnQLNV.setEnabled(false);
        btnQLMT.setEnabled(false);
        btnQLTD.setEnabled(false);
        btnQLTK.setEnabled(false);
    }
}
    private void dangXuat() {
    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn đăng xuất?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        XAuth.clear();
        this.setVisible(false);
        DangNhapJDialog dangNhap = new DangNhapJDialog(this, true);
        dangNhap.setVisible(true);
        if(XAuth.isLogin()){
        this.setVisible(true);   
        setRole();
        }else{
           dispose();
        }
    }
 }
}
