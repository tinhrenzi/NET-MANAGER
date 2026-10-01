/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui;

import controller.WelcomeController;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * Màn hình chào mừng khởi động (Splash Screen) phong cách Navy Tech
 * @author NET-MANAGER Team
 */
public class WelcomeJDialog extends javax.swing.JDialog implements WelcomeController {

    private JLabel lblStatusText;
    private JLabel lblPercent;
    private boolean isWaitingStarted = false;

    /**
     * Creates new form WelcomeJDialog
     * @param parent
     * @param modal
     */
    public WelcomeJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        initNavyWelcomeTheme();
    }

    /**
     * Khởi tạo giao diện chuẩn Navy Tech: Slate Navy #0F172A, Cobalt #1E40AF, Cyan #38BDF8
     */
    private void initNavyWelcomeTheme() {
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());

        // ROOT PANEL
        JPanel root = new JPanel(new BorderLayout());
        root.setPreferredSize(new Dimension(620, 350));
        root.setBackground(new Color(0x0F, 0x17, 0x2A)); // Slate Navy #0F172A
        root.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(0x1E, 0x40, 0xAF), 2, true),
            new EmptyBorder(32, 40, 24, 40)
        ));

        // CENTER CONTAINER
        JPanel pnlCenter = new JPanel();
        pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
        pnlCenter.setOpaque(false);

        // Vector Tech Workstation Icon (Vẽ Java2D độ phân giải cao, không phụ thuộc file ảnh ngoài)
        JPanel iconBadge = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

                int w = getWidth();
                int h = getHeight();
                int size = 56;
                int x = (w - size) / 2;
                int y = (h - size) / 2;

                // Gradient background badge
                GradientPaint gp = new GradientPaint(x, y, new Color(0x1E, 0x40, 0xAF), x + size, y + size, new Color(0x02, 0x84, 0xC7));
                g2.setPaint(gp);
                g2.fillRoundRect(x, y, size, size, 16, 16);

                // Glow accent border
                g2.setColor(new Color(0x38, 0xBD, 0xF8, 200));
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(x, y, size, size, 16, 16);

                // Monitor Bezel
                int sw = 30, sh = 20;
                int sx = x + (size - sw) / 2;
                int sy = y + 12;
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawRoundRect(sx, sy, sw, sh, 4, 4);

                // Monitor Stand Neck & Base
                int standX = x + size / 2;
                g2.drawLine(standX, sy + sh, standX, sy + sh + 5);
                g2.drawLine(standX - 7, sy + sh + 5, standX + 7, sy + sh + 5);

                // Activity Signal Line on screen
                g2.setColor(new Color(0x38, 0xBD, 0xF8));
                g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine(sx + 5, sy + 10, sx + 10, sy + 10);
                g2.drawLine(sx + 13, sy + 6, sx + 16, sy + 14);
                g2.drawLine(sx + 19, sy + 10, sx + 25, sy + 10);

                g2.dispose();
            }
        };
        iconBadge.setPreferredSize(new Dimension(64, 64));
        iconBadge.setMaximumSize(new Dimension(64, 64));
        iconBadge.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconBadge.setOpaque(false);
        pnlCenter.add(iconBadge);

        pnlCenter.add(Box.createVerticalStrut(14));

        // Brand Name (Chuẩn thương hiệu NET-MANAGER)
        JLabel lblTitle = new JLabel("NET-MANAGER");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlCenter.add(lblTitle);

        pnlCenter.add(Box.createVerticalStrut(4));

        // Subtitle
        JLabel lblSub = new JLabel("HỆ THỐNG QUẢN LÝ PHÒNG MÁY CHUYÊN NGHIỆP");
        lblSub.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblSub.setForeground(new Color(0x94, 0xA3, 0xB8)); // Slate 400
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlCenter.add(lblSub);

        pnlCenter.add(Box.createVerticalStrut(32));

        // Status Message & Percentage Row
        JPanel pnlStatusRow = new JPanel(new BorderLayout());
        pnlStatusRow.setOpaque(false);
        pnlStatusRow.setMaximumSize(new Dimension(540, 22));
        pnlStatusRow.setPreferredSize(new Dimension(540, 22));
        pnlStatusRow.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblStatusText = new JLabel("Đang khởi tạo hệ thống...");
        lblStatusText.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblStatusText.setForeground(new Color(0xCB, 0xD5, 0xE1)); // Slate 300
        pnlStatusRow.add(lblStatusText, BorderLayout.WEST);

        lblPercent = new JLabel("0%");
        lblPercent.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPercent.setForeground(new Color(0x38, 0xBD, 0xF8)); // Sky Blue Accent
        pnlStatusRow.add(lblPercent, BorderLayout.EAST);

        pnlCenter.add(pnlStatusRow);
        pnlCenter.add(Box.createVerticalStrut(8));

        // Modern Slim Progress Bar
        ProgressBar.setPreferredSize(new Dimension(540, 6));
        ProgressBar.setMaximumSize(new Dimension(540, 6));
        ProgressBar.setMinimumSize(new Dimension(540, 6));
        ProgressBar.setValue(0);
        ProgressBar.setStringPainted(false);
        ProgressBar.setBorderPainted(false);
        ProgressBar.setBackground(new Color(0x1E, 0x29, 0x3B)); // Slate 800
        ProgressBar.setForeground(new Color(0x38, 0xBD, 0xF8)); // Sky Blue
        ProgressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        ProgressBar.setUI(new BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = c.getWidth();
                int h = c.getHeight();
                // Track
                g2.setColor(new Color(0x1E, 0x29, 0x3B));
                g2.fillRoundRect(0, 0, w, h, 6, 6);
                // Fill
                int amountFull = getAmountFull(new Insets(0, 0, 0, 0), w, h);
                if (amountFull > 0) {
                    GradientPaint gp = new GradientPaint(0, 0, new Color(0x1E, 0x40, 0xAF), amountFull, 0, new Color(0x38, 0xBD, 0xF8));
                    g2.setPaint(gp);
                    g2.fillRoundRect(0, 0, amountFull, h, 6, 6);
                }
                g2.dispose();
            }
        });
        pnlCenter.add(ProgressBar);

        root.add(pnlCenter, BorderLayout.CENTER);

        // Footer Section
        JPanel pnlBottom = new JPanel(new BorderLayout());
        pnlBottom.setOpaque(false);
        JLabel lblFooter = new JLabel("© NET-MANAGER • Vận hành ổn định & Bảo mật", SwingConstants.CENTER);
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(0x64, 0x74, 0x8B)); // Slate 500
        pnlBottom.add(lblFooter, BorderLayout.CENTER);
        root.add(pnlBottom, BorderLayout.SOUTH);

        getContentPane().add(root, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProgressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(ProgressBar, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        waiting();
    }//GEN-LAST:event_formWindowOpened

    /**
     * Chạy tiến trình khởi động mô phỏng và cập nhật thanh tiến trình theo thời gian thực
     */
    @Override
    public void waiting() {
        if (isWaitingStarted) return;
        isWaitingStarted = true;

        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    final int val = i;
                    final String msg;
                    if (val < 25) {
                        msg = "Đang khởi tạo hệ thống...";
                    } else if (val < 50) {
                        msg = "Đang kết nối cơ sở dữ liệu SQL Server...";
                    } else if (val < 75) {
                        msg = "Đang đồng bộ danh mục dịch vụ & sơ đồ máy trạm...";
                    } else if (val < 95) {
                        msg = "Đang tải giao diện điều khiển Navy Tech...";
                    } else {
                        msg = "Hệ thống đã sẵn sàng!";
                    }

                    SwingUtilities.invokeLater(() -> {
                        ProgressBar.setValue(val);
                        if (lblPercent != null) {
                            lblPercent.setText(val + "%");
                        }
                        if (lblStatusText != null) {
                            lblStatusText.setText(msg);
                        }
                    });

                    Thread.sleep(15);
                }
                Thread.sleep(250);
                SwingUtilities.invokeLater(() -> {
                    WelcomeJDialog.this.dispose();
                });
            } catch (InterruptedException ex) {
                SwingUtilities.invokeLater(() -> {
                    WelcomeJDialog.this.dispose();
                });
            }
        }).start();
    }

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
            java.util.logging.Logger.getLogger(WelcomeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WelcomeJDialog dialog = new WelcomeJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JProgressBar ProgressBar;
    // End of variables declaration//GEN-END:variables
}
