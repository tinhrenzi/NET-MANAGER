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
import util.XQuery;

import java.util.List;

public class MayTinhDAOImpl implements MayTinhDAO {

    private final String INSERT_SQL = "INSERT INTO MayTinh (MaMayTinh, TenMay, TrangThai, ViTri, ThoiGian) VALUES (?,?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE MayTinh SET TenMay=?, TrangThai=?, ViTri=?, ThoiGian=? WHERE MaMayTinh=?";
    private final String DELETE_SQL = "DELETE FROM MayTinh WHERE MaMayTinh=?";
    private final String findAll = "SELECT * FROM MayTinh";
    private final String findById = "SELECT * FROM MayTinh WHERE MaMayTinh=?";

    @Override
    public MayTinh create(MayTinh mt) {
        Object[] args = {
            mt.getMaMayTinh(),
            mt.getTenMay(),
            mt.getTrangThai(),
            mt.getViTri(),
            mt.getThoiGian()
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return mt;
    }

    @Override
    public void update(MayTinh mt) {
        Object[] args = {
            mt.getTenMay(),
            mt.getTrangThai(),
            mt.getViTri(),
            mt.getThoiGian(),
            mt.getMaMayTinh()
        };
        XJdbc.executeUpdate(UPDATE_SQL, args);
    }

    @Override
    public void deleteByID(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<MayTinh> findAll() {
       String findAll = "SELECT * FROM MayTinh";
          List<MayTinh> list = new ArrayList<>();
    try (Connection conn = XJdbc.openConnection();
         PreparedStatement ps = conn.prepareStatement(findAll);  // ✅ sửa ở đây
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            MayTinh mt = new MayTinh();
            mt.setMaMayTinh(rs.getString("MaMayTinh"));
            mt.setTenMay(rs.getString("TenMay"));
            mt.setTrangThai(rs.getString("TrangThai"));
            mt.setViTri(rs.getString("ViTri"));
            mt.setThoiGian(rs.getInt("ThoiGian"));  // cột này là int, đúng
            list.add(mt);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
    }

    @Override
    public MayTinh findByID(String id) {
        return XQuery.getSingleBean(MayTinh.class, findById, id);
    }
}

