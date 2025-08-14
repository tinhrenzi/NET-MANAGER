/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;
import entity.ThanhToan;
import util.XJdbc;
import dao.ThanhToanDAO;
import entity.MayTinh;
import entity.SuDungMay;

public class ThanhToanDAOImpl implements ThanhToanDAO {
    
    @Override
    public ThanhToan ThanhToan(ThanhToan tt) {
        String INSERT_SQL = 
            "INSERT INTO ThanhToan " +
            "(MaSDMay, NgayChoi, ThoiGianChoi, GiaTienTheoGio, TongGio, TongTienMay, TongTienMon,NgayThanhToan, TongTien) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

        Object[] args = {
            tt.getMaSDMay(),
            java.sql.Date.valueOf(tt.getNgayBatDau().toLocalDate()), // Ngày chơi
            java.sql.Timestamp.valueOf(tt.getNgayKetThuc()),         // Thời gian kết thúc
            tt.getGiaTheoGio(),
            tt.getTongGio(),
            tt.getTienMay(),
            tt.getTienMenu(),
            tt.getNgayThanhToan(),
            tt.getTongTien()
        };

        XJdbc.executeUpdate(INSERT_SQL, args);
        return tt;
    }

    @Override
    public void TatMay(SuDungMay SDM, MayTinh mt) {
        
        String Sql_TatMay = "Update SDMAY set TrangThai =? where Id =?";
        Object[] i = {
            "Đã thanh toán",
            SDM.getId()
        };
        XJdbc.executeUpdate(Sql_TatMay, i);
        
        String Sql_TrangThai = "Update MayTinh set TrangThai =? where TenMay =?";
                Object[] j = {
                "Trống",
                mt.getTenMay()
            };
            XJdbc.executeUpdate(Sql_TrangThai, j);
    }
}