/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui.manager;

import daoImpl.MonAnDAOImpl;
import entity.MonAn;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import util.XDialog;
import dao.MonAnDAO;
import controller.MenuController;
import controller.QuanlyThucDonController;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import util.Style_Net;

/**
 *
 * @author Admin
 */
public class QuanLyThucDonJDialog extends javax.swing.JDialog implements QuanlyThucDonController {

    /**
     * Creates new form QuanLyThucDonJDialog
     */
    MonAnDAO dao = new MonAnDAOImpl();
    List<MonAn> items = List.of();
    private String DuongDanAnh;

    private javax.swing.JLabel lblTongSoMon;
    private javax.swing.JLabel lblConHang;
    private javax.swing.JTextField txtMaMonDisplay;
    private javax.swing.JComboBox<String> cboDanhMuc;
    private javax.swing.JComboBox<String> cboTinhTrang;
    private javax.swing.JLabel lblImageName;
    private javax.swing.JButton btnTabAll;
    private javax.swing.JButton btnTabFood;
    private javax.swing.JButton btnTabDrink;
    private javax.swing.JButton btnTabSnack;
    private String currentCategoryFilter = "ALL";

    public QuanLyThucDonJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buildModernQLTDLayout();
        setLocationRelativeTo(null);
        open();
    }

    private void buildModernQLTDLayout() {
        setTitle("NET-MANAGER - Quản Lý Thực Đơn F&B");
        setSize(1180, 720);
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
        javax.swing.JLabel lblSubTitle = new javax.swing.JLabel("Quản lý Thực Đơn F&B");
        lblSubTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblSubTitle.setForeground(Style_Net.NAVY_PRIMARY);
        pnlTitleGroup.add(lblBrand);
        pnlTitleGroup.add(new javax.swing.JLabel("  "));
        pnlTitleGroup.add(lblSubTitle);

        javax.swing.JPanel pnlPillsGroup = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));
        pnlPillsGroup.setOpaque(false);
        lblTongSoMon = Style_Net.createBadge("Tổng số: 0 món", new java.awt.Color(0xF1, 0xF5, 0xF9), Style_Net.NAVY_PRIMARY);
        lblConHang = Style_Net.createBadge("Còn hàng: 0 món", new java.awt.Color(0xDC, 0xFC, 0xE7), new java.awt.Color(0x16, 0x65, 0x34));
        pnlPillsGroup.add(lblTongSoMon);
        pnlPillsGroup.add(lblConHang);

        pnlTopHeader.add(pnlTitleGroup, java.awt.BorderLayout.WEST);
        pnlTopHeader.add(pnlPillsGroup, java.awt.BorderLayout.EAST);
        getContentPane().add(pnlTopHeader, java.awt.BorderLayout.NORTH);

        // 2. CENTER CONTENT (Two-column SaaS layout)
        javax.swing.JPanel pnlCenter = new javax.swing.JPanel(new java.awt.BorderLayout(16, 0));
        pnlCenter.setOpaque(false);

        // --- LEFT FORM CARD (width ~380px) ---
        javax.swing.JPanel pnlLeftCard = Style_Net.createCardPanel();
        pnlLeftCard.setPreferredSize(new java.awt.Dimension(390, 600));
        pnlLeftCard.setLayout(new java.awt.BorderLayout(0, 12));

        javax.swing.JLabel lblFormHeader = new javax.swing.JLabel("THÔNG TIN MÓN & ĐỒ UỐNG");
        lblFormHeader.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lblFormHeader.setForeground(Style_Net.NAVY_PRIMARY);
        pnlLeftCard.add(lblFormHeader, java.awt.BorderLayout.NORTH);

        // Form Fields (Vertical Box)
        javax.swing.JPanel pnlFormBody = new javax.swing.JPanel();
        pnlFormBody.setLayout(new javax.swing.BoxLayout(pnlFormBody, javax.swing.BoxLayout.Y_AXIS));
        pnlFormBody.setOpaque(false);

        // Field 1: Mã món
        javax.swing.JLabel lblF1 = new javax.swing.JLabel("Mã món");
        lblF1.setFont(Style_Net.FONT_LABEL);
        lblF1.setForeground(Style_Net.TEXT_MUTED);
        lblF1.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        txtMaMonDisplay = new javax.swing.JTextField();
        txtMaMonDisplay.setEditable(false);
        txtMaMonDisplay.setBackground(new java.awt.Color(0xF1, 0xF5, 0xF9));
        Style_Net.styleTextField(txtMaMonDisplay);
        txtMaMonDisplay.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        txtMaMonDisplay.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 2: Tên món
        javax.swing.JLabel lblF2 = new javax.swing.JLabel("Tên món / Đồ uống");
        lblF2.setFont(Style_Net.FONT_LABEL);
        lblF2.setForeground(Style_Net.TEXT_MUTED);
        lblF2.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        Style_Net.styleTextField(txtName);
        txtName.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        txtName.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 3: Phân loại danh mục
        javax.swing.JLabel lblF3 = new javax.swing.JLabel("Phân loại danh mục");
        lblF3.setFont(Style_Net.FONT_LABEL);
        lblF3.setForeground(Style_Net.TEXT_MUTED);
        lblF3.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        cboDanhMuc = new javax.swing.JComboBox<>(new String[]{"Đồ ăn chính (Mì / Cơm)", "Nước giải khát", "Snack / Ăn vặt"});
        cboDanhMuc.setFont(Style_Net.FONT_BODY);
        cboDanhMuc.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        cboDanhMuc.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 4 & 5: Đơn giá và Tồn kho (Side-by-side)
        javax.swing.JPanel pnlPriceStock = new javax.swing.JPanel(new java.awt.GridLayout(1, 2, 10, 0));
        pnlPriceStock.setOpaque(false);
        pnlPriceStock.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 58));
        pnlPriceStock.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        javax.swing.JPanel pnlP = new javax.swing.JPanel();
        pnlP.setLayout(new javax.swing.BoxLayout(pnlP, javax.swing.BoxLayout.Y_AXIS));
        pnlP.setOpaque(false);
        javax.swing.JLabel lblFP = new javax.swing.JLabel("Đơn giá bán (VNĐ)");
        lblFP.setFont(Style_Net.FONT_LABEL);
        lblFP.setForeground(Style_Net.TEXT_MUTED);
        Style_Net.styleTextField(txtGia);
        txtGia.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        pnlP.add(lblFP);
        pnlP.add(javax.swing.Box.createVerticalStrut(4));
        pnlP.add(txtGia);

        javax.swing.JPanel pnlS = new javax.swing.JPanel();
        pnlS.setLayout(new javax.swing.BoxLayout(pnlS, javax.swing.BoxLayout.Y_AXIS));
        pnlS.setOpaque(false);
        javax.swing.JLabel lblFS = new javax.swing.JLabel("Tồn kho khả dụng");
        lblFS.setFont(Style_Net.FONT_LABEL);
        lblFS.setForeground(Style_Net.TEXT_MUTED);
        Style_Net.styleTextField(txtSoLuong);
        txtSoLuong.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        pnlS.add(lblFS);
        pnlS.add(javax.swing.Box.createVerticalStrut(4));
        pnlS.add(txtSoLuong);

        pnlPriceStock.add(pnlP);
        pnlPriceStock.add(pnlS);

        // Field 6: Tình trạng phục vụ
        javax.swing.JLabel lblF6 = new javax.swing.JLabel("Tình trạng phục vụ");
        lblF6.setFont(Style_Net.FONT_LABEL);
        lblF6.setForeground(Style_Net.TEXT_MUTED);
        lblF6.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
        cboTinhTrang = new javax.swing.JComboBox<>(new String[]{"Đang phục vụ (Còn hàng)", "Tạm ngưng phục vụ"});
        cboTinhTrang.setFont(Style_Net.FONT_BODY);
        cboTinhTrang.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 36));
        cboTinhTrang.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        // Field 7: Hình ảnh đại diện
        javax.swing.JLabel lblF7 = new javax.swing.JLabel("Hình ảnh đại diện");
        lblF7.setFont(Style_Net.FONT_LABEL);
        lblF7.setForeground(Style_Net.TEXT_MUTED);
        lblF7.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        javax.swing.JPanel pnlImageUpload = new javax.swing.JPanel(new java.awt.BorderLayout(10, 0));
        pnlImageUpload.setBackground(new java.awt.Color(0xFA, 0xFA, 0xFA));
        pnlImageUpload.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(Style_Net.BORDER_HAIRLINE, 1, true),
            new javax.swing.border.EmptyBorder(8, 8, 8, 8)
        ));
        pnlImageUpload.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 74));
        pnlImageUpload.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

        lblHinhAnh.setPreferredSize(new java.awt.Dimension(58, 58));
        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setBorder(new javax.swing.border.LineBorder(Style_Net.BORDER_HAIRLINE, 1, true));
        lblHinhAnh.setOpaque(true);
        lblHinhAnh.setBackground(java.awt.Color.WHITE);

        javax.swing.JPanel pnlImgInfo = new javax.swing.JPanel();
        pnlImgInfo.setLayout(new javax.swing.BoxLayout(pnlImgInfo, javax.swing.BoxLayout.Y_AXIS));
        pnlImgInfo.setOpaque(false);
        lblImageName = new javax.swing.JLabel("chua_chon_anh.jpg");
        lblImageName.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        lblImageName.setForeground(Style_Net.TEXT_MAIN);
        javax.swing.JLabel lblImgSub = new javax.swing.JLabel("JPG/PNG dưới 2MB");
        lblImgSub.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
        lblImgSub.setForeground(Style_Net.TEXT_MUTED);
        pnlImgInfo.add(lblImageName);
        pnlImgInfo.add(javax.swing.Box.createVerticalStrut(2));
        pnlImgInfo.add(lblImgSub);

        Style_Net.styleSecondaryButton(btnHinhANH);
        btnHinhANH.setText("Chọn ảnh mới");
        btnHinhANH.setPreferredSize(new java.awt.Dimension(110, 32));

        pnlImageUpload.add(lblHinhAnh, java.awt.BorderLayout.WEST);
        pnlImageUpload.add(pnlImgInfo, java.awt.BorderLayout.CENTER);
        pnlImageUpload.add(btnHinhANH, java.awt.BorderLayout.EAST);

        pnlFormBody.add(lblF1);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(3));
        pnlFormBody.add(txtMaMonDisplay);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(8));

        pnlFormBody.add(lblF2);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(3));
        pnlFormBody.add(txtName);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(8));

        pnlFormBody.add(lblF3);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(3));
        pnlFormBody.add(cboDanhMuc);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(8));

        pnlFormBody.add(pnlPriceStock);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(8));

        pnlFormBody.add(lblF6);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(3));
        pnlFormBody.add(cboTinhTrang);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(8));

        pnlFormBody.add(lblF7);
        pnlFormBody.add(javax.swing.Box.createVerticalStrut(3));
        pnlFormBody.add(pnlImageUpload);
        pnlFormBody.add(javax.swing.Box.createVerticalGlue());

        pnlLeftCard.add(pnlFormBody, java.awt.BorderLayout.CENTER);

        // Action Buttons Grid (2x2)
        javax.swing.JPanel pnlActions = new javax.swing.JPanel(new java.awt.GridLayout(2, 2, 8, 8));
        pnlActions.setOpaque(false);

        btnUp.setText("Lưu cập nhật");
        Style_Net.stylePrimaryButton(btnUp);
        btnUp.setPreferredSize(new java.awt.Dimension(0, 38));

        btnCre.setText("+ Thêm món mới");
        Style_Net.stylePrimaryButton(btnCre);
        btnCre.setBackground(Style_Net.NAVY_ACCENT);
        btnCre.setPreferredSize(new java.awt.Dimension(0, 38));

        btnDe.setText("Xóa món");
        Style_Net.styleDangerButton(btnDe);
        btnDe.setPreferredSize(new java.awt.Dimension(0, 36));

        btnCle.setText("Làm mới form");
        Style_Net.styleSecondaryButton(btnCle);
        btnCle.setPreferredSize(new java.awt.Dimension(0, 36));

        pnlActions.add(btnUp);
        pnlActions.add(btnCre);
        pnlActions.add(btnDe);
        pnlActions.add(btnCle);

        pnlLeftCard.add(pnlActions, java.awt.BorderLayout.SOUTH);
        pnlCenter.add(pnlLeftCard, java.awt.BorderLayout.WEST);

        // --- RIGHT TABLE CARD ---
        javax.swing.JPanel pnlRightCard = Style_Net.createCardPanel();
        pnlRightCard.setLayout(new java.awt.BorderLayout(0, 12));

        // Top category pills & search
        javax.swing.JPanel pnlRightTop = new javax.swing.JPanel(new java.awt.BorderLayout(10, 0));
        pnlRightTop.setOpaque(false);

        javax.swing.JPanel pnlTabs = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 0));
        pnlTabs.setOpaque(false);

        btnTabAll = new javax.swing.JButton("Tất cả (0)");
        btnTabFood = new javax.swing.JButton("Đồ ăn (0)");
        btnTabDrink = new javax.swing.JButton("Đồ uống (0)");
        btnTabSnack = new javax.swing.JButton("Snack (0)");

        java.awt.event.ActionListener tabListener = e -> {
            Object src = e.getSource();
            if (src == btnTabFood) currentCategoryFilter = "FOOD";
            else if (src == btnTabDrink) currentCategoryFilter = "DRINK";
            else if (src == btnTabSnack) currentCategoryFilter = "SNACK";
            else currentCategoryFilter = "ALL";
            applyCategoryStyle();
            filterTableData();
        };

        btnTabAll.addActionListener(tabListener);
        btnTabFood.addActionListener(tabListener);
        btnTabDrink.addActionListener(tabListener);
        btnTabSnack.addActionListener(tabListener);

        applyCategoryStyle();

        pnlTabs.add(btnTabAll);
        pnlTabs.add(btnTabFood);
        pnlTabs.add(btnTabDrink);
        pnlTabs.add(btnTabSnack);

        javax.swing.JPanel pnlSearch = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 0));
        pnlSearch.setOpaque(false);
        txtFinid.setPreferredSize(new java.awt.Dimension(200, 36));
        Style_Net.styleTextField(txtFinid);
        txtFinid.putClientProperty("JTextField.placeholderText", "Tìm tên món hoặc mã...");
        txtFinid.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                filterTableData();
            }
        });

        Style_Net.styleSecondaryButton(btnFindId);
        btnFindId.setText("Tìm");
        btnFindId.setPreferredSize(new java.awt.Dimension(65, 36));

        pnlSearch.add(txtFinid);
        pnlSearch.add(btnFindId);

        pnlRightTop.add(pnlTabs, java.awt.BorderLayout.WEST);
        pnlRightTop.add(pnlSearch, java.awt.BorderLayout.EAST);
        pnlRightCard.add(pnlRightTop, java.awt.BorderLayout.NORTH);

        // Modern Table
        Style_Net.styleTable(tblOrderManager);
        tblOrderManager.setRowHeight(40);
        jScrollPane1.setViewportView(tblOrderManager);
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(Style_Net.BORDER_HAIRLINE, 1, true));
        pnlRightCard.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlCenter.add(pnlRightCard, java.awt.BorderLayout.CENTER);
        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);
    }

    private void applyCategoryStyle() {
        javax.swing.JButton[] tabs = {btnTabAll, btnTabFood, btnTabDrink, btnTabSnack};
        String[] keys = {"ALL", "FOOD", "DRINK", "SNACK"};
        for (int i = 0; i < tabs.length; i++) {
            if (tabs[i] == null) continue;
            boolean active = keys[i].equals(currentCategoryFilter);
            if (active) {
                tabs[i].setBackground(Style_Net.NAVY_PRIMARY);
                tabs[i].setForeground(java.awt.Color.WHITE);
                tabs[i].setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
                tabs[i].setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    new javax.swing.border.LineBorder(Style_Net.NAVY_PRIMARY, 1, true),
                    new javax.swing.border.EmptyBorder(6, 14, 6, 14)
                ));
            } else {
                tabs[i].setBackground(java.awt.Color.WHITE);
                tabs[i].setForeground(Style_Net.TEXT_MAIN);
                tabs[i].setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
                tabs[i].setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    new javax.swing.border.LineBorder(Style_Net.BORDER_HAIRLINE, 1, true),
                    new javax.swing.border.EmptyBorder(6, 14, 6, 14)
                ));
            }
            tabs[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnCre = new javax.swing.JButton();
        btnUp = new javax.swing.JButton();
        btnDe = new javax.swing.JButton();
        btnCle = new javax.swing.JButton();
        lblMaMon = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        btnHinhANH = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrderManager = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnFindId = new javax.swing.JButton();
        txtFinid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Giao diện quản lý thực đơn");

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel3.setText("Id");

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel4.setText("Tên");

        txtName.setBackground(new java.awt.Color(204, 204, 204));
        txtName.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtGia.setBackground(new java.awt.Color(204, 204, 204));
        txtGia.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel5.setText("Đơn giá");

        btnCre.setBackground(new java.awt.Color(22, 163, 74));
        btnCre.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        btnCre.setForeground(new java.awt.Color(255, 255, 255));
        btnCre.setText("Thêm");
        btnCre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreActionPerformed(evt);
            }
        });

        btnUp.setBackground(new java.awt.Color(233, 78, 60));
        btnUp.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        btnUp.setForeground(new java.awt.Color(255, 255, 255));
        btnUp.setText("Sửa");
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpActionPerformed(evt);
            }
        });

        btnDe.setBackground(new java.awt.Color(233, 78, 60));
        btnDe.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        btnDe.setForeground(new java.awt.Color(255, 255, 255));
        btnDe.setText("Xóa");
        btnDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeActionPerformed(evt);
            }
        });

        btnCle.setBackground(new java.awt.Color(193, 189, 189));
        btnCle.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        btnCle.setText("Làm mới");
        btnCle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleActionPerformed(evt);
            }
        });

        lblMaMon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaMon.setText("............");

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel6.setText("Số Lượng");

        txtSoLuong.setBackground(new java.awt.Color(204, 204, 204));
        txtSoLuong.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
        );

        btnHinhANH.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        btnHinhANH.setText("Ảnh");
        btnHinhANH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHinhANHActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(13, 71, 161));

        tblOrderManager.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        tblOrderManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Tên", "Đơn giá", "Số Lượng", "Anh"
            }
        ));
        tblOrderManager.setName(""); // NOI18N
        tblOrderManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderManagerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrderManager);

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Danh sách thực đơn");

        btnFindId.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btnFindId.setText("Tìm kiếm");
        btnFindId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindIdActionPerformed(evt);
            }
        });

        txtFinid.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtFinid, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFindId)))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFindId)
                    .addComponent(txtFinid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(141, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblMaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtName)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCre, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUp)
                                .addGap(18, 18, 18)
                                .addComponent(btnDe)
                                .addGap(18, 18, 18)
                                .addComponent(btnCle, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHinhANH)
                        .addGap(133, 133, 133))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCre, btnDe, btnUp});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHinhANH)
                        .addGap(39, 39, 39)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUp)
                    .addComponent(btnCre, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDe)
                    .addComponent(btnCle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCle, btnCre, btnDe, btnUp});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtGia, txtName});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed
        // TODO add your handling code here:
        if (lblMaMon.getText().trim().isEmpty()) {
            XDialog.alert("Vui lòng chọn món ăn cần cập nhật trước!");
            return;
        }
        update();

    }//GEN-LAST:event_btnUpActionPerformed

    private void btnCreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_btnCreActionPerformed

    private void btnDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnDeActionPerformed

    private void btnCleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnCleActionPerformed

    private void btnFindIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindIdActionPerformed
        // TODO add your handling code here:
        finbyid();
    }//GEN-LAST:event_btnFindIdActionPerformed

    private void tblOrderManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderManagerMouseClicked
        // TODO add your handling code here:
        int row = tblOrderManager.rowAtPoint(evt.getPoint());
        int col = tblOrderManager.columnAtPoint(evt.getPoint());
        if (row >= 0) {
            filltxt(row);
            Object val = tblOrderManager.getValueAt(row, 4);
            if (val != null) {
                String path = val.toString();
                File f = new File(path);
                if (!f.exists()) {
                    String alt = "src/images/" + path;
                    if (new File(alt).exists()) {
                        path = alt;
                    }
                }
                DuongDanAnh = path;
                lblHinhAnh.setIcon(ResizeImage(path));
            } else {
                lblHinhAnh.setIcon(null);
            }

            if (evt.getClickCount() == 2 && col == 4) {
                Object v = tblOrderManager.getValueAt(row, 4);
                if (v != null) {
                    openImage(v.toString());
                }
            }
        }
    }//GEN-LAST:event_tblOrderManagerMouseClicked

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtGiaActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void btnHinhANHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHinhANHActionPerformed
        try {
            File initDir = new File("src/main/java/img");
            if (!initDir.exists()) initDir = new File(".");
            JFileChooser f = new JFileChooser(initDir);
            f.setDialogTitle("Chọn hình ảnh món ăn / đồ uống");
            int result = f.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File ftenanh = f.getSelectedFile();
                DuongDanAnh = ftenanh.getName();
                if (lblImageName != null) {
                    lblImageName.setText(ftenanh.getName());
                }
                lblHinhAnh.setIcon(ResizeImage(ftenanh.getAbsolutePath()));
            }
        } catch (Exception ex) {
            System.out.println("Lỗi chọn ảnh: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnHinhANHActionPerformed

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
            int w = (lblHinhAnh != null && lblHinhAnh.getWidth() > 0) ? lblHinhAnh.getWidth() : 58;
            int h = (lblHinhAnh != null && lblHinhAnh.getHeight() > 0) ? lblHinhAnh.getHeight() : 58;
            Image newImg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        } catch (Exception ex) {
            return null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCle;
    private javax.swing.JButton btnCre;
    private javax.swing.JButton btnDe;
    private javax.swing.JButton btnFindId;
    private javax.swing.JButton btnHinhANH;
    private javax.swing.JButton btnUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblMaMon;
    private javax.swing.JTable tblOrderManager;
    private javax.swing.JTextField txtFinid;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables

    @Override
    public void open() {
        this.setLocationRelativeTo(null);
        this.fillToTable();
        this.clear();
    }

    @Override
    public void setForm(MonAn entity) {
        txtName.setText(entity.getTenMon());
        txtGia.setText(String.valueOf((long)entity.getGiaTien()));
        txtSoLuong.setText(String.valueOf(entity.getSoLuong()));
        DuongDanAnh = entity.getHinhANh();
        if (lblImageName != null) {
            lblImageName.setText(DuongDanAnh != null ? new File(DuongDanAnh).getName() : "chua_chon_anh.jpg");
        }
        if (lblHinhAnh != null && DuongDanAnh != null) {
            lblHinhAnh.setIcon(ResizeImage(DuongDanAnh));
        }
    }

    @Override
    public MonAn getForm() {
        MonAn Od = new MonAn();
        if (!lblMaMon.getText().trim().isEmpty() && !lblMaMon.getText().contains("Tự động")) {
            Od.setId(lblMaMon.getText().trim());
        }
        Od.setTenMon(txtName.getText().trim());
        Od.setGiaTien(Float.parseFloat(txtGia.getText().trim()));
        Od.setSoLuong(Integer.parseInt(txtSoLuong.getText().trim()));
        Od.setHinhANh(DuongDanAnh);
        return Od;
    }

    private boolean validateForm() {
        String name = txtName.getText().trim();
        String giaText = txtGia.getText().trim();
        String soLuongText = txtSoLuong.getText().trim();

        if (name.isEmpty()) {
            XDialog.alert("Tên món không được để trống.");
            return false;
        }

        try {
            float gia = Float.parseFloat(giaText);
            if (gia <= 0) {
                XDialog.alert("Đơn giá phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            XDialog.alert("Đơn giá phải là số hợp lệ.");
            return false;
        }

        try {
            int soLuong = Integer.parseInt(soLuongText);
            if (soLuong < 0) {
                XDialog.alert("Số lượng không được âm.");
                return false;
            }
        } catch (NumberFormatException e) {
            XDialog.alert("Số lượng phải là số nguyên hợp lệ.");
            return false;
        }

        return true;
    }

    private String getCategoryForItem(String name) {
        if (name == null) return "Đồ ăn";
        String u = name.toUpperCase();
        if (u.contains("STING") || u.contains("HÚC") || u.contains("HUC") || u.contains("CÀ PHÊ") ||
            u.contains("CA PHE") || u.contains("COCA") || u.contains("NƯỚC") || u.contains("NUOC") ||
            u.contains("TRÀ") || u.contains("TRA") || u.contains("PEPSI") || u.contains("REVIVE")) {
            return "Đồ uống";
        } else if (u.contains("KHOAI") || u.contains("SNACK") || u.contains("BIM") || u.contains("OISHI")) {
            return "Snack";
        }
        return "Đồ ăn";
    }

    @Override
    public void fillToTable() {
        items = dao.findAll();

        int countAll = items.size();
        int countFood = 0;
        int countDrink = 0;
        int countSnack = 0;
        int inStockCount = 0;

        for (MonAn item : items) {
            String cat = getCategoryForItem(item.getTenMon());
            if (cat.equals("Đồ ăn")) countFood++;
            else if (cat.equals("Đồ uống")) countDrink++;
            else if (cat.equals("Snack")) countSnack++;
            if (item.getSoLuong() > 0) inStockCount++;
        }

        if (lblTongSoMon != null) lblTongSoMon.setText("Tổng số: " + countAll + " món");
        if (lblConHang != null) lblConHang.setText("Còn hàng: " + inStockCount + " món");
        if (btnTabAll != null) btnTabAll.setText("Tất cả (" + countAll + ")");
        if (btnTabFood != null) btnTabFood.setText("Đồ ăn (" + countFood + ")");
        if (btnTabDrink != null) btnTabDrink.setText("Đồ uống (" + countDrink + ")");
        if (btnTabSnack != null) btnTabSnack.setText("Snack (" + countSnack + ")");

        filterTableData();
    }

    public void filterTableData() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"MÃ MÓN", "TÊN MÓN ĂN / NƯỚC", "PHÂN LOẠI", "ĐƠN GIÁ", "TỒN KHO", "TRẠNG THÁI"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblOrderManager.setModel(model);

        String keyword = txtFinid != null ? txtFinid.getText().trim().toLowerCase() : "";

        for (MonAn item : items) {
            String cat = getCategoryForItem(item.getTenMon());
            // Filter by Category Pill
            if ("FOOD".equals(currentCategoryFilter) && !cat.equals("Đồ ăn")) continue;
            if ("DRINK".equals(currentCategoryFilter) && !cat.equals("Đồ uống")) continue;
            if ("SNACK".equals(currentCategoryFilter) && !cat.equals("Snack")) continue;

            // Filter by search keyword
            if (!keyword.isEmpty()) {
                String idStr = item.getId() != null ? item.getId().toLowerCase() : "";
                String nameStr = item.getTenMon() != null ? item.getTenMon().toLowerCase() : "";
                if (!idStr.contains(keyword) && !nameStr.contains(keyword)) {
                    continue;
                }
            }

            String st = item.getSoLuong() > 0 ? "Sẵn sàng" : "Tạm hết";
            model.addRow(new Object[]{
                item.getId(),
                item.getTenMon(),
                cat,
                Style_Net.formatMoney(item.getGiaTien()),
                item.getSoLuong(),
                st
            });
        }
        setupQLTDRenderers();
    }

    private void setupQLTDRenderers() {
        if (tblOrderManager.getColumnCount() >= 6) {
            // Category renderer
            tblOrderManager.getColumnModel().getColumn(2).setCellRenderer((table, value, isSelected, hasFocus, row, col) -> {
                String cat = value != null ? value.toString() : "";
                javax.swing.JPanel pnl = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 7));
                pnl.setBackground(isSelected ? table.getSelectionBackground() : java.awt.Color.WHITE);
                pnl.add(Style_Net.createBadge(cat, "gray"));
                return pnl;
            });

            // Status renderer
            tblOrderManager.getColumnModel().getColumn(5).setCellRenderer((table, value, isSelected, hasFocus, row, col) -> {
                String st = value != null ? value.toString() : "";
                javax.swing.JPanel pnl = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 7));
                pnl.setBackground(isSelected ? table.getSelectionBackground() : java.awt.Color.WHITE);
                if ("Sẵn sàng".equalsIgnoreCase(st)) {
                    pnl.add(Style_Net.createBadge("Sẵn sàng", "green"));
                } else {
                    pnl.add(Style_Net.createBadge("Tạm hết", "red"));
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
        if (!validateForm()) {
            return;
        }
        MonAn Od = this.getForm();
        dao.create(Od);
        this.fillToTable();
        this.clear();
        JOptionPane.showMessageDialog(this, "Thêm món thành công!");
    }

    @Override
    public void update() {
        if (!validateForm()) {
            return;
        }
        MonAn Od = this.getForm();
        dao.update(Od);
        JOptionPane.showMessageDialog(this, "Cập nhật món ăn thành công!");
        this.fillToTable();
    }

    @Override
    public void delete() {
        if (lblMaMon.getText().trim().isEmpty() || lblMaMon.getText().contains("Tự động")) {
            XDialog.alert("Vui lòng chọn món cần xóa!");
            return;
        }
        if (XDialog.confirm("Bạn xác nhận xóa món " + lblMaMon.getText() + "?")) {
            String id = lblMaMon.getText();
            dao.deleteByID(id);
            this.fillToTable();
            this.clear();
            JOptionPane.showMessageDialog(this, "Xóa món thành công!");
        }
    }

    @Override
    public void clear() {
        lblMaMon.setText("");
        if (txtMaMonDisplay != null) {
            txtMaMonDisplay.setText("(Tự động cấp khi thêm)");
        }
        txtName.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
        if (cboDanhMuc != null) cboDanhMuc.setSelectedIndex(0);
        if (cboTinhTrang != null) cboTinhTrang.setSelectedIndex(0);
        if (lblImageName != null) lblImageName.setText("chua_chon_anh.jpg");
        if (lblHinhAnh != null) lblHinhAnh.setIcon(null);
        DuongDanAnh = null;
    }

    @Override
    public void setEditable(boolean editable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void finbyid() {
        filterTableData();
    }

    public void filltxt(int row) {
        if (row < 0 || row >= tblOrderManager.getRowCount()) return;
        Object idVal = tblOrderManager.getValueAt(row, 0);
        String idStr = idVal != null ? idVal.toString() : "";
        lblMaMon.setText(idStr);
        if (txtMaMonDisplay != null) {
            txtMaMonDisplay.setText(idStr);
        }

        for (MonAn item : items) {
            if (item.getId().equals(idStr)) {
                txtName.setText(item.getTenMon() != null ? item.getTenMon() : "");
                txtGia.setText(String.valueOf((long)item.getGiaTien()));
                txtSoLuong.setText(String.valueOf(item.getSoLuong()));

                String cat = getCategoryForItem(item.getTenMon());
                if (cboDanhMuc != null) {
                    if (cat.equals("Đồ uống")) cboDanhMuc.setSelectedIndex(1);
                    else if (cat.equals("Snack")) cboDanhMuc.setSelectedIndex(2);
                    else cboDanhMuc.setSelectedIndex(0);
                }

                if (cboTinhTrang != null) {
                    cboTinhTrang.setSelectedIndex(item.getSoLuong() > 0 ? 0 : 1);
                }

                DuongDanAnh = item.getHinhANh();
                if (lblImageName != null) {
                    lblImageName.setText(DuongDanAnh != null ? new File(DuongDanAnh).getName() : "chua_chon_anh.jpg");
                }
                if (lblHinhAnh != null) {
                    lblHinhAnh.setIcon(ResizeImage(DuongDanAnh));
                }
                break;
            }
        }
    }

    private void openImage(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                File f1 = new File("src/main/java/img/" + path);
                if (f1.exists()) f = f1;
                else {
                    File f2 = new File("src/images/" + path);
                    if (f2.exists()) f = f2;
                }
            }
            if (!f.exists()) return;
            ImageIcon icon = new ImageIcon(f.getAbsolutePath());
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
            ImageIcon big = new ImageIcon(newImg);
            javax.swing.JLabel lb = new javax.swing.JLabel(big);
            javax.swing.JScrollPane sp = new javax.swing.JScrollPane(lb);
            sp.setPreferredSize(new java.awt.Dimension(650, 650));
            javax.swing.JOptionPane.showMessageDialog(this, sp, "Chi tiết hình ảnh món", javax.swing.JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Không thể mở ảnh: " + e.getMessage());
        }
    }

}
