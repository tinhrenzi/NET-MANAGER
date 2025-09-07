package daoImpl;

import entity.MonAn;
import java.sql.*;
import java.util.List;
import util.XJdbc;
import util.XQuery;
import dao.MonAnDAO;

public class MonAnDAOImpl implements MonAnDAO {

    private final Connection conn;

    public MonAnDAOImpl() {
        conn = XJdbc.openConnection();
    }
    private final String INSERT_SQL = "INSERT INTO MonAn(TenMon, GiaTien, SoLuong,HinhAnh) VALUES ( ?, ?,?,?)";
    private final String UPDATE_SQL = "UPDATE MonAn SET TenMon = ?, GiaTien = ?,SoLuong =?,HinhAnh =? WHERE Id = ?";
    private final String DELETE_SQL = "DELETE FROM MonAn WHERE Id = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM MonAn";

    @Override
    public MonAn create(MonAn entity) {
        Object[] args = {
            entity.getTenMon(),
            entity.getGiaTien(),
            entity.getSoLuong(),
            entity.getHinhANh()
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return entity;
    }

    @Override
    public void update(MonAn entity) {
        try {
            Object[] args = {
                entity.getTenMon(),
                entity.getGiaTien(),
                entity.getSoLuong(),
                entity.getHinhANh(),
                entity.getId()
            };
            XJdbc.executeUpdate(UPDATE_SQL, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByID(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public MonAn findByID(String id) {
        String sql = "select * from MonAn where Id = ?";
        List<MonAn> list = XQuery.getBeanList(MonAn.class, sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<MonAn> findAll() {
        return XQuery.getBeanList(MonAn.class, SELECT_ALL_SQL);
    }
}
