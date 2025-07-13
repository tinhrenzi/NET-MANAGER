/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;


import dao.DoUongDAO;
import entity.DoUong;
import util.XJdbc;
import util.XQuery;

import java.util.List;

public class DoUongDAOImpl implements DoUongDAO {

    private final String INSERT_SQL = "INSERT INTO DoUong (MaDoUong, TenDoUong, DonGia, SoLuong, TrangThai) VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE DoUong SET TenDoUong=?, DonGia=?, SoLuong=?, TrangThai=? WHERE MaDoUong=?";
    private final String DELETE_SQL = "DELETE FROM DoUong WHERE MaDoUong=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM DoUong";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM DoUong WHERE MaDoUong=?";

    @Override
    public DoUong create(DoUong du) {
        Object[] args = {
            du.getMaDoUong(),
            du.getTenDoUong(),
            du.getDonGia(),
            du.getSoLuong(),
            du.getTrangThai()
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return du;
    }

    @Override
    public void update(DoUong du) {
        Object[] args = {
            du.getTenDoUong(),
            du.getDonGia(),
            du.getSoLuong(),
            du.getTrangThai(),
            du.getMaDoUong()
        };
        XJdbc.executeUpdate(UPDATE_SQL, args);
    }

    @Override
    public void deleteByID(String maDoUong) {
        XJdbc.executeUpdate(DELETE_SQL, maDoUong);
    }

    @Override
    public List<DoUong> findAll() {
        return XQuery.getBeanList(DoUong.class, SELECT_ALL_SQL);
    }

    @Override
    public DoUong findByID(String maDoUong) {
        return XQuery.getSingleBean(DoUong.class, SELECT_BY_ID_SQL, maDoUong);
    }
}

