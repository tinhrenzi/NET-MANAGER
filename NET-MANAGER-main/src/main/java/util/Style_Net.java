package util;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Hệ thống giao diện Navy Tech (Light Minimal) cho NET-MANAGER
 */
public class Style_Net {

    // ==========================================
    // 🎨 BẢNG MÀU CHỦ ĐẠO NAVY TECH
    // ==========================================
    public static final Color NAVY_PRIMARY     = new Color(0x0F, 0x17, 0x2A); // Slate Navy #0F172A
    public static final Color NAVY_ACCENT      = new Color(0x1E, 0x40, 0xAF); // Cobalt Blue #1E40AF
    public static final Color NAVY_HOVER       = new Color(0x1E, 0x29, 0x3B); // Slate 800 #1E293B
    public static final Color BG_CANVAS        = new Color(0xF8, 0xFA, 0xFC); // Slate Tint Canvas #F8FAFC
    public static final Color BG_CARD          = Color.WHITE;
    public static final Color BORDER_HAIRLINE  = new Color(0xE2, 0xE8, 0xF0); // Viền hairline #E2E8F0
    public static final Color BORDER_INPUT     = new Color(0xCB, 0xD5, 0xE1); // Viền input #CBD5E1
    public static final Color TEXT_MAIN        = new Color(0x0F, 0x17, 0x2A); // Chữ chính
    public static final Color TEXT_MUTED       = new Color(0x64, 0x74, 0x8B); // Chữ phụ / nhãn
    public static final Color COLOR_SUCCESS    = new Color(0x05, 0x96, 0x69); // Xanh thành công #059669
    public static final Color COLOR_DANGER     = new Color(0xDC, 0x26, 0x26); // Đỏ cảnh báo #DC2626
    public static final Color COLOR_WARNING    = new Color(0xD9, 0x77, 0x06); // Vàng cảnh báo #D97706

    // ==========================================
    // 🔤 TYPOGRAPHY HIERARCHY (Chuẩn Tiếng Việt Segoe UI)
    // ==========================================
    public static final Font FONT_BRAND  = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FONT_TITLE  = new Font("Segoe UI", Font.BOLD, 17);
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_LABEL  = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONT_BODY   = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BOLD   = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_SMALL  = new Font("Segoe UI", Font.PLAIN, 11);

    /**
     * Khởi tạo FlatLaf và cấu hình UIManager chuẩn Navy Tech
     */
    public static void setup() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());

            // Font hệ thống mặc định
            UIManager.put("defaultFont", FONT_BODY);

            // Bo góc tinh giản hiện đại (không quá tròn)
            UIManager.put("Button.arc", 6);
            UIManager.put("Component.arc", 6);
            UIManager.put("TextComponent.arc", 6);
            UIManager.put("ProgressBar.arc", 6);

            // Padding nút và ô nhập liệu
            UIManager.put("Button.margin", new Insets(7, 16, 7, 16));
            UIManager.put("TextComponent.margin", new Insets(6, 10, 6, 10));

            // Màu sắc chung
            UIManager.put("Component.focusColor", NAVY_ACCENT);
            UIManager.put("Component.borderColor", BORDER_INPUT);
            UIManager.put("Button.hoverBackground", NAVY_HOVER);

            // Bảng dữ liệu JTable
            UIManager.put("Table.background", BG_CARD);
            UIManager.put("Table.foreground", TEXT_MAIN);
            UIManager.put("Table.alternateRowColor", new Color(0xF8, 0xFA, 0xFC));
            UIManager.put("Table.selectionBackground", NAVY_ACCENT);
            UIManager.put("Table.selectionForeground", Color.WHITE);
            UIManager.put("Table.gridColor", BORDER_HAIRLINE);
            UIManager.put("Table.showHorizontalLines", true);
            UIManager.put("Table.showVerticalLines", false);
            UIManager.put("Table.intercellSpacing", new Dimension(0, 1));

            // Tiêu đề bảng
            UIManager.put("TableHeader.background", new Color(0xF1, 0xF5, 0xF9));
            UIManager.put("TableHeader.foreground", NAVY_PRIMARY);
            UIManager.put("TableHeader.font", FONT_HEADER);
            UIManager.put("TableHeader.bottomSeparatorColor", BORDER_HAIRLINE);

            // Scrollbar & Panel
            UIManager.put("Panel.background", BG_CANVAS);
            UIManager.put("ScrollPane.border", new LineBorder(BORDER_HAIRLINE, 1));

        } catch (Exception ex) {
            System.err.println("Lỗi khởi tạo Style_Net: " + ex.getMessage());
        }
    }

    /**
     * Định dạng JTable chuẩn hóa cao cấp
     */
    public static void styleTable(JTable table) {
        table.setFont(FONT_BODY);
        table.setRowHeight(32);
        table.setFillsViewportHeight(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setGridColor(BORDER_HAIRLINE);
        table.setSelectionBackground(NAVY_ACCENT);
        table.setSelectionForeground(Color.WHITE);

        // Header style
        table.getTableHeader().setFont(FONT_HEADER);
        table.getTableHeader().setBackground(new Color(0xF1, 0xF5, 0xF9));
        table.getTableHeader().setForeground(NAVY_PRIMARY);
        table.getTableHeader().setPreferredSize(new Dimension(0, 36));

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.LEADING);
        headerRenderer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_HAIRLINE),
                new EmptyBorder(6, 12, 6, 12)
        ));

        // Content renderer với lề trong thông thoáng
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tbl, Object val, boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(tbl, val, isSelected, hasFocus, row, col);
                setBorder(new EmptyBorder(4, 12, 4, 12));
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? BG_CARD : new Color(0xF8, 0xFA, 0xFC));
                    c.setForeground(TEXT_MAIN);
                }
                return c;
            }
        };
        table.setDefaultRenderer(Object.class, cellRenderer);
    }

    /**
     * Quét và định dạng toàn bộ JTable trong Container
     */
    public static void styleAllTables(Container root) {
        for (Component comp : root.getComponents()) {
            if (comp instanceof JTable) {
                styleTable((JTable) comp);
            } else if (comp instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) comp;
                scroll.setBorder(new LineBorder(BORDER_HAIRLINE, 1));
                if (scroll.getViewport().getView() instanceof JTable) {
                    styleTable((JTable) scroll.getViewport().getView());
                }
            } else if (comp instanceof Container) {
                styleAllTables((Container) comp);
            }
        }
    }

    // ==========================================
    // 🎨 BUTTON STYLING HELPERS
    // ==========================================

    /**
     * Nút hành động chính (Primary): Nền Slate Navy #0F172A, chữ trắng, font đậm
     */
    public static void stylePrimaryButton(JButton button) {
        button.setBackground(NAVY_PRIMARY);
        button.setForeground(Color.WHITE);
        button.setFont(FONT_HEADER);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(NAVY_PRIMARY, 1, true),
                new EmptyBorder(7, 16, 7, 16)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Nút điểm nhấn công nghệ (Accent): Nền Cobalt Blue #1E40AF
     */
    public static void styleAccentButton(JButton button) {
        button.setBackground(NAVY_ACCENT);
        button.setForeground(Color.WHITE);
        button.setFont(FONT_HEADER);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(NAVY_ACCENT, 1, true),
                new EmptyBorder(7, 16, 7, 16)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Nút phụ (Secondary): Nền trắng, viền hairline #CBD5E1, chữ Slate Navy
     */
    public static void styleSecondaryButton(JButton button) {
        button.setBackground(BG_CARD);
        button.setForeground(NAVY_PRIMARY);
        button.setFont(FONT_BOLD);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_INPUT, 1, true),
                new EmptyBorder(7, 14, 7, 14)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Nút xóa / Cảnh báo (Danger): Nền trắng, viền đỏ nhẹ, chữ đỏ #DC2626
     */
    public static void styleDangerButton(JButton button) {
        button.setBackground(BG_CARD);
        button.setForeground(COLOR_DANGER);
        button.setFont(FONT_BOLD);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0xFC, 0xA5, 0xA5), 1, true),
                new EmptyBorder(7, 14, 7, 14)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Ô nhập liệu JTextField chuẩn hairline
     */
    public static void styleTextField(JTextField txt) {
        txt.setBackground(BG_CARD);
        txt.setForeground(TEXT_MAIN);
        txt.setFont(FONT_BODY);
        txt.setCaretColor(TEXT_MAIN);
        txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_INPUT, 1, true),
                new EmptyBorder(6, 10, 6, 10)
        ));
    }

    // ==========================================
    // 🏷️ BADGE & PILL HELPERS
    // ==========================================
    public static final Color BADGE_GREEN_BG  = new Color(0xDC, 0xFC, 0xE7);
    public static final Color BADGE_GREEN_FG  = new Color(0x16, 0x65, 0x34);
    public static final Color BADGE_BLUE_BG   = new Color(0xDB, 0xEA, 0xFE);
    public static final Color BADGE_BLUE_FG   = new Color(0x1E, 0x40, 0xAF);
    public static final Color BADGE_GRAY_BG   = new Color(0xF1, 0xF5, 0xF9);
    public static final Color BADGE_GRAY_FG   = new Color(0x47, 0x55, 0x69);
    public static final Color BADGE_RED_BG    = new Color(0xFE, 0xE2, 0xE2);
    public static final Color BADGE_RED_FG    = new Color(0x99, 0x1B, 0x1B);
    public static final Color BADGE_ORANGE_BG = new Color(0xFE, 0xF3, 0xC7);
    public static final Color BADGE_ORANGE_FG = new Color(0x92, 0x40, 0x0E);

    public static JLabel createBadge(String text, String type) {
        JLabel badge = new JLabel(text, SwingConstants.CENTER);
        badge.setFont(new Font("Segoe UI", Font.BOLD, 11));
        badge.setOpaque(true);
        Color bg, fg;
        if ("green".equalsIgnoreCase(type) || "trong".equalsIgnoreCase(type) || "sàng".equalsIgnoreCase(type)) {
            bg = BADGE_GREEN_BG; fg = BADGE_GREEN_FG;
        } else if ("blue".equalsIgnoreCase(type) || "choi".equalsIgnoreCase(type) || "dung".equalsIgnoreCase(type) || "hoatdong".equalsIgnoreCase(type)) {
            bg = BADGE_BLUE_BG; fg = BADGE_BLUE_FG;
        } else if ("red".equalsIgnoreCase(type) || "danger".equalsIgnoreCase(type)) {
            bg = BADGE_RED_BG; fg = BADGE_RED_FG;
        } else if ("orange".equalsIgnoreCase(type) || "baotri".equalsIgnoreCase(type)) {
            bg = BADGE_ORANGE_BG; fg = BADGE_ORANGE_FG;
        } else {
            bg = BADGE_GRAY_BG; fg = BADGE_GRAY_FG;
        }
        badge.setBackground(bg);
        badge.setForeground(fg);
        badge.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(bg, 1, true),
            new EmptyBorder(3, 8, 3, 8)
        ));
        return badge;
    }

    public static JLabel createBadge(String text, Color bg, Color fg) {
        JLabel badge = new JLabel(text, SwingConstants.CENTER);
        badge.setFont(new Font("Segoe UI", Font.BOLD, 11));
        badge.setOpaque(true);
        badge.setBackground(bg);
        badge.setForeground(fg);
        badge.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(bg, 1, true),
            new EmptyBorder(3, 8, 3, 8)
        ));
        return badge;
    }

    public static String formatMoney(double amount) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0 ₫");
        java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(symbols);
        return df.format(amount);
    }

    /**
     * Thẻ Card viền hairline
     */
    public static void styleCardPanel(JPanel pnl) {
        pnl.setBackground(BG_CARD);
        pnl.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_HAIRLINE, 1, true),
            new EmptyBorder(12, 16, 12, 16)
        ));
    }

    public static JPanel createCardPanel() {
        JPanel pnl = new JPanel();
        styleCardPanel(pnl);
        return pnl;
    }

    public static JPanel createKPICard(String title, String value, String subtitle, Color subColor) {
        JPanel pnl = createCardPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        pnl.setPreferredSize(new Dimension(200, 95));

        JLabel lblTitle = new JLabel(title.toUpperCase());
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTitle.setForeground(TEXT_MUTED);

        JLabel lblVal = new JLabel(value);
        lblVal.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblVal.setForeground(NAVY_PRIMARY);

        JLabel lblSub = new JLabel(subtitle);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(subColor != null ? subColor : TEXT_MUTED);

        pnl.add(lblTitle);
        pnl.add(Box.createVerticalStrut(4));
        pnl.add(lblVal);
        pnl.add(Box.createVerticalStrut(3));
        pnl.add(lblSub);
        return pnl;
    }

    /**
     * Quét và áp dụng đồng bộ giao diện Navy Tech cho toàn bộ Container
     */
    public static void applyTheme(Container container) {
        if (container == null) return;

        // Thay nền canvas nếu đang mang màu xám NetBeans cũ (204, 204, 204)
        Color oldBg = container.getBackground();
        if (oldBg != null && (oldBg.getRed() == 204 && oldBg.getGreen() == 204 && oldBg.getBlue() == 204)) {
            container.setBackground(BG_CANVAS);
        }

        for (Component c : container.getComponents()) {
            if (c instanceof JPanel) {
                JPanel p = (JPanel) c;
                Color pBg = p.getBackground();
                if (pBg != null && (pBg.getRed() == 204 && pBg.getGreen() == 204 && pBg.getBlue() == 204)) {
                    p.setBackground(BG_CANVAS);
                }
                applyTheme(p);
            } else if (c instanceof JTextField) {
                styleTextField((JTextField) c);
            } else if (c instanceof JPasswordField) {
                JPasswordField pwd = (JPasswordField) c;
                pwd.setBackground(BG_CARD);
                pwd.setForeground(TEXT_MAIN);
                pwd.setFont(FONT_BODY);
                pwd.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(BORDER_INPUT, 1, true),
                        new EmptyBorder(6, 10, 6, 10)
                ));
            } else if (c instanceof JTable) {
                styleTable((JTable) c);
            } else if (c instanceof JScrollPane) {
                JScrollPane sp = (JScrollPane) c;
                sp.setBorder(new LineBorder(BORDER_HAIRLINE, 1));
                if (sp.getViewport().getView() instanceof JTable) {
                    styleTable((JTable) sp.getViewport().getView());
                }
            } else if (c instanceof JLabel) {
                JLabel lbl = (JLabel) c;
                Font f = lbl.getFont();
                if (f != null) {
                    int size = f.getSize();
                    int style = f.getStyle();
                    lbl.setFont(new Font("Segoe UI", style, size));
                }
                // Nếu chữ màu đen hoặc xám cũ, gán màu chuẩn
                if (lbl.getForeground() != null && lbl.getForeground().equals(Color.BLACK)) {
                    lbl.setForeground(TEXT_MAIN);
                }
            } else if (c instanceof JButton) {
                JButton btn = (JButton) c;
                String txt = btn.getText() != null ? btn.getText().toLowerCase() : "";
                if (txt.contains("xóa") || txt.contains("đóng") || txt.contains("tắt") || txt.contains("hủy")) {
                    styleDangerButton(btn);
                } else if (txt.contains("làm mới") || txt.contains("lại") || txt.contains("tìm") || txt.contains("chọn")) {
                    styleSecondaryButton(btn);
                } else if (txt.contains("thêm") || txt.contains("lưu") || txt.contains("mở") || txt.contains("thanh toán") || txt.contains("đăng nhập") || txt.contains("xác nhận")) {
                    stylePrimaryButton(btn);
                } else {
                    styleSecondaryButton(btn);
                }
            } else if (c instanceof Container) {
                applyTheme((Container) c);
            }
        }
    }
}
