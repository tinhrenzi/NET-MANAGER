/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;
import dao.HoaDonDAO;
import entity.HoaDon;
import util.XJdbc;
import util.XQuery;

import java.sql.Date;
import java.util.List;

public class HoaDonDAOImpl implements HoaDonDAO {

    private final String INSERT_SQL = "INSERT INTO HoaDon (MaHoaDon, MaMay, IdUser, NgayLap, TrangThai, PhuongThucThanhToan) VALUES (?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE HoaDon SET MaMay=?, IdUser=?, NgayLap=?, TrangThai=?, PhuongThucThanhToan=? WHERE MaHoaDon=?";
    private final String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHoaDon=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE MaHoaDon=?";

    @Override
    public HoaDon create(HoaDon hd) {
        Object[] args = {
            hd.getMaHoaDon(),
            hd.getMaMay(),
            hd.getIdUser(),
            new Date(hd.getNgayLap().getTime()),
            hd.getTrangThai(),
            hd.getPhuongThucThanhToan()
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return hd;
    }

    @Override
    public void update(HoaDon hd) {
        Object[] args = {
            hd.getMaMay(),
            hd.getIdUser(),
            new Date(hd.getNgayLap().getTime()),
            hd.getTrangThai(),
            hd.getPhuongThucThanhToan(),
            hd.getMaHoaDon()
        };
        XJdbc.executeUpdate(UPDATE_SQL, args);
    }

    @Override
    public void deleteByID(String maHoaDon) {
        XJdbc.executeUpdate(DELETE_SQL, maHoaDon);
    }

    @Override
    public List<HoaDon> findAll() {
        return XQuery.getBeanList(HoaDon.class, SELECT_ALL_SQL);
    }

    @Override
    public HoaDon findByID(String maHoaDon) {
        return XQuery.getSingleBean(HoaDon.class, SELECT_BY_ID_SQL, maHoaDon);
    }
}