/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.MayTinhDAO;
import entity.MayTinh;
import util.XJdbc;
import util.XQuery;

import java.util.List;

public class MayTinhDAOImpl implements MayTinhDAO {

    private final String INSERT_SQL = "INSERT INTO MayTinh (MaMayTinh, TenMay, TrangThai, ViTri) VALUES (?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE MayTinh SET TenMay=?, TrangThai=?, ViTri=? WHERE MaMayTinh=?";
    private final String DELETE_SQL = "DELETE FROM MayTinh WHERE MaMayTinh=?";
    private final String findAll = "SELECT * FROM MayTinh";
    private final String findById = "SELECT * FROM MayTinh WHERE MaMayTinh=?";

    @Override
    public MayTinh create(MayTinh mt) {
        Object[] args = {
            mt.getMaMayTinh(),
            mt.getTenMay(),
            mt.getTrangThai(),
            mt.getViTri()
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
        return XQuery.getBeanList(MayTinh.class, findAll);
    }

    @Override
    public MayTinh findByID(String id) {
        return XQuery.getSingleBean(MayTinh.class, findById, id);
    }
}

