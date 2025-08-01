package daoImpl;

import dao.QuanLyThongKeDAO;
import entity.Order;
import entity.SuDungMay;
import entity.ThongKeDoanhThu;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.XJdbc;


public class QuanLyThongKeDaoImpl implements QuanLyThongKeDAO {
    private Connection conn = XJdbc.openConnection();

    public QuanLyThongKeDaoImpl() {}
@Override
    public List<ThongKeDoanhThu > thongKeTheoNgay(java.sql.Date ngay) {
        List<ThongKeDoanhThu> list = new ArrayList<>();
        String sql = "SELECT * FROM ThongKe WHERE NgayChoi = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, ngay);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeDoanhThu tk = new ThongKeDoanhThu();
                tk.setId(rs.getInt("Id"));
                tk.setMaThanhToan(rs.getString("MaThanhToan"));
                tk.setMaMenu(rs.getString("MaMenu"));
                tk.setNgayChoi(rs.getDate("NgayChoi"));
                tk.setTongTienMon(rs.getDouble("TongTienMon"));
                tk.setTongTienMay(rs.getDouble("TongTienMay"));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public List<SuDungMay> getLichSuMay(java.sql.Date ngay) {
        List<SuDungMay> list = new ArrayList<>();
String sql = "SELECT " +
            "    sm.MaMay, " +
            "    sm.NgayChoi, " +
            "    sm.GioBatDau, " +
            "    sm.GioKetThuc, " +
            "    DATEDIFF(MINUTE, sm.GioBatDau, sm.GioKetThuc) / 60.0 AS GioChoi, " +
            "    tt.GiaTienTheoGio, " +
            "    (DATEDIFF(MINUTE, sm.GioBatDau, sm.GioKetThuc) / 60.0) * tt.GiaTienTheoGio AS TongTien " +
            "FROM " +
            "    SDMAY sm " + // ← DẤU CÁCH Ở CUỐI RẤT QUAN TRỌNG
            "OUTER APPLY ( " +
            "    SELECT TOP 1 GiaTienTheoGio " +
            "    FROM ThanhToan " +
            "    WHERE MaMay = sm.MaMay " +
            ") tt " +
            "WHERE " +
            "    sm.NgayChoi = ? " +
            "    AND sm.GioBatDau IS NOT NULL " +
            "    AND sm.GioKetThuc IS NOT NULL;";

      

        try (Connection con = XJdbc.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDate(1, ngay);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            SuDungMay dto = new SuDungMay();
            dto.setMaMay(rs.getString("MaMay"));
            dto.setNgaySuDung(rs.getDate("NgayChoi"));
            dto.setGioBatDau(rs.getTime("GioBatDau"));
            dto.setGioKetThuc(rs.getTime("GioKetThuc"));
            dto.setThoiGianChoi(rs.getFloat("GioChoi"));
            dto.setGiaTienTheoGio(rs.getFloat("GiaTienTheoGio"));
            dto.setTongTien(rs.getFloat("TongTien"));
            list.add(dto);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Order> getLichSuBanHang(Date fromDate) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT " +
                   "ma.Id AS MaMon, " +
                   "ma.TenMon, " +
                   "SUM(mn.SoLuong) AS TongSoLuongBan, " +
                   "SUM(mn.TongTien) AS TongDoanhThu " +
                   "FROM MonAn ma " +
                   "JOIN Menu mn ON ma.Id = mn.MaMon " +
                   "JOIN ThanhToan tt ON tt.MaMenu = mn.Id " +
                   "WHERE tt.NgayChoi = ? " +
                   "GROUP BY ma.Id, ma.TenMon";

        try (Connection con = XJdbc.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDate(1, fromDate);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Order dto = new Order();
            dto.setMaMon(rs.getString("MaMon"));
            dto.setTenMon(rs.getString("TenMon"));
            dto.setSoLuong(rs.getInt("TongSoLuongBan"));
            dto.setTongTien(rs.getFloat("TongDoanhThu"));
            list.add(dto);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;    
    }
    @Override
    public List<ThongKeDoanhThu> getAll1() {
        List<ThongKeDoanhThu> list = new ArrayList<>();
        String sqldoa = "select Id,MaThanhToan,NgayChoi,TongTienMon,TongTienMay from ThongKe";
        try (PreparedStatement ps = conn.prepareStatement(sqldoa)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeDoanhThu tk = new ThongKeDoanhThu();
                tk.setId(rs.getInt("Id"));
                tk.setMaThanhToan(rs.getString("MaThanhToan"));
                //tk.setMaMenu(rs.getString("MaMenu"));
                tk.setNgayChoi(rs.getDate("NgayChoi"));
                tk.setTongTienMon(rs.getDouble("TongTienMon"));
                tk.setTongTienMay(rs.getDouble("TongTienMay"));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<SuDungMay> getAll2() {
        List<SuDungMay> list = new ArrayList<>();
        String sqldoa = "Select MaMay,NgayChoi,GioBatDau,GioKetThuc,DATEDIFF(MINUTE, GioBatDau,GioKetThuc) / 60.0 AS GioChoi,GiaTheoGio,(DATEDIFF(MINUTE, GioBatDau, GioKetThuc) / 60.0) * GiaTheoGio AS TongTien FROM SDMAY";
        try (PreparedStatement ps = conn.prepareStatement(sqldoa)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SuDungMay sd = new SuDungMay();
                sd.setMaMay(rs.getString("MaMay"));
                sd.setNgaySuDung(rs.getDate("NgayChoi"));
                sd.setGioBatDau(rs.getTime("GioBatDau"));
                sd.setGioKetThuc(rs.getTime("GioKetThuc"));
                sd.setThoiGianChoi(rs.getFloat("GioChoi"));
                sd.setGiaTienTheoGio(rs.getFloat("GiaTheoGio"));
                sd.setTongTien(rs.getFloat("TongTien"));
                list.add(sd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Order> getAll3() {
        List<Order> list = new ArrayList<>();
        String sqldoa = "	Select ma.Id as MaMon ,ma.TenMon,SUM(mn.SoLuong) as SoLuong,SUM(mn.TongTien) as TongTien from MonAn ma join Menu mn on ma.Id = mn.MaMon group by ma.Id ,ma.TenMon ";
        try (PreparedStatement ps = conn.prepareStatement(sqldoa)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order od = new Order();
                od.setMaMon(rs.getString("MaMon"));
                od.setTenMon(rs.getString("TenMon"));
                od.setSoLuong(rs.getInt("SoLuong"));
                od.setTongTien(rs.getFloat("TongTien"));
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
