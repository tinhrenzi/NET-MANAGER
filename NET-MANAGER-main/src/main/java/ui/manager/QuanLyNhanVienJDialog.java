/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui.manager;

import controller.QuanLyNhanVienController;
import daoImpl.AdminDAOImpl;
import entity.Admin;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.XDialog;
import dao.AdminDAO;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import util.Style_Net;
import util.XAuth;

/**
 *
 * @author LuongHiep
 */
public class QuanLyNhanVienJDialog extends javax.swing.JDialog implements QuanLyNhanVienController {

    int a;
    /**
     * Creates new form QuanLyNhanVienJDialog
     */
    AdminDAO dao = new AdminDAOImpl();
    List<Admin> items = List.of();
    private String DuongDanAnh;

    private javax.swing.JLabel lblHeaderSummary;
    private javax.swing.JTextField txtMaNVDisplay;
    private javax.swing.JButton btnThemNVQuick;
    private javax.swing.JButton btnShowAllNV;

    public QuanLyNhanVienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buildModernQLNVLayout();
        fillToTable();
        setLocationRelativeTo(null);
    }

    private void buildModernQLNVLayout() {
        setTitle("NET-MANAGER - Quản Lý Nhân Sự & Phân Quyền");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().setLayout(new java.awt.BorderLayout(0, 16));
        getContentPane().setBackground(Style_Net.BG_CANVAS);
        ((javax.swing.JPanel) getContentPane()).setBorder(new javax.swing.border.EmptyBorder(16, 20, 20, 20));

        // 1. TOP HEADER BAR
        javax.swing.JPanel pnlTopHeader = new javax.swing.JPanel(new java.awt.BorderLayout());
        pnlTopHeader.setOpaque(false);

        javax.swing.JPanel pnlTitleGroup = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));
        pnlTitleGroup.setOpaque(false);
        javax.swing.JLabel lblBrand = new javax.swing.JLabel("NET-MANAGER");
        lblBrand.setFont(Style_Net.FONT_BRAND);
        lblBrand.setForeground(Style_Net.NAVY_PRIMARY);
        javax.swing.JLabel lblSubTitle = new javax.swing.JLabel("QUẢN LÝ NHÂN SỰ • PHÂN QUYỀN VẬN HÀNH");
        lblSubTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        lblSubTitle.setForeground(Style_Net.TEXT_MUTED);
        pnlTitleGroup.add(lblBrand);
        pnlTitleGroup.add(new javax.swing.JLabel("  "));
        pnlTitleGroup.add(lblSubTitle);

        lblHeaderSummary = new javax.swing.JLabel("Tổng nhân sự: 0 tài khoản • Sẵn sàng");
        lblHeaderSummary.setFont(Style_Net.FONT_LABEL);
        lblHeaderSummary.setForeground(Style_Net.TEXT_MUTED);

        pnlTopHeader.add(pnlTitleGroup, java.awt.BorderLayout.WEST);
        pnlTopHeader.add(lblHeaderSummary, java.awt.BorderLayout.EAST);
        getContentPane().add(pnlTopHeader, java.awt.BorderLayout.NORTH);

        // 2. CENTER CONTENT (Two-column SaaS layout)
        javax.swing.JPanel pnlCenter = new javax.swing.JPanel(new java.awt.BorderLayout(16, 0));
        pnlCenter.setOpaque(false);

        // --- LEFT TABLE CARD ---
        javax.swing.JPanel pnlLeftCard = Style_Net.createCardPanel();
        pnlLeftCard.setLayout(new java.awt.BorderLayout(0, 12));

        // Search + Quick Add Bar
        javax.swing.JPanel pnlToolbar = new javax.swing.JPanel(new java.awt.BorderLayout(10, 0));
        pnlToolbar.setOpaque(false);

        javax.swing.JPanel pnlSearch = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));
        pnlSearch.setOpaque(false);
        txtFindbyid.setPreferredSize(new java.awt.Dimension(240, 36));
        Style_Net.styleTextField(txtFindbyid);
        txtFindbyid.putClientProperty("JTextField.placeholderText", "Tìm theo tên hoặc số điện thoại...");
        txtFindbyid.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                filterTableData();
            }
        });

        Style_Net.styleSecondaryButton(btnFind);
        btnFind.setText("Tìm kiếm");
        btnFind.setPreferredSize(new java.awt.Dimension(95, 36));

        btnShowAllNV = new javax.swing.JButton("Tất cả");
        Style_Net.styleSecondaryButton(btnShowAllNV);
        btnShowAllNV.setPreferredSize(new java.awt.Dimension(75, 36));
        btnShowAllNV.addActionListener(e -> {
            txtFindbyid.setText("");
            fillToTable();
        });

        pnlSearch.add(txtFindbyid);
        pnlSearch.add(btnFind);
        pnlSearch.add(btnShowAllNV);

        btnThemNVQuick = new javax.swing.JButton("+ THÊM NHÂN VIÊN");
        Style_Net.stylePrimaryButton(btnThemNVQuick);
        btnThemNVQuick.setPreferredSize(new java.awt.Dimension(160, 36));
        btnThemNVQuick.addActionListener(e -> {
            clear();
            txtName.requestFocus();
        });

        pnlToolbar.add(pnlSearch, java.awt.BorderLayout.WEST);
        pnlToolbar.add(btnThemNVQuick, java.awt.BorderLayout.EAST);
        pnlLeftCard.add(pnlToolbar, java.awt.BorderLayout.NORTH);

        // Modern Table
        Style_Net.styleTable(tblUsermager);
        tblUsermager.setRowHeight(40);
        jScrollPane1.setViewportView(tblUsermager);
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(Style_Net.BORDER_HAIRLINE, 1, true));
        pnlLeftCard.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlCenter.add(pnlLeftCard, java.awt.BorderLayout.CENTER);

        // --- RIGHT FORM CARD (width ~350px) ---
        javax.swing.JPanel pnlRightCard = Style_Net.createCardPanel();
        pnlRightCard.setPreferredSize(new java.awt.Dimension(350, 560));
        pnlRightCard.setLayout(new java.awt.BorderLayout(0, 16));

        // Form Header
        javax.swing.JPanel pnlFormHeader = new javax.swing.JPanel();
        pnlFormHeader.setLayout(new javax.swing.BoxLayout(pnlFormHeader, javax.swing.BoxLayout.Y_AXIS));
        pnlFormHeader.setOpaque(false);
        javax.swing.JLabel lblFormTitle = new javax.swing.JLabel("THÔNG TIN NHÂN SỰ");
        lblFormTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lblFormTitle.setForeground(Style_Net.NAVY_PRIMARY);
        lblFormTitle.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        javax.swing.JLabel lblFormDesc = new javax.swing.JLabel("Phân quyền chức năng và thông tin liên hệ");
        lblFormDesc.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        lblFormDesc.setForeground(Style_Net.TEXT_MUTED);
        lblFormDesc.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        pnlFormHeader.add(lblFormTitle);
        pnlFormHeader.add(javax.swing.Box.createVerticalStrut(4));
        pnlFormHeader.add(lblFormDesc);
        pnlRightCard.add(pnlFormHeader, java.awt.BorderLayout.NORTH);

        // Form Fields
        javax.swing.JPanel pnlFormBody = new javax.swing.JPanel();
        pnlFormBody.setLayout(new javax.swing.BoxLayout(pnlFormBody, javax.swing.BoxLayout.Y_AXIS));
        pnlFormBody.setOpaque(false);

        // Field 1: Mã định danh
        javax.swing.JLabel lblF1 = new javax.swing.JLabel("MÃ ĐỊNH DANH");
        lblF1.setFont(Style_Net.FONT_LABEL);
        lblF1.setForeground(Style_Net.TEXT_MUTED);
        lblF1.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        txtMaNVDisplay = new javax.swing.JTextField();
        txtMaNVDisplay.setEditable(false);
        txtMaNVDisplay.setBackground(new java.awt.Color(0xF1, 0xF5, 0xF9));
        Style_Net.styleTextField(txtMaNVDisplay);
        txtMaNVDisplay.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        txtMaNVDisplay.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 2: Tên tài khoản
        javax.swing.JLabel lblF2 = new javax.swing.JLabel("TÊN TÀI KHOẢN ĐĂNG NHẬP");
        lblF2.setFont(Style_Net.FONT_LABEL);
        lblF2.setForeground(Style_Net.TEXT_MUTED);
        lblF2.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        Style_Net.styleTextField(txtName);
        txtName.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        txtName.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 3: Mật khẩu
        javax.swing.JLabel lblF3 = new javax.swing.JLabel("MẬT KHẨU TRUY CẬP");
        lblF3.setFont(Style_Net.FONT_LABEL);
        lblF3.setForeground(Style_Net.TEXT_MUTED);
        lblF3.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        Style_Net.styleTextField(txtPass);
        txtPass.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        txtPass.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 4: Phân quyền vai trò
        javax.swing.JLabel lblF4 = new javax.swing.JLabel("PHÂN QUYỀN VAI TRÒ");
        lblF4.setFont(Style_Net.FONT_LABEL);
        lblF4.setForeground(Style_Net.TEXT_MUTED);
        lblF4.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        cboVaitro.setFont(Style_Net.FONT_BODY);
        cboVaitro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Quản lý (Toàn quyền hệ thống)", "Nhân viên (Mở máy, gọi món, thu tiền)"}));
        cboVaitro.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        cboVaitro.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 5: Số điện thoại
        javax.swing.JLabel lblF5 = new javax.swing.JLabel("SỐ ĐIỆN THOẠI LIÊN HỆ");
        lblF5.setFont(Style_Net.FONT_LABEL);
        lblF5.setForeground(Style_Net.TEXT_MUTED);
        lblF5.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        Style_Net.styleTextField(txtPhone);
        txtPhone.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        txtPhone.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 6: Email
        javax.swing.JLabel lblF6 = new javax.swing.JLabel("ĐỊA CHỈ EMAIL");
        lblF6.setFont(Style_Net.FONT_LABEL);
        lblF6.setForeground(Style_Net.TEXT_MUTED);
        lblF6.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        Style_Net.styleTextField(txtEmail);
        txtEmail.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        txtEmail.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        pnlFormBody.add(lblF1);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(3));
        pnlFormBody.add(txtMaNVDisplay);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(10));

        pnlFormBody.add(lblF2);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(3));
        pnlFormBody.add(txtName);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(10));

        pnlFormBody.add(lblF3);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(3));
        pnlFormBody.add(txtPass);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(10));

        pnlFormBody.add(lblF4);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(3));
        pnlFormBody.add(cboVaitro);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(10));

        pnlFormBody.add(lblF5);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(3));
        pnlFormBody.add(txtPhone);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(10));

        pnlFormBody.add(lblF6);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(3));
        pnlFormBody.add(txtEmail);
        pnlFormBody.add(javax.swing.Box.createVerticalGlue());

        pnlRightCard.add(pnlFormBody, java.awt.BorderLayout.CENTER);

        // Form Buttons
        javax.swing.JPanel pnlFormButtons = new javax.swing.JPanel(new java.awt.GridLayout(3, 1, 0, 8));
        pnlFormButtons.setOpaque(false);

        btnUpdate.setText("LƯU THÔNG TIN NHÂN VIÊN");
        Style_Net.stylePrimaryButton(btnUpdate);
        btnUpdate.setPreferredSize(new java.awt.Dimension(300, 38));

        jButton2.setText("LÀM MỚI FORM");
        Style_Net.styleSecondaryButton(jButton2);
        jButton2.setPreferredSize(new java.awt.Dimension(300, 36));
        for (java.awt.event.ActionListener al : jButton2.getActionListeners()) {
            jButton2.removeActionListener(al);
        }
        jButton2.addActionListener(e -> clear());

        btnDelete.setText("TẠM KHÓA / XÓA TÀI KHOẢN");
        Style_Net.styleDangerButton(btnDelete);
        btnDelete.setPreferredSize(new java.awt.Dimension(300, 36));
        for (java.awt.event.ActionListener al : btnDelete.getActionListeners()) {
            btnDelete.removeActionListener(al);
        }
        btnDelete.addActionListener(e -> delete());

        tblUsermager.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int r = tblUsermager.getSelectedRow();
                if (r >= 0) fillTXT(r);
            }
        });

        pnlFormButtons.add(btnUpdate);
        pnlFormButtons.add(jButton2);
        pnlFormButtons.add(btnDelete);

        pnlRightCard.add(pnlFormButtons, java.awt.BorderLayout.SOUTH);

        pnlCenter.add(pnlRightCard, java.awt.BorderLayout.EAST);
        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);
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
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtNamSinh = new javax.swing.JTextField();
        rdo1 = new javax.swing.JRadioButton();
        rdo2 = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboVaitro = new javax.swing.JComboBox<>();
        dacDateCre = new com.toedter.calendar.JDateChooser();
        lblID = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlAnh = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsermager = new javax.swing.JTable();
        txtFindbyid = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Giao diện quản lý nhân viên");
        setBackground(new java.awt.Color(204, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(13, 71, 161));
        jLabel1.setText("Quản Lý Nhân Viên");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(239, 244, 246));

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel3.setText("ID");

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel4.setText("Tên đăng nhập");

        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel5.setText("Mật khẩu");

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel6.setText("Năm sinh");

        txtName.setBackground(new java.awt.Color(243, 244, 246));
        txtName.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel8.setText("Trạng thái");

        jLabel9.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel9.setText("Phone");

        txtEmail.setBackground(new java.awt.Color(243, 244, 246));
        txtEmail.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtPhone.setBackground(new java.awt.Color(243, 244, 246));
        txtPhone.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtPhone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        txtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneActionPerformed(evt);
            }
        });

        txtNamSinh.setBackground(new java.awt.Color(243, 244, 246));
        txtNamSinh.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtNamSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        txtNamSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamSinhActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo1);
        rdo1.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        rdo1.setText("Hoạt động");

        buttonGroup1.add(rdo2);
        rdo2.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        rdo2.setText("Không hoạt động");

        jLabel12.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel12.setText("Ngày tạo");

        txtPass.setBackground(new java.awt.Color(243, 244, 246));
        txtPass.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPassMouseClicked(evt);
            }
        });
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel10.setText("Vai trò");

        cboVaitro.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        cboVaitro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý ", "Nhân viên" }));
        cboVaitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVaitroActionPerformed(evt);
            }
        });

        dacDateCre.setBackground(new java.awt.Color(204, 204, 204));
        dacDateCre.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        lblID.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lblID.setText("Mã mặc định AD");

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh.setText("Ảnh cần có mặt");

        javax.swing.GroupLayout pnlAnhLayout = new javax.swing.GroupLayout(pnlAnh);
        pnlAnh.setLayout(pnlAnhLayout);
        pnlAnhLayout.setHorizontalGroup(
            pnlAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnhLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlAnhLayout.setVerticalGroup(
            pnlAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnhLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnUpdate.setBackground(new java.awt.Color(220, 38, 38));
        btnUpdate.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnCreate.setBackground(new java.awt.Color(22, 163, 74));
        btnCreate.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("Thêm");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(220, 38, 38));
        jButton1.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ảnh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail)
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel7))
                            .addComponent(jLabel6)
                            .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(dacDateCre, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cboVaitro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdo1)
                                .addGap(18, 18, 18)
                                .addComponent(rdo2)))
                        .addGap(52, 52, 52)
                        .addComponent(pnlAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(100, 100, 100))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtName, txtPass});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCreate, btnUpdate, jButton1});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pnlAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(rdo1)
                                                .addComponent(rdo2)))
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboVaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dacDateCre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel3)
                                .addGap(20, 20, 20)
                                .addComponent(lblID)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)))
                .addGap(9, 9, 9)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnUpdate)
                    .addComponent(jButton1))
                .addGap(23, 23, 23))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dacDateCre, txtEmail, txtNamSinh, txtName, txtPass, txtPhone});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Biểu mẫu", jPanel3);

        jPanel4.setBackground(new java.awt.Color(239, 244, 246));

        tblUsermager.setAutoCreateRowSorter(true);
        tblUsermager.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        tblUsermager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Tên", "Mật khẩu", "Vai trò", "Trạng thái", "Năm sinh", "Email", "Số điện thại", "Ngày tạo", "Link ảnh"
            }
        ));
        tblUsermager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsermagerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsermager);

        txtFindbyid.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtFindbyid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindbyidActionPerformed(evt);
            }
        });

        btnFind.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btnFind.setText("Tìm kiếm");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(220, 38, 38));
        btnDelete.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtFindbyid, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFind)
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindbyid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(14, 14, 14))
        );

        jTabbedPane1.addTab("Thông tin người dùng", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(412, 412, 412)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed

        String username = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String pass = txtPass.getText().trim();
        String year = txtNamSinh.getText().trim();

        String usernameRegex = "^[a-zA-Z0-9_]+$";
        String yearRegex = "^[0-9]{4}$";
        String passwordRegex = "^[a-zA-Z0-9_!@#$%^&*]+$";
        String passRegex = "^(?=.*[A-Z]).{7,}$";
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        String phoneRegex = "^0\\d{9}$";

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống");
            return;
        }
        if (!username.matches(usernameRegex)) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không hợp lệ! (Chỉ dùng chữ không dấu, số và gạch dưới)");
            return;
        }

        if (year.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Năm sinh không được trống");
            return;
        }
        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được trống");
            return;
        }
        if (email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống Email hoặc Số điện thoại!");
            return;
        }
        if (!year.matches(yearRegex)) {
            JOptionPane.showMessageDialog(this, "Năm sinh không hợp lệ");
            return;
        }
        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
            return;
        }
        if (!pass.matches(passwordRegex)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không hợp lệ! (Không được chứa dấu tiếng Việt)");
            return;
        }
        if (pass.length() < 7 || !pass.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải >= 7 ký tự và có ít nhất 1 chữ in hoa");
            return;
        }
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
            return;
        }
        if (!phone.matches(phoneRegex)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! (Phải bắt đầu bằng 0 và đủ 10 số)");
            return;
        }

        this.create();
//        showSuccessDialog("Thêm thành công", 2000);

    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (lblID.getText().trim().isEmpty() || lblID.getText().contains("Tự động") || lblID.getText().contains("mặc định")) {
            btnCreateActionPerformed(evt);
            return;
        }

        String username = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String pass = txtPass.getText().trim();
        String year = txtNamSinh.getText().trim();

        String usernameRegex = "^[a-zA-Z0-9_]+$";
        String yearRegex = "^[0-9]{4}$";
        String passRegex = "^(?=.*[A-Z]).{7,}$";
        String passwordRegex = "^[a-zA-Z0-9_!@#$%^&*]+$";
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        String phoneRegex = "^0\\d{9}$";

        // Check username
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống");
            return;
        }
        if (!username.matches(usernameRegex)) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không hợp lệ! (Chỉ dùng chữ không dấu, số và gạch dưới)");
            return;
        }

        // Check các trường khác
        if (year.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Năm sinh không được trống");
            return;
        }
        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được trống");
            return;
        }
        if (email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống Email hoặc Số điện thoại!");
            return;
        }
        if (!year.matches(yearRegex)) {
            JOptionPane.showMessageDialog(this, "Năm sinh không hợp lệ");
            return;
        }
        // Check Password
        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
            return;
        }
        if (!pass.matches(passwordRegex)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không hợp lệ! (Không được chứa dấu tiếng Việt)");
            return;
        }
        if (pass.length() < 7 || !pass.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải >= 7 ký tự và có ít nhất 1 chữ in hoa");
            return;
        }
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
            return;
        }
        if (!phone.matches(phoneRegex)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! (Phải bắt đầu bằng 0 và đủ 10 số)");
            return;
        }

        String oldRole = tblUsermager.getValueAt(tblUsermager.getSelectedRow(), 3).toString();
        String newRole = cboVaitro.getSelectedItem().toString();

        if ("Quản lý".equalsIgnoreCase(oldRole) && "Nhân viên".equalsIgnoreCase(newRole)) {
            JOptionPane.showMessageDialog(this, "Không thể hạ Quản lý xuống Nhân viên");
            return;
        }

        this.update();
//        showSuccessDialog("Sửa thành công", 2000);

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblUsermagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsermagerMouseClicked
        // TODO add your handling code here:
        int index = tblUsermager.getSelectedRow();
        fillTXT(index);
    }//GEN-LAST:event_tblUsermagerMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        String id = txtFindbyid.getText();
        this.findname(id);
    }//GEN-LAST:event_btnFindActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneActionPerformed
        // TODO add your handling code here:      
    }//GEN-LAST:event_txtPhoneActionPerformed

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed

    }//GEN-LAST:event_txtPassActionPerformed

    private void txtPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMouseClicked

    }//GEN-LAST:event_txtPassMouseClicked

    private void txtNamSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamSinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamSinhActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void cboVaitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVaitroActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboVaitroActionPerformed

    private void txtFindbyidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindbyidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindbyidActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            JFileChooser f = new JFileChooser("C:\\DUANXUONG\\NET-MANAGER");
            f.setDialogTitle("Mở file");
            f.showOpenDialog(null);
            File ftenanh = f.getSelectedFile();

            DuongDanAnh = ftenanh.getAbsolutePath();

            lblAnh.setIcon(ResizeImage(String.valueOf(DuongDanAnh)));
            System.out.println(DuongDanAnh);
        } catch (Exception ex) {
            System.out.println("chưa chọn ảnh");
            System.out.println(DuongDanAnh);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuanLyNhanVienJDialog dialog = new QuanLyNhanVienJDialog(new javax.swing.JFrame(), true);
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

    private void showSuccessDialog(String message, int timeMillis) {
        JOptionPane optionPane = new JOptionPane(
                message,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{},
                null);

        JDialog dialog = optionPane.createDialog(this, "Thông báo");

        dialog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dialog.dispose();
            }
        });
        new javax.swing.Timer(timeMillis, e -> dialog.dispose()).start();
        dialog.setVisible(true);
    }

    public void fillTXT(int row) {
        if (row < 0 || row >= tblUsermager.getRowCount()) return;
        Object idVal = tblUsermager.getValueAt(row, 0);
        String idStr = idVal != null ? idVal.toString() : "";
        lblID.setText(idStr);
        if (txtMaNVDisplay != null) {
            txtMaNVDisplay.setText(idStr);
        }

        for (Admin user : items) {
            if (user.getId().equals(idStr)) {
                txtName.setText(user.getTen() != null ? user.getTen() : "");
                txtPass.setText(user.getMatKhau() != null ? user.getMatKhau() : "");
                txtPhone.setText(user.getSoDienThoai() != null ? user.getSoDienThoai() : "");
                txtEmail.setText(user.getEmail() != null ? user.getEmail() : "");
                txtNamSinh.setText(String.valueOf(user.getNamSinh()));
                if (cboVaitro != null) {
                    cboVaitro.setSelectedIndex(user.getVaiTro() == 1 ? 0 : 1);
                }
                rdo1.setSelected(user.isTrangThai());
                rdo2.setSelected(!user.isTrangThai());

                if (user.getNgayTao() != null) {
                    dacDateCre.setDate(user.getNgayTao());
                }

                DuongDanAnh = user.getAnh();
                if (lblAnh != null) {
                    if (DuongDanAnh != null && !DuongDanAnh.isEmpty()) {
                        lblAnh.setIcon(ResizeImage(DuongDanAnh));
                    } else {
                        lblAnh.setIcon(null);
                    }
                }
                break;
            }
        }

        if (btnUpdate != null) {
            btnUpdate.setText("✓ LƯU THÔNG TIN NHÂN VIÊN");
        }
        if (lblHeaderSummary != null) {
            lblHeaderSummary.setText("Tổng nhân sự: " + items.size() + " tài khoản • Đang chọn: " + idStr + " (" + txtName.getText() + ")");
        }
    }

    public ImageIcon ResizeImage(String ImagePath) {
        if (ImagePath == null || ImagePath.trim().isEmpty()) return null;
        File f = new File(ImagePath);
        if (!f.exists()) {
            File f1 = new File("src/main/java/img/" + ImagePath);
            if (f1.exists()) f = f1;
            else {
                File f2 = new File("src/images/" + ImagePath);
                if (f2.exists()) f = f2;
            }
        }
        if (!f.exists()) return null;
        try {
            ImageIcon myImage = new ImageIcon(f.getAbsolutePath());
            Image img = myImage.getImage();
            int w = (lblAnh != null && lblAnh.getWidth() > 0) ? lblAnh.getWidth() : 60;
            int h = (lblAnh != null && lblAnh.getHeight() > 0) ? lblAnh.getHeight() : 60;
            Image newImg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        } catch (Exception ex) {
            return null;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboVaitro;
    private com.toedter.calendar.JDateChooser dacDateCre;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblID;
    private javax.swing.JPanel pnlAnh;
    private javax.swing.JRadioButton rdo1;
    private javax.swing.JRadioButton rdo2;
    private javax.swing.JTable tblUsermager;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFindbyid;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables

    @Override
    public void open() {
        this.setLocationRelativeTo(null);
        this.fillToTable();
        this.clear();
    }

    public void setRole() {
        int role = XAuth.user != null ? XAuth.user.getVaiTro() : 1;
        if (role == 2) {
            btnUpdate.setEnabled(false);
        } else {
            btnUpdate.setEnabled(true);
        }
    }

    @Override
    public void setForm(Admin entity) {
        lblID.setText(entity.getId());
        if (txtMaNVDisplay != null) txtMaNVDisplay.setText(entity.getId());
        txtName.setText(entity.getTen());
        txtPass.setText(entity.getMatKhau());
        txtEmail.setText(entity.getEmail());
        cboVaitro.setSelectedIndex(entity.getVaiTro() == 1 ? 0 : 1);
        txtPhone.setText(entity.getSoDienThoai());
        txtNamSinh.setText(String.valueOf(entity.getNamSinh()));
        if (entity.getNgayTao() != null) {
            dacDateCre.setDate(entity.getNgayTao());
        }
        rdo1.setSelected(entity.isTrangThai());
        rdo2.setSelected(!entity.isTrangThai());

        if (entity.getAnh() != null && !entity.getAnh().isEmpty()) {
            DuongDanAnh = entity.getAnh();
            if (lblAnh != null) lblAnh.setIcon(ResizeImage(DuongDanAnh));
        } else {
            if (lblAnh != null) lblAnh.setIcon(null);
            DuongDanAnh = null;
        }
    }

    @Override
    public Admin getForm() {
        Admin users = new Admin();
        try {
            String id = lblID.getText().trim();
            if (!id.isEmpty() && !id.contains("Tự động")) {
                users.setId(id);
            }
            users.setTen(txtName.getText().trim());
            users.setMatKhau(txtPass.getText().trim());
            users.setVaiTro(cboVaitro.getSelectedIndex() == 0 ? 1 : 2);
            users.setTrangThai(rdo1.isSelected());
            users.setEmail(txtEmail.getText().trim());
            users.setSoDienThoai(txtPhone.getText().trim());
            String namSinhStr = txtNamSinh.getText().trim();
            users.setNamSinh(!namSinhStr.isEmpty() ? Integer.parseInt(namSinhStr) : 2000);
            users.setNgayTao(dacDateCre.getDate() != null ? dacDateCre.getDate() : new java.util.Date());
            users.setAnh(DuongDanAnh);
        } catch (Exception ex) {
            return null;
        }
        return users;
    }

    @Override
    public void fillToTable() {
        items = dao.findAll();
        if (lblHeaderSummary != null) {
            lblHeaderSummary.setText("Tổng nhân sự: " + items.size() + " tài khoản • Sẵn sàng");
        }
        filterTableData();
    }

    public void filterTableData() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"MÃ NV", "TÊN TÀI KHOẢN", "SỐ ĐIỆN THOẠI", "EMAIL", "VAI TRÒ", "TRẠNG THÁI"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblUsermager.setModel(model);

        String keyword = txtFindbyid != null ? txtFindbyid.getText().trim().toLowerCase() : "";

        for (Admin item : items) {
            if (!keyword.isEmpty()) {
                String idStr = item.getId() != null ? item.getId().toLowerCase() : "";
                String nameStr = item.getTen() != null ? item.getTen().toLowerCase() : "";
                String phoneStr = item.getSoDienThoai() != null ? item.getSoDienThoai().toLowerCase() : "";
                if (!idStr.contains(keyword) && !nameStr.contains(keyword) && !phoneStr.contains(keyword)) {
                    continue;
                }
            }

            String roleName = item.getVaiTro() == 1 ? "Quản lý" : "Nhân viên";
            String st = item.isTrangThai() ? "Hoạt động" : "Tạm khóa";

            model.addRow(new Object[]{
                item.getId(),
                item.getTen(),
                item.getSoDienThoai(),
                item.getEmail(),
                roleName,
                st
            });
        }
        setupQLNVRenderers();
    }

    private void setupQLNVRenderers() {
        if (tblUsermager.getColumnCount() >= 6) {
            // Vai trò renderer
            tblUsermager.getColumnModel().getColumn(4).setCellRenderer((table, value, isSelected, hasFocus, row, col) -> {
                String role = value != null ? value.toString() : "";
                javax.swing.JPanel pnl = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 7));
                pnl.setBackground(isSelected ? table.getSelectionBackground() : java.awt.Color.WHITE);
                if ("Quản lý".equalsIgnoreCase(role)) {
                    pnl.add(Style_Net.createBadge("Quản lý", Style_Net.NAVY_PRIMARY, java.awt.Color.WHITE));
                } else {
                    pnl.add(Style_Net.createBadge("Nhân viên", new java.awt.Color(0xF1, 0xF5, 0xF9), new java.awt.Color(0x47, 0x55, 0x69)));
                }
                return pnl;
            });

            // Trạng thái renderer
            tblUsermager.getColumnModel().getColumn(5).setCellRenderer((table, value, isSelected, hasFocus, row, col) -> {
                String st = value != null ? value.toString() : "";
                javax.swing.JLabel lbl = new javax.swing.JLabel(st.equalsIgnoreCase("Hoạt động") ? "• Hoạt động" : "• Tạm khóa", javax.swing.SwingConstants.CENTER);
                lbl.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
                lbl.setForeground(st.equalsIgnoreCase("Hoạt động") ? new java.awt.Color(0x10, 0xB9, 0x81) : new java.awt.Color(0xEF, 0x44, 0x44));
                javax.swing.JPanel pnl = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 8));
                pnl.setBackground(isSelected ? table.getSelectionBackground() : java.awt.Color.WHITE);
                pnl.add(lbl);
                return pnl;
            });
        }
    }

    @Override
    public void edit() {
    }

    @Override
    public void create() {
        Admin user = this.getForm();
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        dao.create(user);
        JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
        this.fillToTable();
        this.clear();
    }

    @Override
    public void update() {
        Admin user = this.getForm();
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại thông tin!");
            return;
        }
        dao.update(user);
        JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên thành công!");
        this.fillToTable();
    }

    @Override
    public void delete() {
        String id = lblID.getText().trim();

        if (id.isEmpty()) {
            XDialog.alert("Vui lòng chọn tài khoản cần xóa!");
            return;
        }

        Admin admin = dao.findByID(id);
        if (admin != null && admin.getVaiTro() == 1) {
            XDialog.alert("Không thể xóa tài khoản quản lý!");
            return;
        }

        if (XDialog.confirm("Bạn xác nhận xóa?")) {
            dao.deleteByID(id);
            this.fillToTable();
            this.clear();
            showSuccessDialog("Xóa thành công", 1000);
        }
    }

    @Override
    public void clear() {
        lblID.setText("");
        if (txtMaNVDisplay != null) {
            txtMaNVDisplay.setText("(Tự động cấp khi thêm)");
        }
        txtName.setText("");
        txtPass.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtNamSinh.setText("");
        dacDateCre.setDate(new java.util.Date());
        if (cboVaitro != null) cboVaitro.setSelectedIndex(1);
        rdo1.setSelected(true);
        if (lblAnh != null) lblAnh.setIcon(null);
        DuongDanAnh = null;
        if (btnUpdate != null) {
            btnUpdate.setText("+ THÊM VÀO HỆ THỐNG");
        }
        if (lblHeaderSummary != null) {
            lblHeaderSummary.setText("Tổng nhân sự: " + items.size() + " tài khoản • Thêm nhân sự mới");
        }
    }

    @Override
    public void setEditable(boolean editable) {

    }

    public void findname(String name) {
        String ten = txtFindbyid.getText();
        DefaultTableModel model = (DefaultTableModel) tblUsermager.getModel();
        model.setRowCount(0);
        for (Admin i : items) {
            String Vaitro = switch (i.getVaiTro()) {
                case 1 ->
                    "Quản lý";
                case 2 ->
                    "Nhân viên";
                default ->
                    "ko ro";
            };
            if (i.getTen().toLowerCase().contains(ten.toLowerCase())) {
                model.addRow(new Object[]{i.getId(),
                    i.getTen(),
                    i.getMatKhau(),
                    Vaitro,
                    i.isTrangThai() ? "Hoạt động" : "Không hoạt động",
                    i.getNamSinh(),
                    i.getEmail(),
                    i.getSoDienThoai(),
                    i.getNgayTao(),
                    i.getAnh()
                });
            }
        }
    }

    private void openImage(String path) {

        try {
            File f = new File(path);
            if (!f.exists()) {
                String alt = "src/images/" + path;
                if (new File(alt).exists()) {
                    path = alt;
                }
            }
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
            ImageIcon big = new ImageIcon(newImg);
            javax.swing.JLabel lb = new javax.swing.JLabel(big);
            javax.swing.JScrollPane sp = new javax.swing.JScrollPane(lb);
            sp.setPreferredSize(new java.awt.Dimension(900, 820));
            javax.swing.JOptionPane.showMessageDialog(this, sp, "Xem ảnh lớn", javax.swing.JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Không thể mở ảnh: " + e.getMessage());
        }
    }

}
