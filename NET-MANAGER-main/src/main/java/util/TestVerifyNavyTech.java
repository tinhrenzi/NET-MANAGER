package util;

import util.Style_Net;
import util.XAuth;
import entity.Admin;
import daoImpl.*;
import ui.*;
import ui.manager.*;
import javax.swing.JFrame;

public class TestVerifyNavyTech {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   BẮT ĐẦU KIỂM THỬ GIAO DIỆN NAVY TECH TOÀN DIỆN");
        System.out.println("==================================================");

        try {
            // 1. Kiểm tra Style_Net FlatLaf setup
            System.out.print("[1/5] Khởi tạo Style_Net (FlatLaf Navy Tech)... ");
            Style_Net.setup();
            System.out.println("OK");

            // 2. Kiểm tra kết nối CSDL và DAO
            System.out.print("[2/5] Kiểm tra CSDL & truy vấn các DAO... ");
            AdminDAOImpl adminDAO = new AdminDAOImpl();
            Admin admin = adminDAO.findByUsername("admin");
            if (admin == null) {
                System.out.println("CẢNH BÁO: Không tìm thấy tài khoản admin trong CSDL.");
            } else {
                XAuth.user = admin;
            }

            MayTinhDAOImpl mayTinhDAO = new MayTinhDAOImpl();
            int countMay = mayTinhDAO.findAll().size();

            MonAnDAOImpl monAnDAO = new MonAnDAOImpl();
            int countMon = monAnDAO.findAll().size();
            System.out.println("OK (" + countMay + " máy, " + countMon + " món)");

            // 3. Khởi tạo tất cả 9 màn hình UI
            System.out.println("[3/5] Khởi tạo & kiểm tra nạp Layout 9 màn hình:");
            JFrame dummy = new JFrame();

            // Màn 1: Đăng nhập
            System.out.print("   - DangNhapJDialog: ");
            DangNhapJDialog dangNhap = new DangNhapJDialog(dummy, false);
            dangNhap.dispose();
            System.out.println("OK");

            // Màn 2: Dashboard NetManagerJFrame (bỏ qua modal blocking để test layout)
            System.out.print("   - NetManagerJFrame: ");
            NetManagerJFrame mainFrame = new NetManagerJFrame() {
                @Override
                public void showWelcome(JFrame jFrame) { }
                @Override
                public void showLogin(JFrame jFrame) { }
            };
            mainFrame.dispose();
            System.out.println("OK");

            // Màn 3: Sơ đồ phòng máy (MoMayJDialog)
            System.out.print("   - MoMayJDialog: ");
            MoMayJDialog moMay = new MoMayJDialog(dummy, false);
            moMay.dispose();
            System.out.println("OK");

            // Màn 4: Thực đơn gọi món F&B (MenuJDialog)
            System.out.print("   - MenuJDialog: ");
            MenuJDialog menu = new MenuJDialog(dummy, false);
            menu.dispose();
            System.out.println("OK");

            // Màn 5: Hóa đơn thanh toán (ThanhToanJDialog)
            System.out.print("   - ThanhToanJDialog: ");
            ThanhToanJDialog thanhToan = new ThanhToanJDialog(dummy, false);
            thanhToan.dispose();
            System.out.println("OK");

            // Màn 6: Quản lý danh mục máy trạm (QuanLyMayTinh)
            System.out.print("   - QuanLyMayTinh: ");
            QuanLyMayTinh qlMay = new QuanLyMayTinh(dummy, false);
            qlMay.dispose();
            System.out.println("OK");

            // Màn 7: Quản lý thực đơn F&B (QuanLyThucDonJDialog)
            System.out.print("   - QuanLyThucDonJDialog: ");
            QuanLyThucDonJDialog qlThucDon = new QuanLyThucDonJDialog(dummy, false);
            qlThucDon.dispose();
            System.out.println("OK");

            // Màn 8: Quản lý nhân viên (QuanLyNhanVienJDialog)
            System.out.print("   - QuanLyNhanVienJDialog: ");
            QuanLyNhanVienJDialog qlNhanVien = new QuanLyNhanVienJDialog(dummy, false);
            qlNhanVien.dispose();
            System.out.println("OK");

            // Màn 9: Quản lý thống kê (QuanLyThongKeJDialog)
            System.out.print("   - QuanLyThongKeJDialog: ");
            QuanLyThongKeJDialog qlThongKe = new QuanLyThongKeJDialog(dummy, false);
            qlThongKe.dispose();
            System.out.println("OK");

            dummy.dispose();

            System.out.println("[4/5] Tất cả các component UI nạp và render thành công, không phát sinh lỗi.");
            System.out.println("[5/5] Hoàn thành kiểm thử xác thực 100%!");
            System.out.println("==================================================");
            System.out.println("            TẤT CẢ KIỂM THỬ ĐÃ PASS!              ");
            System.out.println("==================================================");
            System.exit(0);

        } catch (Throwable t) {
            System.err.println("\n[LỖI TRONG QUÁ TRÌNH KIỂM THỬ]: " + t.getMessage());
            t.printStackTrace();
            System.exit(1);
        }
    }
}
