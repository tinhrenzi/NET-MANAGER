/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui.manager;

import controller.MoMayController;
import dao.MayTinhDAO;
import dao.SDMayDAO;
import daoImpl.MayTinhDAOImpl;
import daoImpl.SDMayDAOImpl;
import entity.MayTinh;
import entity.SuDungMay;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import util.Style_Net;
import util.XDialog;

/**
 *
 * @author VINH
 */
public class MoMayJDialog extends javax.swing.JDialog implements MoMayController {

    /**
     * Creates new form MoMayJDialog
     *
     * @param parent
     * @param modal
     */
    private Timer dongHoTimer;
    DefaultTableModel model = new DefaultTableModel();
    SDMayDAO dao = new SDMayDAOImpl();
    List<SuDungMay> items = List.of();
    List<MayTinh> itemscp = List.of();

    // Dynamic real-time Card bindings & F&B cache
    private final Map<String, JLabel> cardTimerMap = new ConcurrentHashMap<>();
    private final Map<String, JLabel> cardTamTinhMap = new ConcurrentHashMap<>();
    private final Map<String, JLabel> cardSubMap = new ConcurrentHashMap<>();
    private final Map<Integer, Double> cacheFoodTotal = new ConcurrentHashMap<>();
    private final Map<Integer, Integer> cacheFoodQty = new ConcurrentHashMap<>();

    public MoMayJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initNavyMoMayTheme();
        setLocationRelativeTo(null);
        FilltblMayTinh();
        FilltblSDMay();
        DongHo();
        NgayHienTai();
    }

    // Modern Card Grid & Inspector components
    private JPanel pnlCardGrid;
    private JScrollPane scrollGrid;
    private String currentFilter = "ALL";
    private MayTinh selectedMachine = null;
    private JLabel lblInspArea;
    private JLabel lblInspTitle;
    private JLabel lblInspSubtitle;
    private JLabel lblInspRateVal;
    private JLabel lblInspStartTimeVal;
    private JLabel lblInspDurationVal;
    private JLabel lblInspMachineBillVal;
    private JLabel lblInspFoodBillVal;
    private JLabel lblInspTotalBillVal;
    private JButton btnActionPrimary;
    private JButton btnActionOrder;
    private JButton btnActionShutdown;
    private JButton btnPillAll;
    private JButton btnPillActive;
    private JButton btnPillFree;
    private JButton btnPillMaint;
    private JLabel lblTopInfoRight;

    private void initNavyMoMayTheme() {
        buildModernMoMayLayout();
    }

    private void buildModernMoMayLayout() {
        setTitle("NET-MANAGER - Sơ Đồ Máy Trạm & Điều Khiển");
        setSize(1320, 760);
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout(0, 0));

        // 1. TOP HEADER BAR
        JPanel pnlTop = new JPanel(new BorderLayout(16, 0));
        pnlTop.setBackground(Color.WHITE);
        pnlTop.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Style_Net.BORDER_HAIRLINE),
            new EmptyBorder(12, 24, 12, 24)
        ));

        // Brand + Subtitle
        JPanel pnlBrand = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        pnlBrand.setOpaque(false);
        JLabel lblB = new JLabel("NET-MANAGER");
        lblB.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblB.setForeground(Style_Net.NAVY_PRIMARY);
        JLabel lblS = new JLabel("HỆ THỐNG QUẢN LÝ PHÒNG MÁY");
        lblS.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblS.setForeground(Style_Net.TEXT_MUTED);
        pnlBrand.add(lblB);
        pnlBrand.add(lblS);
        pnlTop.add(pnlBrand, BorderLayout.WEST);

        // Filter Pills in center
        JPanel pnlFilters = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 2));
        pnlFilters.setOpaque(false);

        btnPillAll = createPillButton("Tất cả máy (13)", true);
        btnPillActive = createPillButton("Đang chơi (4)", false);
        btnPillFree = createPillButton("Máy sẵn sàng (8)", false);
        btnPillMaint = createPillButton("Bảo trì (1)", false);

        btnPillAll.addActionListener(e -> setFilter("ALL"));
        btnPillActive.addActionListener(e -> setFilter("HOAT_DONG"));
        btnPillFree.addActionListener(e -> setFilter("TRONG"));
        btnPillMaint.addActionListener(e -> setFilter("BAO_TRI"));

        pnlFilters.add(btnPillAll);
        pnlFilters.add(btnPillActive);
        pnlFilters.add(btnPillFree);
        pnlFilters.add(btnPillMaint);
        pnlTop.add(pnlFilters, BorderLayout.CENTER);

        // Right Info & Date
        String cashier = (util.XAuth.user != null) ? util.XAuth.user.getTen() : "admin";
        String roleStr = (util.XAuth.isQuanLy()) ? " (Quản lý)" : (util.XAuth.isNhanVien() ? " (Nhân viên)" : "");
        String todayStr = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        lblTopInfoRight = new JLabel("Thu ngân: " + cashier + roleStr + " • " + todayStr);
        lblTopInfoRight.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTopInfoRight.setForeground(Style_Net.TEXT_MUTED);
        pnlTop.add(lblTopInfoRight, BorderLayout.EAST);

        getContentPane().add(pnlTop, BorderLayout.NORTH);

        // 2. MAIN BODY (CENTER)
        JPanel pnlBody = new JPanel(new BorderLayout(18, 0));
        pnlBody.setBackground(Style_Net.BG_CANVAS);
        pnlBody.setBorder(new EmptyBorder(16, 24, 20, 24));

        // LEFT: Sơ đồ máy trạm (4-column card grid in scrollpane)
        JPanel pnlLeft = Style_Net.createCardPanel();
        pnlLeft.setLayout(new BorderLayout(0, 12));

        // Left Header
        JPanel pnlLeftHead = new JPanel(new BorderLayout());
        pnlLeftHead.setOpaque(false);

        JPanel pnlLeftTitles = new JPanel();
        pnlLeftTitles.setLayout(new BoxLayout(pnlLeftTitles, BoxLayout.Y_AXIS));
        pnlLeftTitles.setOpaque(false);

        JLabel lblGridHead = new JLabel("SƠ ĐỒ MÁY TRẠM");
        lblGridHead.setFont(Style_Net.FONT_HEADER);
        lblGridHead.setForeground(Style_Net.NAVY_PRIMARY);

        JLabel lblGridSub = new JLabel("Chọn máy tính để mở phiên, gọi món hoặc thanh toán");
        lblGridSub.setFont(Style_Net.FONT_SMALL);
        lblGridSub.setForeground(Style_Net.TEXT_MUTED);

        pnlLeftTitles.add(lblGridHead);
        pnlLeftTitles.add(Box.createVerticalStrut(2));
        pnlLeftTitles.add(lblGridSub);

        JLabel lblRange = new JLabel("Dàn máy 01 • 8.000 đ/h - 20.000 đ/h");
        lblRange.setFont(Style_Net.FONT_SMALL);
        lblRange.setForeground(Style_Net.TEXT_MUTED);

        pnlLeftHead.add(pnlLeftTitles, BorderLayout.WEST);
        // Put Filter Pills in pnlLeftHead at EAST so they are always visible right above cards
        JPanel pnlPillsBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        pnlPillsBar.setOpaque(false);
        pnlPillsBar.add(btnPillAll);
        pnlPillsBar.add(btnPillActive);
        pnlPillsBar.add(btnPillFree);
        pnlPillsBar.add(btnPillMaint);
        pnlLeftHead.add(pnlPillsBar, BorderLayout.EAST);
        pnlLeft.add(pnlLeftHead, BorderLayout.NORTH);

        // Grid Panel with Wrapper to prevent cards stretching vertically
        pnlCardGrid = new JPanel(new GridLayout(0, 4, 12, 12));
        pnlCardGrid.setBackground(Color.WHITE);

        JPanel pnlGridWrapper = new JPanel(new BorderLayout());
        pnlGridWrapper.setBackground(Color.WHITE);
        pnlGridWrapper.setBorder(new EmptyBorder(8, 8, 8, 8));
        pnlGridWrapper.add(pnlCardGrid, BorderLayout.NORTH);

        scrollGrid = new JScrollPane(pnlGridWrapper);
        scrollGrid.setBorder(null);
        scrollGrid.getViewport().setBackground(Color.WHITE);
        pnlLeft.add(scrollGrid, BorderLayout.CENTER);

        pnlBody.add(pnlLeft, BorderLayout.CENTER);

        // RIGHT: Inspector Panel (~32% width, 360px)
        JPanel pnlInspector = Style_Net.createCardPanel();
        pnlInspector.setPreferredSize(new Dimension(360, 0));
        pnlInspector.setLayout(new BorderLayout(0, 14));

        // Inspector Content (strictly left-aligned)
        JPanel pnlInspContent = new JPanel();
        pnlInspContent.setLayout(new BoxLayout(pnlInspContent, BoxLayout.Y_AXIS));
        pnlInspContent.setOpaque(false);

        lblInspArea = new JLabel("DÀN MÁY THƯỜNG • KHU 01");
        lblInspArea.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblInspArea.setForeground(Style_Net.TEXT_MUTED);
        lblInspArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblInspTitle = new JLabel("Máy 01");
        lblInspTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblInspTitle.setForeground(Style_Net.NAVY_PRIMARY);
        lblInspTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblInspSubtitle = new JLabel("Mã phiên: #SD0028 • Trạng thái: Sẵn sàng");
        lblInspSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblInspSubtitle.setForeground(Style_Net.COLOR_SUCCESS);
        lblInspSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        pnlInspContent.add(lblInspArea);
        pnlInspContent.add(Box.createVerticalStrut(4));
        pnlInspContent.add(lblInspTitle);
        pnlInspContent.add(Box.createVerticalStrut(2));
        pnlInspContent.add(lblInspSubtitle);
        pnlInspContent.add(Box.createVerticalStrut(12));

        JSeparator sep = new JSeparator();
        sep.setForeground(Style_Net.BORDER_HAIRLINE);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlInspContent.add(sep);
        pnlInspContent.add(Box.createVerticalStrut(12));

        // Details key-values
        lblInspRateVal = new JLabel("8.000 đ / giờ");
        lblInspStartTimeVal = new JLabel("--:--:--");
        lblInspDurationVal = new JLabel("--");
        lblInspMachineBillVal = new JLabel("0 ₫");
        lblInspFoodBillVal = new JLabel("0 ₫");

        pnlInspContent.add(createInspRow("Đơn giá áp dụng:", lblInspRateVal));
        pnlInspContent.add(Box.createVerticalStrut(8));
        pnlInspContent.add(createInspRow("Thời điểm mở máy:", lblInspStartTimeVal));
        pnlInspContent.add(Box.createVerticalStrut(8));
        pnlInspContent.add(createInspRow("Thời gian đã chơi:", lblInspDurationVal));
        pnlInspContent.add(Box.createVerticalStrut(8));
        pnlInspContent.add(createInspRow("Tiền giờ chơi tạm tính:", lblInspMachineBillVal));
        pnlInspContent.add(Box.createVerticalStrut(8));
        pnlInspContent.add(createInspRow("Dịch vụ ăn uống (F&B):", lblInspFoodBillVal));
        pnlInspContent.add(Box.createVerticalStrut(14));

        // Total Box
        JPanel pnlTotalBox = new JPanel(new BorderLayout(0, 4));
        pnlTotalBox.setBackground(new Color(0xF8, 0xFA, 0xFC));
        pnlTotalBox.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Style_Net.BORDER_HAIRLINE, 1, true),
            new EmptyBorder(12, 14, 12, 14)
        ));
        pnlTotalBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        pnlTotalBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTotalLabel = new JLabel("TỔNG TẠM TÍNH HIỆN TẠI");
        lblTotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTotalLabel.setForeground(Style_Net.TEXT_MUTED);

        lblInspTotalBillVal = new JLabel("0 ₫");
        lblInspTotalBillVal.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblInspTotalBillVal.setForeground(Style_Net.NAVY_PRIMARY);

        pnlTotalBox.add(lblTotalLabel, BorderLayout.NORTH);
        pnlTotalBox.add(lblInspTotalBillVal, BorderLayout.CENTER);
        pnlInspContent.add(pnlTotalBox);

        // Wrap Inspector Content to anchor at top
        JPanel pnlInspTopWrapper = new JPanel(new BorderLayout());
        pnlInspTopWrapper.setOpaque(false);
        pnlInspTopWrapper.add(pnlInspContent, BorderLayout.NORTH);
        pnlInspector.add(pnlInspTopWrapper, BorderLayout.CENTER);

        // Action Buttons at bottom (clean text, no broken unicode symbols)
        JPanel pnlInspActions = new JPanel();
        pnlInspActions.setLayout(new BoxLayout(pnlInspActions, BoxLayout.Y_AXIS));
        pnlInspActions.setOpaque(false);

        btnActionPrimary = new JButton("MỞ MÁY SỬ DỤNG");
        Style_Net.stylePrimaryButton(btnActionPrimary);
        btnActionPrimary.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btnActionPrimary.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnActionOrder = new JButton("GỌI MÓN / DỊCH VỤ F&B");
        Style_Net.styleSecondaryButton(btnActionOrder);
        btnActionOrder.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnActionOrder.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnActionShutdown = new JButton("TẮT MÁY TẠM THỜI");
        Style_Net.styleDangerButton(btnActionShutdown);
        btnActionShutdown.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btnActionShutdown.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnActionPrimary.addActionListener(e -> {
            if (selectedMachine == null) return;
            boolean isAct = "Hoạt động".equalsIgnoreCase(selectedMachine.getTrangThai()) || "Đang dùng".equalsIgnoreCase(selectedMachine.getTrangThai()) || "Đang chơi".equalsIgnoreCase(selectedMachine.getTrangThai());
            if (isAct) {
                openThanhToan();
            } else {
                MoMay();
            }
        });

        btnActionOrder.addActionListener(e -> openMenu());
        btnActionShutdown.addActionListener(e -> TatMay());

        pnlInspActions.add(btnActionPrimary);
        pnlInspActions.add(Box.createVerticalStrut(8));
        pnlInspActions.add(btnActionOrder);
        pnlInspActions.add(Box.createVerticalStrut(8));
        pnlInspActions.add(btnActionShutdown);

        pnlInspector.add(pnlInspActions, BorderLayout.SOUTH);
        pnlBody.add(pnlInspector, BorderLayout.EAST);

        getContentPane().add(pnlBody, BorderLayout.CENTER);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private JButton createPillButton(String text, boolean active) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (active) {
            btn.setBackground(Style_Net.NAVY_PRIMARY);
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Style_Net.NAVY_PRIMARY, 1, true),
                new EmptyBorder(6, 14, 6, 14)
            ));
        } else {
            btn.setBackground(Color.WHITE);
            btn.setForeground(Style_Net.NAVY_PRIMARY);
            btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Style_Net.BORDER_INPUT, 1, true),
                new EmptyBorder(6, 14, 6, 14)
            ));
        }
        return btn;
    }

    private JPanel createInspRow(String title, JLabel valLabel) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        t.setForeground(Style_Net.TEXT_MUTED);

        valLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        valLabel.setForeground(Style_Net.NAVY_PRIMARY);

        row.add(t, BorderLayout.WEST);
        row.add(valLabel, BorderLayout.EAST);
        return row;
    }

    private void setFilter(String filter) {
        this.currentFilter = filter;
        btnPillAll.setBackground("ALL".equals(filter) ? Style_Net.NAVY_PRIMARY : Color.WHITE);
        btnPillAll.setForeground("ALL".equals(filter) ? Color.WHITE : Style_Net.NAVY_PRIMARY);

        btnPillActive.setBackground("HOAT_DONG".equals(filter) ? Style_Net.NAVY_PRIMARY : Color.WHITE);
        btnPillActive.setForeground("HOAT_DONG".equals(filter) ? Color.WHITE : Style_Net.NAVY_PRIMARY);

        btnPillFree.setBackground("TRONG".equals(filter) ? Style_Net.NAVY_PRIMARY : Color.WHITE);
        btnPillFree.setForeground("TRONG".equals(filter) ? Color.WHITE : Style_Net.NAVY_PRIMARY);

        btnPillMaint.setBackground("BAO_TRI".equals(filter) ? Style_Net.NAVY_PRIMARY : Color.WHITE);
        btnPillMaint.setForeground("BAO_TRI".equals(filter) ? Color.WHITE : Style_Net.NAVY_PRIMARY);

        renderCardGrid();
    }

    private SuDungMay findActiveSession(String tenMay) {
        if (items != null) {
            for (SuDungMay s : items) {
                if (tenMay.equalsIgnoreCase(s.getTenMay()) &&
                    ("Hoạt động".equalsIgnoreCase(s.getTrangThai()) || "Chưa thanh toán".equalsIgnoreCase(s.getTrangThai()))) {
                    return s;
                }
            }
        }
        return null;
    }

    private void openThanhToan() {
        if (selectedMachine == null) return;
        SuDungMay sdm = findActiveSession(selectedMachine.getTenMay());
        String maSD = (sdm != null) ? String.valueOf(sdm.getId()) : "1";
        SimpleDateFormat sdfD = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm:ss");
        String nChoi = (sdm != null && sdm.getNgayChoi() != null) ? sdfD.format(sdm.getNgayChoi()) : sdfD.format(new Date());
        String nNghi = sdfD.format(new Date());
        String gVao = (sdm != null && sdm.getGioBatDau() != null) ? sdm.getGioBatDau().toString() : sdfT.format(new Date());
        String gNghi = sdfT.format(new Date());
        float giah = selectedMachine.getGiaTheoGio();

        ThanhToanJDialog dialog = new ThanhToanJDialog(null, true, maSD, selectedMachine.getTenMay(), nChoi, nNghi, gVao, gNghi, giah);
        dialog.setVisible(true);
        refreshFoodCache();
        FilltblSDMay();
        FilltblMayTinh();
        renderCardGrid();
        if (selectedMachine != null) selectMachine(selectedMachine.getTenMay());
    }

    private void openMenu() {
        if (selectedMachine == null) return;
        SuDungMay sdm = findActiveSession(selectedMachine.getTenMay());
        String maSD = (sdm != null) ? String.valueOf(sdm.getId()) : "1";
        MenuJDialog menu = new MenuJDialog(null, true, maSD, selectedMachine.getTenMay());
        menu.setVisible(true);
        refreshFoodCache();
        FilltblSDMay();
        FilltblMayTinh();
        renderCardGrid();
        if (selectedMachine != null) selectMachine(selectedMachine.getTenMay());
    }

    private long calculateElapsedSeconds(SuDungMay sdm) {
        if (sdm == null || sdm.getGioBatDau() == null) return 0;
        try {
            LocalDate playDate = (sdm.getNgayChoi() != null) ? sdm.getNgayChoi().toLocalDate() : LocalDate.now();
            LocalTime startTime = sdm.getGioBatDau().toLocalTime();
            LocalDateTime startDt = LocalDateTime.of(playDate, startTime);
            LocalDateTime now = LocalDateTime.now();
            long diff = Duration.between(startDt, now).getSeconds();
            return Math.max(0, diff);
        } catch (Exception e) {
            return 0;
        }
    }

    private double calculateMachineFee(long elapsedSeconds, float giaTheoGio) {
        if (elapsedSeconds <= 0) return 0;
        long minutes = elapsedSeconds / 60;
        long seconds = elapsedSeconds % 60;
        if (seconds >= 30) {
            minutes++;
        }
        if (minutes == 0 && elapsedSeconds > 0) {
            minutes = 1;
        }
        double hoursPlayed = Math.ceil((minutes / 60.0) * 100.0) / 100.0;
        return Math.ceil((hoursPlayed * giaTheoGio) * 100.0) / 100.0;
    }

    private void refreshFoodCache() {
        cacheFoodTotal.clear();
        cacheFoodQty.clear();
        String sql = "SELECT MaSDMay, ISNULL(SUM(TongTien), 0) AS TotalMoney, ISNULL(SUM(SoLuong), 0) AS TotalQty FROM Menu GROUP BY MaSDMay";
        try (java.sql.ResultSet rs = util.XJdbc.executeQuery(sql)) {
            while (rs != null && rs.next()) {
                int maSD = rs.getInt("MaSDMay");
                double total = rs.getDouble("TotalMoney");
                int qty = rs.getInt("TotalQty");
                cacheFoodTotal.put(maSD, total);
                cacheFoodQty.put(maSD, qty);
            }
        } catch (Exception ignored) {
        }
    }

    private double getFoodTotal(int maSD) {
        return cacheFoodTotal.getOrDefault(maSD, 0.0);
    }

    private int getFoodQty(int maSD) {
        return cacheFoodQty.getOrDefault(maSD, 0);
    }

    private void updateLiveTimers() {
        if (itemscp == null) return;
        for (MayTinh mt : itemscp) {
            String tt = mt.getTrangThai();
            boolean isAct = "Hoạt động".equalsIgnoreCase(tt) || "Đang dùng".equalsIgnoreCase(tt) || "Đang chơi".equalsIgnoreCase(tt);
            if (isAct) {
                SuDungMay sdm = findActiveSession(mt.getTenMay());
                if (sdm != null) {
                    long elapsed = calculateElapsedSeconds(sdm);
                    long h = elapsed / 3600;
                    long m = (elapsed % 3600) / 60;
                    long s = elapsed % 60;
                    String timeStr = String.format("%02d:%02d:%02d", h, m, s);
                    double tienGio = calculateMachineFee(elapsed, mt.getGiaTheoGio());
                    double tienFnb = getFoodTotal(sdm.getId());

                    JLabel lblTimer = cardTimerMap.get(mt.getTenMay());
                    if (lblTimer != null) {
                        lblTimer.setText(timeStr);
                    }
                    JLabel lblTamTinh = cardTamTinhMap.get(mt.getTenMay());
                    if (lblTamTinh != null) {
                        lblTamTinh.setText("Tạm tính: " + Style_Net.formatMoney(tienGio + tienFnb));
                    }
                }
            }
        }

        if (selectedMachine != null) {
            String tt = selectedMachine.getTrangThai();
            boolean isAct = "Hoạt động".equalsIgnoreCase(tt) || "Đang dùng".equalsIgnoreCase(tt) || "Đang chơi".equalsIgnoreCase(tt);
            if (isAct && lblInspDurationVal != null) {
                SuDungMay sdm = findActiveSession(selectedMachine.getTenMay());
                if (sdm != null) {
                    long elapsed = calculateElapsedSeconds(sdm);
                    long h = elapsed / 3600;
                    long m = (elapsed % 3600) / 60;
                    long s = elapsed % 60;
                    String durText = (h > 0) ? String.format("%02d giờ %02d phút %02d giây", h, m, s) : String.format("%02d phút %02d giây", m, s);
                    lblInspDurationVal.setText(durText);

                    double tienGio = calculateMachineFee(elapsed, selectedMachine.getGiaTheoGio());
                    double tienFnb = getFoodTotal(sdm.getId());
                    int foodQty = getFoodQty(sdm.getId());

                    if (lblInspMachineBillVal != null) {
                        lblInspMachineBillVal.setText(Style_Net.formatMoney(tienGio));
                    }
                    if (lblInspFoodBillVal != null) {
                        lblInspFoodBillVal.setText(Style_Net.formatMoney(tienFnb) + (foodQty > 0 ? " (" + foodQty + " món)" : " (0 món)"));
                    }
                    if (lblInspTotalBillVal != null) {
                        lblInspTotalBillVal.setText(Style_Net.formatMoney(tienGio + tienFnb));
                    }
                }
            }
        }
    }

    public void selectMachine(String tenMay) {
        if (itemscp != null) {
            for (MayTinh m : itemscp) {
                if (m.getTenMay().equals(tenMay)) {
                    selectedMachine = m;
                    break;
                }
            }
        }
        if (selectedMachine == null) return;

        if (lblTenMay != null) lblTenMay.setText(selectedMachine.getTenMay());
        if (lblGiaTheoGio != null) lblGiaTheoGio.setText(String.valueOf(selectedMachine.getGiaTheoGio()));
        if (lblTrangThai != null) lblTrangThai.setText(selectedMachine.getTrangThai());

        SuDungMay sdm = findActiveSession(selectedMachine.getTenMay());
        if (sdm != null) {
            if (lblMaSd != null) lblMaSd.setText(String.valueOf(sdm.getId()));
            if (lblGioBatDau != null) lblGioBatDau.setText(sdm.getGioBatDau() != null ? sdm.getGioBatDau().toString() : new SimpleDateFormat("HH:mm:ss").format(new Date()));
        } else {
            if (lblMaSd != null) lblMaSd.setText("");
            if (lblGioBatDau != null) lblGioBatDau.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }

        updateInspectorUI();
        renderCardGrid();
    }

    private void updateInspectorUI() {
        if (selectedMachine == null || lblInspTitle == null) return;
        boolean isAct = "Hoạt động".equalsIgnoreCase(selectedMachine.getTrangThai()) || "Đang dùng".equalsIgnoreCase(selectedMachine.getTrangThai()) || "Đang chơi".equalsIgnoreCase(selectedMachine.getTrangThai());
        boolean isMaint = "Bảo trì".equalsIgnoreCase(selectedMachine.getTrangThai());
        SuDungMay sdm = findActiveSession(selectedMachine.getTenMay());

        if (lblInspArea != null && selectedMachine.getTenMay() != null) {
            String u = selectedMachine.getTenMay().toUpperCase();
            if (u.contains("VIP")) {
                lblInspArea.setText("DÀN MÁY VIP • KHU CAO CẤP");
            } else if (u.contains("THI ĐẤU")) {
                lblInspArea.setText("DÀN MÁY THI ĐẤU • KHU ESPORTS");
            } else {
                lblInspArea.setText("DÀN MÁY THƯỜNG • KHU 01");
            }
        }

        lblInspTitle.setText(selectedMachine.getTenMay());
        lblInspRateVal.setText(Style_Net.formatMoney(selectedMachine.getGiaTheoGio()) + " / giờ");

        if (isAct) {
            String sessId = (sdm != null) ? "#SD" + String.format("%04d", sdm.getId()) : "#SD----";
            lblInspSubtitle.setText("Mã phiên: " + sessId + " • Trạng thái: Đang hoạt động");
            lblInspSubtitle.setForeground(Style_Net.NAVY_ACCENT);

            String startTime = (sdm != null && sdm.getGioBatDau() != null) ? sdm.getGioBatDau().toString() : "--:--:--";
            lblInspStartTimeVal.setText(startTime + " (Hôm nay)");

            long elapsed = calculateElapsedSeconds(sdm);
            long h = elapsed / 3600;
            long m = (elapsed % 3600) / 60;
            long s = elapsed % 60;
            String durText = (h > 0) ? String.format("%02d giờ %02d phút %02d giây", h, m, s) : String.format("%02d phút %02d giây", m, s);
            lblInspDurationVal.setText(durText);

            double tienGio = calculateMachineFee(elapsed, selectedMachine.getGiaTheoGio());
            double tienFnB = (sdm != null) ? getFoodTotal(sdm.getId()) : 0.0;
            int foodQty = (sdm != null) ? getFoodQty(sdm.getId()) : 0;

            lblInspMachineBillVal.setText(Style_Net.formatMoney(tienGio));
            lblInspFoodBillVal.setText(Style_Net.formatMoney(tienFnB) + (foodQty > 0 ? " (" + foodQty + " món)" : " (0 món)"));
            lblInspTotalBillVal.setText(Style_Net.formatMoney(tienGio + tienFnB));

            btnActionPrimary.setText("THANH TOÁN & TRẢ MÁY");
            btnActionPrimary.setEnabled(true);
            btnActionOrder.setVisible(true);
            btnActionShutdown.setVisible(true);
        } else if (isMaint) {
            lblInspSubtitle.setText("Trạng thái: Đang bảo trì");
            lblInspSubtitle.setForeground(Style_Net.COLOR_WARNING);
            lblInspStartTimeVal.setText("--:--:--");
            lblInspDurationVal.setText("--");
            lblInspMachineBillVal.setText("0 ₫");
            lblInspFoodBillVal.setText("0 ₫");
            lblInspTotalBillVal.setText("0 ₫");

            btnActionPrimary.setText("MÁY ĐANG BẢO TRÌ");
            btnActionPrimary.setEnabled(false);
            btnActionOrder.setVisible(false);
            btnActionShutdown.setVisible(false);
        } else {
            lblInspSubtitle.setText("Trạng thái: Máy sẵn sàng phục vụ");
            lblInspSubtitle.setForeground(Style_Net.COLOR_SUCCESS);
            lblInspStartTimeVal.setText("--:--:--");
            lblInspDurationVal.setText("--");
            lblInspMachineBillVal.setText("0 ₫");
            lblInspFoodBillVal.setText("0 ₫");
            lblInspTotalBillVal.setText("0 ₫");

            btnActionPrimary.setText("MỞ MÁY SỬ DỤNG");
            btnActionPrimary.setEnabled(true);
            btnActionOrder.setVisible(false);
            btnActionShutdown.setVisible(false);
        }
    }

    public void renderCardGrid() {
        if (pnlCardGrid == null) return;
        pnlCardGrid.removeAll();
        cardTimerMap.clear();
        cardTamTinhMap.clear();
        cardSubMap.clear();

        List<MayTinh> list = itemscp;
        if (list == null || list.isEmpty() || list.size() < 13) {
            try {
                list = new MayTinhDAOImpl().findAll();
                itemscp = list;
            } catch (Exception e) {
                list = dao.finMayTinh();
                itemscp = list;
            }
        }

        int countAll = list.size();
        int countActive = 0;
        int countFree = 0;
        int countMaint = 0;

        for (MayTinh mt : list) {
            String tt = mt.getTrangThai();
            if ("Hoạt động".equalsIgnoreCase(tt) || "Đang dùng".equalsIgnoreCase(tt) || "Đang chơi".equalsIgnoreCase(tt)) {
                countActive++;
            } else if ("Bảo trì".equalsIgnoreCase(tt)) {
                countMaint++;
            } else {
                countFree++;
            }
        }

        if (btnPillAll != null) btnPillAll.setText("Tất cả máy (" + countAll + ")");
        if (btnPillActive != null) btnPillActive.setText("Đang chơi (" + countActive + ")");
        if (btnPillFree != null) btnPillFree.setText("Máy sẵn sàng (" + countFree + ")");
        if (btnPillMaint != null) btnPillMaint.setText("Bảo trì (" + countMaint + ")");

        for (MayTinh mt : list) {
            String tt = mt.getTrangThai();
            boolean isAct = "Hoạt động".equalsIgnoreCase(tt) || "Đang dùng".equalsIgnoreCase(tt) || "Đang chơi".equalsIgnoreCase(tt);
            boolean isMaint = "Bảo trì".equalsIgnoreCase(tt);
            boolean isFree = !isAct && !isMaint;

            if ("HOAT_DONG".equals(currentFilter) && !isAct) continue;
            if ("TRONG".equals(currentFilter) && !isFree) continue;
            if ("BAO_TRI".equals(currentFilter) && !isMaint) continue;

            JPanel card = createMachineCard(mt, isAct, isFree, isMaint);
            pnlCardGrid.add(card);
        }

        pnlCardGrid.revalidate();
        pnlCardGrid.repaint();
    }

    private JPanel createMachineCard(MayTinh mt, boolean isAct, boolean isFree, boolean isMaint) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(0, 6));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(175, 120));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boolean isSelected = (selectedMachine != null && selectedMachine.getTenMay().equals(mt.getTenMay()));
        if (isSelected) {
            card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Style_Net.NAVY_PRIMARY, 2, true),
                new EmptyBorder(8, 12, 8, 12)
            ));
        } else {
            card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Style_Net.BORDER_HAIRLINE, 1, true),
                new EmptyBorder(9, 13, 9, 13)
            ));
        }

        // Top Row: Machine name on left, badge on right
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setOpaque(false);
        JLabel lblName = new JLabel(mt.getTenMay());
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblName.setForeground(Style_Net.NAVY_PRIMARY);
        pnlTop.add(lblName, BorderLayout.WEST);

        JLabel badge;
        if (isAct) {
            badge = Style_Net.createBadge("ĐANG CHƠI", "blue");
        } else if (isMaint) {
            badge = Style_Net.createBadge("BẢO TRÌ", "gray");
        } else {
            badge = Style_Net.createBadge("TRỐNG", "green");
        }
        pnlTop.add(badge, BorderLayout.EAST);
        card.add(pnlTop, BorderLayout.NORTH);

        // Center / Info Body
        JPanel pnlBody = new JPanel();
        pnlBody.setLayout(new BoxLayout(pnlBody, BoxLayout.Y_AXIS));
        pnlBody.setOpaque(false);

        if (isAct) {
            SuDungMay sdm = findActiveSession(mt.getTenMay());
            long elapsed = calculateElapsedSeconds(sdm);
            long h = elapsed / 3600;
            long m = (elapsed % 3600) / 60;
            long s = elapsed % 60;
            String timeStr = String.format("%02d:%02d:%02d", h, m, s);

            JLabel lblTimer = new JLabel(timeStr);
            lblTimer.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lblTimer.setForeground(Style_Net.NAVY_PRIMARY);

            double tienGio = calculateMachineFee(elapsed, mt.getGiaTheoGio());
            double tienFnB = (sdm != null) ? getFoodTotal(sdm.getId()) : 0.0;
            int foodQty = (sdm != null) ? getFoodQty(sdm.getId()) : 0;

            JLabel lblTamTinh = new JLabel("Tạm tính: " + Style_Net.formatMoney(tienGio + tienFnB));
            lblTamTinh.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblTamTinh.setForeground(Style_Net.COLOR_SUCCESS);

            String subText = "Đang phục vụ" + (foodQty > 0 ? " • " + foodQty + " món" : " • Chưa gọi món");
            JLabel lblSub = new JLabel(subText);
            lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            lblSub.setForeground(Style_Net.TEXT_MUTED);

            cardTimerMap.put(mt.getTenMay(), lblTimer);
            cardTamTinhMap.put(mt.getTenMay(), lblTamTinh);
            cardSubMap.put(mt.getTenMay(), lblSub);

            pnlBody.add(lblTimer);
            pnlBody.add(Box.createVerticalStrut(2));
            pnlBody.add(lblTamTinh);
            pnlBody.add(Box.createVerticalStrut(2));
            pnlBody.add(lblSub);
        } else if (isMaint) {
            JLabel lblReason = new JLabel("Tạm ngừng phục vụ");
            lblReason.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblReason.setForeground(Style_Net.TEXT_MUTED);

            JLabel lblWait = new JLabel("Đang bảo trì thiết bị");
            lblWait.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            lblWait.setForeground(new Color(0x94, 0xA3, 0xB8));

            pnlBody.add(Box.createVerticalStrut(6));
            pnlBody.add(lblReason);
            pnlBody.add(Box.createVerticalStrut(4));
            pnlBody.add(lblWait);
        } else {
            JLabel lblRate = new JLabel(Style_Net.formatMoney(mt.getGiaTheoGio()) + "/giờ");
            lblRate.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblRate.setForeground(Style_Net.NAVY_PRIMARY);

            String desc = "Máy thường • Sẵn sàng";
            if (mt.getTenMay() != null) {
                String upper = mt.getTenMay().toUpperCase();
                if (upper.contains("VIP")) {
                    desc = "Máy VIP • Sẵn sàng";
                } else if (upper.contains("THI ĐẤU")) {
                    desc = "Máy Thi Đấu • Sẵn sàng";
                }
            }
            JLabel lblSub = new JLabel(desc);
            lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblSub.setForeground(Style_Net.TEXT_MUTED);

            pnlBody.add(Box.createVerticalStrut(4));
            pnlBody.add(lblRate);
            pnlBody.add(Box.createVerticalStrut(4));
            pnlBody.add(lblSub);
        }
        card.add(pnlBody, BorderLayout.CENTER);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectMachine(mt.getTenMay());
            }
        });

        return card;
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
        jPanel1 = new javax.swing.JPanel();
        lblNgayHienTai = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblGioBatDau = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblGiaTheoGio = new javax.swing.JLabel();
        lblTenMay = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        btnMo = new javax.swing.JButton();
        btnTat = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSDM = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        lblMaSd = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblMayTinh = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Trang mở máy\n");
        setIconImage(    java.awt.Toolkit.getDefaultToolkit().getImage(
            "C:/Users/NITRO 5/Documents/NetBeansProjects/NET-MANAGER/NET-MANAGER-main/src/main/java/img/backroundMoMay.jpg")

    );
    setIconImages(null);
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

    jPanel1.setBackground(new java.awt.Color(204, 204, 204));

    lblNgayHienTai.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
    lblNgayHienTai.setText("Ngày hiện tại");

    jLabel5.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
    jLabel5.setText("Giờ bắt đầu :");

    lblGioBatDau.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N

    lblTrangThai.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
    lblTrangThai.setText("........................................");

    jLabel4.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
    jLabel4.setText("Trạng thái :");

    lblGiaTheoGio.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
    lblGiaTheoGio.setText(".................................................");

    lblTenMay.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
    lblTenMay.setText("........................");

    jLabel1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
    jLabel1.setText("Tên Máy :");

    jLabel6.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
    jLabel6.setText("Giá theo giờ :");

    btnXoa.setBackground(new java.awt.Color(193, 189, 189));
    btnXoa.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
    btnXoa.setForeground(new java.awt.Color(102, 102, 102));
    btnXoa.setText("Làm mới");
    btnXoa.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnXoaActionPerformed(evt);
        }
    });

    btnMo.setBackground(new java.awt.Color(22, 163, 74));
    btnMo.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
    btnMo.setForeground(new java.awt.Color(255, 255, 255));
    btnMo.setText("Mở");
    btnMo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnMoActionPerformed(evt);
        }
    });

    btnTat.setBackground(new java.awt.Color(220, 38, 38));
    btnTat.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
    btnTat.setForeground(new java.awt.Color(255, 255, 255));
    btnTat.setText("Tắt");
    btnTat.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnTatActionPerformed(evt);
        }
    });

    btnMenu.setBackground(new java.awt.Color(0, 0, 0));
    btnMenu.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
    btnMenu.setForeground(new java.awt.Color(255, 255, 255));
    btnMenu.setText("Menu");
    btnMenu.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnMenuActionPerformed(evt);
        }
    });

    btnThanhToan.setBackground(new java.awt.Color(0, 0, 0));
    btnThanhToan.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
    btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
    btnThanhToan.setText("Thanh toán");
    btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnThanhToanActionPerformed(evt);
        }
    });

    tblSDM.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
    tblSDM.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null}
        },
        new String [] {
            "Mã SD", "Tên máy", "Giá  tiền theo giờ", "Ngày chơi", "Ngày kết thúc", "Giờ bắt đầu", "Giờ kết thúc", "Trạng thái"
        }
    ));
    tblSDM.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblSDMMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tblSDM);

    jLabel8.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
    jLabel8.setText("Danh sách sử dụng");

    lblMaSd.setBackground(new java.awt.Color(204, 204, 204));
    lblMaSd.setForeground(new java.awt.Color(204, 204, 204));

    jPanel14.setBackground(new java.awt.Color(13, 71, 161));

    jLabel3.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
    jLabel3.setText("Danh sách máy");

    tblMayTinh.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
    tblMayTinh.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null},
            {null, null, null},
            {null, null, null},
            {null, null, null}
        },
        new String [] {
            "Tên máy", "Trạng thái", "Giá theo giờ"
        }
    ));
    tblMayTinh.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tblMayTinhMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            tblMayTinhMouseEntered(evt);
        }
    });
    jScrollPane15.setViewportView(tblMayTinh);

    javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
    jPanel14.setLayout(jPanel14Layout);
    jPanel14Layout.setHorizontalGroup(
        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        .addGroup(jPanel14Layout.createSequentialGroup()
            .addGap(44, 44, 44)
            .addComponent(jLabel3)
            .addContainerGap(44, Short.MAX_VALUE))
    );
    jPanel14Layout.setVerticalGroup(
        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel14Layout.createSequentialGroup()
            .addGap(32, 32, 32)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(52, 52, 52)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(16, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(lblMaSd, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(514, 514, 514)
                    .addComponent(lblNgayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)
                    .addComponent(lblTenMay, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(112, 112, 112)
                    .addComponent(jLabel5)
                    .addGap(6, 6, 6)
                    .addComponent(lblGioBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(jLabel6)
                    .addGap(6, 6, 6)
                    .addComponent(lblGiaTheoGio)
                    .addGap(86, 86, 86)
                    .addComponent(jLabel4)
                    .addGap(13, 13, 13)
                    .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(279, 279, 279)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnMo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnTat, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnThanhToan))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblMaSd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNgayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTenMay, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(lblGioBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(4, 4, 4)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblGiaTheoGio, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(21, 21, 21)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnMo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnTat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(22, 22, 22)
            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10, 10, 10)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        DongHo();
        NgayHienTai();
    }//GEN-LAST:event_formWindowOpened

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void tblMayTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMayTinhMouseClicked
        int index = tblMayTinh.getSelectedRow();
        filltblMayTinh(index);
    }//GEN-LAST:event_tblMayTinhMouseClicked

    private void tblSDMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSDMMouseClicked
        int index = tblSDM.getSelectedRow();
        filltblSDMAY(index);
    }//GEN-LAST:event_tblSDMMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (lblTrangThai.getText().equals("Đã thanh toán")) {
            XDialog.alert("Máy đã thanh toán không thể thanh toán tiếp");
            return;
        }
        if (lblTrangThai.getText().equals("Trống")) {
            showSuccessDialog("Vui lòng chọn máy từ bảng Danh sách sử dụng máy ", 2000);
            return;
        }
        if (lblTrangThai.getText().equals("Bảo trì")) {
            showSuccessDialog("Vui lòng chọn máy từ bảng Danh sách sử dụng máy ", 2000);
            return;
        }

        try {
            int selectedRow = tblSDM.getSelectedRow();
            if (selectedRow < 0) {
                XDialog.alert("Vui lòng chọn máy bạn muốn thanh toán");
                return;
            }
            if (selectedRow >= 0) {
                String maMay = tblSDM.getValueAt(selectedRow, 0).toString();
                String tenMay = tblSDM.getValueAt(selectedRow, 1).toString();
                String ngayVao = tblSDM.getValueAt(selectedRow, 3).toString();
                String ngayNghi = tblSDM.getValueAt(selectedRow, 4).toString();
                String gioVao = tblSDM.getValueAt(selectedRow, 5).toString();
                String gioNghi = tblSDM.getValueAt(selectedRow, 6).toString();
                float giaTheoh = (Float) tblSDM.getValueAt(selectedRow, 2);

                ThanhToanJDialog dialog = new ThanhToanJDialog(null, true, maMay, tenMay, ngayVao, ngayNghi, gioVao, gioNghi, giaTheoh);
                dialog.setVisible(true);
            }
        } catch (Exception e) {
            XDialog.alert("Máy đang hoạt động");
        }
        FilltblMayTinh();
        FilltblSDMay();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        String maMay = lblMaSd.getText().trim();
        String tenMay = lblTenMay.getText();
        String trangthai = lblTrangThai.getText().trim();
        MenuJDialog menuDialog = new MenuJDialog((java.awt.Frame) this.getParent(), true, maMay, tenMay);
        if (trangthai.equals("Đã thanh toán")) {
            XDialog.alert("Máy đã thanh toán không thể mua tiếp");
            return;
        }
        if (maMay.isEmpty()) {
            showSuccessDialog("Bạn phải chọn máy đang hoạt động tại danh sách máy sử dụng", 4000);
            menuDialog.setVisible(false);
            return;
        }

        if (maMay == null || maMay.equals("MT") || maMay.trim().isEmpty()) {
            XDialog.alert("Vui lòng chọn một máy trước khi mở menu.");
            return;
        }
        menuDialog.setVisible(true);
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnTatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTatActionPerformed
        int row = tblSDM.getSelectedRow();
        if (row < 0) {
            XDialog.alert("Vui lòng chọn máy bạn muốn tắt");
            return;
        }
        String trangthai = lblTrangThai.getText().trim();
        if (trangthai.equals("Chưa thanh toán") || trangthai.equals("Trống")) {
            XDialog.alert("Máy đã tắt");
            return;
        } else if (trangthai.equals("Đã thanh toán")) {
            XDialog.alert("Máy đã thanh toán");
            return;
        }
        TatMay();
        Clear();
    }//GEN-LAST:event_btnTatActionPerformed

    private void btnMoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoActionPerformed
        int selectedRow = tblMayTinh.getSelectedRow();
        String trangthai = lblTrangThai.getText().trim();
        if (selectedRow < 0) {
            XDialog.alert("Vui lòng chọn máy bạn muốn mở");
            return;
        }

        if (trangthai.equals("Hoạt động")) {
            XDialog.alert("Máy đã hoạt động, không thể mở tiếp");
            return;
        }
        if (trangthai.equals("Bảo trì")) {
            showSuccessDialog("Máy hiện đang bảo trì vui lòng chọn máy khác", 2000);
            return;
        }

        MoMay();
        Clear();
    }//GEN-LAST:event_btnMoActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        Clear();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblMayTinhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMayTinhMouseEntered
        CapNhat();
    }//GEN-LAST:event_tblMayTinhMouseEntered

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

    public void filltblMayTinh(int row) {
        String Ten = tblMayTinh.getValueAt(row, 0).toString();
        String Trang = tblMayTinh.getValueAt(row, 1).toString();
        String Gia = tblMayTinh.getValueAt(row, 2).toString();
        lblTenMay.setText(Ten);
        lblGiaTheoGio.setText(Gia);
        lblTrangThai.setText(Trang);
    }

    public void filltblSDMAY(int row) {
        String id = tblSDM.getValueAt(row, 0).toString();
        String Ten = tblSDM.getValueAt(row, 1).toString();
        String GBD = tblSDM.getValueAt(row, 5).toString();
        String Trang = tblSDM.getValueAt(row, 7).toString();
        String Gia = tblSDM.getValueAt(row, 2).toString();
        lblGioBatDau.setText(GBD);
        lblMaSd.setText(id);
        lblTenMay.setText(Ten);
        lblGiaTheoGio.setText(Gia);
        lblTrangThai.setText(Trang);
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

    public void CapNhat() {
        lblMaSd.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnMo;
    private javax.swing.JButton btnTat;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblGiaTheoGio;
    private javax.swing.JLabel lblGioBatDau;
    private javax.swing.JLabel lblMaSd;
    private javax.swing.JLabel lblNgayHienTai;
    private javax.swing.JLabel lblTenMay;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JTable tblMayTinh;
    private javax.swing.JTable tblSDM;
    // End of variables declaration//GEN-END:variables
    @Override
    public void FilltblMayTinh() {
        model = (DefaultTableModel) tblMayTinh.getModel();
        model.setRowCount(0);
        itemscp = dao.finMayTinh();
        itemscp.forEach(i -> {
            Object[] rowdata = {
                i.getTenMay(),
                i.getTrangThai(),
                i.getGiaTheoGio()
            };
            model.addRow(rowdata);
        });
        renderCardGrid();
        if (selectedMachine == null && !itemscp.isEmpty()) {
            selectMachine(itemscp.get(0).getTenMay());
        }
    }

    @Override
    public void FilltblSDMay() {
        model = (DefaultTableModel) tblSDM.getModel();
        model.setRowCount(0);
        items = dao.findAll();
        items.forEach(i -> {
            Object[] rowdata = {
                i.getId(),
                i.getTenMay(),
                i.getGiaTheoGio(),
                i.getNgayChoi(),
                i.getNgayKetThuc(),
                i.getGioBatDau(),
                i.getGioKetThuc(),
                i.getTrangThai(),};
            model.addRow(rowdata);
        });
        refreshFoodCache();
        renderCardGrid();
    }

    @Override
    public SuDungMay getFromOne() {
        SuDungMay sdm = new SuDungMay();
        Time now = new Time(System.currentTimeMillis());
        sdm.setGioBatDau(now);
        sdm.setGioKetThuc(now);
        sdm.setNgayChoi(new java.sql.Date(System.currentTimeMillis()));
        sdm.setNgayKetThuc(new java.sql.Date(System.currentTimeMillis()));
        if (selectedMachine != null) {
            sdm.setGiaTheoGio(selectedMachine.getGiaTheoGio());
        } else {
            sdm.setGiaTheoGio(Float.parseFloat(lblGiaTheoGio.getText()));
        }
        return sdm;
    }

    @Override
    public SuDungMay getFromBytoShutdow() {
        SuDungMay sdm = new SuDungMay();
        if (selectedMachine != null) {
            SuDungMay active = findActiveSession(selectedMachine.getTenMay());
            if (active != null) {
                sdm.setId(active.getId());
                sdm.setGioBatDau(active.getGioBatDau());
            }
        }
        if (sdm.getId() == 0 && lblMaSd.getText() != null && !lblMaSd.getText().isEmpty()) {
            try {
                sdm.setId(Integer.parseInt(lblMaSd.getText()));
            } catch (Exception ignored) {}
        }
        sdm.setGioKetThuc(new Time(System.currentTimeMillis()));
        sdm.setNgayChoi(new java.sql.Date(System.currentTimeMillis()));
        sdm.setNgayKetThuc(new java.sql.Date(System.currentTimeMillis()));
        return sdm;
    }

    @Override
    public MayTinh getFromTwo() {
        if (selectedMachine != null) {
            return selectedMachine;
        }
        MayTinh mt = new MayTinh();
        mt.setTenMay(lblTenMay.getText());
        return mt;
    }

    @Override
    public void MoMay() {
        if (selectedMachine != null && "Hoạt động".equalsIgnoreCase(selectedMachine.getTrangThai())) {
            XDialog.alert("Máy đã hoạt động, không thể mở tiếp");
            return;
        }
        if (selectedMachine != null && "Bảo trì".equalsIgnoreCase(selectedMachine.getTrangThai())) {
            XDialog.alert("Máy hiện đang bảo trì, vui lòng chọn máy khác");
            return;
        }
        SuDungMay sdm = this.getFromOne();
        MayTinh mt = this.getFromTwo();
        dao.MoMay(sdm, mt); // insert
        FilltblSDMay();
        FilltblMayTinh();
        if (selectedMachine != null) {
            selectMachine(selectedMachine.getTenMay());
        }
    }

    @Override
    public void TatMay() {
        SuDungMay sdm = this.getFromBytoShutdow();
        if (sdm == null || sdm.getId() == 0) {
            XDialog.alert("Máy chưa mở hoặc không có phiên sử dụng cần tắt");
            return;
        }
        MayTinh mt = this.getFromTwo();
        dao.TatMay(sdm, mt);
        FilltblSDMay();
        FilltblMayTinh();
        if (selectedMachine != null) {
            selectMachine(selectedMachine.getTenMay());
        }
    }

    @Override
    public void Clear() {
        if (itemscp != null && !itemscp.isEmpty()) {
            selectMachine(itemscp.get(0).getTenMay());
        }
        DongHo();
    }

    @Override
    public void DongHo() {
        if (dongHoTimer == null || !dongHoTimer.isRunning()) {
            dongHoTimer = new Timer(1000, e -> {
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                if (lblGioBatDau != null && (selectedMachine == null || !"Hoạt động".equalsIgnoreCase(selectedMachine.getTrangThai()))) {
                    lblGioBatDau.setText(sdf.format(now));
                }
                updateLiveTimers();
            });
            dongHoTimer.start();
        }
    }

    @Override
    public void TatDongHo() {
        if (dongHoTimer != null && dongHoTimer.isRunning()) {
            dongHoTimer.stop();
        }
    }

    @Override
    public void NgayHienTai() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayHienTai = sdf.format(new Date());
        lblNgayHienTai.setText(ngayHienTai);
    }
}
