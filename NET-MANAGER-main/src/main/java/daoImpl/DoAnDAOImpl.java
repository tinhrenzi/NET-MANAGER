/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.DoAnDAO;
import entity.DoAn;
import util.XJdbc;
import util.XQuery;

import java.util.List;

public class DoAnDAOImpl implements DoAnDAO {

    // Câu lệnh SQL
    private final String INSERT_SQL = "INSERT INTO DoAn (MaDoAn, TenDoAn, DonGia, SoLuong, TrangThai) VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE DoAn SET TenDoAn=?, DonGia=?, SoLuong=?, TrangThai=? WHERE MaDoAn=?";
    private final String DELETE_SQL = "DELETE FROM DoAn WHERE MaDoAn=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM DoAn";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM DoAn WHERE MaDoAn=?";

    @Override
    public DoAn create(DoAn da) {
        Object[] args = {
            da.getMaDoan(),
            da.getTenDoAn(),
            da.getDonGia(),
            da.getSoLuong(),
            da.isTrangThai()
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return da;
    }

    @Override
    public void update(DoAn da) {
        Object[] args = {
            da.getTenDoAn(),
            da.getDonGia(),
            da.getSoLuong(),
            da.isTrangThai(),
            da.getMaDoan()
        };
        XJdbc.executeUpdate(UPDATE_SQL, args);
    }

    

    @Override
    public List<DoAn> findAll() {
        return XQuery.getBeanList(DoAn.class, SELECT_ALL_SQL);
    }


    @Override
    public void deleteByID(String id) {
       XJdbc.executeUpdate(id, DELETE_SQL);
    }

    @Override
    public DoAn findByID(String id) {
       return XQuery.getSingleBean(DoAn.class, id, findByID(id));
    }
}

