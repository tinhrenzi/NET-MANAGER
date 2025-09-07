package util;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.sun.tools.javac.Main;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.InputStream;

public class Style_Net {

    /**
     * Setup FlatLaf và style mặc định cho toàn bộ ứng dụng
     */
    public static void setup() {
        try {
            // Cài FlatLaf (Light theme)
            UIManager.setLookAndFeel(new FlatIntelliJLaf());

            // ======================
            // 🎨 Style toàn cục
            // ======================
            // Font mặc định
            UIManager.put("defaultFont", new Font("Montserrat", Font.PLAIN, 14));

            // Bo góc component vừa phải
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 8);
            UIManager.put("TextComponent.arc", 8);
            UIManager.put("ProgressBar.arc", 10);

            // Padding cho nút & textfield
            UIManager.put("Button.minimumWidth", 90);
            UIManager.put("Button.margin", new Insets(5, 12, 5, 12));
            UIManager.put("TextComponent.margin", new Insets(5, 8, 5, 8));

            // Màu sắc chủ đạo
            UIManager.put("Component.focusColor", new Color(100, 149, 237));   // Xanh Cornflower
            UIManager.put("Button.hoverBackground", new Color(0x154D71));
            UIManager.put("Table.selectionBackground", new Color(65, 105, 225)); // xanh đậm khi chọn
            UIManager.put("Table.selectionForeground", Color.WHITE);
            UIManager.put("Table.alternateRowColor", new Color(245, 245, 245));

            // JTable grid lines
            UIManager.put("Table.showHorizontalLines", true);
            UIManager.put("Table.showVerticalLines", true);
            UIManager.put("Table.intercellSpacing", new Dimension(1, 1));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Áp dụng style cho 1 JTable
     */
    public static void styleTable(JTable table) {
        // Header căn giữa, in đậm, nền xanh, chữ trắng
        DefaultTableCellRenderer headerRenderer
                = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(70, 130, 180)); // Steel Blue
        table.getTableHeader().setForeground(Color.WHITE);

        // Nội dung căn giữa mặc định
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEADING);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Chiều cao hàng
        table.setRowHeight(28);

        // Cho phép fill viewport
        table.setFillsViewportHeight(true);
    }

    /**
     * Áp dụng style cho tất cả JTable trong container (JFrame/JDialog/Panel)
     */
    public static void styleAllTables(Container root) {
        for (Component comp : root.getComponents()) {
            if (comp instanceof JTable) {
                styleTable((JTable) comp);
            } else if (comp instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) comp;
                if (scroll.getViewport().getView() instanceof JTable) {
                    styleTable((JTable) scroll.getViewport().getView());
                }
            } else if (comp instanceof Container) {
                styleAllTables((Container) comp);
            }
        }
    }

    // ==========================
    // 🎨 Button Styles
    // ==========================
    public static void stylePrimaryButton(JButton button) {
        button.putClientProperty("JButton.buttonType", "roundRect");
        button.setBackground(new Color(65, 105, 225)); // xanh đậm
        button.setForeground(Color.WHITE);
    }

    public static void styleDangerButton(JButton button) {
        button.putClientProperty("JButton.buttonType", "roundRect");
        button.setBackground(new Color(220, 53, 69)); // đỏ bootstrap
        button.setForeground(Color.WHITE);
    }

    public static void styleSuccessButton(JButton button) {
        button.putClientProperty("JButton.buttonType", "roundRect");
        button.setBackground(new Color(40, 167, 69)); // xanh lá
        button.setForeground(Color.WHITE);
    }
}
