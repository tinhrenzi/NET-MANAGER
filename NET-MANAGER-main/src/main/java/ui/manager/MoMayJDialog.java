/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui.manager;

import controller.MoMayController;
import dao.MayTinhDAO;
import daoImpl.MayTinhDAOImpl;
import entity.MayTinh;
import entity.Order;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import util.XDialog;

/**
 *
 * @author VINH
 */
public class MoMayJDialog extends javax.swing.JDialog implements MoMayController {

    MayTinhDAO dao = new MayTinhDAOImpl();
    List<MayTinh> list = new ArrayList<>();

    /**
     * Creates new form MoMayJDialog
     *
     * @param parent
     * @param modal
     */
    public MoMayJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

        public MoMayJDialog(Dialog owner, boolean modal, MayTinh mayTinh) {
        super(owner, modal);
        initComponents();             // Tạo UI
        setForm(mayTinh);             // Đổ dữ liệu máy vào form
        startThoiGianThuc();          // Chạy đồng hồ thời gian thực
        if ("Đang hoạt động".equals(mayTinh.getTrangThai())) {
            startDongHo();            // Nếu đang hoạt động thì chạy đồng hồ chơi
        }
        setTitle(Title.getName()+ mayTinh.getTenMay());  // Tiêu đề động
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        txtViTri = new javax.swing.JTextField();
        btnTatMay = new javax.swing.JButton();
        btnMoMay = new javax.swing.JButton();
        btnBaoTri = new javax.swing.JButton();
        rdoHoatDong = new javax.swing.JRadioButton();
        rdoNgungHoatDong = new javax.swing.JRadioButton();
        rdoBaoTri = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMaMay = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenMay = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Title = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblThoiGianThuc = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblThoiGianChoi = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mo May");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtViTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtViTriActionPerformed(evt);
            }
        });

        btnTatMay.setText("Tắt máy");
        btnTatMay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTatMayActionPerformed(evt);
            }
        });

        btnMoMay.setText("Mở máy");
        btnMoMay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoMayActionPerformed(evt);
            }
        });

        btnBaoTri.setText("Bảo trì");
        btnBaoTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaoTriActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoHoatDong);
        rdoHoatDong.setText("Đang hoạt động");

        buttonGroup1.add(rdoNgungHoatDong);
        rdoNgungHoatDong.setText("Ngừng hoạt động");

        buttonGroup1.add(rdoBaoTri);
        rdoBaoTri.setText("Bảo trì");

        jLabel9.setText("Vị trí");

        jLabel10.setText("Thời gian");

        jLabel6.setText("Mã máy");

        jLabel7.setText("Tên máy");

        jLabel8.setText("Trạng thái");

        Title.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Title.setText("Máy");
        Title.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Title.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                TitleAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel2.setText("Thời gian chơi");

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoNgungHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenMay, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaMay, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoBaoTri, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThanhToan)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMoMay, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTatMay, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBaoTri))
                            .addComponent(lblThoiGianThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(126, 126, 126))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(126, 126, 126))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblThoiGianChoi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaMay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThoiGianThuc)
                    .addComponent(lblThoiGianChoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenMay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtViTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdoHoatDong)
                .addGap(7, 7, 7)
                .addComponent(rdoNgungHoatDong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdoBaoTri)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMoMay)
                        .addComponent(btnTatMay)
                        .addComponent(btnBaoTri)
                        .addComponent(btnThanhToan)))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.open();
    }//GEN-LAST:event_formWindowOpened

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_formMouseClicked

    private void btnBaoTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaoTriActionPerformed
        // TODO add your handling code here:
        this.BaoTri();
    }//GEN-LAST:event_btnBaoTriActionPerformed

    private void btnTatMayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatMayActionPerformed
        // TODO add your handling code here:
        this.TatMay();
    }//GEN-LAST:event_btnTatMayActionPerformed

    private void btnMoMayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoMayActionPerformed
        // TODO add your handling code here:
        this.MoMay();
    }//GEN-LAST:event_btnMoMayActionPerformed

    private void txtViTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtViTriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtViTriActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        // Mở giao diện ThanhToanJDialog
        ThanhToanJDialog thanhToanDialog = new ThanhToanJDialog(null, true); // Sử dụng null làm parent
        thanhToanDialog.setVisible(true);
        
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void TitleAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_TitleAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_TitleAncestorAdded

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
            java.util.logging.Logger.getLogger(MoMayJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MoMayJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MoMayJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MoMayJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MoMayJDialog dialog = new MoMayJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Title;
    private javax.swing.JButton btnBaoTri;
    private javax.swing.JButton btnMoMay;
    private javax.swing.JButton btnTatMay;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblThoiGianChoi;
    private javax.swing.JLabel lblThoiGianThuc;
    private javax.swing.JRadioButton rdoBaoTri;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JRadioButton rdoNgungHoatDong;
    private javax.swing.JTextField txtMaMay;
    private javax.swing.JTextField txtTenMay;
    private javax.swing.JTextField txtViTri;
    // End of variables declaration//GEN-END:variables

    @Override
    public void open() {
        this.setLocationRelativeTo(null);
        this.fillToTable();
        this.clear();
        this.startThoiGianThuc();
    }


    @Override
    public void setForm(MayTinh entity) {
        txtMaMay.setText(entity.getMaMayTinh());
        txtTenMay.setText(entity.getTenMay());
        txtViTri.setText(entity.getViTri());
        buttonGroup1.clearSelection();

        String trangThai = entity.getTrangThai();
        switch (trangThai) {
            case "Đang hoạt động" ->
                rdoHoatDong.setSelected(true);
            case "Ngừng hoạt động" ->
                rdoNgungHoatDong.setSelected(true);
            case "Bảo trì" ->
                rdoBaoTri.setSelected(true);
        }
    }

    @Override
    public MayTinh getForm() {
        MayTinh entity = new MayTinh();

        String maMay = txtMaMay.getText().trim(); // trim dùng để xóa khoảng trắng đầu cuối
        if (maMay.isEmpty()) {
            XDialog.alert("Mã máy không được để trống!");
            return null;
        }

        String tenMay = txtTenMay.getText().trim();
        if (tenMay.isEmpty()) {
            XDialog.alert("Tên máy không được để trống!");
            return null;
        }

        String viTri = txtViTri.getText().trim();
        if (viTri.isEmpty()) {
            XDialog.alert("Vị trí không được để trống!");
            return null;
        }

        String trangThai;
        if (rdoHoatDong.isSelected()) {
            trangThai = "Đang hoạt động";
        } else if (rdoNgungHoatDong.isSelected()) {
            trangThai = "Ngừng hoạt động";
        } else if (rdoBaoTri.isSelected()) {
            trangThai = "Bảo trì";
        } else {
            XDialog.alert("Vui lòng chọn trạng thái!");
            return null;
        }

        // Gán các giá trị vào entity
        entity.setMaMayTinh(maMay);
        entity.setTenMay(tenMay);
        entity.setViTri(viTri);
        entity.setTrangThai(trangThai);
        return entity;
    }

    @Override
    public void fillToTable() {
        list = dao.findAll();
        list.forEach(item -> {
            Object[] rowData = {
                item.getMaMayTinh(),
                item.getTenMay(),
                item.getTrangThai(),
                item.getViTri(),
                item.getThoiGian()
            };
        });
    }

    @Override
    public void create() {
        MayTinh entity = this.getForm();
        if (entity == null) {
            return; // Không tạo nếu getForm trả về null
        }
        try {
            dao.create(entity);
            this.fillToTable();
            this.clear();
            XDialog.alert("Thêm máy thành công!");
        } catch (Exception e) {
            XDialog.alert("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void MoMay() {

        MayTinh mt = getForm(); // lấy thông tin máy từ form
        /*
        lấy dữ liệu từ form
         */
        if (mt == null) {
            return;
        }

        mt.setTrangThai("Đang hoạt động"); // cập nhật trạng thái sang "Đang hoạt động"
        try {
            dao.update(mt); // cập nhật vào database
            fillToTable();  // đổ lại bảng
            clear();        // xóa form
            rdoHoatDong.setSelected(true);
            rdoNgungHoatDong.setSelected(false);
            rdoBaoTri.setSelected(false);
            startDongHo();
            XDialog.alert("Mở máy thành công");
        } catch (Exception e) {
            e.printStackTrace();
            XDialog.alert("Lỗi mở máy" + e.getMessage());
        }
    }

    private void TatMay() {
        MayTinh entity = getForm();
        if (entity == null) {
            return;
        }

        // Đặt trạng thái là "Ngừng hoạt động"
        entity.setTrangThai("Ngừng hoạt động");

        try {
            // Cập nhật trạng thái vào DB
            dao.update(entity);

            // Dừng đồng hồ thời gian chơi nếu đang chạy
            if (timerThoiGianChoi != null && timerThoiGianChoi.isRunning()) {
                timerThoiGianChoi.stop();
            }

            clear();
            rdoHoatDong.setSelected(false);
            rdoNgungHoatDong.setSelected(true);
            rdoBaoTri.setSelected(false);

            XDialog.alert("Tắt máy thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            XDialog.alert("Lỗi tắt máy: " + e.getMessage());
        }
    }

    private void BaoTri() {

        MayTinh entity = getForm();
        if (entity == null) {
            return;
        }

        entity.setTrangThai("Bảo trì");

        try {
            dao.update(entity);

            if (timerThoiGianChoi != null && timerThoiGianChoi.isRunning()) {
                timerThoiGianChoi.stop();
            }

            clear();

            rdoHoatDong.setSelected(false);
            rdoNgungHoatDong.setSelected(false);
            rdoBaoTri.setSelected(true);

            XDialog.alert("Đưa vào bảo trì thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            XDialog.alert("Lỗi bảo trì máy: " + e.getMessage());
        }
    }
    // Tạo đồng hồ
    private Timer timerThoiGianThuc;
    private Timer timerThoiGianChoi;
    private long startTimeMillis; // lưu thời gian mở máy tính theo milliseconds
// Chạy thời gian thực

    public void startThoiGianThuc() {
        timerThoiGianThuc = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                lblThoiGianThuc.setText(sdf.format(new Date()));
            }
        });
        timerThoiGianThuc.start(); // luôn chạy khi form khởi động

    }

// Chạy thời gian chơi    
    public void startDongHo() {
        // Dừng timerThoiGianThuc nếu nó đang chạy
        if (timerThoiGianThuc != null && timerThoiGianThuc.isRunning()) {
            timerThoiGianThuc.stop();
        }

        // Lấy thời gian bắt đầu và hiển thị
        startTimeMillis = System.currentTimeMillis(); // thời gian bắt đầu tính bằng mili giây
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        lblThoiGianThuc.setText(sdf.format(new Date(startTimeMillis)));

        // Bắt đầu đồng hồ đếm giờ chơi
        timerThoiGianChoi = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long now = System.currentTimeMillis(); // Lấy thời gian hiện tại
                long playedMillis = now - startTimeMillis;

                long seconds = (playedMillis / 1000) % 60; // Đổi từ mili giây sang giây và lấy từ 0-59 giây/phút
                long minutes = (playedMillis / 1000 / 60) % 60; // Đổi từ mili giây sang phút và lấy từ 0-59 phút/giờ
                long hours = (playedMillis / 1000 / 60 / 60);

                String thoiGianChoi = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                lblThoiGianChoi.setText(thoiGianChoi);
            }
        });

        timerThoiGianChoi.start(); // Khởi động timerThoiGianChoi
    }

// khong dung den
    @Override
    public void update() {
    }

    @Override
    public void edit() {

    }

    @Override
    public void delete() {
    }

    @Override
    public void clear() {
    }

    @Override
    public void setEditable(boolean editable) {
    }

    @Override
    public void close() {

    }
}
