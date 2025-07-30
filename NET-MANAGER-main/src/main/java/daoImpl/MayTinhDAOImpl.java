/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.MayTinhDAO;
import entity.MayTinh;
import java.sql.*;
import java.util.*;
import util.XJdbc;

import java.util.List;

public class MayTinhDAOImpl implements MayTinhDAO {
 @Override
    public void insert(MayTinh mt) {
        String sql = "INSERT INTO MayTinh (Id, Name, NowTime, StartTime, Status) VALUES (?, ?, ?, ?, ?)";
        XJdbc.executeUpdate(sql, mt.getId(), mt.getName(), mt.getNowTime(), mt.getStartTime(), mt.getStatus());
    }

    @Override
    public void update(MayTinh mt) {
        String sql = "UPDATE MayTinh SET Name=?, NowTime=?, StartTime=?, Status=? WHERE Id=?";
        XJdbc.executeUpdate(sql, mt.getName(), mt.getNowTime(), mt.getStartTime(), mt.getStatus(), mt.getId());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM MayTinh WHERE Id=?";
        XJdbc.executeUpdate(sql, id);
    }

    @Override
    public MayTinh selectById(String id) {
        String sql = "SELECT * FROM MayTinh WHERE Id=?";
        List<MayTinh> list = selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<MayTinh> selectAll() {
        String sql = "SELECT * FROM MayTinh";
        return selectBySql(sql);
    }

    @Override
    public void moMay(String id) {
        String sql = "UPDATE MayTinh SET Status=N'Đang hoạt động', StartTime=?, NowTime=? WHERE Id=?";
        Timestamp now = new Timestamp(System.currentTimeMillis());
        XJdbc.executeUpdate(sql, now, now, id);
    }

    @Override
    public void tatMay(String id) {
        String sql = "UPDATE MayTinh SET Status=N'Tắt', NowTime=?, StartTime=NULL WHERE Id=?";
        Timestamp now = new Timestamp(System.currentTimeMillis());
        XJdbc.executeUpdate(sql, now, id);
    }

    private List<MayTinh> selectBySql(String sql, Object... args) {
        List<MayTinh> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                MayTinh mt = new MayTinh(
                    rs.getString("Id"),
                    rs.getString("Name"),
                        rs.getTimestamp("StartTime"),
                    rs.getTimestamp("NowTime"),
                    rs.getString("Status")
                );
                list.add(mt);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

