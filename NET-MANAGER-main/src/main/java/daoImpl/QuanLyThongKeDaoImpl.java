package daoImpl;


import entity.Menu;
import entity.SuDungMay;
import entity.ThanhToan;
import entity.ThongKeDoanhThu;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.XJdbc;
import dao.QuanLyThongKeDAO;


public class QuanLyThongKeDaoImpl implements QuanLyThongKeDAO {
    private Connection conn = XJdbc.openConnection();

    public QuanLyThongKeDaoImpl() {}

    @Override
    public List<ThongKeDoanhThu> getAllThonKe() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
        public List<SuDungMay> getAllSDMay() {
        List<SuDungMay> list = new ArrayList<>();
        String sql = "SELECT mt.Id AS MaMay, mt.TenMay, " +
                     "SUM(ISNULL(tt.TongGio, 0)) AS TongThoiGianSuDung, " +
                     "mt.GiaTheoGio, " +
                     "CAST(SUM(ISNULL(tt.TongTien, 0)) AS DECIMAL(20, 2)) AS TongTien " +
                     "FROM ThanhToan tt " +
                     "JOIN MayTinh mt ON tt.MaMay = mt.Id " +
                     "GROUP BY mt.Id, mt.TenMay, mt.GiaTheoGio";


        try (Connection conn = XJdbc.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ThanhToan tt = new ThanhToan();
                SuDungMay sdm = new SuDungMay();
                sdm.setMaMay(rs.getInt("MaMay"));
                sdm.setTenMay(rs.getString("TenMay"));
                sdm.setThoiGianChoi(rs.getFloat("TongThoiGianSuDung"));
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
    public List<Menu> getAllMenu() {
    List<Menu> list = new ArrayList<>();
        String sql = 
         "SELECT m.MaMon, m.TenMon, SUM(m.SoLuong) AS SoLuong, SUM(m.TongTien) AS TongTienMenu " +
         "FROM Menu m " +
         "GROUP BY m.MaMon, m.TenMon";

            try (Connection conn = XJdbc.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Menu sdm = new Menu();
                sdm.setMaMon(rs.getInt("MaMon"));
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
    String sql = "SELECT MaMay, TenMay, TrangThai, NgayChoi, NgayKetThuc, GioBatDau, GioKetThuc, GiaTheoGio, TongTien " +
                 "FROM SDMAY " +
                 "WHERE NgayChoi BETWEEN ? AND ?";

    try (Connection conn = XJdbc.openConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setDate(1, tuNgay);
        ps.setDate(2, denNgay);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            SuDungMay sdm = new SuDungMay();
            sdm.setMaMay(rs.getInt("MaMay"));
            sdm.setTenMay(rs.getString("TenMay"));
            sdm.setTrangThai(rs.getString("TrangThai"));
            sdm.setNgayChoi(rs.getDate("NgayChoi"));
            sdm.setNgayKetThuc(rs.getDate("NgayKetThuc"));
            sdm.setGioBatDau(rs.getTime("GioBatDau"));
            sdm.setGioKetThuc(rs.getTime("GioKetThuc"));
            sdm.setGiaTheoGio(rs.getFloat("GiaTheoGio"));
            sdm.setTongTien(rs.getFloat("TongTien"));

            // Tính thời gian chơi theo giờ
            if (sdm.getGioBatDau() != null && sdm.getGioKetThuc() != null) {
                long milliseconds = sdm.getGioKetThuc().getTime() - sdm.getGioBatDau().getTime();
                float gioChoi = milliseconds / (1000f * 60 * 60);
                sdm.setThoiGianChoi(gioChoi);
            } else {
                sdm.setThoiGianChoi(0);
            }

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
       String sql = "SELECT MaMon, TenMon, SUM(SoLuong) AS SoLuong, SUM(TongTien) AS TongTienMenu " +
                    "FROM Menu " +
                    "WHERE NgayMua BETWEEN ? AND ? " +
                    "GROUP BY MaMon, TenMon";

       try (Connection conn = XJdbc.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

           ps.setDate(1, tuNgay);
           ps.setDate(2, denNgay);

           try (ResultSet rs = ps.executeQuery()) {
               while (rs.next()) {
                   Menu m = new Menu();
                   m.setMaMon(rs.getInt("MaMon"));
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
        String sql = 
            "SELECT " +
            "    ISNULL(SUM(tt.TongTien), 0) AS TongTienMay, " +
            "    ISNULL(SUM(m.TongTien), 0) AS TongTienMenu, " +
            "    ISNULL(SUM(tt.TongTien), 0) + ISNULL(SUM(m.TongTien), 0) AS TongDoanhThu " +
            "FROM ThanhToan tt " +
            "FULL OUTER JOIN Menu m ON CONVERT(DATE, tt.NgayChoi) = CONVERT(DATE, m.NgayMua) " +
            "WHERE (tt.NgayChoi BETWEEN ? AND ? OR tt.NgayChoi IS NULL) " +
            "  AND (m.NgayMua BETWEEN ? AND ? OR m.NgayMua IS NULL)";

        try (Connection conn = XJdbc.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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
public List<SuDungMay> getAllSDMAY_FromTable() {
    List<SuDungMay> list = new ArrayList<>();
    String sql = "SELECT * FROM SDMAY";

    try (Connection conn = XJdbc.openConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            SuDungMay sdm = new SuDungMay();
            sdm.setId(rs.getInt("Id"));
            sdm.setMaMay(rs.getInt("MaMay"));
            sdm.setTenMay(rs.getString("TenMay"));
            sdm.setTrangThai(rs.getString("TrangThai"));
            sdm.setNgayChoi(rs.getDate("NgayChoi"));
            sdm.setNgayKetThuc(rs.getDate("NgayKetThuc"));
            sdm.setGioBatDau(rs.getTime("GioBatDau"));
            sdm.setGioKetThuc(rs.getTime("GioKetThuc"));
            sdm.setGiaTheoGio(rs.getFloat("GiaTheoGio"));
            sdm.setTongTien(rs.getFloat("TongTien"));

            // Tính thời gian chơi nếu có giờ bắt đầu và kết thúc
            if (sdm.getGioBatDau() != null && sdm.getGioKetThuc() != null) {
                long millis = sdm.getGioKetThuc().getTime() - sdm.getGioBatDau().getTime();
                float gio = millis / (1000f * 60 * 60);
                sdm.setThoiGianChoi(gio);
            }

            list.add(sdm);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

}
