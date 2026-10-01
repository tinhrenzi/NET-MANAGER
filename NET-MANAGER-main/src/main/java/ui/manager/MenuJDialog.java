/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui.manager;

import daoImpl.MonAnDAOImpl;
import entity.MonAn;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import daoImpl.MenuDAOImpl;
import entity.Menu;
import dao.MenuDAO;
import dao.MonAnDAO;
import java.text.SimpleDateFormat;
import controller.MenuController;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;
import util.Style_Net;
import util.XDialog;

/**
 *
 * @author Admin
 */
public class MenuJDialog extends javax.swing.JDialog implements MenuController {

    /**
     * Creates new form MenuJDialog
     */
    private static final int COL_ANH = 3;  // cột Ảnh trong tblOrderManager
    private String duongDanAnhDangChon;

    private MonAnDAO MonAnDao = new MonAnDAOImpl();
    private MenuDAO MenuDao = new MenuDAOImpl();

    // Modern Food Grid & Cart components
    private JPanel pnlFoodGrid;
    private JScrollPane scrollFoodGrid;
    private JTextField txtSearchFood;
    private JLabel lblTopMachineBadge;
    private JLabel lblCartHeaderSub;
    private JLabel lblCartTotalDisplay;
    private DefaultTableModel modelCartDisplay;
    private JTable tblCartDisplay;
    private JButton btnPillAllFood;
    private JButton btnPillFoodHot;
    private JButton btnPillFoodDrink;
    private JButton btnModernConfirmOrder;
    private JButton btnModernClearCart;
    private String foodFilter = "ALL";

    public MenuJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initNavyMenuTheme();
        setLocationRelativeTo(null);
        fillToTable();
        renderFoodCardGrid();
        syncCartToDisplay();
    }
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public MenuJDialog(java.awt.Frame parent, boolean modal, String maMay, String TenMay) {
        super(parent, modal);
        initComponents();
        lblTenMay.setText(TenMay);
        lblMaSd.setText(maMay);
        initNavyMenuTheme();
        setLocationRelativeTo(null);
        fillToTable();
        scheduler.schedule(() -> {
            filltblDaNMua();
        }, 1, TimeUnit.SECONDS);
        scheduler.shutdown();
        updateMachineTitle(TenMay, maMay);
        renderFoodCardGrid();
        syncCartToDisplay();
    }

    private void initNavyMenuTheme() {
        buildModernMenuLayout();
    }

    private void buildModernMenuLayout() {
        setTitle("NET-MANAGER - Thực Đơn & Gọi Món");
        setSize(1280, 750);
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

        // Brand
        JLabel lblB = new JLabel("NET-MANAGER");
        lblB.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblB.setForeground(Style_Net.NAVY_PRIMARY);
        pnlTop.add(lblB, BorderLayout.WEST);

        // Center machine badge
        String machine = (lblTenMay != null && lblTenMay.getText() != null && !lblTenMay.getText().isEmpty()) ? lblTenMay.getText() : "Máy 02";
        lblTopMachineBadge = new JLabel("Đang chọn gọi món cho: " + machine + " (Dàn máy thường)", SwingConstants.CENTER);
        lblTopMachineBadge.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTopMachineBadge.setForeground(Style_Net.NAVY_PRIMARY);
        lblTopMachineBadge.setOpaque(true);
        lblTopMachineBadge.setBackground(new Color(0xF1, 0xF5, 0xF9));
        lblTopMachineBadge.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Style_Net.BORDER_HAIRLINE, 1, true),
            new EmptyBorder(6, 16, 6, 16)
        ));
        JPanel pnlCenterWrap = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlCenterWrap.setOpaque(false);
        pnlCenterWrap.add(lblTopMachineBadge);
        pnlTop.add(pnlCenterWrap, BorderLayout.CENTER);

        // Right Info
        String cashier = (util.XAuth.user != null) ? util.XAuth.user.getTen() : "admin";
        JLabel lblRightCashier = new JLabel("Thu ngân: " + cashier + " • 28/09/2026");
        lblRightCashier.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblRightCashier.setForeground(Style_Net.TEXT_MUTED);
        pnlTop.add(lblRightCashier, BorderLayout.EAST);

        getContentPane().add(pnlTop, BorderLayout.NORTH);

        // 2. MAIN BODY (CENTER)
        JPanel pnlBody = new JPanel(new BorderLayout(0, 14));
        pnlBody.setBackground(Style_Net.BG_CANVAS);
        pnlBody.setBorder(new EmptyBorder(14, 24, 18, 24));

        // Filter & Search Row
        JPanel pnlFilterSearch = new JPanel(new BorderLayout());
        pnlFilterSearch.setOpaque(false);

        JPanel pnlPills = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pnlPills.setOpaque(false);

        btnPillAllFood = createPillButton("Tất cả món", true);
        btnPillFoodHot = createPillButton("Đồ ăn chế biến", false);
        btnPillFoodDrink = createPillButton("Nước giải khát", false);

        btnPillAllFood.addActionListener(e -> setFoodFilter("ALL"));
        btnPillFoodHot.addActionListener(e -> setFoodFilter("EAT"));
        btnPillFoodDrink.addActionListener(e -> setFoodFilter("DRINK"));

        pnlPills.add(btnPillAllFood);
        pnlPills.add(btnPillFoodHot);
        pnlPills.add(btnPillFoodDrink);
        pnlFilterSearch.add(pnlPills, BorderLayout.WEST);

        txtSearchFood = new JTextField();
        Style_Net.styleTextField(txtSearchFood);
        txtSearchFood.setPreferredSize(new Dimension(240, 36));
        txtSearchFood.putClientProperty("JTextField.placeholderText", "Tìm kiếm món ăn, nước...");
        txtSearchFood.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                renderFoodCardGrid();
            }
        });
        pnlFilterSearch.add(txtSearchFood, BorderLayout.EAST);
        pnlBody.add(pnlFilterSearch, BorderLayout.NORTH);

        // Split Content Area (Left: Food Grid ~68%, Right: Cart ~32%)
        JPanel pnlSplit = new JPanel(new BorderLayout(18, 0));
        pnlSplit.setOpaque(false);

        // Left Food Cards Grid inside ScrollPane
        pnlFoodGrid = new JPanel(new GridLayout(0, 3, 14, 14));
        pnlFoodGrid.setBackground(Style_Net.BG_CANVAS);
        scrollFoodGrid = new JScrollPane(pnlFoodGrid);
        scrollFoodGrid.setBorder(null);
        scrollFoodGrid.getViewport().setBackground(Style_Net.BG_CANVAS);
        pnlSplit.add(scrollFoodGrid, BorderLayout.CENTER);

        // Right Cart Panel (~360px)
        JPanel pnlCart = Style_Net.createCardPanel();
        pnlCart.setPreferredSize(new Dimension(360, 0));
        pnlCart.setLayout(new BorderLayout(0, 12));

        // Cart Header
        JPanel pnlCartHead = new JPanel();
        pnlCartHead.setLayout(new BoxLayout(pnlCartHead, BoxLayout.Y_AXIS));
        pnlCartHead.setOpaque(false);

        JLabel lblCartTitle = new JLabel("GIỎ HÀNG GỌI MÓN");
        lblCartTitle.setFont(Style_Net.FONT_HEADER);
        lblCartTitle.setForeground(Style_Net.NAVY_PRIMARY);

        lblCartHeaderSub = new JLabel("Đang chuẩn bị đơn cho " + machine);
        lblCartHeaderSub.setFont(Style_Net.FONT_SMALL);
        lblCartHeaderSub.setForeground(Style_Net.TEXT_MUTED);

        pnlCartHead.add(lblCartTitle);
        pnlCartHead.add(Box.createVerticalStrut(2));
        pnlCartHead.add(lblCartHeaderSub);
        pnlCart.add(pnlCartHead, BorderLayout.NORTH);

        // Cart Table
        String[] cartCols = {"MÓN ĂN", "SL", "ĐƠN GIÁ", "TỔNG", "XÓA"};
        modelCartDisplay = new DefaultTableModel(cartCols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return c == 4; }
        };
        tblCartDisplay = new JTable(modelCartDisplay);
        Style_Net.styleTable(tblCartDisplay);
        tblCartDisplay.setRowHeight(36);
        tblCartDisplay.getColumnModel().getColumn(0).setPreferredWidth(120);
        tblCartDisplay.getColumnModel().getColumn(1).setPreferredWidth(35);
        tblCartDisplay.getColumnModel().getColumn(2).setPreferredWidth(65);
        tblCartDisplay.getColumnModel().getColumn(3).setPreferredWidth(75);
        tblCartDisplay.getColumnModel().getColumn(4).setPreferredWidth(45);

        tblCartDisplay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = tblCartDisplay.getSelectedColumn();
                int row = tblCartDisplay.getSelectedRow();
                if (col == 4 && row >= 0) {
                    DefaultTableModel mOld = (DefaultTableModel) tblTongMonAn.getModel();
                    if (row < mOld.getRowCount()) {
                        mOld.removeRow(row);
                        updateTongTien();
                        syncCartToDisplay();
                    }
                }
            }
        });

        JScrollPane scrollCart = new JScrollPane(tblCartDisplay);
        scrollCart.setBorder(new LineBorder(Style_Net.BORDER_HAIRLINE, 1));
        pnlCart.add(scrollCart, BorderLayout.CENTER);

        // Cart Bottom (Total & Actions)
        JPanel pnlCartBottom = new JPanel();
        pnlCartBottom.setLayout(new BoxLayout(pnlCartBottom, BoxLayout.Y_AXIS));
        pnlCartBottom.setOpaque(false);

        JPanel pnlTotalRow = new JPanel(new BorderLayout());
        pnlTotalRow.setOpaque(false);
        JLabel lblTLabel = new JLabel("TỔNG TIỀN MÓN:");
        lblTLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTLabel.setForeground(Style_Net.TEXT_MUTED);

        lblCartTotalDisplay = new JLabel("0 ₫");
        lblCartTotalDisplay.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblCartTotalDisplay.setForeground(Style_Net.NAVY_PRIMARY);

        pnlTotalRow.add(lblTLabel, BorderLayout.WEST);
        pnlTotalRow.add(lblCartTotalDisplay, BorderLayout.EAST);
        pnlCartBottom.add(pnlTotalRow);
        pnlCartBottom.add(Box.createVerticalStrut(14));

        btnModernConfirmOrder = new JButton("XÁC NHẬN GỌI MÓN (" + machine + ")");
        Style_Net.stylePrimaryButton(btnModernConfirmOrder);
        btnModernConfirmOrder.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btnModernConfirmOrder.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModernConfirmOrder.addActionListener(e -> {
            btn_muaActionPerformed(null);
            syncCartToDisplay();
            renderFoodCardGrid();
        });

        btnModernClearCart = new JButton("Làm mới giỏ hàng");
        Style_Net.styleSecondaryButton(btnModernClearCart);
        btnModernClearCart.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btnModernClearCart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModernClearCart.addActionListener(e -> {
            clear();
            syncCartToDisplay();
        });

        pnlCartBottom.add(btnModernConfirmOrder);
        pnlCartBottom.add(Box.createVerticalStrut(8));
        pnlCartBottom.add(btnModernClearCart);

        pnlCart.add(pnlCartBottom, BorderLayout.SOUTH);
        pnlSplit.add(pnlCart, BorderLayout.EAST);

        pnlBody.add(pnlSplit, BorderLayout.CENTER);
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

    private void setFoodFilter(String f) {
        this.foodFilter = f;
        btnPillAllFood.setBackground("ALL".equals(f) ? Style_Net.NAVY_PRIMARY : Color.WHITE);
        btnPillAllFood.setForeground("ALL".equals(f) ? Color.WHITE : Style_Net.NAVY_PRIMARY);

        btnPillFoodHot.setBackground("EAT".equals(f) ? Style_Net.NAVY_PRIMARY : Color.WHITE);
        btnPillFoodHot.setForeground("EAT".equals(f) ? Color.WHITE : Style_Net.NAVY_PRIMARY);

        btnPillFoodDrink.setBackground("DRINK".equals(f) ? Style_Net.NAVY_PRIMARY : Color.WHITE);
        btnPillFoodDrink.setForeground("DRINK".equals(f) ? Color.WHITE : Style_Net.NAVY_PRIMARY);

        renderFoodCardGrid();
    }

    public void updateMachineTitle(String tenMay, String maMay) {
        if (lblTenMay != null) lblTenMay.setText(tenMay);
        if (lblMaSd != null) lblMaSd.setText(maMay);
        if (lblTopMachineBadge != null) {
            lblTopMachineBadge.setText("Đang chọn gọi món cho: " + tenMay + " (Dàn máy thường)");
        }
        if (lblCartHeaderSub != null) {
            lblCartHeaderSub.setText("Đang chuẩn bị đơn cho " + tenMay);
        }
        if (btnModernConfirmOrder != null) {
            btnModernConfirmOrder.setText("XÁC NHẬN GỌI MÓN (" + tenMay + ")");
        }
    }

    private void renderFoodCardGrid() {
        if (pnlFoodGrid == null) return;
        pnlFoodGrid.removeAll();

        List<MonAn> list = MonAnDao.findAll();
        String search = (txtSearchFood != null && txtSearchFood.getText() != null) ? txtSearchFood.getText().trim().toLowerCase() : "";

        for (MonAn m : list) {
            String ten = m.getTenMon();
            if (!search.isEmpty() && !ten.toLowerCase().contains(search)) {
                continue;
            }
            boolean isDrink = ten.toLowerCase().contains("sting") || ten.toLowerCase().contains("coca") || ten.toLowerCase().contains("trà") || ten.toLowerCase().contains("nước") || ten.toLowerCase().contains("bia") || ten.toLowerCase().contains("cà phê");
            if ("EAT".equals(foodFilter) && isDrink) continue;
            if ("DRINK".equals(foodFilter) && !isDrink) continue;

            JPanel card = createFoodCard(m, isDrink);
            pnlFoodGrid.add(card);
        }

        pnlFoodGrid.revalidate();
        pnlFoodGrid.repaint();
    }

    private JPanel createFoodCard(MonAn m, boolean isDrink) {
        JPanel card = Style_Net.createCardPanel();
        card.setLayout(new BorderLayout(0, 6));
        card.setPreferredSize(new Dimension(200, 260));

        // 1. Image container at top (height 120px)
        JPanel pnlImgWrap = new JPanel(new BorderLayout());
        pnlImgWrap.setPreferredSize(new Dimension(0, 120));
        pnlImgWrap.setBackground(new Color(0xF8, 0xFA, 0xFC));
        pnlImgWrap.setBorder(new LineBorder(Style_Net.BORDER_HAIRLINE, 1, true));

        JLabel lblImg = new JLabel("", SwingConstants.CENTER);
        String imgPath = m.getHinhANh();
        File f = null;
        if (imgPath != null && !imgPath.isEmpty()) {
            f = new File(imgPath);
            if (!f.exists()) f = new File("src/main/java/img/" + imgPath);
        }
        if (f == null || !f.exists()) {
            String ten = m.getTenMon().toLowerCase();
            if (ten.contains("bò húc") || ten.contains("húc") || ten.contains("sting") || ten.contains("redbull")) {
                f = new File("src/main/java/img/Sting.jpg");
                if (!f.exists()) f = new File("src/main/java/img/stingVang.jpg");
            } else if (ten.contains("mì xào") || ten.contains("bò trứng") || ten.contains("mì tôm") || ten.contains("mì")) {
                f = new File("src/main/java/img/MiTomTrung.jpg");
            } else if (ten.contains("coca")) {
                f = new File("src/main/java/img/CocaCola.jpg");
            } else if (ten.contains("bánh mì") || ten.contains("pate")) {
                f = new File("src/main/java/img/BanhMiPate.jpg");
            } else if (ten.contains("khoai")) {
                f = new File("src/main/java/img/khoailangchien.jpg");
            } else if (ten.contains("trà đào") || ten.contains("trà")) {
                f = new File("src/main/java/img/tradao.jpg");
            } else if (ten.contains("bia")) {
                f = new File("src/main/java/img/Bia.jpg");
            } else if (isDrink) {
                f = new File("src/main/java/img/CocaCola.jpg");
            } else {
                f = new File("src/main/java/img/MiTomTrung.jpg");
            }
        }
        if (f != null && f.exists()) {
            try {
                ImageIcon icon = new ImageIcon(f.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(140, 105, Image.SCALE_SMOOTH);
                lblImg.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                lblImg.setText(isDrink ? "NƯỚC UỐNG" : "MÓN ĂN");
                lblImg.setFont(new Font("Segoe UI", Font.BOLD, 12));
                lblImg.setForeground(Style_Net.TEXT_MUTED);
            }
        } else {
            lblImg.setText(isDrink ? "NƯỚC UỐNG" : "MÓN ĂN");
            lblImg.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblImg.setForeground(Style_Net.TEXT_MUTED);
        }
        pnlImgWrap.add(lblImg, BorderLayout.CENTER);

        // Badge at top right of image container
        JPanel pnlBadgeWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 4));
        pnlBadgeWrap.setOpaque(false);
        JLabel badge = (m.getSoLuong() > 0) ? Style_Net.createBadge("Còn " + m.getSoLuong(), "green") : Style_Net.createBadge("Tạm hết", "gray");
        pnlBadgeWrap.add(badge);
        pnlImgWrap.add(pnlBadgeWrap, BorderLayout.NORTH);

        card.add(pnlImgWrap, BorderLayout.NORTH);

        // 2. Info Body
        JPanel pnlInfo = new JPanel();
        pnlInfo.setLayout(new BoxLayout(pnlInfo, BoxLayout.Y_AXIS));
        pnlInfo.setOpaque(false);

        JLabel lblCat = new JLabel(isDrink ? "NƯỚC GIẢI KHÁT" : "ĐỒ ĂN NÓNG");
        lblCat.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblCat.setForeground(Style_Net.TEXT_MUTED);

        JLabel lblName = new JLabel("<html><body style='width: 150px;'>" + m.getTenMon() + "</body></html>");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblName.setForeground(Style_Net.NAVY_PRIMARY);

        pnlInfo.add(lblCat);
        pnlInfo.add(Box.createVerticalStrut(2));
        pnlInfo.add(lblName);
        card.add(pnlInfo, BorderLayout.CENTER);

        // 3. Price & Add button at bottom
        JPanel pnlBottom = new JPanel(new BorderLayout(6, 0));
        pnlBottom.setOpaque(false);

        JLabel lblPrice = new JLabel(Style_Net.formatMoney(m.getGiaTien()));
        lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPrice.setForeground(Style_Net.NAVY_PRIMARY);

        JButton btnAdd = new JButton("+ Thêm");
        Style_Net.styleSecondaryButton(btnAdd);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.addActionListener(e -> {
            addFoodToCart(m);
        });

        pnlBottom.add(lblPrice, BorderLayout.WEST);
        pnlBottom.add(btnAdd, BorderLayout.EAST);
        card.add(pnlBottom, BorderLayout.SOUTH);

        return card;
    }

    private void addFoodToCart(MonAn m) {
        DefaultTableModel model = (DefaultTableModel) tblTongMonAn.getModel();
        boolean found = false;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equals(m.getId())) {
                int currentQty = Integer.parseInt(model.getValueAt(i, 3).toString());
                model.setValueAt(currentQty + 1, i, 3);
                found = true;
                break;
            }
        }
        if (!found) {
            model.addRow(new Object[]{m.getId(), m.getTenMon(), m.getGiaTien(), 1});
        }
        updateTongTien();
        syncCartToDisplay();
    }

    private void syncCartToDisplay() {
        if (modelCartDisplay == null) return;
        modelCartDisplay.setRowCount(0);
        DefaultTableModel model = (DefaultTableModel) tblTongMonAn.getModel();
        double sum = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            String ten = model.getValueAt(i, 1).toString();
            double gia = Double.parseDouble(model.getValueAt(i, 2).toString());
            int sl = Integer.parseInt(model.getValueAt(i, 3).toString());
            double total = gia * sl;
            sum += total;
            modelCartDisplay.addRow(new Object[]{ten, sl, Style_Net.formatMoney(gia), Style_Net.formatMoney(total), "Xóa"});
        }
        if (lblCartTotalDisplay != null) {
            lblCartTotalDisplay.setText(Style_Net.formatMoney(sum));
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

        jPanel4 = new javax.swing.JPanel();
        lblNgayHienTai = new javax.swing.JLabel();
        Tabpnl = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTongMonAn = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDaMua = new javax.swing.JTable();
        lblTenMay = new javax.swing.JLabel();
        lblHinhAnh = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_mua = new javax.swing.JButton();
        btn_them = new javax.swing.JButton();
        btn_Clear = new javax.swing.JButton();
        btn_huy = new javax.swing.JButton();
        lblMaSd = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        lblMaDaMua = new javax.swing.JLabel();
        lblGiaMon = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrderManager = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Giao diện thực đơn");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
        });

        lblNgayHienTai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        tblTongMonAn.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        tblTongMonAn.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã món", "Tên món", "Giá", "Số Lượng"
                }
        ));
        tblTongMonAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTongMonAnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTongMonAn);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
        );

        Tabpnl.addTab("Danh sách đang mua", jPanel2);

        tblDaMua.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        tblDaMua.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}
                },
                new String[]{
                    "Mã đã mua", "Tên món", "Giá món", "Số lượng", "Tổng tiền"
                }
        ));
        tblDaMua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDaMuaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDaMua);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
        );

        Tabpnl.addTab("Danh sách đã mua", jPanel3);

        lblTenMay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTenMay.setText("Tên máy");

        lblHinhAnh.setText("Hình ảnh miêu tả món");

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel5.setText("Số lượng");

        txtSoLuong.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel4.setText("Tổng");

        lblTongTien.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lblTongTien.setText("- - - - - - - -");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btn_mua.setBackground(new java.awt.Color(126, 211, 33));
        btn_mua.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btn_mua.setForeground(new java.awt.Color(255, 255, 255));
        btn_mua.setText("Mua");
        btn_mua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_muaActionPerformed(evt);
            }
        });

        btn_them.setBackground(new java.awt.Color(22, 163, 74));
        btn_them.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btn_them.setForeground(new java.awt.Color(255, 255, 255));
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_Clear.setBackground(new java.awt.Color(193, 189, 189));
        btn_Clear.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btn_Clear.setForeground(new java.awt.Color(51, 51, 51));
        btn_Clear.setText("Làm mới");
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });

        btn_huy.setBackground(new java.awt.Color(233, 78, 60));
        btn_huy.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btn_huy.setForeground(new java.awt.Color(255, 255, 255));
        btn_huy.setText("Hủy mua");
        btn_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyActionPerformed(evt);
            }
        });

        lblMaSd.setFont(new java.awt.Font("Segoe UI", 0, 3)); // NOI18N
        lblMaSd.setForeground(new java.awt.Color(204, 204, 204));

        btnSua.setBackground(new java.awt.Color(126, 211, 33));
        btnSua.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        lblMaDaMua.setForeground(new java.awt.Color(204, 204, 204));

        lblGiaMon.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblMaDaMua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblMaSd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblGiaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btn_mua, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btn_huy)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnSua)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btn_Clear, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[]{btnSua, btn_mua});

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_huy)
                                        .addComponent(btn_Clear)
                                        .addComponent(btn_mua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSua))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lblMaDaMua, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(lblMaSd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(6, 6, 6))))
                                        .addComponent(lblGiaMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[]{btnSua, btn_Clear, btn_huy, btn_mua, btn_them});

        jPanel5.setBackground(new java.awt.Color(13, 71, 161));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Thực Đơn");

        tblOrderManager.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        tblOrderManager.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Mã đồ món", "Tên món", "Đơn Giá", "Ảnh"
                }
        ));
        tblOrderManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderManagerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblOrderManager);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addComponent(Tabpnl, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(32, 32, 32)))
                                                .addComponent(lblNgayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(lblTenMay, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(55, 55, 55)
                                                                .addComponent(lblTenMay, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(49, 49, 49)
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                                                                .addGap(32, 32, 32)
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(63, 63, 63)
                                                                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(27, 27, 27)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblNgayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(14, 14, 14)
                                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(Tabpnl)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[]{jLabel4, jLabel5});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        NgayHienTai();
    }//GEN-LAST:event_formWindowOpened

    private void tblOrderManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderManagerMouseClicked
        int row = tblOrderManager.rowAtPoint(evt.getPoint());
        if (row < 0) {
            return;
        }

        Object imgVal = tblOrderManager.getValueAt(row, COL_ANH);
        showImageOnLabel(imgVal);

        if (evt.getClickCount() == 2 && imgVal != null) {
            String path = resolveImagePath(imgVal);
            if (path != null) {
                openImage(path);
            }
        }
    }//GEN-LAST:event_tblOrderManagerMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int sl;
        try {
            sl = Integer.parseInt(txtSoLuong.getText());
            if (sl <= 0) {
                XDialog.alert("Số lượng phải lớn hơn 0");
                return;
            }
        } catch (NumberFormatException e) {
            XDialog.alert("Số lượng phải là số nguyên hợp lệ");
            return;
        }

        if (tblTongMonAn.getSelectedRow() != -1) {
            SuaSoLuongDangMua();
        } else {
            SuaSoLuong();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyActionPerformed
        int selectedRow = tblTongMonAn.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblTongMonAn.getModel();
            model.removeRow(selectedRow);
            updateTongTien();
            showSuccessDialog("Đã hủy món thành công.", 2000);
        } else {
            XDialog.alert("Vui lòng chọn dòng cần hủy.");
        }
    }//GEN-LAST:event_btn_huyActionPerformed

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        this.clear();
        filltblDaNMua();
    }//GEN-LAST:event_btn_ClearActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        int selectedRow = tblOrderManager.getSelectedRow();
        if (selectedRow == -1) {
            XDialog.alert("Vui lòng chọn món trong bảng OrderManager.");
            return;
        }

        String ma = tblOrderManager.getValueAt(selectedRow, 0).toString();
        String ten = tblOrderManager.getValueAt(selectedRow, 1).toString();
        float gia = Float.parseFloat(tblOrderManager.getValueAt(selectedRow, 2).toString());

        int soLuongNhap = 1;
        try {
            String slText = txtSoLuong.getText().trim();
            if (!slText.isEmpty()) {
                soLuongNhap = Integer.parseInt(slText);
                if (soLuongNhap <= 0) {
                    XDialog.alert("Số lượng phải lớn hơn 0.");
                    return;
                }
            }
        } catch (NumberFormatException e) {
            XDialog.alert("Số lượng phải là số nguyên.");
            return;
        }

        MonAn mon = MonAnDao.findByID(ma);
        int tonKho = mon.getSoLuong();

        if (soLuongNhap > tonKho) {
            JOptionPane.showMessageDialog(this,
                    "Số lượng nhập vượt quá tồn kho (" + tonKho + "). Hệ thống sẽ tự chỉnh về " + tonKho);
            soLuongNhap = tonKho;
        }

        DefaultTableModel model = (DefaultTableModel) tblTongMonAn.getModel();
        boolean found = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equals(ma)) {
                int currentQty = Integer.parseInt(model.getValueAt(i, 3).toString());
                int newQty = currentQty + soLuongNhap;
                if (newQty > tonKho) {
                    newQty = tonKho;
                    JOptionPane.showMessageDialog(this,
                            "Tổng số lượng cho món này vượt tồn kho. Đã chỉnh về " + newQty);
                }
                model.setValueAt(newQty, i, 3);
                found = true;
                break;
            }
        }

        if (!found) {
            model.addRow(new Object[]{ma, ten, gia, soLuongNhap});
        }

        updateTongTien();
        txtSoLuong.setText("");
        lblHinhAnh.setIcon(null);

    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_muaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_muaActionPerformed
        String masd = lblMaSd.getText().trim();
        if (masd.isEmpty()) {
            showSuccessDialog("Bạn đã bỏ qua bước chọn máy từ danh sách sử dụng", 3000);
            return;
        }
        this.create();
    }//GEN-LAST:event_btn_muaActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed

    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void tblDaMuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDaMuaMouseClicked
        int Slt = tblDaMua.getSelectedRow();
        if (Slt >= 0) {
            String id = tblDaMua.getValueAt(Slt, 0).toString();
            String GiaTien = tblDaMua.getValueAt(Slt, 2).toString();
            String Soluong = tblDaMua.getValueAt(Slt, 3).toString();
            String Tong = tblDaMua.getValueAt(Slt, 4).toString();
            txtSoLuong.setText(Soluong);
            lblTongTien.setText(Tong);
            lblMaDaMua.setText(id);
            lblGiaMon.setText(GiaTien);
        }
    }//GEN-LAST:event_tblDaMuaMouseClicked

    private void tblTongMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTongMonAnMouseClicked
        int row = tblTongMonAn.getSelectedRow();
        if (row < 0) {
            return;
        }

        String tongTien = tblTongMonAn.getValueAt(row, 2).toString();
        String soLuong = tblTongMonAn.getValueAt(row, 3).toString();

        txtSoLuong.setText(soLuong);
        lblTongTien.setText(tongTien);
    }//GEN-LAST:event_tblTongMonAnMouseClicked

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        CapNhat();
    }//GEN-LAST:event_jPanel4MouseEntered

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
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MenuJDialog dialog = new MenuJDialog(new javax.swing.JFrame(), true);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Tabpnl;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btn_Clear;
    private javax.swing.JButton btn_huy;
    private javax.swing.JButton btn_mua;
    private javax.swing.JButton btn_them;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblGiaMon;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblMaDaMua;
    private javax.swing.JLabel lblMaSd;
    private javax.swing.JLabel lblNgayHienTai;
    private javax.swing.JLabel lblTenMay;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblDaMua;
    private javax.swing.JTable tblOrderManager;
    private javax.swing.JTable tblTongMonAn;
    private javax.swing.JTextField txtSoLuong;

    // End of variables declaration//GEN-END:variables
    public void CapNhat() {
        filltblDaNMua();
    }

    private void updateTongTien() {
        DefaultTableModel model = (DefaultTableModel) tblTongMonAn.getModel();
        double tongTien = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            Object giaObj = model.getValueAt(i, 2);
            Object soLuongObj = model.getValueAt(i, 3);

            if (giaObj != null && soLuongObj != null) {
                try {
                    double gia = Double.parseDouble(giaObj.toString().trim());
                    int soLuong = Integer.parseInt(soLuongObj.toString().trim());
                    tongTien += gia * soLuong;
                } catch (NumberFormatException e) {
                }
            }
        }

        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###.##");
        lblTongTien.setText(df.format(tongTien) + " VNĐ");
    }

    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblOrderManager.getModel();
        model.setRowCount(0);
        List<MonAn> items = MonAnDao.findAll();
        for (MonAn item : items) {
            Object[] rowData = {
                item.getId(),
                item.getTenMon(),
                item.getGiaTien(),
                item.getHinhANh()
            };
            model.addRow(rowData);
        }
    }

    public void create() {
        int MaSD = Integer.parseInt(lblMaSd.getText().trim());
        String tenMay = lblTenMay.getText().trim();

        try {
            DefaultTableModel model = (DefaultTableModel) tblTongMonAn.getModel();
            if (model.getRowCount() == 0) {
                XDialog.alert("Không có món nào để mua.");
                return;
            }

            java.sql.Date ngayMua = new java.sql.Date(System.currentTimeMillis());

            for (int i = 0; i < model.getRowCount(); i++) {
                String maMon = model.getValueAt(i, 0).toString();
                String tenMon = model.getValueAt(i, 1).toString();
                float gia = Float.parseFloat(model.getValueAt(i, 2).toString());
                int soLuongYeuCau = Integer.parseInt(model.getValueAt(i, 3).toString());

                MonAn mon = MonAnDao.findByID(maMon);
                if (mon == null) {
                    continue;
                }

                int soLuongTon = mon.getSoLuong();

                if (soLuongYeuCau > soLuongTon) {
                    showSuccessDialog("Số lượng nhập vượt quá tồn kho (" + soLuongTon + "). Hệ thống sẽ tự chỉnh về " + soLuongTon, 2000);
                    soLuongYeuCau = soLuongTon;
                }

                if (soLuongYeuCau <= 0) {
                    continue;
                }

                double tongTienMon = gia * soLuongYeuCau;

                Menu order = new Menu(i, MaSD, tenMay, maMon, tenMon, gia, ngayMua, soLuongYeuCau, tongTienMon);
                MenuDao.Mua(order);

                int soLuongMoi = soLuongTon - soLuongYeuCau;
                mon.setSoLuong(Math.max(0, soLuongMoi));
                MonAnDao.update(mon);
            }

            showSuccessDialog("Mua thành công!", 2000);
            fillToTable();
            clear();
            filltblDaNMua();

        } catch (Exception e) {
            e.printStackTrace();
            XDialog.alert("Lỗi khi mua: " + e.getMessage());
        }
    }

    public void clear() {
        lblTongTien.setText("");
        txtSoLuong.setText("");
        DefaultTableModel model = (DefaultTableModel) tblTongMonAn.getModel();
        model.setRowCount(0);
    }

    public void NgayHienTai() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        lblNgayHienTai.setText("Ngày hiện tại " + sdf.format(new java.util.Date()));
    }

    @Override
    public void filltblDaNMua() {
        DefaultTableModel model = (DefaultTableModel) tblDaMua.getModel();
        model.setRowCount(0);

        String MaSDMay = lblMaSd.getText().trim();

        List<Menu> items = MenuDao.findByIdSD(MaSDMay);
        for (Menu item : items) {
            Object[] rowData = {
                item.getId(),
                item.getTenMon(),
                item.getGiaTien(),
                item.getSoLuong(),
                item.getTongTien()
            };
            model.addRow(rowData);
        }
    }

    @Override
    public Menu getFromDaMua() {
        Menu mn = new Menu();

        String idText = lblMaDaMua.getText().trim();
        String tongTienText = lblTongTien.getText().trim();
        String soLuongText = txtSoLuong.getText().trim();

        if (idText.isEmpty() || tongTienText.isEmpty() || soLuongText.isEmpty()) {
            XDialog.alert("Vui lòng chọn món từ bảng đã mua trước khi sửa.");
            return null;
        }

        mn.setId(Integer.parseInt(idText));
        mn.setTongTien(Double.parseDouble(tongTienText));
        mn.setSoLuong(Integer.parseInt(soLuongText));

        return mn;
    }

    @Override
    public void SuaSoLuong() {
        try {
            Menu mn = this.getFromDaMua();
            if (mn == null) {
                return;
            }

            // Lấy thông tin món từ Menu cũ
            Menu oldOrder = MenuDao.findById(mn.getId());
            MonAn mon = MonAnDao.findByID(oldOrder.getMaMon());

            if (oldOrder != null && mon != null) {
                int soLuongCu = oldOrder.getSoLuong();
                int soLuongMoi = mn.getSoLuong();

                // Cập nhật Menu
                MenuDao.UpSoluong(mn);

                int chenhlech = soLuongCu - soLuongMoi;
                mon.setSoLuong(mon.getSoLuong() + chenhlech);
                MonAnDao.update(mon);
            }

            filltblDaNMua();
            showSuccessDialog("Sửa số lượng thành công!", 2000);
        } catch (Exception e) {
            e.printStackTrace();
            XDialog.alert("Lỗi sửa số lượng: " + e.getMessage());
        }
    }

    public Menu getFromDangMua() {
        Menu mn = new Menu();

        String idText = lblMaDaMua.getText().trim();
        String tongTienText = lblTongTien.getText().trim();
        String soLuongText = txtSoLuong.getText().trim();

        if (idText.isEmpty() || tongTienText.isEmpty() || soLuongText.isEmpty()) {
            XDialog.alert("Vui lòng chọn món từ bảng đã mua trước khi sửa.");
            return null;
        }

        mn.setId(Integer.parseInt(idText));
        mn.setTongTien(Double.parseDouble(tongTienText));
        mn.setSoLuong(Integer.parseInt(soLuongText));

        return mn;
    }

    public void SuaSoLuongDangMua() {
        int row = tblTongMonAn.getSelectedRow();
        if (row == -1) {
            XDialog.alert("Vui lòng chọn món trong bảng đang mua để sửa.");
            return;
        }

        String soLuongText = txtSoLuong.getText().trim();
        if (soLuongText.isEmpty()) {
            XDialog.alert("Vui lòng nhập số lượng mới.");
            return;
        }

        try {
            int soLuong = Integer.parseInt(soLuongText);
            if (soLuong <= 0) {
                XDialog.alert("Số lượng phải lớn hơn 0.");
                return;
            }

            DefaultTableModel model = (DefaultTableModel) tblTongMonAn.getModel();
            model.setValueAt(soLuong, row, 3);

            updateTongTien();

            showSuccessDialog("Sửa số lượng thành công!", 2000);
        } catch (NumberFormatException e) {
            XDialog.alert("Số lượng phải là số nguyên hợp lệ.");
        }
    }

    private String resolveImagePath(Object cellVal) {
        if (cellVal == null) {
            return null;
        }
        String p = cellVal.toString().trim();
        if (p.isEmpty()) {
            return null;
        }

        File f = new File(p);
        if (f.exists()) {
            return f.getAbsolutePath();
        }

        File alt1 = new File("src/images/" + p);
        if (alt1.exists()) {
            return alt1.getAbsolutePath();
        }

        File alt2 = new File("images/" + p);
        if (alt2.exists()) {
            return alt2.getAbsolutePath();
        }

        return null;
    }

    private ImageIcon resizeToLabel(String path, javax.swing.JLabel label) {
        ImageIcon icon = new ImageIcon(path);
        int w = label.getWidth() > 0 ? label.getWidth() : 180;
        int h = label.getHeight() > 0 ? label.getHeight() : 140;
        Image scaled = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private void showImageOnLabel(Object cellVal) {
        String path = resolveImagePath(cellVal);
        if (path != null) {
            lblHinhAnh.setIcon(resizeToLabel(path, lblHinhAnh));
            lblHinhAnh.setText(null);
            lblHinhAnh.setToolTipText(path);
            duongDanAnhDangChon = path;
        } else {
            lblHinhAnh.setIcon(null);
            lblHinhAnh.setText("Không có ảnh / Không tìm thấy");
            lblHinhAnh.setToolTipText(null);
            duongDanAnhDangChon = null;
        }
    }

    private void openImage(String path) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
            ImageIcon big = new ImageIcon(img);
            javax.swing.JLabel lb = new javax.swing.JLabel(big);
            javax.swing.JScrollPane sp = new javax.swing.JScrollPane(lb);
            sp.setPreferredSize(new java.awt.Dimension(900, 820));
            javax.swing.JOptionPane.showMessageDialog(this, sp, "Xem ảnh lớn", javax.swing.JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Không thể mở ảnh: " + e.getMessage());
        }
    }

}
