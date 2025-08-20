/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.SDMayDAO;
import entity.MayTinh;
import entity.SuDungMay;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.XJdbc;
import util.XQuery;

/**
 *
 * @author NITRO 5
 */
public class SDMayDAOImpl implements SDMayDAO {

    private Connection conn = XJdbc.openConnection();

    @Override
    public void MoMay(SuDungMay SDM, MayTinh mt) {
        String Sql_MoMay = "insert into SDMAY (TrangThai, NgayChoi,GioBatDau,TenMay,GiaTheoGio)  values (?,?,?,?,?)";
        Object[] args = {
            "Hoạt động",
            SDM.getNgayChoi(),
            SDM.getGioBatDau(),
            mt.getTenMay(),
            SDM.getGiaTheoGio()
        };
        XJdbc.executeUpdate(Sql_MoMay, args);
        String Sql_TrangThai = "Update MayTinh set TrangThai =? where TenMay =?";
        Object[] i = {
            "Hoạt động",
            mt.getTenMay()
        };
        XJdbc.executeUpdate(Sql_TrangThai, i);
    }

    @Override
    public void TatMay(SuDungMay SDM, MayTinh mt) {
        String Sql_TatMay = "Update SDMAY set TrangThai =?,NgayKetThuc = ?,GioKetThuc=?,TongTien =?  where Id =?";
        Object[] args = {
            "Chưa thanh toán",
            SDM.getNgayKetThuc(),
            SDM.getGioKetThuc(),
            SDM.getTongTien(),
            SDM.getId()
        };
        XJdbc.executeUpdate(Sql_TatMay, args);
        String Sql_TrangThai = "Update MayTinh set TrangThai =? where TenMay =?";
        Object[] i = {
            "Trống",
            mt.getTenMay()
        };
        XJdbc.executeUpdate(Sql_TrangThai, i);
    }

    @Override
    public List<SuDungMay> findAll() {
        List<SuDungMay> list = new ArrayList<>();
        String sql = "SELECT Id, MaMay, TenMay, TrangThai, NgayChoi, NgayKetThuc, GioBatDau, GioKetThuc, GiaTheoGio, TongTien FROM SDMAY";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SuDungMay suDungMay = new SuDungMay();
                suDungMay.setId(rs.getInt("Id"));
                suDungMay.setMaMay(rs.getString("MaMay"));
                suDungMay.setTenMay(rs.getString("TenMay"));
                suDungMay.setTrangThai(rs.getString("TrangThai"));
                suDungMay.setNgayChoi(rs.getDate("NgayChoi"));
                suDungMay.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                suDungMay.setGioBatDau(rs.getTime("GioBatDau"));
                suDungMay.setGioKetThuc(rs.getTime("GioKetThuc"));
                suDungMay.setGiaTheoGio(rs.getFloat("GiaTheoGio"));
                suDungMay.setTongTien(rs.getDouble("TongTien"));
                // ThoiGianChoi is not in the table, so it remains unset or could be calculated if needed
                list.add(suDungMay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<MayTinh> finMayTinh() {
        List<MayTinh> list = new ArrayList<>();
        String sql = "SELECT Id, TenMay, TrangThai, GiaTheoGio FROM MayTinh";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MayTinh mayTinh = new MayTinh();
                mayTinh.setId(rs.getString("Id"));
                mayTinh.setTenMay(rs.getString("TenMay"));
                mayTinh.setTrangThai(rs.getString("TrangThai"));
                mayTinh.setGiaTheoGio(rs.getFloat("GiaTheoGio"));
                list.add(mayTinh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean isMayDangHoatDong(String tenMay) {
        String sql = "SELECT COUNT(*) FROM SDMAY WHERE TenMay = ? AND TrangThai = 'Hoạt động'";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, tenMay);
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu máy đang hoạt động
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
