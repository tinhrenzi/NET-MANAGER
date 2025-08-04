/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.MayTinhDAO;
import entity.MayTinh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.XJdbc;

import java.util.List;
import util.XQuery;

public class MayTinhDAOImpl implements MayTinhDAO {
    private final String Sql_Cre = "insert into MayTinh(TenMay,TrangThai,GiaTheoGio) values (?,?,?);";
    private final String Sql_Up = "Update SDMAY set TenMay = ?,TrangThai =? ,NgayChoi =null ,NgayKetThuc= null ,GioBatDau = null"
            + " ,GioKetThuc = null ,GiaTheoGio = ? ,TongTien =null where MaMay = ?";
    private final String Sql_De ="Delete from MayTinh where Id=?";
    private final String Sql_All= "select * from MayTinh SDMAY";

    @Override
    public MayTinh create(MayTinh entity) {
        Object[] args = {
            entity.getTenMay(),
            entity.getTrangThai(),
            entity.getGiaTheoGio()
        };
        XJdbc.executeUpdate(Sql_Cre, args);
        return entity;
    }

    @Override
    public void update(MayTinh entity) {
        String sql = "update MayTinh set TrangThai=? where Id= ? ";
        try (
            Connection conn = XJdbc.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, entity.getTrangThai());

            ps.setInt(2, entity.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật máy tính: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void deleteByID(String id) {
    XJdbc.executeUpdate(Sql_De, id);
    }

    @Override
    public List<MayTinh> findAll() {
    return XQuery.getBeanList(MayTinh.class, Sql_All);
    }

    @Override
    public MayTinh findByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     @Override
    public void capNhatTrangThai(String maMay, String trangThai) {
        String sql = "UPDATE MayTinh SET TrangThai = ? WHERE Id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThai);
            ps.setString(2, maMay);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Connection conn = XJdbc.openConnection();
    @Override
    public String layTrangThai(String maMay) {
        String sql = "SELECT TrangThai FROM MayTinh WHERE Id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maMay);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không rõ";
    }

    @Override
    public List<String> getTatCaMaMay() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT Id FROM MayTinh";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(rs.getString(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void capNhatTrangThai(int maMay, String trangThai) {
        String sql = "UPDATE MayTinh SET TrangThai = ? WHERE Id = ?";
        try (
            Connection con = XJdbc.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, trangThai);
            ps.setInt(2, maMay);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

