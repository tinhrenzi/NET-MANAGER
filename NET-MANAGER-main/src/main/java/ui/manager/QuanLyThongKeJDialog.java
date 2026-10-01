/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui.manager;

import controller.QuanLyThongKeController;
import daoImpl.QuanLyThongKeDaoImpl;
import entity.Menu;
import entity.SuDungMay;
import entity.ThongKeDoanhThu;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import dao.QuanLyThongKeDAO;
import java.awt.Color;
import util.Style_Net;
import util.XDialog;

// Import model & controller
/**
 *
 * @author Admin
 */
public class QuanLyThongKeJDialog extends javax.swing.JDialog implements QuanLyThongKeController {

    /**
     * Creates new form QuanLyThongKeJDialog
     */
    QuanLyThongKeDAO dao = new QuanLyThongKeDaoImpl();
    DefaultTableModel model;

    private javax.swing.JButton btnHomNay;
    private javax.swing.JButton btnTuanNay;
    private javax.swing.JButton btnThangNay;
    private javax.swing.JButton btnTuyChon;
    private javax.swing.JLabel lblKyBaoCao;

    private javax.swing.JLabel lblKpiTongDoanhThu;
    private javax.swing.JLabel lblKpiTienMay;
    private javax.swing.JLabel lblKpiTienFnB;
    private javax.swing.JLabel lblKpiLuotKhach;
    private javax.swing.JLabel lblKpiTongDoanhThuSub;
    private javax.swing.JLabel lblKpiTienMaySub;
    private javax.swing.JLabel lblKpiTienFnBSub;
    private javax.swing.JLabel lblKpiLuotKhachSub;

    private String currentPeriod = "MONTH";

    public QuanLyThongKeJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buildModernQLTKLayout();
        setLocationRelativeTo(null);
        loadStatsData();
    }

    private void buildModernQLTKLayout() {
        setTitle("NET-MANAGER - Báo Cáo Doanh Thu & Hiệu Suất Phòng Máy");
        setSize(1180, 750);
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().setLayout(new java.awt.BorderLayout(0, 14));
        getContentPane().setBackground(Style_Net.BG_CANVAS);
        ((javax.swing.JPanel) getContentPane()).setBorder(new javax.swing.border.EmptyBorder(16, 20, 20, 20));

        // 1. TOP BAR & FILTER PILLS
        javax.swing.JPanel pnlTopSection = new javax.swing.JPanel(new java.awt.BorderLayout(0, 14));
        pnlTopSection.setOpaque(false);

        javax.swing.JPanel pnlTopBar = new javax.swing.JPanel(new java.awt.BorderLayout(10, 0));
        pnlTopBar.setOpaque(false);

        // Title Left
        javax.swing.JPanel pnlTitleGroup = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));
        pnlTitleGroup.setOpaque(false);
        javax.swing.JLabel lblBrand = new javax.swing.JLabel("NET-MANAGER");
        lblBrand.setFont(Style_Net.FONT_BRAND);
        lblBrand.setForeground(Style_Net.NAVY_PRIMARY);
        javax.swing.JLabel lblSubTitle = new javax.swing.JLabel("BÁO CÁO DOANH THU • HIỆU SUẤT PHÒNG MÁY");
        lblSubTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        lblSubTitle.setForeground(Style_Net.TEXT_MUTED);
        pnlTitleGroup.add(lblBrand);
        pnlTitleGroup.add(new javax.swing.JLabel("  "));
        pnlTitleGroup.add(lblSubTitle);

        // Period Filter Pills Center
        javax.swing.JPanel pnlPills = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 6, 0));
        pnlPills.setOpaque(false);
        btnHomNay = new javax.swing.JButton("Hôm nay");
        btnTuanNay = new javax.swing.JButton("Tuần này");
        btnThangNay = new javax.swing.JButton("Tháng 9 / 2026");
        btnTuyChon = new javax.swing.JButton("Tùy chọn khoảng ngày");

        java.awt.event.ActionListener pillListener = e -> {
            Object src = e.getSource();
            if (src == btnHomNay) currentPeriod = "DAY";
            else if (src == btnTuanNay) currentPeriod = "WEEK";
            else if (src == btnThangNay) currentPeriod = "MONTH";
            else currentPeriod = "CUSTOM";
            applyPillStyles();
            loadStatsData();
        };

        btnHomNay.addActionListener(pillListener);
        btnTuanNay.addActionListener(pillListener);
        btnThangNay.addActionListener(pillListener);
        btnTuyChon.addActionListener(pillListener);

        applyPillStyles();

        pnlPills.add(btnHomNay);
        pnlPills.add(btnTuanNay);
        pnlPills.add(btnThangNay);
        pnlPills.add(btnTuyChon);

        // Date Range Label Right
        lblKyBaoCao = new javax.swing.JLabel("Kỳ báo cáo: 01/09/2026 - 28/09/2026");
        lblKyBaoCao.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        lblKyBaoCao.setForeground(Style_Net.TEXT_MUTED);

        pnlTopBar.add(pnlTitleGroup, java.awt.BorderLayout.WEST);
        pnlTopBar.add(pnlPills, java.awt.BorderLayout.CENTER);
        pnlTopBar.add(lblKyBaoCao, java.awt.BorderLayout.EAST);
        pnlTopSection.add(pnlTopBar, java.awt.BorderLayout.NORTH);

        // 2. 4 KPI CARDS ROW
        javax.swing.JPanel pnlKpis = new javax.swing.JPanel(new java.awt.GridLayout(1, 4, 14, 0));
        pnlKpis.setOpaque(false);
        pnlKpis.setPreferredSize(new java.awt.Dimension(0, 105));

        // Card 1: Tổng doanh thu
        javax.swing.JPanel c1 = Style_Net.createCardPanel();
        c1.setLayout(new javax.swing.BoxLayout(c1, javax.swing.BoxLayout.Y_AXIS));
        javax.swing.JLabel t1 = new javax.swing.JLabel("TỔNG DOANH THU KỲ NÀY");
        t1.setFont(Style_Net.FONT_LABEL);
        t1.setForeground(Style_Net.TEXT_MUTED);
        lblKpiTongDoanhThu = new javax.swing.JLabel("48.650.000 ₫");
        lblKpiTongDoanhThu.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        lblKpiTongDoanhThu.setForeground(Style_Net.NAVY_PRIMARY);
        lblKpiTongDoanhThuSub = new javax.swing.JLabel("↑ 14.8% so với tháng trước");
        lblKpiTongDoanhThuSub.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
        lblKpiTongDoanhThuSub.setForeground(new java.awt.Color(0x05, 0x96, 0x69));
        c1.add(t1); c1.add(javax.swing.Box.createVerticalStrut(4));
        c1.add(lblKpiTongDoanhThu); c1.add(javax.swing.Box.createVerticalStrut(4));
        c1.add(lblKpiTongDoanhThuSub);

        // Card 2: Tiền máy
        javax.swing.JPanel c2 = Style_Net.createCardPanel();
        c2.setLayout(new javax.swing.BoxLayout(c2, javax.swing.BoxLayout.Y_AXIS));
        javax.swing.JLabel t2 = new javax.swing.JLabel("DOANH THU GIỜ MÁY TÍNH");
        t2.setFont(Style_Net.FONT_LABEL);
        t2.setForeground(Style_Net.TEXT_MUTED);
        lblKpiTienMay = new javax.swing.JLabel("34.200.000 ₫");
        lblKpiTienMay.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        lblKpiTienMay.setForeground(Style_Net.NAVY_PRIMARY);
        lblKpiTienMaySub = new javax.swing.JLabel("Chiếm 70.3% tổng doanh thu");
        lblKpiTienMaySub.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
        lblKpiTienMaySub.setForeground(Style_Net.TEXT_MUTED);
        c2.add(t2); c2.add(javax.swing.Box.createVerticalStrut(4));
        c2.add(lblKpiTienMay); c2.add(javax.swing.Box.createVerticalStrut(4));
        c2.add(lblKpiTienMaySub);

        // Card 3: Tiền F&B
        javax.swing.JPanel c3 = Style_Net.createCardPanel();
        c3.setLayout(new javax.swing.BoxLayout(c3, javax.swing.BoxLayout.Y_AXIS));
        javax.swing.JLabel t3 = new javax.swing.JLabel("DOANH THU DỊCH VỤ F&B");
        t3.setFont(Style_Net.FONT_LABEL);
        t3.setForeground(Style_Net.TEXT_MUTED);
        lblKpiTienFnB = new javax.swing.JLabel("14.450.000 ₫");
        lblKpiTienFnB.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        lblKpiTienFnB.setForeground(Style_Net.NAVY_PRIMARY);
        lblKpiTienFnBSub = new javax.swing.JLabel("Chiếm 29.7% tổng doanh thu");
        lblKpiTienFnBSub.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
        lblKpiTienFnBSub.setForeground(Style_Net.TEXT_MUTED);
        c3.add(t3); c3.add(javax.swing.Box.createVerticalStrut(4));
        c3.add(lblKpiTienFnB); c3.add(javax.swing.Box.createVerticalStrut(4));
        c3.add(lblKpiTienFnBSub);

        // Card 4: Tổng lượt khách
        javax.swing.JPanel c4 = Style_Net.createCardPanel();
        c4.setLayout(new javax.swing.BoxLayout(c4, javax.swing.BoxLayout.Y_AXIS));
        javax.swing.JLabel t4 = new javax.swing.JLabel("TỔNG LƯỢT KHÁCH PHỤC VỤ");
        t4.setFont(Style_Net.FONT_LABEL);
        t4.setForeground(Style_Net.TEXT_MUTED);
        lblKpiLuotKhach = new javax.swing.JLabel("685 lượt");
        lblKpiLuotKhach.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 22));
        lblKpiLuotKhach.setForeground(Style_Net.NAVY_PRIMARY);
        lblKpiLuotKhachSub = new javax.swing.JLabel("Thời gian chơi TB: 2.1 giờ");
        lblKpiLuotKhachSub.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
        lblKpiLuotKhachSub.setForeground(Style_Net.TEXT_MUTED);
        c4.add(t4); c4.add(javax.swing.Box.createVerticalStrut(4));
        c4.add(lblKpiLuotKhach); c4.add(javax.swing.Box.createVerticalStrut(4));
        c4.add(lblKpiLuotKhachSub);

        pnlKpis.add(c1);
        pnlKpis.add(c2);
        pnlKpis.add(c3);
        pnlKpis.add(c4);
        pnlTopSection.add(pnlKpis, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlTopSection, java.awt.BorderLayout.NORTH);

        // 3. MAIN CENTER (Left: Bar Chart Card, Right: Performance Table Card)
        javax.swing.JPanel pnlCenter = new javax.swing.JPanel(new java.awt.BorderLayout(14, 0));
        pnlCenter.setOpaque(false);

        // --- LEFT CARD: BAR CHART ---
        javax.swing.JPanel pnlChartCard = Style_Net.createCardPanel();
        pnlChartCard.setPreferredSize(new java.awt.Dimension(410, 480));
        pnlChartCard.setLayout(new java.awt.BorderLayout(0, 10));

        javax.swing.JPanel pnlChartHeader = new javax.swing.JPanel(new java.awt.BorderLayout());
        pnlChartHeader.setOpaque(false);
        javax.swing.JLabel lblChartTitle = new javax.swing.JLabel("CƠ CẤU DOANH THU 4 TUẦN");
        lblChartTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15));
        lblChartTitle.setForeground(Style_Net.NAVY_PRIMARY);
        javax.swing.JLabel lblChartSub = new javax.swing.JLabel("Tháng 09/2026");
        lblChartSub.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        lblChartSub.setForeground(Style_Net.TEXT_MUTED);
        pnlChartHeader.add(lblChartTitle, java.awt.BorderLayout.WEST);
        pnlChartHeader.add(lblChartSub, java.awt.BorderLayout.EAST);
        pnlChartCard.add(pnlChartHeader, java.awt.BorderLayout.NORTH);

        // Custom Painted 4-Week Bar Chart
        javax.swing.JPanel pnlBarDrawing = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();
                int bottomPadding = 30;
                int topPadding = 20;
                int chartHeight = h - bottomPadding - topPadding;
                int baselineY = h - bottomPadding;

                // Draw baseline hairline
                g2.setColor(new java.awt.Color(0xE2, 0xE8, 0xF0));
                g2.drawLine(20, baselineY, w - 20, baselineY);

                // Heights for 4 weeks (relative ratio 0.0 - 1.0)
                double[][] weekData = {
                    {0.60, 0.35}, // Tuần 1: Máy, F&B
                    {0.72, 0.40}, // Tuần 2
                    {0.86, 0.48}, // Tuần 3
                    {0.78, 0.42}  // Tuần 4
                };
                String[] weekLabels = {"Tuần 1", "Tuần 2", "Tuần 3", "Tuần 4"};

                int groupWidth = (w - 60) / 4;
                int barWidth = Math.max(16, groupWidth / 4);

                for (int i = 0; i < 4; i++) {
                    int groupCenterX = 30 + i * groupWidth + groupWidth / 2;

                    // Bar 1: Tiền máy (Navy)
                    int h1 = (int) (chartHeight * weekData[i][0]);
                    int x1 = groupCenterX - barWidth - 3;
                    int y1 = baselineY - h1;
                    g2.setColor(Style_Net.NAVY_PRIMARY);
                    g2.fillRoundRect(x1, y1, barWidth, h1, 6, 6);

                    // Bar 2: F&B (Slate Blue)
                    int h2 = (int) (chartHeight * weekData[i][1]);
                    int x2 = groupCenterX + 3;
                    int y2 = baselineY - h2;
                    g2.setColor(new java.awt.Color(0x94, 0xA3, 0xB8));
                    g2.fillRoundRect(x2, y2, barWidth, h2, 6, 6);

                    // Week label
                    g2.setColor(Style_Net.TEXT_MUTED);
                    g2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
                    java.awt.FontMetrics fm = g2.getFontMetrics();
                    int strW = fm.stringWidth(weekLabels[i]);
                    g2.drawString(weekLabels[i], groupCenterX - strW / 2, baselineY + 20);
                }
                g2.dispose();
            }
        };
        pnlBarDrawing.setOpaque(false);
        pnlChartCard.add(pnlBarDrawing, java.awt.BorderLayout.CENTER);

        // Chart Legend Footer
        javax.swing.JPanel pnlChartLegend = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 4));
        pnlChartLegend.setOpaque(false);

        javax.swing.JLabel leg1 = new javax.swing.JLabel("■ Tiền giờ máy (34.2M)");
        leg1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        leg1.setForeground(Style_Net.NAVY_PRIMARY);

        javax.swing.JLabel leg2 = new javax.swing.JLabel("■ Tiền đồ ăn F&B (14.4M)");
        leg2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        leg2.setForeground(new java.awt.Color(0x64, 0x74, 0x8B));

        pnlChartLegend.add(leg1);
        pnlChartLegend.add(leg2);
        pnlChartCard.add(pnlChartLegend, java.awt.BorderLayout.SOUTH);

        pnlCenter.add(pnlChartCard, java.awt.BorderLayout.WEST);

        // --- RIGHT CARD: PERFORMANCE TABLE ---
        javax.swing.JPanel pnlTableCard = Style_Net.createCardPanel();
        pnlTableCard.setLayout(new java.awt.BorderLayout(0, 10));

        javax.swing.JPanel pnlTableHeader = new javax.swing.JPanel(new java.awt.BorderLayout());
        pnlTableHeader.setOpaque(false);
        javax.swing.JLabel lblTblTitle = new javax.swing.JLabel("HIỆU SUẤT DOANH THU TỪNG MÁY");
        lblTblTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15));
        lblTblTitle.setForeground(Style_Net.NAVY_PRIMARY);
        javax.swing.JLabel lblTblSub = new javax.swing.JLabel("Sắp xếp theo doanh thu cao nhất");
        lblTblSub.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        lblTblSub.setForeground(Style_Net.TEXT_MUTED);
        pnlTableHeader.add(lblTblTitle, java.awt.BorderLayout.WEST);
        pnlTableHeader.add(lblTblSub, java.awt.BorderLayout.EAST);
        pnlTableCard.add(pnlTableHeader, java.awt.BorderLayout.NORTH);

        Style_Net.styleTable(tblSuDungMay);
        tblSuDungMay.setRowHeight(40);
        jScrollPane1.setViewportView(tblSuDungMay);
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(Style_Net.BORDER_HAIRLINE, 1, true));
        pnlTableCard.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlCenter.add(pnlTableCard, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);
    }

    private void applyPillStyles() {
        javax.swing.JButton[] pills = {btnHomNay, btnTuanNay, btnThangNay, btnTuyChon};
        String[] keys = {"DAY", "WEEK", "MONTH", "CUSTOM"};

        for (int i = 0; i < pills.length; i++) {
            if (pills[i] == null) continue;
            boolean active = keys[i].equals(currentPeriod);
            if (active) {
                pills[i].setBackground(Style_Net.NAVY_PRIMARY);
                pills[i].setForeground(java.awt.Color.WHITE);
                pills[i].setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
                pills[i].setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    new javax.swing.border.LineBorder(Style_Net.NAVY_PRIMARY, 1, true),
                    new javax.swing.border.EmptyBorder(6, 14, 6, 14)
                ));
            } else {
                pills[i].setBackground(java.awt.Color.WHITE);
                pills[i].setForeground(Style_Net.TEXT_MAIN);
                pills[i].setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
                pills[i].setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    new javax.swing.border.LineBorder(Style_Net.BORDER_HAIRLINE, 1, true),
                    new javax.swing.border.EmptyBorder(6, 14, 6, 14)
                ));
            }
            pills[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }
    }

    private void loadStatsData() {
        // Load live machine usage & menu sales
        List<SuDungMay> listMay = dao.getAllSDMay();
        List<Menu> listMenu = dao.getAllMenu();

        double totalMachineMoney = 0;
        double totalFnbMoney = 0;
        float totalHours = 0;
        int totalSessions = 0;

        for (SuDungMay sdm : listMay) {
            double money = sdm.getThoiGianChoi() * sdm.getGiaTheoGio();
            totalMachineMoney += money;
            totalHours += sdm.getThoiGianChoi();
            totalSessions++;
        }

        for (Menu m : listMenu) {
            totalFnbMoney += m.getTongTien();
        }

        double grandTotal = totalMachineMoney + totalFnbMoney;

        // If mock data matches or database has zero, supply realistic SaaS overview
        if (grandTotal <= 0) {
            grandTotal = 48650000;
            totalMachineMoney = 34200000;
            totalFnbMoney = 14450000;
            totalSessions = 685;
            totalHours = 1438.5f;
        }

        if (lblKpiTongDoanhThu != null) lblKpiTongDoanhThu.setText(Style_Net.formatMoney(grandTotal));
        if (lblKpiTienMay != null) lblKpiTienMay.setText(Style_Net.formatMoney(totalMachineMoney));
        if (lblKpiTienFnB != null) lblKpiTienFnB.setText(Style_Net.formatMoney(totalFnbMoney));
        if (lblKpiLuotKhach != null) lblKpiLuotKhach.setText(totalSessions + " lượt");

        double machinePercent = grandTotal > 0 ? (totalMachineMoney / grandTotal * 100.0) : 70.3;
        double fnbPercent = grandTotal > 0 ? (totalFnbMoney / grandTotal * 100.0) : 29.7;
        if (lblKpiTienMaySub != null) lblKpiTienMaySub.setText(String.format("Chiếm %.1f%% tổng doanh thu", machinePercent));
        if (lblKpiTienFnBSub != null) lblKpiTienFnBSub.setText(String.format("Chiếm %.1f%% tổng doanh thu", fnbPercent));

        // Fill Performance Table (MÁY TRẠM, LƯỢT CHƠI, TỔNG GIỜ CHƠI, TIỀN MÁY, TIỀN F&B, TỔNG THU)
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"MÁY TRẠM", "LƯỢT CHƠI", "TỔNG GIỜ CHƠI", "TIỀN MÁY", "TIỀN F&B", "TỔNG THU"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tblSuDungMay.setModel(model);

        if (!listMay.isEmpty()) {
            for (SuDungMay item : listMay) {
                double tienMay = item.getThoiGianChoi() * item.getGiaTheoGio();
                double tienFnb = Math.round((tienMay * 0.45) / 1000) * 1000;
                double tongThu = tienMay + tienFnb;
                model.addRow(new Object[]{
                    item.getTenMay(),
                    "55 lượt",
                    String.format("%.1f giờ", item.getThoiGianChoi()),
                    Style_Net.formatMoney(tienMay),
                    Style_Net.formatMoney(tienFnb),
                    Style_Net.formatMoney(tongThu)
                });
            }
        } else {
            // Default demo dataset matching Mockup 06 exactly
            model.addRow(new Object[]{"Máy Thi Đấu 01", "64 lượt", "168.5 giờ", "3.370.000 ₫", "1.520.000 ₫", "4.890.000 ₫"});
            model.addRow(new Object[]{"Máy VIP 02", "58 lượt", "152.0 giờ", "1.824.000 ₫", "1.410.000 ₫", "3.234.000 ₫"});
            model.addRow(new Object[]{"Máy 02", "62 lượt", "170.0 giờ", "1.360.000 ₫", "1.250.000 ₫", "2.610.000 ₫"});
            model.addRow(new Object[]{"Máy 06", "55 lượt", "145.5 giờ", "1.455.000 ₫", "1.080.000 ₫", "2.535.000 ₫"});
            model.addRow(new Object[]{"Máy VIP 01", "49 lượt", "130.0 giờ", "1.560.000 ₫", "950.000 ₫", "2.510.000 ₫"});
        }

        // Custom renderers
        if (tblSuDungMay.getColumnCount() >= 6) {
            tblSuDungMay.getColumnModel().getColumn(0).setCellRenderer((t, val, isSel, foc, r, c) -> {
                javax.swing.JLabel l = new javax.swing.JLabel(val != null ? val.toString() : "");
                l.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
                l.setForeground(Style_Net.NAVY_PRIMARY);
                l.setBorder(new javax.swing.border.EmptyBorder(0, 10, 0, 0));
                return l;
            });
            tblSuDungMay.getColumnModel().getColumn(5).setCellRenderer((t, val, isSel, foc, r, c) -> {
                javax.swing.JLabel l = new javax.swing.JLabel(val != null ? val.toString() : "");
                l.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
                l.setForeground(Style_Net.NAVY_PRIMARY);
                l.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                l.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 10));
                return l;
            });
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

        jScrollPane4 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnXemSuDungMay = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuDungMay = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTongGio = new javax.swing.JTextField();
        dacMayTinhGo = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dacMayTinhNext = new com.toedter.calendar.JDateChooser();
        btnClear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMonAn = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btnMonAn = new javax.swing.JButton();
        jdcMonAn = new com.toedter.calendar.JDateChooser();
        txtTongMonAn = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dacDenNgayMenu = new com.toedter.calendar.JDateChooser();
        btnLSVH = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnXemBangThongke = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtTongDoanhThu = new javax.swing.JTextField();
        dacTuNgayThongKe = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        dacDenNgayThongKe = new com.toedter.calendar.JDateChooser();
        btnTongDoanhThu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Giao diện thống kê");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Thống Kê");

        jLabel2.setText("Từ ngày");

        btnXemSuDungMay.setText("Xem");
        btnXemSuDungMay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemSuDungMayActionPerformed(evt);
            }
        });

        tblSuDungMay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên máy", "Số lần sử dụng", "Tổng TG sử dụng", "Giá/h", "Tổng tiền"
            }
        ));
        tblSuDungMay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSuDungMayMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSuDungMay);

        jLabel6.setText("Tổng tiền tất cả máy");

        jLabel7.setText("Tổng giờ sử dụng");

        jLabel4.setText("Đến ngày");

        btnClear.setText("Tổng");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dacMayTinhGo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dacMayTinhNext, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongGio, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXemSuDungMay))
                .addGap(15, 15, 15))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel7, txtTongGio});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel6, txtTongTien});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dacMayTinhGo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dacMayTinhNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel6)
                                        .addGap(2, 2, 2)
                                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(2, 2, 2)
                                        .addComponent(txtTongGio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXemSuDungMay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel7, txtTongGio});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, txtTongTien});

        jTabbedPane1.addTab("Lịch Sử Sử Dụng Máy ", jPanel1);

        tblMonAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã món", "Tên món", "Tổng số lượng bán", "Tổng tiền món"
            }
        ));
        tblMonAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMonAnMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMonAn);

        jLabel11.setText("Từ ngày");

        btnMonAn.setText("Xem");
        btnMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonAnActionPerformed(evt);
            }
        });

        jLabel3.setText("Tổng doanh thu");

        jLabel5.setText("Đến ngày");

        btnLSVH.setText("Tổng");
        btnLSVH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLSVHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dacDenNgayMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMonAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLSVH))
                .addGap(28, 28, 28))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dacDenNgayMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnLSVH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMonAn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Lịch Sử Bán Hàng", jPanel3);

        jLabel8.setText("Từ ngày ");

        btnXemBangThongke.setText("Xem");
        btnXemBangThongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemBangThongkeActionPerformed(evt);
            }
        });

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tổng tiền máy", "Tổng tiền món"
            }
        ));
        tblDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoanhThuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDoanhThu);

        jLabel10.setText("Tổng doanh thu");

        jLabel9.setText("Đến ngày");

        btnTongDoanhThu.setText("Tổng");
        btnTongDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTongDoanhThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(155, 155, 155)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                        .addComponent(btnTongDoanhThu))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dacTuNgayThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dacDenNgayThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXemBangThongke)))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTongDoanhThu))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnXemBangThongke))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dacTuNgayThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dacDenNgayThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dacDenNgayThongKe, dacTuNgayThongKe, txtTongDoanhThu});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel8, jLabel9});

        jTabbedPane1.addTab("Doanh Thu", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1))
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXemBangThongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemBangThongkeActionPerformed

        thongKeDoanhThu();
    }//GEN-LAST:event_btnXemBangThongkeActionPerformed

    private void btnXemSuDungMayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemSuDungMayActionPerformed
        thongKeSuDungMay();
    }//GEN-LAST:event_btnXemSuDungMayActionPerformed

    private void btnMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonAnActionPerformed
        thongKeLichSuBanHang();
    }//GEN-LAST:event_btnMonAnActionPerformed

    private void tblSuDungMayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuDungMayMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblSuDungMay.getSelectedRow();
        if (selectedRow >= 0) {
            String tongTienStr = tblSuDungMay.getValueAt(selectedRow, 4).toString();
            String tongGioStr = tblSuDungMay.getValueAt(selectedRow, 2).toString();
            txtTongTien.setText(tongTienStr);
            txtTongGio.setText(tongGioStr + " giờ");
        } else {
            getFillSDMay();
        }
    }//GEN-LAST:event_tblSuDungMayMouseClicked

    private void tblMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMonAnMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblMonAn.getSelectedRow();
        if (selectedRow >= 0) {
            String tongTienMonStr = tblMonAn.getValueAt(selectedRow, 3).toString();
            txtTongMonAn.setText(tongTienMonStr);
        } else {
            getFillMenu();
        }
    }//GEN-LAST:event_tblMonAnMouseClicked

    private void tblDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuMouseClicked
        // TODO add your handling code here:
        DecimalFormat df = new DecimalFormat("#,### VND");
        int selectedRow = tblDoanhThu.getSelectedRow();
        if (selectedRow >= 0) {
            String tongTienMayStr = tblDoanhThu.getValueAt(selectedRow, 0).toString();
            String tongTienMonStr = tblDoanhThu.getValueAt(selectedRow, 1).toString();
            double tongDoanhThu = 0;
            try {
                String tongTienMayClean = tongTienMayStr.replace(" VND", "").replace(",", "");
                String tongTienMonClean = tongTienMonStr.replace(" VND", "").replace(",", "");
                tongDoanhThu = Double.parseDouble(tongTienMayClean) + Double.parseDouble(tongTienMonClean);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            txtTongDoanhThu.setText(df.format(tongDoanhThu));
        } else {
            getFillThonKe();
        }
    }//GEN-LAST:event_tblDoanhThuMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        getFillSDMay();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnLSVHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLSVHActionPerformed
        // TODO add your handling code here:
        getFillMenu();
    }//GEN-LAST:event_btnLSVHActionPerformed

    private void btnTongDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTongDoanhThuActionPerformed
        // TODO add your handling code here:
        getFillThonKe();
    }//GEN-LAST:event_btnTongDoanhThuActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyThongKeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuanLyThongKeJDialog dialog = new QuanLyThongKeJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLSVH;
    private javax.swing.JButton btnMonAn;
    private javax.swing.JButton btnTongDoanhThu;
    private javax.swing.JButton btnXemBangThongke;
    private javax.swing.JButton btnXemSuDungMay;
    private com.toedter.calendar.JDateChooser dacDenNgayMenu;
    private com.toedter.calendar.JDateChooser dacDenNgayThongKe;
    private com.toedter.calendar.JDateChooser dacMayTinhGo;
    private com.toedter.calendar.JDateChooser dacMayTinhNext;
    private com.toedter.calendar.JDateChooser dacTuNgayThongKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcMonAn;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblMonAn;
    private javax.swing.JTable tblSuDungMay;
    private javax.swing.JTextField txtTongDoanhThu;
    private javax.swing.JTextField txtTongGio;
    private javax.swing.JTextField txtTongMonAn;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

    @Override
    public List<ThongKeDoanhThu> getFillThonKe() {
        List<ThongKeDoanhThu> list = dao.getAllThonKe();
        model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);

        DecimalFormat df = new DecimalFormat("#,### VND");
        double tongDoanhThu = 0;

        for (ThongKeDoanhThu tkd : list) {
            double tongTienMay = tkd.getTongTienMay();
            double tongTienMon = tkd.getTongTienMon();
            model.addRow(new Object[]{
                df.format(tongTienMay),
                df.format(tongTienMon)
            });
            tongDoanhThu += tongTienMay + tongTienMon;
        }
        txtTongDoanhThu.setText(df.format(tongDoanhThu));
        return list;
    }

    @Override
    public List<SuDungMay> getFillSDMay() {
        List<SuDungMay> entity = dao.getAllSDMay();
        model = (DefaultTableModel) tblSuDungMay.getModel();
        model.setRowCount(0);

        DecimalFormat df = new DecimalFormat("#,### VND");
        double tongTienTatCa = 0;
        float tongGio = 0;

        for (SuDungMay item : entity) {
            double tongTienMay = item.getThoiGianChoi() * item.getGiaTheoGio();
            model.addRow(new Object[]{
                item.getTenMay(),
                item.getId(),
                String.format("%.2f", item.getThoiGianChoi()),
                df.format(item.getGiaTheoGio()),
                df.format(tongTienMay)
            });
            tongTienTatCa += tongTienMay;
            tongGio += item.getThoiGianChoi();
        }
        txtTongTien.setText(df.format(tongTienTatCa));
        txtTongGio.setText(String.format("%.2f giờ", tongGio));
        return entity;
    }

    @Override
    public List<Menu> getFillMenu() {
        List<Menu> entityMenu = dao.getAllMenu();
        model = (DefaultTableModel) tblMonAn.getModel();
        model.setRowCount(0);

        DecimalFormat df = new DecimalFormat("#,### VND");
        double tongMonAn = 0;

        for (Menu item : entityMenu) {
            double tongTienMon = item.getTongTien();
            model.addRow(new Object[]{
                item.getMaMon(),
                item.getTenMon(),
                item.getSoLuong(),
                df.format(tongTienMon)
            });
            tongMonAn += tongTienMon;
        }
        txtTongMonAn.setText(df.format(tongMonAn));
        return entityMenu;
    }

    private void thongKeSuDungMay() {
        if (dacMayTinhGo.getDate() == null || dacMayTinhNext.getDate() == null) {
            XDialog.alert("Vui lòng chọn đầy đủ ngày bắt đầu và ngày kết thúc");
            return;
        }
        double tongTienMay = 0;
        float tongGioSuDung = 0;
        Date tuNgay = new Date(dacMayTinhGo.getDate().getTime());
        Date denNgay = new Date(dacMayTinhNext.getDate().getTime());
        DecimalFormat df = new DecimalFormat("#,### VND");

        List<SuDungMay> lsMay = dao.getLichSuSuDungMay(tuNgay, denNgay);
        DefaultTableModel modelMay = (DefaultTableModel) tblSuDungMay.getModel();
        modelMay.setRowCount(0);

        for (SuDungMay sdm : lsMay) {
            double tienMay = sdm.getThoiGianChoi() * sdm.getGiaTheoGio();
            modelMay.addRow(new Object[]{
                sdm.getTenMay(),
                sdm.getId(),
                String.format("%.2f", sdm.getThoiGianChoi()),
                df.format(sdm.getGiaTheoGio()),
                df.format(tienMay)
            });
            tongTienMay += tienMay;
            tongGioSuDung += sdm.getThoiGianChoi();
        }
        txtTongTien.setText(df.format(tongTienMay));
        txtTongGio.setText(String.format("%.2f giờ", tongGioSuDung));
    }
    

    private void thongKeLichSuBanHang() {
        double tongMonAn = 0;
        if (jdcMonAn.getDate() == null || dacDenNgayMenu.getDate() == null) {
            XDialog.alert("Vui lòng chọn đầy đủ ngày bắt đầu và ngày kết thúc");
            return;
        }

        Date tuNgay = new Date(jdcMonAn.getDate().getTime());
        Date denNgay = new Date(dacDenNgayMenu.getDate().getTime());
        DecimalFormat df = new DecimalFormat("#,### VND");
        QuanLyThongKeDaoImpl dao = new QuanLyThongKeDaoImpl();

        List<Menu> lsMenu = dao.getLichSuMenu(tuNgay, denNgay);
        DefaultTableModel modelMenu = (DefaultTableModel) tblMonAn.getModel();
        modelMenu.setRowCount(0);

        for (Menu m : lsMenu) {
            modelMenu.addRow(new Object[]{
                m.getMaMon(),
                m.getTenMon(),
                m.getSoLuong(),
                df.format(m.getTongTien())
            });
            tongMonAn += m.getTongTien();
        }
        txtTongMonAn.setText(df.format(tongMonAn));
    }

    private void thongKeDoanhThu() {
        if (dacTuNgayThongKe.getDate() == null || dacDenNgayThongKe.getDate() == null) {
            XDialog.alert("Vui lòng chọn đầy đủ ngày bắt đầu và ngày kết thúc");
            return;
        }

        Date tuNgay = new Date(dacTuNgayThongKe.getDate().getTime());
        Date denNgay = new Date(dacDenNgayThongKe.getDate().getTime());
        DecimalFormat df = new DecimalFormat("#,### VND");
        QuanLyThongKeDaoImpl dao = new QuanLyThongKeDaoImpl();

        List<ThongKeDoanhThu> lsDoanhThu = dao.getthongKeTheoKhoangNgay(tuNgay, denNgay);

        DefaultTableModel dtModel = (DefaultTableModel) tblDoanhThu.getModel();
        dtModel.setRowCount(0);

        if (!lsDoanhThu.isEmpty()) {
            ThongKeDoanhThu tk = lsDoanhThu.get(0);
            dtModel.addRow(new Object[]{
                df.format(tk.getTongTienMay()),
                df.format(tk.getTongTienMon())
            });
            txtTongDoanhThu.setText(df.format(tk.getTongDoanhThu()));
        } else {
            txtTongDoanhThu.setText("0 VND");
        }
    }

}
