/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui.manager;
import ui.manager.DanhSachDoUongJDialog;
import controller.QuanLyNhanVienController;
import controller.QuanLyThucDonController;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Admin
 */
public class QuanLyThucDonJDialog extends javax.swing.JDialog implements QuanLyThucDonController{

    /**
     * Creates new form QuanLyThucDonJDialog
     */
    public QuanLyThucDonJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
          this.init(); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnThemDA = new javax.swing.JButton();
        btnThemDU = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDA = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDU = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Quản Lý Thực Đơn");

        btnThemDA.setText("Thêm Đồ Uống");
        btnThemDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDAActionPerformed(evt);
            }
        });

        btnThemDU.setText("Thêm Đồ Ăn");
        btnThemDU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDUActionPerformed(evt);
            }
        });

        tblDA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã đồ ăn", "Tên đồ ăn", "Đơn giá", "Số lượng"
            }
        ));
        jScrollPane2.setViewportView(tblDA);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đồ ăn", jPanel1);

        tblDU.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã đồ uống", "Tên đồ uống", "Đơn giá", "Số lượng"
            }
        ));
        jScrollPane3.setViewportView(tblDU);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đồ Uống", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemDU)
                        .addGap(62, 62, 62)
                        .addComponent(btnThemDA)
                        .addGap(16, 16, 16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemDA)
                    .addComponent(btnThemDU))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemDUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDUActionPerformed
            DanhSachDoAnJDialog dialog = new DanhSachDoAnJDialog((javax.swing.JFrame) this.getOwner(), true);
            dialog.setVisible(true); 
            loadDataDoAn();
    }//GEN-LAST:event_btnThemDUActionPerformed

    private void btnThemDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDAActionPerformed
        DanhSachDoUongJDialog dialog = new DanhSachDoUongJDialog((javax.swing.JFrame) this.getOwner(), true);
        dialog.setVisible(true);    
           loadDataDoUong();
    }//GEN-LAST:event_btnThemDAActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyThucDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyThucDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyThucDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyThucDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuanLyThucDonJDialog dialog = new QuanLyThucDonJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThemDA;
    private javax.swing.JButton btnThemDU;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDA;
    private javax.swing.JTable tblDU;
    // End of variables declaration//GEN-END:variables
private void loadDataDoAn() {
    DefaultTableModel model = (DefaultTableModel) tblDA.getModel();
    model.setRowCount(0);

    dao.DoAnDAO dao = new daoImpl.DoAnDAOImpl();
    for (entity.DoAn da : dao.findAll()) {
        Object[] row = {
            da.getMaDoAn(),
            da.getTenDoAn(),
            da.getDonGia(),
            da.getSoLuong()
        };
        model.addRow(row);
    }
}

private void loadDataDoUong() {
    DefaultTableModel model = (DefaultTableModel) tblDU.getModel();
    model.setRowCount(0);

    dao.DoUongDAO dao = new daoImpl.DoUongDAOImpl();
    for (entity.DoUong du : dao.findAll()) {
        Object[] row = {
            du.getMaDoUong(),
            du.getTenDoUong(),
            du.getDonGia(),
            du.getSoLuong()
        };
        model.addRow(row);
    }
}
    @Override
    public void init() {
        loadDataDoAn();
        loadDataDoUong();
        }

    @Override
    public void showDialog(JDialog dialog) {
        QuanLyThucDonController.super.showDialog(dialog);
    }

    @Override
    public void showDanhSachDoAn(JFrame jFrame) {
         QuanLyThucDonController.super.showDanhSachDoAn(jFrame);
    }

    @Override
    public void showDanhSachDoUong(JFrame jFrame) {
         QuanLyThucDonController.super.showDanhSachDoUong(jFrame);
    }
    
}
