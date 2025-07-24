/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.DoAnDAO;
import entity.DoAn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.XJdbc;
import util.XQuery;

import java.util.List;
import static util.XJdbc.openConnection;

public class DoAnDAOImpl implements DoAnDAO {

    // Câu lệnh SQL
    private final String INSERT_SQL = "INSERT INTO DoAn (MaDoAn, TenDoAn, DonGia, SoLuong, TrangThai) VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE DoAn SET TenDoAn=?, DonGia=?, SoLuong=?, TrangThai=? WHERE MaDoAn=?";
    private final String DELETE_SQL = "DELETE FROM DoAn WHERE MaDoAn=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM DoAn";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM DoAn WHERE MaDoAn=?";
    private Connection getConnection() throws SQLException {
        return openConnection();
    }

    @Override
    public DoAn create(DoAn da) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, da.getMaDoAn());
            stmt.setString(2, da.getTenDoAn());
            stmt.setFloat(3, da.getDonGia());
            stmt.setInt(4, da.getSoLuong());
            stmt.setString(5, da.getTrangThai());
            stmt.executeUpdate();
            return da;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(DoAn da) {
         if (da == null) return; // Kiểm tra null

    try {
        Object[] args = {
            da.getTenDoAn(),
            da.getDonGia(),
            da.getSoLuong(),
            da.getTrangThai(),
            da.getMaDoAn()
        };
        XJdbc.executeUpdate(UPDATE_SQL, args);
    } catch (Exception e) {
        e.printStackTrace();
    }
        
    }

    

    @Override
    public List<DoAn> findAll() {
        return XQuery.getBeanList(DoAn.class, SELECT_ALL_SQL);
    }


    @Override
public void deleteByID(String id) {
    XJdbc.executeUpdate(DELETE_SQL, id); 
}


    @Override
public DoAn findByID(String id) {
   return XQuery.getSingleBean(DoAn.class, SELECT_BY_ID_SQL, id); 
}
}