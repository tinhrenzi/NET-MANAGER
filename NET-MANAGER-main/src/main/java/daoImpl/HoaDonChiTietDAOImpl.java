/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoImpl;

import dao.HoaDonChiTietDAO;
import entity.HoaDonChiTiet;
import util.XJdbc;
import util.XQuery;

import java.util.List;

public class HoaDonChiTietDAOImpl implements HoaDonChiTietDAO {

    private final String INSERT_SQL = "INSERT INTO HoaDonChiTiet (MaHoaDonChiTiet, MaHoaDon, MaDoAn, MaDoUong, TenDoAn, TenDoUong, SoLuong, DonGia) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE HoaDonChiTiet SET MaHoaDon=?, MaDoAn=?, MaDoUong=?, TenDoAn=?, TenDoUong=?, SoLuong=?, DonGia=? WHERE MaHoaDonChiTiet=?";
    private final String DELETE_SQL = "DELETE FROM HoaDonChiTiet WHERE MaHoaDonChiTiet=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM HoaDonChiTiet";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM HoaDonChiTiet WHERE MaHoaDonChiTiet=?";

    @Override
    public HoaDonChiTiet create(HoaDonChiTiet hdct) {
        Object[] args = {
            hdct.getMaHoaDonChiTiet(),
            hdct.getMaHoaDon(),
            hdct.getMaDoAn(),
            hdct.getMaDoUong(),
            hdct.getTenDoAn(),
            hdct.getTenDoUong(),
            hdct.getSoLuong(),
            hdct.getDonGia()
        };
        XJdbc.executeUpdate(INSERT_SQL, args);
        return hdct;
    }

    @Override
    public void update(HoaDonChiTiet hdct) {
        Object[] args = {
            hdct.getMaHoaDon(),
            hdct.getMaDoAn(),
            hdct.getMaDoUong(),
            hdct.getTenDoAn(),
            hdct.getTenDoUong(),
            hdct.getSoLuong(),
            hdct.getDonGia(),
            hdct.getMaHoaDonChiTiet()
        };
        XJdbc.executeUpdate(UPDATE_SQL, args);
    }

    @Override
    public void deleteByID(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<HoaDonChiTiet> findAll() {
        return XQuery.getBeanList(HoaDonChiTiet.class, SELECT_ALL_SQL);
    }

    @Override
    public HoaDonChiTiet findByID(String id) {
        return XQuery.getSingleBean(HoaDonChiTiet.class, SELECT_BY_ID_SQL, id);
    }
}
