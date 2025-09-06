package daoImpl;

import dao.QuanLyThongKeDAO;
import entity.Menu;
import entity.SuDungMay;
import entity.ThongKeDoanhThu;
import util.XJdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuanLyThongKeDaoImpl implements QuanLyThongKeDAO {

    public QuanLyThongKeDaoImpl() {
    }

    @Override
    public List<ThongKeDoanhThu> getAllThonKe() {
        List<ThongKeDoanhThu> list = new ArrayList<>();
        String sql = "SELECT "
                + "ISNULL(SUM(TongTienMay), 0) AS TongTienMay, "
                + "ISNULL(SUM(TongTienMon), 0) AS TongTienMon, "
                + "ISNULL(SUM(TongTien), 0) AS TongDoanhThu "
                + "FROM ThanhToan";

        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                ThongKeDoanhThu tkd = new ThongKeDoanhThu();
                tkd.setTongTienMay(rs.getDouble("TongTienMay"));
                tkd.setTongTienMon(rs.getDouble("TongTienMon"));
                tkd.setTongDoanhThu(rs.getDouble("TongDoanhThu"));
                list.add(tkd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<SuDungMay> getAllSDMay() {
        List<SuDungMay> list = new ArrayList<>();
        String sql = """
                 SELECT
                     sdm.TenMay,
                     COUNT(tt.Id) AS SoLanSuDung,
                     CAST(ROUND(SUM(tt.TongGio), 2) AS DECIMAL(10,2)) AS TongGioSuDung,
                     MAX(tt.GiaTienTheoGio) AS GiaTheoGio,
                     SUM(tt.TongTienMay) AS TongTien 
                 FROM ThanhToan tt
                 JOIN SDMAY sdm ON sdm.Id = tt.MaSDMay
                 WHERE tt.NgayThanhToan IS NOT NULL
                 GROUP BY sdm.TenMay
                 ORDER BY sdm.TenMay;
                 """;

        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SuDungMay sdm = new SuDungMay();
                sdm.setTenMay(rs.getString("TenMay"));
                sdm.setId(rs.getInt("SoLanSuDung"));
                sdm.setThoiGianChoi(rs.getDouble("TongGioSuDung"));
                sdm.setGiaTheoGio(rs.getFloat("GiaTheoGio"));
                sdm.setTongTien(rs.getFloat("TongTien"));
                list.add(sdm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<SuDungMay> getLichSuSuDungMay(Date tuNgay, Date denNgay) {
        List<SuDungMay> list = new ArrayList<>();
        String sql = """
        SELECT
                             sdm.TenMay,
                             COUNT(tt.Id) AS SoLanSuDung,
                             CAST(ROUND(SUM(tt.TongGio), 2) AS DECIMAL(10,2)) AS TongGioSuDung,
                             MAX(tt.GiaTienTheoGio) AS GiaTheoGio,
                             SUM(tt.TongTienMay) AS TongTien 
                         FROM ThanhToan tt
                         JOIN SDMAY sdm ON sdm.Id = tt.MaSDMay
                         WHERE tt.NgayThanhToan IS NOT NULL
                         GROUP BY sdm.TenMay
                         ORDER BY sdm.TenMay;
    """;

        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, tuNgay);
            ps.setDate(2, denNgay);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SuDungMay sdm = new SuDungMay();
                sdm.setTenMay(rs.getString("TenMay"));
                sdm.setId(rs.getInt("SoLanSuDung"));
                sdm.setThoiGianChoi(rs.getDouble("TongGioSuDung"));
                sdm.setGiaTheoGio(rs.getFloat("GiaTheoGio"));
                sdm.setTongTien(rs.getFloat("TongTien"));
                list.add(sdm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<ThongKeDoanhThu> getthongKeTheoKhoangNgay(Date tuNgay, Date denNgay) {
        List<ThongKeDoanhThu> list = new ArrayList<>();
        String sql
                = "SELECT "
                + "    ISNULL(SUM(tt.TongTien), 0) AS TongTienMay, "
                + "    ISNULL(SUM(m.TongTien), 0) AS TongTienMenu, "
                + "    ISNULL(SUM(tt.TongTien), 0) + ISNULL(SUM(m.TongTien), 0) AS TongDoanhThu "
                + "FROM ThanhToan tt "
                + "FULL OUTER JOIN Menu m ON CONVERT(DATE, tt.NgayChoi) = CONVERT(DATE, m.NgayMua) "
                + "WHERE (tt.NgayChoi BETWEEN ? AND ? OR tt.NgayChoi IS NULL) "
                + "  AND (m.NgayMua BETWEEN ? AND ? OR m.NgayMua IS NULL)";

        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, tuNgay);
            ps.setDate(2, denNgay);
            ps.setDate(3, tuNgay);
            ps.setDate(4, denNgay);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ThongKeDoanhThu tk = new ThongKeDoanhThu();
                tk.setTongTienMay(rs.getFloat("TongTienMay"));
                tk.setTongTienMon(rs.getFloat("TongTienMenu"));
                tk.setTongDoanhThu(rs.getFloat("TongDoanhThu"));
                list.add(tk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> list = new ArrayList<>();
        String sql
                = "SELECT m.MaMon, m.TenMon, SUM(m.SoLuong) AS SoLuong, SUM(m.TongTien) AS TongTienMenu "
                + "FROM Menu m "
                + "GROUP BY m.MaMon, m.TenMon";

        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Menu sdm = new Menu();
                sdm.setMaMon(rs.getString("MaMon"));
                sdm.setTenMon(rs.getString("TenMon"));
                sdm.setSoLuong(rs.getInt("SoLuong"));
                sdm.setTongTien(rs.getFloat("TongTienMenu"));
                list.add(sdm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Menu> getLichSuMenu(Date tuNgay, Date denNgay) {
        List<Menu> list = new ArrayList<>();
        String sql = """
        SELECT 
            m.MaMon, 
            m.TenMon, 
            SUM(m.SoLuong) AS SoLuong, 
            SUM(m.TongTien) AS TongTienMenu
        FROM Menu m
        WHERE CAST(m.NgayMua AS DATE) BETWEEN ? AND ?
        GROUP BY m.MaMon, m.TenMon
        ORDER BY m.TenMon;
    """;
        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, tuNgay);
            ps.setDate(2, denNgay);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Menu m = new Menu();
                    m.setMaMon(rs.getString("MaMon"));
                    m.setTenMon(rs.getString("TenMon"));
                    m.setSoLuong(rs.getInt("SoLuong"));
                    m.setTongTien(rs.getFloat("TongTienMenu"));
                    list.add(m);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
