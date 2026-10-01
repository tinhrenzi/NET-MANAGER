/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui.manager;

import controller.QuanLyMayTinhConntroll;
import dao.MayTinhDAO;
import daoImpl.MayTinhDAOImpl;
import entity.MayTinh;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.Style_Net;
import util.XDialog;

/**
 *
 * @author FPT
 */
public class QuanLyMayTinh extends javax.swing.JDialog implements QuanLyMayTinhConntroll {

    /**
     * Creates new form QuanLyMayTinh
     */
    MayTinhDAO dao = new MayTinhDAOImpl();
    List<MayTinh> list = new ArrayList<>();

    private javax.swing.JLabel lblHeaderSummary;
    private javax.swing.JComboBox<String> cboKhuVuc;
    private javax.swing.JTextField txtMaMayDisplay;
    private javax.swing.JButton btnThemMoiQuick;
    private javax.swing.JButton btnResetTable;

    public QuanLyMayTinh(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buildModernQLMTLayout();
        this.open();
    }

    private void buildModernQLMTLayout() {
        setTitle("NET-MANAGER - Quản Lý Danh Mục Máy Tính");
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
        javax.swing.JLabel lblSubTitle = new javax.swing.JLabel("QUẢN LÝ DANH MỤC MÁY TÍNH • CẤU HÌNH ĐƠN GIÁ");
        lblSubTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        lblSubTitle.setForeground(Style_Net.TEXT_MUTED);
        pnlTitleGroup.add(lblBrand);
        pnlTitleGroup.add(new javax.swing.JLabel("  "));
        pnlTitleGroup.add(lblSubTitle);

        lblHeaderSummary = new javax.swing.JLabel("Hệ thống: 0 máy trạm • Sẵn sàng");
        lblHeaderSummary.setFont(Style_Net.FONT_LABEL);
        lblHeaderSummary.setForeground(Style_Net.TEXT_MUTED);

        pnlTopHeader.add(pnlTitleGroup, java.awt.BorderLayout.WEST);
        pnlTopHeader.add(lblHeaderSummary, java.awt.BorderLayout.EAST);
        getContentPane().add(pnlTopHeader, java.awt.BorderLayout.NORTH);

        // 2. CENTER CONTENT (Two-column SaaS layout)
        javax.swing.JPanel pnlCenter = new javax.swing.JPanel(new java.awt.BorderLayout(16, 0));
        pnlCenter.setOpaque(false);

        // --- LEFT CARD (Table Area) ---
        javax.swing.JPanel pnlLeftCard = Style_Net.createCardPanel();
        pnlLeftCard.setLayout(new java.awt.BorderLayout(0, 12));

        // Left Top Bar: Search + Quick Add
        javax.swing.JPanel pnlTableToolbar = new javax.swing.JPanel(new java.awt.BorderLayout(10, 0));
        pnlTableToolbar.setOpaque(false);

        javax.swing.JPanel pnlSearchBox = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));
        pnlSearchBox.setOpaque(false);
        txtFind.setPreferredSize(new java.awt.Dimension(220, 36));
        Style_Net.styleTextField(txtFind);
        txtFind.putClientProperty("JTextField.placeholderText", "Tìm theo tên máy...");
        Style_Net.styleSecondaryButton(btnFind);
        btnFind.setText("Tìm kiếm");
        btnFind.setPreferredSize(new java.awt.Dimension(95, 36));

        btnResetTable = new javax.swing.JButton("Tất cả");
        Style_Net.styleSecondaryButton(btnResetTable);
        btnResetTable.setPreferredSize(new java.awt.Dimension(75, 36));
        btnResetTable.addActionListener(e -> {
            txtFind.setText("");
            fillToTable();
        });

        pnlSearchBox.add(txtFind);
        pnlSearchBox.add(btnFind);
        pnlSearchBox.add(btnResetTable);

        btnThemMoiQuick = new javax.swing.JButton("+ THÊM MÁY MỚI");
        Style_Net.stylePrimaryButton(btnThemMoiQuick);
        btnThemMoiQuick.setPreferredSize(new java.awt.Dimension(150, 36));
        btnThemMoiQuick.addActionListener(e -> {
            clear();
            txtName.requestFocus();
        });

        pnlTableToolbar.add(pnlSearchBox, java.awt.BorderLayout.WEST);
        pnlTableToolbar.add(btnThemMoiQuick, java.awt.BorderLayout.EAST);
        pnlLeftCard.add(pnlTableToolbar, java.awt.BorderLayout.NORTH);

        // Modern Table
        Style_Net.styleTable(tblQLMT);
        tblQLMT.setRowHeight(40);
        jScrollPane2.setViewportView(tblQLMT);
        jScrollPane2.setBorder(new javax.swing.border.LineBorder(Style_Net.BORDER_HAIRLINE, 1, true));
        pnlLeftCard.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnlCenter.add(pnlLeftCard, java.awt.BorderLayout.CENTER);

        // --- RIGHT CARD (Form Area) ---
        javax.swing.JPanel pnlRightCard = Style_Net.createCardPanel();
        pnlRightCard.setPreferredSize(new java.awt.Dimension(340, 560));
        pnlRightCard.setLayout(new java.awt.BorderLayout(0, 16));

        // Form Header
        javax.swing.JPanel pnlFormHeader = new javax.swing.JPanel();
        pnlFormHeader.setLayout(new javax.swing.BoxLayout(pnlFormHeader, javax.swing.BoxLayout.Y_AXIS));
        pnlFormHeader.setOpaque(false);
        javax.swing.JLabel lblFormTitle = new javax.swing.JLabel("THÔNG TIN MÁY TRẠM");
        lblFormTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lblFormTitle.setForeground(Style_Net.NAVY_PRIMARY);
        lblFormTitle.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        javax.swing.JLabel lblFormDesc = new javax.swing.JLabel("Chỉnh sửa thông số đơn giá và trạng thái máy");
        lblFormDesc.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        lblFormDesc.setForeground(Style_Net.TEXT_MUTED);
        lblFormDesc.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        pnlFormHeader.add(lblFormTitle);
        pnlFormHeader.add(javax.swing.Box.createVerticalStrut(4));
        pnlFormHeader.add(lblFormDesc);
        pnlRightCard.add(pnlFormHeader, java.awt.BorderLayout.NORTH);

        // Form Fields (Vertical Box)
        javax.swing.JPanel pnlFormBody = new javax.swing.JPanel();
        pnlFormBody.setLayout(new javax.swing.BoxLayout(pnlFormBody, javax.swing.BoxLayout.Y_AXIS));
        pnlFormBody.setOpaque(false);

        // Field 1: Mã máy
        javax.swing.JLabel lblF1 = new javax.swing.JLabel("MÃ MÁY (HỆ THỐNG TỰ CẤP)");
        lblF1.setFont(Style_Net.FONT_LABEL);
        lblF1.setForeground(Style_Net.TEXT_MUTED);
        lblF1.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        txtMaMayDisplay = new javax.swing.JTextField();
        txtMaMayDisplay.setEditable(false);
        txtMaMayDisplay.setBackground(new java.awt.Color(0xF1, 0xF5, 0xF9));
        Style_Net.styleTextField(txtMaMayDisplay);
        txtMaMayDisplay.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        txtMaMayDisplay.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 2: Tên máy
        javax.swing.JLabel lblF2 = new javax.swing.JLabel("TÊN HIỂN THỊ CỦA MÁY");
        lblF2.setFont(Style_Net.FONT_LABEL);
        lblF2.setForeground(Style_Net.TEXT_MUTED);
        lblF2.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        Style_Net.styleTextField(txtName);
        txtName.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        txtName.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 3: Phân loại khu vực
        javax.swing.JLabel lblF3 = new javax.swing.JLabel("PHÂN LOẠI KHU VỰC / PHÒNG");
        lblF3.setFont(Style_Net.FONT_LABEL);
        lblF3.setForeground(Style_Net.TEXT_MUTED);
        lblF3.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        cboKhuVuc = new javax.swing.JComboBox<>(new String[]{"Dàn máy thường", "Phòng máy VIP", "Phòng Thi Đấu"});
        cboKhuVuc.setFont(Style_Net.FONT_BODY);
        cboKhuVuc.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        cboKhuVuc.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 4: Đơn giá
        javax.swing.JLabel lblF4 = new javax.swing.JLabel("ĐƠN GIÁ CƯỚC (₫ / GIỜ)");
        lblF4.setFont(Style_Net.FONT_LABEL);
        lblF4.setForeground(Style_Net.TEXT_MUTED);
        lblF4.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        Style_Net.styleTextField(txtGia);
        txtGia.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        txtGia.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 5: Trạng thái
        javax.swing.JLabel lblF5 = new javax.swing.JLabel("TRẠNG THÁI HIỆN TẠI");
        lblF5.setFont(Style_Net.FONT_LABEL);
        lblF5.setForeground(Style_Net.TEXT_MUTED);
        lblF5.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        cboTrangThai.setFont(Style_Net.FONT_BODY);
        cboTrangThai.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        cboTrangThai.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        pnlFormBody.add(lblF1);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(4));
        pnlFormBody.add(txtMaMayDisplay);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(12));

        pnlFormBody.add(lblF2);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(4));
        pnlFormBody.add(txtName);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(12));

        pnlFormBody.add(lblF3);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(4));
        pnlFormBody.add(cboKhuVuc);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(12));

        pnlFormBody.add(lblF4);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(4));
        pnlFormBody.add(txtGia);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(12));

        pnlFormBody.add(lblF5);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(4));
        pnlFormBody.add(cboTrangThai);
        pnlFormBody.add(javax.swing.Box.createVerticalGlue());

        pnlRightCard.add(pnlFormBody, java.awt.BorderLayout.CENTER);

        // Form Footer: Action Buttons Stack
        javax.swing.JPanel pnlFormButtons = new javax.swing.JPanel();
        pnlFormButtons.setLayout(new java.awt.GridLayout(3, 1, 0, 8));
        pnlFormButtons.setOpaque(false);

        btnCapNhat.setText("LƯU THAY ĐỔI CẤU HÌNH");
        Style_Net.stylePrimaryButton(btnCapNhat);
        btnCapNhat.setPreferredSize(new java.awt.Dimension(300, 38));

        btnLamMoi.setText("LÀM MỚI FORM");
        Style_Net.styleSecondaryButton(btnLamMoi);
        btnLamMoi.setPreferredSize(new java.awt.Dimension(300, 36));

        btnXoa.setText("XÓA MÁY TRẠM NÀY");
        Style_Net.styleDangerButton(btnXoa);
        btnXoa.setPreferredSize(new java.awt.Dimension(300, 36));

        pnlFormButtons.add(btnCapNhat);
        pnlFormButtons.add(btnLamMoi);
        pnlFormButtons.add(btnXoa);

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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblMaMay = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQLMT = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Giao diện quản lý máy tính");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Tên Máy Tính ");

        txtName.setBackground(new java.awt.Color(204, 204, 204));
        txtName.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Giá theo giờ");

        txtGia.setBackground(new java.awt.Color(204, 204, 204));
        txtGia.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Mã Máy Tính ");

        lblMaMay.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lblMaMay.setText(".................");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Trạng thái");

        cboTrangThai.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động ", "Trống", "Bảo trì" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(193, 189, 189));
        btnLamMoi.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(102, 102, 102));
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(22, 163, 74));
        btnThem.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(233, 78, 60));
        btnCapNhat.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Sửa");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(233, 78, 60));
        btnXoa.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(13, 71, 161));

        tblQLMT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã máy", "Tên máy", "Giá theo giờ", "Trạng thái"
            }
        ));
        tblQLMT.setName(""); // NOI18N
        tblQLMT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLMTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQLMT);

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Danh sách máy");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tìm kiếm");

        btnFind.setText("Tìm kiếm");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFind))
                    .addComponent(jLabel6))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaMay, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtName)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaMay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(150, 150, 150))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, lblMaMay});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCapNhat, btnLamMoi, btnThem, btnXoa, cboTrangThai});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 930, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        int hang = tblQLMT.getRowCount();
        String tenNhap = txtName.getText().trim();
        boolean trungTen = false;

        for (int i = 0; i < hang; i++) {
            String tenMayTrongBang = tblQLMT.getValueAt(i, 1).toString().trim();
            if (tenNhap.equalsIgnoreCase(tenMayTrongBang)) {
                trungTen = true;
                break;
            }
        }

        if (trungTen) {
            XDialog.alert("Tên máy " + tenNhap + "đã tồn tại trong bảng!");
            return;
        }

        create();
        clear();

    }//GEN-LAST:event_btnThemActionPerformed

    private void tblQLMTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLMTMouseClicked
        // TODO add your handling code here:
        int index = tblQLMT.getSelectedRow();
        if (index >= 0) {
            fillTXT(index);
        }
    }//GEN-LAST:event_tblQLMTMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        clear();
        fillToTable();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (lblMaMay.getText().trim().isEmpty() || lblMaMay.getText().contains("Tự động")) {
            btnThemActionPerformed(evt);
            return;
        }
        int hang = tblQLMT.getRowCount();
        String tenNhap = txtName.getText().trim();
        boolean trungTen = false;
        for (int i = 0; i < hang; i++) {
            if (i == tblQLMT.getSelectedRow()) {
                continue;
            }
            String tenMayTrongBang = tblQLMT.getValueAt(i, 1).toString().trim();
            if (tenNhap.equalsIgnoreCase(tenMayTrongBang)) {
                trungTen = true;
                break;
            }
        }
        if (trungTen) {
            XDialog.alert("Tên máy " + tenNhap + " đã tồn tại trong bảng!");
            return;
        }

        update();
        clear();
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (tblQLMT.getSelectedRow() < 0) {
            XDialog.alert("Vui lòng chọn máy bạn muốn xóa");
            return;
        }
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        String finname = txtFind.getText();
        findName(finname);
    }//GEN-LAST:event_btnFindActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(QuanLyMayTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuanLyMayTinh dialog = new QuanLyMayTinh(new javax.swing.JFrame(), true);
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

    public void fillTXT(int row) {
        if (row < 0 || row >= tblQLMT.getRowCount()) {
            return;
        }
        Object Id = tblQLMT.getValueAt(row, 0);
        Object TenMay = tblQLMT.getValueAt(row, 1);
        Object KhuVuc = tblQLMT.getValueAt(row, 2);
        Object TrangThai = tblQLMT.getValueAt(row, 4);

        String idStr = Id != null ? Id.toString() : "";
        lblMaMay.setText(idStr);
        if (txtMaMayDisplay != null) {
            txtMaMayDisplay.setText(idStr);
        }
        txtName.setText(TenMay != null ? TenMay.toString() : "");
        if (KhuVuc != null && cboKhuVuc != null) {
            cboKhuVuc.setSelectedItem(KhuVuc.toString());
        }

        for (MayTinh mt : list) {
            if (mt.getId().equals(idStr)) {
                txtGia.setText(String.valueOf((long)mt.getGiaTheoGio()));
                break;
            }
        }

        if (TrangThai != null) {
            String trangThai = TrangThai.toString().trim();
            if (trangThai.equalsIgnoreCase("Hoạt động") || trangThai.equalsIgnoreCase("Đang dùng")) {
                cboTrangThai.setSelectedIndex(0);
            } else if (trangThai.equalsIgnoreCase("Trống")) {
                cboTrangThai.setSelectedIndex(1);
            } else if (trangThai.equalsIgnoreCase("Bảo trì")) {
                cboTrangThai.setSelectedIndex(2);
            } else {
                cboTrangThai.setSelectedIndex(0);
            }
        }
        if (btnCapNhat != null) {
            btnCapNhat.setText("✓ LƯU THAY ĐỔI CẤU HÌNH");
        }
        if (lblHeaderSummary != null) {
            lblHeaderSummary.setText("Hệ thống: " + list.size() + " máy trạm • Đang chọn sửa: " + idStr + " (" + txtName.getText() + ")");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblMaMay;
    private javax.swing.JTable tblQLMT;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    @Override
    public void open() {
        setLocationRelativeTo(null);
        fillToTable();
    }

    @Override
    public void setForm(MayTinh mt) {
        txtName.setText(mt.getTenMay());
        if (mt.getTrangThai() != null) {
            cboTrangThai.setSelectedItem(mt.getTrangThai().trim());
        } else {
            cboTrangThai.setSelectedIndex(0);
        }
    }

    @Override
    public MayTinh getForm() {
        MayTinh entity = new MayTinh();
        entity.setTenMay(txtName.getText().trim());
        entity.setGiaTheoGio(Float.parseFloat(txtGia.getText().trim()));
        entity.setTrangThai("Trống");
        return entity;
    }

    public MayTinh getFormByUp() {
        MayTinh entity = new MayTinh();
        entity.setId(lblMaMay.getText().trim());
        entity.setTenMay(txtName.getText().trim());
        entity.setGiaTheoGio(Float.parseFloat(txtGia.getText().trim()));
        entity.setTrangThai(cboTrangThai.getSelectedItem().toString());
        return entity;
    }

    @Override
    public void fillToTable() {
        DefaultTableModel model = new DefaultTableModel(
            new String[] { "MÃ MÁY", "TÊN MÁY", "PHÂN LOẠI PHÒNG", "ĐƠN GIÁ / GIỜ", "TRẠNG THÁI" }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblQLMT.setModel(model);
        list = dao.findAll();
        for (MayTinh item : list) {
            String room = "Dàn máy thường";
            if (item.getTenMay() != null) {
                String u = item.getTenMay().toUpperCase();
                if (u.contains("VIP")) room = "Phòng máy VIP";
                else if (u.contains("THI ĐẤU") || u.contains("THI DAU")) room = "Phòng Thi Đấu";
            }
            model.addRow(new Object[]{
                item.getId(),
                item.getTenMay(),
                room,
                Style_Net.formatMoney(item.getGiaTheoGio()) + " / giờ",
                item.getTrangThai()
            });
        }
        setupQLMTRenderers();
        if (lblHeaderSummary != null) {
            lblHeaderSummary.setText("Hệ thống: " + list.size() + " máy trạm • Sẵn sàng");
        }
    }

    private void setupQLMTRenderers() {
        if (tblQLMT.getColumnCount() >= 5) {
            tblQLMT.getColumnModel().getColumn(4).setCellRenderer((table, value, isSelected, hasFocus, row, col) -> {
                String st = value != null ? value.toString().trim() : "";
                javax.swing.JPanel pnl = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 7));
                pnl.setBackground(isSelected ? table.getSelectionBackground() : java.awt.Color.WHITE);
                if (st.equalsIgnoreCase("Trống")) {
                    pnl.add(Style_Net.createBadge("TRỐNG", new java.awt.Color(0xEC, 0xFD, 0xF5), new java.awt.Color(0x05, 0x96, 0x69)));
                } else if (st.equalsIgnoreCase("Bảo trì")) {
                    pnl.add(Style_Net.createBadge("BẢO TRÌ", new java.awt.Color(0xF1, 0xF5, 0xF9), new java.awt.Color(0x64, 0x74, 0x8B)));
                } else {
                    pnl.add(Style_Net.createBadge("ĐANG DÙNG", new java.awt.Color(0xEF, 0xF6, 0xFF), new java.awt.Color(0x1D, 0x4E, 0xD8)));
                }
                return pnl;
            });
        }
    }

    @Override
    public void edit() {
    }

    @Override
    public void create() {
        String tenMay = txtName.getText().trim();
        if (tenMay.isEmpty()) {
            XDialog.alert("Vui lòng nhập tên máy");
            return;
        }

        String giaStr = txtGia.getText().trim();
        if (giaStr.isEmpty()) {
            XDialog.alert("Vui lòng nhập giá theo giờ");
            return;
        }

        try {
            double gia = Double.parseDouble(giaStr);
            if (gia <= 0) {
                XDialog.alert("Giá theo giờ phải lớn hơn 0");
                return;
            }
        } catch (NumberFormatException e) {
            XDialog.alert("Giá theo giờ phải là số hợp lệ");
            return;
        }

        MayTinh mt = this.getForm();
        dao.create(mt);
        this.fillToTable();
        JOptionPane.showMessageDialog(this, "Thêm máy thành công!");
    }

    @Override
    public void update() {
        if (lblMaMay.getText().trim().isEmpty() || lblMaMay.getText().contains("Tự động")) {
            XDialog.alert("Vui lòng chọn máy bạn muốn sửa");
            return;
        }

        String tenMay = txtName.getText().trim();
        if (tenMay.isEmpty()) {
            XDialog.alert("Vui lòng nhập tên máy");
            return;
        }

        String giaStr = txtGia.getText().trim();
        if (giaStr.isEmpty()) {
            XDialog.alert("Vui lòng nhập giá theo giờ");
            return;
        }

        try {
            double gia = Double.parseDouble(giaStr);
            if (gia <= 0) {
                XDialog.alert("Giá theo giờ phải lớn hơn 0");
                return;
            }
        } catch (NumberFormatException e) {
            XDialog.alert("Giá theo giờ phải là số hợp lệ");
            return;
        }

        MayTinh mt = this.getFormByUp();
        dao.update(mt);
        this.fillToTable();
        JOptionPane.showMessageDialog(this, "Cập nhật thông số máy thành công!");
    }

    @Override
    public void delete() {
        String id = lblMaMay.getText().trim();

        if (id.isEmpty()) {
            XDialog.alert("Vui lòng chọn máy bạn muốn xóa");
            return;
        }

        if (!XDialog.confirm("Bạn có chắc chắn muốn xóa máy " + id + " không?")) {
            return;
        }

        try {
            dao.deleteByID(id);
            fillToTable();
            clear();
            JOptionPane.showMessageDialog(this, "Xóa máy thành công!");
        } catch (Exception e) {
            XDialog.alert("Xóa thất bại! " + e.getMessage());
        }
    }

    @Override
    public void clear() {
        lblMaMay.setText("");
        if (txtMaMayDisplay != null) {
            txtMaMayDisplay.setText("(Tự động cấp khi lưu)");
        }
        txtName.setText("");
        txtGia.setText("");
        if (cboKhuVuc != null) cboKhuVuc.setSelectedIndex(0);
        cboTrangThai.setSelectedIndex(1);
        if (btnCapNhat != null) {
            btnCapNhat.setText("+ THÊM VÀO HỆ THỐNG");
        }
        if (lblHeaderSummary != null) {
            lblHeaderSummary.setText("Hệ thống: " + list.size() + " máy trạm • Thêm máy mới");
        }
    }

    @Override
    public void setEditable(boolean editable) {

    }

    public void findName(String name) {
        String tenmay = txtFind.getText().trim();
        if (tenmay.isEmpty()) {
            fillToTable();
            return;
        }
        DefaultTableModel model = new DefaultTableModel(
            new String[] { "MÃ MÁY", "TÊN MÁY", "PHÂN LOẠI PHÒNG", "ĐƠN GIÁ / GIỜ", "TRẠNG THÁI" }, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblQLMT.setModel(model);

        for (MayTinh item : list) {
            if (item.getTenMay().toLowerCase().contains(tenmay.toLowerCase()) ||
                item.getId().toLowerCase().contains(tenmay.toLowerCase())) {
                String room = "Dàn máy thường";
                if (item.getTenMay() != null) {
                    String u = item.getTenMay().toUpperCase();
                    if (u.contains("VIP")) room = "Phòng máy VIP";
                    else if (u.contains("THI ĐẤU") || u.contains("THI DAU")) room = "Phòng Thi Đấu";
                }
                model.addRow(new Object[]{
                    item.getId(),
                    item.getTenMay(),
                    room,
                    Style_Net.formatMoney(item.getGiaTheoGio()) + " / giờ",
                    item.getTrangThai()
                });
            }
        }
        setupQLMTRenderers();
    }

}
