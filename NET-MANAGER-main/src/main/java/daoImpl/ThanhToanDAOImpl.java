/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;
import entity.ThanhToan;
import util.XJdbc;
import util.XQuery;

import java.sql.Date;
import java.util.List;
import dao.ThanhToanDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ThanhToanDAOImpl implements ThanhToanDAO {

@Override
public boolean insert(ThanhToan tt) {
    String sql = "INSERT INTO ThanhToan (MaMay, NgayChoi, ThoiGianChoi, GiaTienTheoGio,TongGio, TongTien) " +
                 "VALUES (?, ?, ?, ?, ?,?)";
    try (Connection con = XJdbc.openConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, tt.getMaMay());
        ps.setDate(2, java.sql.Date.valueOf(tt.getNgayBatDau().toLocalDate()));
        ps.setTimestamp(3, java.sql.Timestamp.valueOf(tt.getNgayKetThuc()));
        ps.setDouble(4, tt.getGiaTheoGio());
        ps.setDouble(5, tt.getTongGio());
        ps.setDouble(6, tt.getTongTien());

        return ps.executeUpdate() > 0; // trả về true nếu insert thành công
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}



   
}