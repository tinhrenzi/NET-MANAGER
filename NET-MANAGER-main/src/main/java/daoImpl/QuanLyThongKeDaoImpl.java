package daoImpl;

import dao.QuanLyThongKeDAO;
import entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuanLyThongKeDaoImpl implements QuanLyThongKeDAO {
    private Connection conn;

    public QuanLyThongKeDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Order> getLichSuSuDung(Date tuNgay, Date denNgay, String maMay, String tenKhachHang) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM LichSu ls "
                   + "JOIN NguoiDung nd ON ls.IdUser = nd.IdUser "
                   + "WHERE ls.ThoiGianBD BETWEEN ? AND ? "
                   + "AND (? = '' OR ls.MaMayTinh = ?) "
                   + "AND (? = '' OR nd.TenDangNhap LIKE ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(tuNgay.getTime()));
            ps.setTimestamp(2, new Timestamp(denNgay.getTime()));
            ps.setString(3, maMay);
            ps.setString(4, maMay);
            ps.setString(5, tenKhachHang);
            ps.setString(6, "%" + tenKhachHang + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order ls = new Order();
                // TODO: set dữ liệu từ rs -> ls
                list.add(ls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ThongKeDoanhThu> getDoanhThu(Date tuNgay, Date denNgay) {
        List<ThongKeDoanhThu> list = new ArrayList<>();
        String sql = "SELECT * FROM ThongKeDoanhThu WHERE NgayThongKe BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(tuNgay.getTime()));
            ps.setDate(2, new java.sql.Date(denNgay.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeDoanhThu tk = new ThongKeDoanhThu();
                // TODO: set dữ liệu từ rs -> tk
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<HoaDonChiTiet> getLichSuBanHang(Date tuNgay, Date denNgay) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "SELECT c.* FROM HoaDonChiTiet c "
                   + "JOIN HoaDon h ON c.MaHoaDon = h.MaHoaDon "
                   + "WHERE h.NgayLap BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(tuNgay.getTime()));
            ps.setTimestamp(2, new Timestamp(denNgay.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet c = new HoaDonChiTiet();
                // TODO: set dữ liệu từ rs -> c
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public double getTongDoanhThu(Date tuNgay, Date denNgay) {
        double total = 0;
        String sql = "SELECT SUM(TongTien) FROM ThongKeDoanhThu WHERE NgayThongKe BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(tuNgay.getTime()));
            ps.setDate(2, new java.sql.Date(denNgay.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public int getTongGioSuDung(Date tuNgay, Date denNgay, String maMay) {
        int total = 0;
        String sql = "SELECT SUM(TongThoiGian) FROM LichSu "
                   + "WHERE ThoiGianBD BETWEEN ? AND ? AND (? = '' OR MaMayTinh = ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(tuNgay.getTime()));
            ps.setTimestamp(2, new Timestamp(denNgay.getTime()));
            ps.setString(3, maMay);
            ps.setString(4, maMay);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}
