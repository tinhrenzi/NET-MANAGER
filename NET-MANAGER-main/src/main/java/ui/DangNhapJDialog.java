/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui;

import controller.DangNhapController;
import daoImpl.AdminDAOImpl;
import entity.Admin;
import util.XAuth;
import util.XDialog;
import dao.AdminDAO;
import java.awt.Color;
import util.Style_Net;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class DangNhapJDialog extends javax.swing.JDialog implements DangNhapController{

    /**
     * Creates new form DangNhapJDialog
     */
    AdminDAO dao = new AdminDAOImpl();
    public DangNhapJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buildModernDangNhapLayout();
        setLocationRelativeTo(null);
        setIconAll();
    }

    private void buildModernDangNhapLayout() {
        setTitle("NET-MANAGER - Đăng Nhập Hệ Thống");
        setResizable(false);

        getContentPane().removeAll();
        getContentPane().setLayout(new java.awt.BorderLayout());

        // ROOT CONTAINER
        javax.swing.JPanel rootPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        rootPanel.setPreferredSize(new java.awt.Dimension(780, 470));
        rootPanel.setBackground(Color.WHITE);

        // LEFT PANEL (Brand / Info - Slate Navy #0F172A)
        javax.swing.JPanel leftPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        leftPanel.setPreferredSize(new java.awt.Dimension(320, 470));
        leftPanel.setBackground(Style_Net.NAVY_PRIMARY);
        leftPanel.setBorder(new javax.swing.border.EmptyBorder(40, 30, 30, 30));

        // Left Top & Center Content
        javax.swing.JPanel leftContent = new javax.swing.JPanel();
        leftContent.setLayout(new javax.swing.BoxLayout(leftContent, javax.swing.BoxLayout.Y_AXIS));
        leftContent.setOpaque(false);

        // Brand Name (Strictly NET-MANAGER only)
        javax.swing.JLabel lblBrand = new javax.swing.JLabel("NET-MANAGER");
        lblBrand.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24));
        lblBrand.setForeground(Color.WHITE);
        lblBrand.setAlignmentX(0.0f);
        leftContent.add(lblBrand);

        leftContent.add(javax.swing.Box.createVerticalStrut(6));

        // Subtitle
        javax.swing.JLabel lblSub = new javax.swing.JLabel("HỆ THỐNG QUẢN LÝ PHÒNG MÁY");
        lblSub.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        lblSub.setForeground(new Color(148, 163, 184)); // Slate 400
        lblSub.setAlignmentX(0.0f);
        leftContent.add(lblSub);

        leftContent.add(javax.swing.Box.createVerticalStrut(26));

        // Icon if available
        java.io.File iconFile = new java.io.File("src/main/java/img/Ui-DangNhap-icon-nguoi.png");
        if (iconFile.exists()) {
            javax.swing.JLabel lblIcon = new javax.swing.JLabel();
            lblIcon.setIcon(new javax.swing.ImageIcon(iconFile.getAbsolutePath()));
            lblIcon.setAlignmentX(0.0f);
            leftContent.add(lblIcon);
            leftContent.add(javax.swing.Box.createVerticalStrut(20));
        }

        // Highlight bullets / SaaS info cards (Safe HTML bullets without broken font glyphs)
        String[] features = {
            "Giám sát sơ đồ máy trạm thời gian thực",
            "Tự động hóa gọi món F&B và thanh toán",
            "Phân quyền bảo mật & kiểm toán ca trực"
        };
        for (String feat : features) {
            javax.swing.JLabel lblFeat = new javax.swing.JLabel("<html><span style='color:#38BDF8; font-weight:bold;'>•</span>&nbsp;&nbsp;" + feat + "</html>");
            lblFeat.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
            lblFeat.setForeground(new Color(203, 213, 225)); // Slate 300
            lblFeat.setAlignmentX(0.0f);
            leftContent.add(lblFeat);
            leftContent.add(javax.swing.Box.createVerticalStrut(10));
        }

        leftPanel.add(leftContent, java.awt.BorderLayout.CENTER);

        // Left Bottom: Status indicator
        javax.swing.JPanel leftBottom = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        leftBottom.setOpaque(false);
        javax.swing.JLabel lblStatus = new javax.swing.JLabel("<html><span style='color:#34D399; font-weight:bold;'>•</span>&nbsp;&nbsp;Máy chủ cơ sở dữ liệu đã kết nối</html>");
        lblStatus.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
        lblStatus.setForeground(new Color(52, 211, 153)); // Emerald green
        leftBottom.add(lblStatus);
        leftPanel.add(leftBottom, java.awt.BorderLayout.SOUTH);

        // RIGHT PANEL (Login Form - Clean White Card)
        javax.swing.JPanel rightPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        rightPanel.setPreferredSize(new java.awt.Dimension(460, 470));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(new javax.swing.border.EmptyBorder(40, 45, 35, 45));

        // Form Title
        javax.swing.JPanel titleBox = new javax.swing.JPanel();
        titleBox.setLayout(new javax.swing.BoxLayout(titleBox, javax.swing.BoxLayout.Y_AXIS));
        titleBox.setOpaque(false);

        javax.swing.JLabel lblTitle = new javax.swing.JLabel("ĐĂNG NHẬP HỆ THỐNG");
        lblTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));
        lblTitle.setForeground(Style_Net.NAVY_PRIMARY);
        lblTitle.setAlignmentX(0.0f);
        titleBox.add(lblTitle);

        titleBox.add(javax.swing.Box.createVerticalStrut(4));

        javax.swing.JLabel lblDesc = new javax.swing.JLabel("Vui lòng nhập thông tin xác thực để bắt đầu ca làm việc");
        lblDesc.setFont(Style_Net.FONT_SMALL);
        lblDesc.setForeground(Style_Net.TEXT_MUTED);
        lblDesc.setAlignmentX(0.0f);
        titleBox.add(lblDesc);

        rightPanel.add(titleBox, java.awt.BorderLayout.NORTH);

        // Fields Container
        javax.swing.JPanel fieldsPanel = new javax.swing.JPanel();
        fieldsPanel.setLayout(new javax.swing.BoxLayout(fieldsPanel, javax.swing.BoxLayout.Y_AXIS));
        fieldsPanel.setOpaque(false);
        fieldsPanel.setBorder(new javax.swing.border.EmptyBorder(25, 0, 10, 0));

        // Field 1: Username
        javax.swing.JLabel lblUser = new javax.swing.JLabel("TÊN TÀI KHOẢN / MÃ NHÂN VIÊN");
        lblUser.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        lblUser.setForeground(new Color(71, 85, 105)); // Slate 600
        lblUser.setAlignmentX(0.0f);
        fieldsPanel.add(lblUser);
        fieldsPanel.add(javax.swing.Box.createVerticalStrut(6));

        Style_Net.styleTextField(txtNameAccount);
        txtNameAccount.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 38));
        txtNameAccount.setPreferredSize(new java.awt.Dimension(360, 38));
        txtNameAccount.setAlignmentX(0.0f);
        fieldsPanel.add(txtNameAccount);

        fieldsPanel.add(javax.swing.Box.createVerticalStrut(16));

        // Field 2: Password
        javax.swing.JLabel lblPass = new javax.swing.JLabel("MẬT KHẨU TRUY CẬP");
        lblPass.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        lblPass.setForeground(new Color(71, 85, 105));
        lblPass.setAlignmentX(0.0f);
        fieldsPanel.add(lblPass);
        fieldsPanel.add(javax.swing.Box.createVerticalStrut(6));

        pwdPassWord.setBackground(Color.WHITE);
        pwdPassWord.setForeground(Style_Net.TEXT_MAIN);
        pwdPassWord.setFont(Style_Net.FONT_BODY);
        pwdPassWord.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(Style_Net.BORDER_INPUT, 1, true),
            new javax.swing.border.EmptyBorder(8, 12, 8, 12)
        ));
        pwdPassWord.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 38));
        pwdPassWord.setPreferredSize(new java.awt.Dimension(360, 38));
        pwdPassWord.setAlignmentX(0.0f);
        fieldsPanel.add(pwdPassWord);

        fieldsPanel.add(javax.swing.Box.createVerticalStrut(14));

        // Option Row: Remember me checkbox + Forgot password hint
        javax.swing.JPanel optRow = new javax.swing.JPanel(new java.awt.BorderLayout());
        optRow.setOpaque(false);
        optRow.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 26));
        optRow.setAlignmentX(0.0f);

        javax.swing.JCheckBox chkRemember = new javax.swing.JCheckBox("Ghi nhớ tài khoản");
        chkRemember.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        chkRemember.setForeground(Style_Net.TEXT_MUTED);
        chkRemember.setOpaque(false);
        chkRemember.setSelected(true);
        optRow.add(chkRemember, java.awt.BorderLayout.WEST);

        javax.swing.JLabel lblForgot = new javax.swing.JLabel("Quên mật khẩu?");
        lblForgot.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        lblForgot.setForeground(Style_Net.NAVY_ACCENT);
        lblForgot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblForgot.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                QuenMatKhauJDialog qmk = new QuenMatKhauJDialog((java.awt.Frame) DangNhapJDialog.this.getParent(), true);
                qmk.setLocationRelativeTo(DangNhapJDialog.this);
                qmk.setVisible(true);
            }
        });
        optRow.add(lblForgot, java.awt.BorderLayout.EAST);

        fieldsPanel.add(optRow);
        rightPanel.add(fieldsPanel, java.awt.BorderLayout.CENTER);

        // Action Button: btnLogin
        javax.swing.JPanel bottomAction = new javax.swing.JPanel(new java.awt.BorderLayout());
        bottomAction.setOpaque(false);
        bottomAction.setBorder(new javax.swing.border.EmptyBorder(15, 0, 0, 0));

        btnLogin.setText("ĐĂNG NHẬP HỆ THỐNG");
        btnLogin.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btnLogin.setPreferredSize(new java.awt.Dimension(360, 42));
        Style_Net.stylePrimaryButton(btnLogin);
        bottomAction.add(btnLogin, java.awt.BorderLayout.CENTER);

        rightPanel.add(bottomAction, java.awt.BorderLayout.SOUTH);

        // Add Enter key listener for fast UX
        txtNameAccount.addActionListener(e -> pwdPassWord.requestFocusInWindow());
        pwdPassWord.addActionListener(e -> login());

        // Assemble root
        rootPanel.add(leftPanel, java.awt.BorderLayout.WEST);
        rootPanel.add(rightPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(rootPanel, java.awt.BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        txtNameAccount = new javax.swing.JTextField();
        pwdPassWord = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Giao diện đăng nhập");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btnLogin.setBackground(new java.awt.Color(25, 118, 210));
        btnLogin.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Đăng Nhập");
        btnLogin.setBorder(null);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtNameAccount.setBackground(new java.awt.Color(204, 204, 204));
        txtNameAccount.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNameAccount.setToolTipText("");
        txtNameAccount.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txtNameAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameAccountActionPerformed(evt);
            }
        });

        pwdPassWord.setBackground(new java.awt.Color(204, 204, 204));
        pwdPassWord.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pwdPassWord.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel2.setText("Tên Đăng Nhập");

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel3.setText("Mật Khẩu");

        jPanel2.setBackground(new java.awt.Color(13, 71, 161));

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Đăng Nhập");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(129, 129, 129))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNameAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pwdPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {pwdPassWord, txtNameAccount});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNameAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pwdPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {pwdPassWord, txtNameAccount});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

    this.login();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void txtNameAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameAccountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameAccountActionPerformed

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
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DangNhapJDialog dialog = new DangNhapJDialog(new javax.swing.JFrame(), true);
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
    public void setIconAll(){
    jLabel5.setIcon(new ImageIcon("src/main/java/img/Ui-DangNhap-icon-nguoi.png"));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField pwdPassWord;
    private javax.swing.JTextField txtNameAccount;
    // End of variables declaration//GEN-END:variables

    @Override
    public void open() {
    this.setLocationRelativeTo(null);
    }

    @Override
    public void login() {
    String username = txtNameAccount.getText();
    String password = pwdPassWord.getText();
    Admin user = dao.findByUsername(username);

    if (user == null) {
        XDialog.alert("Sai tên đăng nhập!");
    } else if (!password.equals(user.getMatKhau())) {
        XDialog.alert("Sai mật khẩu đăng nhập!");
    } else if (!user.isTrangThai()) {
        XDialog.alert("Tài khoản của bạn đang tạm dừng!");
    } else {
        XAuth.user = user;
        JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
        this.dispose();
    }
}
}
