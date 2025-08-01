package daoImpl;

import dao.QuanLyThongKeDAO;
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
                tk.setId(rs.getString("Id"));
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
    public List<ThongKeDoanhThu> getAll() {
        List<ThongKeDoanhThu> list = new ArrayList<>();
        String sqldoa = "SELECT * FROM ThongKe";
        try (PreparedStatement ps = conn.prepareStatement(sqldoa)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeDoanhThu tk = new ThongKeDoanhThu();
                tk.setId(rs.getString("Id"));
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
        String sql = "SELECT sm.MaMay,"
                + " sm.NgayChoi,"
                + " sm.GioBatDau,"
                + " sm.GioKetThuc,"
                + " DATEDIFF(MINUTE, sm.GioBatDau, sm.GioKetThuc) / 60.0 AS GioChoi,"
                + " tt.GiaTienTheoGio,(DATEDIFF(MINUTE, sm.GioBatDau, sm.GioKetThuc) / 60.0) * tt.GiaTienTheoGio AS TongTien FROM SDMAY sm "
                + "JOIN ThanhToan tt ON sm.MaMay = tt.MaMay WHERE sm.NgayChoi = ? AND sm.GioBatDau IS NOT NULL AND sm.GioKetThuc IS NOT NULL";

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


}
