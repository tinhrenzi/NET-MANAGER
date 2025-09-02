package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.QuanLyThongKeDAO;
import entity.Menu;
import entity.SuDungMay;
import entity.ThongKeDoanhThu;
import util.XJdbc;

public class QuanLyThongKeDaoImpl implements QuanLyThongKeDAO {

    private Connection conn = XJdbc.openConnection();

    public QuanLyThongKeDaoImpl() {
    }

    @Override
    public List<ThongKeDoanhThu> getAllThonKe() {
        List<ThongKeDoanhThu> list = new ArrayList<>();

        String sqlMay = """
        SELECT ISNULL(SUM(
            (DATEDIFF(SECOND, CAST(sdm.GioBatDau AS DATETIME),
                CASE 
                    WHEN sdm.GioKetThuc < sdm.GioBatDau 
                    THEN DATEADD(DAY, 1, CAST(sdm.GioKetThuc AS DATETIME)) 
                    ELSE CAST(sdm.GioKetThuc AS DATETIME) 
                END
            ) / 3600.0) * sdm.GiaTheoGio
        ), 0) AS TongTienMay
        FROM SDMAY sdm
        WHERE sdm.GioKetThuc IS NOT NULL
    """;

        String sqlMenu = """
        SELECT ISNULL(SUM(m.TongTien), 0) AS TongTienMon
        FROM Menu m
    """;

        double tongTienMay = 0;
        double tongTienMon = 0;

        try (Connection conn = XJdbc.openConnection()) {
            // --- Tổng tiền máy ---
            try (PreparedStatement ps = conn.prepareStatement(sqlMay); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tongTienMay = rs.getDouble("TongTienMay");
                }
            }

            // --- Tổng tiền món ---
            try (PreparedStatement ps = conn.prepareStatement(sqlMenu); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tongTienMon = rs.getDouble("TongTienMon");
                }
            }

            // --- Đưa vào đối tượng ---
            ThongKeDoanhThu tk = new ThongKeDoanhThu();
            tk.setTongTienMay(tongTienMay);
            tk.setTongTienMon(tongTienMon);
            tk.setTongDoanhThu(tongTienMay + tongTienMon);
            list.add(tk);

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
            COUNT(*) AS SoLanSuDung,
            SUM(
                DATEDIFF(SECOND, CAST(sdm.GioBatDau AS DATETIME),
                    CASE 
                        WHEN sdm.GioKetThuc < sdm.GioBatDau 
                        THEN DATEADD(DAY, 1, CAST(sdm.GioKetThuc AS DATETIME)) 
                        ELSE CAST(sdm.GioKetThuc AS DATETIME) 
                    END
                ) / 3600.0
            ) AS TongGioSuDung,
            sdm.GiaTheoGio,
            SUM(
                (DATEDIFF(SECOND, CAST(sdm.GioBatDau AS DATETIME),
                    CASE 
                        WHEN sdm.GioKetThuc < sdm.GioBatDau 
                        THEN DATEADD(DAY, 1, CAST(sdm.GioKetThuc AS DATETIME)) 
                        ELSE CAST(sdm.GioKetThuc AS DATETIME) 
                    END
                ) / 3600.0) * sdm.GiaTheoGio
            ) AS TongTien
        FROM SDMAY sdm
        WHERE sdm.GioKetThuc IS NOT NULL
        GROUP BY sdm.MaMay, sdm.TenMay, sdm.GiaTheoGio
        ORDER BY sdm.TenMay;
    """;

        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SuDungMay sdm = new SuDungMay();
                sdm.setTenMay(rs.getString("TenMay"));
                sdm.setSoLanSuDung(rs.getInt("SoLanSuDung"));
                sdm.setThoiGianChoi(rs.getDouble("TongGioSuDung"));
                sdm.setGiaTheoGio(rs.getFloat("GiaTheoGio"));
                sdm.setTongTien(rs.getDouble("TongTien"));
                list.add(sdm);
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
    public List<SuDungMay> getLichSuSuDungMay(Date tuNgay, Date denNgay) {
        List<SuDungMay> list = new ArrayList<>();
        String sql = """
        SELECT 
            sdm.MaMay,
            sdm.TenMay,
            COUNT(*) AS SoLanSuDung,
            sdm.GiaTheoGio,
            SUM(
                DATEDIFF(SECOND, CAST(sdm.GioBatDau AS DATETIME),
                    CASE 
                        WHEN sdm.GioKetThuc < sdm.GioBatDau 
                        THEN DATEADD(DAY, 1, CAST(sdm.GioKetThuc AS DATETIME)) 
                        ELSE CAST(sdm.GioKetThuc AS DATETIME) 
                    END
                ) / 3600.0
            ) AS TongGioSuDung,
            SUM(
                (DATEDIFF(SECOND, CAST(sdm.GioBatDau AS DATETIME),
                    CASE 
                        WHEN sdm.GioKetThuc < sdm.GioBatDau 
                        THEN DATEADD(DAY, 1, CAST(sdm.GioKetThuc AS DATETIME)) 
                        ELSE CAST(sdm.GioKetThuc AS DATETIME) 
                    END
                ) / 3600.0) * sdm.GiaTheoGio
            ) AS TongTien
        FROM SDMAY sdm
        WHERE sdm.NgayChoi BETWEEN ? AND ?
          AND sdm.GioKetThuc IS NOT NULL
        GROUP BY sdm.MaMay, sdm.TenMay, sdm.GiaTheoGio
        ORDER BY sdm.TenMay;
    """;

        try (Connection conn = XJdbc.openConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, tuNgay);
            ps.setDate(2, denNgay);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SuDungMay sdm = new SuDungMay();
                    sdm.setTenMay(rs.getString("TenMay"));
                    sdm.setSoLanSuDung(rs.getInt("SoLanSuDung"));
                    sdm.setThoiGianChoi(rs.getDouble("TongGioSuDung"));
                    sdm.setGiaTheoGio(rs.getFloat("GiaTheoGio"));
                    sdm.setTongTien(rs.getDouble("TongTien"));
                    list.add(sdm);
                }
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
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<ThongKeDoanhThu> getthongKeTheoKhoangNgay(Date tuNgay, Date denNgay) {
        List<ThongKeDoanhThu> list = new ArrayList<>();

        String sqlMay = """
        SELECT ISNULL(SUM(
            (DATEDIFF(SECOND, CAST(sdm.GioBatDau AS DATETIME),
                CASE 
                    WHEN sdm.GioKetThuc < sdm.GioBatDau 
                    THEN DATEADD(DAY, 1, CAST(sdm.GioKetThuc AS DATETIME)) 
                    ELSE CAST(sdm.GioKetThuc AS DATETIME) 
                END
            ) / 3600.0) * sdm.GiaTheoGio
        ), 0) AS TongTienMay
        FROM SDMAY sdm
        WHERE sdm.NgayChoi BETWEEN ? AND ?
          AND sdm.GioKetThuc IS NOT NULL
    """;

        String sqlMenu = """
        SELECT ISNULL(SUM(m.TongTien), 0) AS TongTienMenu
        FROM Menu m
        WHERE CAST(m.NgayMua AS DATE) BETWEEN ? AND ?
    """;

        double tongTienMay = 0;
        double tongTienMon = 0;

        try (Connection conn = XJdbc.openConnection()) {
            // --- Tiền máy ---
            try (PreparedStatement ps = conn.prepareStatement(sqlMay)) {
                ps.setDate(1, tuNgay);
                ps.setDate(2, denNgay);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        tongTienMay = rs.getDouble("TongTienMay");
                    }
                }
            }

            // --- Tiền món ---
            try (PreparedStatement ps = conn.prepareStatement(sqlMenu)) {
                ps.setDate(1, tuNgay);
                ps.setDate(2, denNgay);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        tongTienMon = rs.getDouble("TongTienMenu");
                    }
                }
            }

            // --- Gộp kết quả ---
            ThongKeDoanhThu tk = new ThongKeDoanhThu();
            tk.setTongTienMay(tongTienMay);
            tk.setTongTienMon(tongTienMon);
            tk.setTongDoanhThu(tongTienMay + tongTienMon);
            list.add(tk);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
